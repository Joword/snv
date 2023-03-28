package com.joword.snv.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;

/**
 * @author Joword
 * @date: 2022/11/25 16:42
 * @version: 1.0
 * @description: 其他信息实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OtherInfo extends BaseEntity {

    @Column(name = "`slideId`")
    private String slideId;
    @Column(name = "`pooling`")
    private String pooling;
    @Column(name = "`Mean_adjust(chr13)`")
    private String meanAdjustChr13;
    @Column(name = "`CV_adjust(chr13)`")
    private String cvAdjustChr13;


}
