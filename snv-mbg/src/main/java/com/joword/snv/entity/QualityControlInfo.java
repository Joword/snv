package com.joword.snv.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Joword
 * @date: 2022/11/24 20:38
 * @version: 1.0
 * @description: 质控信息
 */
@Data
@Table(name = "`quality_control_info`")
@EqualsAndHashCode(callSuper = true)
public class QualityControlInfo extends BaseEntity {

    @Column(name = "`slideId`")
    private String slideId;
    @Column(name = "`pooling`")
    private String pooling;
    @Column(name = "`gender`")
    private String gender;
    @Column(name = "`ur`")
    private Integer ur;
    @Column(name = "`gc`")
    private Float gc;
    @Column(name = "`rawData`")
    private Integer rawData;
    @Column(name = "`maprate`")
    private Float maprate;
    @Column(name = "`duprate`")
    private Float duprate;
    @Column(name = "`fetalFraction`")
    private Float fetalFraction;
    @Column(name = "`fetalNew`")
    private Float fetalNew;
    @Column(name = "`chr21Fraction`")
    private Float chr21Fraction;
    @Column(name = "`chr18Fraction`")
    private Float chr18Fraction;
    @Column(name = "`chr13Fraction`")
    private Float chr13Fraction;
    @Column(name = "`chrXFraction`")
    private Float chrXFraction;
    @Column(name = "`chrYFraction`")
    private Float chrYFraction;
    @Column(name = "`concentrationDiff`")
    private Float concentrationDiff;
    @Column(name = "`chr21Number`")
    private Integer chr21Number;
    @Column(name = "`chrXNumber`")
    private Integer chrXNumber;
    @Column(name = "`chrYPercent`")
    private Float chrYPercent;
    @Column(name = "`mtop5Zscore`")
    private Float mtop5Zscore;
    @Transient
    private String date;

}
