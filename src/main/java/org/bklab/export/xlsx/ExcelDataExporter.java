package org.bklab.export.xlsx;

import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.StreamResourceWriter;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.bklab.export.data.ColumnDataBuilder;
import org.bklab.export.data.IColumnDataSupplier;
import org.bklab.flow.dialog.ErrorDialog;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Map;

public class ExcelDataExporter<T> {

    private final ColumnDataBuilder<T> builder;

    public ExcelDataExporter(ColumnDataBuilder<T> builder) {
        this.builder = builder;
    }

    public File createFile(String name, Collection<T> entities) throws Exception {
        XSSFWorkbook workbook = createWorkbook(name, entities);

        File tempFile = Files.createTempFile(name, "").toFile();
        FileOutputStream stream = new FileOutputStream(tempFile);
        workbook.write(stream);
        stream.close();
        return tempFile;
    }

    public StreamResourceWriter createStreamResourceWrite(String name, Collection<T> entities) {
        return (outputStream, vaadinSession) -> createWorkbook(name, entities).write(outputStream);
    }

    public StreamResource createStreamFactory(String fileName, String title, Collection<T> entities) {
        return new StreamResource(fileName, () -> {
            try {
                XSSFWorkbook workbook = createWorkbook(title, entities);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                return new ByteArrayInputStream(outputStream.toByteArray());
            } catch (Exception e) {
                e.printStackTrace();
                LoggerFactory.getLogger(getClass()).error("导出失败：", e);
                new ErrorDialog(e).header("导出失败").build().open();
            }
            return null;
        });
    }

    public XSSFWorkbook createWorkbook(String name, Collection<T> entities) {
        XSSFWorkbook workbook = new XSSFWorkbook(XSSFWorkbookType.XLSX);
        XSSFSheet sheet = workbook.createSheet();
        workbook.createCellStyle();
        buildTitle(sheet, name);
        buildHeaders(sheet);
        buildBody(sheet, entities);

        return workbook;
    }

    private void buildTitle(XSSFSheet sheet, String name) {
        if (name == null || name.isBlank()) return;
        XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
        XSSFCell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue(name);
        cell.setCellStyle(createStyle(sheet.getWorkbook(), null, true));
        sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, builder.columnSize() - 1));
    }

    private void buildHeaders(XSSFSheet sheet) {
        for (Collection<String> header : builder.getHeaders()) {
            XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
            int i = 0;
            for (String name : header) {
                XSSFCell cell = row.createCell(i++, CellType.STRING);
                cell.setCellValue(name);
                cell.setCellStyle(createStyle(sheet.getWorkbook(), builder.getAlign(name), true));
            }
        }
    }

    private void buildBody(XSSFSheet sheet, Collection<T> entities) {
        for (T entity : entities) {
            XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
            int i = 0;
            for (Map.Entry<String, IColumnDataSupplier<T>> entry : builder.getMap().entrySet()) {
                XSSFCell cell = row.createCell(i++, CellType.STRING);
                cell.setCellValue(entry.getValue().get(entity));
                cell.setCellStyle(createStyle(sheet.getWorkbook(), builder.getAlign(entry.getKey()), false));
            }
        }
    }

    private XSSFCellStyle createStyle(XSSFWorkbook workbook, HorizontalAlignment alignment, boolean bold) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        if (alignment == null) alignment = HorizontalAlignment.CENTER;

        cellStyle.setAlignment(alignment);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont xssfFont = new XSSFFont();
        xssfFont.setFontName("微软雅黑");
        xssfFont.setBold(bold);
        cellStyle.setFont(xssfFont);
        return cellStyle;
    }
}
