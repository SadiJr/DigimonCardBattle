/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Erros.ErroTolo;
import Erros.ForaDoAlcanceException;
import Erros.UmPassoDeCadaVezException;
import Poderes.TipoDePoderes.PoderLocalAlvo;
import coliseumrpg.Personagem;
import java.awt.Dimension;
import java.util.HashMap;

/**
 *
 * @author Matheus
 */
public class Mapa {

    private HashMap<Dimension, Lugar> alteracoes;
    private HashMap<Personagem, Lugar> posicoesPersonagens;
    protected Lugar mapa[][];

    public Mapa() {
        this.posicoesPersonagens = new HashMap<>();
        this.alteracoes = new HashMap<>();

        this.mapa = new Lugar[20][12];
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                mapa[i][j] = new Lugar(i, j);
            }
        }
    }

    public void adicionaPersonagensPrimeiroAJogar(Personagem pers1, Personagem pers2) {
        posicionarPersonagem(pers1, new Dimension(3, 2));
        posicionarPersonagem(pers2, new Dimension(3, 9));
    }

    public void adicionaPersonagensSegundoAJogar(Personagem pers1, Personagem pers2) {
        posicionarPersonagem(pers1, new Dimension(16, 2));
        posicionarPersonagem(pers2, new Dimension(16, 9));
    }

    /**
     * @param p personagem que esta tentando se mover
     * @param destino local para onde o personagem deve se mover
     */
    public void mover(Personagem p, Dimension destino) {
        int distancia = calculaDistancia(getPosicaoPersonagem(p), destino);
        switch (distancia) {
            case 1:
                posicionarPersonagem(p, destino);
                break;
            case 0:
                throw new ErroTolo("Você acabou de tentar andar para o lugar onde ja estava?\n...\nPor que??");
            default:
                throw new UmPassoDeCadaVezException("Voce deve andar um quadrante por vez, para poder escolher seu caminho.");
        }

    }

    private void posicionarPersonagem(Personagem personagem, Dimension destino) {
        Lugar alvo = getTarget(destino);
        Lugar lugarOrigem = posicoesPersonagens.get(personagem);
        if (lugarOrigem != null) {
            lugarOrigem.desocupar();
            alteracoes.put(lugarOrigem.getCoordenada(), lugarOrigem);
        }
        alvo.ocupar(personagem);
        alteracoes.put(destino, alvo);
        posicoesPersonagens.put(personagem, alvo);
    }

    public void poderLocalAlvo(Dimension coordenadaOrigem, PoderLocalAlvo poder, Dimension coordenadaDestino) {
        int distancia = calculaDistancia(coordenadaOrigem, coordenadaDestino);
        if (distancia <= poder.getAlcance()) {
            Lugar alvo = getTarget(coordenadaDestino);
            poder.usar(alvo);
            alteracoes.put(coordenadaDestino, alvo);
        } else {
            throw new ForaDoAlcanceException("Essa habilidade alcança apenas " + poder.getAlcance() + " quadrantes."
                    + "\n O quadrante que você escolheu esta a " + distancia + " de você.");
        }
    }

    public void atacar(Personagem personagem, Dimension destino) {
        Personagem alvo = getTarget(destino).getPersonagem();
        if (alvo != null) {
            Dimension origem = posicoesPersonagens.get(personagem).getCoordenada();
            if (personagem.getAlcance() >= calculaDistancia(origem, destino)) {
                alvo.receberDano(personagem.atacar());
            } else {
                throw new ForaDoAlcanceException("O alcance do ataque de seu personagem é " + personagem.getAlcance() + " quadrantes."
                        + "\n Escolha um alvo mais próximo.");
            }
        } else {
            throw new ErroTolo("Não tem ninguem ai, você tentou atacar o vento?");
        }
    }

    public Dimension getPosicaoPersonagem(Personagem p) {
        return posicoesPersonagens.get(p).getCoordenada();
    }

    private int calculaDistancia(Dimension coordenadaOrigem, Dimension coordenadaDestino) {
        int distanciaVertical = Math.abs((int) coordenadaOrigem.getHeight() - (int) coordenadaDestino.getHeight());
        int distanciaHorizontal = Math.abs((int) coordenadaOrigem.getWidth() - (int) coordenadaDestino.getWidth());
        return distanciaHorizontal + distanciaVertical;
    }

    public Lugar getTarget(Dimension d) {
        return d == null ? null : mapa[(int) d.getWidth()][(int) d.getHeight()];
    }

    public void atualizar(HashMap<Dimension, Lugar> alteracoesMapa, HashMap<Personagem, Lugar> novaPosicaoPersonagens) {
        this.posicoesPersonagens = novaPosicaoPersonagens;
        alteracoesMapa.forEach((coordenada, lugar) -> {
            mapa[(int) coordenada.getWidth()][(int) coordenada.getHeight()] = lugar;
        });
        novaPosicaoPersonagens.forEach((pers, lugar) -> {
            Dimension coordenada = lugar.getCoordenada();
            mapa[(int) coordenada.getWidth()][(int) coordenada.getHeight()].ocupar(pers);
        });
        posicoesPersonagens = novaPosicaoPersonagens;
    }

    /**
     * Pega as ultimas alterações feitas no mapa, como itens adicionados ou
     * personagens que se moveram. Exemplo, se um personagem se moveu um
     * quadrante teremos guardados o quadrante de onde ele saiu e o para onde
     * ele foi.
     *
     * @return hashmap com dimensions referenciando os lugares alterados.
     */
    public HashMap<Dimension, Lugar> getAlteracoes() {
        return this.alteracoes;
    }

    public void resetAlteracoes() {
        alteracoes.clear();
    }

    public HashMap<Personagem, Lugar> getPosicoesPersonagens() {
        return posicoesPersonagens;
    }

}
