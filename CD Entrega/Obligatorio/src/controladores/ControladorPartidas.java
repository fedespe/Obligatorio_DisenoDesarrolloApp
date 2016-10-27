/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import logica.Partida;
import logica.Sistema;
import utilidades.Observable;
import utilidades.Observador;

/**
 *
 * @author usuario
 */
public class ControladorPartidas implements Observador{

    private Sistema modelo = Sistema.getInstancia();
    private VistaPartidas vista;
    
    public ControladorPartidas (VistaPartidas vista){
        this.vista = vista;
        modelo.agregar(this);
        vista.mostrarPartidas(modelo.getPartidas());
    }
    
    @Override
    public void actualizar(Observable origen, Object evento) {
        if(evento.equals(Sistema.Eventos.actualizacionEnPartida))
            vista.mostrarPartidas(modelo.getPartidas());
    }

    public void salir() {
        modelo.quitar(this);
        vista.cerrar();
    }

    public void abrirPartida(int selectedIndex) {
        ArrayList<Partida> partidas = modelo.getPartidas();
        Partida partida = partidas.get(selectedIndex);
        vista.mostrarPartida(partida);
    }
    
}
