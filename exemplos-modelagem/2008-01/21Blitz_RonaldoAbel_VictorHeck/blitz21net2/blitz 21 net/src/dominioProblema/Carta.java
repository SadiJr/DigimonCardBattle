package dominioProblema;
public class Carta {

	/**
    * Atributos da Cartas.
   */
	private int numero;
	private char naipe;
	private String imagem;  
	public static final char PAUS = 'P';
	public static final char COPAS = 'C';
	public static final char OUROS = 'O';
	public static final char ESPADAS = 'E';

//------------------------------------------------------------------------------------
	
	/**
	 * Construtor da Classe Cartas.
	 * @param pNumero = numero da carta
	 * @param pNaipe = naipe da carta
	 */
	public Carta(){
		numero=10;
	}
	public Carta(int pNumero) {
		numero = pNumero;
				
	}
	public void setNumero( int p){
		numero=p;
	}
	/**
	 * @return Retorna o naipe da carta
	 */
	public char getNaipe() {
		return naipe;
	}

	/**
	 * @return Retorna o número da carta
	 */
	public int getNumero() {
		return numero;
	}
}