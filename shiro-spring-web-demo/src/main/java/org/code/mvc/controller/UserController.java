package org.code.mvc.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.code.mvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by s on 2017/9/25.
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request){
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(User user, HttpServletRequest request){
        System.out.println("doLogin");
        Subject subject =SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try{
            subject.login(token);//会跳到我们自定义的realm中
            request.getSession().setAttribute("user", user);
            return "index";
        }catch(Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("user", user);
            request.setAttribute("error", "用户名或密码错误！");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout( HttpServletRequest request){
        Subject subject =SecurityUtils.getSubject();
        subject.logout();
        request.getSession().removeAttribute("user");
        return "index";
    }

}
