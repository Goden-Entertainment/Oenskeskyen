package org.example.oenskeskyen.h2Test;

import org.example.oenskeskyen.model.User;
import org.example.oenskeskyen.model.Wish;
import org.example.oenskeskyen.repository.WishlistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
public class oenskeskyenRepoTest {

    @Autowired
    private WishlistRepository repo;

    @Test
    void all() {
        int wishlistId = 1;

        List<Wish> all = repo.getWishesByWishlistId(wishlistId);

        assertThat(all).isNotNull();
        assertThat(all.size()).isEqualTo(2);
        assertThat(all.get(0).getName()).isEqualTo("helicoptor");
        assertThat(all.get(1).getName()).isEqualTo("kat");
    }

    @Test
    void insertAndReadBack() {

        repo.addUser(new User("bob", "kode1234", "bob@gmail.com"));
        var bob = repo.getUserByEmailAndPassword("bob@gmail.com", "kode1234");
        assertThat(bob).isNotNull();
        assertThat(bob.getEmail()).isEqualTo("bob@gmail.com");

    }
}
