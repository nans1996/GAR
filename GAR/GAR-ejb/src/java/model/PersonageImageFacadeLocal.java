/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.PersonageImage;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasilisa
 */
@Local
public interface PersonageImageFacadeLocal {

    void create(PersonageImage personageImage);

    void edit(PersonageImage personageImage);

    void remove(PersonageImage personageImage);

    PersonageImage find(Object id);

    List<PersonageImage> findAll();

    List<PersonageImage> findRange(int[] range);

    int count();
    
}
