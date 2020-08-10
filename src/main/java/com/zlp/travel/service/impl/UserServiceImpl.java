package com.zlp.travel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlp.travel.dao.UserDao;
import com.zlp.travel.entity.User;
import com.zlp.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Author: zlp
 * Date: 2020-08-07 16:09
 * Description:张立朋，写点注释吧!!
 */
@Service
@Transactional
public  class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(User user) {
        user.setCreateTime(new Date());
        return userDao.insert(user);
    }

    @Override
    public List<User> findByUserName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        List<User> userList =userDao.selectList(queryWrapper);
        return userList;
    }

    @Override
    public List<User> login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",user.getUsername())
                .eq("password",user.getPassword());
        List<User> users = userDao.selectList(queryWrapper);
        return users;
    }
}
