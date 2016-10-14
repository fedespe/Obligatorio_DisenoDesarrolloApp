
package controladores;

import logica.Sistema;
import utilidades.Observable;
import utilidades.Observador;

public class ControladorServidor{
    
    private Sistema modelo = Sistema.getInstancia();
    private VistaServidor vista;

    public ControladorServidor(VistaServidor vista) {
        this.vista = vista;
    }

    public void jugador() {
        vista.ingresarIngresarLogin(true);
    }

    public void administrador() {
        vista.ingresarIngresarLogin(false);
    }

    public void salir() {
        //if(modelo.VerificarPartidasCerradas())
        vista.cerrar();
    }

    
}
