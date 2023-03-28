package com.joword.snv.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Joword
 * @date: 2023/2/9 10:35
 * @version: 1.0
 * @description: sample_info与quality_control_info合并查询
 */
@Entity
@Setter
@Getter
@ToString
public class SampleQualityControlInfo extends BaseEntity {

    @Column(name = "`slideId`")
    private String slideId;
    @Column(name = "`pooling`")
    private String pooling;
    @Column(name = "`sampleId`")
    private String sampleId;
    @Column(name = "`area`")
    private String area;
    @Column(name = "`platform`")
    private String platform;
    @Column(name = "`barcode`")
    private String barcode;
    @Column(name = "`runId`")
    private String runId;
    @Column(name = "`lane`")
    private String lane;
    @Column(name = "`date`")
    private String date;
    @Column(name = "`chip`")
    private String chip;
    @Column(name = "`fetalType`")
    private String fetalType;
    @Column(name = "`enrichment`")
    private String enrichment;
    @Column(name = "`hospital`")
    private String hospital;
    @Column(name = "`brand`")
    private String brand;
    @Column(name = "`gestationalAge`")
    private Integer gestationalAge;
    @Column(name = "`gestationalWeek`")
    private String gestationalWeek;
    @Column(name = "`tag`")
    private String tag;
    @Column(name = "`gender`")
    private String gender;
    @Column(name = "`ur`")
    private Integer ur;
    @Column(name = "`gc`")
    private Double gc;
    @Column(name = "`rawData`")
    private Double rawData;
    @Column(name = "`maprate`")
    private Double maprate;
    @Column(name = "`duprate`")
    private Double duprate;
    @Column(name = "`fetalFraction`")
    private Double fetalFraction;
    @Column(name = "`fetalNew`")
    private Double fetalNew;

}
