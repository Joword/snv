package com.snv.common.model;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author Joword
 * @date: 2022/10/13 0013 16:08
 * @version: 1.0.0
 * @description: 抽象基础类
 */
@Data
abstract class Base {
    @Column(name = "`status`")
    private int status;
    @Column(name = "`create_time`")
    private String createTime;
    @Column(name = "`update_time`")
    private String updateTime;
}
