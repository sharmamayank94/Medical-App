package dao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DeLL
 */
public class DBconnection {
    private static Connection conn;
    static{
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn= DriverManager.getConnection("jdbc:mysql:thin:@DESKTOP-QILU182:1521/xe","system","ananya");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/medical","admin","password");
//            JOptionPane.showMessageDialog(null,"connection done succesfully","success",JOptionPane.INFORMATION_MESSAGE);

        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null,"class not found");
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, "sql exception..");
        }
    }
    public static Connection getConnection(){
        return conn;
    }
    public static void closeConnection(){
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Not able to close the connection");

        }
    }
}
