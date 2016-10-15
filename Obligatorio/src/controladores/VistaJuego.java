/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import logica.Ficha;

/**
 *
 * @author usuario
 */
public interface VistaJuego {

    public void cerrar();

    public void cargarPaneles(ArrayList<Ficha> tablero, ArrayList<Ficha> fichas);
    
}
