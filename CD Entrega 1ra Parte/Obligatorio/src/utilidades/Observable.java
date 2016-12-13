/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.ArrayList;

/**
 *
 * @author docenteFI
 */
public class Observable {
    
    private ArrayList<Observador> observadores = new ArrayList();
    
    public void agregar(Observador obs){
        if(!observadores.contains(obs)){
            observadores.add(obs);
        }
    }
    public void quitar(Observador obs){
        observadores.remove(obs);
    }
    public void avisar(Object evento){
        for(Observador obs:observadores){
            obs.actualizar(this, evento);
        }
    }
}
