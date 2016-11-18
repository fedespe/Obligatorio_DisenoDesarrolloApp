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
import logica.Jugador;
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

    public void setPartida(Partida partida) {
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
        sqls[0] = "INSERT INTO partida (oid,codPartida,nombreJugador1,nombreJugador2) values " +
                  "("  + getOid() + ",'" +partida.getId() +"','"+ partida.getJugadores().get(0).getNombre() +
                "','" + partida.getJugadores().get(1).getNombre() + "')";
        generarMovimientos(sqls,1);
        return sqls;
    }
    
    private void generarMovimientos(String[] sqls,int desde){
        ArrayList<Movimiento> movimientos = partida.getMovimientos();
        for(int x=0; x<movimientos.size();x++){
            Movimiento m = movimientos.get(x);
            String nombreGanador = "null";
            if(m.getGanador() != null){
                nombreGanador = m.getGanador().getNombre();          
                //select last_insert_id(); - Es una función de MySQL que retorna el último ID generado           
                sqls[x+desde] = "INSERT INTO movimiento (oid,numeroMovimiento,codPartida,ganador,fecha,pozo,nombreJugador) "+
                            "values (" + getOid() + "," + (x+1) + ",'" + partida.getId() + "','" + nombreGanador + 
                            "','" + new java.sql.Timestamp(m.getFechaHora().getTime()) + "'," + m.getPozoApuestas() +
                            ",'" + m.getJugador().getNombre() + "')";
            }else{
                sqls[x+desde] = "INSERT INTO movimiento (oid,numeroMovimiento,codPartida,ganador,fecha,pozo,nombreJugador) "+
                        "values (" + getOid() + "," + (x+1) + ",'" + partida.getId() + "'," + nombreGanador + 
                        ",'" + new java.sql.Timestamp(m.getFechaHora().getTime()) + "'," + m.getPozoApuestas() +
                        ",'" + m.getJugador().getNombre() + "')";
            }
                
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
                sqls[desde] = "INSERT INTO fichastableromovimiento (oid,numeroFichaJugada,codPartida,numeroMovimiento,valorDer,valorIzq) "+
                        "values (" + getOid() +","+ (i+1) + ",'" + partida.getId() + "'," + x + 
                        "," + f.getValorDerecha() + "," + f.getValorIzquierda() + ")";
                desde++;
            }
        }
    }

    @Override
    public String[] getSqlUpdate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[] getSqlDelete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSqlRestaurar() {
        //return "SELECT * FROM Factura f,Linea l WHERE f.oid=l.oid AND f.oid=" + getOid() ;
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void leer(ResultSet rs) throws SQLException {
        if(getOid()==0){
            cargarPartida(rs);
        }
        cargarMovimientosFichas(rs);
    }
    //FATA ARREGLAR LA FECHA Y LA HORA AL LEVANTARLA
    private void cargarMovimientosFichas(ResultSet rs) throws SQLException{
        Jugador ganador= null;
        Jugador j1= partida.getJugadores().get(0);
        Jugador j2= partida.getJugadores().get(1);
        ArrayList<Ficha> tablero= new ArrayList();
        Movimiento ultimoMov =partida.getMovimientos().get((partida.getMovimientos().size()-1));
        
        if(rs.getInt("m.numeroMovimiento")==1){
            Ficha ficha = new Ficha(rs.getInt("f.valorIzq"), rs.getInt("f.valorDer"));
            int numeroFicha=rs.getInt("f.numeroFichaJugada");
            if(numeroFicha==1){   
                tablero.add(ficha);
                Movimiento m=new Movimiento(ganador,rs.getInt("m.pozo"), j1,tablero);
                //movimientos.add(m);
                partida.getMovimientos().add(m);
            }else if(numeroFicha!=0){             
                ultimoMov.getTablero().add(ficha);
                ultimoMov.setGanador(ganador);                
                if(ultimoMov.getJugador().getNombre().equals(j1.getNombre())){
                    ultimoMov.setJugador(j2);
                }else{
                    ultimoMov.setJugador(j1);
                }
            }  
            partida.setGanador(ganador);
            partida.setPozoApuestas(rs.getInt("m.pozo"));
        }
        if(rs.getInt("j.oid")!=0){
                ganador=new Jugador();
                ganador.setOid(rs.getInt("j.oid"));
                ganador.setNombreCompleto(rs.getString("j.nombreCompleto"));
                partida.setGanador(ganador);
                ultimoMov.setGanador(ganador);
        }
        if(ultimoMov.getPozoApuestas()!=rs.getInt("m.pozo")){
            int numMov=rs.getInt("m.numeroMovimiento");
            partida.setPozoApuestas(rs.getInt("m.pozo"));
            for(int i=numMov-1;i<partida.getMovimientos().size();i++){
                partida.getMovimientos().get(i).setPozoApuestas(rs.getInt("m.pozo"));
            }
        }
        //HAY UN DETALLE AL LEVANTAR LA HORA QUE TE AGREGA UN CERO AL MOSTRAR ES POR EL TIMESTAMP NO LO BUSQUE
        partida.getMovimientos().get(rs.getInt("m.numeroMovimiento")-1).setFechaHora(rs.getTimestamp("m.fecha"));
        
    }
    private void cargarPartida(ResultSet rs) throws SQLException{
        partida.setOid(rs.getInt("p.oid"));
        partida.setId(rs.getString("p.codPartida"));
        Jugador ganador= null;
        
        //Carga de Jugadores
        Jugador j1=new Jugador(rs.getString("j1.nombre"),rs.getString("j1.pass") , rs.getString("j1.nombreCompleto"), rs.getInt("j1.saldo"));
        j1.setOid(rs.getInt("j1.oid"));
        Jugador j2=new Jugador(rs.getString("j2.nombre"),rs.getString("j2.pass") , rs.getString("j2.nombreCompleto"), rs.getInt("j2.saldo"));
        j1.setOid(rs.getInt("j2.oid"));
        ArrayList<Jugador> jugadores=new ArrayList();
        jugadores.add(j1);
        jugadores.add(j2);
        partida.setJugadores(jugadores);

        if(rs.getInt("j.oid")!=0){
            ganador=new Jugador();
            ganador.setOid(rs.getInt("j.oid"));
            ganador.setNombreCompleto(rs.getString("j.nombreCompleto"));
        }    
        ArrayList<Ficha> tablero= new ArrayList();
        Movimiento m=new Movimiento(ganador,rs.getInt("m.pozo"), j1,tablero);
        m.setFechaHora(rs.getDate("m.fecha"));
        partida.getMovimientos().add(m);
        partida.setGanador(ganador);
        partida.setPozoApuestas(rs.getInt("m.pozo"));
        
    }

    @Override
    public String getSqlSelect() {
        return "SELECT DISTINCT  * FROM  partida p LEFT JOIN fichastableromovimiento f " +
                "ON p.oid=f.oid LEFT JOIN movimiento m ON m.oid=p.oid "+//AND m.numeroMovimiento=1 " +
                "LEFT JOIN jugadores j ON j.nombre=m.ganador " +"LEFT JOIN jugadores j1 ON p.nombreJugador1=j1.nombre " +
                "LEFT JOIN jugadores j2 ON p.nombrejugador2=j2.nombre "+
                "ORDER BY p.oid,m.numeroMovimiento,f.numeroMovimiento,f.numeroFichaJugada";
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
