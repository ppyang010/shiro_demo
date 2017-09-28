package org.code.mvc.dao.impl;

import org.code.mvc.dao.UserDao;
import org.code.mvc.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by s on 2017/9/28.
 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
    public User getUserByUsername(String username) {
        String sql ="org.code.mvc.dao.UserDao.getUserByUsername";
        User user=this.getSqlSession().selectOne(sql,username);
        return user;
    }

    /**
     * 查询用户roles
     *
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        String sql ="org.code.mvc.dao.UserDao.findRoles";
        List<String> list = this.getSqlSession().selectList(sql,username);
        HashSet<String> set = new HashSet<String>(list);
        return set;
    }

    /**
     * 查询用户Permissions
     *
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        return null;
    }
}
