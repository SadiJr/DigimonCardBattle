
//classe q ira comandar tudo praticamente

public class Tabuleiro {


       private Jogador jogador1;
       private Jogador jogador2;
       private Posicao posicoes[][];
       private boolean temVencedor;
       private boolean podeTiraPecinha;
       private boolean partidaEmAndamento;

       //testar
       private AtorJogador ator;


       public Tabuleiro(AtorJogador pAtor){

              // instancia os jogadores
              jogador1= new Jogador("comJogador1.JPG");//nome1,"comJogador1.JPG",  2, 0); // o 2 eh da linha e o 0 eh da coluna, o jogador 1 sempre vai começar com essas cordenadas
              jogador2= new Jogador("comJogador2.JPG");//nome2,"comJogador2.JPG",  3, 7); // o 3 eh da linha e o 7 eh da coluna, o jogador 2 sempre vai começar com essas cordenadas

              //instancia as posições

              posicoes = new Posicao[6][8];

              for(int i=0; i<6; i++){
                    for(int j=0; j<8; j++){
                        posicoes[i][j] = new Posicao();//true,false); // to falando q tem pecinha e q a posição naum ta ocupada
                   }
	          }

	          // pega a referencia do ator, isso vai ser usado depois para a manipulação das imagens
	          ator= pAtor;

       }

       //metodo q vai verificando c as posições desejadas estaum disponiveis para c ocupar ou c pode tirar a pecinha desejada
       public boolean disponivel(int linha, int coluna){

              //c tiver pecinha e naum tiver jogador ocupando ela, ela podera ser tirada
              return posicoes[linha][coluna].disponivel();

        }

        public void joga(int linha, int coluna){

               if(temVencedor==false){

                  if(podeTiraPecinha == false){
                      alocaJogador(linha,coluna);
                  }

                  else{
                       boolean disponivel = disponivel(linha,coluna);
                     if(disponivel){
                         tiraPecinha(linha,coluna);
                         podeTiraPecinha=false;
                         temVencedor = verificaVencedor();
                         if(temVencedor){
                            Jogador vencedor = informaVencedor();
                            ator.exibeMsgObs(vencedor.getNome()+" venceuuuu =), c quiser começa denovo clique em reiniciar, ou em novo");
                            finalizaPartida();
                          }
                          else
                             passaVez();
                      }
                      else{
                           ator.exibeMsgObs("escolha outra pecinha para tirar");
                      }
                  }

               }

               else{
                   ator.exibeMsgObs("Jogo ja terminou, clique em NOVO ou REINICIAR");
                }

        }

        public void tiraPecinha(int linha, int coluna){

               //seto a pecinha como false para tirar ela
               posicoes[linha][coluna].tiraPecinha();
               //tira pecinha da interface grafica
               ator.tiraPecinha(linha,coluna);

        }


        //aloca o jogador na nova posicao
        public void alocaJogador(int linha, int coluna){

               boolean pode = podeAlocar(linha,coluna);
               if(pode){
                    podeTiraPecinha=true;
                    partidaEmAndamento = true;
                    //Jogador jogadorAux;
                    int linhaJogador;
                    int colunaJogador;
                    String imagem;

                    if(jogador1.informaDaVez()){
                        linhaJogador = jogador1.getLinha();
                        colunaJogador = jogador1.getColuna();
                        imagem = jogador1.getImagem();
                        jogador1.setLinha(linha);
                        jogador1.setColuna(coluna);
                    }
                    else{
                         linhaJogador = jogador2.getLinha();
                         colunaJogador = jogador2.getColuna();
                         imagem = jogador2.getImagem();
                         jogador2.setLinha(linha);
                         jogador2.setColuna(coluna);
                    }

                    posicoes[linhaJogador][colunaJogador].desocupa();
                    //desaloca jogador da interface grafica
                    ator.tiraJogador(linhaJogador,colunaJogador);
                    posicoes[linha][coluna].ocupa();
                    //aloca jogador na interface grafica
                    ator.alocaJogador(linha,coluna,imagem);
                    ator.exibeMsgObs("escolha uma peça para remover agora");
                }

        }

        //verifica c o trajeto q ele ta pretendendo fazer eh valido
        public int verificaTrajeto(int linha,int coluna){

               int linhaJogador;
               int colunaJogador;

               if(jogador1.informaDaVez()){
                 linhaJogador = jogador1.getLinha();
                 colunaJogador = jogador1.getColuna();
              }
              else{
                 linhaJogador = jogador2.getLinha();
                 colunaJogador = jogador2.getColuna();
              }


               if((linhaJogador==linha) && (colunaJogador!=coluna))
                    return 0;

                else if((colunaJogador==coluna) && (linhaJogador!=linha))
                    return 1;

                else if((linhaJogador+colunaJogador) == (linha+coluna))
                    return 2;

                else if((linhaJogador-colunaJogador) == (linha-coluna))
                    return 3;

                else
                    return 4;
        }


        //arruma esse metodo, naum ta verificando tudo q tem q percorre
        public boolean possoPercorreTrajeto(int linha,int coluna, int comand){

               int linhaJogador;
               int colunaJogador;

               if(jogador1.informaDaVez()){
                 linhaJogador = jogador1.getLinha();
                 colunaJogador = jogador1.getColuna();
              }
              else{
                 linhaJogador = jogador2.getLinha();
                 colunaJogador = jogador2.getColuna();
              }


               int aux; //vai ajudar a percorre o trajeto
               boolean posso = true; // vai dizer c pode ocupar ou naum

               if(comand==0){

                    aux = colunaJogador;

                    if(aux>coluna){
                            coluna++;
                            while(coluna<aux && posso==true){
                                posso = disponivel(linha,coluna);
                                coluna++;
                            }
                    }
                    else{
                        coluna--;
                        while(aux<coluna && posso==true){
                            posso = disponivel(linha,coluna);
                            coluna--;
                        }
                    }
                    return posso;
                }

               else if(comand==1){
                    aux = linhaJogador;
                    if(aux>linha ){
                            linha++;
                            while(linha<aux && posso==true){
                                posso = disponivel(linha,coluna);
                                linha++;
                            }
                    }
                    else{
                        linha--;
                        while(aux<linha && posso==true){
                            posso = disponivel(linha,coluna);
                            linha--;
                        }
                    }
                    return posso;
                }

                else if(comand==2){

                    aux = linhaJogador;
                    if(aux>linha ){
                            linha++;
                            coluna--;
                            while(linha<aux && posso==true){
                                posso = disponivel(linha,coluna);
                                linha++;
                                coluna--;
                            }
                    }
                    else{
                        linha--;
                        coluna++;
                        while(aux<linha && posso==true){
                            posso = disponivel(linha,coluna);
                            linha--;
                            coluna++;
                        }
                    }
                    return posso;

                }

                else if(comand==3){

                    aux = linhaJogador;
                    if(aux>linha ){
                            linha++;
                            coluna++;
                            while(linha<aux && posso==true){
                                posso = disponivel(linha,coluna);
                                linha++;
                                coluna++;
                            }
                    }
                    else{
                        linha--;
                        coluna--;
                        while(aux<linha && posso==true){
                            posso = disponivel(linha,coluna);
                            linha--;
                            coluna--;
                        }
                    }
                    return posso;

                }

                // soh para naum dar erro de compilação, q isso naum vai acontecer
                else
                 return false;

        }


        public boolean podeAlocar(int linha, int coluna){

               boolean disponivel = disponivel(linha,coluna);

               //verifica se a posição eh valida
               if(disponivel){

                    int verifica= verificaTrajeto(linha,coluna);

                    if(verifica==4){
                       ator.exibeMsgObs("trajeto invalido, escolha outra posição");
                       //System.out.println("trajeto invalido, escolha outra posição");
                       return false;
                    }

                    else{
                         boolean possoPercorre = possoPercorreTrajeto(linha,coluna,verifica);
                         if(possoPercorre){
                            return true;
                        }
                         else{
                             ator.exibeMsgObs("naum posso percorre trajeto, escolha outra posição");
                             //System.out.println("naum posso percorre trajeto, escolha outra posição");
                             return false;
                         }
                    }

               }

               else{
                    ator.exibeMsgObs("a posição desejada naum esta disponivel, escolha outra posição");
                   //System.out.println("a posição desejada naum esta disponivel, escolha outra posição");
                   return false;
               }

        }

        //metodo usado para passar a vez do jogador
        public void passaVez(){

               if(jogador1.informaDaVez()){
                    jogador1.desabilitar();
                    jogador2.habilitar();
                    ator.exigeMsgJogador(jogador2.getNome());
               }
               else{
                    jogador2.desabilitar();
                    jogador1.habilitar();
                    ator.exigeMsgJogador(jogador1.getNome());
               }

               ator.exibeMsgObs("escolha uma posição para c alocar");

        }

        public void defineSequencia(boolean daVez1){

	          if(daVez1==true){
	                jogador1.habilitar();
	                ator.exigeMsgJogador(jogador1.getNome());
	          }
	          else{
	                jogador2.habilitar();
	                ator.exigeMsgJogador(jogador2.getNome());
	          }

              ator.exibeMsgObs(" começou jogo");

        }


        // vai ser usado qdo o jogador, queh um novo jogo, soh q entretanto ele ja tem um sendo jogado
        public void organiza(String nome1, String nome2,boolean daVez1){

               temVencedor = false;
               podeTiraPecinha = false;
               partidaEmAndamento = false;
               jogador1.setNome(nome1);
               jogador2.setNome(nome2);

               //bota jogadores para as posições iniciais
               //player 1
               jogador1.setLinha(2);
               jogador1.setColuna(0);
               //player 2
               jogador2.setLinha(3);
               jogador2.setColuna(7);

               // setando as posições para o estado inicial agora

               for(int i=0; i<6; i++){
                    for(int j=0; j<8; j++){

                        //estas cordenadas saum as iniciais dos jogadores, por isso recebem um tratamento diferente
	                    if((i==2 && j==0)||(i==3 && j==7)){
                            posicoes[i][j].setPecinha(false); //to falando q naum tem pecinha e q a posição ta ocupada
                            posicoes[i][j].setOcupada(true);
                        }
                        //todas as outras posições
                        else{
                            posicoes[i][j].setPecinha(true); //to falando q naum tem pecinha e q a posição ta ocupada
                            posicoes[i][j].setOcupada(false);
                        }
                    }
	          }

              defineSequencia(daVez1);


        }

        public boolean verificaVencedor(){

              boolean venceu = false;
              int linhaAux ;
              int colunaAux;
              boolean vez1 = jogador1.informaDaVez();

              if(vez1){
                 linhaAux = jogador2.getLinha();
                 colunaAux = jogador2.getColuna();
              }
              else{
                 linhaAux = jogador1.getLinha();
                 colunaAux = jogador1.getColuna();
              }

              boolean pode = podeSeMovimentar(linhaAux,colunaAux);

              if(pode==false){
                venceu = true;
                partidaEmAndamento = false;
                 if(vez1)
                    jogador1.tornaVencedor();
                else
                    jogador2.tornaVencedor();
              }

              return venceu;

        }

        public boolean podeSeMovimentar(int linhaAux, int colunaAux){

               boolean pode = false;

               if(linhaAux==0 && colunaAux==0){
                        if ( disponivel(linhaAux+1,colunaAux) || disponivel(linhaAux,colunaAux+1) || disponivel(linhaAux+1,colunaAux+1))
                             pode=true;
              }

              else if(linhaAux==5 && colunaAux==0){
                        if (disponivel(linhaAux-1,colunaAux)|| disponivel(linhaAux-1,colunaAux+1) || disponivel(linhaAux,colunaAux+1))
                             pode=true;
              }

              else if(linhaAux==5 && colunaAux==7){
                        if (disponivel(linhaAux-1,colunaAux-1)|| disponivel(linhaAux,colunaAux-1) || disponivel(linhaAux-1,colunaAux))
                             pode=true;
              }

              else if(linhaAux==0 && colunaAux==7){
                        if (disponivel(linhaAux,colunaAux-1)|| disponivel(linhaAux+1,colunaAux-1) || disponivel(linhaAux+1,colunaAux))
                             pode=true;
              }

              else if(linhaAux==0){
                        if ( disponivel(linhaAux,colunaAux+1) || disponivel(linhaAux+1,colunaAux+1) || disponivel(linhaAux+1,colunaAux) || disponivel(linhaAux+1,colunaAux-1) || disponivel(linhaAux,colunaAux-1))
                             pode=true;
              }

              else if(linhaAux==5){
                        if (disponivel(linhaAux-1,colunaAux)|| disponivel(linhaAux-1,colunaAux+1) || disponivel(linhaAux,colunaAux+1) || disponivel(linhaAux,colunaAux-1) || disponivel(linhaAux-1,colunaAux-1))
                             pode=true;
              }

              else if(colunaAux==0){
                        if ( disponivel(linhaAux+1,colunaAux) || disponivel(linhaAux,colunaAux+1) || disponivel(linhaAux+1,colunaAux+1) || disponivel(linhaAux-1,colunaAux) || disponivel(linhaAux-1,colunaAux+1))
                             pode=true;
              }

              else if(colunaAux==7){
                        if (disponivel(linhaAux-1,colunaAux-1)|| disponivel(linhaAux,colunaAux-1) || disponivel(linhaAux-1,colunaAux) || disponivel(linhaAux+1,colunaAux-1) || disponivel(linhaAux+1,colunaAux))
                             pode=true;
              }

              else {
                   if (disponivel(linhaAux-1,colunaAux-1) || disponivel(linhaAux,colunaAux-1) || disponivel(linhaAux+1,colunaAux-1) || disponivel(linhaAux+1,colunaAux) || disponivel(linhaAux+1,colunaAux+1) || disponivel(linhaAux,colunaAux+1) || disponivel(linhaAux-1,colunaAux+1) || disponivel(linhaAux-1,colunaAux) )
                             pode=true;
              }

              return pode;
        }

        public void finalizaPartida(){

               jogador1.desabilitar();
               jogador1.resetarVencedor();
               jogador2.desabilitar();
               jogador2.resetarVencedor();
               partidaEmAndamento=false;
        }

        public boolean temPartidaAndamento(){

               return partidaEmAndamento;

       }

       public Jogador informaVencedor() {

        if (jogador1.informaVencedor())
			return jogador1;
		 else
			return jogador2;

	   }


}