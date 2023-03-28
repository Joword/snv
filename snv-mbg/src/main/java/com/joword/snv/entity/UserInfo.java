package com.joword.snv.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author Joword
 * @date: 2021/12/21 0021 11:17
 * @version:
 * @description:
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "`user`")
public class UserInfo extends BaseEntity {

    @Column(name = "`user_id`")
    private String userId;
    @Column(name = "`user_name`")
    private String userName;
    @Column(name = "`family_name`")
    private String familyName;
    @Column(name = "`given_name`")
    private String givenName;
    @Column(name = "password")
    private String password;
    @Column(name = "`areas`")
    private String areas;
    @Column(name = "`role`")
    private String role;
}
