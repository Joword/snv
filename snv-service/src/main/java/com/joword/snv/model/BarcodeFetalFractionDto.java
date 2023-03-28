package com.joword.snv.model;

import lombok.Data;

/**
 * @author Joword
 * @date: 2023/2/3 17:39
 * @version: 1.0
 * @description: 单样本胎儿浓度分布情况
 */
@Data
public class BarcodeFetalFractionDto {

    private Double fetalFraction;
    private String area;
    private String type;
    private String name;

}
