package com.joword.snv.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Transient;
import java.util.Date;

/**
 * @author Joword
 * @date: 2022/11/21 09:49
 * @version: 1.0
 * @description: 样本数据暂存对象
 */
@Data
public class SampleInfoDTO {

    @Transient
    @ApiModelProperty(value = "编号")
    private String id;
    @ApiModelProperty(value = "样本脱敏后uuid")
    private String slidId;
    @ApiModelProperty(value = "建库Id")
    private String poolingId;
    @ApiModelProperty(value = "样本Id")
    private String sampleId;
    @ApiModelProperty(value = "片区")
    private String area;
    @ApiModelProperty(value = "平台,MGISeq500/MGISeq2000等")
    private String platform;
    @ApiModelProperty(value = "引物编号")
    private Integer barcode;
    @ApiModelProperty(value = "跑道Id")
    private String runId;
    @ApiModelProperty(value = "lane编号")
    private String lane;
    @ApiModelProperty(value = "上机时间")
    private String date;
    @ApiModelProperty(value = "上机时间-月份")
    private String month;
    @ApiModelProperty(value = "芯片号")
    private String chip;
    @ApiModelProperty(value = "胎型，S-单胎，D-双胎，V-双胎减胎")
    private String fetalType;
    @ApiModelProperty(value = "富集")
    private String enrichment;
    @ApiModelProperty(value = "医院")
    private String hospital;
    @ApiModelProperty(value = "管型，G管、K管、EDTA管、Other管、S管")
    private String brand;
    @ApiModelProperty(value = "孕龄")
    private Integer gestationalAge;
    @ApiModelProperty(value = "孕周")
    private String gestationalWeek;
    @ApiModelProperty(value = "6M/25M")
    private String tag;
    @ApiModelProperty(value = "表征数据状态")
    private Integer status;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
