/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeadores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import logica.Movimiento;
import logica.Partida;
import persistencia.Mapeador;

/**
 *
 * @author docenteFI
 */
public class MapeadorPartida implements Mapeador{
    
    private Partida partida;

    public MapeadorPartida() {
    }

    public MapeadorPartida(Partida partida) {
        this.partida = partida;
    }

    public void setFactura(Partida partida) {
        this.partida = partida;
    }
    
    @Override
    public int getOid() {
        return partida.getOid();
    }

    @Override
    public void setOid(int oid) {
        partida.setOid(oid);
    }

    @Override
    public String[] getSqlInsert() {
        ArrayList<Movimiento> lineas = partida.getMovimientos();
        String[] sqls = new String[lineas.size() + 1];
        sqls[0] = "INSERT INTO factura (oid,numeroF,fecha,datos) values " +
                  "("  + getOid() + "," + factura.getNumero() 
                + ",'" + new java.sql.Timestamp(factura.getFecha().getTime()) + "','"
                + factura.getDatos() + "')";
        generarLineas(sqls,1);
        return sqls;
    }
    private void generarLineas(String[] sqls,int desde){
        ArrayList<Linea> lineas = factura.getLineas();
        for(int x=0; x<lineas.size();x++){
            Linea l = lineas.get(x);
            sqls[x+desde] = "INSERT INTO linea (oid,numeroF,numeroL,producto,cantidad) "+
                        "values (" + getOid() +","+ factura.getNumero()+"," + (x+1) + 
                        ",'" + l.getProducto() + "'," + l.getCantidad() +")";
        }
    }

    @Override
    public String[] getSqlUpdate() {
        String[] sqls = new String[factura.getLineas().size() + 2];
        sqls[0] = "UPDATE Factura set numeroF=" + factura.getNumero() + "," +
                   "fecha='" + new Timestamp(factura.getFecha().getTime()) + 
                   "',datos='" + factura.getDatos() + "' WHERE oid=" + getOid(); 
        sqls[1] = "DELETE FROM linea where oid=" + getOid();
        generarLineas(sqls,2);
        return sqls;
    }

    @Override
    public String[] getSqlDelete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSqlRestaurar() {
        
        return "SELECT * FROM Factura f,Linea l WHERE f.oid=l.oid AND f.oid=" + getOid() ;

    }

    @Override
    public void leer(ResultSet rs) throws SQLException {
        if(getOid()==0){
            cargarCabezal(rs);
        }
        cargarLinea(rs);
    }
    private void cargarLinea(ResultSet rs) throws SQLException{
        factura.agregar(rs.getString("producto"), rs.getInt("cantidad"));
    }
    private void cargarCabezal(ResultSet rs) throws SQLException{
        factura.setOid(rs.getInt("oid"));
        factura.setNumero(rs.getInt("numeroF"));
        factura.setFecha(rs.getTimestamp("fecha"));
        factura.setDatos(rs.getString("datos"));
        
    }

    @Override
    public String getSqlSelect() {
        return "SELECT * FROM Factura f,Linea l WHERE f.oid=l.oid" + " ORDER by f.oid";
    }

    @Override
    public void crearNuevo() {
        partida = new Partida();
    }

    @Override
    public Object getObjeto() {
        return partida;
    }
    
    
}
