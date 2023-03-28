package com.snv.common.model;

import lombok.Data;

import java.util.List;

/**
 * @author Joword
 * @date: 2022/3/8 0008 16:40
 * @version:
 * @description: file object
 */
@Data
public class FileResult {
    private String format;
    private String sheetName;
    private String title;
    private String[] headers;
    private List<List<Object>> dataList;
}
