
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class Gameplay implements Runnable{

	private PacMenu menu = null;
	private Pacman pacman = null;
	private Mapa mapa = null;
	private Ghost ghost = null;
	private ChecaLimite checalimite = null;
	private EventManager eMan = null;
	
	private boolean running;
	private boolean isPause;
	private boolean isPressed;
	private boolean isScared;
	
	private int placar;
	private int vidas;
	private int fase;
	private int numeroPilulas;
	
	private long scaredTime;
	
	private byte dir;
	private byte lastdir;
	private byte nextdir;

	private Vector vGhost = null;
	
	private Frame fGame = null;
	private Graphics bufferG = null; 
	private BufferedImage offscreen;
	private Font font;
	
	private Thread principal = null;
	
	public Gameplay (PacMenu menu){
		this.menu = menu;

		fGame = new Frame();
		fGame.setSize(Dados.WIDTH+15, Dados.HEIGHT+105);
		fGame.setTitle("PacMan - by Farakeys and Ozzy");
		fGame.setBackground(Color.BLACK);
		
		// Instancia a Classe com os tratadores de evento
		eMan = new EventManager(this);
		
		fGame.addWindowListener(eMan);
		fGame.addKeyListener(eMan);
		fGame.setVisible(true);
		
		this.placar = 0;
		this.vidas = Dados.VIDAS;
		this.fase = 1;
		
		
		this.init();
	}
	
	@SuppressWarnings("unchecked")
	public void init (){
		
		this.mapa = new Mapa();
		this.checalimite = new ChecaLimite(mapa.getScreenData());
		this.pacman = new Pacman(fGame.getGraphics() , this.fGame);
		this.vGhost = new Vector();
			
		for(int i = 0; i < Dados.GHOSTS + fase; i++){
			
			ghost = new Ghost(fGame.getGraphics() , this.fGame, this.checalimite);
			ghost.setXY(Dados.INI_GHOST_XY);
			vGhost.add(ghost);
			
		}

		/* Double Buffering
		 * O Double Buffering é a técnica pela qual o sistema desenha em uma tela
		 * "offline" todos os elementos gráficos e depois imprime esta tela de uma vez só.
		 * Com isto evita-se o chamado "Flickering" que é a tela piscando enquanto é redesenhada.
		 */
		offscreen = new BufferedImage(Dados.WIDTH + 15, Dados.HEIGHT + 105,BufferedImage.TYPE_INT_RGB);
		bufferG = offscreen.getGraphics();
		
		// Seta a fonte padrão para ser utilizada no placar 
		font = new Font("Comic Sans MS", Font.BOLD, 20);
		bufferG.setFont(font);
		
		// Inicialização das variáveis de controle do "Estado" da partida
		this.numeroPilulas = mapa.contaPilulas();
		this.running = true;
		this.isPressed = false;
		this.isScared = false;
		
		// Cria a "Thread" principal que vai controlar a partida
		principal = new Thread(this,"Pacman");
		principal.start();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException IE){
			System.out.println("Sleep da Thread Interrompido!!");
		}
	}
	
	public void run (){
		
		while(running){
			
			if(!isPause){
			this.movePacman();
			this.moveGhost();
			this.checkMaze();
			this.redrawMaze();
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException IE){
				System.out.println("Sleep da Thread Interrompido!!");
			}			
		}
	}
	
	public void movePacman (){
		
		if(pacman.getXY()[0]%Dados.BLOCKSIZE==0&&pacman.getXY()[1]%Dados.BLOCKSIZE==0){
			dir = nextdir;
			lastdir = dir;
		} else {
			dir = lastdir;
		}

		if(isPressed){
			if (dir==Dados.LEFT &&(checalimite.checaLimite(pacman.getXY(),Dados.LEFT)==false))
				pacman.moveLeft();
			if (dir==Dados.RIGHT &&(checalimite.checaLimite(pacman.getXY(),Dados.RIGHT)==false))
				pacman.moveRight();
			if (dir==Dados.UP &&(checalimite.checaLimite(pacman.getXY(),Dados.UP)==false))
				pacman.moveUp();
			if (dir==Dados.DOWN &&(checalimite.checaLimite(pacman.getXY(),Dados.DOWN)==false))
				pacman.moveDown();			
		}
	}
	
	public void moveGhost(){
		
		for (int i = 0; i<vGhost.size(); i++){
			ghost = (Ghost)vGhost.get(i);
			ghost.move(isScared);			
		}			
	}
	
	/**
	 * 
	 */
	public void checkMaze(){
		if(checalimite.isPastilha(pacman.getXY())){
			Audio audio = new Audio();
			Thread sound = new Thread(audio, "audio");
			String curDir = System.getProperty("user.dir");
			audio.setAudio(curDir + "/bin/sound/pilula.wav");
			sound.start();
			
			this.placar +=10;
			this.numeroPilulas--;
		}
		if(checalimite.isPastilhaPoder(pacman.getXY())){
			Audio audio = new Audio();
			Thread sound = new Thread(audio, "audio");
			String curDir = System.getProperty("user.dir");
			audio.setAudio(curDir + "/bin/sound/ghostbusters.wav");
			sound.start();
			
			this.placar +=50;
			this.numeroPilulas--;
			this.setScared(true);
		}
		if(isScared){
			if(System.currentTimeMillis()-this.scaredTime>=Dados.SCARED_TIME){
				this.setScared(false);
			}
		}
		if(this.numeroPilulas==0){
			this.proximaFase();
		}
		for (int i = 0; i<vGhost.size(); i++){
			ghost = (Ghost)vGhost.get(i);
			
			int difGPx = (ghost.getXY()[0]) - (pacman.getXY()[0]);
			int difGPy = (ghost.getXY()[1]) - (pacman.getXY()[1]);
			
			if	((difGPx < 25) && (difGPx > -25) && (difGPy < 25) && (difGPy > -25)) {
				if(isScared){
					this.mataGhost();
				} else {
					this.morre();	
				}
			}
		}
	}
	
	public void mataGhost(){
		this.placar +=200;
		bufferG.drawString("200",pacman.getXY()[0],pacman.getXY()[1]+55);
		ghost.setXY(Dados.INI_GHOST_XY);
		this.paint();
		try {
			Thread.sleep(200);
		} catch (InterruptedException IE){
			System.out.println("Sleep da Thread Interrompido!!");
		}
		Audio audio = new Audio();
		Thread sound = new Thread(audio, "audio");
		String curDir = System.getProperty("user.dir");
		audio.setAudio(curDir + "/bin/sound/mataghost.wav");
		sound.start();
	}
	
	public void morre(){
		this.vidas --;
		
		Audio audio = new Audio();
		Thread sound = new Thread(audio, "audio");
		String curDir = System.getProperty("user.dir");
		audio.setAudio(curDir + "/bin/sound/pacmandie.wav");
		sound.start();
		
		if(vidas==0) {
			this.running = false;
			this.gameOver();
		} else {
			try {
				Thread.sleep(800);
			} catch (InterruptedException IE){
				System.out.println("Sleep da Thread Interrompido!!");
			}
			
			pacman.setXY(Dados.INI_PACMAN_XY);
			
			for(int i = 0; i < Dados.GHOSTS; i++){
				ghost = (Ghost)vGhost.get(i);
				ghost.setXY(Dados.INI_GHOST_XY);
			}			
		}
	}
	
	public void proximaFase(){

		vGhost.removeAllElements();
		
		this.fase++;
		this.mapa.init();
		this.numeroPilulas = mapa.contaPilulas();
		this.checalimite = null;
		this.checalimite = new ChecaLimite(mapa.getScreenData());		
		this.isPressed = false;
		this.isScared = false;
		
		pacman.setXY(Dados.INI_PACMAN_XY);
		
		for(int i = 0; i < (Dados.GHOSTS + fase); i++){
			ghost = new Ghost(fGame.getGraphics() , this.fGame, this.checalimite);
			ghost.setXY(Dados.INI_GHOST_XY);
			vGhost.add(ghost);
		}
		
		bufferG.setColor(new Color(0,0,0));
		bufferG.fillRect(150,200,250,100);

		Font fontTemp = new Font("Comic Sans MS", Font.BOLD, 30);
		bufferG.setFont(fontTemp);
		bufferG.setColor(new Color(255,255,0));
		bufferG.drawRect(150,200,250,100);
		bufferG.drawString("READY", 220, 260);
		bufferG.setFont(font);
		
		this.paint();
		
		Audio audio = new Audio();
		Thread sound = new Thread(audio, "audio");
		String curDir = System.getProperty("user.dir");
		audio.setAudio(curDir + "/bin/sound/welcome.wav");
		sound.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException IE){
			System.out.println("Sleep da Thread Interrompido!!");
		}
		this.redrawMaze();
		
	}
	
	public void gameOver(){
		
		bufferG.setColor(new Color(0,0,0));
		bufferG.fillRect(150,200,250,100);

		Font fontTemp = new Font("Comic Sans MS", Font.BOLD, 30);
		bufferG.setFont(fontTemp);
		bufferG.setColor(new Color(255,255,0));
		bufferG.drawRect(150,200,250,100);
		bufferG.drawString("GAME OVER", 180, 260);
		
		this.paint();
		
		Audio audio = new Audio();
		Thread sound = new Thread(audio, "audio");
		String curDir = System.getProperty("user.dir");
		audio.setAudio(curDir + "/bin/sound/gameover.wav");
		sound.start();
		
		Placar placar = new Placar(false);
		placar.incluiPlacar(this.placar);
		this.finaliza();
	}
	
	public void drawPlacar(){
		
		bufferG.setColor(new Color(255,255,0));		
		bufferG.drawString("Vidas " + vidas, 20, 550);
		bufferG.drawString("Placar " + placar, 200, 550);
		bufferG.drawString("Pilulas " + numeroPilulas, 400, 550);
		
	}

	public void redrawMaze(){
		
		bufferG.clearRect(0,0, Dados.WIDTH+15, Dados.HEIGHT+70);
		pacman.desenhaPacman(bufferG);
		mapa.desenhaMapa(bufferG);
		
		for (int i = 0; i<vGhost.size(); i++){
			ghost = (Ghost)vGhost.get(i);
			ghost.desenhaGhost(bufferG);			
		}
		
		this.drawPlacar();
		this.paint();
	}
	
	public void paint (){
		fGame.getGraphics().drawImage(offscreen,5,35,fGame);
	}
	
	public void setDir (byte dir){
		this.nextdir = dir;
	}
	
	public void setScared(boolean isScared){
		if(isScared){
			this.isScared = true;
			this.scaredTime = System.currentTimeMillis();
		} else {
			this.isScared = false;
		}
	}
	
	public void setPressed (boolean status){
		this.isPressed = status;
	}
	
	public boolean getPause() {
		return this.isPause;
	}

	public void pause (){
		
		if(!isPause){
			isPause = true;
			bufferG.setColor(new Color(0,0,0));
			bufferG.fillRect(150,200,250,100);

			Font fontTemp = new Font("Comic Sans MS", Font.BOLD, 30);
			bufferG.setFont(fontTemp);
			bufferG.setColor(new Color(255,255,0));
			bufferG.drawRect(150,200,250,100);
			bufferG.drawString("Pausa", 230, 240);
			
			fontTemp = new Font("Comic Sans MS", Font.BOLD, 18);
			bufferG.setFont(fontTemp);
			bufferG.drawString("ESC - Retorna, S - Sair", 170, 280);
			
			bufferG.setFont(font);
			this.paint();
			
		} else {
			isPause = false;
		}
	}
	
	public void finaliza (){
		
		running = false;
		principal = null;
		pacman = null;
		mapa = null;
		ghost = null;
		checalimite = null;		
		
		fGame.setVisible(false);
		this.fGame = null;
		this.menu.init();
	}
}
