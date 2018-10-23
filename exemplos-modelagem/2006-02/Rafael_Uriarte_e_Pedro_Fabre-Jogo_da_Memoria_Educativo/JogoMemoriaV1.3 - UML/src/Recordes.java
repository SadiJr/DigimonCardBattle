import java.io.Serializable;

public class Recordes implements Serializable{
 
	private int[] listaJogadasRecordistas;
	 
	private String[] listaNomesRecordistas;
	 
	private String caminhoArquivoRecordes;
	
	public Recordes(){

	}
	//Contrutor usado para criar os arquivos padroes de sistema
	public Recordes(int nivel){
		caminhoArquivoRecordes = "sistema/Recordes"+nivel;
		listaNomesRecordistas = new String[] {"Computador","Computador","Computador","Computador","Computador","Computador","Computador","Computador","Computador","Computador"};
		if ( nivel == 1){
			listaJogadasRecordistas = new int [] {27,26,25,22,20,17,15,13,10,8};
		}else{
			listaJogadasRecordistas = new int [] {37,35,33,30,28,25,22,18,15};			
		}
	}
	
	/**
	 *Recebe como parametro o numero de jogadas e verifica se foi recorde
	 *(quanto menos jogadas melhor)
	 */
	public boolean verificarseFoiRecorde(int numeroJogadas) {
		for ( int contador = 0; contador < listaJogadasRecordistas.length; contador++){
			if ( numeroJogadas < listaJogadasRecordistas[contador] ){
				return true;
			}
		}
		return false;
	}
	 
	/**
	 *Usado para gravar um novo recordista na lista de recordistas
	 *
	 */
	public void gravarRecordista(String nome, int numeroJogadas) {
		int posicaoRecorde = -1;
		for ( int contador = 0; contador < listaJogadasRecordistas.length; contador++){
				if ( numeroJogadas < listaJogadasRecordistas[contador] ){
					posicaoRecorde = contador;
				}
		}
		reorganizarLista(posicaoRecorde);
		listaJogadasRecordistas[posicaoRecorde] = numeroJogadas;
		listaNomesRecordistas[posicaoRecorde] = nome;
	}
	 
	private void reorganizarLista(int posicaoRecorde) {
		for ( int contador = 0; contador < posicaoRecorde; contador++ ){
			listaJogadasRecordistas[contador] = listaJogadasRecordistas[contador+1];
			listaNomesRecordistas[contador] = listaNomesRecordistas[contador+1];
		}	
	}
	 
	public String getListaRecordistas() {
		int x = 1;
		String listaRecordistas = "Os 10 maiores recordistas sao: \n";
		
		for ( int contador = (listaJogadasRecordistas.length-1); contador >=0 ;contador--){
        	listaRecordistas += "\n"+x+"° "+listaNomesRecordistas[contador]+" com " +listaJogadasRecordistas[contador]+" jogadas.";
                x++;
        	}
        return listaRecordistas;
	}
	 
	public String getCaminhoArquivoRecordes() {
		return caminhoArquivoRecordes;
	}
	 
}
 
