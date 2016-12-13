
package controladores;

import logica.Sistema;
import persistencia.BaseDatos;
import utilidades.ObligatorioException;

public class ControladorServidor{
    
    private Sistema modelo = Sistema.getInstancia();
    private VistaServidor vista;

    public ControladorServidor(VistaServidor vista) {
        this.vista = vista;
    }

    public void jugador() {
        vista.ingresarLogin(true);
    }

    public void administrador() {
        vista.ingresarLogin(false);
    }

    public void salir() {
        try{
            modelo.partidasFinalizadas();
            vista.cerrar();
            BaseDatos.getInstancia().desconectar(); //Preguntar si está bien que esté ahí
        }
        catch (ObligatorioException ex){
            vista.error(ex.getMessage());
        }
    }
}
