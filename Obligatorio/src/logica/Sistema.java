/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Observable;
import utilidades.ObligatorioException;

/**
 *
 * @author usuario
 */
public class Sistema extends utilidades.Observable{
    private SubSistemaUsuario ssu = new SubSistemaUsuario();
    private SubSistemaPartida ssp = new SubSistemaPartida();
    private static Sistema instancia = new Sistema();
    
    private Sistema(){};

    public static Sistema getInstancia() {
        return instancia;
    }

    public Jugador loginJugador(String nombre, String pass) throws ObligatorioException {
        return ssu.loginJugador(nombre, pass);
    }

    public Administrador loginAdministrador(String nombre, String pass) throws ObligatorioException {
        return ssu.loginAdministrador(nombre, pass);
    }

    public void partidasFinalizadas() throws ObligatorioException {
        ssp.partidasFinalizadas();
    }

    public ArrayList<Partida> getPartidas() {
        return ssp.getPartidas();
    }

    public void agregarJugador(Jugador j) throws ObligatorioException {
        ssp.agregarJugador(j);
    }

    public Partida getPartidaParaJugar() {
        return ssp.partidaParaJugar();
    }
 
    public enum Eventos{
        fichaDescartada, //Lo utilizaremos cada vez que se agrega una ficha al Tablero
        partidaFinalizada, //Lo urilizaremos cada vez que una partida se finaliza
        ingresoJugador; //Lo utilizaremos cada vez que ingresa un segudno jugador a una partida que estaba esperando
        
    }
}
