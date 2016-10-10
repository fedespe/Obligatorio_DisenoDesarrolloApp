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

    public ArrayList<Ficha> getTablero() {
        return tablero;
    }

    
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

    //Se crea la partida y luego se agregan los jugadores
    //La partida se agrega al sistema si tiene jugadores ingresados
    public Partida(){
        //iniciar la apuesta
        //mezclar las fichas (metodo) 
        pozoApuestas=100;//inicial  
        ultimaApuesta= new Apuesta();
        crearFichas();
        //No mezclo para hacer pruebas
        //Esta mezclando bien
        //mezclarFichas();
    }
    public void agregarJugador(Jugador jugador) throws ObligatorioException{
        if(jugadores.isEmpty()){
            jugador.verificarSaldo(pozoApuestas);
            jugadores.add(jugador);
            turno=jugador;
        }else if(jugadores.size()==1){
            jugador.verificarSaldo(pozoApuestas);
            jugadores.add(jugador);
            repartirFichas();
        }
    }
    
    //Podemos hacer que el movimiento inicial en el cual 
    //no hay fichas en el tablero se ingrese 
    //fichaTablero null
    public boolean mover(Jugador jugador, Ficha fichaTablero, Ficha fichaDescartada)throws ObligatorioException{
        //falta registrar movimiento
        verificarTurno(jugador);//tira excepcion 
        unirFicha(fichaTablero, fichaDescartada);//tira excepcion
        //Finaliza la partida si se descarto todas
        verificarSiSeDescartoTodas();
        cambiarTurno();
        return true;
    }
    
    //El metodo robar tendria que ser void
    //ya que el sistema vuelve a mostrar las fichas
    public void robar(Jugador j)throws ObligatorioException{
        verificarTurno(j);
        //ver que pasa cuando no hay 
        //mas fichas para robar
        if(!libres.isEmpty()){
            j.agregarFicha(libres.get(0));
            libres.remove(0);
        }else if(libres.size()==1){
            //Finaliza la partida si no se puede colocar
            verificarSiNoSePuedeColocar(libres.get(0));
        }       
    }
    //Ver que el pozoApuesta creo tiene que tener la suma
    //de los dos si se apuesta 500 creo tiene que ser 1000
    public void apostar(Jugador apostador, double monto)throws ObligatorioException{
        //Verifico que no haya sido el ultimo en apostar
        //Verifico que el monto sea correcto
        //tanto para el que apuesta como para el rival
        //Ingreso la apuesta ya que si no la acepta termina el juego
        //Verificar que el rival acepte
        //esto lo hace lanzando una notificacion al observador
        verificarUltimoEnApostar(apostador);
        verificarApuesta(monto);
        ultimaApuesta.setJugador(apostador);
        ultimaApuesta.setValor(monto);
    }
    public void confirmarApuesta(boolean confirmacion){
        //si acepta incremento el pozo
        //si no acepta finaliza partida
        if(confirmacion){
            pozoApuestas+=ultimaApuesta.getValor();
        }else{
            finalizarPartida(ultimaApuesta.getJugador());
        }
    }
      
    //Se podria hacer con la ficha a descartar e indicar izquierda 
    //o derecha es mas facil no se si es lo mejor
    private void unirFicha(Ficha fichaTablero, Ficha fichaDescartada)throws ObligatorioException{
        if(tablero.isEmpty()){
            tablero.add(0,fichaDescartada);
            turno.getFichas().remove(fichaDescartada);
        }else{
            //Todas las verificaciones lanzan exceptions
            Ficha primera = tablero.get(0);
            Ficha ultima = tablero.get(tablero.size()-1);

            if(fichaTablero.equals(primera)){
                if(primera.getValorIzquierda() == fichaDescartada.getValorDerecha()){
                    tablero.add(0,fichaDescartada);
                    turno.getFichas().remove(fichaDescartada);
                }
                else if(tablero.get(0).getValorIzquierda() == fichaDescartada.getValorIzquierda()){
                    fichaDescartada.rotar();
                    tablero.add(0,fichaDescartada);
                    turno.getFichas().remove(fichaDescartada);
                }
                else{
                    throw new ObligatorioException("Las fichas seleccionadas no se pueden unir.");
                }
                //Lo escribi afuera asi no lo escribimos 2 veces
                //verificarSiSeDescartoTodas();
            }
            else if(fichaTablero.equals(ultima)){
                if(ultima.getValorDerecha() == fichaDescartada.getValorIzquierda()){
                    tablero.add(fichaDescartada);
                    turno.getFichas().remove(fichaDescartada);
                }
                else if(ultima.getValorDerecha() == fichaDescartada.getValorDerecha()){
                    fichaDescartada.rotar();
                    tablero.add(fichaDescartada);
                    turno.getFichas().remove(fichaDescartada);
                }else{
                    throw new ObligatorioException("Las fichas seleccionadas no se pueden unir.");
                }
                //verificarSiSeDescartoTodas();
            }else{
                throw new ObligatorioException("Las fichas seleccionadas no se pueden unir.");
            }
        }
    }
  
    //Metodo para reuso en unir ficha codigo repetido
    //No lo cambie todavia
    private void agregarFichaAlTableroYRemoverDelJugador(Ficha ficha){
        tablero.add(0,ficha);
        turno.getFichas().remove(ficha);
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
        //lanzar evento de fin de partida
        this.ganador=ganador;
    }
    
    private void verificarTurno(Jugador jugador)throws ObligatorioException{
        //ver si sirve que compare direccion de memoria
        if(jugador != turno)
            throw new ObligatorioException("No es su turno.");
    }
    
    
    private void verificarSiSeDescartoTodas()throws ObligatorioException{
        for(Jugador j:jugadores){
            if(j.getFichas().isEmpty())
                finalizarPartida(j);
        }       
    }
    private void verificarSiNoSePuedeColocar(Ficha ficha)throws ObligatorioException{
        Ficha primera = tablero.get(0);
        Ficha ultima = tablero.get(tablero.size()-1);
        if(ficha.getValorIzquierda()!=primera.getValorIzquierda()
        && ficha.getValorDerecha()!=primera.getValorIzquierda()
        && ficha.getValorIzquierda() !=ultima.getValorDerecha()
        && ficha.getValorDerecha() !=ultima.getValorDerecha()){
            //Cambio el jugador para dar el ganador 
            //asi no hago otro metodo
            cambiarTurno();
            finalizarPartida(turno); 
        }
    }
    
    //Le cambie el nombre a verificarApuesta asi
    //el que verifica el saldo es el propio jugador.
    private void verificarApuesta(double monto) throws ObligatorioException{
        for(Jugador j : jugadores){
            j.verificarSaldo(monto);
        }
    }
    private void verificarUltimoEnApostar(Jugador apostador)throws ObligatorioException{
        if(apostador==ultimaApuesta.getJugador())
            throw new ObligatorioException("No se puede realizar dos apuestas consecutivas.");
    }

    public boolean enEspera() {
        if(jugadores.size() < 2)
            return true;
        
        return false;
    }
    public void crearFichas(){
        libres.add(new Ficha(0,0));
        libres.add(new Ficha(0,1));
        libres.add(new Ficha(0,2));
        libres.add(new Ficha(0,3));
        libres.add(new Ficha(0,4));
        libres.add(new Ficha(0,5));
        libres.add(new Ficha(0,6));
        libres.add(new Ficha(1,1));
        libres.add(new Ficha(1,2));
        libres.add(new Ficha(1,3));
        libres.add(new Ficha(1,4));
        libres.add(new Ficha(1,5));
        libres.add(new Ficha(1,6));
        libres.add(new Ficha(2,2));
        libres.add(new Ficha(2,3));
        libres.add(new Ficha(2,4));
        libres.add(new Ficha(2,5));
        libres.add(new Ficha(2,6));
        libres.add(new Ficha(3,3));
        libres.add(new Ficha(3,4));
        libres.add(new Ficha(3,5));
        libres.add(new Ficha(3,6));
        libres.add(new Ficha(4,4));
        libres.add(new Ficha(4,5));
        libres.add(new Ficha(4,6));
        libres.add(new Ficha(5,5));
        libres.add(new Ficha(5,6));
        libres.add(new Ficha(6,6));
    }
    //La elimine ya que la partida inicia 
    //cuando se agrega el segundo jugador
//    public void iniciarPartida(Jugador jugador2) throws ObligatorioException{
//        //Se ingresa el jugdor 
//        //Se reparten las fichas(metodo)
//        //hay que verificar que este logueado?
//        
//        jugadores.add(jugador2);
//        verificarApuesta(pozoApuestas);
//        //Ver que pasa acá... Si salta la excepción, la partida va a quedar con el jugador2 agregado... Eso no está bien. Creo que deberíamos controlarlo antes...
//        repartirFichas();
//    }
}
