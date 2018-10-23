package DaNumba;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.*;

import java.util.Random;

/**
 * Classe que implementa todas as regras de negócio do jogo.
 * @author DaNumba
 */

public class Partida {
    
    private int           nivel;
    private Random        numeroRandom;
    private TelaPrincipal objTelaPrincipal;
    private int           pontuacao;
    private int           quantidade;
    private int           somaParcial;
    private int           somaTotal;
    private int[]         proximoNivel;
    private int           valorDoPonto;
    
    private final String sProximoNivel = "Próximo nível!!!";
    private final String sGameOver = "Você perdeu o jogo!!";
    private final String sJogoTerminou = "Parabéns!!!\nVocê acabou o jogo.";
    private final String somProximaFase = "./media/proximo.wav";
    private final String somMarcouPonto = "./media/marcou.wav";
    private final String somPassouDoSomatorio = "./media/passou.wav";
    private final String somGanhouOJogo = "./media/winner.wav";
    
    private ControlePrincipal objControlePrincipal;

    //~--- constructors -------------------------------------------------------

    public Partida(ControlePrincipal pObjControlePrincipal) {
        objControlePrincipal = pObjControlePrincipal;
        this.iniciarNovoJogo();
    }

    //~--- methods ------------------------------------------------------------

    public void iniciarNovoJogo() {
        try {
            //inicia o valor do ponto em 75
            this.setValorDoPonto(75);
            //inicia com o nível em 1
            this.setNivel(1);
            //zera a pontuacao
            this.zerarPontuacao();
            //zera as variáveis temporárias
            this.zerarSomaParcial();
            this.zerarSomaTotal();
            //cria uma relaçao de agregação com a tela principal
            objTelaPrincipal = new TelaPrincipal(this);
            //exibe a tela principal
            objTelaPrincipal.setVisible(true);
            
        } catch (Exception exc){
            //trata algum eventual erro que ocorrer durante a inicialização
            javax.swing.JOptionPane.showMessageDialog(null,exc.getMessage(),"Erro",1);
        }
    }
    
    //quando não é atingido o somatório
    public void naoAtingiu(){      
    }
    
    //quando o somatório é atingido
    public void atingiu(){
        //toca o som
        objControlePrincipal.reproduzSom(somMarcouPonto);
        
        //calcula quantos pontos foi feito
        this.setPontuacao(quantidade * valorDoPonto);
        
        //atualiza o somatorio na tela        
        objTelaPrincipal.atualizarSomatorio(); 
        
        //atualiza o label de pontuacao na tela
        objTelaPrincipal.atualizarPontuacao(getPontuacao());
        
        //não troca o nível
        if ((getPontuacao() < getProximoNivel())) {
            //faz porra nenhuma
            
        //troca o nível    
        } else if ((getPontuacao() > getProximoNivel()) && (getPontuacao() < 25000)) {
            //incrementa mais uma fase
            setNivel(getNivel()+1);
            //toca o som da próxima fase
            objControlePrincipal.reproduzSom(somProximaFase);
            //mostra mensagem de próximo nível
            this.mostrarMensagem(sProximoNivel); 
            //atualiza o somatorio na tela
            objTelaPrincipal.atualizarSomatorio();  
            //atualiza a tela principal
            atualizarTelaPrincipal();
    
        //termina o jogo
        } else if (getPontuacao() > 25000){
            //tocar som de final do jogo
            objControlePrincipal.reproduzSom(somGanhouOJogo);
            //mostra mensagem de jogo terminado
            this.mostrarMensagem(sJogoTerminou);
            //encerra a partida
            this.encerrarPartida();
        }
        //zera os contadores
        this.zerarVariaveisTemporarias();
    }
    
    //quando passa o valor do somatório
    public void passou(){
        //diminui a pontuacao
        this.setPontuacao(quantidade * (-valorDoPonto));
        //atualiza a pontuacao na tela
        objTelaPrincipal.atualizarPontuacao(getPontuacao());
        //reproduz som de erro
        objControlePrincipal.reproduzSom(somPassouDoSomatorio);
        //zera as variáveis temporárias
        this.zerarVariaveisTemporarias();
    }
    
    //mostra na tela determinada mensagem
    public void mostrarMensagem(String pMensagem){
        //invoca o método para mostrar mensagem na tela
        objControlePrincipal.mostrarMensagem(pMensagem);
    }

    //zera os contadores
    public void zerarVariaveisTemporarias(){
        zerarSomaTotal();
        zerarSomaParcial();
        zerarQuantidade();        
    }
    
    //atualiza a tela principal
    public void atualizarTelaPrincipal(){
        //atualiza o nível
        objTelaPrincipal.atualizarNivel(getNivel());
        //atualiza o proximo nível
        objTelaPrincipal.atualizarProximoNivel(getProximoNivel());
        //repreenche a matriz
        objTelaPrincipal.atualizarMatriz();
    }
    
    //verifica quadradinhos caso não exista mais chance de jogar
    public void autorizarJogada(){
        if (objTelaPrincipal.matrizVazia() == true){
            objControlePrincipal.mostrarMensagem(sGameOver);
            this.encerrarPartida();
        }
        if ((objTelaPrincipal.existeValor(1) == false) && (objTelaPrincipal.getSomatorio() == 1)){
            objControlePrincipal.mostrarMensagem(sGameOver);
            this.encerrarPartida();
        }
        if ((objTelaPrincipal.existeValor(1) == false) && (objTelaPrincipal.existeValor(2) == false) &&(objTelaPrincipal.getSomatorio() == 2)){
            objControlePrincipal.mostrarMensagem(sGameOver);
            this.encerrarPartida();
        }
    }
    
    //encerra a partida e esconde a tela
    public void encerrarPartida(){
        //esconde a tela prinpal
        objTelaPrincipal.dispose();
    }
    
    //método responsável por fazer os calculos quando é clicado em um quadradinho
    public void procederJogada(int pValor) throws Exception {
        // adiciona o valor clicado ao valor do somatorio parcial
        somaParcial += pValor;
        // inclementa a quantidade de cliques para calular os pontos depois
        quantidade++;
        if (somaParcial < somaTotal) {
            this.naoAtingiu();
        } else if (somaParcial == somaTotal) {
            this.atingiu();
        } else if (somaParcial > somaTotal) {
            this.passou();
        } else {
            throw new Exception("Impossível proceder jogada");
        }
    }
    
    //método responsável por gerar os quadradinhos aleatórios
    public int sortQ() {
        //inicia a variável com uma semente padrão da JVM
        numeroRandom = new Random();

        int numeroGerado = 0;

        switch (this.getNivel()){
            case 1: numeroGerado = (numeroRandom.nextInt(5) + 1);
            case 2: numeroGerado = (numeroRandom.nextInt(6) + 1);
            case 3: numeroGerado = (numeroRandom.nextInt(7) + 1);
            case 4: numeroGerado = (numeroRandom.nextInt(8) + 1);
            case 5: numeroGerado = (numeroRandom.nextInt(9) + 1);
        }
        return numeroGerado;
    }
    
    //zera a pontuaçao do jogo
    public void zerarPontuacao() {
        this.setPontuacao(0);
    }
    
    //zera a quantidade de cliques do jogo (CONTADOR)
    public void zerarQuantidade() {
        this.setQuantidade(0);
    }
    
    //zera a soma parcial (CONTADOR)
    public void zerarSomaParcial() {
        this.setSomaParcial(0);
    }
    
    //zera a soma total (CONTADOR)
    private void zerarSomaTotal() {
        this.setSomaTotal(0);
    }

    //~--- get methods --------------------------------------------------------
    
    //retorna um numero aleatorio utilizado no somatório do jogo
    public int getNumeroAleatorio() {
        //inicia um numero com uma semente do processador
        numeroRandom = new Random(System.currentTimeMillis());
        //variável temporária
        int numeroGerado = 0;

        if (this.getNivel() == 1) {
            numeroGerado = (numeroRandom.nextInt(5) + 1);
        } else if (this.getNivel() == 2) {
            numeroGerado = (numeroRandom.nextInt(8) + 2);
        } else if (this.getNivel() == 3) {
            numeroGerado = (numeroRandom.nextInt(12) + 2);
        } else if (this.getNivel() == 4) {
            numeroGerado = (numeroRandom.nextInt(16) + 2);
        } else if (this.getNivel() == 5) {
            numeroGerado = (numeroRandom.nextInt(20) + 2);
        }

        zerarSomaParcial();
        zerarSomaTotal();
        somaTotal = numeroGerado;

        return numeroGerado;
    }
    
    //retorna o valor do ponto
    public int getValorDoPonto(){
        return valorDoPonto;
    }

    //retorna o valor do proximo nivel
    public int getProximoNivel(){
        return getNivel() * 5000;
    }
    
    //retorna a fase atual do jogo
    public int getNivel() {
        return this.nivel;
    }    

    //retorna a pontuação atual do jogo
    public int getPontuacao() {
        return pontuacao;
    }
    
    //retorna a quantidade de cliques
    public int getQuantidade() {
        return this.quantidade;
    }
    
    //retorna o somatório parcial
    public int getSomaParcial() {
        return this.somaParcial;
    }
    
    //retorna o somatório parcial
    public int getSomaTotal() {
        return this.somaTotal;
    }

    //~--- set methods --------------------------------------------------------
    
    //seta o valor do ponto
    public void setValorDoPonto(int pValor){
        valorDoPonto = pValor;
    }

    //seta a fase
    public void setNivel(int pNivel) {
        nivel = pNivel;
    }
    
    //seta pontuacao
    public void setPontuacao(int pPontuacao) {
        pontuacao += pPontuacao;
    }
    
    //seta quantidade de cliques
    public void setQuantidade(int pValor) {
        quantidade = pValor;
    }
    
    //seta a soma parcial 
    public void setSomaParcial(int pValor) {
        somaParcial = pValor;
    }
    
    //seta a soma total
    public void setSomaTotal(int pValor) {
        somaParcial = pValor;
    }
}
//~ Formatted by Jindent --- http://www.jindent.com
