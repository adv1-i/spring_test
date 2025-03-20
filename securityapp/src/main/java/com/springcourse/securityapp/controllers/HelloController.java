package com.springcourse.securityapp.controllers;

import com.springcourse.securityapp.security.PersonDetails;
//import com.springcourse.securityapp.services.AdminService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

//    private final AdminService adminService;
//
//    @Autowired
//    public HelloController(AdminService adminService) {
//        this.adminService = adminService;
//    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/userInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());

        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage() {
//        adminService.doAdminStuff();
        return "admin";
    }
}
