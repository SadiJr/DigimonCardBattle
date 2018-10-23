/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominioProblema;

import interfaceGrafica.AtorJogador;
import java.util.Random;

/**
 *
 * @author Rodrigo
 */
public class Tabuleiro {
    
    AtorJogador atorJogador;
    Baralho baralho;
    Carta[] solucao;
    String logDaPartida;
    Jogador eu;
    Jogador oponente;
    Posicao[][] posicoes = new Posicao[10][10];


    /*quando se recebe a informação de que a partida foi iniciada na rede
     * os dois jogadores instanciam seus tabuleiros com os dados que são
     * iguais sempre
     */
    public Tabuleiro(AtorJogador atorJogador){
        //tabuleiro precisa ter uma referencia de atorJogador para enviar mensagem pra interface
        this.atorJogador = atorJogador;
        baralho = new Baralho();
        logDaPartida = "";
        criaPosicoes();

    }



    public Jogador getEu() {
        return eu;
    }

    public void setEu(Jogador eu) {
        this.eu = eu;
    }

    public Jogador getOponente() {
        return oponente;
    }

    public void setOponente(Jogador oponente) {
        this.oponente = oponente;
    }

    public Carta[] getSolucao() {
        return solucao;
    }

    public void setSolucao(Carta[] solucao) {
        this.solucao = solucao;
    }

    public void criaPosicoes(){

        //cria as posições do tabuleiro
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++){
                posicoes[i][j] = new Posicao();
            }
        }

        //seta os comodos nas suas respectivas posicoes
        posicoes[0][0].setComodo(Carta.NPD);
        posicoes[0][5].setComodo(Carta.REITORIA);
        posicoes[1][9].setComodo(Carta.BU);
        posicoes[4][0].setComodo(Carta.EPS);
        posicoes[9][0].setComodo(Carta.CTC);
        posicoes[9][5].setComodo(Carta.INE);
        posicoes[6][9].setComodo(Carta.CCS);

    }

    //retorna um array de strings com o nome das cartas que o jogador possui
    //
    public void retornaCartasInterface() {
        Carta[] cartas = eu.getCartas();
        String[] nomeCartas = new String[9];
        for(int i = 0; i < 9; i++) {
            nomeCartas[i] = cartas[i].retornaArquivo();
        }
        atorJogador.atualizaInterface(nomeCartas);

    }

    public void recebeJogadaSimples(String logDaPartida, int[] posicao) {

        int[] posicaoAntiga = getPosicaoJogador(oponente);

        this.desalocarPeaoPosicao(posicaoAntiga[0], posicaoAntiga[1]);

        this.alocarPeaoPosicao(oponente, posicao[0], posicao[1]);


        this.logDaPartida = logDaPartida;
        eu.setNumeroPassos(0);
        eu.setDaVez(true);
        atorJogador.atualizaInterface(oponente.getPeao(), posicaoAntiga, posicao, logDaPartida);

    }

    /* Quando não foi você que iniciou a partida na rede, o outro jogador
     * instancia os objetos referentes aos jogadores com suas respectivas cartas
     * e os envia juntamente com o array contendo as 3 cartas referentes
     * a solucao do crime da partida atual
     */
     public void recebeInicio(Carta[] solucao, Jogador eu, Jogador oponente) {
        this.setSolucao(solucao);
        this.setEu(eu);
        this.setOponente(oponente);
        this.retornaCartasInterface();
        posicoes[4][4].setOcupante(oponente);
        posicoes[4][5].setOcupante(eu);
        
    }



   
     //instancia o jogador "oponente" com o seu nome, e o inteiro referente
    // a cor do seu peão
    public void instanciarOponente(String nomeOponente, int peao) {

        oponente = new Jogador(nomeOponente, peao);
        posicoes[4][5].setOcupante(oponente);
        
    }

    //instancia o jogador "eu" com o seu nome, e o inteiro referente
    // a cor do seu peão, esse método só é chamado quando você teve
    // a iniciativa de iniciar a partida, portanto já seta o jogador
    // como sendo a vez dele.
    public void instanciarJogador(String nomeJogador, int peao) {

        eu = new Jogador(nomeJogador, peao);
        posicoes[4][4].setOcupante(eu);
        eu.setDaVez(true);
        
    }



   
    

    /* Retorna um array de inteiros contendo na posicao 0
     * a coordenada x e na posicao 1 a coordenada y
     * do jogador requisitado
     */
    public int[] getPosicaoJogador(Jogador jogador) {
        Jogador ocupante;
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                ocupante = posicoes[i][j].getOcupante();
                if(ocupante == jogador)
                    return new int[]{i, j};
            }

        }

        return null;

    }

    //adiciona uma nova linha ao log da partida com o string enviado
    public void atualizarLog(String novaLinha) {
        logDaPartida += novaLinha + "\n";

    }

    //coloca o jogador enviado como ocupante da posição enviada
    public void alocarPeaoPosicao(Jogador jogador, int x, int y){

        posicoes[x][y].setOcupante(jogador);

    }

    //retira o ocupante da posição enviada como parametro
     public void desalocarPeaoPosicao(int x, int y){

         posicoes[x][y].setOcupante(null);

     }

     //cria as cartas correspondentes aos parametros
     //coloca num array de cartas na mesma ordem dos parametros
     public Carta[] criaCartasCorrespondentes(int arma, int local, int suspeito){

         return new Carta[]{new Carta(Carta.ARMA, arma), new Carta(Carta.LOCAL, local), new Carta(Carta.ASSASSINO, suspeito)};


     }

     //recebe as cartas do palpite e procura na mão do oponente,
     //retorna a primeira que foi encontrada
     //caso nao encontre nenhuma retorna null
     public Carta procurarCartaOponente(Carta[] palpite) {

         //embaralha essas cartas, afim de randomizar a ordem da busca na mao do oponente
         //simulando a escolha do oponente de que carta mostrar, caso ele tenha mais de uma
         palpite = Baralho.embaralhar(palpite.clone());
         
         Carta[] cartasO = oponente.getCartas();
         
         //para cada carta do palpite
         for(Carta atual: palpite){
             
             //varre a mao do oponente e ve se ele tem a carta,
             //retornando-a se positivo
             for(int i = 0; i < 9; i++) {
             if(cartasO[i].equals(atual))
                 return atual;
             }
             
           
         }
         //chegou aqui sem ter retornado, é porque não tem a carta
         return null;

     }


        

    /* Quando o jogador local tem a iniciativa de iniciar a partida
     * (clicando no botao "iniciar partida rede") ele que instancia
     * os jogadores e distribui as cartas, posteriormente enviando
     * para o outro jogador pela rede
     */
    public void iniciarPartida(String nomeJogador, String nomeOponente) {

        this.instanciarJogador(nomeJogador, 1);
        this.instanciarOponente(nomeOponente, 2);
        baralho.embaralhar();
        solucao = baralho.retornaSolucao();
        eu.setCartas(baralho.retornaCartasJogador());
        oponente.setCartas(baralho.retornaCartasJogador());

        this.retornaCartasInterface();
        atorJogador.enviaJogadaInicial(solucao, oponente, eu);


    }

    //faz o palpite correspondente aos parametros recebidos e atualiza o log da partida
    //com as informações referentes.
    public String palpite(int arma, int local, int suspeito) {
        
        boolean ehDaVez = eu.isDaVez();

        if(!ehDaVez){
            return "Não é a sua vez";
        }

        int[] posicao = this.getPosicaoJogador(eu);

        int comodoDaPosicao = posicoes[posicao[0]][posicao[1]].getComodo();

        //verifica se o jogador esta no local correto (comodo correspondente ao local do palpite que fez)
        if(comodoDaPosicao != local){
            return "Você precisa estar no local correspondente para fazer esse palpite";
        }

        //cria as cartas correspondentes
        //coloca num array de cartas na mesma ordem dos parametros
        Carta[] palpite = this.criaCartasCorrespondentes(arma, local, suspeito);

         
        //busca as cartas na mao do oponente na ordem em que estao no array
        Carta encontrada = this.procurarCartaOponente(palpite);

        //pega os nomes dos jogadores para poder atualizar o log da partida
        String nomeJogador = eu.getNome();

        String nomeOponente = oponente.getNome();
        
        
        //verifica se a carta foi encontrada e atualiza o log com a mensagem correspondente
        if(encontrada != null){
            this.atualizarLog("Jogador " + nomeJogador + " fez um palpite: " + palpite[2] + " na(o) " + palpite[1] + " com o(a) " + palpite[0] + ", viu a carta " + encontrada + " do Jogador " + nomeOponente );
        }
        else {
            this.atualizarLog("Jogador " + nomeJogador + " fez um palpite: " + palpite[2] + " na(o) " + palpite[1] + " com o(a) " + palpite[0] + ", mas o Jogador " + nomeOponente + " nao possui nenhuma dessas cartas" );
        }

        //passa a vez do jogador, visto q ele nao pode fazer mais nada no momento
        this.passarAvez(1);

        return null;
    }


    //recebe os inteiros correspondentes a arma, local e suspeito
    public String acusacao(int arma, int local, int suspeito) {
        
        boolean ehDaVez = eu.isDaVez();

        if(!eu.isDaVez()){
            return "Não é a sua vez";
        }

        //cria as cartas correspondentes aos parametros recebidos
        Carta[] acusacao = this.criaCartasCorrespondentes(arma, local, suspeito);

        String nomeJogador = eu.getNome();

        //compara as cartas criadas com a solução e seta o jogador como vencedor caso esteja correta
        if(acusacao[0].equals(solucao[0]) && acusacao[1].equals(solucao[1]) && acusacao[2].equals(solucao[2])){
            eu.setVencedor(true);
            this.atualizarLog("Jogador " + eu.getNome() + " fez a acusação: " + acusacao[2] + " na(o) " + acusacao[1] + " com o(a) " + acusacao[0] + ", está CORRETO!");
        }
        else{
            this.atualizarLog("Jogador " + eu.getNome() + " fez a acusação: " + acusacao[2] + " na(o) " + acusacao[1] + " com o(a) " + acusacao[0] + ", está ERRADO!");
        }

        //passa a vez com argumento diferente de 1, sigificando jogada final e encerramento da partida
        this.passarAvez(2);

        return null;
    }

    /*Recebe como parametro a intencao do chamador
     * se intencao é igual a 1, o chamado foi originado por vontade do jogador (Click em passar a vez)
     * ou por um palpite. Nesse caso será enviada uma JogadaSimples (ver referencia na classe JogadaSimples) pela rede.
     * se a intenção é igual 2, significa que foi originada de uma acusação, onde será enviada uma JogadaFinal
     * (ver referencia na classe) e depois será encerrada a partida
     *
     */
    public String passarAvez(int intencao){

        boolean ehDaVez = eu.isDaVez();

        if(!ehDaVez){
                atorJogador.mostraMensagem("Nao é a sua vez");
                return "";
        }

        //nao é mais a vez do jogador
        eu.setDaVez(false);


        int[] posicao = getPosicaoJogador(eu);

        String nomeEu = eu.getNome();
        String nomeOponente = oponente.getNome();

        
        //verifica a intencao e toma a acao correspondente
        if(intencao == 1){
            this.atualizarLog("Passou a vez do jogador " + nomeEu + ", " + nomeOponente + " pode jogar");
            atorJogador.enviaJogadaSimples(posicao, logDaPartida);
        }
        else{
            this.atualizarLog("A partida está encerrada");
            atorJogador.enviaJogadaFinal(logDaPartida, !eu.isVencedor());
        }

        return null;
    }

    /*recebe a posição desejada, verifica se ela é viável, se o jogador possui passos suficientes
     * se tudo der certo, atualiza a posição do jogador, a interface, o log da partida e retorna null
     * caso exista alguma irregularidade retorna null
     */
    public String andarComPeao(int linha, int coluna) {

            boolean ehDaVez = eu.isDaVez();
            
            if(!ehDaVez){
                return "Nao é a sua vez";
            }
                

            int passos = eu.getNumeroPassos();
       

            int[] posicao = this.getPosicaoJogador(eu);

            //verifica se esta na mesma linha ou na mesma coluna
            // ou seja, se não está na diagonal
            if(posicao[0] != linha && posicao[1] != coluna){
                
                return "Voce nao pode andar na diagonal";

            }

            int passosRestantes = verificaDiminuiPassos(posicao, linha, coluna, passos);


            if(passosRestantes == -1){
                return "Voce nao possui passos suficientes";
            }




            Jogador ocupantePosicao = posicoes[linha][coluna].getOcupante();

            //verifica se a posição já está ocupada
            if(ocupantePosicao != null) {
                return "A posicao ja esta ocupada";
            }

                  

            this.desalocarPeaoPosicao(posicao[0], posicao[1]);

            this.alocarPeaoPosicao(eu, linha, coluna);

            eu.setNumeroPassos(passosRestantes);
            
            this.atualizarLog("Jogador " + eu.getNome() + " andou pelo tabuleiro");
            
            atorJogador.atualizaInterface(eu.getPeao(), posicao, new int[]{linha, coluna}, passosRestantes, logDaPartida);

            return null;


    }

    /*sorteia um numero de passos de 1 a 6 e atualiza os dados
     * do jogador e a interface gráfica
     * retorna null se tudo der certo, caso exista alguma
     * irregularidade, retorna um string com a mensagem
     * correspondente
     */
    public String jogarDado() {

        boolean ehDaVez = eu.isDaVez();

        if(!ehDaVez){
            return "Nao é a sua vez";
        }

        Random sorteador = new Random();
        int numeroSorteado = sorteador.nextInt(6) + 1;

        eu.setNumeroPassos(numeroSorteado);

        String nomeJogador = eu.getNome();

        this.atualizarLog("Jogador " + nomeJogador + " sorteou " + numeroSorteado + " no dado");

        atorJogador.atualizaInterface(numeroSorteado, logDaPartida);

        return null;


    }

    //devolve a quantidade de passos restantes do jogador, ou -1 caso ele nao tenha
    // passos suficientes para realizar o movimento desejado
    private int verificaDiminuiPassos(int[] posicao, int linha, int coluna, int passos) {

        //verifica se tem passos suficientes na mesma linha
          int passosRestantes = -1;

          if(posicao[0] == linha) {

                if(passos < Math.abs(coluna - posicao[1]))
                    return -1;
                else
                    passosRestantes = passos - Math.abs(coluna - posicao[1]);

           }

            //verifica se tem passos suficientes na mesma coluna
            if(posicao[1] == coluna) {

                if(passos < Math.abs(linha - posicao[0]))
                    return -1;
                else
                    passosRestantes = passos - Math.abs(linha - posicao[0]);
            }

            return passosRestantes;
    }



    

}
