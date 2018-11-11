package enums;
public enum Effect {
	ATK300("+300 de dano em todos os ataques"),
	ATK500("+500 de dano em todos os ataques"),
	HP300("+300 de HP"),
	HP500("+500 de HP"),
	ATK1_100("+100 de dano no Ataque 1"),
	C_ATK400("+400 de dano em todos os ataques, caso a carta seja nível C"),
	U_ATK400("+400 de dano em todos os ataques, caso a carta seja nível U"),
	HP1000("+1000 de HP"),
	HP1_500_HP2_200("+500 de HP e +200 de HP para o adversário"),
	ATK1_X2("Dano do Ataque 1 multiplicado por 2"),
	ATK2_X2("Dano do Ataque 2 multiplicado por 2"),
	ATK3_X2("Dano do Ataque 3 multiplicado por 2");
	
	private String description;
	
	Effect(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}