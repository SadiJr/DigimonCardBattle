package dominio;

import estatico.PosicoesEnum;
import estatico.TipoPosicao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Tabuleiro {

    private List<Posicao> posicoes;
	private List<Jogador> jogadores;
    private int jogadorAtual;
    Map<TipoPosicao, Integer> quantidadePorCor = new HashMap<TipoPosicao, Integer>();

    public Map<TipoPosicao, Integer> getQuantidadePorCor() {
        return quantidadePorCor;
    }

    public void setQuantidadePorCor(Map<TipoPosicao, Integer> quantidadePorCor) {
        this.quantidadePorCor = quantidadePorCor;
    }

    public Tabuleiro() {
        jogadores = new ArrayList<Jogador>();
        Terreno leblon = new Terreno(PosicoesEnum.LEBLON,100,6,30,90,270,400,500,50,50,50);
        Terreno avPresidenteVargas = new Terreno(PosicoesEnum.AV_PRESIDENTE_VARGAS,60,2,10,30,90,160,250,50,50,30);
        Terreno avNossaCopacabana = new Terreno(PosicoesEnum.AV_NOSSA_COPACABANA,60,4,20,60,180,320,450,50,50,30);
        Terreno avBrigadeiro = new Terreno(PosicoesEnum.AV_BRIGRADEIRO_FARIA_LIMA,240,20,100,300,750,925,1100,150,150,120);
        Terreno avReboucas = new Terreno(PosicoesEnum.AV_REBOUCAS,220,18,90,250,700,875,1050,150,150,110);
        Terreno julho9 = new Terreno(PosicoesEnum.AV_9_JULHO,220,18,90,250,700,875,1050,150,150,110);
        Terreno avEuropa = new Terreno(PosicoesEnum.AV_EUROPA,200,16,80,220,600,800,1000,100,100,100);
        Terreno ruaAugusta = new Terreno(PosicoesEnum.RUA_AUGUSTA,180,14,70,200,550,750,950,100,100,90);
        Terreno avPacaembu = new Terreno(PosicoesEnum.AV_PACAEMBU,180,14,70,200,550,750,950,100,100,90);
        Terreno interlagos = new Terreno(PosicoesEnum.INTERLAGOS,350,35,175,500,1100,1300,1500,200,200,175);
        Terreno morumbi = new Terreno(PosicoesEnum.MORUMBI,400,50,200,600,1400,1700,2000,200,200,200);
        Terreno flamengo = new Terreno(PosicoesEnum.FLAMENGO,120,8,40,100,300,450,600,50,50,60);
        Terreno botafogo = new Terreno(PosicoesEnum.BOTAFOGO,100,6,30,90,270,400,500,50,50,50);
        Terreno avBrasil = new Terreno(PosicoesEnum.AV_BRASIL,160,12,60,180,500,700,900,100,100,80);
        Terreno avPaulista = new Terreno(PosicoesEnum.AV_PAULISTA,140,10,50,150,450,625,750,100,100,70);
        Terreno jardimEuropa = new Terreno(PosicoesEnum.JARDIM_EUROPA,140,10,50,150,450,625,750,100,100,70);
        Terreno copacabana = new Terreno(PosicoesEnum.COPACABANA,260,22,110,330,800,975,1150,150,150,130);
        Terreno avVieiraSouto = new Terreno(PosicoesEnum.AV_VIEIRA_SOUTO,320,28,150,450,1000,1200,1400,200,200,160);
        Terreno avAtlantica = new Terreno(PosicoesEnum.AV_ATLANTICA,300,26,13,390,900,1100,1275,200,200,150);
        Terreno ipanema = new Terreno(PosicoesEnum.IPANEMA,300,26,130,390,900,1100,1275,200,200,150);
        Terreno jardimPaulista = new Terreno(PosicoesEnum.JARDIM_PAULISTA,280,24,120,360,850,1025,1200,150,150,140);
        Terreno brooklin = new Terreno(PosicoesEnum.BROOKLIN,260,22,110,330,800,975,1150,150,150,130);

        quantidadePorCor.put(TipoPosicao.AMARELO,3);
        quantidadePorCor.put(TipoPosicao.VERDE,4);
        quantidadePorCor.put(TipoPosicao.AZUL_ESCURO,2);
        quantidadePorCor.put(TipoPosicao.ROSA,3);
        quantidadePorCor.put(TipoPosicao.AZUL_CLARO,3);
        quantidadePorCor.put(TipoPosicao.VERMELHO,2);
        quantidadePorCor.put(TipoPosicao.LARANJA, 2);
        quantidadePorCor.put(TipoPosicao.ROXO,3);

        posicoes = new ArrayList<Posicao>(40);
        posicoes.add(new PosicaoImpl(PosicoesEnum.PARTIDA));
        posicoes.add(leblon);
        posicoes.add(new SorteOuReves());
        posicoes.add(avPresidenteVargas);
        posicoes.add(avNossaCopacabana);
        posicoes.add(new Companhia(PosicoesEnum.CIA_FERROVIARIA,200,50,100));
        posicoes.add(avBrigadeiro);
        posicoes.add(new Companhia(PosicoesEnum.CIA_VIACAO,200,50,150));
        posicoes.add(avReboucas);
        posicoes.add(julho9);
        posicoes.add(new PosicaoImpl(PosicoesEnum.PRISAO));
        posicoes.add(avEuropa);
        posicoes.add(new SorteOuReves());
        posicoes.add(ruaAugusta);
        posicoes.add(avPacaembu);
        posicoes.add(new Companhia(PosicoesEnum.CIA_TAXI,150,40,75));
        posicoes.add(new SorteOuReves());
        posicoes.add(interlagos);
        posicoes.add(new PosicaoImpl(PosicoesEnum.LUCROS_DIVIDENDOS));
        posicoes.add(morumbi);
        posicoes.add(new PosicaoImpl(PosicoesEnum.PARTIDA_LIVRE));
        posicoes.add(flamengo);
        posicoes.add(new SorteOuReves());
        posicoes.add(botafogo);
        posicoes.add(new PosicaoImpl(PosicoesEnum.IMPOSTO_RENDA));
        posicoes.add(new Companhia(PosicoesEnum.CIA_NAVEGACAO,150,40,75));
        posicoes.add(avBrasil);
        posicoes.add(new SorteOuReves());
        posicoes.add(avPaulista);
        posicoes.add(jardimEuropa);
        posicoes.add(new PosicaoImpl(PosicoesEnum.VA_PARA_PRISAO));
        posicoes.add(copacabana);
        posicoes.add(new Companhia(PosicoesEnum.CIA_AVIACAO,200,50,100));
        posicoes.add(avVieiraSouto);
        posicoes.add(avAtlantica);
        posicoes.add(new Companhia(PosicoesEnum.CIA_TAXI_AEREO,200,50,100));
        posicoes.add(ipanema);
        posicoes.add(new SorteOuReves());
        posicoes.add(jardimPaulista);
        posicoes.add(brooklin);

    }
    public Jogador informarJogadorDaVez() {
        return jogadores.get(jogadorAtual);
    }

    public void passarParaProxJogador() {
        do {
            jogadorAtual = (jogadorAtual+1)%jogadores.size();
        } while (jogadores.get(jogadorAtual).getCapital()<0);
    }


    public List<Posicao> getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(List<Posicao> posicoes) {
        this.posicoes = posicoes;
    }

    public int[] informarQuantidadePorCor(){
        int [] quantidade = new int[8];
        for(Posicao p : posicoes){
            if(posicoes instanceof Terreno){
                TipoPosicao subtipo = ((Terreno)p).getTipoPosicao().getTipo();
                quantidade[subtipo.getId()]++;
            }
        }
        return quantidade;
    }

    public Posicao getPosicao(int i) {
        return posicoes.get(i);
    }


    public void adicionarJogador(Jogador jogador) {
        jogador.setOrdem(jogadores.size());
        jogadores.add(jogador);
    }
    public void adicionarJogador(int posicao,Jogador jogador) {
        jogador.setOrdem(posicao);
        jogadores.add(posicao, jogador);
        reordenar();
    }
    public boolean unicoJogador() {
        int i=0;
        for (Jogador j : jogadores) {
            i += j.getCapital()>=0?1:0;
        }
        return i==1;
    }
    private void reordenar() {
        for (int i = 0;i<jogadores.size();i++) {
            jogadores.get(i).setOrdem(i);
        }
    }

    public void alterarCapital(int jogador,int novoCapital) {
        jogadores.get(jogador).setCapital(novoCapital);
    }
    public int[] informarCapitais() {
        int[] capitais = new int[jogadores.size()];
        int i = 0;
        for (Jogador jogador : jogadores) {
            capitais[i++] = jogador.getCapital();
        }
        return capitais;
    }


    public int[] falirJogador(int i) {
        return jogadores.get(i).falir();
    }

    public void adicionarAdversarios(List<String> adversarios) {
        for (String adversario : adversarios) {
            this.adicionarJogador(new Jogador(adversario));
        }
    }
}