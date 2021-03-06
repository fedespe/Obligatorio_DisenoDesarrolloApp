/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controladores.ControladorJuego;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import logica.Jugador;
import logica.Partida;

/**
 *
 * @author usuario
 */
public class PanelInformacionJuego extends javax.swing.JPanel {
    private ControladorJuego controlador;
    
    /**
     * Creates new form PanelInformacion
     */
    public PanelInformacionJuego(ControladorJuego cj) {
        initComponents();
        controlador = cj;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        TxtApuestaTotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtMontoApuesta = new javax.swing.JTextField();
        btnApostar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtFichasDisponibles = new javax.swing.JTextField();
        btnRobar = new javax.swing.JButton();
        txtNombreJugador = new javax.swing.JTextField();
        txtNombreRival = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnDescartar = new javax.swing.JButton();
        lblTurno = new javax.swing.JLabel();
        lblMensaje = new javax.swing.JLabel();

        setLayout(null);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Total Apostado");
        add(jLabel5);
        jLabel5.setBounds(430, 60, 121, 22);

        TxtApuestaTotal.setEditable(false);
        TxtApuestaTotal.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        add(TxtApuestaTotal);
        TxtApuestaTotal.setBounds(560, 60, 120, 28);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Su Saldo");
        add(jLabel4);
        jLabel4.setBounds(700, 60, 80, 22);

        txtSaldo.setEditable(false);
        txtSaldo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        add(txtSaldo);
        txtSaldo.setBounds(790, 60, 120, 28);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Monto Apuesta");
        add(jLabel3);
        jLabel3.setBounds(430, 110, 120, 22);

        TxtMontoApuesta.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        add(TxtMontoApuesta);
        TxtMontoApuesta.setBounds(560, 110, 120, 28);

        btnApostar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnApostar.setText("Apostar");
        btnApostar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApostarActionPerformed(evt);
            }
        });
        add(btnApostar);
        btnApostar.setBounds(700, 110, 120, 31);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Fichas Disponibles");
        add(jLabel2);
        jLabel2.setBounds(10, 190, 151, 22);

        txtFichasDisponibles.setEditable(false);
        txtFichasDisponibles.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        add(txtFichasDisponibles);
        txtFichasDisponibles.setBounds(180, 190, 80, 28);

        btnRobar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnRobar.setText("Robar");
        btnRobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRobarActionPerformed(evt);
            }
        });
        add(btnRobar);
        btnRobar.setBounds(270, 190, 130, 31);

        txtNombreJugador.setEditable(false);
        txtNombreJugador.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        add(txtNombreJugador);
        txtNombreJugador.setBounds(90, 60, 320, 28);

        txtNombreRival.setEditable(false);
        txtNombreRival.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        add(txtNombreRival);
        txtNombreRival.setBounds(90, 110, 320, 28);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Jugador:");
        add(jLabel6);
        jLabel6.setBounds(10, 60, 80, 22);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Rival:");
        add(jLabel7);
        jLabel7.setBounds(30, 110, 50, 22);

        btnDescartar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnDescartar.setText("Descartar Ficha");
        btnDescartar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescartarActionPerformed(evt);
            }
        });
        add(btnDescartar);
        btnDescartar.setBounds(560, 190, 340, 31);

        lblTurno.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        add(lblTurno);
        lblTurno.setBounds(20, 10, 360, 40);

        lblMensaje.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(0, 204, 0));
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        add(lblMensaje);
        lblMensaje.setBounds(410, 10, 500, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRobarActionPerformed
        controlador.robar();
    }//GEN-LAST:event_btnRobarActionPerformed

    private void btnApostarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApostarActionPerformed
        try {
            controlador.apostar(Double.parseDouble(TxtMontoApuesta.getText()));
        }
        catch (java.lang.NumberFormatException ex) {
            JOptionPane pane = new JOptionPane("Ingreso no valido");
            JDialog dialog = pane.createDialog(this,"Aviso");
            dialog.setLocationRelativeTo(null);
            dialog.setModal(false); 
            dialog.setVisible(true);
        }
        
    }//GEN-LAST:event_btnApostarActionPerformed

    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed
        controlador.descartar();
    }//GEN-LAST:event_btnDescartarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtApuestaTotal;
    private javax.swing.JTextField TxtMontoApuesta;
    private javax.swing.JButton btnApostar;
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnRobar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JTextField txtFichasDisponibles;
    private javax.swing.JTextField txtNombreJugador;
    private javax.swing.JTextField txtNombreRival;
    private javax.swing.JTextField txtSaldo;
    // End of variables declaration//GEN-END:variables


    public void mostrar(Partida partida, Jugador jugador) {
        
        for(Jugador j : partida.getJugadores()){
            if(j==jugador){
                txtNombreJugador.setText(j.getNombreCompleto());
                txtSaldo.setText(jugador.getSaldo()+"");
            }else{
                txtNombreRival.setText(j.getNombreCompleto());
            }
        }
        TxtApuestaTotal.setText(partida.getPozoApuestas()+"");
        txtFichasDisponibles.setText(partida.getLibres().size()+"");
        lblTurno.setText("Turno de "+partida.getTurno().getNombreCompleto());
        if(partida.getJugadores().size()<2){
            lblMensaje.setText("Esperando jugador rival");
        }else if(partida.getGanador() != null){
            lblMensaje.setText("El Ganador es: " + partida.getGanador().getNombreCompleto());
        }
//        else{
//            lblMensaje.setText(partida.getTiempoTurno().getSegundos()+"");
//        }
    }
    public void actualizarSegundos(Partida partida){
        if(partida!=null){
            lblMensaje.setText("Tiempo Turno: "+partida.getTiempoTurno().getSegundos()+" Tiempo Apuesta: "+partida.getTiempoApuesta().getSegundos()+" ");
        }
    }
}
