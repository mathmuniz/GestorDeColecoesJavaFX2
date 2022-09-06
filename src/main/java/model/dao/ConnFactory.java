/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author mmuni
 */
public class ConnFactory {

    public static Connection getConnection() throws SQLException {
        Connection conn
                = DriverManager.getConnection("jdbc:mysql://localhost:3306/skin", "root", "");
        return conn;

    }

}
