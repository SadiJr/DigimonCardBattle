package app;

public class Player {
 
	protected int score;
	protected int lifes = 3;
	protected boolean have_trophy;
	protected int acessories = 0;
		 
	public int updateScore(int value) {
		this.score+=value;
		return this.score;
	}
	 
	public int addLife(int num) {
		return 0;
	}
	 
	public void gotTrophy(boolean got) {
		this.have_trophy = got;
	}
	public boolean hasTrophy(){
		return this.have_trophy;
	}
	public void setAcessories(int acessories) {
		if (acessories !=0)	
			this.acessories+=acessories;
		else
			this.acessories=0;
	}

	public boolean hasJetPack() {
		return this.acessories >=2;
	}
	
	public boolean hasGun() {
		return this.acessories%2 ==0;
	}

	public void updateLife(int life) {
		if (life>0)
			this.lifes++;
		else
			this.lifes--;
	}

	public int getLifes() {
		return this.lifes;
	}
}
 
