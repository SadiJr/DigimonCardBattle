package dominioProblema;
import java.util.*;
public class Tabuleiro {
 
 
	protected Posicao posicoes[][] = new Posicao[5][6];
	private Baralho monte;
	private Baralho lixo;
	private Carta  cartaPop;
	protected Jogador jogador1;
	protected Jogador jogador2;
	protected boolean partidaEmAndamento;
	protected boolean partidaComVencedor;
	protected int pontos;
	 
	 	 
	public boolean InformarPartidaEmAndamento() {
		return partidaEmAndamento;
	}
	 
	 
	public void criarJogadorHumano(String nome, boolean simbolo) {
		int umSimbolo = 2;
		if ((jogador1 != null) && (jogador2 != null)) {
			jogador2 = null;
			jogador1 = new JogadorHumano();
			if (simbolo) {umSimbolo = 1;};
			jogador1.iniciar(nome, umSimbolo);
		} else {
			if (jogador1 == null) {
				jogador1 = new JogadorHumano();
				if (simbolo) {umSimbolo = 1;};
				jogador1.iniciar(nome, umSimbolo);
			} else {
				jogador2 = new JogadorHumano();
				if (simbolo) {umSimbolo = 1;};
				jogador2.iniciar(nome, umSimbolo);	
			};
		};
	}
	public void criarJogadorAutomatico(String nome, boolean simbolo) {
		int umSimbolo = 2;
		jogador2 = new JogadorHumano();
		if (simbolo) {umSimbolo = 1;};
		jogador2.iniciar(nome, umSimbolo);
	} 
	
	public void definirOPrimeiro(int primeiro) {
		boolean afirmativo = false;
		boolean condRepetir = true;
		int resposta;
		while (condRepetir) {
			resposta = JOptionPane.showConfirmDialog(this, (idJ1 + " inicia a partida?"));
			afirmativo = resposta == JOptionPane.YES_OPTION;
			condRepetir = resposta == JOptionPane.CANCEL_OPTION;
		};
		if (afirmativo) {
			tab.definirOPrimeiro(1);
		} else {
			tab.definirOPrimeiro(2);
		};
	}
	
		 
	public boolean tratarPosicao(int linha, int coluna) {
		Posicao posicaoSelecionada;
		boolean posicaoOcupada;
		boolean vezJogador1;
		ImagemDeTabuleiro estado;
		posicaoSelecionada = this.recuperarPosicao(linha, coluna);
		posicaoOcupada = posicaoSelecionada.ocupada();
		if (coluna == 1 || coluna == 6)
			posicaoOcupada = true;
		if (linha == 5)
			posicaoOcupada = true;
		if (posicaoOcupada) {
			return false;		
		} else {
			vezJogador1 = jogador1.informarDaVez();
			if (vezJogador1)
				posicaoSelecionada.alocarPeao(jogador1,cartaPop);
			partidaComVencedor = this.avaliarVencedor(linha, coluna);
			partidaEmAndamento = this.avaliarPartidaEmAndamento();
			estado = this.informarEstado();
			if (partidaEmAndamento) {
				if (vezJogador1) {
					jogador1.desabilitar();
					jogador1.habilitar(estado);
				}
			}
			return true;
		}	
	}
	
	
	public void esvaziar() {
		for (int linha=1; linha<6; linha++){
			for (int coluna=1; coluna<7; coluna++){
				posicoes[(linha-1)][(coluna-1)].esvaziar();				
			}
		}
	}
	 
	 
	public int click(int linha, int coluna) {
		boolean lanceOK;
		if (this.InformarPartidaEmAndamento()) {
		  lanceOK = this.tratarPosicao(linha, coluna);
		  if (lanceOK) {
			  return 0;
		  } else {
			  return 1;
		  }			
		} else {
			return 2;	
		}
	}
	
	
	public ImagemDeTabuleiro informarEstado() {
		ImagemDeTabuleiro estado;
		int valor = 0;
		//int numCartL1,numCartL2numCartL3,numCartL4, numCartCol1,numCartCol2,numCartCol3,numCartCol4 ; 
		int cncl;
		int cncc;
		int c21maior,c21igual,l21maior,l21igual;
		int numCartaLinha[]= new int[15];
		int numCartaColuna[]= new int[15];
		int somaLinha[]=new int[15];
		int somaColuna[]=new int[15];
		
		for(int i=0;i<numCartaLinha.length;i++){numCartaLinha[i]=0;}
		
		for(int g=0;g<numCartaColuna.length;g++){numCartaColuna[g]=0;}
		
		for(int i=0;i<somaLinha.length;i++){somaLinha[i]=0;}
		
		for(int i=0;i<somaColuna.length;i++){somaColuna[i]=0;}
		String linha1,linha2,linha3,linha4,coluna5,coluna2,coluna3,coluna4;
		
		Jogador vencedor = null;
		estado = new ImagemDeTabuleiro();
		if (this.InformarPartidaEmAndamento()) {
			if (jogador1.informarDaVez()) {
				estado.assumirMensagem("Jogador: " + jogador1.informarNome());
			}			
		} else {
			vencedor = this.informarVencedor();
			if (vencedor == null) {
				estado.assumirMensagem("Partida encerrada sem vencedor");
			} else {
				estado.assumirMensagem("Vencedor: " + vencedor.informarNome());			
			}			
		}
		for (int linha=1; linha<6; linha++){
			
			for (int coluna=1; coluna<7; coluna++){
			
				if ((this.recuperarPosicao(linha, coluna)).ocupada()) {
					valor = ((this.recuperarPosicao(linha, coluna)).informarNumero());
					estado.assumirValor(linha, coluna, valor);
					
							
				} else {
					valor = 0;
					estado.assumirValor(linha, coluna, valor);
				}			
			}
		}
		    cncl=0;//controla o numero de cartas na linha
		    cncc=0;//controla o numero de cartas na coluna
		     c21maior=0;// controla o estouro do valor coluna
		     c21igual=0;
		     l21maior=0;
		    l21igual=0;
		for (int i=1; i < 5; i++){//calcula o numero de cartas de cada linha
 		 	numCartaLinha[i]=this.contaLinha(i);
 		 	if (numCartaLinha[i]==4)
 		 		cncl=i;// atribui o numero da linha se linha contiver 4 cartas
		 	}
		 	int p;
    	for (int i=2; i < 6; i++){//calcula o numero de cartas de cada coluna
 		 	p=i-1;
 		 	numCartaColuna[p]=this.contaColuna(i);
 		 	if (numCartaColuna[p]==4)
 		 		cncc=i;// atribui o numero da coluna se a coluna  contiver 4 cartas
		 	}
		 	
		 for (int i=1; i < 5; i++){//calcula a soma cartas de cada linha
 		 somaLinha[i]=this.somal(i);
 		 if(somaLinha[i]==21){
 		 	l21igual=i; //atribui o numero da linha a variavel 
 		 }
 		 if(somaLinha[i]>21){
 		 	l21maior=i; //atribui o numero da linha a variavel 
 		 }	
 		 
 		 
 		 }	
 		 	int p2;
		for (int i=2; i < 6; i++){//calcula a soma cartas de cada coluna
 		 p2=i-1;
 		 somaColuna[p2]=this.somac(i);
 		 if(somaColuna[p2]==21){
 		 	c21igual=i; //atribui o numero da linha a variavel 
 		 }
 		 if(somaColuna[p2]>21){
 		 	c21maior=i; //atribui o numero da linha a variavel 
 		 }	
 		//  c21maior,c21igual,l21maiorl,l21igual;cncc cncl
 		 }
		if (c21igual!=0&&l21igual!=0){
			   pontos=pontos+21;
			   this.limpaColuna(c21igual);
			   this.limpalinha(l21igual);
			   c21igual=0;
			   l21igual=0;
		} 	
		if (c21igual!=0||l21igual!=0){
			  pontos=pontos+7;
		      if(c21igual!=0) {this.limpaColuna(c21igual);}else{
		      this.limpalinha(l21igual);
		      }
			   	
		}
		if ((c21maior!=0&&l21igual==0)||(l21maior!=0&&c21igual==0)){
			    pontos=pontos-7;
				if(c21maior!=0) {this.limpaColuna(c21maior);}else{
		      this.limpalinha(l21maior);
		      }
		
		
		}
		if ((cncc!=0&&l21igual==0)&&(cncl!=0&&c21igual==0)){
			 pontos=pontos-21;
			 this.limpaColuna(cncc);
			 this.limpalinha(cncl);
			 cncc=0;
		     cncl=0; 
		}
		
		if ((cncc!=0&&l21igual==0)||(cncl!=0&&c21igual==0)){
			pontos=pontos-7;
			if(cncc!=0) {this.limpaColuna(cncc);}else{
		      this.limpalinha(cncl);
		      }
		}
		
		
		
		estado.setM56(Integer.toString(pontos));
		int aux;
		aux=this.somal(1);
		linha1=Integer.toString(aux);
		estado.setM16(linha1);
	
		
		
    	aux=this.somal(2);		
		linha2=Integer.toString(aux);
		estado.setM26(linha2);
		
		aux=this.somal(3);
		linha3=Integer.toString(aux);
		estado.setM36(linha3);
		
		aux=this.somal(4);
		linha4=Integer.toString(aux);
		estado.setM46(linha4);
		
		aux=this.somac(2);
		coluna2=Integer.toString(aux);		
		estado.setM52(coluna2);
		
		aux=this.somac(3);
		coluna3=Integer.toString(aux);
		estado.setM53(coluna3);
		
		aux=this.somac(4);
		coluna4=Integer.toString(aux);
		estado.setM54(coluna4);

			
    
		aux=this.somac(5);
		coluna5=Integer.toString(aux);
		estado.setM55(coluna5);
		
	
		estado.assumirValor(3, 1,cartaPop.getNumero());
					
		return (estado);
	}
	
	 
	public Posicao recuperarPosicao(int linha, int coluna) {
		return (posicoes[(linha-1)][(coluna-1)]);
	}
	
	public int contaLinha (int linha){
		int l=linha;
		int countCol=0;
    	for (int i=2;i<6; i++){// conta o numero de cartas na linha (coluna 2 a 5)
		   if ((this.recuperarPosicao(l, i)).informarCarta()!= null){
		   			countCol=countCol+1;
		   	}
		   	}
			return countCol;	
	}
	
	public int contaColuna (int coluna){
		int c=coluna;
		int countLin=0;
		for (int i=1;i<5; i++){// conta o numero de cartas na coluna (linha 1 a 4)
		   
		   if ((this.recuperarPosicao(i, c)).informarCarta()!= null){
		   			countLin=countLin+1;
		   }
		}
		return countLin;	
	}


	public void limpalinha(int linha){
	 int valor;	
	 	ImagemDeTabuleiro estado; 	
	 	estado = new ImagemDeTabuleiro();
	  
	 	for (int i=1;i<5;i++){
			posicoes[linha - 1][i].esvaziar();//linha zero poi o array comeca com 0
			valor = 0;
			estado.assumirValor(linha, i, valor);
		}
	}
	
	public void limpaColuna (int coluna){
		int valor;
		ImagemDeTabuleiro estado;
		estado = new ImagemDeTabuleiro();
		
		for (int i=1; i<5; i++){
			posicoes[i-1][coluna-1].esvaziar();
			valor = 0;
			estado.assumirValor(i, coluna, valor);
		}
	}
	
	
	
	public int somal(int linha){
		
		int l=linha;
		int acumCol=0;
		
		int aux=0;
		for (int i=2;i<6; i++){// fazendo a soma da linha (coluna 2 a 5)
		   
		   if ((this.recuperarPosicao(l, i)).informarCarta()!= null){
		   		
		   			aux=((this.recuperarPosicao(l, i)).informarNumero())%13;
		   			if (aux==0||aux>10)
		   				aux=10;
		          	acumCol=acumCol +aux;}
				}
				//v=Integer.toString(acumCol);
				return acumCol;	
	}
	 
	 
	public int somac(int coluna){
		
		int c=coluna;
		int acumLin=0;
		
		int aux=0;
			
		for (int i=1;i<5; i++){// fazendo a soma da linha (coluna 2 a 5)
		   
		   if ((this.recuperarPosicao(i, c)).informarCarta()!= null){
		   		
		   			aux=((this.recuperarPosicao(i, c)).informarNumero())%13;
		   			if (aux==0||aux>10)aux=10;
		          	acumLin=acumLin + aux;
		   }
		}
		//v=Integer.toString(acumLin);//Integer
		return acumLin;	
	}
	
		
	public boolean avaliarVencedor(int linha, int coluna) {
		boolean vencedor = false;
		cartaPop=monte.comprar();
		return vencedor;
	}
	
	 
	public boolean avaliarPartidaEmAndamento() {
		boolean cheio = true;
		boolean resultado;
		if (partidaComVencedor) {
			resultado = false;
		} else {
			for (int linha=1; linha<6; linha++){
				for (int coluna=1; coluna<7; coluna++){
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
			return null;
		}
	}
	
	
	public boolean informarExistenciaJogadores() {
		return ((jogador1 != null)&& (jogador1 != null));
	}

	 
	public void iniciar() {
		monte = new Baralho();
		lixo = new Baralho();
		monte.inicializarCartas();
		monte.embaralhar();
		cartaPop=monte.comprar();
				
		for (int linha=1; linha<6; linha++){
			for (int coluna=1; coluna<7; coluna++){
				posicoes[(linha-1)][(coluna-1)] = new Posicao();
				posicoes[(linha-1)][(coluna-1)].esvaziar();				
			}
		}

	}
	 
}
 
