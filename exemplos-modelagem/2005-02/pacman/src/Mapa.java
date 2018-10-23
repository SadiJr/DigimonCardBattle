
import java.awt.Color;
import java.awt.Graphics;


public class Mapa {

	private int blocksize = Dados.BLOCKSIZE;
	private short screendata[] = null;
	private Color corpastilha = new Color(192,192,0);
	private Color corpastilhapoder = new Color (255,0,0);
	private Color mazecolor = new Color (0,0,255);
	
	/**
	 * Construtor padrão do Mapa
	 */
	public Mapa() {
		if(this.screendata==null){
			this.init();
		}
	}
	
	/**
	 * Este método reinicializa o mapa em seus valores originais.
	 * A tentativa de utilizar a classe de dados não funcionou pois com  
	 * um array estático, os dados nunca são reinicializados após serem alterados
	 * no decorrer do jogo!
	 */
	public void init(){
		short nivel1 []	 =  { 	19,26,26,22, 9,12,19,26,22, 9,12,19,26,26,22,
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
		
		this.screendata = nivel1;
	}
	
/**	
 * Este método renderiza o Mapa da fase atual, baseado em Screendata
 */ 
	public void desenhaMapa(Graphics bufferG){
	    short i=0;
	    int x,y;

	    for (y=0; y<Dados.HEIGHT; y+=blocksize)
	    {
	      for (x=0; x<Dados.WIDTH; x+=blocksize)
	      {
		bufferG.setColor(mazecolor);
	        if ((screendata[i]&1)!=0)
		{
	        bufferG.drawLine(x,y,x,y+blocksize-1);
		}
		if ((screendata[i]&2)!=0)
		{
	        bufferG.drawLine(x,y,x+blocksize-1,y);
		}
		if ((screendata[i]&4)!=0)
		{
	        bufferG.drawLine(x+blocksize-1,y,x+blocksize-1,y+blocksize-1);
		}
		if ((screendata[i]&8)!=0)
		{
	        bufferG.drawLine(x,y+blocksize-1,x+blocksize-1,y+blocksize-1);
		}
		//Desenha as pastilhas
		if ((screendata[i]&16)!=0)
		{
	        bufferG.setColor(corpastilha);
	        bufferG.fillRect(x+15,y+15,4,4);
		}
		//Desenha as pílulas do poder
		if ((screendata[i]&32)!=0)
		{
	        bufferG.setColor(corpastilhapoder);  
			bufferG.fillOval(x+10,y+10,12,12);
		}
		i++;
	      }
	    }
	}
	
	public void setScreenData (short[] screendata){
		this.screendata = screendata;
	}
	
	public short[] getScreenData(){
		return screendata;
	}
	
	public int contaPilulas(){
		int pilulas = 0;
		for(int i=0; i<screendata.length; i++){
			if( ((screendata[i]&16)!=0) || ((screendata[i]&32)!=0) ){
				pilulas ++;
			}
		}
		return pilulas;
	}
}
