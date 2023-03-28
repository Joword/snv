package com.joword.snv.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Joword
 * @date: 2023/2/22 11:47
 * @version: 1.0
 * @description: lane数据实体类
 */
@Entity
@Getter
@Setter
@ToString
public class LaneInfo extends BaseEntity {

    @Column(name = "`slideId`")
    private String slideId;
    @Column(name = "`pooling`")
    private String pooling;
    @Column(name = "`sampleId`")
    private String sampleId;
    @Column(name = "`date`")
    private Integer date;
    @Column(name = "`lane`")
    private String lane;
    @Column(name = "`area`")
    private String area;
    @Column(name = "`month`")
    private String month;
    @Column(name = "`tag`")
    private String tag;
    @Column(name = "`rawData`")
    private Integer rawData;
    @Column(name = "`ur`")
    private Integer ur;
    @Column(name = "`number`")
    private Integer number;
    @Column(name = "`maprate`")
    private Float maprate;
    @Column(name = "`duprate`")
    private Float duprate;
    @Column(name = "`chrYFraction`")
    private Float chrYFraction;
}
