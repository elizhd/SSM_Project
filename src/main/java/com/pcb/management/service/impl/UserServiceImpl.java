package com.pcb.management.service.impl;

import com.pcb.management.dao.IUserDao;
import com.pcb.management.model.User;
import com.pcb.management.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : ErGouBigDevil
 * @date : 2019/5/19
 * @time : 17:50
 */

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public User findByNameAndPassword(String name, String password) {
        return userDao.findByNameAndPassword(name,password);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }
}
