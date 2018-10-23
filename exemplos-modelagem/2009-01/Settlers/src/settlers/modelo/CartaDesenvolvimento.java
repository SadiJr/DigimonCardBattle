package settlers.modelo;

public class CartaDesenvolvimento extends Carta {

	public static final int CARTA_CONSTRUCAO_ESTRADA = 0;
	public static final int CARTA_ANO_FARTURA        = 1;
	public static final int CARTA_MONOPOLIO          = 2;
	public static final int CARTA_SOLDADO            = 3;
	public static final int CARTA_PONTO_VITORIA      = 4;
	
	private int tipo;
	
	public CartaDesenvolvimento(int tipo) {
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}

}
