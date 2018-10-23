import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class PacMenu implements Runnable{

	private Frame fMenu = null;
	private Graphics bufferG = null;
	private Thread menu = null;
	private boolean running = false;

	private int opcao = 1;
	
	private BufferedImage 	offscreen, imTitulo, imInfo,
							imJogar, imJogar1, imJogar2,
							imPlacar, imPlacar1, imPlacar2,
							imSair, imSair1, imSair2,
							imCreditos, imCreditos1, imCreditos2;
	
	public PacMenu (){

		fMenu = new Frame();
		fMenu.setSize(Dados.WIDTH+15, Dados.HEIGHT+105);
		fMenu.setTitle("PacMan - by Farakeys and Ozzy");
		fMenu.setBackground(Color.BLACK);

		offscreen = new BufferedImage(Dados.WIDTH + 15, Dados.HEIGHT + 105,BufferedImage.TYPE_INT_RGB);
		bufferG = offscreen.getGraphics();
		
		// Instancia a Inner Class com os tratadores de evento
		EventManager eMan = new EventManager();
		
		fMenu.addWindowListener(eMan);
		fMenu.addKeyListener(eMan);
		fMenu.setVisible(true);
		
		try {
			
			imTitulo 	= ImageIO.read (getClass().getResource("/pictures/pacman.png"));
			imInfo		= ImageIO.read (getClass().getResource("/pictures/pressbutton.png"));
			
			imJogar1 	= ImageIO.read (getClass().getResource("/pictures/jogar1.png"));
			imJogar2 	= ImageIO.read (getClass().getResource("/pictures/jogar2.png"));
			imPlacar1 	= ImageIO.read (getClass().getResource("/pictures/placar1.png"));
			imPlacar2 	= ImageIO.read (getClass().getResource("/pictures/placar2.png"));
			imSair1 	= ImageIO.read (getClass().getResource("/pictures/sair1.png"));
			imSair2 	= ImageIO.read (getClass().getResource("/pictures/sair2.png"));
			imCreditos1 = ImageIO.read (getClass().getResource("/pictures/creditos1.png"));
			imCreditos2 = ImageIO.read (getClass().getResource("/pictures/creditos2.png"));
			
			imJogar 	= imJogar2;
			imPlacar	= imPlacar1;
			imSair		= imSair1;
			imCreditos  = imCreditos1;
			
		} catch (IOException e){
				System.out.println("Erro no carregamento da Imagem!!");
				System.exit(0);
		}

		this.init();
	}
	
	public void init(){
		menu = new Thread(this,"menu");
		running = true;
		menu.start();
	}
	
	public void run (){
		while(running){
			this.mostraMenu();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
	
	public void mostraMenu (){
		
		if(!this.fMenu.isVisible()){
			this.fMenu.setVisible(true);
			this.mostraMenu();
		}
		
		bufferG.clearRect(0,0, Dados.WIDTH+15, Dados.HEIGHT+105);
		
		bufferG.drawImage(imTitulo, (Dados.WIDTH/2)-150 , 50, fMenu);
		bufferG.drawImage(imJogar, (Dados.WIDTH/2)-100, (Dados.HEIGHT/2)-100, fMenu);
		bufferG.drawImage(imPlacar, (Dados.WIDTH/2)-100, (Dados.HEIGHT/2)-50, fMenu);
		bufferG.drawImage(imCreditos, (Dados.WIDTH/2)-100, (Dados.HEIGHT/2), fMenu);
		bufferG.drawImage(imSair, (Dados.WIDTH/2)-100, (Dados.HEIGHT/2)+50, fMenu);
		bufferG.drawImage(imInfo, (Dados.WIDTH/2)-200, (Dados.HEIGHT/2)+150, fMenu);
		
		this.paint();
	}
	
	public void paint (){
		fMenu.getGraphics().drawImage(offscreen,5,35,fMenu);
	}
	
	public void seletor(){
		
		imJogar 	= imJogar1;
		imPlacar 	= imPlacar1;
		imCreditos	= imCreditos1;
		imSair		= imSair1;
		
		if(this.opcao>4){
			opcao = 1;
		} else if (this.opcao<1) {
			opcao = 4;
		}

		switch(opcao){
			case 1: {
				imJogar = imJogar2;
				break;
			}	
					
			case 2: {
				imPlacar = imPlacar2;
				break;
			}	
			
			case 3: {
				imCreditos = imCreditos2;
				break;
			}	
			
			case 4: {
				imSair = imSair2;
				break;
			}	
		}
		this.mostraMenu();
	}
	
	public void executa(){
		switch(opcao){
			case 1: { 
				this.running = false;
				this.menu = null;
				this.jogar();
				break;
			}
					
			case 2: { this.placar(); break; }
			
			case 3: { this.creditos(); break; }
			
			case 4: { System.exit(0);	break; }
		}
	}
	
	public void jogar (){
		fMenu.setVisible(false);
		@SuppressWarnings("unused") Gameplay game = new Gameplay(this);
	}
	
	public void placar(){
		Placar placar = new Placar(true);
	}
	
	public void creditos(){
		//JOptionPane.showMessageDialog(fMenu,"Este programa foi desenvolvido...");
		Creditos creditos = new Creditos();
	}
	
	
	private class EventManager implements KeyListener, WindowListener {

		/**
		 * Métodos da Interface KeyListener
		 */
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_UP){
				PacMenu.this.opcao--;
				PacMenu.this.seletor();				
			}
			if(arg0.getKeyCode()==KeyEvent.VK_DOWN){
					PacMenu.this.opcao++;
					PacMenu.this.seletor();
			}
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
				PacMenu.this.executa();
			}
			if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE){
				System.exit(0);	
			}
		}
		public void keyReleased(KeyEvent arg0) {}	
		public void keyTyped(KeyEvent arg0) {}
		
		/**
		 * Métodos da Interface WindowListener
		 */
		public void windowClosing(WindowEvent arg0) {
			System.exit(0);
		}
		
		public void windowOpened(WindowEvent arg0) {}
		public void windowClosed(WindowEvent arg0) {}
		public void windowIconified(WindowEvent arg0) {}
		public void windowDeiconified(WindowEvent arg0) {}
		public void windowActivated(WindowEvent arg0) {}
		public void windowDeactivated(WindowEvent arg0) {}
	}	
}
