/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Jugador jugador;
    
    public ControladorJuego(VistaJuego vista, Jugador j){
        //modelo.agregar(this);
        this.vista = vista;       
        jugador=j;
        partida = modelo.getPartidaParaJugar();
        partida.agregar(this);
    }
    //tuve que sacarlo del constructor sino los paneles me 
    //quedaban con el controlador en null, no se terminaba de crear el
    // contructor del controlador y largaba la actualizacion por agregar el jugador
    public void agregarJugador(){
        try {
            modelo.agregarJugador(jugador);//aca realiza la carga de paneles por que actualiza
        } catch (ObligatorioException ex) {
            vista.mensaje(ex.getMessage());
        }
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
        //Se podria tener un evento solo ya que 
        //hacen casi todos lo mismo
        
        if(evento.equals(Partida.Eventos.ingresoJugador)){           
            vista.actualizarPaneles(partida, jugador);
        }
        if(evento.equals(Partida.Eventos.roboFicha)){
            vista.actualizarPaneles(partida, jugador);
        }
        if(evento.equals(Partida.Eventos.apuesta)){
            if(partida.getUltimaApuesta().getJugador()==jugador){
                vista.mensaje("Esperando confirmacion.");
            }else{
                vista.confirmarApuesta("Acepta la apuesta: "+ partida.getUltimaApuesta().getValor());
            }
        }
        if(evento.equals(Partida.Eventos.confirmacionApuesta)){
            vista.actualizarPaneles(partida, jugador);
        }
        if(evento.equals(Partida.Eventos.realizoMovimiento)){
            vista.actualizarPaneles(partida, jugador);
        }
    }
    
    public void salir() {
        vista.cerrar();
    }

    public void robar() {
        try {
            partida.robar(jugador);
        } catch (ObligatorioException ex) {
            vista.mensaje(ex.getMessage());
        }
    }

    public void apostar(double parseDouble) {
        try {
            partida.apostar(jugador, parseDouble);
        } catch (ObligatorioException ex) {
            vista.mensaje(ex.getMessage());
        }
    }

    public void confirmarApuesta(boolean b) {
        try {
            partida.confirmarApuesta(b);
        } catch (ObligatorioException ex) {
            vista.mensaje(ex.getMessage());
        }
    }

    public void descartar() {
        try {
            partida.mover(jugador, destino, origen);
        } catch (ObligatorioException ex) {
            vista.mensaje(ex.getMessage());        }
        }   
}
