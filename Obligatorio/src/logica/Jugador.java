/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import utilidades.ObligatorioException;

/**
 *
 * @author usuario
 */
public class Jugador extends Usuario {
    private double saldo;
    private ArrayList<Ficha> fichas = new ArrayList();
    
    public Jugador(String n, String p, String np, double s){
        super(n,p,np);
        saldo = s;
    }

    public double getSaldo() {
        return saldo;
    }
    public void verificarSaldo(double monto)throws ObligatorioException {
        if(monto >= saldo)
            throw new ObligatorioException("Saldo insuficiente del jugador: "+getNombreCompleto());
    }

    public void agregarFicha(Ficha get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
