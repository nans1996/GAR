/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localInterface;
import entity.User;
import java.util.List;
import javax.ejb.Local;
/**
 *
 * @author Vasilisa
 */
@Local
public interface UserInterface {
    public List<User> findUserEntities();
}
