package org.example.oenskeskyen.service;

import org.example.oenskeskyen.exceptions.DatabaseOperationException;
import org.example.oenskeskyen.exceptions.ProfileNotFoundException;
import org.example.oenskeskyen.model.User;
import org.example.oenskeskyen.model.Wish;
import org.example.oenskeskyen.model.WishList;
import org.example.oenskeskyen.repository.WishlistRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {


    final private WishlistRepository wishlistRepository;
    private User user;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }


    public void addUser(User user) {
        wishlistRepository.addUser(user);
    }


    public List<Wish> getWishes(int id) {
        return wishlistRepository.getWishes(id);
    }

    public List<WishList> getWishList() {
        return wishlistRepository.getWishList();
    }

    public void addWishList(WishList wishList) {
        wishlistRepository.addWishList(wishList);
    }

    public void addWish(Wish wish) {
        wishlistRepository.addWish(wish);
    }


    public void updateWish(Wish wish) {
        wishlistRepository.updateWish(wish);
    }

    public void deletewish(int id) {

        try {
            int rows = wishlistRepository.deletewish(id);
            if (rows == 0) throw new ProfileNotFoundException(id);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to delete profile", e);
        }

        //Tidligere metode
        // wishlistRepository.deletewish(id);
    }

    public Wish serchWish(int id) {
        return wishlistRepository.serchWish(id);
    }


    public User getUserByEmailAndPassword(String email, String password) {
        return wishlistRepository.getUserByEmailAndPassword(email, password);
    }

}
