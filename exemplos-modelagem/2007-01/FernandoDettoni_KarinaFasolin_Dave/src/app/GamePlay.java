package app;

import gui.Map;
import gui.PlayerActor;
import gui.sprites.Special;

import java.util.ArrayList;

import app.data.Score;

import com.genuts.gameui.Sprite;

public class GamePlay {
 
	protected static GamePlay instance = null;
	protected boolean isPaused, inHelp;
	protected Player player;
	protected PlayerActor playerActor;
	protected int actualLevel= 1;
	protected int actualScreen = 0;
	protected Map map;
	protected Levels levels;
	protected ArrayList<int[][]> actualMaps;
	protected int[][] actualMap;
	

	protected GamePlay(){
		this.playerActor = new PlayerActor();
		this.playerActor.addKeyListener(new Handler(this));
		this.playerActor.setVisible(true);
		this.playerActor.pack();
		this.levels = new Levels();
	}
	
	public static GamePlay getInstance(){
		if(instance==null)
			instance = new GamePlay();
		return instance;
	}
	
	public void startGame() {
		this.player = new Player();
		this.playerActor.startGame();
		this.map = this.playerActor.getMap();
		this.actualMaps = this.levels.getLevel(this.actualLevel);
		this.actualMap = this.actualMaps.get(this.actualScreen);
		this.map.makeMap(this.actualMap, this.levels.getValues());
		this.actualScreen++;
		this.map.update(this.map.getGraphics());
	}
	
	public void restartGame() {
		this.map.pause(true);
		boolean check = this.playerActor.sendMessage("Do you really want restart?", 1);
		if (check){
			this.player = new Player();
			this.levels = new Levels();
			this.actualLevel = 1;
			this.actualMaps = this.levels.getLevel(this.actualLevel);
			this.actualScreen = 0;
			this.actualMap = this.actualMaps.get(0);
			this.updateScreen();
			this.playerActor.updateScore(this.player.updateScore(0));
			this.playerActor.showMap();
			this.playerActor.updateLevelNumber(this.actualLevel);
			this.playerActor.updateLife(this.player.getLifes());
			this.playerActor.updateTrophy(false);
			this.playerActor.updateAcessories(0);
			this.playerActor.pack();
			this.map.pause(false);
			this.playerActor.restartHandler();
		}else
			this.map.pause(false);
	}
	 
	public void updateScore(int score) {
		int s = this.player.updateScore(score);
		this.playerActor.updateScore(s);
		this.playerActor.pack();
	}
	 

	public void explosion(){
		this.map.explosion();
		new Thread(new Runnable(){
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				GamePlay.this.updateLife(-1);
			}				
		}).start();
	}
	 
	public void updateLife(int life){
		this.player.updateLife(life);
		this.playerActor.updateLife(this.player.getLifes());
		this.playerActor.pack();
		if (this.player.getLifes()>=0)
			this.restartLevel();
		else if (this.player.getLifes()<0)
			this.endGame();
	}
	
	public void endGame() {
		Score s = new Score();
		String name = this.playerActor.sendInputMessage();
		s.addScore(name, new Integer(this.player.updateScore(0)).toString());
		this.playerActor.showScore(s.getScores());	
	}
	
	
	public void restartLevel() {
		this.actualScreen =0;
		this.updateScreen();
	}
	
	public void updateTrophy(boolean got) {
		if (got)
			this.player.gotTrophy(true);
		this.playerActor.updateTrophy(this.player.hasTrophy());
		this.playerActor.pack();
		
	}
	 
	public void updateLevel() {
			this.actualLevel++;
			this.actualScreen = 0;
			this.actualMaps = this.levels.getLevel(this.actualLevel);
			this.updateScreen();
			this.map.stopPlayerWalking();
			this.playerActor.pauseHandler(false);
			this.playerActor.updateLevelNumber(this.actualLevel);
			this.playerActor.pack();
	}
	 
	public void updateAcessories(int opt) {//1-jet 2-gun
		this.player.setAcessories(opt);
		this.playerActor.updateAcessories(opt);
	}
	 
	public void removeSprite(Sprite sprite) {
		assert(sprite instanceof Special);
		Special s = (Special)sprite;
		this.actualMap[s.getI()][s.getJ()] = 0;
		this.map.removeSprite(sprite);
	}
	
	public void movePlayerRight() {
		this.map.movePlayerRight();
	}

	public void movePlayerLeft() {
		this.map.movePlayerLeft();
	}
	public void stopPlayerWalking() {
		this.map.stopPlayerWalking();
	}
	public void movePlayerUp() {
		//if (player.hasJetPack())
		//	map.movePlayerFly();
	//	else 
	//		if(!jumping){
			this.map.movePlayerJump();
		//}
	}
	
	public void passLevel(){
		if(this.player.hasTrophy()){
			this.map.putSprites(this.levels.getLevel(10).get(0), this.levels.getValues(), false);
			this.map.movePlayerRight();
			this.player.gotTrophy(false);
			this.playerActor.updateScore(this.player.updateScore(1000));
			this.playerActor.updateTrophy(false);
			this.playerActor.pauseHandler(true);
			this.playerActor.pack();
		}
	}
	
	public void setJetPackOn(boolean b) {
				
	}
	
	public void shot() {
		if(this.player.hasGun())
			this.map.shot();
	}
	
	public void enableHelp(boolean enable) {
		if (enable){
			this.map.pause(true);
			this.inHelp = true;
			this.playerActor.showHelp();}
		else{ 
			this.playerActor.removeHelp();
			this.inHelp = false;
			this.map.pause(false);
			}
	}
	
	public void updateHelp() {
		if (this.inHelp)
			this.inHelp = this.playerActor.updateHelp(); 
		if (!this.inHelp)
			this.enableHelp(false);
	}
	
	public void pause(boolean p){
		this.map.pause(p);
		boolean check = this.playerActor.sendMessage("Paused!!!", 2);
		if (check)
			this.map.pause(false);
	}
	public void finishGame() {
		this.map.pause(true);
		boolean check = this.playerActor.sendMessage("Do you really want quit?", 1);
		if (check)
			System.exit(0);
		else
			this.map.pause(false);	
	}
	public void sendEscape() {
		if(this.inHelp)
			this.enableHelp(false);
		else
			this.finishGame();
	}
	public void updateScreen() {	
		this.actualMap = this.actualMaps.get(this.actualScreen);
		this.map.putSprites(this.actualMap, this.levels.getValues(), false);
		this.map.update(this.map.getGraphics());
		this.actualScreen ++;
	}
	public void backScreen() {
		this.actualScreen--;
		this.actualMaps.set(this.actualScreen, this.actualMap);
		this.actualMap = this.actualMaps.get(this.actualScreen-1);
		this.map.putSprites(this.actualMap, this.levels.getValues(), true);
		this.map.update(this.map.getGraphics());
	}
	

	 
}
 
