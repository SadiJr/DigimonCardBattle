
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;

public class Jogo extends MIDlet implements CommandListener {
 
	protected Display display;
	protected ControleJogo ctrJogo;
	protected List mList;
	protected Command mExitCommand, mNextCommand;
	protected Command mReiniciarCommand;


	public Jogo()
	{
		String[] stringElements = { "Conversivel\n\n", "Cobra\n\n", "Pégasus\n\n", "Nave\n\n", "Scania\n\n" };
	    Image[] imageElements = { loadImage("/imagens/carro1.png"), 
	        loadImage("/imagens/carro2.png"), loadImage("/imagens/carro3.png"), loadImage("/imagens/carro4.png"), 
	        loadImage("/imagens/caminhao.png")};
	    mList = new List("Escolha seu Modelo", List.IMPLICIT,
	        stringElements, imageElements);
	    mNextCommand = new Command("Next", Command.SCREEN, 0);
	    mExitCommand = new Command("Exit", Command.EXIT, 0);
	    mReiniciarCommand = new Command("Reiniciar", Command.SCREEN, 0);
	    mList.addCommand(mNextCommand);
	    mList.addCommand(mExitCommand);
	    mList.setCommandListener(this);

		display = Display.getDisplay(this);
	}

	protected void startApp(){
		Display.getDisplay(this).setCurrent(mList);
      	//display.setCurrent(ctrJogo);
   	}
   	protected void pauseApp(){}
   	protected void destroyApp(boolean unconditional){}

   	public void exitMIDlet(){
      		destroyApp(false);
      		notifyDestroyed();
      
   	}
   	
   	protected Image loadImage(String pNome) {
   	    Image image = null;
   	    try {
   	      image = Image.createImage(pNome);
   	    }
   	    catch (IOException ioe) {
   	      System.out.println(ioe);
   	    }
   	    
   	    return image;
   	  }
   
   	public void commandAction(Command c, Displayable s) {
   		
   		if (c == mNextCommand || c == List.SELECT_COMMAND) {
   	      int index = mList.getSelectedIndex();
   	      ctrJogo = new ControleJogo(index + 1);
   	      ctrJogo.addCommand(mExitCommand);
   	      ctrJogo.addCommand(mReiniciarCommand);
   	      ctrJogo.setCommandListener(this);
   	      Display.getDisplay(this).setCurrent(ctrJogo);
   	    }
   	    else if (c == mExitCommand)
   	      exitMIDlet();
   	
   	 if (c == mReiniciarCommand) {
   		 ctrJogo.pause = true;
   		 startApp();
	     //Display.getDisplay(this).setCurrent(mList);
   		 
   	  }
   	}
      
   	
   	

}
