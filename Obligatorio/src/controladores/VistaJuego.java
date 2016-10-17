
package controladores;

import java.util.ArrayList;
import logica.Ficha;
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
}
