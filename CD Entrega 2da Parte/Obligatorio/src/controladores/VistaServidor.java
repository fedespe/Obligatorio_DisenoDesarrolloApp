/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author usuario
 */
public interface VistaServidor {

    public void cerrar();

    public void ingresarLogin(boolean jugador);

    public void error(String message);
    
}
