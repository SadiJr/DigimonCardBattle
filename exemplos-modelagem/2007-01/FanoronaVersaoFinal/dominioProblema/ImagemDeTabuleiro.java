package dominioProblema;

public class ImagemDeTabuleiro {
	
	protected String mensagem;
	 
	protected int mapa[][] = new int [5][9];
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String texto) {
		mensagem = texto;
	}	 

	public void setValor(int linha, int coluna, int valor) {
		mapa [(linha-1)][(coluna-1)] = valor;
	}
	
	public int getValor(int linha, int coluna) {
		int retorno;
		retorno = mapa [(linha-1)][(coluna-1)];
		return (retorno);
	}
}
