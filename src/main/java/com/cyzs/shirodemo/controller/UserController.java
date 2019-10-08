package com.cyzs.shirodemo.controller;

import com.cyzs.shirodemo.dao.ShiroSessionDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @Author xiaoh
 * @create 2019-09-03 13:02
 */
@Controller
public class UserController {

    @RequestMapping("/list")
    public String index(HttpSession session){
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()){
            System.out.println("-------"+attributeNames.nextElement());
        }
        return "redirect:/list.jsp";
    }

    @RequestMapping("/loginIn")
    public String loginIn(@RequestParam("username")String username, @RequestParam("password")String password, Model model){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        System.out.println("------------"+password);
        if (!currentUser.isAuthenticated()){
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            //usernamePasswordToken.setRememberMe(true);
            try{
                currentUser.login(usernamePasswordToken);
            }catch (AuthenticationException e){
                System.out.println("登录失败"+e.getMessage());
                model.addAttribute("msg","用户名或密码不正确");
            }
        }
        return "redirect:/list.jsp";
    }


}
