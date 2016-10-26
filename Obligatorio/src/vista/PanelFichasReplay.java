/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controladores.ControladorReplay;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import logica.Ficha;

/**
 *
 * @author usuario
 */
public class PanelFichasReplay extends javax.swing.JPanel {
    private ControladorReplay controlador;
    /**
     * Creates new form PanelFichasReplay
     */
    public PanelFichasReplay(ControladorReplay controlador) {
        initComponents();
        this.controlador = controlador;
    }
    
    public void mostrar(ArrayList<Ficha> fichas){
        GridLayout layout = new GridLayout(1,fichas.size()); //Creo un GridLayout de una fila por tantas columnas como fichas tenga.
        setLayout(layout); //Metodo heredado de la clase JPanel.
        for(int i = 0; i < fichas.size(); i++){ //Se puede hacer con for(IFicha f:fichas) pero nos puede servir la variable i para trabajar diferentes los botones según su posición.
            Ficha f = fichas.get(i);
            BotonFicha boton = new BotonFicha(f);
            ImageIcon icono = new ImageIcon("Imagenes\\Horizontales\\" + f.getValorIzquierda() + "-" + f.getValorDerecha() + ".jpg");
            
            boton.setIcon(icono);

            boton.setEnabled(false);
            
            this.add(boton);
            
            //Si consigo saber que alto y ancho va a tener cada botón, se puede hacer una imagen a escala para ponerle como imagen
            //a iconoEscala en lugar de icono
            //int alto = ;
            //int ancho = ;
            //ImageIcon iconoEscala = new ImageIcon(icono.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_DEFAULT));
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
