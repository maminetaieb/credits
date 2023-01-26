package com.example.service;

import com.example.entity.Client;
import com.example.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserService implements Service<User> {
    public boolean initialize() {
        String sql = """
                CREATE TABLE IF NOT EXISTS `users` (
                	login text PRIMARY KEY,
                	password text NOT NULL
                );""";

        try (Statement stmt = connection.createStatement()) {
            // create a new table
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    @Override
    public Boolean insert(User instance) {
        try {
            String req = "INSERT INTO `users` ("
                    + "`login`, `password`"
                    + ") VALUES ("
                    + "?, ?"
                    + ");";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, instance.getLogin());
            ps.setString(2, instance.getPassword());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;    }

    @Override
    public List<User> find(Integer offset, Integer rowCount) {
        List<User> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `users`";
            if (rowCount != null) {
                req += " LIMIT "+ rowCount;
            }
            if (offset != null) {
                req += " OFFSET " + offset;
            }
            req += ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new User(
                        rs.getString("login"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return l;
    }

    public User authenticate(String login, String password) {
        try {
            String req = "SELECT * FROM `users` WHERE `login` LIKE '" +login+ "' AND `password` LIKE '"+password+"'";
            req += ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            if (rs.next()) {
                return new User(
                        rs.getString("login"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Boolean modify(User instance) {
        try {
            String req = "UPDATE `users` SET "
                    + "`login`=?, `password`=? WHERE `login` LIKE '" + instance.getLogin()+"'";
            req += ";";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, instance.getLogin());
            ps.setString(2, instance.getPassword());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;    }

    @Override
    public Boolean delete(User instance) {
        try {
            String req = "DELETE FROM `users` WHERE `login` LIKE '"+instance.getLogin() +"'";
            req += ";";
            Statement s = connection.createStatement();

            return s.executeUpdate(req) > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
}
