/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Poderes;

import Classes.Mago;
import Erros.NenhumPersonagemNoQuadranteException;
import Mapa.Lugar;
import Poderes.TipoDePoderes.Custo;

/**
 *
 * @author Matheus
 */
public class Cura extends Magia {

    private int cura;

    public Cura(Mago caster) {
        super("Curar", "Usa as forças do mundo ao seu redor para restaurar"
                + " o alvo da magia em 2 pontos de vida.",
                1, 2, caster, new Custo[]{Custo.AtoMenor});
        this.cura = 2;
    }

    /**
     * Pode ser usado em qualquer personagem idependente do time, tem um alcance
     * máximo de
     *
     * @param alvo quadrante onde esta o personagem a ser curado
     */
    @Override
    public void usar(Lugar alvo) {
        temManaSuficiente(caster);
        if (alvo.getPersonagem() != null) {
            alvo.getPersonagem().curar(cura);
            caster.gastarMana(custoMana);
        } else {
            throw new NenhumPersonagemNoQuadranteException();
        }
    }
}
