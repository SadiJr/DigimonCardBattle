/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Poderes.Congelamento;
import Poderes.Cura;
import Poderes.Explosao;
import coliseumrpg.Personagem;
import Poderes.Poder;

/**
 *
 * @author Matheus
 */
public class Mago extends Personagem {

    protected int manaTotal;
    protected int manaAtual;

    public Mago() {
        super("Mago",
                "Inteligente e poderoso, controla a realidade com sua mente."
                + "Cuidadoso, prefere atacar a distância com seu cajado, "
                + "pode criar poderosas explosões, aprisionar seus inimigos"
                + " com gelo, ou curar quem estiver prestes a cair.", Classes.Mago,
                6, 2, 4, 1, "Mago.png", "MagoMorto.png");

        this.manaAtual = 6;
        this.manaTotal = 6;

        poderes = new Poder[3];

        poderes[0] = new Congelamento(this);
        poderes[1] = new Cura(this);
        poderes[2] = new Explosao(this);
    }

    public int getMana() {
        return manaAtual;
    }

    public void gastarMana(int custo) {
        manaAtual -= custo;
    }
}
