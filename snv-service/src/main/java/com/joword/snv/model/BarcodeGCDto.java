package com.joword.snv.model;

import lombok.Data;

/**
 * @author Joword
 * @date: 2023/2/3 17:42
 * @version: 1.0
 * @description: 单样本GC分布情况
 */
@Data
public class BarcodeGCDto {

    private Double gc;
    private String area;
    private String type;
    private String name;

}
