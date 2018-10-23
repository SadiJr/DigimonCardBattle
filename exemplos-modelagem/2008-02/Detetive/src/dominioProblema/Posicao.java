/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominioProblema;

import java.io.Serializable;

/**
 *
 * @author Rodrigo
 */
public class Posicao implements Serializable {

    private Jogador ocupante;
    private int comodo;

    public Posicao() {
       
    }

    /**
     * Get the value of comodo
     *
     * @return the value of comodo
     */
    public int getComodo() {
        return comodo;
    }

    /**
     * Set the value of comodo
     *
     * @param comodo new value of comodo
     */
    public void setComodo(int comodo) {
        this.comodo = comodo;
    }


    /**
     * Get the value of ocupante
     *
     * @return the value of ocupante
     */
    public Jogador getOcupante() {
        return ocupante;
    }

    /**
     * Set the value of ocupante
     *
     * @param ocupante new value of ocupante
     */
    public void setOcupante(Jogador ocupante) {
        this.ocupante = ocupante;
    }


}
