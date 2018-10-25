/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Poderes;

import NetGames.Time;
import Poderes.TipoDePoderes.Colocavel;
import coliseumrpg.Personagem;

/**
 *
 * @author Matheus
 */
public class Armadilha implements Colocavel {

    private final int dano = 2;

    private String icon = "Armadilha.png";
    private boolean estaFuncional;
    private final Time time;

    public Armadilha(Time time) {
        this.time = time;
        this.estaFuncional = true;
    }

    @Override
    public void destruir() {
        this.estaFuncional = false;
    }

    /**
     * Método para ser chamado quando um personagem pisar na armadilha
     *
     * @param p personagem que esta pisando na armadilha
     */
    @Override
    public void pisar(Personagem p) {
        if (p.getTime() == time) {
            return;
        }
        destruir();
        p.receberDano(dano);
        if (p.estaVivo()) {
            icon = "ArmadilhaPressionada.png";
        } else {
            icon = "ArmadilhaPressionadaComPe.png";
        }

    }

    @Override
    public Time getTime() {
        return this.time;
    }

    @Override
    public boolean estaFuncional() {
        return estaFuncional;
    }

    @Override
    public Boolean visivelPeloInimigo() {
        return false;
    }

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public String getDescricao() {
        return estaFuncional() ? "Tem uma armadilha aqui, apenas nosso time pode ve-la então só quem pode ativa-la é o time inimigo." : "tinha uma armadilha aqui, mas ela foi ativada ou destruida.";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            Armadilha tmp = (Armadilha) obj;
            if (tmp.getTime().equals(getTime())) {
                return true;

            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 42;
        result = 2 * result + time.hashCode();
        return result;
    }

}
