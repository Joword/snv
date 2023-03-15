package com.joword.snv.model;

import lombok.Data;

/**
 * @author Joword
 * @date: 2023/3/15 15:09
 * @version: 1.0
 * @description: user info data transfer object
 */
@Data
public class UserInfoDo {
    private String name;
    private String email;
    private String area;
    private String role;
}
