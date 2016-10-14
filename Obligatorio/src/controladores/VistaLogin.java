/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import logica.Administrador;
import logica.Jugador;
import logica.Usuario;

/**
 *
 * @author usuario
 */
public interface VistaLogin {

    public void inicializar(String jugador);
    public void ingresarJugador(Jugador j);
    public void ingresarAdministrador(Administrador a);
    public void error(String textoError);
    public void cerrar();
    
    
    
}
