/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import utilidades.ObligatorioException;

/**
 *
 * @author usuario
 */
public class SubSistemaPartida {
    private ArrayList<Partida> partidas = new ArrayList();
    
    public void partidasFinalizadas() throws ObligatorioException{
        
        if(!partidas.isEmpty()){ //Ver si es necesario el if... Si está vacía la lista, da excepción o no hace nada?
            for(Partida p:partidas){
                if(p.getGanador() == null)
                    throw new ObligatorioException("No puede cerrar el servidor hasta que todas las partidas hayan finalizado.");
            }
        }
    }
    
    public Partida partidaPosision(int pos){
        return partidas.get(pos);
    }

    public ArrayList<Partida> getPartidas() {
        return partidas;
    }
    
    public Partida agregarJugador(Jugador j) throws ObligatorioException{
        Partida ultimaPartida = null;
                
        if(!partidas.isEmpty())
             ultimaPartida = partidas.get(partidas.size() -1);
        
        if(ultimaPartida != null && ultimaPartida.enEspera()){
            ultimaPartida.agregarJugador(j);
            return ultimaPartida;
        }
        else{
            Partida nueva= new Partida();
            nueva.agregarJugador(j);
            partidas.add(nueva);
            return nueva;
        }
    }
}
