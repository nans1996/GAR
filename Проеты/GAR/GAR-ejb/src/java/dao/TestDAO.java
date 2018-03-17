/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Vasilisa
 */
@Stateless
public class TestDAO implements Serializable, TestInterface {
    
    @Override
    public String TestVoid(){
        return "Тестовое сообщение!";
    }
}
