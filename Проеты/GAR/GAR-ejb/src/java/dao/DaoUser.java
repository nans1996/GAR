/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless;
import entities.User;

/**
 *
 * @author Vasilisa
 */
//@Stateless
public class DaoUser extends DAO<User> implements Serializable, UserDAORemote {
    
   //@Override
   //public entities.User getId(String id) {
   //     try {
    //        Connection conn = connection_DataSource();
    //        PreparedStatement ps = conn.prepareStatement("SELECT Login, Pass, Surname,Name,Phone,Email FROM User_Role,User WHERE User_Role.Login = User.Login AND myuser.username = ?");
    //        ps.setString(1, id);
     //       ResultSet rs = ps.executeQuery();
     //       rs.next();
     //       User value = new User();
     //           //value.setUsername(rs.getString("Username"));
     //           //value.setPassword(rs.getString("Password"));
     //           //value.setFio(rs.getString("Fio"));
     //           //value.setBirthdate(rs.getString("Birthdate"));
    //            //value.setRole(rs.getString("Role"));
    //        conn.close();
    //        return value;
    //    } catch (SQLException ex) {
    //        System.out.println(ex.getMessage());
    //        return new User();
   //     }
   // }
}
