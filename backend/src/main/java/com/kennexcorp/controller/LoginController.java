package com.kennexcorp.controller;

import com.kennexcorp.model.User;
import com.kennexcorp.service.SecurityService;
import com.kennexcorp.service.SecurityServiceImpl;
import com.kennexcorp.service.UserService;
import com.kennexcorp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login_api")
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private SecurityServiceImpl securityService;


    @GetMapping(value = "/test")
    public String test() {

        return "Hello dear";
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody String registerUser(@ModelAttribute User user){

        userService.save(user);
        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        return "User Logged in";
    }

    @GetMapping(value = "/login")
    public @ResponseBody String login(Model model, String error, String logout) {
        if (error != null)
            return ("Your username and password is invalid.");

        if (logout != null)
            return ("You have been logged out successfully.");

        return "user logged in";
    }
}
