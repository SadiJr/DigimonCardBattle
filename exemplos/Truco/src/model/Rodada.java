package model;

import br.ufsc.inf.leobr.cliente.Jogada;
import java.util.ArrayList;
import java.util.List;

public class Rodada implements Jogada {

    private List<Lance> lances;
    private Jogador vencedorRodada;

    public Rodada() {
        lances = new ArrayList<Lance>();
      
    }




    public Jogador calculaVencedorRodada(Carta gato) {

        Carta cartaAtual = null;
        Lance lanceVencedor = null;
        boolean jaTeveCoringa = false;

        Carta coringa = this.getCoringa(gato);

        for (Lance lance : lances) {

            Carta cartaTemp = lance.getCarta();

            //verifica se a carta eh coringa
            if (coringa.getNumero() == cartaTemp.getNumero()) {
                //se a cartaAtual for != null e a cartaAtual for coringa tambem tem que comparar por naipe
                if (cartaAtual != null && (coringa.getNumero() == cartaAtual.getNumero())) {
                    if (cartaTemp.getNaipe().naipeMaiorQueOutro(cartaAtual.getNaipe())) {

                        cartaAtual = cartaTemp;
                        lanceVencedor = lance;
                    }

                } else {

                    cartaAtual = lance.getCarta();
                    lanceVencedor = lance;
                }

                jaTeveCoringa = true;
//se ja teve coringa nao faz sentido entrar aqui denovo pois o coringa vai ser maior que todas as cartas e só vai precisar validar se for
                //outro coringa
            } else if (!jaTeveCoringa){

                if (cartaAtual == null) {
                    cartaAtual = lance.getCarta();
                    lanceVencedor = lance;

                } else {

                     boolean duplaIgual = false;

                    if (lanceVencedor != null && !lanceVencedor.getJogador().getNome().equals("Empate")){
                       duplaIgual = lance.getJogador().getDupla().equals(lanceVencedor.getJogador().getDupla());

                    }



                    if (!duplaIgual && (cartaAtual.getNumero() == cartaTemp.getNumero())) {

                        Lance l = new Lance();
                        Jogador jog = new Jogador("Empate");
                        l.setJogador(jog);
                        lanceVencedor = l;

                    }else  if (!cartaAtual.cartaEhMaiorOutra(lance.getCarta())) {
                        cartaAtual = lance.getCarta();
                        lanceVencedor = lance;

                    }
                }
            }
        }

        return lanceVencedor.getJogador();
    }

    private Carta getCoringa(Carta cartaVirada) {

        Carta coringa = null;
        int numero = cartaVirada.getNumero();
        String naipe = cartaVirada.getNaipe().getLetraReferente();

        if (numero != 7 && numero != 12) {
            coringa = new Carta((numero + 1) + naipe);
        } else if (numero == 7) {

            coringa = new Carta((numero + 3) + naipe);

        } else if (numero == 12) {
            coringa = new Carta(1 + naipe);
        }


        return coringa;
    }

    public List<Lance> getLances() {
        return lances;
    }

    public void setLances(List<Lance> lances) {
        this.lances = lances;
    }

    public void addLance(Lance lance) {
        if (lances == null) {
            lances = new ArrayList<Lance>();
        }

        System.out.println(lances.size());
        lances.add(lance);
    }

    public int getQuantidadeLances() {

        if (lances == null) {
            lances = new ArrayList<Lance>();
        }

        return lances.size();
    }

    public Jogador getVencedorRodada() {
        return vencedorRodada;
    }

    public void setVencedorRodada(Jogador vencedorRodada) {
        this.vencedorRodada = vencedorRodada;
    }


    
}
