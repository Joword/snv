package com.snv.common.model;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author Joword
 * @date: 2022/10/12 0012 21:07
 * @version:
 * @description: result_info table header entity
 */
@Data
public class ResultExcel {

    @Column(name = "`slideID`", unique = true)
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
    @Column(name = "`cnv`")
    private String cnv;
    @Column(name = "`autom`")
    private String autom;
    @Column(name = "`Z-score(chr21)`")
    private float zscoreCheckChr21;
    @Column(name = "`Z-score(chr18)`")
    private float zscoreCheckChr18;
    @Column(name = "`Z-score(chr13)`")
    private float zscoreCheckChr13;

}
