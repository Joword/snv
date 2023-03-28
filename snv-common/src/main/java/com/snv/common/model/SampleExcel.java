package com.snv.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;

/**
 * @author Joword
 * @date: 2022/10/13 0013 15:55
 * @version: 1.0.0
 * @description: sample info header entity
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SampleExcel extends Base {

    @Column(name = "`slideID`", unique = true)
    private String slideId;
    @Column(name = "`pooling`")
    private String pooling;
    @Column(name = "`sampleID`")
    private String sampleId;
    @Column(name = "`Area`")
    private String area;
    @Column(name = "`plat`")
    private String platform;
    @Column(name = "`barcode`")
    private String barcode;
    @Column(name = "`RunID`")
    private String runId;
    @Column(name = "`lane`")
    private String lane;
    @Column(name = "`date`")
    private String date;
    @Column(name = "`month`")
    private String month;
    @Column(name = "`chip`")
    private String chip;
    @Column(name = "taixing")
    private String fetalType;
    @Column(name = "fuji")
    private String enrichment;
    @Column(name = "`hospital`")
    private String hospital;
    @Column(name = "`brand`")
    private String brand;
    @Column(name = "`yunlin`")
    private String gestationalAge;
    @Column(name = "`yunzhou`")
    private String gestationalWeek;
    @Column(name = "`tag`")
    private String tag;

}
