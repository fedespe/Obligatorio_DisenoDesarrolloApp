/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class Movimiento {
    private Jugador ganador; 
    private Date fechaHora = new Date();
    private double pozoApuestas;
    private Jugador jugador;
    private ArrayList<Ficha> tablero; 

    public Jugador getGanador() {
        return ganador;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public double getPozoApuestas() {
        return pozoApuestas;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public ArrayList<Ficha> getTablero() {
        return tablero;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    public Movimiento(Jugador ganador, double pozoApuestas, Jugador jugador, ArrayList<Ficha> tablero) {
        this.ganador = ganador;
        this.pozoApuestas = pozoApuestas;
        this.jugador = jugador;
        this.tablero = tablero;
    }
    
    
}
