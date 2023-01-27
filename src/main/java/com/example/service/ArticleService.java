package com.example.service;

import com.example.entity.Article;
import com.example.entity.Client;
import com.example.entity.Credit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleService implements Service<Article> {
    public boolean initialize() {
        String sql = new StringBuilder().append("CREATE TABLE IF NOT EXISTS `articles` (\n")
                .append("	`id` integer PRIMARY KEY,\n")
                .append("	`title` text NOT NULL,\n")
                .append("	`defaultPrice` double NOT NULL\n")
                .append(");")
                .toString();

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
    public Boolean insert(Article instance) {
        try {
            String req = "INSERT INTO `articles` ("
                    + "`id`, `title`, `defaultPrice`"
                    + ") VALUES ("
                    + "?, ?, ?"
                    + ");";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setObject(1, instance.getId(), java.sql.Types.INTEGER);
            ps.setString(2, instance.getTitle());
            ps.setDouble(3, instance.getDefaultPrice());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Article> find(Integer offset, Integer rowCount) {
        List<Article> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `articles`";
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
                l.add(new Article(
                        rs.getObject("id", Integer.class),
                        rs.getString("title"),
                        rs.getDouble("defaultPrice")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return l;
    }

    public Article findOne(Integer id) {
        try {
            String req = "SELECT * FROM `articles` WHERE `id`="+id;
            req += ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            if (rs.next()) {
                return new Article(
                        rs.getObject("id", Integer.class),
                        rs.getString("title"),
                        rs.getDouble("defaultPrice")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Boolean modify(Article instance) {
        try {
            String req = "UPDATE `articles` SET "
                    + "`title`=?, `defaultPrice`=? WHERE `id`=" + instance.getId();
            req += ";";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, instance.getTitle());
            ps.setDouble(2, instance.getDefaultPrice());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean delete(Article instance) {
        try {
            String req = "DELETE FROM `articles` WHERE `id`=" + instance.getId();
            req += ";";
            Statement s = connection.createStatement();

            return s.executeUpdate(req) > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
}
