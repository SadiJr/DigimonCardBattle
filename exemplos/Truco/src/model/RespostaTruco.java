/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import br.ufsc.inf.leobr.cliente.Jogada;

/**
 *
 * @author pavei
 *
 *
 * Classe criada para o envio de dados pela rede sobre a resposta do truco;
 */
public class RespostaTruco implements Jogada {

    private boolean aceitou;
    private Jogador jogadorQueRespondeu;

    public RespostaTruco(boolean aceitou, Jogador jogadorQueRespondeu) {
        this.aceitou = aceitou;
        this.jogadorQueRespondeu = jogadorQueRespondeu;
    }

    

    public boolean isAceitou() {
        return aceitou;
    }

    public void setAceitou(boolean aceitou) {
        this.aceitou = aceitou;
    }

    public Jogador getJogadorQueRespondeu() {
        return jogadorQueRespondeu;
    }

    public void setJogadorQueRespondeu(Jogador jogadorQueRespondeu) {
        this.jogadorQueRespondeu = jogadorQueRespondeu;
    }


}
