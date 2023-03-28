package com.joword.snv.model;

import lombok.Data;

/**
 * @author Joword
 * @date: 2023/2/3 17:45
 * @version: 1.0
 * @description: 各片区barcode原始数据量均值实体类
 */
@Data
public class BarcodeRawDataDto {

    private String barcode;
    private Double rawData;
    private String type;
    private String name;

}
