/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Goal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasilisa
 */
@Local
public interface GoalFacadeLocal {

    void create(Goal goal);

    void edit(Goal goal);

    void remove(Goal goal);

    Goal find(Object id);

    List<Goal> findAll();

    List<Goal> findRange(int[] range);

    int count();
        
    public List<Goal> findGoalDefolt();
    
}
