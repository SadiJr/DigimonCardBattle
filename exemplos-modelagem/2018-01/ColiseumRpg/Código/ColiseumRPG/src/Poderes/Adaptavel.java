/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Poderes;

import coliseumrpg.Personagem;
import Poderes.TipoDePoderes.PoderAutoModificacao;
import Poderes.Poder;
import Poderes.TipoDePoderes.Custo;

/**
 *
 * @author Matheus
 */
public class Adaptavel extends PoderAutoModificacao {

    public Adaptavel() {
        super("Adaptável",
                "Graças a seus anos de experiência em caça ele é capaz de lutar"
                + " tanto com sua adaga que causa 2 de dano,"
                + " tanto com seu arco que casa um de dano.",
                new Custo[]{Custo.AtoMenor});
    }

    @Override
    public void usar(Personagem personagem) {
        if (personagem.getAlcance() == 1) {
            personagem.setAlcance(3);
            personagem.setDano(1);
        } else {
            personagem.setAlcance(1);
            personagem.setDano(2);
        }
    }
}
