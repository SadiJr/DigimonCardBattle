/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Poderes.TipoDePoderes;

import Mapa.Lugar;
import Poderes.Poder;

public abstract class PoderLocalAlvo extends Poder{

    public PoderLocalAlvo(String nome, String descricao, Custo[] custo) {
        super(nome, descricao, custo);
    }

    public abstract void usar(Lugar lugar);

    public abstract int getAlcance();
}
