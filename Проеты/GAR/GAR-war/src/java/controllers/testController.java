/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
    
    //тестовая шляпа чтобы было понятно взимодействие
    @RequestMapping(method = RequestMethod.GET,value = "/test")
    public String userHello(ModelMap model) {
        // не появляются компоненты уровня предприятия
        model.addAttribute("message", "Тестовое сообщение!");
        return "test";
    }
}
