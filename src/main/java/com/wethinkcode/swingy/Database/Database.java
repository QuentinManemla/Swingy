package com.wethinkcode.swingy.Database;

/* Imports */
import com.wethinkcode.swingy.Hero.Hero;

import java.sql.*;
import java.math.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String Url = "jdbc:mysql://db4free.net:3306/qmanamelswingy";
    private String Username = "qmanamel";
    private String Password = "zprcRfED";
    Connection Con;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Con = DriverManager.getConnection(Url, Username, Password);
            System.out.println("Success: Driver Class Loaded Successfully!");
        } catch (ClassNotFoundException ex ){
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        } catch (SQLException ex) {
            System.out.println("Error: unable to establish database connection: " + ex);
            System.exit(1);
        }
    }

    public List<Hero> getHeroes() {
        PreparedStatement pstmt = null;
        try {
            String SQL = "SELECT * FROM heroes";
            pstmt = this.Con.prepareStatement(SQL);

            List<Hero> heroes = new ArrayList<Hero>();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                heroes.add(new Hero(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getInt(4),
                        rs.getInt(5),rs.getInt(6),
                        rs.getInt(7), rs.getInt(8) ));
            }
            return (heroes);
        } catch (SQLException ex) {
            System.out.println("Error: unable to Run Prepared Statement!");
            System.exit(1);
        }
        return null;
    }

    public Hero getHero(Integer _heroId) {
        PreparedStatement pstmt = null;
        try {
            String SQL = "SELECT * FROM heroes WHERE id = " + _heroId;
            pstmt = this.Con.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            if (rs.getRow() > 0) {
                return new Hero(rs.getInt(1), rs.getString(2),rs.getString(3),
                        rs.getInt(4), rs.getInt(5),rs.getInt(6),
                        rs.getInt(7), rs.getInt(8) );
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Error: unable to Run Prepared Statement!");
            System.exit(1);
        }
        return null;
    }

    public int countHeroes() {
        PreparedStatement pstmt = null;
        try {
            String SQL = "SELECT * FROM heroes";
            pstmt = this.Con.prepareStatement(SQL);

            ResultSet rs = pstmt.executeQuery();
            rs.last();

            return (rs.getRow());
        } catch (SQLException ex) {
            System.out.println("Error: unable to Execute Prepared Statement! [coundHeroes] : " + ex);
            System.exit(1);
        }
        return (-1);
    }
}
