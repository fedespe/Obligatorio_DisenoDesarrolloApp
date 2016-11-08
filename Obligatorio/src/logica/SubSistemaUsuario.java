/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import mapeadores.MapeadorJugador;
import persistencia.Persistencia;
import utilidades.ObligatorioException;

/**
 *
 * @author usuario
 */
public class SubSistemaUsuario{
    private ArrayList<Administrador> administradores = new ArrayList();
    private ArrayList<Jugador> jugadores = new ArrayList();
    private ArrayList<Jugador> jugadoresLogueados = new ArrayList();
    private Persistencia persistencia = new Persistencia();
    private MapeadorJugador jugadorMapper = new MapeadorJugador();
    
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
        ArrayList<Object> listaJugadores = persistencia.obtenerTodos(jugadorMapper);
        for(Object o: listaJugadores){
            Jugador j = (Jugador)o;
            jugadores.add(j);
        }
        
        administradores.add(new Administrador("a","a","Analía Pereyra"));
        administradores.add(new Administrador("b","b","Blanca Moreira"));
        administradores.add(new Administrador("c","c","Claudia Tabárez"));
        administradores.add(new Administrador("d","d","Dilma Rousseff"));
        administradores.add(new Administrador("e","e","Emilia Suárez"));
        administradores.add(new Administrador("f","f","Fabiana Guerra"));
        administradores.add(new Administrador("g","g","Graciela García"));
        administradores.add(new Administrador("h","h","Heidy Montero"));
        administradores.add(new Administrador("i","i","Ilda De León"));
        administradores.add(new Administrador("j","j","Judith Barsi"));
    }
}
