/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author usuario
 */
public class Partida {
    private double apuestaInicial;
    private double pozoApuestas;
    private ArrayList<Jugador> jugadores = new ArrayList();
    private Jugador turno;
    private Jugador ganador;
    private ArrayList<Movimiento> jugadas = new ArrayList();
    private Apuesta ultimaApuesta;
    private ArrayList<Ficha> libres = new ArrayList();
    private ArrayList<Ficha> tablero = new ArrayList();

    public Jugador getGanador() {
        return ganador;
    }

    public double getPozoApuestas() {
        return pozoApuestas;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Apuesta getUltimaApuesta() {
        return ultimaApuesta;
    }

    public Partida(Jugador jugador1) {
        //iniciar la apuesta 
        //ingresar el primer jugador
        //mezclar las fichas para luego cuando entre el 
        //segundo jugador repartir
    }
    
    private void mezclarFichas(){
        Collections.shuffle(libres);
    }
    //Le cambie el nombre a verificarApuesta asi
    //el que verifica el saldo es el propio jugador.
    //Ver que al ser una verificacion se puede hacer con
    //exception.
    //Se podria tambien identificar que jugador es el que 
    //tiene saldo insuficiente.
    private boolean verificarApuesta(double monto){
        boolean retorno=true;
        for(Jugador j : jugadores){
            if(!j.verificarSaldo(monto))
                retorno=false;
        }
        return retorno;
    }
    //Ver que en el UML pusimos que era usuario el
    //parametro y es jugador.
    public boolean mover(Jugador jugador, Ficha fichaTablero, Ficha fichaDescartada){
        //verifico turno (metodo privado se va a reusar 
        //para ingreso apuesta)
        //unir ficha (metodo privado)
        //Cambiar el turno
        return true;
    }
    private boolean verificarTurno(Jugador jugador){
        //ver si sirve que compare direccion de memoria
        return jugador==turno;
    }
    private boolean unirFicha(Ficha fichaTablero, Ficha fichaDescartada){
        //Hay que chequear que la ficha tablero sea de la punta.
        //Hay que chequear cual de las dos puntas del 
        //tablero es la ficha para saber que lado esta libre.
        //Verificar si se puede unir 
        //unir
      
        return true;
    }
    private void cambiarTurno(){
        for(Jugador j : jugadores){
            if(j!=turno)
                turno=j;
        }
    }
    
}
