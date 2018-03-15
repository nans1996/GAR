/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classJSF;

import entity.UserRole;
import entity.UserRolePK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import seans.UserRoleFacade;

/**
 *
 * @author Vasilisa
 */
public class UserRoleConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        UserRolePK id = getId(string);
        UserRoleController controller = (UserRoleController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "userRole");
        return controller.getJpaController().find(id);
    }

    UserRolePK getId(String string) {
        UserRolePK id = new UserRolePK();
        String[] params = new String[2];
        int p = 0;
        int grabStart = 0;
        String delim = "#";
        String escape = "~";
        Pattern pattern = Pattern.compile(escape + "*" + delim);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String found = matcher.group();
            if (found.length() % 2 == 1) {
                params[p] = string.substring(grabStart, matcher.start());
                p++;
                grabStart = matcher.end();
            }
        }
        if (p != params.length - 1) {
            throw new IllegalArgumentException("string " + string + " is not in expected format. expected 2 ids delimited by " + delim);
        }
        params[p] = string.substring(grabStart);
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].replace(escape + delim, delim);
            params[i] = params[i].replace(escape + escape, escape);
        }
        id.setRole(params[0]);
        id.setLogin(params[1]);
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof UserRole) {
            UserRole o = (UserRole) object;
            UserRolePK id = o.getUserRolePK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String role = id.getRole();
            role = role == null ? "" : role.replace(escape, escape + escape);
            role = role.replace(delim, escape + delim);
            String login = id.getLogin();
            login = login == null ? "" : login.replace(escape, escape + escape);
            login = login.replace(delim, escape + delim);
            return role + delim + login;
            // TODO: no setter methods were found in your primary key class
            //    entity.UserRolePK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entity.UserRole");
        }
    }
    
}
