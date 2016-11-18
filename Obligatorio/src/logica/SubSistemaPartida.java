/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import mapeadores.MapeadorPartida;
import persistencia.Persistencia;
import utilidades.ObligatorioException;

/**
 *
 * @author usuario
 */
public class SubSistemaPartida {
    private ArrayList<Partida> partidas = new ArrayList();
    private Partida proximaPartida;
    private Persistencia persistencia = new Persistencia();
    private MapeadorPartida partidaMapper = new MapeadorPartida();

    public SubSistemaPartida() {
        proximaPartida = new Partida();
        cargarPartidas();
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
    
    private void cargarPartidas(){
        ArrayList<Object> listaPartidas = persistencia.obtenerTodos(partidaMapper);
        for(Object o: listaPartidas){
            Partida p = (Partida)o;
            partidas.add(p);
        }
    }
}
    
    
