/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Poderes;

import Classes.Mago;
import Erros.OutOfManaException;
import Mapa.Lugar;
import Poderes.TipoDePoderes.PoderLocalAlvo;
import Poderes.Poder;
import Poderes.TipoDePoderes.Custo;

/**
 *
 * @author Matheus
 */
public abstract class Magia extends PoderLocalAlvo {

    protected int custoMana;
    protected int alcance;
    protected Mago caster;

    protected Magia(String nome, String descricao, int custoMana, int alcance, Mago caster, Custo[] custo) {
        super(nome, descricao, custo);
        this.custoMana = custoMana;
        this.alcance = alcance;
        this.caster = caster;
    }

    @Override
    public int getAlcance() {
        return this.alcance;
    }

    protected void temManaSuficiente(Mago caster) {
        if (caster.getMana() < custoMana) {
            throw new OutOfManaException("Ops, parece que você não tem mais mana para usar essa habilidade.");
        }
    }
}
