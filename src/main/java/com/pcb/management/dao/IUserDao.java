package com.pcb.management.dao;

import com.pcb.management.model.User;
import org.apache.ibatis.annotations.Param;



/**
 * @author : ErGouBigDevil
 * @date : 2019/5/19
 * @time : 16:17
 */
public interface IUserDao {
    User findByNameAndPassword(@Param(value="name") String name, @Param(value="password") String password);
    int insertUser(User user);
    User findUserByName(String name);

}
