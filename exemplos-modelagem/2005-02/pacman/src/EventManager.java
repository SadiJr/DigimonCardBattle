

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class EventManager implements KeyListener, WindowListener {

	private Gameplay game;
	
	public EventManager (Gameplay game){
		this.game = game;
	}
		/**
		 * Métodos da Interface KeyListener
		 */
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_LEFT){
				game.setDir(Dados.LEFT);
			}
			if(arg0.getKeyCode()==KeyEvent.VK_RIGHT){
				game.setDir(Dados.RIGHT);
			}
			if(arg0.getKeyCode()==KeyEvent.VK_UP){
				game.setDir(Dados.UP);
			}
			if(arg0.getKeyCode()==KeyEvent.VK_DOWN){
				game.setDir(Dados.DOWN);	
			}
			if(arg0.getKeyCode()==KeyEvent.VK_S && game.getPause()){
				game.finaliza();	
			}
			if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE){
				game.pause();	
			}
			game.setPressed(true);
		}
		public void keyReleased(KeyEvent arg0) {}	
		public void keyTyped(KeyEvent arg0) {}
		
		/**
		 * Métodos da Interface WindowListener
		 */
		public void windowClosing(WindowEvent arg0) {
			game.finaliza();
		}
		public void windowOpened(WindowEvent arg0) {}
		public void windowClosed(WindowEvent arg0) {}
		public void windowIconified(WindowEvent arg0) {}
		public void windowDeiconified(WindowEvent arg0) {}
		public void windowActivated(WindowEvent arg0) {}
		public void windowDeactivated(WindowEvent arg0) {}
}	


