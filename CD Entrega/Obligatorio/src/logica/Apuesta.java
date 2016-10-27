/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author usuario
 */
public class Apuesta {
    private double valor;
    private Jugador jugador;

    public double getValor() {
        return valor;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    public Apuesta(double valor){
        this.valor = valor;
    }
}
