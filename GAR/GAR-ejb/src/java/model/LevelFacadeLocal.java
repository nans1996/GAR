/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Level;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasilisa
 */
@Local
public interface LevelFacadeLocal {

    void create(Level level);

    void edit(Level level);

    void remove(Level level);

    Level find(Object id);

    List<Level> findAll();

    List<Level> findRange(int[] range);

    int count();
    
}
