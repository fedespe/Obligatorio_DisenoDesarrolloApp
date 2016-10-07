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
public class SubSistemaUsuario {
    private ArrayList<Usuario> usuarios = new ArrayList();
    private ArrayList<Usuario> logueados = new ArrayList();
    
    public Usuario login(String n, String p){
        for(Usuario u:usuarios){
            if(u.getNombre().equalsIgnoreCase(n)){
                if(u.getPassword().equals(p)){
                    if(!estaLogueado(n)){
                        logueados.add(u);
                        return u;
                    }
                    //else
                        //dar aviso de que ya está logueado y debe cerrar sesión
                }
                else
                    return null;
            } 
        }
        return null;
    }
    
    public void logout(Usuario usu){
        for(Usuario u:logueados){
            if(usu.equals(u)){
                logueados.remove(u);
                return;
            }  
        }
    }
    
    private boolean estaLogueado(String n){
        for(Usuario u:logueados){
            if(u.getNombre().equalsIgnoreCase(n))
                return true;
        }
        return false;
    }
    
    public SubSistemaUsuario(){
        cargarUsuarios();
    }
    
    private void cargarUsuarios(){
        usuarios.add(new Jugador("a","a","Ana",5));
        usuarios.add(new Jugador("b","b","Bruno",500));
        usuarios.add(new Jugador("c","c","Carlos",250));
        usuarios.add(new Administrador("d","d","Dilma"));
        usuarios.add(new Administrador("e","e","Emiliano"));
        usuarios.add(new Administrador("f","f","Federico"));
    }
}
