/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import logica.Ficha;
import logica.Jugador;
import logica.Partida;
import logica.Sistema;
import utilidades.ObligatorioException;
import utilidades.Observable;
import utilidades.Observador;

/**
 *
 * @author usuario
 */
public class ControladorJuego implements Observador{
    private Sistema modelo = Sistema.getInstancia();
    private Ficha origen;
    private Ficha destino;
    private VistaJuego vista;
    private Partida partida;
    
    public ControladorJuego(VistaJuego vista, Jugador j) throws ObligatorioException{
        this.vista = vista;
        partida = modelo.agregarJugador(j);
        vista.cargarPaneles(partida.getTablero(), j.getFichas());
    }
    
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
    
    public void salir() {
        vista.cerrar();
    }
}
