package com.marveliu.web.service;

import com.marveliu.web.component.service.BaseService;
import com.marveliu.web.dao.entity.User;
import com.marveliu.web.dao.repository.UserRepository;

/**
 * @Author: Marveliu
 * @Date: 2018/9/24 下午5:08
 * @Description:
 **/

public interface UserService extends BaseService<User, Integer> {

    @Override
    UserRepository getDAO();

    boolean authorityUserRole(User user, Integer roleId);
}
