package com.example.service;

import com.example.entity.Credit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreditService implements Service<Credit> {
    public boolean initialize() {
        String sql = new StringBuilder().append("CREATE TABLE IF NOT EXISTS `credits` (\n")
                .append("	`id` integer PRIMARY KEY,\n")
                .append("	`clientId` integer REFERENCES `clients`(`id`) ,\n")
                .append("	`amount` double NOT NULL,\n")
                .append("	`date` date NOT NULL\n")
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
    public Boolean insert(Credit instance) {
        try {
            String req = "INSERT INTO `credits` ("
                    + "`id`, `clientId`, `amount`, `date`"
                    + ") VALUES ("
                    + "?, ?, ?, ?"
                    + ");";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setObject(1, instance.getId(), java.sql.Types.INTEGER);
            ps.setObject(2, instance.getClientId(), java.sql.Types.INTEGER);
            ps.setDouble(3, instance.getAmount());
            ps.setDate(4, new Date(instance.getDate().getTime()));

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;    }

    @Override
    public List<Credit> find(Integer offset, Integer rowCount) {
        List<Credit> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `credits`";
            if (rowCount != null) {
                req += " LIMIT "+ rowCount;
            }
            if (offset != null) {
                req += " OFFSET " + offset;
            }
            req += "ORDER BY `date` DESC;";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new Credit(
                        rs.getObject("id", Integer.class),
                        rs.getObject("clientId", Integer.class),
                        rs.getDouble("amount"),
                        rs.getDate("date")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return l;
    }

    public List<Credit> findByClientId(Integer clientId, Integer offset, Integer rowCount) {
        List<Credit> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `credits` WHERE `clientId`=" + clientId;
            if (rowCount != null) {
                req += " LIMIT "+ rowCount;
            }
            if (offset != null) {
                req += " OFFSET " + offset;
            }
            req += "ORDER BY `date` DESC;";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new Credit(
                        rs.getObject("id", Integer.class),
                        rs.getObject("clientId", Integer.class),
                        rs.getDouble("amount"),
                        rs.getDate("date")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return l;
    }

    @Override
    public Boolean modify(Credit instance) {
        try {
            String req = "UPDATE `credits` SET "
                    + "`clientId`=?, `amount`=?, `date`=? WHERE `id`=" + instance.getId();
            req += ";";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setObject(1, instance.getClientId(), java.sql.Types.INTEGER);
            ps.setDouble(2, instance.getAmount());
            ps.setDate(3, (Date) instance.getDate());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;    }

    @Override
    public Boolean delete(Credit instance) {
        try {
            String req = "DELETE FROM `credits` WHERE `id`=" + instance.getId();
            req += ";";
            Statement s = connection.createStatement();

            return s.executeUpdate(req) > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    public Boolean deleteByClient(Integer clientId) {
        try {
            String req = "DELETE FROM `credits` WHERE `clientId`=" + clientId;
            req += ";";
            Statement s = connection.createStatement();

            return s.executeUpdate(req) > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
}
