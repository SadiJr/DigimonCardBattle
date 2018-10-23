package app.data;


public class Score {
	protected String[][] scores;
	protected int last = 0;
	protected ActorFile actor; 
	public Score(){
		this.actor = new ActorFile();
		this.scores =  this.actor.readFile();
		this.last = this.actor.getLastRead();
	}
	public void addScore(String name, String score) {
		if(this.last>9 && Integer.parseInt(score)>Integer.parseInt(this.scores[1][this.last-1])){
			this.scores[0][this.last-1] = name;
			this.scores[1][this.last-1] = score;
		}else if(this.last<=9){
			this.scores[0][this.last] = name;
			this.scores[1][this.last] = score;
			this.last++;
		}
		this.sortArray();
		this.actor.saveScore(this.scores, this.last);
	}
	public void sortArray(){
		for(int j = 0; j<this.last-1; j++)
			for(int i = 0; i< this.last-1; i++){
				int v1 = Integer.parseInt(this.scores[1][i]);
				int v2 = Integer.parseInt(this.scores[1][i+1]); 
				if(v2>v1){
					String auxS = this.scores[0][i];
					String auxI = this.scores[1][i];
					this.scores[0][i] = this.scores[0][i+1];
					this.scores[1][i] = this.scores[1][i+1];
					this.scores[0][i+1] = auxS;
					this.scores[1][i+1] = auxI;
				}		
			}
		
	}
	public String[][] getScores() {
		return this.scores;
	}
	public int getLast() {
		return this.last;
	}
}
