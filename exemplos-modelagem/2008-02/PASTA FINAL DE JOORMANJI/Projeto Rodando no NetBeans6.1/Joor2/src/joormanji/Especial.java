/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package joormanji;

/**
 *
 * @author adm
 */
public class Especial extends Evento{
    
    private boolean usado;
    
    
    public Especial(String texto){
        super(texto);
        this.usado = false;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }
    
    
}
