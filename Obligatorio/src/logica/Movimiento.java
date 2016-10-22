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
    private Jugador ganador; //Consultamos siempre si no es null, y si no lo es, se muestra el ganador...
    private Date fechaHora = new Date(); //Fecha y Hora del último descarte
    private double pozoApuestas; //Total apostado hasta el momento
    private Jugador jugador; //Ultimo jugador que descartó (Confirmar si es esto lo que se quiere, o si se quiere ver quien es el proximo en jugar)
    private ArrayList<Ficha> tablero; //Tablero de fichas... Lo pasamos siempre por arriba en lugar de guardar la última ficha jugada e ir agregando...

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

    public Movimiento(Jugador ganador, double pozoApuestas, Jugador jugador, ArrayList<Ficha> tablero) {
        this.ganador = ganador;
        this.pozoApuestas = pozoApuestas;
        this.jugador = jugador;
        this.tablero = tablero;
    }
    
    
}
