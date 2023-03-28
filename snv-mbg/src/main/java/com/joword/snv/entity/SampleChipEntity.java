package com.joword.snv.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;

/**
 * @author Joword
 * @date: 2023/3/23 16:19
 * @version: 1.0
 * @description: 芯片样本数
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SampleChipEntity extends BaseEntity {

    @Column(name = "`slideId`", unique = true)
    private String slideId;
    @Column(name = "`chip`")
    private String chip;
    @Column(name = "tag")
    private String tag;

}
