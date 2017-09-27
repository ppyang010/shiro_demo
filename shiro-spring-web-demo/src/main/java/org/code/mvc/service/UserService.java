package org.code.mvc.service;

import org.code.mvc.model.User;

/**
 * Created by s on 2017/9/26.
 * 用户相关service
 */
public interface UserService {
    /**
     * 根据用户名获取
     * @param username
     * @return
     */
    User getUserByUsername(String username);
}
