package org.example.oenskeskyen.controller;

import org.example.oenskeskyen.model.User;
import org.example.oenskeskyen.model.Wish;
import org.example.oenskeskyen.service.WishlistService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishlistController.class)
public class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishlistService wishlistService;

    @BeforeEach
    void setup(){}

    @AfterEach
    void tearDown(){}

    //Tester myWishList GetMapping controlleren, checker om den
    @Test
    void shouldGetAllWishes() throws Exception{
        int id = 1;
        Wish wishTest = new Wish("Brandbil", 250, "WWW.br.dk", 1, "Det er en blå brandbil");
        when(wishlistService.getWishes(1)).thenReturn(List.of(wishTest));

        mockMvc.perform(get("/wishlist/wishList").param("id", String.valueOf(id)))
                .andExpect(status().isOk())
                .andExpect(view().name("myWishList"))
                .andExpect(model().attributeExists("myWishList"))
                .andExpect(model().attribute("myWishList", List.of(wishTest)))
                .andExpect(model().attributeExists("addWishForm"));
    }

    //Venter med at lave testen færdig til addUser er done.
//    @Test
//    void shouldAddUser() throws Exception{
//        User userTest = new User("Yadi", "kode123", 1, List.of(), "yadi@gmail.com");
//        when(wishlistService.get)
//    }

    @Test
    void shouldUpdateWish() throws Exception {

        Wish wishTest = new Wish("T-shirt", 350, "www.link.dk", 1, "Sort tshirt i M");
        when(wishlistService.searchWish("T-shirt")).thenReturn(wishTest);

        mockMvc.perform(post("/wishlist/T-shirt/update"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlist/profile"));
    }
}
