package dominioProblema;
import java.util.*;
public class Tabuleiro {
 
 
	protected Posicao posicoes[][] = new Posicao[5][6];
	private Baralho monte;

	private Carta  cartaPop;
	private Carta cartaRede;
	protected Jogador jogador1;
	protected Jogador jogador2;
	protected boolean partidaEmAndamento;
	protected boolean partidaComVencedor;
	protected int pontos;
	protected int cartaE; 
	protected boolean rede;
	

    public void setRedeTrue(){rede=true;}
    public void setRedeFalse(){rede=false;}
	public void setCartaE(int p){
		this.cartaE=p;
	}
	public int getCartaE(){
		return cartaE;
	}
	public boolean InformarPartidaEmAndamento() {
		return partidaEmAndamento;
	}
	 
	 
	public void criarJogadorHumano(String nome, boolean simbolo) {
		int umSimbolo = 2000;
		if ((jogador1 != null) && (jogador2 != null)) {
			jogador2 = null;
			jogador1 = new JogadorHumano();
			if (simbolo) {umSimbolo = 1000;};
			jogador1.iniciar(nome, umSimbolo);
			jogador1.setPonto(0);
		} else {
			if (jogador1 == null) {
				jogador1 = new JogadorHumano();
				if (simbolo) {umSimbolo = 1000;};
				jogador1.iniciar(nome, umSimbolo);
			} else {
				jogador2 = new JogadorHumano();
				if (simbolo) {umSimbolo = 1000;};
				jogador2.iniciar(nome, umSimbolo);
				jogador2.setPonto(0);
			};
		};
	}
	 
	public void criarJogadorAutomatico(String nome, boolean simbolo) {
		int umSimbolo = 2000;
		jogador2 = new JogadorAutomatico();
		if (simbolo) {umSimbolo = 1000;};
		jogador2.iniciar(nome, umSimbolo);
	}
	public void definirOPrimeiro(int primeiro) {
		ImagemDeTabuleiro estado;
		Lance j2Lance;
		this.esvaziar();
		partidaEmAndamento = true;
		estado = this.informarEstado();
		jogador1.reiniciar();
		jogador2.reiniciar();
		if (primeiro == 1000) {
			jogador1.habilitar(estado);
		} else {
			j2Lance = jogador2.habilitar(estado);
			if (j2Lance.informarLinha()!=0) {
				this.tratarPosicao((j2Lance.informarLinha()),(j2Lance.informarColuna()));		
			};			
		};
	}
	
	public int somaPontos(int linha,int coluna){
	int glinha,gcoluna,sl,sc,total,pp;
	pp=0;
	total=0;
	gcoluna=this.contaColuna(coluna);
	glinha=this.contaLinha(linha);
	sc=this.somac(coluna);
	sl=this.somal(linha);
	
	if (sl==21&& sc==21){total=total+20;}
	else { if((sl==21)||(sc==21)){total=total+10;}
	else{pp=1;}
	}
	
	if(pp==1){
		if ((glinha==4)&&(gcoluna==4)||(glinha==4 && sc>21)||(gcoluna==4 && sl>21)){total=total-20;}
		else {if((sc>21)||(sl>21)||(glinha==4)||gcoluna==4){total=total-10;}
		
		else{pp=0;}
		}
		}
	return total;
	}	 
	public boolean tratarPosicao(int linha, int coluna) {
		
		Posicao posicaoSelecionada;
		boolean posicaoOcupada;
		boolean vezJogador1;
		ImagemDeTabuleiro estado;
		Lance j2Lance;
		posicaoSelecionada = this.recuperarPosicao(linha, coluna);
		posicaoOcupada = posicaoSelecionada.ocupada();
		//this.setRedeFalse();
		if (posicaoOcupada) {
			return false;		
		} else {
			vezJogador1 = jogador1.informarDaVez();
			if (vezJogador1) {
				posicaoSelecionada.alocarPeao(jogador1,cartaPop);
				jogador1.setPonto(jogador1.getPonto()+this.somaPontos(linha,coluna));
			} else {
				posicaoSelecionada.alocarPeao(jogador2,cartaPop);
				jogador2.setPonto(jogador2.getPonto()+this.somaPontos(linha,coluna));
			};
			
			
			partidaComVencedor = this.avaliarVencedor(linha, coluna);
			partidaEmAndamento = this.avaliarPartidaEmAndamento();
			estado = this.informarEstado();
			if (partidaEmAndamento) {
				if (vezJogador1) {
		//			System.out.println("j1"+jogador1.informarNome());
					jogador1.desabilitar();
					j2Lance = jogador2.habilitar(estado);
					
					System.out.println("j2"+jogador2.informarNome()+"ptr="+jogador2.getPonto());
					
					System.out.println(""+this.informarCartaSorteada());
					
					System.out.println(""+j2Lance.informarLinha()+""+j2Lance.informarColuna());
					if (j2Lance.informarLinha()!= 0) {
						this.tratarPosicao((j2Lance.informarLinha()),(j2Lance.informarColuna()));		
					};
				} else {
					jogador2.desabilitar();
					jogador1.habilitar(estado);
				};
			};
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
	public int clickRede(int linha, int coluna,int carta) {
		boolean lanceOK;
		//this.setRedeFalse();
	//	System.out.println("carta clickRede====>"+carta);
		//cartaPop.setNumero(carta);
	//	System.out.println("carta clickRede====>"+cartaPop.getNumero());
		if (this.InformarPartidaEmAndamento()) {
		  lanceOK = this.tratarPosicaoRede(linha, coluna,carta);
		  if (lanceOK) {
			  return 0;
		  } else {
			  return 1;
		  }			
		} else {
			return 2;	
		}
	}
	public boolean tratarPosicaoRede(int linha, int coluna,int carta) {
		
	    
		cartaRede=new Carta(carta);
		Posicao posicaoSelecionada;
		boolean posicaoOcupada;
		boolean vezJogador1;
		ImagemDeTabuleiro estado;
		Lance j2Lance;
		posicaoSelecionada = this.recuperarPosicao(linha, coluna);
		posicaoOcupada = posicaoSelecionada.ocupada();
		
		if (posicaoOcupada) {
			return false;		
		} else {
			vezJogador1 = jogador1.informarDaVez();
			if (vezJogador1) {
				posicaoSelecionada.alocarPeao(jogador1,cartaRede);
				jogador1.setPonto(jogador1.getPonto()+this.somaPontos(linha,coluna));
			} else {
				posicaoSelecionada.alocarPeao(jogador2,cartaRede);
				jogador2.setPonto(jogador2.getPonto()+this.somaPontos(linha,coluna));
			};
			
			
			partidaComVencedor = this.avaliarVencedor(linha, coluna);
			partidaEmAndamento = this.avaliarPartidaEmAndamento();
			estado = this.informarEstado();
			if (partidaEmAndamento) {
				if (vezJogador1) {
		//			System.out.println("j1"+jogador1.informarNome());
					jogador1.desabilitar();
					j2Lance = jogador2.habilitar(estado);
					
					System.out.println("j2"+jogador2.informarNome()+"ptr="+jogador2.getPonto());
					
					System.out.println(""+this.informarCartaSorteada());
					
					System.out.println(""+j2Lance.informarLinha()+""+j2Lance.informarColuna());
					if (j2Lance.informarLinha()!= 0) {
						this.tratarPosicao((j2Lance.informarLinha()),(j2Lance.informarColuna()));		
					};
				} else {
					jogador2.desabilitar();
					jogador1.habilitar(estado);
				};
			};
			return true;
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
				estado.assumirMensagem("Jogador: " + jogador1.informarNome()+ "  Pontos ="+jogador1.getPonto());
			    estado.assumirValor(1,1,jogador1.informarSimbolo());
			} else {
				estado.assumirMensagem("Jogador: " + jogador2.informarNome()+ "   Pontos= "+jogador2.getPonto());			
				 estado.assumirValor(1,1,jogador2.informarSimbolo());			
			};			
		} else {
			vencedor = this.informarVencedor();
			if (vencedor == null) {
				estado.assumirMensagem("Partida encerrada sem vencedor");
			} else {
				estado.assumirMensagem("Vencedor: " + vencedor.informarNome()+"="+vencedor.getPonto());
				
				estado.assumirValor(1,1,vencedor.informarSimbolo());
	    
								
			};			
		};
		for (int linha=1; linha<6; linha++){
			
			for (int coluna=2; coluna<7; coluna++){
			
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
		
		
		if(jogador1.informarDaVez()){
		estado.setM56(Integer.toString(jogador1.getPonto()));
		estado.setM11(Integer.toString(jogador1.informarSimbolo()));
		
		}
		else{estado.setM56(Integer.toString(jogador2.getPonto()));
		estado.setM11(Integer.toString(jogador2.informarSimbolo()));
		}
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
		if (rede)
		{estado.assumirValor(3, 1,this.getCartaE());}//com rede	
		else{
		estado.assumirValor(3, 1,cartaPop.getNumero());}//sem rede
		if (vencedor != null){
			for (int linha=1; linha<6; linha++){
			    for (int coluna=2; coluna<7; coluna++){
			         estado.assumirValor(linha,coluna,vencedor.informarSimbolo());	
			    }
			    }
		}
		
					
		return (estado);
		
		};
	
	 
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
		int a;
		boolean vencedor = false;
		a=jogador1.getPonto();
		if(a > 20){	vencedor=true;
			jogador1.tornarSeVencedor();
		}
		a=jogador2.getPonto();
		if( a > 20){
			vencedor=true;
			jogador2.tornarSeVencedor();
		}
		this.setCartaE(cartaPop.getNumero());
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
		} else {if (jogador2.informarVencedor()) {
			return jogador2;
		} else {
			return null;
		}
	}
			
	}
	
	public int informarCartaSorteada(){
		int x;
		
		if ((this.recuperarPosicao(3, 1)).informarCarta()!= null){
   		x=this.recuperarPosicao(3,1).informarNumero();
   		return x;
   }else{
		x=21;
		return x;}
	}
	public boolean informarExistenciaJogadores() {
		return ((jogador1 != null)&& (jogador1 != null));
	}

	 
	public void iniciar() {
		monte = new Baralho();
	//	lixo = new Baralho();
		monte.inicializarCartas();
		monte.embaralhar();
		cartaPop=monte.comprar();
		this.setCartaE(cartaPop.getNumero());
		this.setRedeFalse();		
		for (int linha=1; linha<6; linha++){
			for (int coluna=1; coluna<7; coluna++){
				posicoes[(linha-1)][(coluna-1)] = new Posicao();
				posicoes[(linha-1)][(coluna-1)].esvaziar();				
			}
		}

	}
	 
	public Jogador getJogador1() {
		return jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}
}
 
