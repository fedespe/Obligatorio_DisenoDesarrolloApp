/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasWeb;

import controladores.ControladorLogin;
import controladores.ControladorReplay;
import controladores.VistaReplay;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.AsyncContext;
import logica.Ficha;
import logica.Movimiento;
import logica.Partida;

/**
 *
 * @author Usuario
 */
public class VistaReplayWeb implements VistaReplay{
    private ControladorReplay controlador;
    private AsyncContext contexto;
    private PrintWriter out;
    private String nombreVista;

    //Le tiene que llegar la partida desde el monitoreo
    public VistaReplayWeb(AsyncContext contexto, Partida partida, String nombreVista) {
        this.contexto = contexto;
        try {
            out = contexto.getResponse().getWriter();
        } catch (IOException ex) { }
        controlador = new ControladorReplay(this,partida);
        this.nombreVista=nombreVista;
        controlador.inicializar();
    }
    public void siguiente(){
        controlador.movimientoSiguiente();
    }

    @Override
    public void actualizarPaneles(Movimiento movimiento) {
        enviar("mostrarFichas",formatear(movimiento));
    }

    @Override
    public void error(String message) {
        //enviar("mostrarFichas","<p>No existen mas movimientos</p>");
    }
    private void enviar(String evento, String dato) {
        if(out!=null){
            out.write("event: " + evento + "\n"); //asi se especifica el nombre del evento, con este nombre se registran los listeners en la pagina
            dato = dato.replace("\n", ""); //el dato no puede tener \n por protocolo
            out.write("data: " + dato + "\n\n"); //envio el dato
            out.flush();
        }
    }

    private String formatear(Movimiento movimiento) {
        String out="";
        if(movimiento.getGanador()!=null){
            out+="<p>Ganador: "+movimiento.getGanador().getNombreCompleto()+"</p>";
        }else{
            out+="<p>Ganador: aún no hay ganador</p>";
        }
        if(movimiento.getTablero().isEmpty()){
            out+="<br><p>Jugador: Aún no se ha jugado </p>";
            out+="<br><p>Fecha: Aún no se ha jugado </p>";
        }else{
            out+="<br><p>Jugador: "+movimiento.getJugador().getNombreCompleto()+"</p>";
            out+="<br><p>Fecha: "+movimiento.getFechaHora()+"</p>";
        }
        
        
        for(Ficha f : movimiento.getTablero()){
            out+="<img src='Imagenes/Horizontales/" + f.getValorIzquierda() + "-" + f.getValorDerecha() + ".jpg'>";
        }
        return out;
    }

    public void anterior() {
        controlador.movimientoAnterior();
    }

    public void enviarNombreVista() {
        enviar("guardarNombreVista", nombreVista);
    }
}
