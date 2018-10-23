package worms;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Display;

public class LArma implements CommandListener{
 
	private List menuArmas;
	 
	private CArma cArma;
	
	private Command okCommand,exitCommand;
	
	private Display tela;
	 
	public LArma(String[] listaArmas, CArma cArma) {
		this.cArma = cArma;
		okCommand    = new Command("OK", Command.OK, 1);
		exitCommand  = new Command("Voltar", Command.SCREEN, 1);
		menuArmas = new List("Worms",List.IMPLICIT,listaArmas,null);
		menuArmas.addCommand(exitCommand);
		menuArmas.addCommand(okCommand);
		menuArmas.setCommandListener(this);		
	}
	 
	public void mostraMenu(Display d) {
		tela = d;
		d.setCurrent(menuArmas);
	}
	
	public void commandAction(Command cmd, Displayable disp) {
		if (cmd == okCommand && menuArmas.isShown()){
			cArma.setArmaMinhoca(menuArmas.getString(menuArmas.getSelectedIndex()));			
		} else if(cmd==exitCommand)
			cArma.voltaProJogo(tela);
		
	}
	 
}
 
