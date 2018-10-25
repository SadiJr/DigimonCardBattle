/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Poderes;

import Classes.Mago;
import Mapa.Lugar;
import Poderes.TipoDePoderes.Custo;
import coliseumrpg.Personagem;

/**
 *
 * @author Matheus
 */
public class Explosao extends Magia {

    private final int dano;

    public Explosao(Mago caster) {
        super("Explosão",
                "Causa uma poderosa explosão de chamas no local,"
                + " causando bastante dano e destruindo"
                + " qualquer objeto no quadrante.",
                3, 3, caster, new Custo[]{Custo.AtoMaior, Custo.AtoMenor});
        this.dano = 3;
    }

    @Override
    public void usar(Lugar alvo) {
        temManaSuficiente(caster);
        alvo.getPersonagem().receberDano(dano);
        alvo.destruir();
        caster.gastarMana(custoMana);
    }

}
