/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import logica.Movimiento;

/**
 *
 * @author usuario
 */
public interface VistaReplay {

    public void actualizarPaneles(Movimiento movimiento);

    public void error(String message);
    
}
