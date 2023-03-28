package com.joword.snv.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Joword
 * @date: 2021/12/16 0016 10:38
 * @version:
 * @description: 服务层DO
 */

@Data
public class UserDO implements Serializable {
    private String name;
    private String email;
    private String area;
    private String role;
}
