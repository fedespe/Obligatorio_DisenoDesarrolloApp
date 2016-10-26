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
            vista.mensajeModal(ex.getMessage());
            modelo.logoutJugador(jugador);
            partida.quitar(this);
            jugador=null;
            //vista.cerrar(); no se puede por que tidavia no esta creada la vista
            //por eso se crea un evento despues que la ventana esta creada
        }
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
        
        if(evento.equals(Partida.Eventos.ingresoJugador)
                || evento.equals(Partida.Eventos.roboFicha)
                || evento.equals(Partida.Eventos.confirmacionApuesta)
                || evento.equals(Partida.Eventos.realizoMovimiento)){           
            vista.actualizarPaneles(partida, jugador);
        }
        else if(evento.equals(Partida.Eventos.apuesta)){
            if(partida.getUltimaApuesta().getJugador()==jugador){
                vista.mensaje("Se envió la solicitud de apuesta.");
            }else{
                vista.confirmarApuesta("Acepta la apuesta de: $"+ partida.getUltimaApuesta().getValor() + "?");
            }
        }
        else if(evento.equals(Partida.Eventos.partidaFinalizada)){
            vista.actualizarPaneles(partida, jugador); //Actualizo para que muestre bien el saldo del jugador cuando termina la partida
            vista.mostrarGanador(partida.getGanador());
        }
    }
    
    public void salir() {
        partida.jugadorAbandonando(jugador);
        modelo.logoutJugador(jugador);
        partida.quitar(this);
        vista.cerrar();
    }

    public void robar() {
        try {
            partida.robar(jugador);
        } catch (ObligatorioException ex) {
            vista.mensaje(ex.getMessage());
        }
    }

    public void apostar(double monto) {
        try {
            partida.apostar(jugador, monto);
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
            origen = null;
            destino = null;
        }
        catch (ObligatorioException ex) {
            vista.mensaje(ex.getMessage());
        }
    }   

    public void verificarJugadorAgregado() {
        if(jugador==null)
            vista.cerrar();
    }
}
