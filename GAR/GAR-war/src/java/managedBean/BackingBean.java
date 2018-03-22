/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

//import entitys.User;
//import dao.UserFacadeLocal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import org.apache.myfaces.trinidad.component.UIXTable;

/**
 *
 * @author Vasilisa
 */
public class BackingBean {
    //@EJB
    //UserFacadeLocal userFacadeLocal;
    //private User user;

    protected UIXTable table;

    /**
     * Creates a new instance of BackingBean
     */
    public BackingBean() {
    }

    public void setTable(UIXTable table) {
        this.table = table;
    }

    public UIXTable getTable() {
        return table;
    }

    public void delete() {

        UIXTable tabletmp = getTable();
        // получение списка выбранных строк
        Iterator selection = tabletmp.getSelectedRowKeys().iterator();
        // запоминание текущей строки таблицы
        Object oldKey = tabletmp.getRowKey();
        // цикл по списку выбранных строк
        while (selection.hasNext()) {
            Object rowKey = selection.next();
            // установка текущей строки таблицы
            tabletmp.setRowKey(rowKey);
            // получение данных из текущей строки
        //    User row = (User) tabletmp.getRowData();
            // удаление клиента 
            //тут раскментировать как напишем метод
           // userInterface.delete(row.getID_user());
        }
        // восстановление запомненной текущей строки
        tabletmp.setRowKey(oldKey);
    }

    //public List<User> getAll() {
      //  return userFacadeLocal.findAll();
   //   return null;
   // }
}
