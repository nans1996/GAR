/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitys.Personage;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasilisa
 */
@Local
public interface PersonageFacadeLocal {

    void create(Personage personage);

    void edit(Personage personage);

    void remove(Personage personage);

    Personage find(Object id);

    List<Personage> findAll();

    List<Personage> findRange(int[] range);

    int count();
    
}
