package org.example.oenskeskyen.repository;

import org.example.oenskeskyen.model.User;
import org.example.oenskeskyen.model.Wish;
import org.example.oenskeskyen.model.WishList;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class WishlistRepository {

    JdbcTemplate jdbcTemplate;

    private User user;

    public WishlistRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;

        makeTable();
        testData();
    }

    //opmærksom på hvordan wishes er skrevet op
    public void makeTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users(id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), password VARCHAR(255), email VARCHAR(255))");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS wishlist(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS wishes(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) NOT NULL, price DOUBLE, link VARCHAR(255), description VARCHAR(255))");

    }

    public void testData(){
        jdbcTemplate.update("INSERT IGNORE INTO users(username, password, email) VALUES (?, ?, ?)", "Goden", "Kode", "goden@gmail.com");
        jdbcTemplate.update("INSERT IGNORE INTO users(username, password, email) VALUES (?, ?, ?)", "Yadiii", "Deeznuts", "yadi123@gmail.com");
        jdbcTemplate.update("INSERT IGNORE INTO users(username, password, email) VALUES (?, ?, ?)", "RuneDog", "missekat", "missekat@gmail.com");
        jdbcTemplate.update("INSERT IGNORE INTO users(username, password, email) VALUES (?, ?, ?)", "Unc", "Morp", "unc@gmail.com");

        jdbcTemplate.update("INSERT IGNORE INTO wishlist(name) VALUES (?)", "Godens Ønskeliste");
        jdbcTemplate.update("INSERT IGNORE INTO wishlist(name) VALUES (?)", "Yadis ønskeliste");
        jdbcTemplate.update("INSERT IGNORE INTO wishlist(name) VALUES (?)", "Druid Ønskeliste");
        jdbcTemplate.update("INSERT IGNORE INTO wishlist(name) VALUES (?)", "Unc");

        jdbcTemplate.update("INSERT IGNORE INTO wishes(name, price, link, description) VALUES (?, ?, ?, ?)", "Brøndbil", "250 kr.", "www.yadi.com", "Den skal være rød");
        jdbcTemplate.update("INSERT IGNORE INTO wishes(name, price, link, description) VALUES (?, ?, ?, ?)", "Tshirt H&M", "450 kr.", "august.com", "Den skal være i M og sort");
    }


    public List<User> addUser(User user){
        String sqlAddUser = "INSERT INTO users (username, password, id, email, wishlist) values (?,?,?,?,?)";

        jdbcTemplate.update(sqlAddUser, user.getUsername(), user.getPassword(), user.getEmail(), user.getId(), user.getWishlist());

        //Skal den stå tom eller er dette rigtigt?
        return List.of(user);
    }

    public List<WishList> getWishList() {
        String sqlGet = "SELECT * FROM wishlist";
        return jdbcTemplate.query(sqlGet, (rs, rowNum) ->
                new WishList(rs.getString("name"))
        );
    }

//Tilkobel den specifikke ønskeliste til de forskellige ønsker !!!!!
    public List<Wish> getWishes(int id) {
        String sqlGet = "SELECT * FROM wishes";
        return jdbcTemplate.query(sqlGet, (rs, rowNum) ->
                new Wish(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("link"),
                        rs.getInt("id"),
                        rs.getString("description")
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

    public int deletewish(int id) throws DataAccessException {
        String sqlDel = "DELETE FROM wishes where id = ? ";

        return  jdbcTemplate.update(sqlDel, id);
    }

    public Wish serchWish(int id) {
        String sqlSerch = "SELECT * FROM wishes WHERE id = ?";


        return jdbcTemplate.queryForObject(sqlSerch, (rs, rowNum) ->
                new Wish(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("link"),
                        rs.getInt("id"),
                        rs.getString("description"))
        );
    }


}
