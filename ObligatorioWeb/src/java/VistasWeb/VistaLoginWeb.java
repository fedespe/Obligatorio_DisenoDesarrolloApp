/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasWeb;

import Utilidades.Componentes;
import controladores.ControladorLogin;
import controladores.ControladorPartidas;
import controladores.VistaLogin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.AsyncContext;
import logica.Administrador;
import logica.Jugador;

/**
 *
 * @author Usuario
 */
public class VistaLoginWeb implements VistaLogin{
    private ControladorLogin controlador;
    private AsyncContext contexto;
    private PrintWriter out;

    public VistaLoginWeb(AsyncContext contexto) {
        this.contexto = contexto;
        try {
            out = contexto.getResponse().getWriter();
        } catch (IOException ex) { }
        controlador = new ControladorLogin(this,false);
    }

    @Override
    public void inicializar(String jugador) {
    }

    @Override
    public void ingresarJugador(Jugador j) {
    }

    @Override
    public void ingresarAdministrador(Administrador a) {
        enviar("mostrarPartidas"," ");
    }

    @Override
    public void error(String textoError) {
    }

    @Override
    public void cerrar() {
    }

    public void ingresar(String nombre, String pass) {
        controlador.login(nombre, pass);
    }
    private void enviar(String evento, String dato) {
        if(out!=null){
            out.write("event: " + evento + "\n"); //asi se especifica el nombre del evento, con este nombre se registran los listeners en la pagina
            dato = dato.replace("\n", ""); //el dato no puede tener \n por protocolo
            out.write("data: " + dato + "\n\n"); //envio el dato
            out.flush();
        }
    }
}
