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
    
    public Usuario(){
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    @Override
    public boolean equals(Object o){
        return nombre.equals(((Usuario)o).getNombre());
    }
}
