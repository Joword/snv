package com.joword.snv.model;

import lombok.Data;

/**
 * @author Joword
 * @date: 2023/2/3 16:56
 * @version: 1.0
 * @description: 样本重复率分布情况
 */
@Data
public class BarcoderDuprateDto {

    private Double duprate;
    private String area;
    private String type;
    private String name;

}
