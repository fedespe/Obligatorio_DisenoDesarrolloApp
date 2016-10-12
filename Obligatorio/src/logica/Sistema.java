/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import utilidades.ObligatorioException;

/**
 *
 * @author usuario
 */
public class Sistema {
    private SubSistemaUsuario ssu = new SubSistemaUsuario();
    private SubSistemaPartida ssp = new SubSistemaPartida();
    private static Sistema instancia = new Sistema();
    
    private Sistema(){};

    public static Sistema getInstancia() {
        return instancia;
    }
    
    public enum Eventos{
        fichaDescartada, //Lo utilizaremos cada vez que se agrega una ficha al Tablero
        fartidaFinalizada, //Lo urilizaremos cada vez que una partida se finaliza
        jugadorAgregado; //Lo utilizaremos cada vez que ingresa un segudno jugador a una partida que estaba esperando
        
    }
}
