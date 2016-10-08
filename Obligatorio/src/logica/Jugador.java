/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;

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
    public boolean verificarSaldo(double monto) {
        if(monto <= saldo)
            return true;
        
        return false;
    }
    
}
