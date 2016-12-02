/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasWeb;

import Utilidades.Componentes;
import controladores.ControladorPartidas;
import controladores.VistaPartidas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.AsyncContext;
import logica.Jugador;
import logica.Partida;

/**
 *
 * @author Usuario
 */
public class VistaMonitoreoWeb implements VistaPartidas{
    private ControladorPartidas controlador;
    private AsyncContext contexto;
    private PrintWriter out;

    public VistaMonitoreoWeb(AsyncContext contexto) {
        this.contexto = contexto;
        try {
            out = contexto.getResponse().getWriter();
        } catch (IOException ex) { }
        controlador = new ControladorPartidas(this);
    }

    @Override
    public void cerrar() {
    }

    @Override
    public void mostrarPartidas(ArrayList<Partida> partidas) {
        ArrayList<String> listaFormateada = formatear(partidas);
        enviar("listarPartidas",Componentes.select(listaFormateada, "iDlistaPartidas"));
    }

    @Override
    public void mostrarPartida(Partida get) {
    }
    
    private void enviar(String evento, String dato) {
        if(out!=null){
            out.write("event: " + evento + "\n"); //asi se especifica el nombre del evento, con este nombre se registran los listeners en la pagina
            dato = dato.replace("\n", ""); //el dato no puede tener \n por protocolo
            out.write("data: " + dato + "\n\n"); //envio el dato
            out.flush();
        }
    }
    private ArrayList<String> formatear(ArrayList<Partida> partidas){
        ArrayList<String> lista = new ArrayList();
        
        for(Partida p:partidas){
            String linea = "[En juego] ";;
            String g = "Aún no hay ganador";
            
            Jugador ganador = p.getGanador();
            
            if(ganador != null){
                linea = "[Finalizada] ";
                g = ganador.getNombreCompleto();
            }
            
            linea += p.getJugadores().get(0).getNombreCompleto() + " ($ " + p.getJugadores().get(0).getSaldo() + ") - ";
            linea += p.getJugadores().get(1).getNombreCompleto() + " ($ " + p.getJugadores().get(1).getSaldo() + ") : $";
            
            linea += p.getPozoApuestas()+" => ";
            
            linea += g;
            
            lista.add(linea);
        }
        return lista;
    }
    //    @Override
//    public void cargarTipos(ArrayList<String> tipos) {
//        enviar("listaTipos",Componentes.select(tipos, "iDListaTipos"));
//    }
//
//    @Override
//    public void limpiar() {
//        enviar("limpiar","");
//    }
//
//    @Override
//    public void mostrarAvisos(ArrayList<Aviso> avisos) {
//        enviar("listaAvisos",Componentes.select(avisos, "iDlistaAvisos"));
//    }
//
//    public void agregar(String texto, String duracion) {
//        controlador.agregarAviso(texto, Integer.parseInt(duracion));
//    }
//
//    public void crear(String nombre, String tipo) {
//        controlador.agregarPublicidad(nombre, tipo);
//    }
}
