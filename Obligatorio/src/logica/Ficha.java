/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import utilidades.ObligatorioException;

/**
 *
 * @author usuario
 */
public class Ficha {
    private int valorDerecha;
    private int valorIzquierda;
    
    private void rotar(){
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

    public Ficha(int valorIzquierda, int valorDerecha) {
        this.valorDerecha = valorDerecha;
        this.valorIzquierda = valorIzquierda;
    }
    
    public void sePuedeUnir(String lado, Ficha fichaDescartada) throws ObligatorioException {
        if(lado.equals("Izq")){
            if(this.valorIzquierda != fichaDescartada.getValorDerecha()){
                if(this.valorIzquierda == fichaDescartada.getValorIzquierda()){
                    fichaDescartada.rotar();
                }
                else
                    throw new ObligatorioException("Las fichas seleccionadas no se pueden unir.");
            }
        }
        else{
            if(this.valorDerecha != fichaDescartada.getValorIzquierda()){
                if(this.valorDerecha == fichaDescartada.getValorDerecha()){
                    fichaDescartada.rotar();
                }
                else
                    throw new ObligatorioException("Las fichas seleccionadas no se pueden unir.");
            }
        }
    }
}
