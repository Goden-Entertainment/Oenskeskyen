package org.example.oenskeskyen.repository;

import org.example.oenskeskyen.model.Wish;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class WishlistRepository {

    JdbcTemplate jdbcTemplate;

    public WishlistRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void makeTabel () {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users(int id AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), password VARCHAR(255), email VARCHAR(255)");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS wishlist(VARCHAR name AUTO_INCREMENT PRIMARY KEY,");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS wishes(VARCHAR name AUTO_INCREMENT PRIMARY KEY, price DOUBLE, link VARCHAR(255))");

    }


    public List<Wish> getWishlist() {
        String sql = "SELECT * FROM wishes";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Wish(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("link")
                )
        );
    }


}
