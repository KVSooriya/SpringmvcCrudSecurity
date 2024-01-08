package com.vismee.springmvccrudsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController
{
    @RequestMapping("/showLogin")
    public String showLoginPage()
    {
        return "employees/login-page";
    }

    @RequestMapping("/accessDenied")
    public String showAccessDenied()
    {
        return "employees/access-denied";
    }
}
