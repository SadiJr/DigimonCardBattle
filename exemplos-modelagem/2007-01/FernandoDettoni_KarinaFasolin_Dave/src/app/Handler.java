package app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Handler implements KeyListener {

	GamePlay gamePlay;
	protected boolean pause = false;
	protected boolean inScore = false;
	protected boolean inMenu = true;
	
	
	public Handler(GamePlay gamePlay){
		this.gamePlay = gamePlay;
	}

	public void keyPressed(KeyEvent e) {
		if(!this.pause && !this.inMenu)
			switch(e.getKeyCode()){
			case KeyEvent.VK_RIGHT:
				this.gamePlay.movePlayerRight();
				break;
			case KeyEvent.VK_LEFT:
				this.gamePlay.movePlayerLeft();
				break;
				
			case KeyEvent.VK_UP:
				this.gamePlay.movePlayerUp();
				break;
				
			case KeyEvent.VK_ALT:
				this.gamePlay.setJetPackOn(true);
				break;
	
			case KeyEvent.VK_CONTROL:
				this.gamePlay.shot();
				break;
			case KeyEvent.VK_F1:
				this.gamePlay.enableHelp(true);
				break;
			
			case KeyEvent.VK_F9:
				this.gamePlay.pause(true);
				break;
				
			case KeyEvent.VK_SPACE:
				this.gamePlay.updateHelp();
				break;
				
			case KeyEvent.VK_F3:
				this.gamePlay.restartGame();
				break;
				
			case KeyEvent.VK_ESCAPE:
				this.gamePlay.sendEscape();
				break;
			}
		if(this.inScore)
			switch(e.getKeyCode()){
			case KeyEvent.VK_F3:
				this.gamePlay.restartGame();
				break;
				
			case KeyEvent.VK_ESCAPE:
				this.gamePlay.sendEscape();
				break;
				}
		if(this.inMenu && e.getKeyCode()==KeyEvent.VK_ENTER)
			this.gamePlay.startGame();
			
	}

	

	public void keyReleased(KeyEvent e) {
		if(!this.pause && !this.inMenu)
			switch (e.getKeyCode()){
			case KeyEvent.VK_RIGHT:
				this.gamePlay.stopPlayerWalking();
				break;
			case KeyEvent.VK_LEFT:
				this.gamePlay.stopPlayerWalking();
				break;
			}
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void pause(boolean pause){
		this.pause  = pause;
	}

	public void setInScore(boolean inScore) {
		this.inScore = inScore;
	}

	public boolean isInMenu() {
		return inMenu;
	}

	public void setInMenu(boolean inMenu) {
		this.inMenu = inMenu;
	}
	
	
	
}
