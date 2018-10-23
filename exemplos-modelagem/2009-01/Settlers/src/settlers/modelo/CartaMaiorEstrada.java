package settlers.modelo;

public class CartaMaiorEstrada extends Carta {
	
	public void setJogador(Jogador jogador) {
		if (getJogador() != null) {
			getJogador().setCartaMaiorEstrada(null);
		}
		jogador.setCartaMaiorEstrada(this);
		super.setJogador(jogador);
	}

}
