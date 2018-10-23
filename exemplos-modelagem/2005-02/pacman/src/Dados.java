

public final class Dados {

	public static final int VIDAS = 3;
	
	public static final int GHOSTS = 4;
	
	public static final int HEIGHT = 525;
	public static final int WIDTH = 525;
	
	public static final byte NONE = 0;
	public static final byte LEFT = 1;
	public static final byte RIGHT = 2;
	public static final byte UP = 3;
	public static final byte DOWN = 4;

	public static final int BLOCKSIZE = 35;
	
	public static final int INI_PACMAN_X = Dados.BLOCKSIZE*7;
	public static final int INI_PACMAN_Y = Dados.BLOCKSIZE*11;
	public static final int[] INI_PACMAN_XY = {INI_PACMAN_X,INI_PACMAN_Y};
	
	public static final int INI_GHOST_X = Dados.BLOCKSIZE*5;
	public static final int INI_GHOST_Y = Dados.BLOCKSIZE*5;
	public static final int[] INI_GHOST_XY = {INI_GHOST_X,INI_GHOST_Y};
	
	public static final int STEP = Dados.BLOCKSIZE/5;
	
	public static final long SCARED_TIME = 9000;
	
	public static final String NOME_ARQUIVO = "placar.dat"; 
	
	public final short NIVEL1 [] = { 
		19,26,26,22, 9,12,19,26,22, 9,12,19,26,26,22,
		37,11,14,17,26,26,20,15,17,26,26,20,11,14,37,
		17,26,26,20,11, 6,17,26,20, 3,14,17,26,26,20,
		21, 3, 6,25,22, 5,21, 7,21, 5,19,28, 3, 6,21,
		21, 9, 8,14,21,13,21, 5,21,13,21,11, 8,12,21,
		25,18,26,18,24,18,28, 5,25,18,24,18,26,18,28,
		 6,21, 7,21, 7,21,11, 8,14,21, 7,21, 7,21,03,
		 4,21, 5,21, 5,21,11,10,14,21, 5,21, 5,21, 1,
		12,21,13,21,13,21,11,10,14,21,13,21,13,21, 9,
		19,24,26,24,26,16,26,18,26,16,26,24,26,24,22,
		21, 3, 2, 2, 6,21,15,21,15,21, 3, 2, 2,06,21,
		21, 9, 8, 8, 4,17,26, 8,26,20, 1, 8, 8,12,21,
		17,26,26,22,13,21,11, 2,14,21,13,19,26,26,20,
		37,11,14,17,26,24,22,13,19,24,26,20,11,14,37,
		25,26,26,28, 3, 6,25,26,28, 3, 6,25,26,26,28  };

}
