package NetGames;

import coliseumrpg.Turno;
import coliseumrpg.Personagem;

public class Jogador {

    private Time time;
    private Personagem personagens[];
    private Turno turno;

    public Jogador(Personagem primeiroPersonagem, Personagem segundoPersonagem) {
        personagens = new Personagem[2];
        personagens[0] = primeiroPersonagem;
        personagens[1] = segundoPersonagem;
    }

    /**
     * Deve ser chamado quando o turno antigo ja estiver encerrado, pede para o
     * jogador gerar um turno para o proximo personagem dele.
     *
     * @return o novo turno com as informações do personagem da vez.
     */
    public Turno tomarVez() {
        Personagem p;
        if (this.turno == null) {
            this.turno = new Turno(time, personagens[0]);
        } else {
            if (this.turno.getPersonagem().equals(personagens[0])) {
                if (personagens[1].estaVivo()) {
                    p = personagens[1];
                } else {
                    p = personagens[0];
                }
            } else {
                if (personagens[0].estaVivo()) {
                    p = personagens[0];
                } else {
                    p = personagens[1];
                }
            }
            this.turno = new Turno(getTime(), p);
        }
        return turno;
    }

    public Time getTime() {
        return time;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setPersonagens(Personagem[] personagens) {
        this.personagens = personagens;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Personagem[] getPersonagens() {
        return personagens;
    }

    /**
     * Só deve ser chamado uma vez, assim que se souber o time. O time deve ser
     * definido pela ordem de jogo, o jogador que começa é o azul, o segundo é o
     * vermelho.
     *
     * @param time time para o jogador e seus personagens.
     */
    public void setTime(Time time) {
        if (this.time == null) {
            this.time = time;
            for (Personagem p : personagens) {
                p.setTime(time);
            }
        } else {
            throw new UnsupportedOperationException("O jogador ja possui um time.\nAlgo deu bastante errado, tentativa de trocar o time de um jogador.");
        }
    }

}
