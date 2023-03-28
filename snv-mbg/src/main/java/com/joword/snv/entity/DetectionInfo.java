package com.joword.snv.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author Joword
 * @date: 2022/11/24 11:56
 * @version: 1.0
 * @description: 检出结果实体类
 */
@Data
@Table(name = "`detection_result`")
@EqualsAndHashCode(callSuper = true)
public class DetectionInfo extends BaseEntity {

    @Column(name = "`slideId`")
    private String slideId;
    @Column(name = "`pooling`")
    private String pooling;
    @Column(name = "`Test(chr21)`")
    private String testChr21;
    @Column(name = "`Test(chr18)`")
    private String testChr18;
    @Column(name = "`Test(chr13)`")
    private String testChr13;
    @Column(name = "`Test(X)`")
    private String testX;
    @Column(name = "`note1`")
    private String note1;
    @Column(name = "`note2`")
    private String note2;
    @Column(name = "`disease`")
    private String disease;
    @Column(name = "`autom`")
    private String autom;
    @Column(name = "`Z-score_check(chr21)`")
    private Double zScoreChr21;
    @Column(name = "`Z-score_check(chr18)`")
    private Double zScoreChr18;
    @Column(name = "`Z-score_check(chr13)`")
    private Double zScoreChr13;

}
