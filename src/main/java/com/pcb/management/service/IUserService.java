package com.pcb.management.service;

import com.pcb.management.model.User;
import org.apache.ibatis.annotations.Param;


/**
 * @author : ErGouBigDevil
 * @date : 2019/5/19
 * @time : 17:49
 */
public interface IUserService {
    User findByNameAndPassword(String name, String password);

    int insertUser(User user);

    User findUserByName(String name);
}
