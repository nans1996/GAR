/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasilisa
 */
@Local
public interface UserFacadeLocal {

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);
    
    User findLogin(String login);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();
    
}
