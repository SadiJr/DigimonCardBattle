
package dominioProblema;

public class ImagemDeTabuleiro {
 
	protected String mensagem,m11,m46,m36,m26,m16,m52,m53,m54,m56,m55;
	 
	protected int mapa[][] = new int [5][6];
	 
	public String informarMensagem() {
		return mensagem;
	}
	public String getM11() {
		return m11;
	}
	public String getM56() {
		return m56;
	}
	public String getM55() {
		return m55;
	}
	public String getM54() {
		return m54;
	}
	public String getM53() {
		return m53;
	}
	public String getM52() {
		return m52;
	}
	public String getM16() {
		return m16;
	}
	public String getM26() {
		return m26;
	}
	public String getM36() {
		return m36;
	}
	public String getM46() {
		return m46;
	}
	 
	public int informarValor(int linha, int coluna) {
		int retorno;
		retorno = mapa [(linha-1)][(coluna-1)];
		return (retorno);
	}
	
	 
	public void assumirMensagem(String texto) {
		mensagem = texto;
	}
	public void setM11(String texto) {
		m11 = texto;
	}
	public void setM56(String texto) {
		m56 = texto;
	}
	public void setM55(String texto) {
		m55 = texto;
	}
	public void setM54(String texto) {
		m54 = texto;
	}
	public void setM53(String texto) {
		m53 = texto;
	}
	public void setM52(String texto) {
		m52 = texto;
	}
	public void setM16(String texto) {
		m16 = texto;
	}
	public void setM26(String texto) {
		m26 = texto;
	}
	public void setM36(String texto) {
		m36 = texto;
	}
	public void setM46(String texto) {
		m46 = texto;
	}
	
	
	 
	public void assumirValor(int linha, int coluna, int valor) {
		mapa [(linha-1)][(coluna-1)] = valor;
		
	}
	 
	public boolean informarVazio() {
		boolean vazio = true;
		for (int linha=1; linha<6; linha++){
			for (int coluna=2; coluna<6; coluna++){
				if (this.informarPosicaoOcupada(linha, coluna)) {
					vazio = false;
				};
			};
		};
		return vazio;
	}
	 
	public boolean informarCentroVazio() {
		return (this.informarPosicaoVazia(2, 2));
	}
	 
	public boolean informarPosicaoVazia(int linha, int coluna) {
		return ((this.informarValor(linha, coluna)) == 0);
	}
	 
	public boolean informarPosicaoOcupada(int linha, int coluna) {
		return ((this.informarValor(linha, coluna)) != 0);
		
	}
	 
	public int ocupadasNaLinha(int ordem) {
		int cont = 0;
		for (int coluna=2; coluna<6; coluna++){
			if (this.informarPosicaoOcupada(ordem, coluna)) {
				cont++;
			};
		};
		return cont;
	}
	 
	public int ocupadasNaColuna(int ordem) {
		int cont = 0;
		for (int linha=1; linha<5; linha++){
			if (this.informarPosicaoOcupada(linha, ordem)) {
				cont++;
			};
		};
		return cont;
	}
	 
}


