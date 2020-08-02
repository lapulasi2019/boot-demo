package com.gaodun.bootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
//不需要responseBody
public class HelloController {

    @GetMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "/greeting";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用戶名不存在");
            return "/login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密碼錯誤");
            return "/login";
        }
    }

    @GetMapping("/greeting")
    public String thymeleaf(@RequestParam(name = "name", required = false, defaultValue = "world") String name, Model model) {
        model.addAttribute("name", name);
        System.out.println("hello world");
        return "greeting";
    }

    @GetMapping("/add")
    public String add() {
        return "user/add";
    }

    @GetMapping("/update")
    public String update() {
        return "user/update";
    }
}
