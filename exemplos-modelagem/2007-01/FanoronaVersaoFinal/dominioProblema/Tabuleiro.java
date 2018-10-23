package dominioProblema;

public class Tabuleiro {
	
	public Tabuleiro(){
		iniciar();
		
	}
	protected Posicao posicoes[][] = new Posicao[5][9];
	protected Jogador jogador1;
	protected Jogador jogador2;
	protected Peca    peca1;
	protected Peca    peca2;
	protected boolean partidaemAndamento;

	 
	
	public void esvaziarTabuleiro() {
		for (int linha=1; linha<6; linha++){
			for (int coluna=1; coluna<10; coluna++){
				posicoes[(linha-1)][(coluna-1)].esvaziarPosicao();				
			}
		}
	}
	
	public void iniciar() {
		for (int linha=1; linha<6; linha++){
			for (int coluna=1; coluna<10; coluna++){
				posicoes[(linha-1)][(coluna-1)] = new Posicao();
				posicoes[(linha-1)][(coluna-1)].esvaziarPosicao();				
			}
		}
	    peca1 = new Peca(1);
	    peca2 = new Peca(2);
	    partidaemAndamento = false;
		
	}
	
	public boolean getPartidaemAndamento() {
		return partidaemAndamento;
	}
	
	public void setPartidaemAndamento(boolean valor) {
		partidaemAndamento = valor;
	}
	
	public Jogador getVencedor() {
		return null;
	}
	 
	public void criarJogador(String pNome, boolean pPeca) {
	
		Peca pPecaJogador;
		
		if (pPeca) {
			pPecaJogador = peca1;
		}
		else {
			pPecaJogador = peca2;
		}
	    
		if (pPeca) {
			jogador1 = new Jogador();
			jogador1.iniciar(pNome, pPecaJogador);
		} else {
			jogador2 = new Jogador();
			jogador2.iniciar(pNome, pPecaJogador);	
		}
	}	
	 	
	public void reorganizarPecas(){
		
		Peca peca;
		peca = jogador1.getPeca();
		if (peca.getPeca() == 1) {	
    		// Iniciar 1a. e 2a. linha com o Jogador1
	    	for (int linha=1; linha<3; linha++){
		    	for (int coluna=1; coluna<10; coluna++){
				posicoes[(linha-1)][(coluna-1)].setOcupante(peca1);			
			    }
	    	}
	    	
            // Iniciar 4a. e 5a. linha com o Jogador 2
	    	for (int linha=4; linha<6; linha++){
		    	for (int coluna=1; coluna<10; coluna++){
				posicoes[(linha-1)][(coluna-1)].setOcupante(peca2);			
			    }
	    	}
	    	
	    	// Iniciar linha 3
	    	int linha=3;
	    	for (int coluna=1; coluna<10; coluna++){
	    		// Se coluna impar e menor que 5 jogador 1
	    		if ((coluna%2 != 0 ) && (coluna < 5)) {
				   posicoes[(linha-1)][(coluna-1)].setOcupante(peca1);
				}
				else{
				    if ((coluna%2 == 0 ) && (coluna > 5)) {
					   posicoes[(linha-1)][(coluna-1)].setOcupante(peca1);
				    }
				    else {
				    	if (coluna !=5) {
					       posicoes[(linha-1)][(coluna-1)].setOcupante(peca2); }
				    }
			    }
	    	}		
		} // Se Jogador2 tem a peça 1
		else {
    		// Iniciar 1a. e 2a. linha com o Jogador2
	    	for (int linha=1; linha<3; linha++){
		    	for (int coluna=1; coluna<10; coluna++){
				posicoes[(linha-1)][(coluna-1)].setOcupante(peca2);			
			    }
	    	}
	    	
            // Iniciar 4a. e 5a. linha com o Jogador 1
	    	for (int linha=4; linha<6; linha++){
		    	for (int coluna=1; coluna<10; coluna++){
				posicoes[(linha-1)][(coluna-1)].setOcupante(peca1);			
			    }
	    	}
			
            // Iniciar linha 3
	    	int linha=3;
	    	for (int coluna=1; coluna<10; coluna++){
	    		// Se coluna impar e menor que 5 jogador 1
	    		if ((coluna%2 != 0 ) && (coluna < 5)) {
				   posicoes[(linha-1)][(coluna-1)].setOcupante(peca2);
				}
				else{
				    if ((coluna%2 == 0 ) && (coluna > 5)) {
					   posicoes[(linha-1)][(coluna-1)].setOcupante(peca2);
				    }
				    else {
				    	if (coluna !=5) {
					   posicoes[(linha-1)][(coluna-1)].setOcupante(peca1);}
				    }
			    }
	    	}		
		}
	}
	
	public void definirOPrimeiro(int primeiro) {
		// ImagemDeTabuleiro estado;
		// Lance j2Lance;
		// this.esvaziar();
		// partidaEmAndamento = true;
		// estado = this.informarEstado();
		// jogador1.reiniciar();
		// jogador2.reiniciar();
		if (primeiro == 1) {
			// jogador1.habilitar(estado);
			jogador1.habilitar();
		} else {
			// j2Lance = jogador2.habilitar(estado);
			// if (j2Lance.informarLinha() != 0) {
			// 	this.tratarPosicao((j2Lance.informarLinha()), (j2Lance.informarColuna()));
			jogador2.habilitar();
		}			
	}
	
	public boolean validarMovimento(int linIni, int colIni, int linFim, int colFim){
		
		if (((linIni == linFim) && ((colFim == colIni+1) || (colFim == colIni-1))) ||
		   ((colIni == colFim) && ((linFim == linIni+1) || (linFim == linIni-1)))) {
			
		   return true;	
		}
		else {
          // Para as peças onde a linIni e colIni são páres ou ímpares
		  if ((linIni %2 == 0 && colIni%2 == 0) || (linIni %2 != 0 && colIni%2 != 0)) {
			  if ((linFim == linIni+1 || linFim == linIni-1) &&
				  (colFim == colIni+1 || colFim == colIni-1)) {

				  return true;
			  }
			  else {
				  return false;
			  }
		  }
		  else{
			  return false; 
		  }			
		}
	}
	
	public Posicao getPosicao(int linha, int coluna) {
		return (posicoes[(linha-1)][(coluna-1)]);
	}
	
	public int validarJogada(int linIni, int colIni, int linFim, int colFim) {
       
		Posicao posicaoIniSelecionada;
		Posicao posicaoFimSelecionada;
        
	    boolean posicaoOcupada;
	    		
	    posicaoFimSelecionada = this.getPosicao(linFim, colFim);
		posicaoOcupada = posicaoFimSelecionada.ocupada();
		
		if (posicaoOcupada) {
			return 1; // Retorno de Posição Ocupada		
		} else {
			// Verificar se jogada é do jogador da vez
			posicaoIniSelecionada = this.getPosicao(linIni, colIni);
			Jogador jogador;
			if (jogador1.getAtual()) {
				jogador = jogador1;
			} else {
				jogador = jogador2;
			}	
			
			if (jogador.getPeca() == posicaoIniSelecionada.getOcupante()) {
  			    // verificar se posição de jogada é válida
				if (this.validarMovimento(linIni, colIni, linFim, colFim)) {
		           
					int tipoCaptura = this.testaCaptura(linIni, colIni, linFim, colFim);
		            if (tipoCaptura == 3) {
		        	   return 4; // Escolher o tipo de Captura   
		            } else {
				      // realizarJogada(linIni, colIni, linFim, colFim, tipoCaptura);	
				      return 0; // Retorno de Posição Ok
		            } 
				} else {
					return 3; // Posição Inválida (Não adjacente)
				}	   
			} else {
				return 2; // Jogador não é o atual
		    }
        }
    }

	
	public void realizarJogada(int linIni, int colIni, int linFim, int colFim, int tipoCaptura){
		
		int vtipoCaptura = tipoCaptura;
		
		if (vtipoCaptura == 0) {
			vtipoCaptura = this.testaCaptura(linIni, colIni, linFim, colFim);
		}
		Posicao posicaoIniSelecionada;
		Posicao posicaoFimSelecionada;
        
		posicaoIniSelecionada = this.getPosicao(linIni, colIni);
		posicaoFimSelecionada = this.getPosicao(linFim, colFim);
		
		// preencher a posição final com o jogador da vez
		posicaoFimSelecionada.setOcupante(posicaoIniSelecionada.getOcupante());
		// limpar posicao inicial
		posicaoIniSelecionada.esvaziarPosicao();
		
		int direcaoJogada = retornaDirecaoJogada(linIni,colIni,linFim,colFim);
		Peca peca = posicaoFimSelecionada.getOcupante();
		
		if (vtipoCaptura == 1) {// Aproximacao
		    capturarPecas(linFim,colFim,direcaoJogada,peca);		
		}
		else if (vtipoCaptura ==2) { // Afastamento
			   int direcaoOposta = retornaDirecaoOposta(direcaoJogada);	
			   capturarPecas(linIni,colIni,direcaoOposta,peca);
			}
	this.trocarVezJogador();
		
	}
	
	public void trocarVezJogador() {
		if (jogador1.getAtual()) {
			jogador1.incrementaNumVezesQueJogou();
			jogador1.desabilitar();
			jogador2.habilitar();			
		} else {
			jogador2.incrementaNumVezesQueJogou();
			jogador2.desabilitar();
			jogador1.habilitar();
		}
	}
	
	public int retornaDirecaoJogada(int linIni, int colIni, int linFim, int colFim){
		
		if (colIni == colFim && linFim == linIni - 1)
		   return 1; // Norte: mantem a coluna e diminui a linha
		else if (colIni == colFim && linFim == linIni +1)
		   return 2; // Sul: mantem a coluna e aumenta a linha
		else if (linIni == linFim && colFim == colIni +1)
		   return 3; // Leste: mantem a linha e aumenta a coluna
		else if (linIni == linFim && colFim == colIni - 1) 
		   return 4; // Oeste: mantem a linha e diminui a coluna
		else if (linIni-1 == linFim && colIni+1 == colFim)
		   return 5; // Nordeste: diminui a linha e aumenta a coluna
		else if (linIni-1 == linFim && colIni-1 == colFim) 
		   return 6; // Noroeste:diminui linha e diminui coluna
		else if (linIni+1 == linFim && colIni+1 == colFim) 
		   return 7; // Sudeste:aumenta linha e aumenta coluna
		else if (linIni+1 == linFim && colIni-1 == colFim) 
		   return 8; // Sudoeste: aumenta linha e diminui coluna
		else   
		return 0;
	}
	
	public int retornaDirecaoOposta(int pDirecao) {
		switch (pDirecao) {
		case 1: // Norte
			return 2;
		case 2: // Sul
			return 1;
		case 3: // Leste
			return 4;
		case 4: // Oeste
			return 3;
		case 5: // Nordeste
			return 8;
		case 6: // Noroeste
			return 7;
		case 7: // Sudeste
			return 6;
		case 8: // Sudoeste
			return 5;
		default:
		  return 0;
		
		}
	}
	
	public int testaCaptura(int linIni, int colIni, int linFim, int colFim){
		
		Posicao posicaoAtual = this.getPosicao(linIni,colIni);
		Peca pecaAtual = posicaoAtual.getOcupante();
		
		boolean capturaPorAproximacao = false;
		boolean capturaPorAfastamento = false;
		
		Posicao posicaoAux;
		Peca pecaAux;
		int linAux; 
		int colAux;    		
		
		int direcaoJogada = this.retornaDirecaoJogada(linIni, colIni, linFim, colFim);
		int direcaoOposta = this.retornaDirecaoOposta(direcaoJogada);
		
		// Testa Aproximacao
		linAux = this.retLinAdjacente(direcaoJogada,linFim);
		colAux = this.retColAdjacente(direcaoJogada,colFim);
		
		if (linAux >=1 && linAux <=5 && colAux >= 1 && colAux <=9) {
		posicaoAux = this.getPosicao(linAux,colAux);
		pecaAux = posicaoAux.getOcupante();
	    
		if (posicaoAux.ocupada() && pecaAtual != pecaAux) {
			capturaPorAproximacao = true; 
	    }
		}
		// Testa Afastamento
		linAux = this.retLinAdjacente(direcaoOposta,linIni);
		colAux = this.retColAdjacente(direcaoOposta,colIni);
		
		if (linAux >=1 && linAux <=5 && colAux >= 1 && colAux <=9) {
		posicaoAux = this.getPosicao(linAux,colAux);
		pecaAux = posicaoAux.getOcupante();
	    
		if (posicaoAux.ocupada() && pecaAtual != pecaAux) {
			capturaPorAfastamento = true; 
	    }
		}
		
		if (capturaPorAproximacao && capturaPorAfastamento) {
			return 3; } 
		else if (capturaPorAproximacao) {
			return 1; }
		else if (capturaPorAfastamento) {
			return 2; }
		else return 0;
	    
     }
		
	public void capturarPecas(int pLin, int pCol, int pDirecao, Peca pPeca ){
		// Parâmetro: Posição da jogada, Direção e Jogador da vez!		
		
		Posicao posicaoAux;
		Peca pecaAux;
		
		int linAux = pLin;
		int colAux = pCol;
		
		linAux = this.retLinAdjacente(pDirecao,linAux);
		colAux = this.retColAdjacente(pDirecao,colAux);
		
		// Para jogadas do meio
		while (linAux >= 1 && linAux <= 5 && colAux >= 1 && colAux <= 9) {
			
			posicaoAux = this.getPosicao(linAux,colAux);
			pecaAux = posicaoAux.getOcupante();
		    if (posicaoAux.ocupada() && pPeca != pecaAux) {
		    	posicaoAux.esvaziarPosicao(); 
				
				linAux = this.retLinAdjacente(pDirecao,linAux);
				colAux = this.retColAdjacente(pDirecao,colAux);
		    }
		    else break;
	     }
		
		
		
		

	}
		
	
	public int retLinAdjacente (int pDirecao, int pLin) {
			
		switch(pDirecao) {
	
		  case 1: // Norte
			return pLin-1;
	
		  case 2: // Sul
			 return pLin+1;
		
		  case 3: // Leste
			return pLin;
     
		  case 4: // Oeste
		    return pLin;
     
		  case 5: // Nordeste 
		    return pLin-1;
		
		  case 6: // Noroeste            
		    return pLin-1;
		    
		  case 7: // Sudeste            
		    return pLin+1;
  
		  case 8: // Sudoeste
		    return pLin+1;
		    
		   default: 
		 	 return pLin;
		}
		
	}
	
	
	public int retColAdjacente (int pDirecao, int pCol) {
			
		switch(pDirecao) {
	
		  case 1: // Norte 
			return pCol;
	
		  case 2: // Sul
			 return pCol;
		
		  case 3: // Leste
			return pCol + 1;
     
		  case 4: // Oeste
			return pCol - 1;
     
		  case 5: // Nordeste 
			return pCol + 1;
		
		  case 6: // Noroeste            
			return pCol - 1;
		    
		  case 7: // Sudeste            
			return pCol + 1;
  
		  case 8: // Sudoeste
			return pCol - 1;
		    
		  default: 
			 return pCol;
		}
		
	}
	
	
	public ImagemDeTabuleiro informarEstado() {
		ImagemDeTabuleiro estado;
		int valor = 0;
		//Jogador vencedor = null;
		estado = new ImagemDeTabuleiro();
		
		// composicao da mensagem
		if (this.getPartidaemAndamento()) {
			if (jogador1.getAtual()) {
				estado.setMensagem("Jogador Azul: " + jogador1.getNome());
			} else {
				estado.setMensagem("Jogador Amarelo: " + jogador2.getNome());			
			}			
		} /*else {
			vencedor = this.informarVencedor();
			if (vencedor == null) {
				estado.assumirMensagem("Partida encerrada sem vencedor");
			} else {
				estado.assumirMensagem("Vencedor: " + vencedor.informarNome()); 
			}; */			

        //	composicao do mapa de posicoes	
		for (int linha=1; linha<6; linha++){
			for (int coluna=1; coluna<10; coluna++){
				if ((this.getPosicao(linha, coluna)).ocupada()) {
					valor = ((this.getPosicao(linha, coluna)).getOcupante().getPeca());
					estado.setValor(linha, coluna, valor);			
				} else {
					valor = 0;
					estado.setValor(linha, coluna, valor);
				}			
			}
		}
		return (estado);
	}

	public int getNumJogadasVerdes() {
		return jogador1.getNumVezesQueJogou();
	}
	
	public int getNumJogadasVermelho() {
		return jogador2.getNumVezesQueJogou();
	}
	
	public boolean retSeHouveVencedor() {
				
		Posicao posicao;
		int qtdePeca1=0;
		int qtdePeca2=0;
		
		for (int linha=1; linha<6; linha++){
			for (int coluna=1; coluna<10; coluna++){
		        posicao = this.getPosicao(linha, coluna);
		        if (posicao.getOcupante() == peca1) {
		        	qtdePeca1++;
		        }
		        else if(posicao.getOcupante() == peca2) {
		        		qtdePeca2++;
		        }
	
			}
		}
		
		if (qtdePeca1==0) { // Jogador da Peça2 é o vencedor
			this.setPartidaemAndamento(false);
			if (jogador1.getPeca() == peca2) {
				jogador1.setVencedor();
			}
			else {
				jogador2.setVencedor();
			}
			return true;
		}
		else {
			if (qtdePeca2==0) { // Jogador da Peça1 é o vencedor
				this.setPartidaemAndamento(false);
				if (jogador1.getPeca() == peca1) {
					jogador1.setVencedor();
				}
				else {
					jogador2.setVencedor();
				}
			return true;	
			}
		} 
		
		return false;	
	}
	
	public String getNomeVencedor() {
		if (jogador1.vencedor) {
			return (jogador1.getNome());
		}
		else {
			return (jogador2.getNome());
		}
	}

	public Jogador getJogadorDaVez() {
		Jogador jogador;
		if (jogador1.getAtual()) {
			jogador = jogador1;
		} else {
			jogador = jogador2;
		}	
		return jogador;
	}
}
