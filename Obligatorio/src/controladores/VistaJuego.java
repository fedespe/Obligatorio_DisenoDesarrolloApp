
package controladores;

import logica.Jugador;
import logica.Partida;

/**
 *
 * @author usuario
 */
public interface VistaJuego {

    public void cerrar();

    public void actualizarPaneles(Partida partida, Jugador jugador);

    public void mensaje(String message);

    public void confirmarApuesta(String mensaje);
    
    public void mensajeModal(String message);
}
