/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import logica.Partida;

/**
 *
 * @author usuario
 */
public interface VistaPartidas {

    public void cerrar();

    public void mostrarPartidas(ArrayList<Partida> partidas);

    public void mostrarPartida(Partida get);
    
}
