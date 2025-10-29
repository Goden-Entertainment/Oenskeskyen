package org.example.oenskeskyen.repository;

import org.example.oenskeskyen.model.Wish;
import org.example.oenskeskyen.model.WishList;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class WishlistRepository {

    JdbcTemplate jdbcTemplate;

    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //opmærksom på hvordan wishes er skrevet op
    public void makeTabel() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users(int id AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), password VARCHAR(255), email VARCHAR(255)");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS wishlist(name VARCHAR(255) AUTO_INCREMENT PRIMARY KEY,");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS wishes( int id AUTO_INCREMENT PRIMARY KEY,VARCHAR name(255) NOT NULL, price DOUBLE, link VARCHAR(255))");

    }


    public List<WishList> getWishList() {
        String sqlGet = "SELECT * FROM wishlist";
        return jdbcTemplate.query(sqlGet, (rs, rowNum) ->
                new WishList(rs.getString("name"))
        );
    }

//Tilkobel den specifikke ønskeliste til de forskellige ønsker !!!!!
    public List<Wish> getWishes() {
        String sqlGet = "SELECT * FROM wishes";
        return jdbcTemplate.query(sqlGet, (rs, rowNum) ->
                new Wish(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("link"),
                        rs.getInt("id")
                )
        );
    }


    public void addWishList(WishList wishList) {
        String sqlAdd = "INSERT INTO wishlist (name) values(?)";
        jdbcTemplate.update(sqlAdd, wishList.getName());
    }

    public void addWish(Wish wish) {
        String sqlAdd = "INSERT INTO wishes (name, link, price ) values (?,?,?)";
        jdbcTemplate.update(sqlAdd, wish.getName(), wish.getLink(), wish.getPrice());
    }


    public void updateWish(Wish wish) throws DataAccessException {
        String sqlUpdate = "UPDATE wishes SET name = ?, price = ?, link = ? WHERE id = ? ";
        jdbcTemplate.query(sqlUpdate, (rs, rowNum) ->
                        wish.getName(),
                wish.getPrice(),
                wish.getLink(),
                wish.getLink());

    }

    public void deletewish(int id) throws DataAccessException {
        String sqlDel = "DELETE FROM wishes where id = ? ";
        jdbcTemplate.update(sqlDel, id);
    }

    public Wish serchWish(int id) {
        String sqlSerch = "SELECT * FROM wishes WHERE id = ?";


        return jdbcTemplate.queryForObject(sqlSerch, (rs, rowNum) ->
                new Wish(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("link"),
                        rs.getInt("id")
                )
        );
    }


}
