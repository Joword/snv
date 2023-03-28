package com.joword.snv.impl;

import com.joword.snv.entity.UserInfo;
import com.joword.snv.mapper.UserInfoMapper;
import com.joword.snv.UserService;
import com.joword.snv.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Joword
 * @date: 2021/12/16 0016 10:02
 * @version:
 * @description: 用户Service实现层
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserDO selectUserInfo(Integer userId) {
        UserDO userDataObject = new UserDO();
        UserInfo userInfo = userInfoMapper.selectUserInfoUid(userId);
        userDataObject.setEmail(userInfo.getUserName());
        userDataObject.setName(userInfo.getFamilyName() + " " + userInfo.getGivenName());
        userDataObject.setArea(userInfo.getAreas());
        userDataObject.setRole(userInfo.getRole());
        return userDataObject;
    }

    @Override
    public int insertUserInfo(UserDO userInfo) {
        UserInfo userDataObject = new UserInfo();
        int userInfomation = userInfoMapper.insertUserInfo(userDataObject);

        return 1;
    }
}
