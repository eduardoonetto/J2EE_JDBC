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
import java.util.ArrayList;
import java.util.List;

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
        String inset = "INSERT INTO `user` (`email`, `password`) VALUES(?,?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(inset);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.executeUpdate();
            cn.desconectar();
            return true;

        } catch (SQLException e) {
            System.err.println("error " + e);
            return false;
        } finally {
            if (cn == null) {
                cn.desconectar();
            }
        }
    }

    public List<User> list() {
        List<User> listUsers = new ArrayList<User>();
        String list = "SELECT * FROM user";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(list);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                User u = new User(id, email, password);
                listUsers.add(u);
            }
            cn.desconectar();
        } catch (SQLException e) {

        } finally {
            if (cn == null) {
                cn.desconectar();
            }

        }
        return listUsers;
    }

    public boolean delete(int id) {
        String del = "DELETE FROM user where id=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(del);
            ps.setInt(1, id);
            ps.executeUpdate();
            cn.desconectar();
            return true;
        } catch (SQLException e) {
            System.out.println("ERROR= "+ e);
            return false;
        } finally {
            if (cn == null) {
                cn.desconectar();
            }
        }
    }

    public User getById(int id) {
        User u = new User();
        String getById = "select * from user where id_user=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(getById);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                u.id = rs.getInt("id");
                u.email = rs.getString("email");
                u.password = rs.getString("password");
            }
            cn.desconectar();
            return u;
        } catch (SQLException e) {
            return null;
        } finally {
            if (cn == null) {
                cn.desconectar();
            }
        }

    }

}
