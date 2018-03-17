/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitys.User;
import interfaceDao.UserInterface;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Vasilisa
 */
@Stateless
public class DAOuser extends DAO<User> implements UserInterface, Serializable{

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM user"; 
        List<User> list = new ArrayList<User>();
        
        try (PreparedStatement stm = DAO.getPrepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User value = new User();
                value.setLogin(rs.getString("Login"));
                list.add(value);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка : " + e.getMessage());
        }

        return list;
    }

    @Override
    public void update(User object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(User object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
