/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Poderes;

import Erros.SemMaisArmadilhasException;
import Poderes.TipoDePoderes.PoderLocalAlvo;
import Mapa.Lugar;
import NetGames.Time;
import Poderes.TipoDePoderes.Custo;

/**
 *
 * @author Matheus
 */
public final class Armadilhas extends PoderLocalAlvo {

    protected int quantidade;
    protected int alcance;

    private Time time;

    public Armadilhas() {
        super("Armadilhas", "Com seu pensamento estratégico e mãos precisas"
                + " consegue montar uma armadilha invisível para os inimigos,"
                + " que causará 2 de dano ao inimigo que cair nela", new Custo[]{Custo.AtoMenor});
        this.quantidade = 3;
        this.alcance = 2;
    }

    /**
     * utilizado para colocaruma armadilha no local selecionado.
     *
     * @param alvo Local para colocar a armadilha
     */
    @Override
    public void usar(Lugar alvo) {
        if (quantidade > 0) {
            alvo.colocar(new Armadilha(time));
            quantidade--;
        } else {
            throw new SemMaisArmadilhasException("Você já posicionou todas as suas armadilhas!");
        }
    }

    @Override
    public int getAlcance() {
        return this.alcance;
    }

    public void setTime(Time time) {
        this.time=time;
    }

}
