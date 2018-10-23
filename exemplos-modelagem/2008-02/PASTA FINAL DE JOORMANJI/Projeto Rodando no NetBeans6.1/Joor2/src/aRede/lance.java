package aRede;

import br.ufsc.inf.leobr.cliente.Jogada;

public class lance implements Jogada {
	
	private int posicaoVez;
        private boolean tireiCinco;

	public lance(int posicaoVez, boolean tireiCinco) {
		super();
		this.posicaoVez = posicaoVez;
                this.tireiCinco = tireiCinco;
	}

	public int getLance() {
            return posicaoVez;
	}
        
        public boolean getTirouCinco() {
            return this.tireiCinco;
        }
	
	

}
