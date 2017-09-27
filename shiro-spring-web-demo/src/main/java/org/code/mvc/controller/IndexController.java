package org.code.mvc.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.code.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by s on 2017/9/25.
 */
@Controller
@RequestMapping("/")
public class IndexController {


    @RequestMapping("/index")
    public String index(User user, HttpServletRequest request){
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(User user, HttpServletRequest request){
        return "hello";
    }


}
