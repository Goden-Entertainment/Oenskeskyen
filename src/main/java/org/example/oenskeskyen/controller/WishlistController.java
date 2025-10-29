package org.example.oenskeskyen.controller;

import org.example.oenskeskyen.model.Wish;
import org.example.oenskeskyen.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("wishlist")
public class WishlistController {

    final private WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;

    }

    @GetMapping("list")
    public String getAllWishlist(Model model) {
        List<Wish> wishes = wishlistService.getWishlist();
        model.addAttribute("list", wishes);
        return "wishes";
    }

    @PostMapping("/{name}/update")
    public String updateWish(Wish wish) {
        System.out.println(wish);
        wishlistService.updateWish(wish);
        return "redirect:/wishes";
    }


}
