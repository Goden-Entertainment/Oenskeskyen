package org.example.oenskeskyen.controller;

import jakarta.servlet.http.HttpSession;
import org.example.oenskeskyen.model.User;
import org.example.oenskeskyen.model.WishList;
import org.example.oenskeskyen.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProfileController {

    private final org.example.oenskeskyen.service.WishlistService wishlistService;

    public ProfileController(org.example.oenskeskyen.service.WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/signin")
    public String showSigninForm(Model model) {
        model.addAttribute("user", new User());
        return "signin";
    }

    @PostMapping("/signin")
    public String signup(@RequestParam String email,
                         @RequestParam String password,
                         HttpSession session,
                         org.springframework.ui.Model model) {
        try {
            User user = wishlistService.getUserByEmailAndPassword(email, password);
            session.setAttribute("currentUser", user);
            return "redirect:/profile";
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            // forkert email/password
            model.addAttribute("user", new User());
            model.addAttribute("error", "Invalid email or password");
            return "signin";
        }
    }


    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
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

    @GetMapping("/profile")
    public String showProfile(HttpSession session, org.springframework.ui.Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            // Hvis man ikke er logget ind, så tilbage til signin
            model.addAttribute("user", new User());
            return "signin";
        }

        java.util.List<org.example.oenskeskyen.model.WishList> wishlists = wishlistService.getWishList();

        model.addAttribute("user", user);
        model.addAttribute("wishlists", wishlists);
        return "profile";
    }
}
