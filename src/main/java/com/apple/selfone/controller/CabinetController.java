package com.apple.selfone.controller;

import com.apple.selfone.entity.Role;
import com.apple.selfone.entity.User;
import com.apple.selfone.model.ResponseData;
import com.apple.selfone.model.UserData;
import com.apple.selfone.model.UserPrincipal;
import com.apple.selfone.repository.ProductRepository;
import com.apple.selfone.repository.UserRepository;
import com.apple.selfone.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public String getCabinetPage(Model model, @CurrentUser UserPrincipal user){
        model.addAttribute("user", user.getUser());
        model.addAttribute("products", productRepository.findAll());
        for (Role role : user.getUser().getRoles()) {
            if (role.getId() == 10){
                model.addAttribute("users", userRepository.findAll());
                return "dashboard/core";
            }
        }
        return "shop/index";
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseData<List<UserData>> getUsers(){
        List<UserData> list = new ArrayList<>();
        for (User user : userRepository.findAll()){
            list.add(new UserData(user));
        }
        return new ResponseData<List<UserData>>(true, null, list);
    }


}
