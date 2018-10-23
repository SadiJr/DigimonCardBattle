import java.io.Serializable;

public class EntPlacar implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private int placar;

	public EntPlacar(){
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPlacar() {
		return placar;
	}
	public void setPlacar(int placar) {
		this.placar = placar;
	}
}
