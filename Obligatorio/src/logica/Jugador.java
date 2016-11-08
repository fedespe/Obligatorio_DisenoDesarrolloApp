/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import mapeadores.MapeadorJugador;
import persistencia.Persistencia;
import utilidades.ObligatorioException;

/**
 *
 * @author usuario
 */
public class Jugador extends Usuario {
    private double saldo;
    private ArrayList<Ficha> mano = new ArrayList();
    private Partida partidaJugando;
    private Persistencia persistencia = new Persistencia();
    private MapeadorJugador jugadorMapper = new MapeadorJugador();
    
    public Jugador(String n, String p, String np, double s){
        super(n,p,np);
        saldo = s;
    }

    public Jugador() {
        super();
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public double getSaldo() {
        return saldo;
    }
    public void verificarSaldo(double monto)throws ObligatorioException {
        if(monto > saldo)
            throw new ObligatorioException(getNombreCompleto() + " no posee saldo suficiente.");
    }

    public void agregarFicha(Ficha f) {
        mano.add(f);
    }

    public ArrayList<Ficha> getFichas() {
        return mano;
    }

    public void quitarApuesta(double valor) throws ObligatorioException{
        verificarSaldo(valor);
        saldo-=valor;
    }

    public void eliminarFicha(Ficha ficha) {
        mano.remove(ficha);
    }    

    public void vaciarMano() {
        mano = new ArrayList();
    }

    public void actualizarEnBase() {
        jugadorMapper.setJugador(this);
        persistencia.guardar(jugadorMapper);
    }
    
}
