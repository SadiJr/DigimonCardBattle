package dominioProblema;

public class ImagemDeTabuleiro {
	
	protected int mapa[][] = new int [7][7];

	protected String mensagem;
	
	public ImagemDeTabuleiro(){
		for(int linha = 0; linha<7; linha++){
			for(int coluna = 0; coluna<7; coluna++){
				if((linha == 0)&&((coluna == 0)||(coluna == 1)||(coluna == 5)||(coluna == 6))){
					mapa[linha][coluna] = -1;
				}else if((linha == 1)&&((coluna == 0)||(coluna == 1)||(coluna == 5)||(coluna == 6))){
					mapa[linha][coluna] = -1;
				}else if((linha == 5)&&((coluna == 0)||(coluna == 1)||(coluna == 5)||(coluna == 6))){
					mapa[linha][coluna] = -1;
				}else if((linha == 6)&&((coluna == 0)||(coluna == 1)||(coluna == 5)||(coluna == 6))){
					mapa[linha][coluna] = -1;
				}else{
					
					if((linha == 5)&&((coluna == 2)||(coluna == 3)||(coluna == 4))){
						mapa[linha][coluna] = 2;
					}else if((linha == 6)&&((coluna == 2)||(coluna == 3)||(coluna == 4))){
						mapa[linha][coluna] = 2;
					}else if(linha == 4){
						mapa[linha][coluna] = 2;
					}else if(linha == 1 && coluna == 3){
						mapa[linha][coluna] = 1;
					}else{
						mapa[linha][coluna] = -1;
					}
				}
			}
		}
	}
	
	public String informarMensagem() {
		return mensagem;
	}

	public int informarValor(int linha, int coluna) {
		return mapa [linha][coluna]; 
	}

	public void assumirMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void assumirValor(int linha, int coluna, int valor) {
		mapa [linha][coluna] = valor;
	}

}
