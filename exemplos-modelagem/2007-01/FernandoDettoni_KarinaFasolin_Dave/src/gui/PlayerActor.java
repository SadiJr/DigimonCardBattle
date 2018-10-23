package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import app.Handler;


public class PlayerActor extends JFrame {
 	 
	protected Map map = null;
	protected Header header = null;
	protected Footer footer = null; 
	protected Menu menu = null; 
	protected Help help = null;
	protected PanelScore score = null;

	
	
	public PlayerActor(){
		super("TothDave v0.8b");
		this.setPreferredSize(new Dimension(1008, 764));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.showMenu();
	}
	
	public void startGame() {
		this.remove(this.menu);
		((Handler)this.getKeyListeners()[0]).setInMenu(false);
		this.map = new Map();
		this.header = new Header();
		this.footer = new Footer();
		this.add(this.map,BorderLayout.CENTER);
		this.add(this.header, BorderLayout.NORTH);
		this.add(this.footer,BorderLayout.SOUTH);
		this.pack();
		
		
	}
		 
	public void showMenu() {
		this.menu = new Menu();
		this.add(this.menu, BorderLayout.CENTER);
	}
	 
	public Map getMap() {
		return this.map;
	}
	public void pauseHandler(boolean pause){
		Handler a = (Handler)this.getKeyListeners()[0];
		a.pause(pause);		
	}
	 

	public boolean sendMessage(String msg, int type) {
		if(type == 1)
			return JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(this, msg,"Confirma...", JOptionPane.OK_CANCEL_OPTION,1,this.header.images[15]);
		else if(type==2){
			JOptionPane.showMessageDialog(this, msg,"Confirma...", 1,this.header.images[15]);
			return true;
		}
			
		return false;
	}
	 
		 
	public void showHelp() {
		this.map.setVisible(false);
		this.help = new Help();
		this.add(this.help, BorderLayout.CENTER);
		this.pack();
	}
	 
	public void removeHelp() { 	
		this.remove(this.help);
		this.map.setVisible(true);

	}
	 
	public boolean updateHelp() {
		return this.help.update();
	}

	public void updateScore(int s) {
		this.header.updateScore(s);
	}

	public void updateTrophy(boolean got) {
		this.footer.informTrophy(got);	
	}

	public void updateLevelNumber(int actualLevel) {
		this.header.updateLevel(actualLevel);
	}

	public void updateLife(int lifes) {
		this.header.updateLifes(lifes);
	}
	
	public void updateAcessories(int acessories){
		this.footer.setAcessories(acessories);
	}

	public String sendInputMessage() {
		String s = "";
		while(s.length()<1)
			s = JOptionPane.showInputDialog("Enter your name: ");
		return s;
		
	}

	public void showScore(String[][] scores) {
		this.map.setVisible(false);
		this.score = new PanelScore(scores);
		this.add(this.score , BorderLayout.CENTER);
		((Handler)this.getKeyListeners()[0]).pause(true);
		((Handler)this.getKeyListeners()[0]).setInScore(true);
		this.pack();
	}

	public void restartHandler() {
		((Handler)this.getKeyListeners()[0]).pause(false);
		((Handler)this.getKeyListeners()[0]).setInScore(false);	
		
	}

	public void showMap() {
		if(this.help != null )
			this.help.setVisible(false);
		if(this.score != null)
			this.score.setVisible(false);
		this.map.setVisible(true);
		
	}


	

	

	
	
	
	
		
	

	
	
	 
}
 
