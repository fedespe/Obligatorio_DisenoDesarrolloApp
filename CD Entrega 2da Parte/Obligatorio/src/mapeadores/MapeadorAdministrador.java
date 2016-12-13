/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeadores;

import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Administrador;
import persistencia.Mapeador;

/**
 *
 * @author usuario
 */
public class MapeadorAdministrador implements Mapeador{

    private Administrador a;

    public void setAdministrador(Administrador a) {
        this.a = a;
    }
    
    @Override
    public int getOid() {
        return a.getOid();
    }

    @Override
    public void setOid(int oid) {
        a.setOid(oid);
    }

    @Override
    public String[] getSqlInsert() {
        String[] sqls = {"INSERT INTO administradores (oid, nombre, pass, nombreCompleto) "
                + "VALUES (" + getOid() +",'"+ a.getNombre() +"','" + a.getPassword() +
                ",'"+ a.getNombreCompleto() +")"};
        return sqls;
    }

    @Override
    public String[] getSqlUpdate() {
        String[] sqls = {"UPDATE administradores set nombre='" + a.getNombre() + "'," +
               "pass='" + a.getPassword() + "'," + 
               "nombreCompleto='" + a.getNombreCompleto() +
               "WHERE oid=" + getOid()};
        return sqls;
    }

    @Override
    public String[] getSqlDelete() {
        String[] sqls = {"DELETE FROM administradores WHERE oid=" + a.getOid()};
        return sqls;
    }

    @Override
    public String getSqlRestaurar() {
        return "SELECT * FROM administradores where oid=" + a.getOid();
    }

    @Override
    public void leer(ResultSet rs) throws SQLException {
        a.setOid(rs.getInt("oid"));
        a.setNombre(rs.getString("nombre"));
        a.setPassword(rs.getString("pass"));
        a.setNombreCompleto(rs.getString("nombreCompleto"));
    }

    @Override
    public String getSqlSelect() {
        return "SELECT * FROM administradores";
    }

    @Override
    public void crearNuevo() {
        a = new Administrador();
    }

    @Override
    public Object getObjeto() {
        return a;
    }
    
}
