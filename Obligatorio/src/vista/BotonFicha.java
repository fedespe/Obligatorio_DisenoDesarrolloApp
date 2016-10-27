/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import javax.swing.JButton;
import logica.Ficha;

/**
 *
 * @author usuario
 */
public class BotonFicha extends JButton{
    private Ficha ficha;
    private boolean marcado;

    public Ficha getFicha() {
        return ficha;
    }

    public BotonFicha(Ficha ficha) { 
        this.ficha = ficha;
        setBackground(Color.white);
    }

    public void marcar() {
        if(!marcado){
            setBackground(Color.gray);
        }
        else{
            setBackground(Color.white);
        }
        marcado = !marcado;
    }
    
}
