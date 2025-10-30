package org.example.oenskeskyen.controller;

import org.example.oenskeskyen.model.Wish;
import org.example.oenskeskyen.model.WishList;
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
        List<Wish> wishes = wishlistService.getWishes();
        model.addAttribute("list", wishes);
        return "wishes";
    }

    @PostMapping("/{name}/update")
    public String updateWish(Wish wish) {
        System.out.println(wish);
        wishlistService.updateWish(wish);
        return "redirect:/wishList/list";
    }


    @GetMapping("/addWishList")
    public String addWishlist(Model model) {
        WishList newWishList = new WishList("halla");
        model.addAttribute("wishlist", newWishList);

        //  wishlistService.addWishList(newWishList);
        return "addWishListForm";
    }

    @PostMapping("/addWishList")
    public String saveWishList(WishList wishList) {
        wishlistService.addWishList(wishList);
        return "redirect:/wishlist/list";
    }


    @GetMapping("/addWish")
    public String addWish(Model model) {
        Wish newWish = new Wish();
        model.addAttribute("wish", newWish);
//        model.addAttribute("price", newWish);
//        model.addAttribute("link", newWish);
//        model.addAttribute("id", newWish);
//        model.addAttribute("description", newWish);

        return "addWishForm";
    }

    @PostMapping("/addWish")
    public String saveWish(Wish wish) {
        wishlistService.addWish(wish);
        return "redirect:/wishlist/list";
    }


}
