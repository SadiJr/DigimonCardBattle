package dominioProblema;

public class Tabuleiro {
	private final String DESLOCAR_FILEIRA = "DESLOCAR";
	private final String PINTAR_CELULA = "PINTAR";
 
	protected Posicao posicoes[][] = new Posicao[10][10];	
	protected Jogador jogador1;	 
	protected Jogador jogador2;	 
	protected boolean partidaEmAndamento;	 
	protected boolean partidaComVencedor;
	protected int celulasJ1 = 0;
	protected int celulasJ2 = 0;
	protected boolean primeiraJogada = true;
	int linhaTravada;
	int colunaTravada;
	
	
	public boolean InformarPartidaEmAndamento() {
		return partidaEmAndamento;
	}
	 
	public void criarJogador(String nome, boolean simbolo) {
		int umSimbolo = 2;
		if ((jogador1 != null) && (jogador2 != null)) {
			jogador2 = null;
			jogador1 = new Jogador();
			if (simbolo) {umSimbolo = 1;};
			jogador1.iniciar(nome, umSimbolo);
		} else {
			if (jogador1 == null) {
				jogador1 = new Jogador();
				if (simbolo) {umSimbolo = 1;};
				jogador1.iniciar(nome, umSimbolo);
			} else {
				jogador2 = new Jogador();
				if (simbolo) {umSimbolo = 1;};
				jogador2.iniciar(nome, umSimbolo);	
			}
		}
	}
	 
	public void definirOPrimeiro(int primeiro) {
		ImagemDeTabuleiro estado;
		this.inicializarPosicoes();
		partidaEmAndamento = true;
		estado = this.informarEstado();
		jogador1.reiniciar();
		jogador2.reiniciar();
		if (primeiro == 1) {
			jogador1.habilitar();
		} else {
			jogador2.habilitar();		
		}
	}
	
	public boolean informarPosicaoExterna(int linha, int coluna){
		if(linha == 1 || linha == 10 || coluna == 1 || coluna == 10){
			return true;
		}
		return false;
	}
	
	public boolean informarPosicaoInativa(int linha, int coluna){
		if(coluna == 1 || coluna == 10){
			if(linha == 1 || linha == 10){
				return true;
			}
			return false;
		}
		return false;
	}
	 
	public boolean pintarCelula(int linha, int coluna) {
		Posicao posicaoSelecionada;
		boolean posicaoOcupada;
		boolean vezJogador1;
		posicaoSelecionada = this.recuperarPosicao(linha, coluna);		
		posicaoOcupada = posicaoSelecionada.ocupada();
		if (posicaoOcupada) {
			return false;		
		} else {//Posi��o n�o est� ocupada
			vezJogador1 = jogador1.informarDaVez();
			if (vezJogador1) {
				posicaoSelecionada.alocarPeao(jogador1);
				this.somarCelulasJ1(1);
			} else {
				posicaoSelecionada.alocarPeao(jogador2);
				this.somarCelulasJ2(1);
			}
			
			if(this.houvePontuacao(linha, coluna))
				partidaComVencedor = this.avaliarVencedor();
			return true;
		}	
	}
	
	public void alternarJogadores(){
		if(jogador1.informarDaVez()){
			jogador1.desabilitar();
			jogador2.habilitar();
		}else{
			jogador2.desabilitar();
			jogador1.habilitar();
		}
	}
	
	public void inicializarPosicoes() {
		for (int linha=1; linha<11; linha++){
			for (int coluna=1; coluna<11; coluna++){
				posicoes[(linha-1)][(coluna-1)] = new Posicao();
				posicoes[(linha-1)][(coluna-1)].esvaziar();
				
				if(this.informarPosicaoExterna(linha, coluna)){
					posicoes[(linha-1)][(coluna-1)].setAcaoRepresentada(DESLOCAR_FILEIRA);
				}
				else{
					posicoes[(linha-1)][(coluna-1)].setAcaoRepresentada(PINTAR_CELULA);
				}
			}
		}
	}
	 
	public int procederLance(int linha, int coluna) {
		boolean lanceOK = false;
		if (this.InformarPartidaEmAndamento()) {
			Posicao posicao = this.recuperarPosicao(linha, coluna);
			ImagemDeTabuleiro estado;
			if(posicao.getOcupante() == null && !posicao.isFileiraTravada()){
				this.travarFileira(linhaTravada, colunaTravada, false);
			}
			if(posicao.getAcaoRepresentada().equals(DESLOCAR_FILEIRA)) {				
				lanceOK = this.deslocarFileira(linha, coluna);
				if(lanceOK){
					linhaTravada = linha;
					colunaTravada = coluna;
				}
			}
			else {
				lanceOK = this.pintarCelula(linha, coluna);
			}
			
			partidaEmAndamento = this.avaliarPartidaEmAndamento();
			estado = this.informarEstado();
		  
		  if (lanceOK) {
			  this.alternarJogadores();
			  return 0;
		  } else {
			  if(posicao.getAcaoRepresentada().equals(DESLOCAR_FILEIRA))
				  return -1;
			  return 1;
		  }			
		} else {
			return 2;	
		}
	}
	
	public ImagemDeTabuleiro informarEstado() {
		ImagemDeTabuleiro estado;
		int valor = 0;
		Jogador vencedor = null;
		estado = new ImagemDeTabuleiro();
		
		// composicao da mensagem
		if (this.InformarPartidaEmAndamento()) {
			if (jogador1.informarDaVez()) {
				estado.assumirMensagem1("Jogador: " + jogador1.informarNome()+"[ "+jogador1.getPontuacao()+" ]");
				estado.assumirMensagem2(jogador2.informarNome()+"[ "+jogador2.getPontuacao()+" ]");
			} else {
				estado.assumirMensagem2("Jogador: " + jogador2.informarNome()+"[ "+jogador2.getPontuacao()+" ]");
				estado.assumirMensagem1(jogador1.informarNome()+"[ "+jogador1.getPontuacao()+" ]");
			}		
		} else {
			vencedor = this.informarVencedor();
			if (vencedor == null) {
				estado.assumirMensagem1("Partida encerrada sem vencedor");
				estado.assumirMensagem2("");
			} else {
				estado.assumirMensagem1("Vencedor: " + vencedor.informarNome());
				estado.assumirMensagem2("");
			}		
		}
	
		for (int linha=1; linha<11; linha++){
			for (int coluna=1; coluna<11; coluna++){				
				if(!this.informarPosicaoExterna(linha, coluna) && !this.informarPosicaoInativa(linha, coluna)){			
					if ((this.recuperarPosicao(linha, coluna)).ocupada()) {
						valor = ((this.recuperarPosicao(linha, coluna)).informarOcupante()).informarSimbolo();
						estado.assumirValor(linha, coluna, valor);			
					} else {
						valor = 0;
						estado.assumirValor(linha, coluna, valor);
					}
				}								
				else if(this.informarPosicaoExterna(linha, coluna)){
					valor = 3;
					estado.assumirValor(linha, coluna, valor);
				}				
			}
		}
		return (estado);
	}
	 
	public Posicao recuperarPosicao(int linha, int coluna) {
		return (posicoes[(linha-1)][(coluna-1)]);
	}
	
	private boolean formouQuadrado(int linha, int coluna, int linhaAuxiliar, int colunaAuxiliar) {
		Posicao selecionado, posicao2, posicao3, posicao4;
		selecionado= this.recuperarPosicao(linha, coluna);
		
		posicao2 = this.recuperarPosicao(linhaAuxiliar, colunaAuxiliar);		
		if(selecionado.mesmoOcupante(posicao2)){
			posicao3 = this.recuperarPosicao(linhaAuxiliar, coluna);
			if(selecionado.mesmoOcupante(posicao3)){
				posicao4 = this.recuperarPosicao(linha, colunaAuxiliar);
				if(selecionado.mesmoOcupante(posicao4)){
					this.atribuirPontos();
					if(selecionado.informarOcupante().equals(jogador1)){
						this.somarCelulasJ1(-4);
					}
					else{
						this.somarCelulasJ2(-4);	
					}
					posicao2.esvaziar();					
					posicao3.esvaziar();
					posicao4.esvaziar();
					selecionado.esvaziar();
					return true;
				} 
			}
		}
			
		return false;
	}
	
	private void atribuirPontos() {
		if(jogador1.informarDaVez()){
			jogador1.somarPontos();
		}
		else{
			jogador2.somarPontos();
		}
	}

	public boolean avaliarVencedor() {		
		if(this.celulasJ1 == 0 || this.celulasJ2 == 0){
			if(jogador1.getPontuacao() > jogador2.getPontuacao()){
				jogador1.tornarSeVencedor();
			}
			else {
				jogador2.tornarSeVencedor();
			}						
			return true;
		}
		
		return false;
	}

	public boolean avaliarPartidaEmAndamento() {
		boolean cheio = true;
		boolean resultado;
		if (partidaComVencedor) {
			this.finalizarJogo();
			resultado = false;
		} else {
			for (int linha=1; linha<11; linha++){
				for (int coluna=1; coluna<11; coluna++){
					if (!(posicoes[(linha-1)][(coluna-1)].ocupada())) {
						cheio = false;
					}
				}
			}
			resultado = !cheio;
		}
		return resultado;
	}
	 
	public Jogador informarVencedor() {
		if (jogador1.informarVencedor()) {
			return jogador1;
		} else {
			if (jogador2.informarVencedor()) {
				return jogador2;
			} else {
				return null;
			}
		}
	}
	
	public boolean informarExistenciaJogadores() {
		return ((jogador1 != null)&& (jogador2 != null));
	}

	public boolean houvePontuacao(int linha, int coluna){
		boolean pontuou = false;
		pontuou = this.formouQuadrado(linha, coluna, linha-1, coluna-1);
		if(!pontuou) {			
			pontuou = this.formouQuadrado(linha, coluna, linha-1, coluna+1);
			if(!pontuou){
				pontuou = this.formouQuadrado(linha, coluna, linha+1, coluna-1);
				if(!pontuou){
					pontuou = this.formouQuadrado(linha, coluna, linha+1, coluna+1);
				}
			}
		}
			
		return pontuou;
	}
	
	public boolean deslocarFileira(int linha, int coluna){
		Posicao pTemp = new Posicao();
		boolean deslocou = false;
		pTemp = this.recuperarPosicao(linha, coluna);
		if(!pTemp.isFileiraTravada()){
			
			if(coluna == 1){//HORIZONTAL PARA ESQUERDA
				pTemp = posicoes[linha-1][1];
				for(int i = 1; i <= 7; i++){
					posicoes[linha-1][i] = posicoes[linha-1][i+1];
				}
				posicoes[linha-1][8] = pTemp;
				this.verificaPontuacaoDeslocamento(linha,true);				
				deslocou = true;
			}
			if(coluna == 10){//HORIZONTAL PARA DIREITA
				pTemp = posicoes[linha-1][8];
				for(int i = 8; i >= 2; i--){
					posicoes[linha-1][i] = posicoes[linha-1][i-1];
				}
				posicoes[linha-1][1] = pTemp;
				this.verificaPontuacaoDeslocamento(linha, true);
				deslocou = true;
			}
			if(linha == 1){//VERTICAL PARA CIMA
				pTemp = posicoes[1][coluna-1];
				for(int i = 1; i <= 7; i++){
					posicoes[i][coluna-1] = posicoes[i+1][coluna-1];
				}
				posicoes[8][coluna-1] = pTemp;
				this.verificaPontuacaoDeslocamento(coluna, false);
				deslocou = true;
			}
			if(linha == 10){//VERTICAL PARA BAIXO
				pTemp = posicoes[8][coluna-1];
				for(int i = 8; i >= 2; i--){
					posicoes[i][coluna-1] = posicoes[i-1][coluna-1];
				}
				posicoes[1][coluna-1] = pTemp;
				this.verificaPontuacaoDeslocamento(coluna, false);
				deslocou = true;
			}
			this.travarFileira(linha, coluna, true);
		}	
		return deslocou;
	}
	
	public void travarFileira(int linha, int coluna, boolean travar){
		if(linha == 1 || linha == 10){
			this.recuperarPosicao(1, coluna).setFileiraTravada(travar);
			this.recuperarPosicao(10, coluna).setFileiraTravada(travar);
		}
		if(coluna == 1 || coluna == 10){
			this.recuperarPosicao(linha, 1).setFileiraTravada(travar);
			this.recuperarPosicao(linha, 10).setFileiraTravada(travar);
		}
	}
	
	public void verificaPontuacaoDeslocamento(int fileira, boolean horizontal){
		int pontos = 0;
		if(horizontal){
			for(int i=2; i<=8; i++){
				if(this.recuperarPosicao(fileira, i).informarOcupante() != null)
					if(this.houvePontuacao(fileira, i))
						pontos++;
			}
		}else{
			for(int i=2; i<=8; i++){
				if(this.recuperarPosicao(i, fileira).informarOcupante() != null)
					if(this.houvePontuacao(i, fileira))
						pontos++;
			}
		}
		if(pontos > 0)
			partidaComVencedor = this.avaliarVencedor();
	}
	 
	public Jogador getJogador1() {
		return jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}

	public boolean isPartidaComVencedor() {
		return partidaComVencedor;
	}
	
	public void somarCelulasJ1(int numCelulas){
		celulasJ1 = celulasJ1 + numCelulas;
	}
	
	public void somarCelulasJ2(int numCelulas){
		celulasJ2 = celulasJ2 + numCelulas;
	}

	public int getCelulasJ1() {
		return celulasJ1;
	}

	public void setCelulasJ1(int celulasJ1) {
		this.celulasJ1 = celulasJ1;
	}

	public int getCelulasJ2() {
		return celulasJ2;
	}

	public void setCelulasJ2(int celulasJ2) {
		this.celulasJ2 = celulasJ2;
	}
	
	public void finalizarJogo(){
		this.celulasJ1 = 0;
		this.celulasJ2 = 0;
		this.jogador1.pontuacao = 0;
		this.jogador2.pontuacao = 0;
		this.primeiraJogada = true;
		this.partidaComVencedor = false;
		this.jogador1.zerarPontos();
		this.jogador2.zerarPontos();
	}

	public boolean isPrimeiraJogada() {
		return primeiraJogada;
	}

	public void setPrimeiraJogada(boolean primeiraJogada) {
		this.primeiraJogada = primeiraJogada;
	}	
}