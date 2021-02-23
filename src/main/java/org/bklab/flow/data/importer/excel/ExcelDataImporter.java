package org.bklab.flow.data.importer.excel;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bklab.flow.data.importer.core.ImporterEntityCreator;
import org.bklab.flow.data.importer.core.ImporterEntityField;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExcelDataImporter<T> {

    private final ImporterEntityCreator<T> entityCreator;
    private final DataFormatter dataFormatter = new DataFormatter();

    public ExcelDataImporter(ImporterEntityCreator<T> entityCreator) {
        this.entityCreator = entityCreator;
    }

    public List<T> parse(String fileName, InputStream inputStream) throws Exception {
        return fileName.endsWith(".xlsx") ? parseXlsx(inputStream) : parseXls(inputStream);
    }

    public List<T> parseXlsx(InputStream inputStream) throws Exception {
        return parse(new XSSFWorkbook(inputStream));
    }

    public List<T> parseXls(InputStream inputStream) throws Exception {
        return parse(new HSSFWorkbook(inputStream));
    }

    public List<T> parse(Path path) throws Exception {
        return parse(path.toFile());
    }

    public List<T> parse(File file) throws Exception {
        String name = file.getName();
        return parse(name, new FileInputStream(file));
    }

    public List<T> parse(HSSFWorkbook workbook) {
        return parse(workbook, new HSSFFormulaEvaluator(workbook));
    }

    public List<T> parse(XSSFWorkbook workbook) {
        return parse(workbook, new XSSFFormulaEvaluator(workbook));
    }

    public List<T> parse(Workbook workbook, FormulaEvaluator formulaEvaluator) {
        if (workbook.getNumberOfSheets() < 1) return Collections.emptyList();
        Sheet sheet = workbook.getSheetAt(0);

        Row firstRow = findFirstRow(sheet);
        if (firstRow == null) return Collections.emptyList();

        Map<Integer, String> headers = parseHeaders(firstRow, formulaEvaluator);
        if (entityCreator.noHeader()) headers.forEach((k, v) -> headers.put(k, "" + k));

        return IntStream.range(firstRow.getRowNum() + (entityCreator.hasHeader() ? 1 : 0), sheet.getLastRowNum() + 1)
                .mapToObj(sheet::getRow).map(row -> parseRow(row, formulaEvaluator, headers))
                .collect(Collectors.toUnmodifiableList());
    }

    private T parseRow(Row row, FormulaEvaluator formulaEvaluator, Map<Integer, String> headers) {
        T entity = entityCreator.createEntity();
        headers.forEach((k, v) -> {
            ImporterEntityField<T> entityField = entityCreator.get(headers.get(k));
            String cellValue = getCellValue(row.getCell(k), formulaEvaluator);
            if (cellValue == null && !entityField.isNullable()) return;
            entityField.set(entity, cellValue);
        });
        return entity;
    }

    private Row findFirstRow(Sheet sheet) {
        for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (!hasMergedRegion(sheet, row.getRowNum(), 0)) return row;
        }
        return null;
    }

    private Map<Integer, String> parseHeaders(Row row, FormulaEvaluator formulaEvaluator) {
        Map<Integer, String> map = new LinkedHashMap<>();
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            String cellValue = getCellValue(row.getCell(i), formulaEvaluator);
            if (cellValue == null || cellValue.isBlank()) continue;
            map.put(i, cellValue);
        }
        return map;
    }

    private String getCellValue(Cell cell, FormulaEvaluator formulaEvaluator) {
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case _NONE, BLANK, ERROR -> null;
            case STRING -> cell.getStringCellValue();
            default -> dataFormatter.formatCellValue(cell, formulaEvaluator);
        };
    }

    private boolean hasMergedRegion(Sheet sheet, int rowNumber, int columnNumber) {
        return sheet.getMergedRegions().stream().anyMatch(mergedRegion ->
                inRange(rowNumber, mergedRegion.getFirstRow(), mergedRegion.getLastRow())
                && inRange(columnNumber, mergedRegion.getFirstColumn(), mergedRegion.getLastColumn()));
    }

    private boolean inRange(int i, int min, int max) {
        return min <= i && i <= max;
    }

}
