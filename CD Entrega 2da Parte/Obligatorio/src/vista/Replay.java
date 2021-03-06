/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controladores.ControladorReplay;
import controladores.VistaReplay;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import logica.Movimiento;
import logica.Partida;

/**
 *
 * @author usuario
 */
public class Replay extends javax.swing.JDialog implements VistaReplay{
    
    private ControladorReplay controlador;
    /**
     * Creates new form Replay
     * @param parent
     * @param modal
     * @param partida
     */
    public Replay(java.awt.Frame parent, boolean modal, Partida partida){
        super(parent, modal);
        initComponents();
        controlador = new ControladorReplay(this, partida);
        setTitle("Replay");
        setLocationRelativeTo(null);
        controlador.inicializar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1088, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 436, Short.MAX_VALUE)
        );

        setBounds(0, 0, 1106, 483);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void actualizarPaneles(Movimiento movimiento) {
        PanelInformacionReplay panelInfo;
        PanelFichasReplay panelFichasTablero;
   
        setContentPane(new JPanel());
        JPanel panelVentana = (JPanel)getContentPane(); //Obtengo el panel que viene por defecto en esta ventana
        
        GridLayout layout = new GridLayout(2,1);
        panelVentana.setLayout(layout); //Al panel que viene con la ventana, le cambio el layout por el creado
        
        panelInfo=new PanelInformacionReplay(this.controlador);
        panelVentana.add(panelInfo);
        
        panelFichasTablero = new PanelFichasReplay(this.controlador);
        panelVentana.add(panelFichasTablero);

        
        panelFichasTablero.mostrar(movimiento.getTablero());
        panelInfo.mostrar(movimiento);
        
        this.validate();
    }

    @Override
    public void error(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
