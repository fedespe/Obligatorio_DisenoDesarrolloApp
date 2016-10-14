/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import logica.Ficha;
import utilidades.Observable;
import utilidades.Observador;

/**
 *
 * @author usuario
 */
public class ControladorJuego implements Observador{
    private Ficha origen;
    private Ficha destino;
    
    public void tirar(){
        System.out.println("TIRO");
        System.out.println("Origen: " + origen);
        System.out.println("Destino: " + destino);
    }

    public void setOrigen(Ficha ficha) {
        origen = ficha;
    }

    public void setDestino(Ficha ficha) {
        destino = ficha;
    }
    
    @Override
    public void actualizar(Observable origen, Object evento) {
        
    }
    
}
