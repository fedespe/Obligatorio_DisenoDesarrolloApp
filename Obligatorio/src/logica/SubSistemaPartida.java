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

    public SubSistemaPartida() {
        //siempre dejo una partida iniciada para evitar conflicto
        //al enviar evento de actualizar al controlador
        partidas.add(new Partida());
    }
    
    public Partida partidaParaJugar(){
        return partidas.get(partidas.size()-1);
    }
    
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
    
    public void agregarJugador(Jugador j) throws ObligatorioException{
        Partida p= partidaParaJugar();
        if(p.getJugadores().size()==1){//ver que esto podria ser un metodo en partida
            p.agregarJugador(j);
            partidas.add(new Partida());
        }else{//si no tiene jugadores
            p.agregarJugador(j);
        }       
    }
}
