package com.example.service;

import com.example.entity.ClientArticle;
import com.example.entity.User;

import java.sql.*;
import java.util.List;

public class ClientArticleService implements Service<ClientArticle> {
    public boolean initialize() {
        String sql = new StringBuilder().append("CREATE TABLE IF NOT EXISTS `clientArticles` (\n")
                .append("	`clientId` integer REFERENCES `clients`(`id`),\n")
                .append("	`articleId` integer REFERENCES `articles`(`id`),\n")
                .append("	`customPrice` double NOT NULL,\n")
                .append("	PRIMARY KEY(`clientId`, `articleId`)\n")
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
    public Boolean insert(ClientArticle instance) {
        try {
            String req = "INSERT INTO `clientArticles` ("
                    + "`clientId`, `articleId`, `customPrice`"
                    + ") VALUES ("
                    + "?, ?, ?"
                    + ");";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setObject(1, instance.getClientId(), java.sql.Types.INTEGER);
            ps.setObject(2, instance.getArticleId(), java.sql.Types.INTEGER);
            ps.setDouble(3, instance.getCustomPrice());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<ClientArticle> find(Integer offset, Integer rowCount) {
        return null;
    }

    public ClientArticle findOne(Integer clientId, Integer articleId) {
        try {
            String req = "SELECT * FROM `clientArticles` WHERE `clientId`=" + clientId
                    + " AND `articleId`=" + articleId;
            req += ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            if (rs.next()) {
                return new ClientArticle(
                        rs.getObject("clientId", Integer.class),
                        rs.getObject("articleId", Integer.class),
                        rs.getDouble("customPrice")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Boolean modify(ClientArticle instance) {
        try {
            String req = "UPDATE `clientArticles` SET "
                    + "`customPrice`=? WHERE `clientId`=" + instance.getClientId()
                    + " AND `articleId`=" + instance.getArticleId();
            req += ";";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setDouble(1, instance.getCustomPrice());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean delete(ClientArticle instance) {
        try {
            String req = "DELETE FROM `clientArticles` WHERE `clientId`=" + instance.getClientId()
                    + " AND `articleId`=" + instance.getArticleId();
            req += ";";
            Statement s = connection.createStatement();

            return s.executeUpdate(req) > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
    public Boolean deleteByClientId(Integer clientId) {
        try {
            String req = "DELETE FROM `clientArticles` WHERE `clientId`=" + clientId;
            req += ";";
            Statement s = connection.createStatement();

            return s.executeUpdate(req) > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
}
