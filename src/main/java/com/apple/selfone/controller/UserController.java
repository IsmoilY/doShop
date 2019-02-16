package com.apple.selfone.controller;

import com.apple.selfone.entity.User;
import com.apple.selfone.model.ResponseData;
import com.apple.selfone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/list")
    public @ResponseBody ResponseData<List<User>> getUsers() throws Exception {
        return new ResponseData<List<User>>(true, null, userRepository.findAll());
    }

    @GetMapping("/settings/{id}")
    public String getSettingsPage(@PathVariable Long id, Model model){
        model.addAttribute("user", userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("user")));
        return "dashboard/settings";
    }

}
