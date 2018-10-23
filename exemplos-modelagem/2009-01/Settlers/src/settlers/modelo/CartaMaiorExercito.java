package settlers.modelo;

public class CartaMaiorExercito extends Carta {
	
	public void setJogador(Jogador jogador) {
		if (getJogador() != null) {
			getJogador().setCartaMaiorExercito(null);
		}
		jogador.setCartaMaiorExercito(this);
		super.setJogador(jogador);
	}

}
