package com.joword.snv.model;

import lombok.Data;

/**
 * @author Joword
 * @date: 2023/2/3 16:55
 * @version: 1.0
 * @description: UR分布情况实体类
 */
@Data
public class URDistributionDTO {

    private Double ur;
    private String area;
    private String type;
    private String name;

}
