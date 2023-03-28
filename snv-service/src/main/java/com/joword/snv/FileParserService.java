package com.joword.snv;

/**
 * @author Joword
 * @date: 2022/3/8 0008 16:17
 * @version:
 * @description: xls/xlsx文件解析接口
 */
public interface FileParserService {

    /**
     * 解析Excel表格获取内容
     *
     * @return String
     * @throws Exception IOException & FileNotFoundException
     */
    String getExcelResult() throws Exception;

    String getExcelSampleInfo();

    String getExcelQualityControl();

    String getOtherData();

    String putExceltoDatabase();

}
