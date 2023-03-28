package com.joword.snv.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;

/**
 * @author Joword
 * @date: 2023/3/16 11:29
 * @version: 1.0
 * @description: 首页地图数据、片区、时间样本总量
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SampleAreaTimeInfo extends BaseEntity {

    @Column(name = "`slideId`", unique = true)
    private String slideId;
    @Column(name = "`area`")
    private String area;
    @Column(name = "`date`")
    private String date;
    @Column(name = "`month`")
    private String month;
    @Column(name = "`tag`")
    private String tag;
    private Integer count;
    private Integer basicCount;
    private Integer proCount;


}
