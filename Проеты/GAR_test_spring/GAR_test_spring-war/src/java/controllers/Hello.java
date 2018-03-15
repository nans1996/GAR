/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.ejb.EJB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Vasilisa
 */


@Controller 
@RequestMapping("/Hello")
public class Hello  {
    
    @EJB
    UserInte userDAORemote;
    @RequestMapping(method = RequestMethod.GET,value = "/test")
    public String test(ModelMap model) {
        
        model.addAttribute("message", "Hello World!");
        return "test";
    }

}
