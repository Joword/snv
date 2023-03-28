package com.joword.snv.mapper;

import com.joword.snv.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Joword
 * @date: 2021/12/21 0021 11:56
 * @version:
 * @description: user info interface
 */
@Repository
public interface UserInfoMapper {

    /**
     * 使用user_id获取用户信息
     *
     * @param userId 用户唯一id
     *
     * @return UserInfo
     */
    UserInfo selectUserInfoUid(@Param("uid") Integer userId);

    /**
     * 数据库中插入注册信息
     *
     * @param userInfo 实体类
     *
     * @return int
     */
    int insertUserInfo(@Param("u") UserInfo userInfo);
}
