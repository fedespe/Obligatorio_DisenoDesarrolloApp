/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;



import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Administrador;
import logica.Jugador;
import logica.Sistema;
import utilidades.ObligatorioException;

    

public class ControladorLogin {
    private Sistema modelo = Sistema.getInstancia();
    private VistaLogin vista;
    private boolean jugador;

    public ControladorLogin(VistaLogin vista, boolean jugador) {
        this.vista = vista;
        this.jugador = jugador;
        inicio();
    }
    
    private void inicio(){
        if(jugador){           
            vista.inicializar("Jugador");
        }else{
            vista.inicializar("Administrador");
        }
    }
    
    public void login(String nombre, String pass) {
        try {
            if(jugador){
                Jugador j = modelo.loginJugador(nombre, pass);
                if(j!=null){
                    vista.cerrar();
                    vista.ingresarJugador(j);
                }
            }else{           
                Administrador a = modelo.loginAdministrador(nombre, pass);
                if(a!=null){
                    vista.cerrar();
                    vista.ingresarAdministrador(a);
                }
            }
        } catch (ObligatorioException ex) {
            vista.error(ex.getMessage());
        }
    }
    
    
    
    
}
