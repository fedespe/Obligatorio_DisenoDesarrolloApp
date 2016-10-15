/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import logica.Sistema;
import utilidades.Observable;
import utilidades.Observador;

/**
 *
 * @author usuario
 */
public class ControladorPartidas implements Observador{

    private Sistema modelo = Sistema.getInstancia();
    private VistaPartidas vista;
    
    public ControladorPartidas (VistaPartidas vista){
        this.vista = vista;
        vista.mostrarPartidas(modelo.getPartidas());
    }
    
    @Override
    public void actualizar(Observable origen, Object evento) {
        
    }

    public void salir() {
        vista.cerrar();
    }
    
}
