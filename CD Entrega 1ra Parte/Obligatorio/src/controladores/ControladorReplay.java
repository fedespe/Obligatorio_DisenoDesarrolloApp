/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import logica.Movimiento;
import logica.Partida;
import logica.Sistema;

/**
 *
 * @author usuario
 */

public class ControladorReplay{
    
    private Sistema modelo = Sistema.getInstancia();
    private VistaReplay vista;
    private Partida partida;
    private int nroMovimiento;
    private ArrayList<Movimiento> movimientos = new ArrayList();
    
    public ControladorReplay(VistaReplay vista, Partida partida){
        this.vista = vista;
        this.partida = partida;
        this.movimientos = partida.getMovimientos();
        this.nroMovimiento = 0;
    }

    public void movimientoSiguiente() {
        this.nroMovimiento++;
        cargarMovimiento();
    }

    public void movimientoAnterior() {
        this.nroMovimiento--;
        cargarMovimiento();
    }
    
    private void cargarMovimiento(){
        if(nroMovimiento < 0){
            nroMovimiento++;
            vista.error("No existen movimientos anteriores.");
        }
        else if(nroMovimiento >= partida.getMovimientos().size()){
            nroMovimiento--;
            vista.error("No existen movimientos posteriores.");
        }
        else{
            vista.actualizarPaneles(movimientos.get(nroMovimiento));
        }
    }

    public void inicializar() {
        cargarMovimiento();
    }
}
