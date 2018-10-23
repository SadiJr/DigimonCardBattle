package DaNumba;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.*;

import java.util.Random;

/**
 * Classe que implementa todas as regras de neg�cio do jogo.
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
    
    private final String sProximoNivel = "Pr�ximo n�vel!!!";
    private final String sGameOver = "Voc� perdeu o jogo!!";
    private final String sJogoTerminou = "Parab�ns!!!\nVoc� acabou o jogo.";
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
            //inicia com o n�vel em 1
            this.setNivel(1);
            //zera a pontuacao
            this.zerarPontuacao();
            //zera as vari�veis tempor�rias
            this.zerarSomaParcial();
            this.zerarSomaTotal();
            //cria uma rela�ao de agrega��o com a tela principal
            objTelaPrincipal = new TelaPrincipal(this);
            //exibe a tela principal
            objTelaPrincipal.setVisible(true);
            
        } catch (Exception exc){
            //trata algum eventual erro que ocorrer durante a inicializa��o
            javax.swing.JOptionPane.showMessageDialog(null,exc.getMessage(),"Erro",1);
        }
    }
    
    //quando n�o � atingido o somat�rio
    public void naoAtingiu(){      
    }
    
    //quando o somat�rio � atingido
    public void atingiu(){
        //toca o som
        objControlePrincipal.reproduzSom(somMarcouPonto);
        
        //calcula quantos pontos foi feito
        this.setPontuacao(quantidade * valorDoPonto);
        
        //atualiza o somatorio na tela        
        objTelaPrincipal.atualizarSomatorio(); 
        
        //atualiza o label de pontuacao na tela
        objTelaPrincipal.atualizarPontuacao(getPontuacao());
        
        //n�o troca o n�vel
        if ((getPontuacao() < getProximoNivel())) {
            //faz porra nenhuma
            
        //troca o n�vel    
        } else if ((getPontuacao() > getProximoNivel()) && (getPontuacao() < 25000)) {
            //incrementa mais uma fase
            setNivel(getNivel()+1);
            //toca o som da pr�xima fase
            objControlePrincipal.reproduzSom(somProximaFase);
            //mostra mensagem de pr�ximo n�vel
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
    
    //quando passa o valor do somat�rio
    public void passou(){
        //diminui a pontuacao
        this.setPontuacao(quantidade * (-valorDoPonto));
        //atualiza a pontuacao na tela
        objTelaPrincipal.atualizarPontuacao(getPontuacao());
        //reproduz som de erro
        objControlePrincipal.reproduzSom(somPassouDoSomatorio);
        //zera as vari�veis tempor�rias
        this.zerarVariaveisTemporarias();
    }
    
    //mostra na tela determinada mensagem
    public void mostrarMensagem(String pMensagem){
        //invoca o m�todo para mostrar mensagem na tela
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
        //atualiza o n�vel
        objTelaPrincipal.atualizarNivel(getNivel());
        //atualiza o proximo n�vel
        objTelaPrincipal.atualizarProximoNivel(getProximoNivel());
        //repreenche a matriz
        objTelaPrincipal.atualizarMatriz();
    }
    
    //verifica quadradinhos caso n�o exista mais chance de jogar
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
    
    //m�todo respons�vel por fazer os calculos quando � clicado em um quadradinho
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
            throw new Exception("Imposs�vel proceder jogada");
        }
    }
    
    //m�todo respons�vel por gerar os quadradinhos aleat�rios
    public int sortQ() {
        //inicia a vari�vel com uma semente padr�o da JVM
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
    
    //zera a pontua�ao do jogo
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
    
    //retorna um numero aleatorio utilizado no somat�rio do jogo
    public int getNumeroAleatorio() {
        //inicia um numero com uma semente do processador
        numeroRandom = new Random(System.currentTimeMillis());
        //vari�vel tempor�ria
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

    //retorna a pontua��o atual do jogo
    public int getPontuacao() {
        return pontuacao;
    }
    
    //retorna a quantidade de cliques
    public int getQuantidade() {
        return this.quantidade;
    }
    
    //retorna o somat�rio parcial
    public int getSomaParcial() {
        return this.somaParcial;
    }
    
    //retorna o somat�rio parcial
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
