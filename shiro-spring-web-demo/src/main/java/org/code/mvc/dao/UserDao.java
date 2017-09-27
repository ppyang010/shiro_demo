package org.code.mvc.dao;

import org.code.mvc.model.User;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface UserDao {
   User getUserByUsername(String username);
}