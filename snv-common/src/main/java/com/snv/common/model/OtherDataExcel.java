package com.snv.common.model;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author Joword
 * @date: 2022/10/14 0014 15:50
 * @version: 1.0.0
 * @description: otherdata format xlsx
 */
@Data
public class OtherDataExcel {
    @Column(name = "`slideID`")
    private String slideId;
    @Column(name = "`pooling`")
    private String pooling;
    @Column(name = "`Mean_adjust(chr13)`")
    private String meanAdjustChr13;
    @Column(name = "`CV_adjust(chr13)`")
    private String cvAdjustChr13;
    @Column(name = "`RL_adjust(chr13)`")
    private String rlAdjustChr13;
    @Column(name = "`risk(chr13)`")
    private String riskChr13;
    @Column(name = "`Mean(chr13)`")
    private String meanChr13;
    @Column(name = "`CV(chr13)`")
    private String cvChr13;
    @Column(name = "`UR%(Chr13)`")
    private String urPercentChr13;
    @Column(name = "`Mean_adjust(chr18)`")
    private String meanAdjustChr18;
    @Column(name = "`CV_adjust(chr18)`")
    private String cvAdjustChr18;
    @Column(name = "`RL_adjust(chr18)`")
    private String rlAdjustChr18;
    @Column(name = "`risk(chr18)`")
    private String riskChr18;
    @Column(name = "`Mean(chr18)`")
    private String meanChr18;
    @Column(name = "`CV(chr18)`")
    private String cvChr18;
    @Column(name = "`UR%(chr18)`")
    private String urPercentChr18;
    @Column(name = "`Mean_adjust(chr21)`")
    private String meanAdjustChr21;
    @Column(name = "`CV_adjust(chr21)`")
    private String cvAdjustChr21;
    @Column(name = "`RL_adjust(chr21)`")
    private String rlAdjustChr21;
    @Column(name = "`risk(chr21)`")
    private String riskChr21;
    @Column(name = "`Mean(chr21)`")
    private String meanChr21;
    @Column(name = "`CV(chr21)`")
    private String cvChr21;
    @Column(name = "`UR%(chr21)`")
    private String urPercentChr21;
    @Column(name = "`lda-Z(chr21)`")
    private Double ldaZscoreChr21;
    @Column(name = "`ldz-Z(chr18)`")
    private Double ldaZscoreChr18;
    @Column(name = "`lda-Z(chr13)`")
    private Double ldaZscoreChr13;
    @Column(name = "`chr21`")
    private Double chr21;
    @Column(name = "`T-score(chr21)`")
    private Double tScoreChr21;
    @Column(name = "`chr18`")
    private Double chr18;
    @Column(name = "`T-score_adjust(chr18)`")
    private Double tScoreAdjustChr18;
    @Column(name = "`chr13`")
    private Double chr13;
    @Column(name = "`T-score_adjust(chr13)`")
    private Double tScoreAdjustChr13;

}
