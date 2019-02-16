package com.apple.selfone.controller;

import com.apple.selfone.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    AuthService authService;

    @GetMapping(value = {"/", "/home"})
    public String getHomePage(){
        if (authService.checkAuth()){
            return "login";
        }else {
            return "dashboard/core";
        }
    }

}
