/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import modelo.Usuario;

/**
 *
 * @author docenteFI
 */
public class BaseDatos {

    private static BaseDatos instancia = new BaseDatos();
    private Connection conexion;
    private Statement sentencia;

    public static BaseDatos getInstancia(){
        return instancia;
    }
    
    private BaseDatos() {
        //conectar("jdbc:mysql://localhost/obligatorio_disapp","root","root");
        //*****para que se conecte a la base desde web
        conectar("jdbc:mysql://localhost:3306/obligatorio_disapp","root","root");
        //********************************************
    }
    
    private void conectar(String url,String usuario,String pass){
        try {
            //*****para que se conecte a la base desde web
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
            //********************************************
            conexion = DriverManager.getConnection(url, usuario, pass);
            sentencia = conexion.createStatement();
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la base:" + ex.getMessage());
        }
    }
    
    public void desconectar(){
        try {
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
        }
    }
    
    public ResultSet consultar(String sql){
        try {
            ResultSet rs = sentencia.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            System.out.println("Error al consultar:" + ex.getMessage());
            System.out.println("SQL=" + sql);
            return null;
        }
    }
    
    public int modificar(String sql){
        try {
            return sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error al modificar:" + ex.getMessage());
            System.out.println("SQL=" + sql);
            return -1;
        }
    }
    
    public boolean transaccion(String[] sqls){
        try {
            conexion.setAutoCommit(false); //begin transaccion
            for(String sql:sqls){
                if(modificar(sql)==-1){
                    throw new SQLException("Abortar Transaccion");
                }
            }
            conexion.commit(); //guardo
            return true;
        } catch (SQLException ex) {
                System.out.println("Error transaccion:" + ex.getMessage());
            try {
                conexion.rollback();
                System.out.println("Rollback!");
            } catch (SQLException ex1) {
                    //No tendria que llegar nunca a este punto
                    System.out.println("INCENDIO!!!: " +  ex1.getMessage());
            }
           
        }finally{
            try {
                conexion.setAutoCommit(true); //end transaccion
            } catch (SQLException ex) {}
        }
        return false;
    }
}
