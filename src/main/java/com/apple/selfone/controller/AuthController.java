package com.apple.selfone.controller;

import com.apple.selfone.model.RegisterRequest;
import com.apple.selfone.model.ResultResponse;
import com.apple.selfone.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping("/login")
    public String getLoginPage(){
        if (authService.checkAuth()){
            return "login";
        }else {
            return "dashboard/core";
        }
    }

    @GetMapping("/register")
    public String getRegisterPage(RegisterRequest request, Model model){
        if (authService.checkAuth()){
            model.addAttribute("registerRequest", request);
            return "register";
        }else {
            return "redirect:/dashboard/core";
        }
    }

    @PostMapping("/register")
    public String register(@Valid RegisterRequest request, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "register";
        }else {
            ResultResponse response = authService.register(request);
            if (response.isSuccess()){
                return "redirect:/home";
            }
        }
        return "register";
    }



}
