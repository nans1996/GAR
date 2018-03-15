/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classJSF;

import entity.GoalUser;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Vasilisa
 */
public class GoalUserConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        GoalUserController controller = (GoalUserController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "goalUser");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof GoalUser) {
            GoalUser o = (GoalUser) object;
            return o.getIDGoaluser() == null ? "" : o.getIDGoaluser().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entity.GoalUser");
        }
    }
    
}
