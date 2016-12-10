/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import com.sun.corba.se.spi.orbutil.fsm.GuardBase;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapeadores.MapeadorPartida;
import persistencia.Persistencia;
import utilidades.ObligatorioException;
import static java.lang.Thread.sleep;

/**
 *
 * @author usuario
 */
public class Partida extends utilidades.Observable {
    private int oid;
    private boolean partidaActiva=false;
    private double pozoApuestas = 0;
    private ArrayList<Jugador> jugadores = new ArrayList();
    private Jugador turno;
    private Jugador ganador;
    private ArrayList<Movimiento> movimientos = new ArrayList();
    private Apuesta ultimaApuesta;
    private ArrayList<Ficha> libres = new ArrayList();
    private ArrayList<Ficha> tablero = new ArrayList();
    private Ficha primera;
    private Ficha ultima;
    private String id;
    private Persistencia persistencia = new Persistencia();
    private MapeadorPartida partidaMapeador = new MapeadorPartida();
    
    //************************************************************
    //Nuevo Hilos 
    //************************************************************
    private int segTurno=15;//Solamente cambiar aca para cambiar tiempo de juego
    private int segApuesta=15;
    private ProcesoContadorTiempo tiempoTurno=new ProcesoContadorTiempo(this,"TiempoTurno", segTurno);
    private ProcesoContadorTiempo tiempoApuesta=new ProcesoContadorTiempo(this,"TiempoApuesta", segApuesta);
    
    public int getSegTurno() {
        return segTurno;
    } 
    
    public ProcesoContadorTiempo getTiempoTurno() {
        return tiempoTurno;
    }

    public ProcesoContadorTiempo getTiempoApuesta() {
        return tiempoApuesta;
    }

    private void iniciarTiempoTurno(){    
        tiempoTurno.terminar();
        tiempoTurno=new ProcesoContadorTiempo(this, "tiempoTurno", segTurno);
        tiempoTurno.iniciar();
    }
    private void iniciarTiempoApuesta(){
        tiempoApuesta.terminar();
        tiempoApuesta=new ProcesoContadorTiempo(this, "tiempoTurno", segApuesta);
        tiempoApuesta.iniciar();
    }
    private void pausarTiempoTurno(){
        tiempoTurno.pausar();
    }
    private void terminarTiempoApuesta(){
        tiempoApuesta.terminar();
    }
    private void continuarTiempoTurno(){
        int seg=tiempoTurno.getSegundos();
        tiempoApuesta=new ProcesoContadorTiempo(this, "tiempoTurno", seg);
        tiempoTurno.continuar();
    }
    //************************************************************
    //fin hilos
    //************************************************************
    
    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public void setPozoApuestas(double pozoApuestas) {
        this.pozoApuestas = pozoApuestas;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

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

    public void tiempoFinalizado() {
        cambiarTurno();
        finalizarPartida(turno);
    }

    
    
    public enum Eventos{
        fichaDescartada, 
        partidaFinalizada, 
        ingresoJugador, 
        roboFicha, 
        apuesta, 
        confirmacionApuesta,
        realizoMovimiento,
        segundo,
        finTiempo
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

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }
    
    public Partida(){
        ultimaApuesta= new Apuesta(100);
        crearFichas();
        mezclarFichas();
    }
    
    public void agregarJugador(Jugador jugador) throws ObligatorioException{
        jugador.verificarSaldo(ultimaApuesta.getValor());
        jugadores.add(jugador);
        if(jugadores.size()==1){
            turno=jugador;
        }else if(jugadores.size()==2){
            restarMontoJugadoresSumarApuestaEnPartida();
            repartirFichas();
            partidaActiva=true;
            agregarMovimiento();
            this.id=(new Date()).toString()+"_"+jugadores.get(0).getNombre();//genero el identificador de la partida
            //Nuevo Hilo 
            tiempoTurno.iniciar();
        }
        avisar(Eventos.ingresoJugador);
    }

    public void mover(Jugador jugador, Ficha fichaTablero, Ficha fichaDescartada)throws ObligatorioException{
        controlesAntesJugar(jugador);
        unirFicha(fichaTablero, fichaDescartada);
        agregarMovimiento();
        verificarSiSeDescartoTodas();
        cambiarTurno();
        //nuevo hilo
        if(partidaActiva)
            iniciarTiempoTurno();//aca hay que agregarle que si la partida finalizo o se que esta inactiva no inicie
        avisar(Eventos.realizoMovimiento);
        Sistema.getInstancia().avisar(Sistema.Eventos.actualizacionEnPartida);
        if(libres.isEmpty()){
            if(verificarSiTieneMovimientos(turno)){
                cambiarTurno();
                finalizarPartida(turno);
            }
        }
    }
    
    private void controlesAntesJugar(Jugador jugador) throws ObligatorioException{
        sePuedeJugar();
        verificarTurno(jugador);
    }
    
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
        
        if(libres.isEmpty()){
            if(!verificarSiTieneMovimientos(j)){
                cambiarTurno();
                finalizarPartida(turno); 
            } 
        }
        //nuevo hilo
        //ver si se tiene que reiniciar el tiempo si roba ficha
        //iniciarTiempoTurno();
    }
    
    public void apostar(Jugador apostador, double monto)throws ObligatorioException{
        sePuedeJugar();
        verificarUltimoEnApostar(apostador);
        verificarApuesta(monto);
        ultimaApuesta.setJugador(apostador);
        ultimaApuesta.setValor(monto);
        partidaActiva=false;
        //nuevo hilo
        pausarTiempoTurno();
        iniciarTiempoApuesta();
        avisar(Eventos.apuesta);     
    }
    public void confirmarApuesta(boolean confirmacion)throws ObligatorioException{
        terminarTiempoApuesta();
        if(confirmacion){
            restarMontoJugadoresSumarApuestaEnPartida();
            //ACTUALIZO EL ULTIMO MOVIMIENTO PARA QUE QUEDE REGISTRO DE LA APUESTA
            //ASI NO HAY PROBLEMA AL GUARDAR EN LA BASE TAMBIEN
            movimientos.get(movimientos.size()-1).setPozoApuestas(pozoApuestas);
            partidaActiva=true;
            //nuevo hilo
            continuarTiempoTurno();           
        }else{
            finalizarPartida(ultimaApuesta.getJugador());           
        }    
        avisar(Eventos.confirmacionApuesta);
        Sistema.getInstancia().avisar(Sistema.Eventos.actualizacionEnPartida);
    }
    
    public void primerJugada(Ficha fichaDescartada)throws ObligatorioException{
        if(fichaDescartada == null)
                throw new ObligatorioException("Debe seleccionar la ficha a descartar.");
        
        tablero.add(fichaDescartada);
        primera=ultima=fichaDescartada;
    }
    
    public void segundaJugada(Ficha fichaTablero, Ficha fichaDescartada)throws ObligatorioException{
        String lado = "Izq";
        
        try {
            primera.sePuedeUnir(lado, fichaDescartada);
            tablero.add(0,fichaDescartada); 
            primera = fichaDescartada;
        } catch (ObligatorioException ex) {
            lado = "Der";
            ultima.sePuedeUnir(lado, fichaDescartada);
            tablero.add(fichaDescartada); 
            ultima = fichaDescartada;
        }  
    }
    
    private void unirFicha(Ficha fichaTablero, Ficha fichaDescartada)throws ObligatorioException{
        String lado = "";
        if(primera==null){
            primerJugada(fichaDescartada);
        }
        else if(fichaTablero == null || fichaDescartada == null){
            throw new ObligatorioException("Debe seleccionar una ficha de origen y una de destino.");
        }
        else if(primera == ultima){
            segundaJugada(fichaTablero, fichaDescartada);
        }
        else if(fichaTablero == primera){
            lado = "Izq";
            primera.sePuedeUnir(lado, fichaDescartada);
            tablero.add(0,fichaDescartada); 
            primera = fichaDescartada;
        }
        else if(fichaTablero.equals(ultima)){
            lado = "Der";
            ultima.sePuedeUnir(lado, fichaDescartada);
            tablero.add(fichaDescartada);
            ultima = fichaDescartada;
        }
        else{
            throw new ObligatorioException("Debe seleccionar una ficha de uno de los extremos del tablero.");
        }
        turno.eliminarFicha(fichaDescartada);
    }
  
    private void restarMontoJugadoresSumarApuestaEnPartida()throws ObligatorioException{
        jugadores.get(0).quitarApuesta(ultimaApuesta.getValor());
        jugadores.get(1).quitarApuesta(ultimaApuesta.getValor());
        pozoApuestas+=ultimaApuesta.getValor()*2;        
        actualizarSaldoJugadores();
    }

    private void repartirFichas(){
        Jugador j1 = jugadores.get(0);
        Jugador j2 = jugadores.get(1);
        
        j1.vaciarMano();
        j2.vaciarMano();
        
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
        partidaActiva=false;
        //Nuevo Hilo 
        terminarTiempoApuesta();
        pausarTiempoTurno();
        this.ganador=ganador;
        ganador.setSaldo(ganador.getSaldo() + pozoApuestas);
        actualizarSaldoJugadores();
        movimientos.get(movimientos.size()-1).setGanador(ganador);
        actualizarEnBase();//Guardo la partida en la base!
        avisar(Eventos.partidaFinalizada);
        Sistema.getInstancia().avisar(Sistema.Eventos.actualizacionEnPartida);
    }
    
    private void verificarTurno(Jugador jugador)throws ObligatorioException{
        if(jugador != turno)
            throw new ObligatorioException("No es su turno.");
    }
    
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
    
    private void agregarMovimiento(){
        movimientos.add(new Movimiento(ganador, pozoApuestas, turno, (ArrayList<Ficha>)tablero.clone()));
    }
    
    private void actualizarSaldoJugadores(){
        for(Jugador j:jugadores){
            j.actualizarEnBase();
        }
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
    private void actualizarEnBase() {
        partidaMapeador.setPartida(this);
        persistencia.guardar(partidaMapeador);
    }
    
    
}
