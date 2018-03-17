/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import entity.User;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Remove;
/**
 *
 * @author Vasilisa
 */
@Remote
public interface UserInterface {
    public List<User> findUserEntities();
}
