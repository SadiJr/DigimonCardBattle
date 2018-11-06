package enums;
public enum Phase {
	DRAW_PHASE("Fase de Descarte e Compra"),
	DIGIVOLVE_PHASE("Fase de Evolução"),
	BATTLE_PHASE("Fase de Pré Batalha"),
	START_GAME("Iniciando o Jogo"),
	QUIT("Saindo do Jogo");
	
	private String description;
	
	Phase(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}