package com.zlp.travel.service;

import com.zlp.travel.entity.User;

import java.util.List;

/**
 * Author: zlp
 * Date: 2020-08-07 16:08
 * Description:张立朋，写点注释吧!!
 */
public interface UserService {

    Integer register(User user);

    List<User> findByUserName(String userName);

    List<User> login(User user);
}
