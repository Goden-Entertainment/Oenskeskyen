package org.example.oenskeskyen.service;

import org.example.oenskeskyen.exceptions.DatabaseOperationException;
import org.example.oenskeskyen.exceptions.DuplicateProfileException;
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


    public User addUser(User user) {

        try {
            User savedUser = wishlistRepository.addUser(user);

            if (savedUser == null) {
                throw new ProfileNotFoundException(user.getId());
            }

            return savedUser;

        } catch (DataIntegrityViolationException e) {
            // Dette sker når email allerede findes (constraint i DB)
            throw new DuplicateProfileException("A profile with this email already exists");
        } catch (DataAccessException e) {
            // Almindelige databasefejl
            throw new DatabaseOperationException("Failed to update profile", e);
        }
    }



    public List<Wish> getWishesByWishlistId(int wishlistId) {
        return wishlistRepository.getWishesByWishlistId(wishlistId);
    }

    public List<WishList> getWishList(String userKey) {
        return wishlistRepository.getWishList(userKey);
    }

    public void addWishList(WishList wishList) {
        wishlistRepository.addWishList(wishList);
    }

    public WishList searchWishList(int id){
        return wishlistRepository.serchWishList(id);
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

    public Wish searchWish(String name) {
        return wishlistRepository.serchWish(name);
    }


    public User getUserByEmailAndPassword(String email, String password) {
        return wishlistRepository.getUserByEmailAndPassword(email, password);
    }

    public User getUserByUsername(String username){
        return wishlistRepository.getUserByUsername(username);
    }
}
