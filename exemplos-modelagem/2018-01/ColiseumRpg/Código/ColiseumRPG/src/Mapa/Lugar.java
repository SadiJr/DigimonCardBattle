/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Poderes.Armadilha;
import Erros.LocalJaPossuiEsseItemException;
import Erros.LocalOcupadoException;
import NetGames.Time;
import Poderes.TipoDePoderes.Colocavel;
import coliseumrpg.Personagem;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class Lugar implements Serializable {

    private Personagem ocupante;
    protected String caminhoIcone = "CampoVerde.png";
    private final String descricao = "Um lugar aparentemente vazio.";

    private Dimension coordenada;
    private final ArrayList<Colocavel> colocaveis;

    public Lugar(int width, int heigth) {
        coordenada = new Dimension(width, heigth);
        colocaveis = new ArrayList();
    }

    public Personagem getPersonagem() {
        return ocupante;
    }

    public Dimension getCoordenada() {
        return coordenada;
    }

    public void destruir() {
        colocaveis.forEach(c -> c.destruir());
    }

    public void ocupar(Personagem p) {
        if (estaOcupado() && !ocupante.equals(p)) {
            throw new LocalOcupadoException("Dois personagens não podem ocupar o mesmo quadrante no espaço ao mesmo tempo!");
        }
        colocaveis.forEach((c) -> {
            c.pisar(p);
        });
        this.ocupante = p;
    }

    public void desocupar() {
        this.ocupante = null;
    }

    public void colocar(Colocavel c) throws LocalJaPossuiEsseItemException {
        if (!estaOcupado() && disponivelParaColocavel(c)) {
            colocaveis.add(c);
            return;
        }
        throw new LocalJaPossuiEsseItemException("Você ja colocou isso nesse quadrante.");
    }

    public boolean estaOcupado() {
        return ocupante != null;
    }

    private boolean disponivelParaColocavel(Colocavel novo) {
        boolean disponivel = true;
        for (Colocavel c : colocaveis) {
            if (c.getClass().equals(Armadilha.class) && c.getTime().equals(novo.getTime()) && c.estaFuncional()) {
                disponivel = false;
                break;
            }
        }
        return disponivel;
    }

    /**
     * Devolve o icone do lugar para colocar no mapa. O icone de uma lugar vai
     * ser definido primeiro pelo ocupante, depois pelos colocaveis que estão
     * ativos, depois pelos colocaveis destruidos e por fim o campo verde
     * default.
     *
     * @param time Time de quem quer ver o mapa, algumas coisas só são visíveis
     * pelo próprio time.
     * @return O caminho para o icone que deve ser mostrado na tela.
     */
    public String getIcone(Time time) {
        if (estaOcupado()) {
            return getPersonagem().getIcone();
        }
        if (!colocaveis.isEmpty()) {
            Colocavel temp = null;
            for (Colocavel c : colocaveis) {
                if (c.visivelPeloInimigo() != null) {
                    if (temp == null || (c.estaFuncional() && !temp.estaFuncional())) {
                        if (c.visivelPeloInimigo() || c.getTime().equals(time)) {
                            temp = c;
                        }
                    }
                }
            }
            if (temp != null) {
                return temp.getIcon();
            }
        }
        return this.caminhoIcone;
    }

    public String getDescricao(Time time) {
        if (estaOcupado()) {
            if (ocupante.estaVivo()) {
                return ocupante.getTime().equals(time) ? "Seu " + ocupante.getNome() + " esta nesse quadrante, espero que nada de mal aconteça a ele.":"O " + ocupante.getNome() + " inimigo esta nesse quadrante, mal posso esperar para mata-lo.";
            } else {
                return ocupante.getTime().equals(time) ? "Antes ele do que eu.":"Um a menos, falta um." ;
            }
        }
        if (!colocaveis.isEmpty()) {
            Colocavel temp = null;
            for (Colocavel c : colocaveis) {
                if (c.visivelPeloInimigo() != null) {
                    if (temp == null || (c.estaFuncional() && !temp.estaFuncional())) {
                        if (c.visivelPeloInimigo() || c.getTime().equals(time) || !c.estaFuncional()) {
                            temp = c;
                        }
                    }
                }
            }
            if (temp != null) {
                return temp.getDescricao();
            }
        }
        return this.descricao;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            Dimension tmp = ((Lugar) obj).getCoordenada();
            if (tmp.getHeight()==coordenada.getHeight()) {
                if (tmp.getWidth()==coordenada.getWidth()) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 1;
        long f = Double.doubleToLongBits(coordenada.getHeight());
        result = result * 1000 + (int)(f ^ (f >>> 32)) ;
        long f1 = Double.doubleToLongBits(coordenada.getWidth());
        result = result * 3 + (int)(f1 ^ (f1 >>> 32)) ;
        return result;
    }

}
