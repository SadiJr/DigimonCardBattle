/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rede;

import br.ufsc.inf.leobr.cliente.Jogada;
import dominioProblema.*;

/** Esta classe representa uma jogada no NetGamesNRT
 * mais precisamente aquela onde uma partida é iniciada
 * pelo outro lado, que passa para seu oponente as instancia dos
 * jogadores possuindo as cartas sorteadas, e tambem
 * o array de cartas com a solucao do crime.
 *
 * @author Rodrigo
 */
public class InicioPartida implements Jogada {

    Jogador eu;
    Jogador oponente;
    Carta[] solucao;

    public Jogador getEu() {
        return eu;
    }

    public void setEu(Jogador eu) {
        this.eu = eu;
    }

    public Jogador getOponente() {
        return oponente;
    }

    public void setOponente(Jogador oponente) {
        this.oponente = oponente;
    }

    public Carta[] getSolucao() {
        return solucao;
    }

    public void setSolucao(Carta[] solucao) {
        this.solucao = solucao;
    }

    

    public InicioPartida(Jogador eu, Jogador oponente, Carta[] solucao) {
        this.eu = eu;
        this.oponente = oponente;
        this.solucao = solucao;
    }




}
