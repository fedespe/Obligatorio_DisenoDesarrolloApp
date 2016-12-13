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
    private int oid;
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

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Usuario(){
        
    }
}
