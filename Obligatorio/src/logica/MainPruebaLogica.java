
package logica;

import utilidades.ObligatorioException;

public class MainPruebaLogica {

    public static void main(String[] args) {
        Partida partida = new Partida();
        Jugador j1= new Jugador("Jugador 1", "Pass1", "Jugador Uno", 1000);
        Jugador j2= new Jugador("Jugador 2", "Pass2", "Jugador Dos", 2000);
        try{
            //No mezclar las fichas para pruebas
            //0-0 0-1 0-2 0-3 0-4 0-5 0-6
            partida.agregarJugador(j1);
            //1-1 1-2 1-3 1-4 1-5 1-6 2-2
            partida.agregarJugador(j2);
            
            //Movimiento inicial
            partida.mover(j1, null, j1.getFichas().get(2));
            partida.mover(j2, partida.getTablero().get(0), j2.getFichas().get(1));
            
            //partida.mover(j1, partida.getTablero().get(0), j1.getFichas().get(1));
            //partida.mover(j2, partida.getTablero().get(0), j2.getFichas().get(1));
            partida.robar(j1);
            partida.apostar(j1, 500);
            partida.confirmarApuesta(false);
            
        }catch(ObligatorioException ex){
            System.out.println(ex.getMessage());
        }
        
        
    }
    
}
