/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Poderes;

import Classes.Mago;
import Erros.ErroTolo;
import Erros.NenhumPersonagemNoQuadranteException;
import Mapa.Lugar;
import Poderes.TipoDePoderes.Custo;

/**
 *
 * @author Matheus
 */
public class Congelamento extends Magia {

    public Congelamento(Mago caster) {
        super("Congelamento",
                "Criando cristais de gelo ao redor de seu inimigo,"
                + " o mago é capaz de imobiliza-lo por uma rodada.",
                1, 2, caster, new Custo[]{Custo.AtoMenor});
    }

    @Override
    public void usar(Lugar alvo) {
        temManaSuficiente(caster);
        if (alvo.getPersonagem() != null) {
            if (!alvo.getPersonagem().equals(caster)) {
                alvo.getPersonagem().incapacitar(1);
                caster.gastarMana(custoMana);
            } else {
                throw new ErroTolo("Não congele a si mesmo, idiota.");
            }
        } else {
            throw new NenhumPersonagemNoQuadranteException();
        }
    }
}
