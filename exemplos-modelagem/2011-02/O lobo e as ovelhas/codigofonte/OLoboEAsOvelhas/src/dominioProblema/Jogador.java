	package dominioProblema;

public class Jogador {

	protected int tipo;//1 - Lobo e 2 - Ovelha
	 
	protected boolean daVez;
	 
	protected boolean vencedor;
	
	protected String nome;
	
	public Jogador(){
		
	}
	
	public void habilitar() {
		daVez = true;
	}
	
	public void iniciar(String nome, int tipo) {
		this.tipo = tipo;
		daVez = false;
		vencedor = false;
		this.nome = nome;
	}
	
	public void reiniciar() {
		daVez = false;
		vencedor = false;
	}
	
	public void desabilitar() {
		daVez = false;
	}
	
	public boolean informarDaVez() {
		return daVez;
	}

	public String informarNome() {
		return nome;
	}
	
	public int informarTipo(){
		return tipo;
	}
	
	public void assumirVencedor(){
		vencedor = true;
	}
	
	public boolean informarVencedor(){
		return vencedor;
	}

}
