package org.example.oenskeskyen.controller;

import org.example.oenskeskyen.model.User;
import org.example.oenskeskyen.model.Wish;
import org.example.oenskeskyen.model.WishList;
import org.example.oenskeskyen.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("wishlist")
public class WishlistController {

    final private WishlistService wishlistService;
    private User user;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;

    }

    @GetMapping("homepage")
    public String homepage() {

        return "homepage";
    }


    @GetMapping("addUser")
    public String addUser(Model model) {
        User user = new User();

        model.addAttribute("signup", wishlistService.addUser(user));
        return "signup";
    }

    @GetMapping("profile")
    public String showProfile(Model model) {
        model.addAttribute("wishlist", wishlistService.getWishList());

        //henter alle ønskelister
        List<WishList> wishLists = wishlistService.getWishList();
        model.addAttribute("list", wishLists);
        return "profile";
    }


    //ER DET DEN SAMMEN SOM DEN FOR NEDEN???
    @GetMapping("wishList")
    public String myWishList(Model model, int id) {
        model.addAttribute("myWishList", wishlistService.getWishes(id));

        //Det forneden forkommer også i linje 108
        Wish newWish = new Wish();
        model.addAttribute("addWishForm", newWish);

        //SERCH WISH FUNKTIONEN MANGLER
        return "myWishList";
    }


    @PostMapping("/{id}/update")
    public String updateWish(@PathVariable int id, Model model) {
        Wish wish = (Wish) wishlistService.getWishes(id);
        if (wish == null) {
            throw new IllegalArgumentException("erro, invalid wish name");
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
    @GetMapping("/addWishListForm")
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
        model.addAttribute("addWishForm", newWish);
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
