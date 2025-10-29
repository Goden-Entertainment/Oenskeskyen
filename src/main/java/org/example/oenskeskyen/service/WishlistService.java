package org.example.oenskeskyen.service;

import org.example.oenskeskyen.model.Wish;
import org.example.oenskeskyen.model.WishList;
import org.example.oenskeskyen.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {


    final private WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wish> getWishes() {
        return wishlistRepository.getWishes();
    }

    public void addWishList(WishList wishList){
        wishlistRepository.addWishList(wishList);
    }

    public void addWish(Wish wish){
        wishlistRepository.addWish(wish);
    }


    public void updateWish(Wish wish) {
        wishlistRepository.updateWish(wish);
    }

    public void deletewish(int id) {
        wishlistRepository.deletewish(id);
    }

    public Wish serchWish(int id) {
       return wishlistRepository.serchWish(id);
    }



}
