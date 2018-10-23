/*
 * AtorJogador.java
 *
 * Created on November 11, 2008, 7:03 PM
 */

package interfaceGrafica;

import dominioProblema.Carta;
import dominioProblema.Jogador;
import dominioProblema.Tabuleiro;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import rede.AtorRede;

/**
 *
 * @author  Rodrigo
 */
public class AtorJogador extends javax.swing.JFrame {


    Tabuleiro tabuleiro;
    AtorRede atorRede;
    String nomeJogador;

    /** Creates new form AtorJogador */
    public AtorJogador() {

        nomeJogador = pegaEntradaTexto("Digite seu nome");
        String ip = pegaEntradaTexto("Digite o ip do servidor");
        atorRede = new AtorRede(this);
        atorRede.conectar(ip, nomeJogador);
        initComponents();
        labelNomeJogador.setText(nomeJogador);
        
    }

    //acionado quando vem a mensagem da rede indicando que a partida foi iniciada
    public void iniciarPartida(boolean euIniciei, String nomeOponente) {

        tabuleiro = new Tabuleiro(this);

        if(euIniciei) {
            tabuleiro.iniciarPartida(nomeJogador, nomeOponente);
            mostraMensagem("A partida iniciou, " + nomeJogador + " começa jogando");

        }
        else {
            mostraMensagem("A partida iniciou, " + nomeOponente + " começa jogando");
        }


    }

    
    //quando fez uma acusação
    public void enviaJogadaFinal(String logDaPartida, boolean vencedor) {
        campoLogDaPartida.setText(logDaPartida);
        atorRede.enviaJogadaFinal(logDaPartida, vencedor);
        if(vencedor) {
            mostraMensagem("Você fez uma acusação equivocada, você perdeu. Clique em OK para encerrar a partida");
        }
        else  {
            mostraMensagem("Você fez uma acusação correta, você venceu. Clique em OK para encerrar a partida");
        }

        System.exit(0);


    }

    //quando se inicia a partida e instancia as cartas e jogadores
    public void enviaJogadaInicial(Carta[] solucao, Jogador oponente, Jogador eu) {
        atorRede.enviaJogadaInicial(solucao, oponente, eu);
    }

    //quando passou a vez clicando no botao ou fazendo um palpite
    public void enviaJogadaSimples(int[] posicao, String logDaPartida) {
         campoLogDaPartida.setText(logDaPartida);
         atorRede.enviaJogadaSimples(posicao, logDaPartida);
    }

   
   

   


    //quando o oponente iniciou a partida
    public void recebeInicio(Carta[] solucao, Jogador eu, Jogador oponente) {
        tabuleiro.recebeInicio(solucao, eu, oponente);
    }


    //quando o oponente fez uma acusação
    public void recebeJogadaFinal(String logDaPartida, boolean vencedor) {

        campoLogDaPartida.setText(logDaPartida);
        if(vencedor) {
            mostraMensagem("O oponente fez uma acusação equivocada, você é o vencedor. Clique em OK para encerrar a partida");
        }
        else  {
            mostraMensagem("O oponente fez uma acusação correta, você perdeu. Clique em OK para encerrar a partida");
        }

        System.exit(0);


    }

    //quando o oponente passou a vez espontaneamente ou fez um palpite
    public void recebeJogadaSimples(String logDaPartida, int[] posicao) {
        tabuleiro.recebeJogadaSimples(logDaPartida, posicao);
       
    }

     public void clickJogarDado() {
        if(tabuleiro != null){
            String resposta = tabuleiro.jogarDado();
            if(resposta != null){
                mostraMensagem(resposta);
            }
        }
        else{
            mostraMensagemNaoIniciada();
        }

    }

    private void clickIniciarPartidaRede(){
        if(tabuleiro == null)
            atorRede.iniciarPartidaRede();

    }

    private void clickAcusacao(int arma, int local, int suspeito) {
        if(tabuleiro != null) {
            String resposta = tabuleiro.acusacao(arma, local, suspeito);
            if(resposta != null){
                mostraMensagem(resposta);
            }
        }
        else{
            mostraMensagemNaoIniciada();
        }

    }

    private void clickPalpite(int arma, int local, int suspeito) {
        if(tabuleiro != null) {
            String resposta = tabuleiro.palpite(arma, local, suspeito);
            if(resposta != null){
                mostraMensagem(resposta);
            }
        }
        else{
            mostraMensagemNaoIniciada();
        }
        

    }

    private void clickPassarAvez() {
        if(tabuleiro != null){
            tabuleiro.passarAvez(1);
        }
        else{
            mostraMensagemNaoIniciada();
        }
    }

    //acionado por botoes de posição no tabuleiro
    public void clickPosicao(int linha, int coluna){
        if(tabuleiro != null){
            String resposta = tabuleiro.andarComPeao(linha, coluna);
            if(resposta != null){
                JOptionPane.showMessageDialog(null, resposta);
            }
        }
        else{
            mostraMensagemNaoIniciada();
        }
        
    }

    //mostra as imagens das cartas que o jogador possui na sua mão
    public void atualizaInterface(String[] nomeCartas) {
        labelCarta1.setIcon(new javax.swing.ImageIcon(getClass().getResource(nomeCartas[0])));
        labelCarta2.setIcon(new javax.swing.ImageIcon(getClass().getResource(nomeCartas[1])));
        labelCarta3.setIcon(new javax.swing.ImageIcon(getClass().getResource(nomeCartas[2])));
        labelCarta4.setIcon(new javax.swing.ImageIcon(getClass().getResource(nomeCartas[3])));
        labelCarta5.setIcon(new javax.swing.ImageIcon(getClass().getResource(nomeCartas[4])));
        labelCarta6.setIcon(new javax.swing.ImageIcon(getClass().getResource(nomeCartas[5])));
        labelCarta7.setIcon(new javax.swing.ImageIcon(getClass().getResource(nomeCartas[6])));
        labelCarta8.setIcon(new javax.swing.ImageIcon(getClass().getResource(nomeCartas[7])));
        labelCarta9.setIcon(new javax.swing.ImageIcon(getClass().getResource(nomeCartas[8])));

    }

    //acionado pelo caso de uso jogar dado
    public void atualizaInterface(int numeroSorteado, String logDaPartida){
        campoPassosRestantes.setText("" + numeroSorteado);
        campoLogDaPartida.setText(logDaPartida);
        botaoJogarDado.setEnabled(false);



    }

    //acionado quando se recebe uma jogada simples da rede
     public void atualizaInterface(int peao, int[] posicaoAntiga, int[] posicao, String logDaPartida) {
        ocupaDesocupaPosicao(peao, posicaoAntiga, false);
        ocupaDesocupaPosicao(peao, posicao, true);
        campoLogDaPartida.setText(logDaPartida);
        campoPassosRestantes.setText("0");
        botaoJogarDado.setEnabled(true);
    }




    //acionado pelo caso de uso andar com peão
    public void atualizaInterface(int peao, int[] posicaoAntiga, int[] posicaoNova, int passosRestantes, String logDaPartida) {

        ocupaDesocupaPosicao(peao, posicaoAntiga, false);
        campoPassosRestantes.setText("" + passosRestantes);
        ocupaDesocupaPosicao(peao, posicaoNova, true);
        campoLogDaPartida.setText(logDaPartida);

    }
    
     public void mostraMensagem(String resposta) {
        JOptionPane.showMessageDialog(null, resposta);
    }
     
     private String pegaEntradaTexto(String string) {
        String entrada = null;
        do{
            entrada = JOptionPane.showInputDialog(string);
       
        }
        while(entrada == null);
        
        return entrada;
    } 
    
     public void mostraMensagemNaoIniciada(){
        JOptionPane.showMessageDialog(null, "A partida ainda nao foi iniciada");
    }
     
     public void liberaJogarDado() {
        botaoJogarDado.setEnabled(true);
    }

    //coloca o icone correto no botao referente a posição, de acordo com os parametros
    //o parametro "marca" indica se a posiçao sera ocupada (true) ou desocupada (false)
    public void ocupaDesocupaPosicao(int peao, int[] posicao, boolean marca) {

        Icon iconPeaoCasaNormal;
        Icon iconPeaoCasaPorta;

        //identifica se esta desmarcando ou desmarcando
        if(marca){
            //identifica qual o peao que deve ocupar a posiçao
            if(peao == 1){
                 iconPeaoCasaNormal = new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casaPeao1.jpg"));
                 iconPeaoCasaPorta = new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casaportaPeao1.jpg"));
            }
            else {
                iconPeaoCasaNormal = new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casaPeao2.jpg"));
                iconPeaoCasaPorta = new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casaportaPeao2.jpg"));
            }
        }
        else{
            iconPeaoCasaNormal = new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"));
            iconPeaoCasaPorta = new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casaporta.jpg"));

        }

        //identifica a posicao para ser marcada com o icone
        switch(posicao[0]){
            case 0:
                switch(posicao[1]){
                    case 0:
                        linha0coluna0.setIcon(iconPeaoCasaPorta);
                        break;

                    case 1:
                        linha0coluna1.setIcon(iconPeaoCasaNormal);
                        break;

                    case 2:
                        linha0coluna2.setIcon(iconPeaoCasaNormal);
                        break;

                    case 3:
                        linha0coluna3.setIcon(iconPeaoCasaNormal);
                        break;

                    case 4:
                        linha0coluna4.setIcon(iconPeaoCasaNormal);
                        break;

                     case 5:
                        linha0coluna5.setIcon(iconPeaoCasaPorta);
                        break;

                     case 6:
                        linha0coluna6.setIcon(iconPeaoCasaNormal);
                        break;

                     case 7:
                        linha0coluna7.setIcon(iconPeaoCasaNormal);
                        break;

                     case 8:
                        linha0coluna8.setIcon(iconPeaoCasaNormal);
                        break;

                     case 9:
                        linha0coluna9.setIcon(iconPeaoCasaNormal);
                        break;
                }
            break;

            case 1:
                switch(posicao[1]){
                    case 0:
                        linha1coluna0.setIcon(iconPeaoCasaNormal);
                        break;

                    case 1:
                        linha1coluna1.setIcon(iconPeaoCasaNormal);
                        break;

                    case 2:
                        linha1coluna2.setIcon(iconPeaoCasaNormal);
                        break;

                    case 3:
                        linha1coluna3.setIcon(iconPeaoCasaNormal);
                        break;

                    case 4:
                        linha1coluna4.setIcon(iconPeaoCasaNormal);
                        break;

                     case 5:
                        linha1coluna5.setIcon(iconPeaoCasaNormal);
                        break;

                     case 6:
                        linha1coluna6.setIcon(iconPeaoCasaNormal);
                        break;

                     case 7:
                        linha1coluna7.setIcon(iconPeaoCasaNormal);
                        break;

                     case 8:
                        linha1coluna8.setIcon(iconPeaoCasaNormal);
                        break;

                     case 9:
                        linha1coluna9.setIcon(iconPeaoCasaPorta);
                        break;
                }
                break;

                case 2:
                switch(posicao[1]){
                    case 0:
                        linha2coluna0.setIcon(iconPeaoCasaNormal);
                        break;

                    case 1:
                        linha2coluna1.setIcon(iconPeaoCasaNormal);
                        break;

                    case 2:
                        linha2coluna2.setIcon(iconPeaoCasaNormal);
                        break;

                    case 3:
                        linha2coluna3.setIcon(iconPeaoCasaNormal);
                        break;

                    case 4:
                        linha2coluna4.setIcon(iconPeaoCasaNormal);
                        break;

                     case 5:
                        linha2coluna5.setIcon(iconPeaoCasaNormal);
                        break;

                     case 6:
                        linha2coluna6.setIcon(iconPeaoCasaNormal);
                        break;

                     case 7:
                        linha2coluna7.setIcon(iconPeaoCasaNormal);
                        break;

                     case 8:
                        linha2coluna8.setIcon(iconPeaoCasaNormal);
                        break;

                     case 9:
                        linha2coluna9.setIcon(iconPeaoCasaNormal);
                        break;
                }

                break;

                case 3:
                switch(posicao[1]){
                    case 0:
                        linha3coluna0.setIcon(iconPeaoCasaNormal);
                        break;

                    case 1:
                        linha3coluna1.setIcon(iconPeaoCasaNormal);
                        break;

                    case 2:
                        linha3coluna2.setIcon(iconPeaoCasaNormal);
                        break;

                    case 3:
                        linha3coluna3.setIcon(iconPeaoCasaNormal);
                        break;

                    case 4:
                        linha3coluna4.setIcon(iconPeaoCasaNormal);
                        break;

                     case 5:
                        linha3coluna5.setIcon(iconPeaoCasaNormal);
                        break;

                     case 6:
                        linha3coluna6.setIcon(iconPeaoCasaNormal);
                        break;

                     case 7:
                        linha3coluna7.setIcon(iconPeaoCasaNormal);
                        break;

                     case 8:
                        linha3coluna8.setIcon(iconPeaoCasaNormal);
                        break;

                     case 9:
                        linha3coluna9.setIcon(iconPeaoCasaNormal);
                        break;
                }

                break;

                case 4:
                switch(posicao[1]){
                    case 0:
                        linha4coluna0.setIcon(iconPeaoCasaPorta);
                        break;

                    case 1:
                        linha4coluna1.setIcon(iconPeaoCasaNormal);
                        break;

                    case 2:
                        linha4coluna2.setIcon(iconPeaoCasaNormal);
                        break;

                    case 3:
                        linha4coluna3.setIcon(iconPeaoCasaNormal);
                        break;

                    case 4:
                        linha4coluna4.setIcon(iconPeaoCasaNormal);
                        break;

                     case 5:
                        linha4coluna5.setIcon(iconPeaoCasaNormal);
                        break;

                     case 6:
                        linha4coluna6.setIcon(iconPeaoCasaNormal);
                        break;

                     case 7:
                        linha4coluna7.setIcon(iconPeaoCasaNormal);
                        break;

                     case 8:
                        linha4coluna8.setIcon(iconPeaoCasaNormal);
                        break;

                     case 9:
                        linha4coluna9.setIcon(iconPeaoCasaNormal);
                        break;
                }

                break;

                case 5:
                switch(posicao[1]){
                    case 0:
                        linha5coluna0.setIcon(iconPeaoCasaNormal);
                        break;

                    case 1:
                        linha5coluna1.setIcon(iconPeaoCasaNormal);
                        break;

                    case 2:
                        linha5coluna2.setIcon(iconPeaoCasaNormal);
                        break;

                    case 3:
                        linha5coluna3.setIcon(iconPeaoCasaNormal);
                        break;

                    case 4:
                        linha5coluna4.setIcon(iconPeaoCasaNormal);
                        break;

                     case 5:
                        linha5coluna5.setIcon(iconPeaoCasaNormal);
                        break;

                     case 6:
                        linha5coluna6.setIcon(iconPeaoCasaNormal);
                        break;

                     case 7:
                        linha5coluna7.setIcon(iconPeaoCasaNormal);
                        break;

                     case 8:
                        linha5coluna8.setIcon(iconPeaoCasaNormal);
                        break;

                     case 9:
                        linha5coluna9.setIcon(iconPeaoCasaNormal);
                        break;
                }

                break;

                case 6:
                switch(posicao[1]){
                    case 0:
                        linha6coluna0.setIcon(iconPeaoCasaNormal);
                        break;

                    case 1:
                        linha6coluna1.setIcon(iconPeaoCasaNormal);
                        break;

                    case 2:
                        linha6coluna2.setIcon(iconPeaoCasaNormal);
                        break;

                    case 3:
                        linha6coluna3.setIcon(iconPeaoCasaNormal);
                        break;

                    case 4:
                        linha6coluna4.setIcon(iconPeaoCasaNormal);
                        break;

                     case 5:
                        linha6coluna5.setIcon(iconPeaoCasaNormal);
                        break;

                     case 6:
                        linha6coluna6.setIcon(iconPeaoCasaNormal);
                        break;

                     case 7:
                        linha6coluna7.setIcon(iconPeaoCasaNormal);
                        break;

                     case 8:
                        linha6coluna8.setIcon(iconPeaoCasaNormal);
                        break;

                     case 9:
                        linha6coluna9.setIcon(iconPeaoCasaPorta);
                        break;
                }

                break;

                case 7:
                switch(posicao[1]){
                    case 0:
                        linha7coluna0.setIcon(iconPeaoCasaNormal);
                        break;

                    case 1:
                        linha7coluna1.setIcon(iconPeaoCasaNormal);
                        break;

                    case 2:
                        linha7coluna2.setIcon(iconPeaoCasaNormal);
                        break;

                    case 3:
                        linha7coluna3.setIcon(iconPeaoCasaNormal);
                        break;

                    case 4:
                        linha7coluna4.setIcon(iconPeaoCasaNormal);
                        break;

                     case 5:
                        linha7coluna5.setIcon(iconPeaoCasaNormal);
                        break;

                     case 6:
                        linha7coluna6.setIcon(iconPeaoCasaNormal);
                        break;

                     case 7:
                        linha7coluna7.setIcon(iconPeaoCasaNormal);
                        break;

                     case 8:
                        linha7coluna8.setIcon(iconPeaoCasaNormal);
                        break;

                     case 9:
                        linha7coluna9.setIcon(iconPeaoCasaNormal);
                        break;
                }

                break;

                case 8:
                switch(posicao[1]){
                    case 0:
                        linha8coluna0.setIcon(iconPeaoCasaNormal);
                        break;

                    case 1:
                        linha8coluna1.setIcon(iconPeaoCasaNormal);
                        break;

                    case 2:
                        linha8coluna2.setIcon(iconPeaoCasaNormal);
                        break;

                    case 3:
                        linha8coluna3.setIcon(iconPeaoCasaNormal);
                        break;

                    case 4:
                        linha8coluna4.setIcon(iconPeaoCasaNormal);
                        break;

                     case 5:
                        linha8coluna5.setIcon(iconPeaoCasaNormal);
                        break;

                     case 6:
                        linha8coluna6.setIcon(iconPeaoCasaNormal);
                        break;

                     case 7:
                        linha8coluna7.setIcon(iconPeaoCasaNormal);
                        break;

                     case 8:
                        linha8coluna8.setIcon(iconPeaoCasaNormal);
                        break;

                     case 9:
                        linha8coluna9.setIcon(iconPeaoCasaNormal);
                        break;
                }

                break;

                case 9:
                switch(posicao[1]){
                    case 0:
                        linha9coluna0.setIcon(iconPeaoCasaPorta);
                        break;

                    case 1:
                        linha9coluna1.setIcon(iconPeaoCasaNormal);
                        break;

                    case 2:
                        linha9coluna2.setIcon(iconPeaoCasaNormal);
                        break;

                    case 3:
                        linha9coluna3.setIcon(iconPeaoCasaNormal);
                        break;

                    case 4:
                        linha9coluna4.setIcon(iconPeaoCasaNormal);
                        break;

                     case 5:
                        linha9coluna5.setIcon(iconPeaoCasaPorta);
                        break;

                     case 6:
                        linha9coluna6.setIcon(iconPeaoCasaNormal);
                        break;

                     case 7:
                        linha9coluna7.setIcon(iconPeaoCasaNormal);
                        break;

                     case 8:
                        linha9coluna8.setIcon(iconPeaoCasaNormal);
                        break;

                     case 9:
                        linha9coluna9.setIcon(iconPeaoCasaNormal);
                        break;
                }

        }


    }

   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaoJogarDado = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoLogDaPartida = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        linha1coluna0 = new javax.swing.JButton();
        linha1coluna1 = new javax.swing.JButton();
        linha1coluna2 = new javax.swing.JButton();
        linha1coluna4 = new javax.swing.JButton();
        linha1coluna5 = new javax.swing.JButton();
        linha1coluna6 = new javax.swing.JButton();
        linha1coluna7 = new javax.swing.JButton();
        linha1coluna8 = new javax.swing.JButton();
        linha1coluna9 = new javax.swing.JButton();
        linha2coluna0 = new javax.swing.JButton();
        linha2coluna1 = new javax.swing.JButton();
        linha2coluna2 = new javax.swing.JButton();
        linha2coluna3 = new javax.swing.JButton();
        linha2coluna4 = new javax.swing.JButton();
        linha2coluna5 = new javax.swing.JButton();
        linha2coluna6 = new javax.swing.JButton();
        linha2coluna7 = new javax.swing.JButton();
        linha2coluna8 = new javax.swing.JButton();
        linha2coluna9 = new javax.swing.JButton();
        linha3coluna0 = new javax.swing.JButton();
        linha3coluna1 = new javax.swing.JButton();
        linha3coluna2 = new javax.swing.JButton();
        linha3coluna3 = new javax.swing.JButton();
        linha3coluna4 = new javax.swing.JButton();
        linha3coluna5 = new javax.swing.JButton();
        linha3coluna6 = new javax.swing.JButton();
        linha3coluna7 = new javax.swing.JButton();
        linha3coluna8 = new javax.swing.JButton();
        linha3coluna9 = new javax.swing.JButton();
        linha4coluna0 = new javax.swing.JButton();
        linha4coluna1 = new javax.swing.JButton();
        linha4coluna2 = new javax.swing.JButton();
        linha4coluna3 = new javax.swing.JButton();
        linha4coluna4 = new javax.swing.JButton();
        linha4coluna5 = new javax.swing.JButton();
        linha4coluna6 = new javax.swing.JButton();
        linha4coluna7 = new javax.swing.JButton();
        linha4coluna8 = new javax.swing.JButton();
        linha4coluna9 = new javax.swing.JButton();
        linha6coluna0 = new javax.swing.JButton();
        linha6coluna1 = new javax.swing.JButton();
        linha6coluna2 = new javax.swing.JButton();
        linha6coluna3 = new javax.swing.JButton();
        linha6coluna4 = new javax.swing.JButton();
        linha6coluna5 = new javax.swing.JButton();
        linha6coluna6 = new javax.swing.JButton();
        linha6coluna7 = new javax.swing.JButton();
        linha6coluna8 = new javax.swing.JButton();
        linha6coluna9 = new javax.swing.JButton();
        linha5coluna0 = new javax.swing.JButton();
        linha5coluna1 = new javax.swing.JButton();
        linha5coluna2 = new javax.swing.JButton();
        linha5coluna3 = new javax.swing.JButton();
        linha5coluna4 = new javax.swing.JButton();
        linha5coluna5 = new javax.swing.JButton();
        linha5coluna6 = new javax.swing.JButton();
        linha5coluna7 = new javax.swing.JButton();
        linha5coluna8 = new javax.swing.JButton();
        linha5coluna9 = new javax.swing.JButton();
        linha7coluna0 = new javax.swing.JButton();
        linha7coluna1 = new javax.swing.JButton();
        linha7coluna2 = new javax.swing.JButton();
        linha7coluna3 = new javax.swing.JButton();
        linha7coluna4 = new javax.swing.JButton();
        linha7coluna5 = new javax.swing.JButton();
        linha7coluna6 = new javax.swing.JButton();
        linha7coluna7 = new javax.swing.JButton();
        linha7coluna8 = new javax.swing.JButton();
        linha7coluna9 = new javax.swing.JButton();
        linha8coluna0 = new javax.swing.JButton();
        linha8coluna1 = new javax.swing.JButton();
        linha8coluna2 = new javax.swing.JButton();
        linha8coluna3 = new javax.swing.JButton();
        linha8coluna4 = new javax.swing.JButton();
        linha8coluna5 = new javax.swing.JButton();
        linha8coluna6 = new javax.swing.JButton();
        linha8coluna7 = new javax.swing.JButton();
        linha8coluna8 = new javax.swing.JButton();
        linha8coluna9 = new javax.swing.JButton();
        linha9coluna0 = new javax.swing.JButton();
        linha9coluna1 = new javax.swing.JButton();
        linha9coluna2 = new javax.swing.JButton();
        linha9coluna3 = new javax.swing.JButton();
        linha9coluna4 = new javax.swing.JButton();
        linha9coluna5 = new javax.swing.JButton();
        linha9coluna6 = new javax.swing.JButton();
        linha9coluna7 = new javax.swing.JButton();
        linha9coluna8 = new javax.swing.JButton();
        linha9coluna9 = new javax.swing.JButton();
        linha0coluna0 = new javax.swing.JButton();
        linha0coluna1 = new javax.swing.JButton();
        linha0coluna2 = new javax.swing.JButton();
        linha0coluna3 = new javax.swing.JButton();
        linha0coluna4 = new javax.swing.JButton();
        linha0coluna5 = new javax.swing.JButton();
        linha0coluna6 = new javax.swing.JButton();
        linha0coluna7 = new javax.swing.JButton();
        linha0coluna8 = new javax.swing.JButton();
        linha0coluna9 = new javax.swing.JButton();
        linha1coluna3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoPassosRestantes = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        labelNomeJogador = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        labelCarta1 = new javax.swing.JLabel();
        labelCarta2 = new javax.swing.JLabel();
        labelCarta3 = new javax.swing.JLabel();
        comboArmas = new javax.swing.JComboBox();
        comboLocais = new javax.swing.JComboBox();
        comboSuspeitos = new javax.swing.JComboBox();
        labelCarta4 = new javax.swing.JLabel();
        labelCarta5 = new javax.swing.JLabel();
        labelCarta6 = new javax.swing.JLabel();
        labelCarta7 = new javax.swing.JLabel();
        labelCarta8 = new javax.swing.JLabel();
        labelCarta9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        botaoIniciarPartida = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        botaoJogarDado.setText("Jogar Dado");
        botaoJogarDado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoJogarDadoActionPerformed(evt);
            }
        });

        jButton3.setText("Palpite");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Acusação");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Passar a Vez");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setText("LOG DA PARTIDA");

        campoLogDaPartida.setColumns(20);
        campoLogDaPartida.setRows(5);
        jScrollPane1.setViewportView(campoLogDaPartida);

        linha1coluna0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna0.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha1coluna0ActionPerformed(evt);
            }
        });

        linha1coluna1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha1coluna1ActionPerformed(evt);
            }
        });

        linha1coluna2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha1coluna2ActionPerformed(evt);
            }
        });

        linha1coluna4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha1coluna4ActionPerformed(evt);
            }
        });

        linha1coluna5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha1coluna5ActionPerformed(evt);
            }
        });

        linha1coluna6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha1coluna6ActionPerformed(evt);
            }
        });

        linha1coluna7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha1coluna7ActionPerformed(evt);
            }
        });

        linha1coluna8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha1coluna8ActionPerformed(evt);
            }
        });

        linha1coluna9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casaporta.jpg"))); // NOI18N
        linha1coluna9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha1coluna9ActionPerformed(evt);
            }
        });

        linha2coluna0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna0.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha2coluna0ActionPerformed(evt);
            }
        });

        linha2coluna1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha2coluna1ActionPerformed(evt);
            }
        });

        linha2coluna2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha2coluna2ActionPerformed(evt);
            }
        });

        linha2coluna3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha2coluna3ActionPerformed(evt);
            }
        });

        linha2coluna4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha2coluna4ActionPerformed(evt);
            }
        });

        linha2coluna5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha2coluna5ActionPerformed(evt);
            }
        });

        linha2coluna6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha2coluna6ActionPerformed(evt);
            }
        });

        linha2coluna7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha2coluna7ActionPerformed(evt);
            }
        });

        linha2coluna8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha2coluna8ActionPerformed(evt);
            }
        });

        linha2coluna9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha2coluna9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha2coluna9ActionPerformed(evt);
            }
        });

        linha3coluna0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna0.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha3coluna0ActionPerformed(evt);
            }
        });

        linha3coluna1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha3coluna1ActionPerformed(evt);
            }
        });

        linha3coluna2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha3coluna2ActionPerformed(evt);
            }
        });

        linha3coluna3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha3coluna3ActionPerformed(evt);
            }
        });

        linha3coluna4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha3coluna4ActionPerformed(evt);
            }
        });

        linha3coluna5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha3coluna5ActionPerformed(evt);
            }
        });

        linha3coluna6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha3coluna6ActionPerformed(evt);
            }
        });

        linha3coluna7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha3coluna7ActionPerformed(evt);
            }
        });

        linha3coluna8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha3coluna8ActionPerformed(evt);
            }
        });

        linha3coluna9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha3coluna9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha3coluna9ActionPerformed(evt);
            }
        });

        linha4coluna0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casaporta.jpg"))); // NOI18N
        linha4coluna0.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha4coluna0ActionPerformed(evt);
            }
        });

        linha4coluna1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha4coluna1ActionPerformed(evt);
            }
        });

        linha4coluna2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha4coluna2ActionPerformed(evt);
            }
        });

        linha4coluna3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha4coluna3ActionPerformed(evt);
            }
        });

        linha4coluna4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casaPeao1.jpg"))); // NOI18N
        linha4coluna4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha4coluna4ActionPerformed(evt);
            }
        });

        linha4coluna5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casaPeao2.jpg"))); // NOI18N
        linha4coluna5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha4coluna5ActionPerformed(evt);
            }
        });

        linha4coluna6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha4coluna6ActionPerformed(evt);
            }
        });

        linha4coluna7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha4coluna7ActionPerformed(evt);
            }
        });

        linha4coluna8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha4coluna8ActionPerformed(evt);
            }
        });

        linha4coluna9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha4coluna9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha4coluna9ActionPerformed(evt);
            }
        });

        linha6coluna0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna0.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha6coluna0ActionPerformed(evt);
            }
        });

        linha6coluna1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha6coluna1ActionPerformed(evt);
            }
        });

        linha6coluna2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha6coluna2ActionPerformed(evt);
            }
        });

        linha6coluna3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha6coluna3ActionPerformed(evt);
            }
        });

        linha6coluna4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha6coluna4ActionPerformed(evt);
            }
        });

        linha6coluna5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha6coluna5ActionPerformed(evt);
            }
        });

        linha6coluna6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha6coluna6ActionPerformed(evt);
            }
        });

        linha6coluna7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha6coluna7ActionPerformed(evt);
            }
        });

        linha6coluna8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha6coluna8ActionPerformed(evt);
            }
        });

        linha6coluna9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casaporta.jpg"))); // NOI18N
        linha6coluna9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha6coluna9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha6coluna9ActionPerformed(evt);
            }
        });

        linha5coluna0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna0.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha5coluna0ActionPerformed(evt);
            }
        });

        linha5coluna1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha5coluna1ActionPerformed(evt);
            }
        });

        linha5coluna2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha5coluna2ActionPerformed(evt);
            }
        });

        linha5coluna3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha5coluna3ActionPerformed(evt);
            }
        });

        linha5coluna4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha5coluna4ActionPerformed(evt);
            }
        });

        linha5coluna5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha5coluna5ActionPerformed(evt);
            }
        });

        linha5coluna6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha5coluna6ActionPerformed(evt);
            }
        });

        linha5coluna7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha5coluna7ActionPerformed(evt);
            }
        });

        linha5coluna8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha5coluna8ActionPerformed(evt);
            }
        });

        linha5coluna9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha5coluna9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha5coluna9ActionPerformed(evt);
            }
        });

        linha7coluna0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna0.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha7coluna0ActionPerformed(evt);
            }
        });

        linha7coluna1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha7coluna1ActionPerformed(evt);
            }
        });

        linha7coluna2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha7coluna2ActionPerformed(evt);
            }
        });

        linha7coluna3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha7coluna3ActionPerformed(evt);
            }
        });

        linha7coluna4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha7coluna4ActionPerformed(evt);
            }
        });

        linha7coluna5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha7coluna5ActionPerformed(evt);
            }
        });

        linha7coluna6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha7coluna6ActionPerformed(evt);
            }
        });

        linha7coluna7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha7coluna7ActionPerformed(evt);
            }
        });

        linha7coluna8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha7coluna8ActionPerformed(evt);
            }
        });

        linha7coluna9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha7coluna9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha7coluna9ActionPerformed(evt);
            }
        });

        linha8coluna0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna0.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha8coluna0ActionPerformed(evt);
            }
        });

        linha8coluna1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha8coluna1ActionPerformed(evt);
            }
        });

        linha8coluna2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha8coluna2ActionPerformed(evt);
            }
        });

        linha8coluna3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha8coluna3ActionPerformed(evt);
            }
        });

        linha8coluna4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha8coluna4ActionPerformed(evt);
            }
        });

        linha8coluna5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha8coluna5ActionPerformed(evt);
            }
        });

        linha8coluna6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha8coluna6ActionPerformed(evt);
            }
        });

        linha8coluna7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha8coluna7ActionPerformed(evt);
            }
        });

        linha8coluna8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha8coluna8ActionPerformed(evt);
            }
        });

        linha8coluna9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha8coluna9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha8coluna9ActionPerformed(evt);
            }
        });

        linha9coluna0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casaporta.jpg"))); // NOI18N
        linha9coluna0.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha9coluna0ActionPerformed(evt);
            }
        });

        linha9coluna1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha9coluna1ActionPerformed(evt);
            }
        });

        linha9coluna2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha9coluna2ActionPerformed(evt);
            }
        });

        linha9coluna3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha9coluna3ActionPerformed(evt);
            }
        });

        linha9coluna4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha9coluna4ActionPerformed(evt);
            }
        });

        linha9coluna5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casaporta.jpg"))); // NOI18N
        linha9coluna5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha9coluna5ActionPerformed(evt);
            }
        });

        linha9coluna6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha9coluna6ActionPerformed(evt);
            }
        });

        linha9coluna7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha9coluna7ActionPerformed(evt);
            }
        });

        linha9coluna8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha9coluna8ActionPerformed(evt);
            }
        });

        linha9coluna9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha9coluna9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha9coluna9ActionPerformed(evt);
            }
        });

        linha0coluna0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casaporta.jpg"))); // NOI18N
        linha0coluna0.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha0coluna0ActionPerformed(evt);
            }
        });

        linha0coluna1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha0coluna1ActionPerformed(evt);
            }
        });

        linha0coluna2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha0coluna2ActionPerformed(evt);
            }
        });

        linha0coluna3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha0coluna3ActionPerformed(evt);
            }
        });

        linha0coluna4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha0coluna4ActionPerformed(evt);
            }
        });

        linha0coluna5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casaporta.jpg"))); // NOI18N
        linha0coluna5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha0coluna5ActionPerformed(evt);
            }
        });

        linha0coluna6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha0coluna6ActionPerformed(evt);
            }
        });

        linha0coluna7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha0coluna7ActionPerformed(evt);
            }
        });

        linha0coluna8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha0coluna8ActionPerformed(evt);
            }
        });

        linha0coluna9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha0coluna9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha0coluna9ActionPerformed(evt);
            }
        });

        linha1coluna3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/casa.jpg"))); // NOI18N
        linha1coluna3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linha1coluna3ActionPerformed(evt);
            }
        });

        jLabel1.setText("NPD");

        jLabel6.setText("EPS");

        jLabel2.setText("Reitoria");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(linha1coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha1coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha1coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha1coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha1coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha1coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha1coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha1coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha1coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha1coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(linha2coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha2coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha2coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha2coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha2coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha2coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha2coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha2coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha2coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha2coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(linha3coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha3coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha3coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha3coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha3coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha3coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha3coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha3coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha3coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha3coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(linha4coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha4coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha4coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(linha4coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha5coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha4coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha4coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha4coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha4coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha4coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha4coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(linha6coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha6coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha6coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha6coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha6coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha6coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha6coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha6coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha6coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha6coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(linha5coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha5coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha5coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(47, 47, 47)
                            .addComponent(linha5coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha5coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha5coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha5coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha5coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha5coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(linha7coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha7coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha7coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha7coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha7coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha7coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha7coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha7coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha7coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha7coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(linha8coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha8coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha8coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha8coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha8coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha8coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha8coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha8coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha8coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha8coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(linha9coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha9coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha9coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha9coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha9coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha9coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha9coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha9coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha9coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(linha9coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(linha0coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha0coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha0coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha0coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha0coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(linha0coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(linha0coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(linha0coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(linha0coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(linha0coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(linha0coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha0coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha0coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha0coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha0coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha0coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha0coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha0coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha0coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha0coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(linha1coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha1coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha1coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha1coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha1coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha1coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha1coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha1coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha1coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha1coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(linha2coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha2coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha2coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha2coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha2coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha2coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha2coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha2coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha2coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha2coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(linha3coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha3coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha3coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha3coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha3coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha3coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha3coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha3coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha3coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha3coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(linha4coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha4coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha4coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha4coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha4coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha4coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha4coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha4coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha4coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(linha5coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha5coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha5coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha5coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha5coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha5coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha5coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha5coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linha5coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(linha4coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linha5coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(linha6coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha6coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha6coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha6coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha6coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha6coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha6coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha6coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha6coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha6coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(linha7coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha7coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha7coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha7coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha7coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha7coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha7coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha7coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha7coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha7coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(linha8coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha8coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha8coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha8coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha8coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha8coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha8coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha8coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha8coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha8coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(linha9coluna4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha9coluna9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha9coluna6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha9coluna5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha9coluna7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha9coluna8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha9coluna1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha9coluna0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha9coluna2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linha9coluna3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel9.setText("Passos Restantes");

        labelNomeJogador.setText("Jogador");

        jLabel11.setText("Minhas Cartas");

        labelCarta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/cartaAlazao.jpg"))); // NOI18N

        labelCarta2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/cartaAlazao.jpg"))); // NOI18N

        labelCarta3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/cartaAlazao.jpg"))); // NOI18N

        comboArmas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Castiçal", "Chave Inglesa", "Revolver", "Corda", "Cano", "Faca", "Array de Bytes" }));
        comboArmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboArmasActionPerformed(evt);
            }
        });

        comboLocais.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BU", "Reitoria", "NPD", "EPS", "CTC", "INE", "CCS" }));

        comboSuspeitos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Arthur", "DeLucca", "Isaias", "Frank", "Fileto", "Ricardo", "Carla" }));

        labelCarta4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/cartaAlazao.jpg"))); // NOI18N

        labelCarta5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/cartaAlazao.jpg"))); // NOI18N

        labelCarta6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/cartaAlazao.jpg"))); // NOI18N

        labelCarta7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/cartaAlazao.jpg"))); // NOI18N

        labelCarta8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/cartaAlazao.jpg"))); // NOI18N

        labelCarta9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaceGrafica/cartaAlazao.jpg"))); // NOI18N

        jLabel18.setText("Arma");

        jLabel22.setText("Local");

        jLabel23.setText("Suspeito");

        jLabel24.setText("Cartas Selecionadas para Palpite ou Acusação");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCarta4)
                            .addComponent(labelCarta7)
                            .addComponent(comboArmas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(labelCarta1))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCarta5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboLocais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22)))
                            .addComponent(labelCarta8)
                            .addComponent(labelCarta2))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11))
                            .addComponent(labelCarta3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboSuspeitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)))
                            .addComponent(labelCarta9)
                            .addComponent(labelCarta6))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCarta1)
                    .addComponent(labelCarta2)
                    .addComponent(labelCarta3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCarta5)
                    .addComponent(labelCarta6)
                    .addComponent(labelCarta4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCarta7)
                            .addComponent(labelCarta8))
                        .addGap(17, 17, 17)
                        .addComponent(jLabel24))
                    .addComponent(labelCarta9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboArmas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboLocais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSuspeitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(149, 149, 149))
        );

        jLabel3.setText("BU");

        jLabel5.setText("CCS");

        jLabel7.setText("CTC");

        jLabel8.setText("INE");

        jMenu1.setText("Partida");

        botaoIniciarPartida.setText("Iniciar Partida na Rede");
        botaoIniciarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIniciarPartidaActionPerformed(evt);
            }
        });
        jMenu1.add(botaoIniciarPartida);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(407, 407, 407)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(labelNomeJogador))
                            .addComponent(jLabel10)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(campoPassosRestantes, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(botaoJogarDado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel7)
                                .addGap(201, 201, 201)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(botaoJogarDado, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addGap(13, 13, 13)
                        .addComponent(campoPassosRestantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNomeJogador)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(jLabel3)
                                .addGap(175, 175, 175)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoJogarDadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoJogarDadoActionPerformed
    clickJogarDado();

}//GEN-LAST:event_botaoJogarDadoActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    clickPassarAvez();
}//GEN-LAST:event_jButton5ActionPerformed

private void linha3coluna3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha3coluna3ActionPerformed
    clickPosicao(3, 3);
    // TODO add your handling code here:
}//GEN-LAST:event_linha3coluna3ActionPerformed

private void linha0coluna0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha0coluna0ActionPerformed
    clickPosicao(0, 0);
}//GEN-LAST:event_linha0coluna0ActionPerformed

private void linha4coluna2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha4coluna2ActionPerformed
    clickPosicao(4, 2);
    // TODO add your handling code here:
}//GEN-LAST:event_linha4coluna2ActionPerformed

private void linha4coluna3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha4coluna3ActionPerformed
    clickPosicao(4, 3);
    // TODO add your handling code here:
}//GEN-LAST:event_linha4coluna3ActionPerformed

private void linha1coluna0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha1coluna0ActionPerformed
    clickPosicao(1, 0);
}//GEN-LAST:event_linha1coluna0ActionPerformed

private void linha2coluna0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha2coluna0ActionPerformed
    clickPosicao(2, 0);
    // TODO add your handling code here:
}//GEN-LAST:event_linha2coluna0ActionPerformed

private void linha3coluna0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha3coluna0ActionPerformed
    clickPosicao(3, 0);
}//GEN-LAST:event_linha3coluna0ActionPerformed

private void linha4coluna0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha4coluna0ActionPerformed
    clickPosicao(4, 0);
}//GEN-LAST:event_linha4coluna0ActionPerformed

private void linha5coluna0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha5coluna0ActionPerformed
   clickPosicao(5, 0);
}//GEN-LAST:event_linha5coluna0ActionPerformed

private void linha6coluna0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha6coluna0ActionPerformed
   clickPosicao(6, 0);
}//GEN-LAST:event_linha6coluna0ActionPerformed

private void linha7coluna0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha7coluna0ActionPerformed
    // TODO add your handling code here:
    clickPosicao(7, 0);
}//GEN-LAST:event_linha7coluna0ActionPerformed

private void linha8coluna0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha8coluna0ActionPerformed
    clickPosicao(8, 0);
    // TODO add your handling code here:
}//GEN-LAST:event_linha8coluna0ActionPerformed

private void linha9coluna0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha9coluna0ActionPerformed
    clickPosicao(9, 0);
    // TODO add your handling code here:
}//GEN-LAST:event_linha9coluna0ActionPerformed

private void linha0coluna1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha0coluna1ActionPerformed
    clickPosicao(0, 1);
    // TODO add your handling code here:
}//GEN-LAST:event_linha0coluna1ActionPerformed

private void linha1coluna1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha1coluna1ActionPerformed
    clickPosicao(1, 1);
    // TODO add your handling code here:
}//GEN-LAST:event_linha1coluna1ActionPerformed

private void linha2coluna1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha2coluna1ActionPerformed
    clickPosicao(2, 1);
    // TODO add your handling code here:
}//GEN-LAST:event_linha2coluna1ActionPerformed

private void linha3coluna1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha3coluna1ActionPerformed
    clickPosicao(3, 1);
    // TODO add your handling code here:
}//GEN-LAST:event_linha3coluna1ActionPerformed

private void linha4coluna1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha4coluna1ActionPerformed
    clickPosicao(4, 1);
    // TODO add your handling code here:
}//GEN-LAST:event_linha4coluna1ActionPerformed

private void linha5coluna1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha5coluna1ActionPerformed
    clickPosicao(5, 1);
    // TODO add your handling code here:
}//GEN-LAST:event_linha5coluna1ActionPerformed

private void linha6coluna1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha6coluna1ActionPerformed
    clickPosicao(6, 1);
    // TODO add your handling code here:
}//GEN-LAST:event_linha6coluna1ActionPerformed

private void linha7coluna1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha7coluna1ActionPerformed
    clickPosicao(7, 1);
    // TODO add your handling code here:
}//GEN-LAST:event_linha7coluna1ActionPerformed

private void linha8coluna1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha8coluna1ActionPerformed
    clickPosicao(8, 1);
    // TODO add your handling code here:
}//GEN-LAST:event_linha8coluna1ActionPerformed

private void linha9coluna1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha9coluna1ActionPerformed
    clickPosicao(9, 1);
    // TODO add your handling code here:
}//GEN-LAST:event_linha9coluna1ActionPerformed

private void linha0coluna2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha0coluna2ActionPerformed
    clickPosicao(0, 2);
    // TODO add your handling code here:
}//GEN-LAST:event_linha0coluna2ActionPerformed

private void linha1coluna2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha1coluna2ActionPerformed
    clickPosicao(1, 2);
    // TODO add your handling code here:
}//GEN-LAST:event_linha1coluna2ActionPerformed

private void linha2coluna2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha2coluna2ActionPerformed
    clickPosicao(2, 2);
    // TODO add your handling code here:
}//GEN-LAST:event_linha2coluna2ActionPerformed

private void linha3coluna2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha3coluna2ActionPerformed
    clickPosicao(3, 2);
    // TODO add your handling code here:
}//GEN-LAST:event_linha3coluna2ActionPerformed

private void linha5coluna2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha5coluna2ActionPerformed
    clickPosicao(5, 2);
    // TODO add your handling code here:
}//GEN-LAST:event_linha5coluna2ActionPerformed

private void linha6coluna2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha6coluna2ActionPerformed
    clickPosicao(6, 2);
    // TODO add your handling code here:
}//GEN-LAST:event_linha6coluna2ActionPerformed

private void linha7coluna2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha7coluna2ActionPerformed
    clickPosicao(7, 2);
    // TODO add your handling code here:
}//GEN-LAST:event_linha7coluna2ActionPerformed

private void linha8coluna2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha8coluna2ActionPerformed
    clickPosicao(8, 2);
    // TODO add your handling code here:
}//GEN-LAST:event_linha8coluna2ActionPerformed

private void linha9coluna2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha9coluna2ActionPerformed
    clickPosicao(9, 2);
    // TODO add your handling code here:
}//GEN-LAST:event_linha9coluna2ActionPerformed

private void linha0coluna3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha0coluna3ActionPerformed
    clickPosicao(0, 3);
    // TODO add your handling code here:
}//GEN-LAST:event_linha0coluna3ActionPerformed

private void linha1coluna3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha1coluna3ActionPerformed
    clickPosicao(1, 3);
        // TODO add your handling code here:
}//GEN-LAST:event_linha1coluna3ActionPerformed

private void linha2coluna3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha2coluna3ActionPerformed
    clickPosicao(2, 3);
    // TODO add your handling code here:
}//GEN-LAST:event_linha2coluna3ActionPerformed

private void linha5coluna3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha5coluna3ActionPerformed
    clickPosicao(5, 3);
    // TODO add your handling code here:
}//GEN-LAST:event_linha5coluna3ActionPerformed

private void linha6coluna3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha6coluna3ActionPerformed
    clickPosicao(6, 3);
    // TODO add your handling code here:
}//GEN-LAST:event_linha6coluna3ActionPerformed

private void linha7coluna3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha7coluna3ActionPerformed
    clickPosicao(7, 3);
    // TODO add your handling code here:
}//GEN-LAST:event_linha7coluna3ActionPerformed

private void linha8coluna3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha8coluna3ActionPerformed
    clickPosicao(8, 3);
    // TODO add your handling code here:
}//GEN-LAST:event_linha8coluna3ActionPerformed

private void linha9coluna3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha9coluna3ActionPerformed
    clickPosicao(9, 3);
    // TODO add your handling code here:
}//GEN-LAST:event_linha9coluna3ActionPerformed

private void linha0coluna4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha0coluna4ActionPerformed
    clickPosicao(0, 4);
    // TODO add your handling code here:
}//GEN-LAST:event_linha0coluna4ActionPerformed

private void linha1coluna4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha1coluna4ActionPerformed
    clickPosicao(1, 4);
    // TODO add your handling code here:
}//GEN-LAST:event_linha1coluna4ActionPerformed

private void linha2coluna4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha2coluna4ActionPerformed
    clickPosicao(2, 4);
    // TODO add your handling code here:
}//GEN-LAST:event_linha2coluna4ActionPerformed

private void linha3coluna4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha3coluna4ActionPerformed
    clickPosicao(3, 4);
    // TODO add your handling code here:
}//GEN-LAST:event_linha3coluna4ActionPerformed

private void linha4coluna4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha4coluna4ActionPerformed
    clickPosicao(4, 4);
    // TODO add your handling code here:
}//GEN-LAST:event_linha4coluna4ActionPerformed

private void linha5coluna4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha5coluna4ActionPerformed
    clickPosicao(5, 4);
    // TODO add your handling code here:
}//GEN-LAST:event_linha5coluna4ActionPerformed

private void linha6coluna4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha6coluna4ActionPerformed
    clickPosicao(6, 4);
    // TODO add your handling code here:
}//GEN-LAST:event_linha6coluna4ActionPerformed

private void linha7coluna4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha7coluna4ActionPerformed
    clickPosicao(7, 4);
    // TODO add your handling code here:
}//GEN-LAST:event_linha7coluna4ActionPerformed

private void linha8coluna4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha8coluna4ActionPerformed
    clickPosicao(8, 4);
    // TODO add your handling code here:
}//GEN-LAST:event_linha8coluna4ActionPerformed

private void linha9coluna4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha9coluna4ActionPerformed
    clickPosicao(9, 4);
    // TODO add your handling code here:
}//GEN-LAST:event_linha9coluna4ActionPerformed

private void linha0coluna5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha0coluna5ActionPerformed
    clickPosicao(0, 5);
    // TODO add your handling code here:
}//GEN-LAST:event_linha0coluna5ActionPerformed

private void linha1coluna5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha1coluna5ActionPerformed
    clickPosicao(1, 5);
    // TODO add your handling code here:
}//GEN-LAST:event_linha1coluna5ActionPerformed

private void linha2coluna5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha2coluna5ActionPerformed
    clickPosicao(2, 5);
    // TODO add your handling code here:
}//GEN-LAST:event_linha2coluna5ActionPerformed

private void linha3coluna5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha3coluna5ActionPerformed
    clickPosicao(3, 5);
    // TODO add your handling code here:
}//GEN-LAST:event_linha3coluna5ActionPerformed

private void linha4coluna5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha4coluna5ActionPerformed
    clickPosicao(4, 5);
    // TODO add your handling code here:
}//GEN-LAST:event_linha4coluna5ActionPerformed

private void linha5coluna5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha5coluna5ActionPerformed
    clickPosicao(5, 5);
    // TODO add your handling code here:
}//GEN-LAST:event_linha5coluna5ActionPerformed

private void linha6coluna5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha6coluna5ActionPerformed
    clickPosicao(6, 5);
    // TODO add your handling code here:
}//GEN-LAST:event_linha6coluna5ActionPerformed

private void linha7coluna5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha7coluna5ActionPerformed
     clickPosicao(7, 5);
    // TODO add your handling code here:
}//GEN-LAST:event_linha7coluna5ActionPerformed

private void linha8coluna5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha8coluna5ActionPerformed
     clickPosicao(8, 5);
        // TODO add your handling code here:
}//GEN-LAST:event_linha8coluna5ActionPerformed

private void linha9coluna5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha9coluna5ActionPerformed
     clickPosicao(9, 5);
    // TODO add your handling code here:
}//GEN-LAST:event_linha9coluna5ActionPerformed

private void linha0coluna6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha0coluna6ActionPerformed
     clickPosicao(0, 6);
    // TODO add your handling code here:
}//GEN-LAST:event_linha0coluna6ActionPerformed

private void linha1coluna6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha1coluna6ActionPerformed
    clickPosicao(1, 6);
    // TODO add your handling code here:
}//GEN-LAST:event_linha1coluna6ActionPerformed

private void linha2coluna6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha2coluna6ActionPerformed
    clickPosicao(2, 6);
    // TODO add your handling code here:
}//GEN-LAST:event_linha2coluna6ActionPerformed

private void linha3coluna6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha3coluna6ActionPerformed
    clickPosicao(3, 6);
    // TODO add your handling code here:
}//GEN-LAST:event_linha3coluna6ActionPerformed

private void linha4coluna6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha4coluna6ActionPerformed
    clickPosicao(4, 6);
    // TODO add your handling code here:
}//GEN-LAST:event_linha4coluna6ActionPerformed

private void linha5coluna6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha5coluna6ActionPerformed
    clickPosicao(5, 6);
    // TODO add your handling code here:
}//GEN-LAST:event_linha5coluna6ActionPerformed

private void linha6coluna6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha6coluna6ActionPerformed
    clickPosicao(6, 6);
    // TODO add your handling code here:
}//GEN-LAST:event_linha6coluna6ActionPerformed

private void linha7coluna6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha7coluna6ActionPerformed
    clickPosicao(7, 6);
    // TODO add your handling code here:
}//GEN-LAST:event_linha7coluna6ActionPerformed

private void linha8coluna6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha8coluna6ActionPerformed
    clickPosicao(8, 6);
    // TODO add your handling code here:
}//GEN-LAST:event_linha8coluna6ActionPerformed

private void linha9coluna6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha9coluna6ActionPerformed
    clickPosicao(9, 6);
    // TODO add your handling code here:
}//GEN-LAST:event_linha9coluna6ActionPerformed

private void linha0coluna7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha0coluna7ActionPerformed
    clickPosicao(0, 7);
    // TODO add your handling code here:
}//GEN-LAST:event_linha0coluna7ActionPerformed

private void linha1coluna7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha1coluna7ActionPerformed
    clickPosicao(1, 7);
    // TODO add your handling code here:
}//GEN-LAST:event_linha1coluna7ActionPerformed

private void linha2coluna7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha2coluna7ActionPerformed
    clickPosicao(2, 7);
    // TODO add your handling code here:
}//GEN-LAST:event_linha2coluna7ActionPerformed

private void linha3coluna7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha3coluna7ActionPerformed
    clickPosicao(3, 7);
    // TODO add your handling code here:
}//GEN-LAST:event_linha3coluna7ActionPerformed

private void linha4coluna7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha4coluna7ActionPerformed
    clickPosicao(4, 7);
    // TODO add your handling code here:
}//GEN-LAST:event_linha4coluna7ActionPerformed

private void linha5coluna7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha5coluna7ActionPerformed
    clickPosicao(5, 7);
    // TODO add your handling code here:
}//GEN-LAST:event_linha5coluna7ActionPerformed

private void linha6coluna7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha6coluna7ActionPerformed
    clickPosicao(6, 7);
    // TODO add your handling code here:
}//GEN-LAST:event_linha6coluna7ActionPerformed

private void linha7coluna7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha7coluna7ActionPerformed
    clickPosicao(7, 7);
    // TODO add your handling code here:
}//GEN-LAST:event_linha7coluna7ActionPerformed

private void linha8coluna7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha8coluna7ActionPerformed
    clickPosicao(8, 7);
    // TODO add your handling code here:
}//GEN-LAST:event_linha8coluna7ActionPerformed

private void linha9coluna7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha9coluna7ActionPerformed
    clickPosicao(9, 7);
    // TODO add your handling code here:
}//GEN-LAST:event_linha9coluna7ActionPerformed

private void linha0coluna8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha0coluna8ActionPerformed
    clickPosicao(0, 8);
    // TODO add your handling code here:
}//GEN-LAST:event_linha0coluna8ActionPerformed

private void linha1coluna8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha1coluna8ActionPerformed
    clickPosicao(1, 8);
    // TODO add your handling code here:
}//GEN-LAST:event_linha1coluna8ActionPerformed

private void linha2coluna8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha2coluna8ActionPerformed
    clickPosicao(2, 8);
    // TODO add your handling code here:
}//GEN-LAST:event_linha2coluna8ActionPerformed

private void linha3coluna8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha3coluna8ActionPerformed
    clickPosicao(3, 8);
    // TODO add your handling code here:
}//GEN-LAST:event_linha3coluna8ActionPerformed

private void linha4coluna8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha4coluna8ActionPerformed
    clickPosicao(4, 8);
    // TODO add your handling code here:
}//GEN-LAST:event_linha4coluna8ActionPerformed

private void linha5coluna8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha5coluna8ActionPerformed
    clickPosicao(5, 8);
    // TODO add your handling code here:
}//GEN-LAST:event_linha5coluna8ActionPerformed

private void linha6coluna8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha6coluna8ActionPerformed
    clickPosicao(6, 8);
    // TODO add your handling code here:
}//GEN-LAST:event_linha6coluna8ActionPerformed

private void linha7coluna8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha7coluna8ActionPerformed
    clickPosicao(7, 8);
    // TODO add your handling code here:
}//GEN-LAST:event_linha7coluna8ActionPerformed

private void linha8coluna8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha8coluna8ActionPerformed
    clickPosicao(8, 8);
    // TODO add your handling code here:
}//GEN-LAST:event_linha8coluna8ActionPerformed

private void linha9coluna8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha9coluna8ActionPerformed
    clickPosicao(9, 8);
    // TODO add your handling code here:
}//GEN-LAST:event_linha9coluna8ActionPerformed

private void linha0coluna9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha0coluna9ActionPerformed
    clickPosicao(0, 9);
    // TODO add your handling code here:
}//GEN-LAST:event_linha0coluna9ActionPerformed

private void linha1coluna9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha1coluna9ActionPerformed
    clickPosicao(1, 9);
    // TODO add your handling code here:
}//GEN-LAST:event_linha1coluna9ActionPerformed

private void linha2coluna9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha2coluna9ActionPerformed
    clickPosicao(2, 9);
    // TODO add your handling code here:
}//GEN-LAST:event_linha2coluna9ActionPerformed

private void linha3coluna9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha3coluna9ActionPerformed
    clickPosicao(3, 9);
    // TODO add your handling code here:
}//GEN-LAST:event_linha3coluna9ActionPerformed

private void linha4coluna9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha4coluna9ActionPerformed
    clickPosicao(4, 9);
    // TODO add your handling code here:
}//GEN-LAST:event_linha4coluna9ActionPerformed

private void linha5coluna9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha5coluna9ActionPerformed
    clickPosicao(5, 9);
    // TODO add your handling code here:
}//GEN-LAST:event_linha5coluna9ActionPerformed

private void linha6coluna9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha6coluna9ActionPerformed
    clickPosicao(6, 9);
    // TODO add your handling code here:
}//GEN-LAST:event_linha6coluna9ActionPerformed

private void linha7coluna9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha7coluna9ActionPerformed
    clickPosicao(7, 9);
    // TODO add your handling code here:
}//GEN-LAST:event_linha7coluna9ActionPerformed

private void linha8coluna9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha8coluna9ActionPerformed
    clickPosicao(8, 9);
    // TODO add your handling code here:
}//GEN-LAST:event_linha8coluna9ActionPerformed

private void linha9coluna9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linha9coluna9ActionPerformed
    clickPosicao(9, 9);
    // TODO add your handling code here:
}//GEN-LAST:event_linha9coluna9ActionPerformed

private void botaoIniciarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIniciarPartidaActionPerformed
       clickIniciarPartidaRede();

}//GEN-LAST:event_botaoIniciarPartidaActionPerformed

private void comboArmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboArmasActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_comboArmasActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    int arma = 1 + comboArmas.getSelectedIndex();
    int local = 1 + comboLocais.getSelectedIndex();
    int suspeito = 1 + comboSuspeitos.getSelectedIndex();
    clickPalpite(arma, local, suspeito);
}//GEN-LAST:event_jButton3ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    int arma = 1 + comboArmas.getSelectedIndex();
    int local = 1 + comboLocais.getSelectedIndex();
    int suspeito = 1 + comboSuspeitos.getSelectedIndex();
    
    clickAcusacao(arma, local, suspeito);
}//GEN-LAST:event_jButton4ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem botaoIniciarPartida;
    private javax.swing.JButton botaoJogarDado;
    private javax.swing.JTextArea campoLogDaPartida;
    private javax.swing.JTextField campoPassosRestantes;
    private javax.swing.JComboBox comboArmas;
    private javax.swing.JComboBox comboLocais;
    private javax.swing.JComboBox comboSuspeitos;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCarta1;
    private javax.swing.JLabel labelCarta2;
    private javax.swing.JLabel labelCarta3;
    private javax.swing.JLabel labelCarta4;
    private javax.swing.JLabel labelCarta5;
    private javax.swing.JLabel labelCarta6;
    private javax.swing.JLabel labelCarta7;
    private javax.swing.JLabel labelCarta8;
    private javax.swing.JLabel labelCarta9;
    private javax.swing.JLabel labelNomeJogador;
    private javax.swing.JButton linha0coluna0;
    private javax.swing.JButton linha0coluna1;
    private javax.swing.JButton linha0coluna2;
    private javax.swing.JButton linha0coluna3;
    private javax.swing.JButton linha0coluna4;
    private javax.swing.JButton linha0coluna5;
    private javax.swing.JButton linha0coluna6;
    private javax.swing.JButton linha0coluna7;
    private javax.swing.JButton linha0coluna8;
    private javax.swing.JButton linha0coluna9;
    private javax.swing.JButton linha1coluna0;
    private javax.swing.JButton linha1coluna1;
    private javax.swing.JButton linha1coluna2;
    private javax.swing.JButton linha1coluna3;
    private javax.swing.JButton linha1coluna4;
    private javax.swing.JButton linha1coluna5;
    private javax.swing.JButton linha1coluna6;
    private javax.swing.JButton linha1coluna7;
    private javax.swing.JButton linha1coluna8;
    private javax.swing.JButton linha1coluna9;
    private javax.swing.JButton linha2coluna0;
    private javax.swing.JButton linha2coluna1;
    private javax.swing.JButton linha2coluna2;
    private javax.swing.JButton linha2coluna3;
    private javax.swing.JButton linha2coluna4;
    private javax.swing.JButton linha2coluna5;
    private javax.swing.JButton linha2coluna6;
    private javax.swing.JButton linha2coluna7;
    private javax.swing.JButton linha2coluna8;
    private javax.swing.JButton linha2coluna9;
    private javax.swing.JButton linha3coluna0;
    private javax.swing.JButton linha3coluna1;
    private javax.swing.JButton linha3coluna2;
    private javax.swing.JButton linha3coluna3;
    private javax.swing.JButton linha3coluna4;
    private javax.swing.JButton linha3coluna5;
    private javax.swing.JButton linha3coluna6;
    private javax.swing.JButton linha3coluna7;
    private javax.swing.JButton linha3coluna8;
    private javax.swing.JButton linha3coluna9;
    private javax.swing.JButton linha4coluna0;
    private javax.swing.JButton linha4coluna1;
    private javax.swing.JButton linha4coluna2;
    private javax.swing.JButton linha4coluna3;
    private javax.swing.JButton linha4coluna4;
    private javax.swing.JButton linha4coluna5;
    private javax.swing.JButton linha4coluna6;
    private javax.swing.JButton linha4coluna7;
    private javax.swing.JButton linha4coluna8;
    private javax.swing.JButton linha4coluna9;
    private javax.swing.JButton linha5coluna0;
    private javax.swing.JButton linha5coluna1;
    private javax.swing.JButton linha5coluna2;
    private javax.swing.JButton linha5coluna3;
    private javax.swing.JButton linha5coluna4;
    private javax.swing.JButton linha5coluna5;
    private javax.swing.JButton linha5coluna6;
    private javax.swing.JButton linha5coluna7;
    private javax.swing.JButton linha5coluna8;
    private javax.swing.JButton linha5coluna9;
    private javax.swing.JButton linha6coluna0;
    private javax.swing.JButton linha6coluna1;
    private javax.swing.JButton linha6coluna2;
    private javax.swing.JButton linha6coluna3;
    private javax.swing.JButton linha6coluna4;
    private javax.swing.JButton linha6coluna5;
    private javax.swing.JButton linha6coluna6;
    private javax.swing.JButton linha6coluna7;
    private javax.swing.JButton linha6coluna8;
    private javax.swing.JButton linha6coluna9;
    private javax.swing.JButton linha7coluna0;
    private javax.swing.JButton linha7coluna1;
    private javax.swing.JButton linha7coluna2;
    private javax.swing.JButton linha7coluna3;
    private javax.swing.JButton linha7coluna4;
    private javax.swing.JButton linha7coluna5;
    private javax.swing.JButton linha7coluna6;
    private javax.swing.JButton linha7coluna7;
    private javax.swing.JButton linha7coluna8;
    private javax.swing.JButton linha7coluna9;
    private javax.swing.JButton linha8coluna0;
    private javax.swing.JButton linha8coluna1;
    private javax.swing.JButton linha8coluna2;
    private javax.swing.JButton linha8coluna3;
    private javax.swing.JButton linha8coluna4;
    private javax.swing.JButton linha8coluna5;
    private javax.swing.JButton linha8coluna6;
    private javax.swing.JButton linha8coluna7;
    private javax.swing.JButton linha8coluna8;
    private javax.swing.JButton linha8coluna9;
    private javax.swing.JButton linha9coluna0;
    private javax.swing.JButton linha9coluna1;
    private javax.swing.JButton linha9coluna2;
    private javax.swing.JButton linha9coluna3;
    private javax.swing.JButton linha9coluna4;
    private javax.swing.JButton linha9coluna5;
    private javax.swing.JButton linha9coluna6;
    private javax.swing.JButton linha9coluna7;
    private javax.swing.JButton linha9coluna8;
    private javax.swing.JButton linha9coluna9;
    // End of variables declaration//GEN-END:variables





}
