package com.joword.snv.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author Joword
 * @date: 2022/11/18 11:50
 * @version: 1.0
 * @description: sample information entity
 */
@Data
@Table(name = "`sample_info`")
@EqualsAndHashCode(callSuper = true)
public class SampleInfo extends BaseEntity {

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
    private int barcode;
    @Column(name = "`runId`")
    private String runId;
    @Column(name = "`lane`")
    private String lane;
    @Column(name = "`date`")
    private String date;
    @Column(name = "`month`")
    private String month;
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

}
