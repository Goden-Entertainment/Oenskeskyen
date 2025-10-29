package controller;

import org.example.oenskeskyen.model.Wish;
import org.example.oenskeskyen.service.WishlistService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(WishListControllerTest.class)
public class WishListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishlistService wishlistService;

    @BeforeEach
    void setup(){}

    @AfterEach
    void tearDown(){}

    @Test
    void shouldGetAllWishes() throws Exception{
        Wish wishTest = new Wish("Brandbil", 250, "WWW.br.dk", 1, "Det er en blå brndbil");
        when(wishlistService.getWishes()).thenReturn(List.of(wishTest));

        mockMvc.perform(get("/wishlist/list"))
                .andExpect(view().name("wishlist"))
                .andExpect(status().isOk());
    }

}
