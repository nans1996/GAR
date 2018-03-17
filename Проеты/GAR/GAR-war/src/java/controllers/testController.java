/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.TestInterface;
import javax.ejb.EJB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Vasilisa
 */
@Controller 
@RequestMapping("/Test")
public class testController {
   
    @Autowired
    TestInterface testInterface;
    
    //тестовая шляпа чтобы было понятно взимодействие
    @RequestMapping(method = RequestMethod.GET,value = "/test")
    public String userHello(ModelMap model) {
        // в модель отпровляем данные
        model.addAttribute("message", testInterface.TestVoid());
        return "test";
    }
}
