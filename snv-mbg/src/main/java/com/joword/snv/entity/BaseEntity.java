package com.joword.snv.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Joword
 * @date: 2023/3/15 15:11
 * @version: 1.0
 * @description: to include some fundamental argments
 */
@Entity
@Getter
@Setter
@ToString
public abstract class BaseEntity {

    @Id
    @Column(name = "`id`", unique = true)
    @TableId(type = IdType.AUTO)
    private String id;

    @Version
    private String version;

    @Column(name = "`status`", updatable = false)
    private Integer status;

    @Temporal(TemporalType.DATE)
    @Column(name = "`create_time`", updatable = false)
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private Date createTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "`update_time`", updatable = false)
    @TableField(value = "`update_time`", fill = FieldFill.INSERT)
    private Date updateTime;

}
