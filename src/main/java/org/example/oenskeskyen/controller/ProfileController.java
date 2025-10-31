package org.example.oenskeskyen.controller;

import org.example.oenskeskyen.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @GetMapping("/signin")
    public String showSigninForm(Model model) {
        model.addAttribute("user", new User());
        return "signin";
    }

    @PostMapping("/signin")
    public String signup(
            @RequestParam String email,
            @RequestParam String password
    ) {
        return "signin-success";
    }


    @GetMapping("/signup")
    public String showSignupForm(Model model){
        model.addAttribute("user",new User());
        return "signup";
    }
    @PostMapping("/signup")
    public String signup(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam("password-repeat") String confirmPassword,
            @RequestParam(required = false) boolean remember
    ) {
        return "profile";
    }
}
