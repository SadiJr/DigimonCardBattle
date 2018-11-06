package model;

import enums.Effect;
import enums.Level;
import enums.Specialty;

public class DigimonCard extends Card {

	private int hp;
	private int attack1;
	private int attack2;
	private int attack3;
	private int dp;
	private int p;
	private Specialty specialty;
	private Level level;
	
	public DigimonCard(String name, Effect effect, int hp, int attack1, int attack2, int attack3,
			int dp, int p, Specialty specialty, Level level) {
		super(name, effect);
		this.hp = hp;
		this.attack1 = attack1;
		this.attack2 = attack2;
		this.attack3 = attack3;
		this.dp = dp;
		this.p = p;
		this.specialty = specialty;
		this.level = level;
	}

	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack1() {
		return this.attack1;
	}

	public void setAttack1(int attack1) {
		this.attack1 = attack1;
	}

	public int getAttack2() {
		return this.attack2;
	}

	public void setAttack2(int attack2) {
		this.attack2 = attack2;
	}

	public int getAttack3() {
		return this.attack3;
	}

	public void setAttack3(int attack3) {
		this.attack3 = attack3;
	}

	public int getDp() {
		return this.dp;
	}

	public void setDp(int dp) {
		this.dp = dp;
	}

	public int getP() {
		return this.p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public Specialty getSpecialty() {
		return this.specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public Level getLevel() {
		return this.level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}