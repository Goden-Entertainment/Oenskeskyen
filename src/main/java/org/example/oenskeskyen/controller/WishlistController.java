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
    public String addUser(@ModelAttribute Model model) {
        wishlistService.addUser(user);

        model.addAttribute("signup", user);
        return "signup";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        wishlistService.addUser(user);

        return "redirect:/wishlist/profile";

    }


    @GetMapping("profile")
    public String showProfile(Model model) {
        //model.addAttribute("wishlist", wishlistService.getWishList());

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


    @PostMapping("/{name}/update")
    public String updateWish(@PathVariable String name, Model model) {
        Wish wish = (Wish) wishlistService.searchWish(name);

        if (wish == null) {
            throw new IllegalArgumentException("error, invalid wish name");
        }
        model.addAttribute("wish", wish);

        return "redirect:/wishlist/profile";
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

    //Mangler connection mellem html, muligvis ikke nødvendigt, og kan tilkobles til en anden controller.
    @PostMapping("/search")
    public String searchWish(@RequestParam String name, Model model){
        Wish wish = wishlistService.searchWish(name);

        if (wish == null){
            model.addAttribute("error", "Der blev ikke fundet et ønske med navnet: " + name);
            return "profile"; //Måske en anden
        }

        model.addAttribute("wish", wish);
        return "profile"; //ÆNDRE TIL NOGET MERE RELAVANT, måske en ny html? Evt en wishDetails.html
                          //
    }
}
