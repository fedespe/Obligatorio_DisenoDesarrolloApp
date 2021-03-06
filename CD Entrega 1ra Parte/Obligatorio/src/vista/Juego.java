/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controladores.ControladorJuego;
import controladores.VistaJuego;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import logica.Jugador;
import logica.Partida;
import utilidades.ObligatorioException;


public class Juego extends javax.swing.JFrame implements VistaJuego{
    private ControladorJuego controlador;
    
    public Juego(Jugador j) throws ObligatorioException{
        initComponents();
        controlador = new ControladorJuego(this,j);
        controlador.agregarJugador();
        setLocationRelativeTo(null);
        setTitle("Partida");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        setBounds(0, 0, 1081, 769);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        controlador.salir();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        controlador.verificarJugadorAgregado();
    }//GEN-LAST:event_formWindowOpened

    @Override
    public void cerrar() {
        dispose();
    }
    
    @Override
    public void actualizarPaneles(Partida partida, Jugador jugador) {
        PanelInformacionJuego panelInfo;
        PanelFichasJuego panelFichasTablero;
        PanelFichasJuego panelFichasJugador;
   
        setContentPane(new JPanel());
        JPanel panelVentana = (JPanel)getContentPane(); //Obtengo el panel que viene por defecto en esta ventana
        
        GridLayout layout = new GridLayout(3,1);
        panelVentana.setLayout(layout); //Al panel que viene con la ventana, le cambio el layout por el creado
        
        panelInfo=new PanelInformacionJuego(controlador);
        panelVentana.add(panelInfo);
        
        panelFichasTablero = new PanelFichasJuego(controlador, true); //True para decirle que es el panel de destino (Las del tablero)
        panelVentana.add(panelFichasTablero);
        
        panelFichasJugador = new PanelFichasJuego(controlador, false); //False para decirle que es el panel de origen (las de la mano)
        panelVentana.add(panelFichasJugador);

        panelInfo.mostrar(partida, jugador);
        panelFichasTablero.mostrar(partida.getTablero());
        panelFichasJugador.mostrar(jugador.getFichas());
        
        this.validate();
    }

    @Override
    public void mensaje(String message) {
        JOptionPane pane = new JOptionPane(message);
        JDialog dialog = pane.createDialog(this,"Aviso");
        dialog.setModal(false); 
        dialog.setVisible(true);
    }
    
    @Override
    public void mensajeModal(String message) {
        JOptionPane pane = new JOptionPane(message);
        JDialog dialog = pane.createDialog(this,"Aviso");
        dialog.setModal(true); 
        dialog.setVisible(true);
    }

    @Override
    public void confirmarApuesta(String mensaje) {
        int retorno = JOptionPane.showConfirmDialog(this, mensaje);
        if(retorno==0){
            controlador.confirmarApuesta(true);
        }else{
            controlador.confirmarApuesta(false);
        }    
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
