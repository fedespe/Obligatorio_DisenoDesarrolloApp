/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iu;

import controladores.ControladorJuego;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import logica.Ficha;

/**
 *
 * @author usuario
 */
public class PanelFichas extends JPanel implements ActionListener{
    private BotonFicha marcado = null;
    private ControladorJuego controlador;
    private boolean destino;

    public PanelFichas(ControladorJuego controlador, boolean d) {
        this.controlador = controlador;
        destino = d;
    }
    
    public void mostrar(ArrayList<Ficha> fichas){
        GridLayout layout = new GridLayout(1,fichas.size()); //Creo un GridLayout de una fila por tantas columnas como fichas tenga.
        setLayout(layout); //Metodo heredado de la clase JPanel.
        for(int i = 0; i < fichas.size(); i++){ //Se puede hacer con for(IFicha f:fichas) pero nos puede servir la variable i para trabajar diferentes los botones según su posición.
            Ficha f = fichas.get(i);
            BotonFicha boton = new BotonFicha(f);
            ImageIcon icono = new ImageIcon("Imagenes\\" + f.getValorIzquierda() + "-" + f.getValorDerecha() + ".jpg");
            boton.setIcon(icono);
            boton.addActionListener(this); //Me agrego en la lista de escuchadores del botón (Para cada botón creado)
            boton.setEnabled(!destino || i==0 || i==fichas.size()-1); //Solo dejo habilitados los botones de las puntas.
            this.add(boton);
            
            //Si consigo saber que alto y ancho va a tener cada botón, se puede hacer una imagen a escala para ponerle como imagen
            //a iconoEscala en lugar de icono
            //int alto = ;
            //int ancho = ;
            //ImageIcon iconoEscala = new ImageIcon(icono.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_DEFAULT));
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BotonFicha origen = (BotonFicha)e.getSource(); // Me devuelve en quien hicieron click - Podría ser un JButton también
        Ficha ficha = origen.getFicha();
        System.out.println("Click en: " + ficha.getValorIzquierda() + " | " + ficha.getValorDerecha());
        
        if(this.destino)
            controlador.setDestino(ficha);
        else
            controlador.setOrigen(ficha);
        
        origen.marcar();
        
        if(marcado != null)
            marcado.marcar();
        
        marcado = origen;
    }
    
}
