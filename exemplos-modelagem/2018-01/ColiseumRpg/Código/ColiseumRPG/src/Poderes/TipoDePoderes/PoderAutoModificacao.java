/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Poderes.TipoDePoderes;

import coliseumrpg.Personagem;
import Poderes.Poder;

public abstract class PoderAutoModificacao extends Poder{

    public PoderAutoModificacao(String nome, String descricao, Custo[] custo) {
        super(nome, descricao, custo);
    }

    public abstract void usar(Personagem personagem);
}
