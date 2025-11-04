package org.example.oenskeskyen.controller;

import org.example.oenskeskyen.model.User;
import org.example.oenskeskyen.model.Wish;
import org.example.oenskeskyen.model.WishList;
import org.example.oenskeskyen.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("wishlist")
public class WishlistController {

    final private WishlistService wishlistService;
    private User user;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;

    }

    @GetMapping("addUser")
    public String addUser(Model model) {
        User user = new User();

        model.addAttribute("signup", user);
        return "signup";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        wishlistService.addUser(user);

        return "redirect:/wishlist/profile";

    }



    //ER DET DEN SAMMEN SOM DEN FOR NEDEN???
    @GetMapping("myWishList/{id}")
    public String myWishList(Model model, @PathVariable int id) {
        WishList wishList = wishlistService.searchWishList(id);
        List<Wish> wishes = wishlistService.getWishes();
        model.addAttribute("myWishList", wishList);
        model.addAttribute("wishes", wishes);
        return "myWishlist";
    }


    @PostMapping("/{id}/update")
    public String updateWish(@PathVariable String name, Model model) {
        Wish wish = (Wish) wishlistService.searchWish(name);
        if (wish == null) {
            throw new IllegalArgumentException("error, invalid wish name");
        }
        model.addAttribute("wish", wish);

        return "redirect: /profile";
    }

    @PostMapping("{id}/delete")
    public String deleteWish(@PathVariable int id) {
        wishlistService.deletewish(id);

        return "redirect: /profile";
    }

    //Kig denne metode igennem
    @GetMapping("/addWishlist/{username}")
    public String addWishlist(Model model, @PathVariable String username) {

        System.out.println(username);

        WishList newWishList = new WishList();
        newWishList.setUserKey(username);
        System.out.println(newWishList.getUserKey() + "vi er her");

        model.addAttribute("addWishlist", newWishList);

        return "addWishlist";
    }

    @PostMapping("/addWishlist")
    public String saveWishList(WishList wishList) {


        wishlistService.addWishList(wishList);
        return "redirect:/profile";
    }


    @GetMapping("/addWish")
    public String addWish(Model model) {
        Wish newWish = new Wish();
        model.addAttribute("addGift", newWish);

        return "addGift";
    }

    @PostMapping("/addWish")
    public String saveWish(Wish wish) {
        wishlistService.addWish(wish);
        return "redirect:/wishlist/list";
    }


}
