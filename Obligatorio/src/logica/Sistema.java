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
public class Sistema {
    private SubSistemaUsuario ssu = new SubSistemaUsuario();
    private SubSistemaPartida ssp = new SubSistemaPartida();
    private static Sistema instancia = new Sistema();
    
    private Sistema(){};

    public static Sistema getInstancia() {
        return instancia;
    }

    public Usuario login(String n, String p) throws ObligatorioException{
        return ssu.login(n, p);
    }

    public boolean partidasFinalizadas() throws ObligatorioException{
        return ssp.partidasFinalizadas();
    }

    public void logout(Usuario usu) {
        ssu.logout(usu);
    }

    public Partida partidaPosision(int pos) {
        return ssp.partidaPosision(pos);
    }

    public ArrayList<Partida> getPartidas() {
        return ssp.getPartidas();
    }
    
    
}
