package com.pcb.management.controller;

import com.pcb.management.model.User;
import com.pcb.management.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : ErGouBigDevil
 * @date : 2019/5/19
 * @time : 17:54
 */

@Controller
public class UserController {
    @Resource(name = "userService")
    IUserService userService;

    protected void writeJSON2Response(Object out, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            //System.out.println("SERVER: " + out);
            response.getWriter().print(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    @ResponseBody
    public void userLogin(@RequestParam(value = "userName") String userName,
                          @RequestParam(value = "userPassword") String userPassword,
                          HttpServletResponse response) {
        System.out.println(userName + " " + userPassword);
        Object result = "{\"flag\":false}";
        if (userService.findByNameAndPassword(userName, userPassword) != null)
            result = "{\"flag\":true}";
        writeJSON2Response(result, response);
    }


    @RequestMapping(value="/registerUser",method=RequestMethod.POST)
    @ResponseBody
    public void userRegister(@RequestBody User user, HttpServletResponse response){
        System.out.println(user.toString());
        Object result = "{\"flag\":false}";
        // System.out.println(user);
        if (userService.insertUser(user) > 0)
            result = "{\"flag\":true}";
        writeJSON2Response(result, response);
    }


    @RequestMapping(value = "/isRegistered", method = RequestMethod.POST)
    @ResponseBody
    public void isRegistered(String name, HttpServletResponse response){
        System.out.println(name);
        Object result = "{\"flag\":false}";
        if (userService.findUserByName(name) == null){
            result = "{\"flag\":true}";
        }else{
            System.out.println(userService.findUserByName(name).toString());
        }


        writeJSON2Response(result, response);
    }




}
