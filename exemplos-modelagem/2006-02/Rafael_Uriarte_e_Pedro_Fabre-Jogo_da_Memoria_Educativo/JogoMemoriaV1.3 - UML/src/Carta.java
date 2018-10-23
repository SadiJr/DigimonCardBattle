public class Carta {
 
	public Carta(int numero){
		caminhoArquivos[0] = "imagens/imagem" + numero;
		caminhoArquivos[1] = "dicas/dica" + numero;
		if ( numero%2 == 0 ){
			caminhoArquivos[2] = "textos/texto" + numero;
		}else{
			caminhoArquivos[2] = "textos/texto" + (numero-1);
		}
		caminhoArquivos[3] = "nomes/nome" + numero;
	}
	/*CaminhosFigura: 0 = caminho figura
	 * 				  1 = caminho dicas
	 * 				  2 = caminho texto
	 */
	private String [] caminhoArquivos = new String[4];
	 
	private Carta par;
	 
	public String[] getCaminhoArquivos() {
		return caminhoArquivos;
	}
	 
	public Carta getPar() {
		return par;
	}
	 
	public void setPar(Carta carta){
		par = carta;
	}
}
 
