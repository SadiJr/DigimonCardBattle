/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import br.ufsc.inf.leobr.cliente.Jogada;
import java.awt.Color;

/**
 *
 * @author pavei
 */
public enum Dupla implements Jogada{

    DUPLA_A(Color.BLUE), DUPLA_B(Color.RED);
      

    int placar;
    Color corDaDupla;

    private Dupla(Color corDaDupla) {

        this.corDaDupla = corDaDupla;
    }

    public static Dupla getDuplaAdiversaria(Dupla dupla){

        if (dupla.equals(DUPLA_A)){
            return DUPLA_B;
        }else if (dupla.equals(DUPLA_B)){
            return DUPLA_A;
        }

        return null;
    }


    public int getPlacar() {
        return placar;
    }

    public void setPlacar(int placar) {
        this.placar = placar;
    }

    public Color getCorDaDupla() {
        return corDaDupla;
    }

  


}
