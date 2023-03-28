package com.snv.common.utils;

/**
 * @author Joword
 * @date: 2022/3/8 0008 16:53
 * @version:
 * @description: suffix: .xls/.xlsx
 */
public enum ExcelVersionEnum {
    V2003(".xls", 1000, 100), V2007(".xlsx", 100, 100);
    private String suffix;
    private int maxRow;
    private int maxColumn;

    ExcelVersionEnum(String suffix, int maxRow, int maxColumn) {
        this.suffix = suffix.toLowerCase();
        this.maxRow = maxRow;
        this.maxColumn = maxColumn;
    }
}
