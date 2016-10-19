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
public class Partida extends utilidades.Observable{
    //private double apuestaInicial;
    private boolean partidaActiva=false;
    private double pozoApuestas=0;
    private ArrayList<Jugador> jugadores = new ArrayList();
    private Jugador turno;
    private Jugador ganador;
    private ArrayList<Movimiento> jugadas = new ArrayList();
    private Apuesta ultimaApuesta;
    private ArrayList<Ficha> libres = new ArrayList();
    private ArrayList<Ficha> tablero = new ArrayList();
    private Ficha primera;
    private Ficha ultima;

    public void jugadorAbandonando(Jugador jugador) {
        if(jugadores.size() ==2){
            Jugador gana = jugador;
            boolean encontrado = false;

            if(ganador == null){
                for(Jugador j:jugadores){
                    if(!encontrado && gana != j){
                        gana = j;
                        encontrado = true;
                    }
                }
                finalizarPartida(gana);
            }
        }
        else{
            jugadores.clear();
        }
    }
    
    public enum Eventos{
        fichaDescartada, 
        partidaFinalizada, 
        ingresoJugador, 
        roboFicha, 
        apuesta, 
        confirmacionApuesta,
        realizoMovimiento;     
    }

    public ArrayList<Ficha> getLibres() {
        return libres;
    }

    public Jugador getTurno() {
        return turno;
    }

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
        ultimaApuesta= new Apuesta(100);
        crearFichas();
        //No mezclo para hacer pruebas
        mezclarFichas();
    }
    public void agregarJugador(Jugador jugador) throws ObligatorioException{
        if(jugadores.isEmpty()){
            jugador.verificarSaldo(ultimaApuesta.getValor());
            jugadores.add(jugador);
            //Hay que asignarle esta partida al jugador
            turno=jugador;
        }else if(jugadores.size()==1){
            jugador.verificarSaldo(ultimaApuesta.getValor());
            jugadores.add(jugador);
            restarMontoJugadoresSumarApuestaEnPartida();
            repartirFichas();
            partidaActiva=true;            
        }
        avisar(Eventos.ingresoJugador);
    }

    //Podemos hacer que el movimiento inicial en el cual 
    //no hay fichas en el tablero se ingrese 
    //fichaTablero null
    public void mover(Jugador jugador, Ficha fichaTablero, Ficha fichaDescartada)throws ObligatorioException{
        controlesAntesJugar(jugador);
        //falta registrar movimiento
        unirFicha(fichaTablero, fichaDescartada);//tira excepcion
        //Finaliza la partida si se descarto todas
        verificarSiSeDescartoTodas();
        cambiarTurno();
        avisar(Eventos.realizoMovimiento);
        Sistema.getInstancia().avisar(Sistema.Eventos.actualizacionEnPartida);
        if(libres.isEmpty()){
            if(verificarSiTieneMovimientos(turno)){
                cambiarTurno();
                finalizarPartida(turno);
            }
        }
    }
    
    public void primerJugada(Jugador jugador, Ficha fichaDescartada)throws ObligatorioException{
        controlesAntesJugar(jugador);
        tablero.add(fichaDescartada);
        ultima = fichaDescartada;
        primera = fichaDescartada;
        cambiarTurno();
    }
    
    private void controlesAntesJugar(Jugador jugador) throws ObligatorioException{
        sePuedeJugar();
        verificarTurno(jugador);//tira excepcion
    }
    
    //El metodo robar tendria que ser void
    //ya que el sistema vuelve a mostrar las fichas
    public void robar(Jugador j)throws ObligatorioException{
        sePuedeJugar();
        verificarTurno(j);
        if(verificarSiTieneMovimientos(j))
            throw new ObligatorioException("Usted tiene fichas que se pueden jugar, no puede robar del pozo.");
        if(!libres.isEmpty()){
            j.agregarFicha(libres.get(0));
            libres.remove(0);
            avisar(Eventos.roboFicha);
            Sistema.getInstancia().avisar(Sistema.Eventos.actualizacionEnPartida);
        }
        else{
            throw new ObligatorioException("No quedan más fichas en el pozo.");
        }
        
        //Esto no es un else del if de arriba, porque tiene que hacer el control después de haber eliminado la ficha que se robó.
        if(libres.isEmpty()){
            //Finaliza la partida si no se puede colocar
            if(!verificarSiTieneMovimientos(j)){
                //Cambio el jugador para dar el ganador 
                //asi no hago otro metodo
                cambiarTurno();
                finalizarPartida(turno); 
            } 
        }
        //ver si hay que evaluar el caso en que no hay mas fichas       
    }
    //Ver que el pozoApuesta creo tiene que tener la suma
    //de los dos si se apuesta 500 creo tiene que ser 1000
    public void apostar(Jugador apostador, double monto)throws ObligatorioException{
        sePuedeJugar();
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
        partidaActiva=false;
        avisar(Eventos.apuesta);
    }
    public void confirmarApuesta(boolean confirmacion)throws ObligatorioException{
        //si acepta incremento el pozo
        //si no acepta finaliza partida
        if(confirmacion){
            restarMontoJugadoresSumarApuestaEnPartida();
            partidaActiva=true;
        }else{
            finalizarPartida(ultimaApuesta.getJugador());
        }
        avisar(Eventos.confirmacionApuesta);
        Sistema.getInstancia().avisar(Sistema.Eventos.actualizacionEnPartida);
    }
      
    private void unirFicha(Ficha fichaTablero, Ficha fichaDescartada)throws ObligatorioException{
        //Todas las verificaciones lanzan exceptions
        String lado = "";
        if(primera==null){
            tablero.add(fichaDescartada);
            primera=ultima=fichaDescartada;
            turno.eliminarFicha(primera);
        }
        else if(fichaTablero == null || fichaDescartada == null){
            throw new ObligatorioException("Debe seleccionar una ficha de origen y una de destino.");
        }//Si la ficha en el tablero es una sola da problema
        //por que si en la primera le erra tira excepcion
        else if(fichaTablero == primera){
            lado = "Izq";
            //aca arregle el tema comentado arriba de 
            //forma espantosa solo para probar que ande
            try {
                primera.sePuedeUnir(lado, fichaDescartada);
            } catch (ObligatorioException ex) {
                if(primera==ultima){
                    lado = "Der";
                    ultima.sePuedeUnir(lado, fichaDescartada);
                    tablero.add(fichaDescartada); //Si no se puede unir la ficha, lanza excepcion
                    ultima = fichaDescartada;
                    turno.eliminarFicha(fichaDescartada);
                    return;
                }else{
                    throw new ObligatorioException("Las fichas seleccionadas no se pueden unir.");
                }        
            }  
            tablero.add(0,fichaDescartada); //Si no se puede unir la ficha, lanza excepcion
            primera = fichaDescartada;
            turno.eliminarFicha(fichaDescartada);
        }
        else if(fichaTablero.equals(ultima)){
            lado = "Der";
            ultima.sePuedeUnir(lado, fichaDescartada);
            tablero.add(fichaDescartada); //Si no se puede unir la ficha, lanza excepcion
            ultima = fichaDescartada;
            turno.eliminarFicha(fichaDescartada);
        }
        else{
            throw new ObligatorioException("Debe seleccionar una ficha de uno de los extremos del tablero.");
        }
    }
  
    private void restarMontoJugadoresSumarApuestaEnPartida()throws ObligatorioException{
        jugadores.get(0).quitarApuesta(ultimaApuesta.getValor());
        jugadores.get(1).quitarApuesta(ultimaApuesta.getValor());
        pozoApuestas+=ultimaApuesta.getValor()*2;
    }
    //Metodo para reuso en unir ficha codigo repetido
    //No lo cambie todavia
    private void agregarFichaAlTableroYRemoverDelJugador(Ficha ficha){
        
    }
    private void repartirFichas(){
        //se reparten 7 para cada uno
        Jugador j1 = jugadores.get(0);
        Jugador j2 = jugadores.get(1);
        
        for(int i=1; i<=7; i++){
            Ficha f1 = libres.get(0);
            Ficha f2 = libres.get(1);
            j1.agregarFicha(f1);
            j2.agregarFicha(f2);
            libres.remove(f1);
            libres.remove(f2);
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
        partidaActiva=false;
        this.ganador=ganador;
        ganador.setSaldo(ganador.getSaldo() + pozoApuestas);
        for(Jugador j:jugadores){
            j.getFichas().clear();
        }
        avisar(Eventos.partidaFinalizada);
        Sistema.getInstancia().avisar(Sistema.Eventos.actualizacionEnPartida);
    }
    
    private void verificarTurno(Jugador jugador)throws ObligatorioException{
        //ver si sirve que compare direccion de memoria
        if(jugador != turno)
            throw new ObligatorioException("No es su turno.");
    }
    
    //ver que solo puedo preguntar por el jugador que tiene el turno - Eso está bien... Porque nunca se va a quedar sin fichas uno que no sea el turno de él
    private void verificarSiSeDescartoTodas(){
        if(turno.getFichas().isEmpty())
            finalizarPartida(turno);
    }
    
    private boolean verificarSiTieneMovimientos(Jugador j){
        for(Ficha f:j.getFichas()){
            if(primera == null
            || f.getValorIzquierda()== primera.getValorIzquierda()
            || f.getValorDerecha()== primera.getValorIzquierda()
            || f.getValorIzquierda() == ultima.getValorDerecha()
            || f.getValorDerecha() == ultima.getValorDerecha()){
                return true;
            }
        }
        return false;
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
            throw new ObligatorioException("No se puede subir la apuesta dos veces consecutivas.");
    }
    private void sePuedeJugar()throws ObligatorioException{
        if(!partidaActiva)
            throw new ObligatorioException("La partida no está en condiciones de recibir movimientos.");
    }
    public boolean enEspera() {
        if(jugadores.size() < 2)
            return true;
        
        return false;
    }
    private void crearFichas(){
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
}
