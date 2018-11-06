package model;

import br.ufsc.inf.leobr.cliente.Jogada;
import java.util.ArrayList;
import java.util.List;

public class Mesa implements Jogada {

    private List<Jogador> jogadores;
    private Mao mao;
    private Baralho baralho;
    private Jogador jogadorDaVez;
    private Carta cartaGato;
    private Rodada rodadaAtual;
    private StatusMesa status;
    private Dupla duplaA;
    private Dupla duplaB;
    private int placarA;
    private int placarB;
    private int posicaoJogadorAtual = 0;
    private Dupla duplaQuePediuTruco;

    public Dupla verificaSeUmaDuplaGanhouDuasRodadas() {

        return mao.verificaSeUmaDuplaGanhouDuasRodadas();

    }
    
    public enum StatusMesa{
        INICAR_PARTIDA, INICIAR_RODADA, INICIAR_MAO, TRUCO, DESISTIR;
    }


    public void zerarPlacar(){
        this.placarA = 0;
        this.placarB = 0;
    }

    public Mesa(){
        baralho = new Baralho();
        rodadaAtual = new Rodada();
        mao = new Mao();
    }

    public boolean acabouPartida(){


        return placarA >=12 || placarB >=12;
    }

    public void addLance (Lance lance) {
        this.getRodadaAtual().addLance(lance);
    }

     public void defineDuplas() {

         duplaA = Dupla.DUPLA_A;
         duplaB = Dupla.DUPLA_B;
        
        Jogador jog1 = jogadores.get(0);
        jog1.setDupla(duplaA);

        Jogador jog2 = jogadores.get(1);
        jog2.setDupla(duplaB);

        Jogador jog3 = jogadores.get(2);
        jog3.setDupla(duplaA);

        Jogador jog4 = jogadores.get(3);
        jog4.setDupla(duplaB);

        jog1.setCompanheiro(jog3);
        jog3.setCompanheiro(jog1);

        jog2.setCompanheiro(jog4);
        jog4.setCompanheiro(jog2);



    }

     public void iniciarMao(){
         mao = new Mao();
         this.embaralhar();
         this.distribuirCartasJogadores();
         this.definirGato();
         this.duplaQuePediuTruco = null;
         this.iniciarRodada(this.getProximoJogador());
     }

     public void iniciarRodada(Jogador jogadorDaVez) {
         Rodada rodada = new Rodada();
         this.setRodadaAtual(rodada);
         this.setJogadorDaVez(jogadorDaVez);

     }

     public Jogador getProximoJogador(){

         if (posicaoJogadorAtual == 4){
             posicaoJogadorAtual = 0;
         }

        return this.jogadores.get(posicaoJogadorAtual++);
     }

     public Jogador getVencedorRodada(){


         return this.rodadaAtual.calculaVencedorRodada(this.getCartaGato());


     
     }

     public void removeCartaDeJogador(Lance lance){

         for (Jogador jog : this.jogadores){

             if (jog.getNome().equals(lance.getJogador().getNome())){



                for (int i = 0; i < jog.getCartas().size(); i++){

                    Carta atual = jog.getCartas().get(i);

                    if (atual.getNome().equals(lance.getCarta().getNome())){

                      jog.getCartas().remove(i).getNome();
                    }

                }

             }


         }


     }

     private void distribuirCartasJogadores(){

         int ultimoValor = 0;

         int[] posicoes = {3 , 6 , 9 , 12};



         for (int i = 0; i <jogadores.size(); i++){

             Jogador joga = jogadores.get(i);
             int valorAtual = posicoes[i];
             List<Carta> listaDeCartasPorJogador = new ArrayList<Carta>();
             for (int b = ultimoValor; b<posicoes[i]; b++){
             listaDeCartasPorJogador.add(baralho.getCartas().get(b));
             baralho.getCartas();
                         

            

         }
             joga.setCartas(listaDeCartasPorJogador);
              ultimoValor = valorAtual;
         }


     }

     public void definirGato(){
         this.cartaGato = this.baralho.getCartas().get(13);
     }

     public void embaralhar(){
         this.baralho.embaralharCartas();
     }


    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public void addJogador(Jogador jogador) {

        if (jogadores == null) {
            jogadores = new ArrayList<Jogador>();

        }
        jogadores.add(jogador);

    }

    public Baralho getBaralho() {
        return baralho;
    }

    public void setBaralho(Baralho baralho) {
        this.baralho = baralho;
    }

    public Jogador getJogadorDaVez() {
        return jogadorDaVez;
    }

    public void setJogadorDaVez(Jogador jogadorDaVez) {
        this.jogadorDaVez = jogadorDaVez;
    }

    public Carta getCartaGato() {
        return cartaGato;
    }

    public void setCartaGato(Carta cartaGato) {
        this.cartaGato = cartaGato;
    }

    public Mao getMao() {
        return mao;
    }

    public void setMao(Mao mao) {
        this.mao = mao;
    }

    public Rodada getRodadaAtual() {
        return rodadaAtual;
    }

    public void setRodadaAtual(Rodada rodadaAtual) {
        this.rodadaAtual = rodadaAtual;
    }

    public StatusMesa getStatus() {
        return status;
    }

    public void setStatus(StatusMesa status) {
        this.status = status;
    }

    

    public Dupla getDuplaVencedoraMao(){

        return mao.getVencedorMao();
    }

    public int getValorMao(){
        return mao.getValorDaMao();
    }

    public Dupla getDuplaA() {
        return duplaA;
    }

    public Dupla getDuplaB() {
        return duplaB;
    }

    public int getPlacarA() {
        return placarA;
    }

    public void setPlacarA(int placarA) {
        this.placarA = placarA;
    }

    public int getPlacarB() {
        return placarB;
    }

    public void setPlacarB(int placarB) {
        this.placarB = placarB;
    }


    public void setValorMao(int valor){
        mao.setValorDaMao(valor);
    }

    public Dupla getDuplaQuePediuTruco() {
        return duplaQuePediuTruco;
    }

    public void setDuplaQuePediuTruco(Dupla duplaQuePediuTruco) {
        this.duplaQuePediuTruco = duplaQuePediuTruco;
    }


    

    
}
