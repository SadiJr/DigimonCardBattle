package dominioProblema;

public class ImagemDeTabuleiro {
 
	protected String mensagem1;
	protected String mensagem2;
	 
	protected int espelhoTabuleiro[][] = new int [10][10];
	 
	public String informarMensagem1() {
		return mensagem1;
	}
	
	public String informarMensagem2() {
		return mensagem2;
	}
	 
	public int informarValor(int linha, int coluna) {
		int retorno;
		retorno = espelhoTabuleiro [(linha-1)][(coluna-1)];
		return (retorno);
	}
	 
	public void assumirMensagem1(String mensagem) {
		mensagem1 = mensagem;
	}
	
	public void assumirMensagem2(String texto) {
		mensagem2 = texto;
	}
	 
	public void assumirValor(int linha, int coluna, int valor) {
		espelhoTabuleiro [(linha-1)][(coluna-1)] = valor;		
	}
}