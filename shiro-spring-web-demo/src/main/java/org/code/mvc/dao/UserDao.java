package org.code.mvc.dao;

import org.code.mvc.model.User;
import org.mybatis.spring.annotation.MapperScan;

import java.util.Set;
//这个注解其实不用也可以扫描到
@MapperScan
public interface UserDao {
   User getUserByUsername(String username);

    /**
     * 查询用户roles
     * @param username
     * @return
     */
    Set<String> findRoles(String username);
    /**
     * 查询用户Permissions
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);
}