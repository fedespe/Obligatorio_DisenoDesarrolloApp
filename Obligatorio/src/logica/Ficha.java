/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author usuario
 */
public class Ficha {
    private int valorDerecha;
    private int valorIzquierda;
    
    public void rotar(){
        int aux=valorDerecha;
        valorDerecha=valorIzquierda;
        valorIzquierda=aux;
    }
}
