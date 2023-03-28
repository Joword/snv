package com.joword.snv;

import com.joword.snv.model.UserDO;

/**
 * @author Joword
 * @date: 2023/3/15 15:00
 * @version: 1.0
 * @description: user information service
 */
public interface UserService {
    /**
     * 获取对应user_id对应信息
     *
     * @param userId 用户唯一id
     *
     * @return entity
     */
    UserDO selectUserInfo(Integer userId);

    /**
     * 注册信息插入数据库
     *
     * @param userInfo 实体类
     *
     * @return ENTITY
     */
    int insertUserInfo(UserDO userInfo);
}
