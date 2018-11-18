package enums;
public enum Effect {
	ATK300("<html>+300 de dano em todos os ataques</html>"),
	ATK500("<html>+500 de dano em todos os ataques</html>"),
	HP300("<html>+300 de HP</html>"),
	HP500("<html>+500 de HP</html>"),
	ATK1_100("<html>+100 de dano no Ataque 1</html>"),
	C_ATK400("<html>+400 de dano em todos os ataques, caso a carta seja nível C</html>"),
	U_ATK400("<html>+400 de dano em todos os ataques, caso a carta seja nível U</html>"),
	HP1000("<html>+1000 de HP</html>"),
	HP1_500_HP2_200("<html>+500 de HP e +200 de HP para o adversário</html>"),
	ATK1_X2("<html>Dano do Ataque 1 multiplicado por 2</html>"),
	ATK2_X2("<html>Dano do Ataque 2 multiplicado por 2</html>"),
	ATK3_X2("<html>Dano do Ataque 3 multiplicado por 2</html>");
	
	private String description;
	
	Effect(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}