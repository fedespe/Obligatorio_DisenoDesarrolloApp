
package controladores;

import logica.Sistema;
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
        }
        catch (ObligatorioException ex){
            vista.error(ex.getMessage());
        }
    }
}
