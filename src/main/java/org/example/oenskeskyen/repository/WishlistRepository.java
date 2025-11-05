package org.example.oenskeskyen.repository;

import jakarta.annotation.PostConstruct;
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
    }

    @PostConstruct
    public void init(){
        makeTable();
    }

    //opmærksom på hvordan wishes er skrevet op
    public void makeTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users(id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), password VARCHAR(255), email VARCHAR(255) UNIQUE)");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS wishlist(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), userKey VARCHAR(255))");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS wishes(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) NOT NULL, price DOUBLE, link VARCHAR(255), description VARCHAR(255), wishlistKey INT)");

    }

    public void testData() {
//        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
//
//        if (count != null && count > 0) {
//            System.out.println("Testdata findes allerede – springer over.");
//            return;
//        }

        jdbcTemplate.update("INSERT INTO users(username, password, email) VALUES (?, ?, ?)", "Goden", "Kode", "goden@gmail.com");
        jdbcTemplate.update("INSERT INTO users(username, password, email) VALUES (?, ?, ?)", "Yadiii", "Deeznuts", "yadi123@gmail.com");
        jdbcTemplate.update("INSERT INTO users(username, password, email) VALUES (?, ?, ?)", "RuneDog", "missekat", "missekat@gmail.com");
        jdbcTemplate.update("INSERT INTO users(username, password, email) VALUES (?, ?, ?)", "Unc", "Morp", "unc@gmail.com");


        jdbcTemplate.update("INSERT INTO wishlist(name, userKey) VALUES (?,?)", "Godens Ønskeliste", "Goden");
        jdbcTemplate.update("INSERT INTO wishlist(name, userKey) VALUES (?,?)", "Yadis ønskeliste", "Yadiii");
        jdbcTemplate.update("INSERT INTO wishlist(name, userKey) VALUES (?,?)", "Druid Ønskeliste", "RuneDog");
        jdbcTemplate.update("INSERT INTO wishlist(name, userKey) VALUES (?,?)", "Unc", "Unc");

        jdbcTemplate.update("INSERT INTO wishes(name, price, link, description, wishlistKey) VALUES (?, ?, ?, ?, ?)", "Brøndbil", 250, "www.yadi.com", "Den skal være rød", 1);
        jdbcTemplate.update("INSERT INTO wishes(name, price, link, description, wishlistKey) VALUES (?, ?, ?, ?, ?)", "Tshirt H&M", 450, "august.com", "Den skal være i M og sort", 1);
    }


    public User addUser(User user) {
        String sqlAddUser = "INSERT INTO users (username, password, email) values (?,?,?)";

        jdbcTemplate.update(sqlAddUser, user.getUsername(), user.getPassword(), user.getEmail());

        return user;
    }

    //Her
    public List<WishList> getWishList(String userKey) {
        String sqlGet = "SELECT id, name, userKey FROM wishlist WHERE userKey = ? ";
        return jdbcTemplate.query(sqlGet, (rs, rowNum) ->
                new WishList(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("userKey")
                )
                , userKey
        );
    }


    //Lavet navn om, den tager en wishlistId, så vi får den specifikke ønskeliste til den specifikke bruger, bed brug af WHERE
    public List<Wish> getWishesByWishlistId(int wishlistId) {
        String sqlGet = "SELECT * FROM wishes WHERE wishlistKey = ?";
        return jdbcTemplate.query(sqlGet, (rs, rowNum) ->
                new Wish(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("link"),
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getInt("wishlistKey")
                ),
                //Dette er WHERE = ? i SQL'en, hvor fx hvis værdien er 3 så bliver det sat ind i '?'
                //Står bagefter fordi det er en del af SQL'en og ikke new wish object.
                wishlistId
        );
    }

    //test
    public void addWishList(WishList wishList) {
        String sqlAdd = "INSERT INTO wishlist (name,userKey) values(?,?)";
        jdbcTemplate.update(sqlAdd, wishList.getName(),wishList.getUserKey());
    }

    public WishList serchWishList(int id) {
        String sqlSearch = "SELECT * FROM wishlist WHERE id = ?";


        return jdbcTemplate.queryForObject(sqlSearch, new Object[]{id}, (rs, rowNum) ->
                new WishList(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("userKey")
                        ));
    }

    //Test
    public void addWish(Wish wish) {
        String sqlAdd = "INSERT INTO wishes (name, link, price, description, wishlistKey) values (?,?,?,?,?)";
        jdbcTemplate.update(sqlAdd, wish.getName(), wish.getLink(), wish.getPrice(), wish.getDescription(), wish.getWishlistKey());
    }


    public void updateWish(Wish wish) throws DataAccessException {
        String sqlUpdate = "UPDATE wishes SET name = ?, price = ?, link = ? WHERE id = ? ";
        jdbcTemplate.query(sqlUpdate, (rs, rowNum) ->
                        wish.getName(),
                wish.getPrice(),
                wish.getLink(),
                wish.getLink());

    }

    //
    public int deletewish(int id) throws DataAccessException {
        String sqlDel = "DELETE FROM wishes where id = ? ";

        return jdbcTemplate.update(sqlDel, id);
    }

    public Wish serchWish(String name) {
        String sqlSearch = "SELECT * FROM wishes WHERE name = ?";


        return jdbcTemplate.queryForObject(sqlSearch, new Object[]{name}, (rs, rowNum) ->
                new Wish(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("link"),
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getInt("wishlistKey")
                )
        );
    }

    //Metode tiføjet til at finde en bruger i databasen ud fra email + password
    public User getUserByEmailAndPassword(String email, String password) {
            String sql = """
            SELECT id, username, password, email
            FROM users
            WHERE LOWER(email) = LOWER(?) AND password = ?
            LIMIT 1
        """;

        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                            new User(
                                    rs.getString("username"),
                                    rs.getString("password"),
                                    rs.getInt("id"),
                                    null, // ingen wishlist
                                    rs.getString("email")
                            ),
                    email, password
            );
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            // Ingen bruger fundet returner null
            return null;
        }
    }

    public User getUserByUsername(String username){
        String sql = "select * from users where name = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("id"),
                        null,
                        rs.getString("email")
                ),
                username
        );
    }
}
