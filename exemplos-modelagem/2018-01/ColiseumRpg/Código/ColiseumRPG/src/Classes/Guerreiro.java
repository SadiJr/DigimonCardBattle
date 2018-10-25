/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Poderes.Atletismo;
import Poderes.Poder;
import coliseumrpg.Personagem;

/**
 *
 * @author Matheus
 */
public class Guerreiro extends Personagem {

    public Guerreiro() {
        super("Guerreiro", "Treinado para batalha e em excelente forma física, "
                + "o guerreiro é extremamente ágil e forte. Possui uma poderosa"
                + " espada capaz de matar seus inimigos com poucos golpes,"
                + " mas terá que alcança-los primeiro.", Classes.Guerreiro,
                10, 3, 1, 3,  "Guerreiro.png", "GuerreiroMorto.png");
        this.poderes = new Poder[1];
        poderes[0] = new Atletismo();
    }

    public void melhorarVelocidade() {
        velocidadeAtual += 1;
    }
}
