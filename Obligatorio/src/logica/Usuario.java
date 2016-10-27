/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author usuario
 */

//NOTAS:
//El jugador no se puede loguear si no tiene saldo - Este control se va a hacer de forma automática cuando se loguee e intente entrar en una partida.
    //Hay que recordar eliminarlo de la lista de logueados si se loguea y la partida lo rebota por falta de saldo!!!
//El jugador se puede loguear una sola vez - Control hecho en el login
//El administrador se puede loguear  n veces - No se guardan los admin logueados, por lo que no bloqueamos el logueo múltipleS
public class Usuario {
    private String nombre;
    private String password;
    private String nombreCompleto;

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }
    
    public Usuario(String n, String p, String nc){
        nombre = n;
        password = p;
        nombreCompleto = nc;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
}
