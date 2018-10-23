import java.io.Serializable;


public class Nivel implements Serializable{
 
	public Nivel(){
		
	}
	//Contrutor usado para criar os arquivos padroes de sistema
	public Nivel(int nivel ){
		this.nivel = nivel;
	}
	private boolean nivelDiferente;

	private int nivel;
	 
	private String caminhoArquivoNivel = "sistema/Nivel";
	 
	public void setNovoNivel(int nivel) {
			this.nivel = nivel;	
		}
	
	 
	public int getNivel() {
		return nivel;
	}
	 
	/**
	 *Retorna o numero de pares, a resolução(primeiro a altura e depois a largura) e a localização da janela(primeiro a verdical e depois a horizontal)
	 *
	 */
	public int[] getInformacoes() {
		//Nivel 1 tem 6 pares
		if ( nivel == 1){
			int informacoes [] = new int[]{6,700,500,150,100};
			return informacoes;
		} //Nivel 2 tem 10 pares
		if ( nivel == 2){
			int informacoes [] = new int[]{10,900,630,50,50};
			return informacoes;
		}
		return null;
	}
	 
	public String getCaminhoArquivoNivel() {
		return caminhoArquivoNivel;
	}

	public boolean getNivelDiferente() {
		return nivelDiferente;
	}
	public void setNivelDiferente(boolean nivel2) {
		this.nivelDiferente = nivel2;
	}
}
 
