/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Poderes;

import Classes.Guerreiro;
import Poderes.Poder;
import Poderes.TipoDePoderes.PoderAutoModificacao;
import Poderes.TipoDePoderes.Custo;
import coliseumrpg.Personagem;

/**
 * Utilizado pelo guerreiro, deve ser passado o próprio utilizador da magia,
 * para que
 *
 * @author Matheus
 */
public class Atletismo extends PoderAutoModificacao {

    public Atletismo() {
        super("Atletismo", "Devido a sua excelente forma física ele é capaz de correr um bloco a mais por turno, porém deverá abrir mão de realizar qualquer outra ação nesse turno", new Custo[]{Custo.AtoMaior, Custo.AtoMenor});
    }

    @Override
    public void usar(Personagem guerreiro) {
        ((Guerreiro) guerreiro).melhorarVelocidade();
    }

}
