public class Posicao {

	protected int formato;

	protected Peca ocupante = null;

	public Posicao(int vFormato){
		formato = vFormato;	
	}

	public void setOcupante(Peca vOcupante) {
		ocupante = vOcupante;
	}

	public boolean estaVazia() {
		return ocupante == null;
	}

	public Peca getOcupante() {
		return ocupante;
	}
	
	public int getFormato(){
		return this.formato;
	}

}
