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

    //Al crear la partida, se ingresa el primer jugador y de realizan las validaciones correspondientes.
    public Partida(Jugador jugador1) throws ObligatorioException{
        //iniciar la apuesta
        //ingresar el primer jugador
        //setear turno
        //mezclar las fichas (metodo)
        //para luego cuando entre el 
        //segundo jugador repartir
        
        pozoApuestas=100;//inicial
        jugadores.add(jugador1);
        verificarApuesta(pozoApuestas);
        //Ver que pasa acá... Si salta la excepción, queda una partida creada con un jugador, lo que nos deja la lista de partidas "sucia"
        turno=jugador1;
        mezclarFichas();
    }
    
    
    public void iniciarPartida(Jugador jugador2) throws ObligatorioException{
        //Se ingresa el jugdor 
        //Se reparten las fichas(metodo)
        //hay que verificar que este logueado?
        
        jugadores.add(jugador2);
        verificarApuesta(pozoApuestas);
        //Ver que pasa acá... Si salta la excepción, la partida va a quedar con el jugador2 agregado... Eso no está bien. Creo que deberíamos controlarlo antes...
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
        Ficha primera = tablero.get(0);
        Ficha ultima = tablero.get(tablero.size()-1);
        
        if(fichaTablero.equals(primera)){
            if(primera.getValorIzquierda() == fichaDescartada.getValorDerecha()){
                tablero.add(0,fichaDescartada);
            }
            else if(tablero.get(0).getValorIzquierda() == fichaDescartada.getValorIzquierda()){
                fichaDescartada.rotar();
                tablero.add(0,fichaDescartada);
            }
            verificarFinalPartida();
        }
        else if(fichaTablero.equals(ultima)){
            if(ultima.getValorDerecha() == fichaDescartada.getValorIzquierda()){
                tablero.add(fichaDescartada);
            }
            else if(ultima.getValorDerecha() == fichaDescartada.getValorDerecha()){
                fichaDescartada.rotar();
                tablero.add(fichaDescartada);
            }
            verificarFinalPartida();
        }else{
            throw new ObligatorioException("Las fichas seleccionadas no se pueden unir.");
        }
    }
  
    private void repartirFichas(){
        //se reparten 7 para cada uno
        Jugador j1 = jugadores.get(0);
        Jugador j2 = jugadores.get(1);
        
        for(int i=1; i<=7; i++){
            Ficha f = libres.get(0);
            j1.agregarFicha(f);
            libres.remove(f);
        }
        
        for(int i=1; i<=7; i++){
            Ficha f = libres.get(0);
            j2.agregarFicha(f);
            libres.remove(f);
        }
    }
    
    private void mezclarFichas(){
        Collections.shuffle(libres);
    }
    
    private void cambiarTurno(){
        for(Jugador j : jugadores){
            if(j!=turno){
                turno=j;
                return;
            } 
        }
    }
    
    private void finalizarPartida(Jugador ganador){
        //da fin a la partida indicando el gandor
    }
    
    private void verificarTurno(Jugador jugador)throws ObligatorioException{
        //ver si sirve que compare direccion de memoria
        if(jugador != turno)
            throw new ObligatorioException("No es su turno.");
    }
    
    
    private void verificarFinalPartida()throws ObligatorioException{
        for(Jugador j:jugadores){
            if(j.getFichas().isEmpty())
                finalizarPartida(j);
        }
        //verificar que el jugador las tenga todas - Esto no va acá... Esto nay q verificarlo cuando un jugador roba... Si no tiene movimientos posibles para hacer y fueron todas robadas...
    }
    
    //Le cambie el nombre a verificarApuesta asi
    //el que verifica el saldo es el propio jugador.
    private void verificarApuesta(double monto) throws ObligatorioException{
        for(Jugador j : jugadores){
            j.verificarSaldo(monto);
        }
    }

    public boolean enEspera() {
        if(jugadores.size() < 2)
            return true;
        
        return false;
    }
    
}
