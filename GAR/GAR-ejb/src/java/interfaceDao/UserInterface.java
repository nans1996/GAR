/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceDao;

import entitys.User;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Vasilisa
 */
@Remote
public interface UserInterface {
    public List<User> getAll();
}
