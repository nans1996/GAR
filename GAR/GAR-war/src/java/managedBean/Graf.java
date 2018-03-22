/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author Vasilisa
 */
@ManagedBean(name = "graf")
@RequestScoped
public class Graf {

    //загрушки пока что
    
    //по каждой привычке
    public int[] getUsual(){
    //массив выполненной цели ... индекс 1 из 21 дня ...значение-выполнение    
    int[] habitArray = {1, 2, 2, 3, 3, 3, 4, 5, 6};
    return habitArray;
    }
            
    
}
