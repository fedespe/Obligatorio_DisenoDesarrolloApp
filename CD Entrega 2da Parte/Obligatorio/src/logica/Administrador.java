/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import mapeadores.MapeadorAdministrador;
import persistencia.Persistencia;

/**
 *
 * @author usuario
 */
public class Administrador extends Usuario {
    public Administrador(String n, String p, String np){
        super(n,p,np);
    }
    public Administrador(){
        super();
    }
    
    public void actualizarEnBase() {
        Persistencia persistencia = new Persistencia();
        MapeadorAdministrador administradorMapper = new MapeadorAdministrador();
        administradorMapper.setAdministrador(this);
        persistencia.guardar(administradorMapper);
    }
}
