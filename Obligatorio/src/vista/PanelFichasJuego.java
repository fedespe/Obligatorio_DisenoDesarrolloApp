/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

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
public class PanelFichasJuego extends JPanel implements ActionListener{
    private BotonFicha marcado = null;
    private ControladorJuego controlador;
    private boolean destino;
    
    
    public PanelFichasJuego(ControladorJuego controlador, boolean d) {
        this.controlador = controlador;
        destino = d;
    }
    
    public void mostrar(ArrayList<Ficha> fichas){
        GridLayout layout = new GridLayout(1,fichas.size()); //Creo un GridLayout de una fila por tantas columnas como fichas tenga.
        setLayout(layout); //Metodo heredado de la clase JPanel.
        for(int i = 0; i < fichas.size(); i++){ 
            Ficha f = fichas.get(i);
            BotonFicha boton = new BotonFicha(f);
            ImageIcon icono = null;
            
            if(destino)
                //Con Web
                icono = new ImageIcon(getClass().getResource("/Imagenes/Horizontales/" + f.getValorIzquierda() + "-" + f.getValorDerecha() + ".jpg"));
                //Sin Web
                //icono = new ImageIcon("Imagenes/Horizontales/" + f.getValorIzquierda() + "-" + f.getValorDerecha() + ".jpg");
            else
                //ConWeb
                icono = new ImageIcon(getClass().getResource("/Imagenes/Verticales/" + f.getValorIzquierda() + "-" + f.getValorDerecha() + ".jpg"));
                //Sin Web
                //icono = new ImageIcon("Imagenes/Verticales/" + f.getValorIzquierda() + "-" + f.getValorDerecha() + ".jpg");
            boton.setIcon(icono);
            boton.addActionListener(this); //Me agrego en la lista de escuchadores del botón (Para cada botón creado)

            boton.setEnabled(!destino || i==0 || i==fichas.size()-1); //Solo dejo habilitados los botones de las puntas.
            
            this.add(boton);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BotonFicha origen = (BotonFicha)e.getSource(); // Me devuelve en quien hicieron click - Podría ser un JButton también
        Ficha ficha = origen.getFicha();
        
        if(destino)
            controlador.setDestino(ficha);
        else
            controlador.setOrigen(ficha);
        
        origen.marcar();
        
        if(marcado != null)
            marcado.marcar();
        
        marcado = origen;
    }
    
}
