/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.GoalUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasilisa
 */
@Local
public interface GoalUserFacadeLocal {

    void create(GoalUser goalUser);

    void edit(GoalUser goalUser);

    void remove(GoalUser goalUser);

    GoalUser find(Object id);

    List<GoalUser> findAll();

    List<GoalUser> findRange(int[] range);

    int count();
    
    public List<GoalUser> findGoalCurrentClient(Integer iDClient);
}
