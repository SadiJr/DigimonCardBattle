package model;
public class CardPOJO {

	private int hp;
	private int attack1;
	private int attack2;
	private int attack3;
	private int dp;
	private int p;
	private String specialty;
	private char level;
	private String name;
	private String effect;
	private String description;
	private boolean optionCard;
	private String path;
	private String phase;
	
	public CardPOJO(int hp, int attack1, int attack2, int attack3, int dp, int p, String specialty,
			char level, String name, String effect, String description, String path, String phase) {
		this.hp = hp;
		this.attack1 = attack1;
		this.attack2 = attack2;
		this.attack3 = attack3;
		this.dp = dp;
		this.p = p;
		this.specialty = specialty;
		this.level = level;
		this.name = name;
		this.effect = effect;
		this.description = description;
		this.optionCard = false;
		this.path = path;
		this.phase = phase;
	}

	public int getHp() {
		return this.hp;
	}
	
	public CardPOJO(String name, String effect, String description, String path, String phase) {
		this.name = name;
		this.effect = effect;
		this.description = description;
		this.optionCard = true;
		this.path = path;
		this.phase = phase;
	}

	/**
	 * 
	 * @param hp
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack1() {
		return this.attack1;
	}

	/**
	 * 
	 * @param attack1
	 */
	public void setAttack1(int attack1) {
		this.attack1 = attack1;
	}

	public int getAttack2() {
		return this.attack2;
	}

	/**
	 * 
	 * @param attack2
	 */
	public void setAttack2(int attack2) {
		this.attack2 = attack2;
	}

	public int getAttack3() {
		return this.attack3;
	}

	/**
	 * 
	 * @param attack3
	 */
	public void setAttack3(int attack3) {
		this.attack3 = attack3;
	}

	public int getDp() {
		return this.dp;
	}

	/**
	 * 
	 * @param dp
	 */
	public void setDp(int dp) {
		this.dp = dp;
	}

	public int getP() {
		return this.p;
	}

	/**
	 * 
	 * @param p
	 */
	public void setP(int p) {
		this.p = p;
	}

	public String getSpecialty() {
		return this.specialty;
	}

	/**
	 * 
	 * @param specialty
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public char getLevel() {
		return this.level;
	}

	/**
	 * 
	 * @param level
	 */
	public void setLevel(char level) {
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getEffect() {
		return this.effect;
	}

	/**
	 * 
	 * @param effect
	 */
	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isOptionCard() {
		return this.optionCard;
	}

	/**
	 * 
	 * @param optionCard
	 */
	public void setOptionCard(boolean optionCard) {
		this.optionCard = optionCard;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

}