package DominioDoProblema;

public class Tabuleiro {

	protected Jogador jogador1;
	protected Jogador jogador2;
	protected Posicao posicoes[][]=new Posicao[7][7];
	protected boolean conectado;
	protected boolean partidaEmAndamento;

	public boolean informarConectado() {
		return conectado;
	}

	/**
	 * 
	 * @param valor
	 */
	public void estabelecerConectado(boolean valor) {
		conectado=valor;
	}

	public boolean informarEmAndamento() {
		return partidaEmAndamento;
	}

	public void esvaziar() {
		for (int i = 0; i < 7; i++) {
			for(int n=0; n<7; n++) {
				posicoes[i][n].esvaziar();
			}
		}
		jogador1=null;
		jogador2=null;
		partidaEmAndamento=false;
	}

	/**
	 * 
	 * @param idJogador
	 */
	public void criarJogador(String idJogador) {
		if(jogador1==null) {
			jogador1=new Jogador();
			jogador1.iniciar();
			jogador1.assumirNome(idJogador);
		}else {
			jogador2=new Jogador();
			jogador2.iniciar();
			jogador2.assumirNome(idJogador);
		}
	}

	/**
	 * 
	 * @param posicao
	 */
	public void habilitar(Integer posicao) {
		if(posicao==1) {
			jogador1.mudarFase();
			jogador1.assumirCor(1);
			jogador2.assumirCor(2);
			posicionarPeca(jogador1, jogador2);
		}else {
			jogador2.mudarFase();
			jogador2.assumirCor(1);
			jogador1.assumirCor(2);
			posicionarPeca(jogador2, jogador1);
		}
		partidaEmAndamento=true;
	}

	/**
	 * 
	 * @param jog1
	 * @param jog2
	 */
	public void posicionarPeca(Jogador jog1, Jogador jog2) {
		int peca1;
		int peca2;
		peca1=jog1.simbolo;
		jog1.atualizar(posicoes[0][3]);
		posicoes[0][3].modOcupacao(peca1);
		peca2=jog2.simbolo;
		jog2.atualizar(posicoes[6][3]);
		posicoes[6][3].modOcupacao(peca2);
	}

	/**
	 * 
	 * @param linha
	 * @param coluna
	 */
	public int click(int linha, int coluna) {
		int resultado;
		boolean vez;
		vez=jogador1.informarDaVez();
		if(vez) {
			resultado=tratarLance(linha, coluna, jogador1);
		}else {
			resultado=8;
		}
		return resultado;
	}

	/**
	 * 
	 * @param linha
	 * @param coluna
	 */
	public Lance informarJogada(int linha, int coluna) {
		Lance lance;
		lance=new Lance();
		lance.assumir(linha,coluna);
		return lance;
	}

	/**
	 * 
	 * @param lance
	 */
	public void receberJogada(Lance lance) {
		int linha, coluna;
		linha=lance.informarLinha();
		coluna=lance.informarColuna();
		tratarLance(linha, coluna, jogador2);
	}

	public void finalizarPartida() {
		partidaEmAndamento=false;
		jogador1.desabilitar();
		jogador2.desabilitar();
	}

	/**
	 * 
	 * @param linha
	 * @param coluna
	 * @param jog
	 */
	public int tratarLance(int linha, int coluna, Jogador jog) {
		int resultado;
		boolean ocupada;
		ocupada=posicoes[linha][coluna].verificarOcupada();
		if(ocupada) {
			resultado=11;
		}else {
			int fase;
			fase=jog.verificarFase();
			if(fase==1) {
				boolean adjacente;
				adjacente=jog.verificarAdjacente(linha, coluna);
				if(adjacente) {
					jog.atualizar(posicoes[linha][coluna]);
					jog.mudarFase();
					resultado=10;
				}else {
					resultado=12;
				}
			}else {
				posicoes[linha][coluna].modOcupacao(3);
				boolean vencedor;
				vencedor=verificarVencedor();
				if(vencedor) {
					resultado=9;
					finalizarPartida();
				}else {
					passarVez();
					resultado=10;
				}
			}
		}
		return resultado;
	}

	public boolean verificarVencedor() {
		boolean perdeu;
		Posicao posicao;
		posicao=jogador1.informarPosicao();
		perdeu=verificarPerdeu(posicao);
		if(perdeu) {
			jogador2.assumirVencedor();
			return true;
		}
		posicao=jogador2.informarPosicao();
		perdeu=verificarPerdeu(posicao);
		if(perdeu) {
			jogador1.assumirVencedor();
			return true;
		}
		return false;
	}

	public void passarVez() {
		jogador1.mudarFase();
		jogador2.mudarFase();
	}

	/**
	 * 
	 * @param posicao
	 */
	public boolean verificarPerdeu(Posicao posicao) {
		int x, y, l, c, yI;
		x=posicao.informarLinha()-1;
		y=posicao.informarColuna()-1;
		if(x<0) {
			x=0;
		}
		if(y<0) {
			y=0;
		}
		yI=y;
		l=posicao.informarLinha()+1;
		c=posicao.informarColuna()+1;
		if(l>6) {
			l=6;
		}
		if(c>6) {
			c=6;
		}
		while(x<=l) {
			while(y<=c) {
				boolean ocupada=false;
				ocupada=posicoes[x][y].verificarOcupada();
				if(!ocupada) {
					return false;
				}
				y++;
			}
			x++;
			y=yI;
		}
		return true;
	}

	public ImagemTabuleiro informarEstado() {
		ImagemTabuleiro retorno=new ImagemTabuleiro();
		String mensagem;
		if(partidaEmAndamento) {
			boolean vez;
			vez=jogador1.informarDaVez();
			if(vez) {
				int fase=jogador1.verificarFase();
				if(fase==1) {
					int peca=jogador1.informarSimbolo();
					String cor;
					if(peca==1) {
						cor= "Branca";
					}else {
						cor="Preta";
					}
					mensagem="Vez do jogador "+jogador1.informarNome()+" movimentar a pe�a "+cor;
				}else {
					mensagem="Vez do jogador "+jogador1.informarNome()+" bloquear uma posi��o";
				}
			}else {
				int fase=jogador2.verificarFase();
				if(fase==1) {
					int peca=jogador2.informarSimbolo();
					String cor;
					if(peca==1) {
						cor= "Branca";
					}else {
						cor="Preta";
					}
					mensagem="Vez do jogador "+jogador2.informarNome()+" movimentar a pe�a "+cor;
				}else {
					mensagem="Vez do jogador "+jogador2.informarNome()+" bloquear uma posi��o";
				}
			}
			
		}else {
			boolean vencedor=jogador1.informarVencedor();
			if(vencedor) {
				mensagem= "VENCEDOR: "+jogador1.informarNome();
			}else {
				mensagem= "VENCEDOR: "+jogador2.informarNome();
			}
		}
		retorno.assumirMensagem(mensagem);
		
		for(int linha=0;linha<7;linha++) {
			for(int coluna=0;coluna<7;coluna++) {
				int valor=posicoes[linha][coluna].informarOcupacao();
				retorno.assumirValor(linha, coluna, valor);
			}
		}
		
		return retorno;
	}

	public void iniciar() {
		for(int linha=0; linha<7; linha++) {
			for(int coluna=0; coluna<7; coluna++) {
				posicoes[linha][coluna]=new Posicao(linha,coluna);
			}
		}
	}
}