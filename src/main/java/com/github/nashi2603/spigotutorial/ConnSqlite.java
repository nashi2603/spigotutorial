package com.github.nashi2603.spigotutorial;

import java.sql.*;

public class ConnSqlite {
    public Connection connectsqlite() {
        String dbname = "spigotutorial.sqlite";
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:plugins/Spigotutorial/" + dbname);
//            stmt = conn.createStatement();
//            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS testtable1(id int NOT NULL PRIMARY KEY, itemdata blob)");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Connection insertsqlite(Connection conn, Object itemdata) {
        try {
            Statement stmt = conn.createStatement();
            PreparedStatement psst = conn.prepareStatement("insert into testtable1(itemdata) VALUES(?)");
            psst.setObject(0, itemdata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
