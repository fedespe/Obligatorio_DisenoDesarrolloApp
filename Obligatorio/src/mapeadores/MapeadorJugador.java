/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeadores;

import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Jugador;
import persistencia.Mapeador;

/**
 *
 * @author docenteFI
 */
public class MapeadorJugador implements Mapeador{

    private Jugador j;


    public void setJugador(Jugador j) {
        this.j = j;
    }
            
    @Override
    public int getOid() {
        return j.getOid();
    }

    @Override
    public void setOid(int oid) {
        j.setOid(oid);
    }

    @Override
    public String[] getSqlInsert() {
        String[] sqls = {"INSERT INTO jugadores (oid, nombre, pass, nombreCompleto, saldo) "
                + "VALUES (" + getOid() +",'"+ j.getNombre() +"','" + j.getPassword() +
                ",'"+ j.getNombreCompleto() + "'," + j.getSaldo() +")"};
        return sqls;
    }

    @Override
    public String[] getSqlUpdate() {
        String[] sqls = {"UPDATE jugadores set nombre='" + j.getNombre() + "'," +
               "pass='" + j.getPassword() + "'," + 
               "nombreCompleto='" + j.getNombreCompleto()+ "'," + "saldo=" + j.getSaldo() +
               "WHERE oid=" + getOid()};
        return sqls;
    }

    @Override
    public String[] getSqlDelete() {
        
        String[] sqls = {"DELETE FROM jugadores WHERE oid=" + j.getOid()};
        return sqls;
    }

    @Override
    public String getSqlRestaurar() {
        return "SELECT * FROM jugadores where oid=" + j.getOid();
    }
    
    @Override
    public String getSqlSelect() {
        return "SELECT * FROM jugadores";
    }
    
    @Override
    public void leer(ResultSet rs) throws SQLException {
        j.setOid(rs.getInt("oid"));
        j.setNombre(rs.getString("nombre"));
        j.setPassword(rs.getString("pass"));
        j.setNombreCompleto(rs.getString("nombreCompleto"));
        j.setSaldo(rs.getDouble("saldo"));
    }

    @Override
    public void crearNuevo() {
        j = new Jugador();
    }

    @Override
    public Object getObjeto() {
        return j;
    }
}
