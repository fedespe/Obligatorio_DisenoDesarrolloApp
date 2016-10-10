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

    public int getValorDerecha() {
        return valorDerecha;
    }

    public int getValorIzquierda() {
        return valorIzquierda;
    }

    public Ficha(int valorDerecha, int valorIzquierda) {
        this.valorDerecha = valorDerecha;
        this.valorIzquierda = valorIzquierda;
    }
    
    @Override
    public boolean equals(Object o){
        Ficha f = (Ficha)o;
        return (f.getValorDerecha()==valorDerecha && f.getValorIzquierda()==valorIzquierda)
                || (f.getValorDerecha()==valorIzquierda && f.getValorIzquierda()==valorDerecha);
    }
}
