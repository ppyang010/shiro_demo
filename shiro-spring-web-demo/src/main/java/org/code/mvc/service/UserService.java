package org.code.mvc.service;

import org.code.mvc.model.User;

import java.util.Set;

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

    /**
     * 查询用户角色
     * @param username
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 查询用户权限
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);
}
