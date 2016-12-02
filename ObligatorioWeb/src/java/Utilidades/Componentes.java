/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Componentes {
    public static String select(ArrayList<String> opciones,String id){
        String select = "<select multiple id='" + id + "'>";
        int i = 0;
        for(String ob:opciones){
            select +="<option value='" + i + "'>"  + ob  +"</option>";
            i++;
        }
        select +="</select>";
        return select;
    }
}