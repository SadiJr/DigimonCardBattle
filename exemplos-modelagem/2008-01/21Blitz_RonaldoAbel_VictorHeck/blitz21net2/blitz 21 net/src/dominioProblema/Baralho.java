package dominioProblema;
import java.util.*;
public class Baralho {
   
   /**
    * Atributos da Classe Baralho.
   */
	protected Stack cartas;

//-------------------------------------------------------------------------------------
	
   /**
	* M�todo construtor da Classe Baralho.
   */
   public Baralho() {
   		cartas = new Stack();
   }

   /**
	* M�todo que demonstra o ato de comprar cartas do ponto de vista do baralho.
	* @return retorna a carta comprada.	
   */
    public Carta comprar() {
    	if (cartas.isEmpty())
    		return null;
    	else
    		return (Carta)cartas.pop();	
	}

   /**
	* M�todo que demonstra o ato de descartar cartas do ponto de vista do baralho.
   */
	public void jogarFora(Carta pCarta) {
		cartas.push(pCarta);
	}
	
	/** M�todo para ver a carta de cima. Para ver a carta em cima do lixo, por exemplo.
	 * @return A carta de cima do baralho
	 */
	public Carta verCarta() {
		if (cartas.isEmpty())
			return null;
		else
			return (Carta)cartas.peek();		
	}
	
	/** Verifica se o baralho est� vazio
	 * @return True se vazio, false se n�o-vazio
	 */
	public boolean estaVazio(){
		return cartas.isEmpty();
	}
	
	/**
	 * Embaralha as cartas do monte... 
	 */
	public void embaralhar() {
		Collections.shuffle(cartas);
	}
	
	/**
	 * Inicializa as cartas do jogo.
	 */
	public void inicializarCartas(){
		for (int i = 1; i < 53; i++) {
			cartas.push(new Carta(i));
			
		}		
	}
}