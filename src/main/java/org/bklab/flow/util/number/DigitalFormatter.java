/*
 * Class: org.bklab.util.DigitalFormatter
 * Modify date: 2020/3/20 上午10:25
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DigitalFormatter {

    private BigDecimal number;
    private int maximumFractionDigits = 2;
    private int minimumFractionDigits = 2;

    public DigitalFormatter(Number number) {
        this(number, 2);
    }

    public DigitalFormatter(Number number, int scale) {
        this.number = new BigDecimal(number == null ? "0" : number.toString()).setScale(scale, RoundingMode.HALF_UP);
    }

    public DigitalFormatter(String number, int scale) {
        this.number = new BigDecimal(number).setScale(scale, RoundingMode.HALF_UP);
    }

    public DigitalFormatter abs() {
        this.number = this.number.abs();
        return this;
    }

    public String toInteger() {
        return new DecimalFormat("#,###").format(number);
    }

    public String toIntegerPart() {
        return new DecimalFormat("#,### 份").format(number);
    }

    public String toInteger(String prefix, String suffix) {
        return new DecimalFormat(String.format("%s#,### %s",
                Objects.toString(prefix, ""),
                Objects.toString(suffix, ""))
        ).format(number);
    }

    public String toFormatted() {

        return minimumFractionDigits == 0
                ? DecimalFormat.getInstance(Locale.CHINA).format(number)
                : new DecimalFormat("#,##0." + IntStream.range(0, minimumFractionDigits)
                .mapToObj(r -> "0").collect(Collectors.joining())).format(number);
    }

    public String toPercentage() {
        NumberFormat percentInstance = DecimalFormat.getPercentInstance(Locale.CHINA);
        percentInstance.setMinimumFractionDigits(minimumFractionDigits);
        percentInstance.setMaximumFractionDigits(maximumFractionDigits);
        return percentInstance.format(number);
    }

    public String toPriceCNYSymbol() {
        return DecimalFormat.getCurrencyInstance(Locale.CHINA).format(number);
    }

    public String toPriceCNY() {
        return new DecimalFormat("#,##0.00 元").format(number);
    }

    public String toArea() {
        return new DecimalFormat("#,##0.00 ㎡").format(number);
    }

    public String toAreaWord() {
        return new DecimalFormat("#,##0.00 平方米").format(number);
    }

    public String toPriceDollarSymbol() {
        return DecimalFormat.getCurrencyInstance(Locale.US).format(number);
    }

    public DigitalFormatter setMaximumFractionDigits(int maximumFractionDigits) {
        this.maximumFractionDigits = maximumFractionDigits;
        return this;
    }

    public DigitalFormatter setMinimumFractionDigits(int minimumFractionDigits) {
        this.minimumFractionDigits = minimumFractionDigits;
        return this;
    }

    public String toDataSize() {
        double size = number.doubleValue();
        if (size <= 0) return "0 KB";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        if (digitGroups >= units.length) digitGroups = units.length - 1;
        return new DecimalFormat("#,##0.00").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    @Override
    public String toString() {
        return DecimalFormat.getInstance(Locale.CHINA).format(number);
    }
}
