/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Collections;
import utilidades.ObligatorioException;

/**
 *
 * @author usuario
 */
public class Partida {
    //private double apuestaInicial;
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
        //setear turno
        //mezclar las fichas (metodo) 
        //para luego cuando entre el 
        //segundo jugador repartir
        
        pozoApuestas=100;//inicial
        //verificar que jugador tenga para apostar
        jugadores.add(jugador1);
        turno=jugador1;
        mezclarFichas();
    }
    
    public void iniciarPartida(Jugador jugador2){
        //Se ingresa el jugdor 
        //Se reparten las fichas(metodo)
        //hay que verificar que este logueado?
        
        jugadores.add(jugador2);
        repartirFichas();
    }
     
    //Ver que en el UML pusimos que era usuario el
    //parametro y es jugador.
    //Podemos hacer que el movimiento inicial en el cual 
    //no hay fichas en el tablero se ingrese 
    //fichaTablero null
    public boolean mover(Jugador jugador, Ficha fichaTablero, Ficha fichaDescartada)throws ObligatorioException{
        //verifico turno (metodo privado se va a reusar 
        //para ingreso apuesta)
        //unir ficha (metodo privado)
        //Cambiar el turno (metodo)
        //registrar movimiento
        
        
        //falta registrar movimiento
        verificarTurno(jugador);//tira excepcion        
        unirFicha(fichaTablero, fichaDescartada);//tira excepcion
        cambiarTurno();
        return true;
    }
    
    //El metodo robar tendria que ser void
    //ya que el sistema vuelve a mostrar las fichas
    public void robar(Jugador j)throws ObligatorioException{
        //Verifico el turno
        //Agrego la primera ficha
        //del array libres a el 
        //array de fichas del jugador
        //No cambiar turno
        //Verifico fin de partida
        
        verificarTurno(j);
        //ver que pasa cuando no hay 
        //mas fichas para robar
        j.agregarFicha(libres.get(0));
        libres.remove(0);
        verificarFinalPartida();
        
    }
    public boolean apostar(Jugador apostador, double monto){
        //Verifico que sea el turno
        //Verifico que no haya sido el ultimo en apostar
        //Verifico que el monto sea correcto
        //tanto para el que apuesta como para el rival
        //Verificar que el rival acepte
        return true;
    }
      
    //Se podria hacer con la ficha a descartar e indicar izquierda 
    //o derecha es mas facil no se si es lo mejor
    private void unirFicha(Ficha fichaTablero, Ficha fichaDescartada)throws ObligatorioException{
        //Todas las verificaciones lanzan exceptions
        //Hay que chequear que la ficha tablero sea de la punta.
        //Hay que chequear cual de las dos puntas del 
        //tablero es la ficha para saber que lado esta libre.
        //Verificar si se puede unir 
        //unir
        //Verifico fin de partida
    }
  
    private void repartirFichas(){
        //se reparten 7 para cada uno
    }
    
    private void mezclarFichas(){
        Collections.shuffle(libres);
    }
    
    private void cambiarTurno(){
        for(Jugador j : jugadores){
            if(j!=turno)
                turno=j;
        }
    }
    
    private void finalizarPartida(Jugador ganador){
        //da fin a la partida indicando el gandor        
    }
    
    private void verificarTurno(Jugador jugador)throws ObligatorioException{
        //ver si sirve que compare direccion de memoria
        if(jugador!=turno)
            throw new ObligatorioException("No es su turno.");
    }
    
    
    private void verificarFinalPartida()throws ObligatorioException{
        //verificar que el jugador no tenga mas fichas
        //verificar que el jugador las tenga todas
    }
    
    //Le cambie el nombre a verificarApuesta asi
    //el que verifica el saldo es el propio jugador.
    private void verificarApuesta(double monto)throws ObligatorioException{       
        for(Jugador j : jugadores){
            j.verificarSaldo(monto);
        }
    }
    
}
