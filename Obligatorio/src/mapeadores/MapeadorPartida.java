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
import logica.Ficha;
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
        //new java.sql.Timestamp(factura.getFecha().getTime())
    @Override
    public String[] getSqlInsert() {
        int n = partida.getMovimientos().size()-1;
        int cantSentencias = n*(n+1)/2+2+n;
        String[] sqls = new String[cantSentencias];
        sqls[0] = "INSERT INTO partida (oid,nombreJugador1,nombreJugador2) values " +
                  "("  + getOid() + ",'" + partida.getJugadores().get(0).getNombre() +
                "','" + partida.getJugadores().get(1).getNombre() + "')";
        generarMovimientos(sqls,1);
        return sqls;
    }
    
    private void generarMovimientos(String[] sqls,int desde){
        ArrayList<Movimiento> movimientos = partida.getMovimientos();
        for(int x=0; x<movimientos.size();x++){
            Movimiento m = movimientos.get(x);
            String nombreGanador = "";
            if(m.getGanador() != null)
                nombreGanador = m.getGanador().getNombre();
            
            //select last_insert_id(); - Es una función de MySQL que retorna el último ID generado
            
            sqls[x+desde] = "INSERT INTO movimiento (oid,numeroMovimiento,codPartida,ganador,fecha,pozo,nombreJugador) "+
                        "values (" + getOid() + "," + (x+1) + "," + acaVaElIdDeLaPartida + ",'" + nombreGanador + 
                        "','" + new java.sql.Timestamp(m.getFechaHora().getTime()) + "'," + m.getPozoApuestas() +
                        ",'" + m.getJugador() + "')";
        }
        desde = movimientos.size() + 1;
        generarTableros(sqls,desde);
    }
    
    private void generarTableros(String[] sqls, int desde) {
        ArrayList<Movimiento> movimientos = partida.getMovimientos();
        
        for(int x=1;x<movimientos.size(); x++){
            Movimiento m = movimientos.get(x);
            ArrayList<Ficha> tablero = m.getTablero();
            for(int i=0;i<tablero.size();i++){
                Ficha f = tablero.get(i);
                sqls[desde] = "INSERT INTO tableromovimiento (oid,posicionFicha,codPartida,numeroMovimiento,valorDer,valorIzq) "+
                        "values (" + getOid() +","+ (i+1) + "," + acaVaElIdDeLaPartida + "," + x + 
                        "," + f.getValorDerecha() + "," + f.getValorIzquierda() + ")";
                desde++;
            }
        }
    }

    @Override
    public String[] getSqlUpdate() {
        String[] sqls = new String[factura.getLineas().size() + 2];
        sqls[0] = "UPDATE Factura set numeroF=" + factura.getNumero() + "," +
                   "fecha='" + new Timestamp(factura.getFecha().getTime()) + 
                   "',datos='" + factura.getDatos() + "' WHERE oid=" + getOid(); 
        sqls[1] = "DELETE FROM linea where oid=" + getOid();
        generarMovimientos(sqls,2);
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
