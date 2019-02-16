package com.apple.selfone.controller;

import com.apple.selfone.model.UserPrincipal;
import com.apple.selfone.repository.ImageRepository;
import com.apple.selfone.repository.ProductRepository;
import com.apple.selfone.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public String getCorePage(){
        return "dashboard/core";
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @GetMapping("/products")
    public String getProducts(Model model, @CurrentUser UserPrincipal user){
        model.addAttribute("user", user.getUser());
        model.addAttribute("images", imageRepository.findAll());
        return "dashboard/products";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String getUsers(Model model, @CurrentUser UserPrincipal user){
        model.addAttribute("user", user.getUser());
        return "dashboard/users";
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @GetMapping("/orders")
    public String getOrders(Model model, @CurrentUser UserPrincipal user){
        model.addAttribute("user", user.getUser());
        model.addAttribute("products", productRepository.findAll());
        return "dashboard/orders";
    }

}
