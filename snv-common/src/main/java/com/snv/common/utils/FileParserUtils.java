package com.snv.common.utils;

import com.alibaba.excel.EasyExcel;
import com.snv.common.model.FileResult;
import com.snv.common.model.ResultExcel;
import com.snv.common.model.SampleExcel;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Joword
 * @date: 2022/3/8 0008 16:24
 * @version: 1.0
 * @description: to parser files
 */
public class FileParserUtils {

    private final static String PATH = System.getProperty("user.dir") + "\\nifty-common\\src\\main\\resources\\file\\";
    private final static String HEADER = "header";
    private final static String TITLE = "title";
    private final static String SYTLE_DATA = "data";
    private static final HashMap<String, CellStyle> cellStyleMap = new HashMap<>();
    private static final String XLS = ".xls";
    private static final String XLSX = ".xlsx";

    /**
     * to make a workbook
     *
     * @param versionEnum 版本2003/2007
     *
     * @return HSSFWorkbook/XSSFWorkbook/null
     */
    private static Workbook createWorkbookVersion(ExcelVersionEnum versionEnum) {
        switch (versionEnum) {
            case V2003:
                return new HSSFWorkbook();
            case V2007:
                return new XSSFWorkbook();
        }
        return null;
    }

    private static Workbook createWorkbook(ExcelVersionEnum excelVersionEnum, List<FileResult> excelSheets) {
        Workbook wb = createWorkbookVersion(excelVersionEnum);
        for (int i = 0; i < excelSheets.size(); i++) {
            FileResult fileResult = excelSheets.get(i);
            if (fileResult.getSheetName() == null) {
                fileResult.setSheetName("sheet" + i);
            }
            Sheet tempSheet = wb.createSheet(WorkbookUtil.createSafeSheetName(fileResult.getSheetName()));

        }
        return wb;
    }

    private static void buildSheetData(Workbook wb, Sheet sheet, FileResult fileResult, ExcelVersionEnum excelVersionEnum) {
        sheet.setDefaultRowHeight((short) 400);
        sheet.setDefaultColumnWidth((short) 10);


    }

    public static String readExcels(String fileName) throws Exception {
        if (StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException("excel path can not be null.");
        }
        boolean validateExcel = fileName.endsWith(XLS) || fileName.endsWith(XLSX);
        if (!validateExcel) {
            throw new IllegalArgumentException("wrong path，fileName=" + fileName);
        }
        InputStream inputStream = null;
        Workbook workbook = null;
        try {
            inputStream = new FileInputStream(fileName);
            if (fileName.endsWith(XLSX)) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (fileName.endsWith(XLS)) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("wrong path，fileName=" + fileName);
            }
            int sheetNumber = workbook.getNumberOfSheets();
            for (int i = 0; i < sheetNumber; i++) {
                Sheet sheet = workbook.getSheetAt(0);
                System.out.println("1-" + sheet);
                for (int rowIndex = 0; rowIndex < sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(1);
                    System.out.println("2-" + row);
                    for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
                        Cell cell = row.getCell(1);
                        String cellString = cell.toString();
                        System.out.println("3-" + cellString);
                    }
                }
            }
            return "success";
        } catch (Exception e) {
            throw new RuntimeException("file exception", e);
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static <T> List<T> readEasyExcel(String fileName, Object object) {
        List<T> resultLists = null;
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            resultLists = EasyExcel.read(inputStream).head(object.getClass()).sheet().doReadSync();
            return resultLists;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("the file not found.");
            return null;
        }

    }

    public static List<String> getExcelList() {
        List<String> fileLists = new ArrayList<>();
        File file = new File(System.getProperty("user.dir") + "\\nifty-common\\src\\main\\resources\\file");
        File[] files = file.listFiles();
        for (File subFile : files) {
            if (!subFile.isDirectory()) {
                fileLists.add(subFile.toString());
            }
        }

        return fileLists;
    }
}
