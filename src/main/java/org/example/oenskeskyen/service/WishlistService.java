package org.example.oenskeskyen.service;

import org.example.oenskeskyen.model.Wish;
import org.example.oenskeskyen.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {


    final private WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wish> getWishlist() {
        return wishlistRepository.getWishlist();
    }
}
