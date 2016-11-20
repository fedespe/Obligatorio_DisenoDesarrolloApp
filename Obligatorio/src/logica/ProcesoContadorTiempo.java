/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import static java.lang.Thread.sleep;
import java.util.Observable;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;

/**
 *
 * @author Usuario
 */
public class ProcesoContadorTiempo extends Observable implements Runnable {
    private Thread hilo;
    private boolean correr;
    private String nombre;
    private int segundosInicial;
    private int segundos;

    public enum Eventos{
        segundo, 
        finalizado,       
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getSegundos() {
        return segundos;
    }
    
    public ProcesoContadorTiempo(String nombre, int segundos) {
        this.nombre = nombre;
        this.segundosInicial = segundos;
    }
    
    public void continuar(){
        if(!correr){
            correr=true;
            hilo=new Thread(this,nombre);
            hilo.start();
        }
    }
    public void terminar(){
        correr=false;
        segundos=segundosInicial;
    }

    public void pausar(){
        correr=false;
    }

    public void iniciar(){
        if(!correr){
            correr=true;
            segundos=segundosInicial;
            hilo=new Thread(this,nombre);
            hilo.start();
        } 
    }
    
    
    @Override
    public void run() {
        //Hilo alternativo al Main
        System.out.println("INICIO:" + getNombre());
        for(;segundos>0 && correr;){
            System.out.println("Segundos: " + segundos + " - " + getNombre());
            if(correr)
                avisar(Eventos.segundo);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
            }
            if(correr)
                segundos--;
        }
         
        if(segundos==0){ 
            System.out.println("FIN:" + getNombre());
            segundos=segundosInicial;
            avisar(Eventos.finalizado);
            correr = false;
        }
        
        
    }
    
    @Override
    public String toString() {
        String c = "corriendo";
        if(!correr) c = "fin";
        return "Segundos:" + segundos + " - " + getNombre()+ " " + c;
    }

    private void avisar(Object evento) {
        setChanged();
        notifyObservers(evento);
    }
    
}
