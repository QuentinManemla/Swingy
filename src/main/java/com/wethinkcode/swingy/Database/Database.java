package com.wethinkcode.swingy.Database;

/* Imports */
import java.sql.*;
import java.math.*;

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

    public void addHero() {
        PreparedStatement pstmt = null;
        try {
            String SQL = "SELECT * FROM heroes";
            pstmt = this.Con.prepareStatement(SQL);

            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error: unable to Run Prepared Statement!");
            System.exit(1);
        }
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
