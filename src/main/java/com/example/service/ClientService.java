package com.example.service;

import com.example.entity.Client;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class ClientService implements Service<Client> {
    public boolean initialize() {
        String sql = """
                CREATE TABLE IF NOT EXISTS `clients` (
                	id integer PRIMARY KEY,
                	fullName text NOT NULL,
                	phoneNumber text NOT NULL
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
    public Boolean insert(Client instance) {
        try {
            String req = "INSERT INTO `clients` ("
                    + "`id`, `fullname`, `phoneNumber`"
                    + ") VALUES ("
                    + "?, ?, ?"
                    + ");";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setObject(1, instance.getId(), java.sql.Types.INTEGER);
            ps.setString(2, instance.getFullName());
            ps.setString(3, instance.getPhoneNumber());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Client> find(Integer offset, Integer rowCount) {
        List<Client> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `clients`";
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
                l.add(new Client(
                        rs.getObject("id", Integer.class),
                        rs.getString("fullName"),
                        rs.getString("phoneNumber")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return l;
    }

    public Client findOne(Integer id) {
        try {
            String req = "SELECT * FROM `clients` WHERE `id`="+id;
            req += ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            if (rs.next()) {
                return new Client(
                        rs.getObject("id", Integer.class),
                        rs.getString("fullName"),
                        rs.getString("phoneNumber")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public List<Client> findByFullName(String fullName, Integer offset, Integer rowCount) {
        List<Client> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `clients` WHERE `fullName` LIKE '%" + fullName + "%'";
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
                l.add(new Client(
                        rs.getObject("id", Integer.class),
                        rs.getString("fullName"),
                        rs.getString("phoneNumber")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return l;
    }

    public List<Client> findByPhoneNumber(String phoneNumber, Integer offset, Integer rowCount) {
        List<Client> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `clients` WHERE `phoneNumber` LIKE '%" + phoneNumber + "%'";
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
                l.add(new Client(
                        rs.getObject("id", Integer.class),
                        rs.getString("fullName"),
                        rs.getString("phoneNumber")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return l;
    }

    @Override
    public Boolean modify(Client instance) {
        try {
            String req = "UPDATE `clients` SET "
                    + "`fullName`=?, `phoneNumber`=? WHERE `id`=" + instance.getId();
            req += ";";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setObject(1, instance.getId(), java.sql.Types.INTEGER);
            ps.setString(2, instance.getFullName());
            ps.setString(3, instance.getPhoneNumber());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean delete(Client instance) {
        try {
            String req = "DELETE FROM `clients` WHERE `id`="+instance.getId();
            req += ";";
            Statement s = connection.createStatement();

            return s.executeUpdate(req) > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
}
