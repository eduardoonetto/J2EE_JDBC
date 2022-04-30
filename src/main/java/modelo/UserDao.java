/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eduardo.Onetto
 */
public class UserDao {
    //instanciar la clase Conexition que creamos en el package config
    Conexion cn = new Conexion();
    //Variable de tipo connection desde java.sql.Connection
    Connection con;
    //Variable de tipo PreparedStatement desde java.sql.PreparedStatement
    PreparedStatement ps;
    //Variable de tipo ResultSet desde java.sql.ResulrSet
    ResultSet rs;
    
    
     public boolean insert(String email, String password) {
        String Insert = "INSERT INTO user (email, password) VALUES(?,?)";

        try {
            //Instaciamos la Clase Conexion (cn) y traemos su propiedad de Conexion (con)
            con = cn.con;
            //Pasamos la Instruccion SQL
            ps = con.prepareStatement(Insert);
            //llenamos los ?,?
            ps.setString(1, email);
            ps.setString(2, password);
            //ejecutamos
            boolean a = (ps.executeUpdate() > 0) ? true : false;
        } catch (SQLException e) {
            System.err.println("error= " + e);
            return false;
        }
        
        return true;
    }
}
