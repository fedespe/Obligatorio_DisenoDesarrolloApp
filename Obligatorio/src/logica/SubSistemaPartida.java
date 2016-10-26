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
    private Partida proximaPartida;

    public SubSistemaPartida() {
        //siempre dejo una partida iniciada para evitar conflicto
        //al enviar evento de actualizar al controlador
        proximaPartida = new Partida();
    }
    
    public Partida partidaParaJugar(){
        return proximaPartida;
    }
    
    public void partidasFinalizadas() throws ObligatorioException{
        for(Partida p:partidas){
            if(p.getGanador() == null)
                throw new ObligatorioException("No puede cerrar el servidor hasta que todas las partidas hayan finalizado.");
        }
    }

    public ArrayList<Partida> getPartidas() {
        return partidas;
    }
    
    public void agregarJugador(Jugador j) throws ObligatorioException{
        
        proximaPartida.agregarJugador(j);
        if(proximaPartida.getJugadores().size() == 2){
            partidas.add(proximaPartida);
            proximaPartida = new Partida();
            Sistema.getInstancia().avisar(Sistema.Eventos.actualizacionEnPartida);
        } 
    }
}
