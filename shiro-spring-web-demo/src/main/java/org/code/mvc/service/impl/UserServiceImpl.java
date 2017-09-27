package org.code.mvc.service.impl;

import org.code.mvc.dao.UserDao;
import org.code.mvc.model.User;
import org.code.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by s on 2017/9/26.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    /**
     * 根据用户名获取
     *
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
}
