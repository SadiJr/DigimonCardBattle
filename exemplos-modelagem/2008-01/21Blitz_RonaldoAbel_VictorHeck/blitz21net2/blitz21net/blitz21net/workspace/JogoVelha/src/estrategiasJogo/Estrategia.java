package estrategiasJogo;
import dominioProblema.ImagemDeTabuleiro;
import dominioProblema.Lance;

public abstract class Estrategia {
 
	protected int ordemLance = 0;
	 
	protected int caminho = 0;
	 
	public abstract Lance definirLance(ImagemDeTabuleiro estado);
	public abstract Lance lance1(ImagemDeTabuleiro estado);
	public abstract Lance lance2(ImagemDeTabuleiro estado);
	
	public Lance informarPrimeiroLance() {
		Lance lance;
		lance = new Lance();
		lance.assumir(2,3);
		return lance;
	}
	public int valorPosicao(int t){
		  int p;
		    if (t==0){p=0;}else{
		    p=t%13;
			if (p==0||p>10)p=10;}
	      return p;
	}
	public int cartaSorteada(ImagemDeTabuleiro estado){
		return valorPosicao(estado.informarValor(3,1));
	}
	public Lance lance3(ImagemDeTabuleiro estado) {
		Lance lance;
		lance = this.lanceBasico(estado);
		return lance;
	}
	 
	public Lance lance4(ImagemDeTabuleiro estado) {
		Lance lance;
		lance = this.lanceBasico(estado);
		return lance;
	}
	 
	public Lance lanceBasico(ImagemDeTabuleiro estado) {
		ordemLance++;
		Lance lance;
		lance = this.buscarVitoria(estado);
		if (lance.informarLinha() == 0) {
			lance = this.evitarDerrota(estado);
			if (lance.informarLinha() == 0) {
				lance = this.lanceQualquer(estado);
			};
		};
		return lance;
	}
public boolean tcv(ImagemDeTabuleiro estado, int linha){
		
		int contVazia = 0;
		
			for (int coluna=2; coluna<6; coluna++){
					if (estado.informarPosicaoVazia(linha, coluna)) {
						contVazia = contVazia+1;}
					}	
			if (contVazia>1)
				return true;
			   else 
		       return false;
			}
public boolean tlv(ImagemDeTabuleiro estado, int coluna){
	
	int contVazia = 0;
	
		for (int linha=1; linha<5; linha++){
				if (estado.informarPosicaoVazia(linha, coluna)) {
					contVazia = contVazia+1;}
				}	
		if (contVazia>1)
			return true;
		   else 
	       return false;
		}
	

	public int contaColunaVazia(ImagemDeTabuleiro estado, int linha){
		
		int contVazia = 0;
		
			for (int coluna=2; coluna<5; coluna++){
					if (estado.informarPosicaoVazia(linha, coluna)) {
						contVazia = contVazia+1;}
					}	
		return contVazia;
			}
					
		public int somaColuna(ImagemDeTabuleiro estado, int linha){
			
			int lance;
			
			int somaColuna = 0;
				for (int coluna=2; coluna<5; coluna++){
						  lance=this.valorPosicao(estado.informarValor(linha, coluna)); 
							somaColuna = somaColuna+lance;
						}	
			return somaColuna;			
				
	 }
public boolean e21(ImagemDeTabuleiro estado, int linha){
			
			int lance;
			lance=0;
			int somaColuna = 0;
				for (int coluna=2; coluna<6; coluna++){
						  lance=this.valorPosicao(estado.informarValor(linha, coluna)); 
							somaColuna = somaColuna+lance;
						}
				somaColuna=somaColuna+this.cartaSorteada(estado);
				if (somaColuna==21){return true;}else return false;
						
				
	 }
public boolean e21lm(ImagemDeTabuleiro estado, int linha){
	
	int lance;
	
	int somaColuna = 0;
		for (int coluna=2; coluna<6; coluna++){
				  lance=this.valorPosicao(estado.informarValor(linha, coluna)); 
					somaColuna = somaColuna+lance;
				}
		somaColuna=somaColuna+this.cartaSorteada(estado);
		if (somaColuna<=21){return true;}else return false;
				
		
}
public boolean e21c(ImagemDeTabuleiro estado, int coluna){
	
	int lance;
	
	int somalinha = 0;
		for (int linha=1; linha<5; linha++){
				  lance=this.valorPosicao(estado.informarValor(linha, coluna)); 
					somalinha = somalinha+lance;
				}
		somalinha=somalinha+this.cartaSorteada(estado);
		if (somalinha==21){return true;}else return false;
				
		
}
public boolean e21cm(ImagemDeTabuleiro estado, int coluna){
	
	int lance;
	
	int somalinha = 0;
		for (int linha=1; linha<5; linha++){
				  lance=this.valorPosicao(estado.informarValor(linha, coluna)); 
					somalinha = somalinha+lance;
				}
		somalinha=somalinha+this.cartaSorteada(estado);
		if (somalinha<=21){return true;}else return false;
				
		
}


public boolean emenor21(ImagemDeTabuleiro estado, int linha){
	
	int lance;
	
	int somaColuna = 0;
		for (int coluna=2; coluna<6; coluna++){
				  lance=this.valorPosicao(estado.informarValor(linha, coluna)); 
					somaColuna = somaColuna+lance;
				}
		somaColuna=somaColuna+this.cartaSorteada(estado);
		if (somaColuna<21){return true;}else return false;
				
		
}
   public Lance lancelinha(ImagemDeTabuleiro estado, int linha){
	   Lance lance;
	   lance =new Lance();
	   for (int i=2;i<6;i++){if(estado.informarValor(linha,i)==0){
			lance.assumir(linha,i);
			//lp=false;
			//lv=true;
			return lance;};
		}
	   
	   return lance;
   }
   public Lance lancecoluna(ImagemDeTabuleiro estado, int coluna){
	   Lance lance;
	   lance =new Lance();
	   for (int i=1;i<5;i++){if(estado.informarValor(i,coluna)==0){
			lance.assumir(i,coluna);
			//lp=false;
			//lv=true;
			return lance;};
		}
	   
	   return lance;
   }
	 	 
	
	public Lance avaliarLinhaPreenchivel (ImagemDeTabuleiro estado, int simbolo){
		Lance lance;
		int contSimbolo = 0;
		int linhaDesoc = 0;
		int colunaDesoc = 0;
		lance = new Lance();
		for (int linha=1; linha<5; linha++){
			if (estado.ocupadasNaLinha(linha) == 4) {
				contSimbolo = 0;
				linhaDesoc = 0;
				colunaDesoc = 0;
				for (int coluna=2; coluna<5; coluna++){
					if (estado.informarPosicaoVazia(linha, coluna)) {
						linhaDesoc = linha;
						colunaDesoc = coluna;
					} else {
						if (estado.informarValor(linha, coluna) == simbolo) {
							contSimbolo++;
						}
					};
				};
				if (contSimbolo == 4 ) {
					lance.assumir(linhaDesoc, colunaDesoc);
					return lance;
				};
			};
		};
		return lance;
	}
	
	public Lance avaliarColunaPreenchivel (ImagemDeTabuleiro estado, int simbolo){
		Lance lance;
		int contSimbolo = 0;
		int linhaDesoc = 0;
		int colunaDesoc = 0;
		lance = new Lance();
		for (int coluna=2; coluna<5; coluna++){
			if (estado.ocupadasNaColuna(coluna) == 5) {
				contSimbolo = 0;
				linhaDesoc = 0;
				colunaDesoc = 0;
				for (int linha=1; linha<5; linha++){
					if (estado.informarPosicaoVazia(linha, coluna)) {
						linhaDesoc = linha;
						colunaDesoc = coluna;
					} else {
						if (estado.informarValor(linha, coluna) == simbolo) {
							contSimbolo++;
						}
					};
				};
				if (contSimbolo == 2 ) {
					lance.assumir(linhaDesoc, colunaDesoc);
					return lance;
				};
			};
		};
		return lance;
	}

	public Lance avaliarDiagonalPrincipalPreenchivel (ImagemDeTabuleiro estado, int simbolo){
		Lance lance;
		int contSimbolo = 0;
		int linhaDesoc = 0;
		int colunaDesoc = 0;
		lance = new Lance();
		if (estado.informarPosicaoVazia(1, 2)) {
			linhaDesoc = 1;
			colunaDesoc = 1;
		} else {
			if (estado.informarValor(1, 1) == simbolo) {
				contSimbolo++;
			}
		};
		if (estado.informarPosicaoVazia(2, 2)) {
			linhaDesoc = 2;
			colunaDesoc = 2;
		} else {
			if (estado.informarValor(2, 2) == simbolo) {
				contSimbolo++;
			}
		};
		if (estado.informarPosicaoVazia(3, 3)) {
			linhaDesoc = 3;
			colunaDesoc = 3;
		} else {
			if (estado.informarValor(3, 3) == simbolo) {
				contSimbolo++;
			}
		};
		if (contSimbolo == 2 ) {
			lance.assumir(linhaDesoc, colunaDesoc);
			return lance;
		};
		return lance;
	}

	public Lance avaliarDiagonalSecundariaPreenchivel (ImagemDeTabuleiro estado, int simbolo){
		Lance lance;
		int contSimbolo = 0;
		int linhaDesoc = 0;
		int colunaDesoc = 0;
		lance = new Lance();
		if (estado.informarPosicaoVazia(3, 2)) {
			linhaDesoc = 3;
			colunaDesoc = 1;
		} else {
			if (estado.informarValor(3, 3) == simbolo) {
				contSimbolo++;
			}
		};
		if (estado.informarPosicaoVazia(2, 2)) {
			linhaDesoc = 2;
			colunaDesoc = 2;
		} else {
			if (estado.informarValor(2, 2) == simbolo) {
				contSimbolo++;
			}
		};
		if (estado.informarPosicaoVazia(1, 3)) {
			linhaDesoc = 1;
			colunaDesoc = 3;
		} else {
			if (estado.informarValor(1, 3) == simbolo) {
				contSimbolo++;
			}
		};
		if (contSimbolo == 2 ) {
			lance.assumir(linhaDesoc, colunaDesoc);
			return lance;
		};
		return lance;
	}
	
	public Lance buscarVitoria(ImagemDeTabuleiro estado) {
		Lance lance, primeiroLance;
		int meuSimbolo;
		primeiroLance = this.informarPrimeiroLance();
		meuSimbolo = estado.informarValor((primeiroLance.informarLinha()), (primeiroLance.informarColuna()));
		// varredura de linhas
		lance = this.avaliarLinhaPreenchivel(estado, meuSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		// varredura de colunas
		lance = this.avaliarColunaPreenchivel(estado, meuSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		// diagonal principal
		lance = this.avaliarDiagonalPrincipalPreenchivel(estado, meuSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		// diagonal secundaria
		lance = this.avaliarDiagonalSecundariaPreenchivel(estado, meuSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		return lance;
	}
	 
	public Lance evitarDerrota(ImagemDeTabuleiro estado) {
		Lance lance, primeiroLance;
		int meuSimbolo;
		int outroSimbolo = 1000;
		lance = new Lance();
		primeiroLance = this.informarPrimeiroLance();
		meuSimbolo = estado.informarValor((primeiroLance.informarLinha()), (primeiroLance.informarColuna()));
		if (meuSimbolo == 1000){
			outroSimbolo = 2000;
		};
		// varredura de linhas
		lance = this.avaliarLinhaPreenchivel(estado, outroSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		// varredura de colunas
		lance = this.avaliarColunaPreenchivel(estado, outroSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		// diagonal principal
		lance = this.avaliarDiagonalPrincipalPreenchivel(estado, outroSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		// diagonal secundaria
		lance = this.avaliarDiagonalSecundariaPreenchivel(estado, outroSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		return lance;
	}
	 
	public Lance lanceQualquer(ImagemDeTabuleiro estado) {
		Lance lance = null;
		for (int linha=1; linha<5; linha++){
			for (int coluna=1; coluna<5; coluna++){
				if (estado.informarPosicaoVazia(linha, coluna)) {
					lance = new Lance();
					lance.assumir(linha, coluna);
					return lance;
				};
			};
		};
		return lance;
	}
	 
}
 
