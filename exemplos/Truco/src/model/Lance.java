package model;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Lance implements Jogada {

	private Carta carta;
	private Jogador jogador;
	private boolean truco;
        private RespostaTruco respostaTruco;

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public boolean isTruco() {
        return truco;
    }

    public void setTruco(boolean truco) {
        this.truco = truco;
    }

    public RespostaTruco getRespostaTruco() {
        return respostaTruco;
    }

    public void setRespostaTruco(RespostaTruco respostaTruco) {
        this.respostaTruco = respostaTruco;
    }


        

}
