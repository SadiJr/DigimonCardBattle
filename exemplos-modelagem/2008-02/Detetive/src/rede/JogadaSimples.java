/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rede;

import br.ufsc.inf.leobr.cliente.Jogada;

/**Esta classe representa uma jogada simples, onde ou o jogador passou a vez
 * por vontade própria, ou fez um palpite.
 * @author Rodrigo
 */
public class JogadaSimples implements Jogada {

    int[] posicao;
    String logDaPartida;

    public JogadaSimples(int[] posicao, String logDaPartida) {
        this.posicao = posicao;
        this.logDaPartida = logDaPartida;
    }



    public String getLogDaPartida() {
        return logDaPartida;
    }

    public void setLogDaPartida(String logDaPartida) {
        this.logDaPartida = logDaPartida;
    }

    public int[] getPosicao() {
        return posicao;
    }

    public void setPosicao(int[] posicao) {
        this.posicao = posicao;
    }




}
