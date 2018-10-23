import java.io.*;

public class Jogador implements Serializable{

	protected static final long serialVersionUID = -6552373120091030226L;

	protected String nome = "";

	protected QuebraCabeca[] quebraCabecas;

	/**
	 * Construtor
	 * Seta o nome e criar os quebra-cabeças associoados ao jogador
	 * @param nome Nome do jogador
	 * @throws Exception
	 */
	public Jogador(String nome) throws Exception{
		setNome(nome);
		quebraCabecas = new QuebraCabeca[50];
		for(int i = 0; i < 50; i++){
			quebraCabecas[i] = new QuebraCabeca((byte)(i+1));
		}
	}

	/**
	 * Informa os quebra-cabeças do jogador
	 * @return Array de QuebraCabeca
	 */
	public QuebraCabeca[] informarQuebraCabecas() {
		return quebraCabecas;
	}

	
	/**
	 * Informa o nome do jogador
	 * @return Nome do jogador
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setar o nome do jogador
	 * @param value Nome do jogador
	 * @throws Exception Caso o nome seja uma String vazia
	 */
	public void setNome(String value) throws Exception{
		if(value.equals(""))
			throw new Exception("O nome deve ser preenchido");
		nome = value;
	}
	
	/**
	 * Informa o número de partidas que o jogador jogou
	 * @return Número de partidas
	 */
	public int partidasJogadas(){
		int valor = 0;
		for(int i= 0; i < this.quebraCabecas.length; i++){
			valor += this.quebraCabecas[i].numTentativas();
		}
		return valor;			
	}
	
	/**
	 * Informa o número de quebra-cabeças que o jogador finalizou
	 * @return Número de quebra-cabeças finalizados
	 */
	public int quebraCabecasFinalizados(){
		int valor = 0;
		for(int i= 0; i < this.quebraCabecas.length; i++){
			if(this.quebraCabecas[i].getFinalizado()){
				valor++;
			}
		}
		return valor;				
	}
	
	/**
	 * Retorna o indice de aproveitamento do jogador
	 * que é o percentual do número de quebra-cabeças finalizados 
	 * pelo número de partidas jogadas
	 * @return Percentual do índice de aproveitamento
	 */
	public int indiceDeAproveitamento(){
		int indice = 0;
		try{
			indice = 100 * this.quebraCabecasFinalizados() / this.partidasJogadas();
		} catch (Exception e){
			indice = 0;
		}		
		return indice; 
	}
}

