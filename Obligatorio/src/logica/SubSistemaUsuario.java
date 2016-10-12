/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import utilidades.ObligatorioException;

/**
 *
 * @author usuario
 */
public class SubSistemaUsuario{
    private ArrayList<Administrador> administradores = new ArrayList();
    private ArrayList<Jugador> jugadores = new ArrayList();
    private ArrayList<Jugador> jugadoresLogueados = new ArrayList();
    
    public Jugador loginJugador(String n, String p) throws ObligatorioException{
        for(Jugador j:jugadores){
            if(j.getNombre().equalsIgnoreCase(n)){
                if(j.getPassword().equals(p)){
                    if(!estaLogueado(n)){
                        jugadoresLogueados.add(j);
                        return j;
                    }
                    else
                        throw new ObligatorioException("Jugador ya logueado, debe cerrar la otra sesión.");
                }
                else
                    throw new ObligatorioException("Password incorrecto.");
            } 
        }
        throw new ObligatorioException("Usuario incorrecto.");
    }
    
    public Administrador loginAdministrador(String n, String p) throws ObligatorioException{
        for(Administrador a:administradores){
            if(a.getNombre().equalsIgnoreCase(n)){
                if(a.getPassword().equals(p)){
                    return a;
                }
                else
                    throw new ObligatorioException("Password incorrecto.");
            }
        } 
        throw new ObligatorioException("Usuario incorrecto.");
    }
    
    public void logoutJugador(Jugador jug){
        for(Jugador j:jugadoresLogueados){
            if(jug.equals(j)){
                jugadoresLogueados.remove(j);
                return;
            }
        }
    }
    
    private boolean estaLogueado(String n){
        for(Jugador j:jugadoresLogueados){
            if(j.getNombre().equalsIgnoreCase(n))
                return true;
        }
        return false;
    }
    
    public SubSistemaUsuario(){
        cargarUsuarios();
    }
    
    private void cargarUsuarios(){
        jugadores.add(new Jugador("a","a","Ana",5));
        jugadores.add(new Jugador("b","b","Bruno",500));
        jugadores.add(new Jugador("c","c","Carlos",250));
        administradores.add(new Administrador("d","d","Dilma"));
        administradores.add(new Administrador("e","e","Emiliano"));
        administradores.add(new Administrador("f","f","Federico"));
    }
}
