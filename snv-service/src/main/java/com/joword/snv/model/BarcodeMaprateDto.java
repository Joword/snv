package com.joword.snv.model;

import lombok.Data;

/**
 * @author Joword
 * @date: 2023/2/3 17:37
 * @version: 1.0
 * @description: 样本比对率分布情况
 */
@Data
public class BarcodeMaprateDto {

    private Double maprate;
    private String area;
    private String type;
    private String name;

}
