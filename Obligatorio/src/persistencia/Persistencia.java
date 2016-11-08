/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author docenteFI
 */
public class Persistencia {
    private BaseDatos bd = BaseDatos.getInstancia();
    
    public int proximoOid(){
        String sql = "SELECT valor FROM parametros WHERE nombre = 'oid'";
        ResultSet rs = bd.consultar(sql);
        try {
            if(rs.next()){
                int oidActual = rs.getInt("valor");
                sql = "UPDATE parametros set valor=" + (oidActual + 1);
                bd.modificar(sql);
                return oidActual;
            }else System.out.println("FALTA AGREGAR REGISTRO OID");
        } catch (SQLException ex) {
            System.out.println("Error al obtener proximo oid:" + ex.getMessage());
        }
        return -1;
    }
    
    public void guardar(Mapeador p){
        if(p.getOid()==0){
            insertar(p);
        }else actualizar(p);
    }

    private void insertar(Mapeador p) {
        int oid = proximoOid();
        p.setOid(oid);
        String[] sqls = p.getSqlInsert();
        if(!bd.transaccion(sqls)){
           p.setOid(0);
           System.out.println("Error al guardar");//Tendriamos que tirar exceptions
        }
        
    }

    private void actualizar(Mapeador p) {
        String[] sqls = p.getSqlUpdate();
        if(!bd.transaccion(sqls)){
                System.out.println("Error al actualizar");//Tendriamos que tirar exceptions
        }
      
    }
    public void borrar(Mapeador p){
        String[] sqls = p.getSqlDelete();
        if(bd.transaccion(sqls)){
            p.setOid(0);
        }else{   
            System.out.println("Error al borrar");//Tendriamos que tirar exceptions 
        }
    }
    public void restaurar(Mapeador p){
        String sql = p.getSqlRestaurar();
        ResultSet rs = bd.consultar(sql);
        try {
            while(rs.next()){
                p.leer(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Error al restaurar:" + ex.getMessage());
        }
    }
    public ArrayList obtenerTodos(Mapeador p){
        return buscar(p,"");
    }
    public ArrayList buscar(Mapeador p,String where){
        String sql = p.getSqlSelect() + " " + where ;
        ResultSet rs = bd.consultar(sql);
        ArrayList resultado = new ArrayList();
        Object o;
        int oid,oidAnt=-1;
        try {
            while(rs.next()){
                oid = rs.getInt("oid"); //asumo que se llama asi
                if(oid!=oidAnt){
                    p.crearNuevo();
                    o = p.getObjeto();
                    resultado.add(o);
                    oidAnt = oid;
                }
                p.leer(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer todos:" + ex.getMessage());
        }
        return resultado;
    }
    
}
