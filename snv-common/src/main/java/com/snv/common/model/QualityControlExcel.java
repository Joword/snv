package com.snv.common.model;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author Joword
 * @date: 2022/10/14 0014 15:00
 * @version: 1.0.0
 * @description: quality control xlsx
 */
@Data
public class QualityControlExcel {

    @Column(name = "`slideID`")
    private String slideId;
    @Column(name = "`pooling`")
    private String pooling;
    @Column(name = "`gender`")
    private String gender;
    @Column(name = "`UR`")
    private Integer ur;
    @Column(name = "`GC`")
    private Double gc;
    @Column(name = "`Rawdate`")
    private Double rawData;
    @Column(name = "`Maprate`")
    private Double maprate;
    @Column(name = "`Duprate`")
    private Double duprate;
    @Column(name = "`fetalfraction`")
    private Double fetalFraction;
    @Column(name = "`fetal_new`")
    private Double fetalNew;
    @Column(name = "`chr21_fra`")
    private Double chr21Fraction;
    @Column(name = "`chr18_fra`")
    private Double chr18Fraction;
    @Column(name = "`chr13_fra`")
    private Double chr13Fraction;
    @Column(name = "`chrx_fra`")
    private Double chrXFraction;
    @Column(name = "`chry_fra`")
    private Double chrYFraction;
    @Column(name = "`nongducha`")
    private Double concentrationDiff;
    @Column(name = "`chr21num`")
    private Integer chr21Number;
    @Column(name = "`chrxnum`")
    private Integer chrXNumber;
    @Column(name = "`chrY%`")
    private Double chrYPercent;
    @Column(name = "`MTOP5Zscores`")
    private Double mtop5Zscore;

}
