package worms;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;

public class LAtor extends MIDlet implements CommandListener, ItemStateListener {
 
	private CPrincipal cPrincipal;
	private List menuPrincipal,menuCenarios,menuPreJogo;
	private Command okCommand,exitCommand;
	private Form formConfig;
	private ChoiceGroup chConfig;
	private String cenarioEscolhido = "/BangBang.PNG";//inicializado com o cenário padrão
	private String cenarios[] = {"BangBang","Livros","Vaquinhas","Frutas","Neve"};
	private int level = 0;
	private boolean somAtivo = true;
	private boolean twoPlayers = true;
	
	protected void startApp() throws MIDletStateChangeException{
		try{
			Display.getDisplay(this).setCurrent(new Abertura("/abertura.PNG"));
			Thread.currentThread().sleep(3000);//só pra dar tempo de aparecer a abertura
			mostraMenuPrincipal();
		}catch (Exception e) {
			System.out.println("erro iniciando a aplicação");
		}
		
	}
	
	private void iniciarPartida(){
		try {
			cPrincipal = new CPrincipal(this,cenarioEscolhido);
			Display display = Display.getDisplay(this);
			cPrincipal.ativaSom(somAtivo);
			cPrincipal.setTwoPlayers(twoPlayers);
			cPrincipal.setDificudade(level);
			System.out.println(twoPlayers);
			// remember, Canvas is a Displayable so it can
			// be set on the display like Screen elements
			display.setCurrent(cPrincipal);	
			// force repaint of the canvas
			cPrincipal.repaint();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void mostraMenuPrincipal(){
		String opcoes[] = {"Iniciar partida","Escolher Cenário","Configurações","Ajuda"};
		okCommand    = new Command("OK", Command.OK, 1);
		exitCommand  = new Command("Voltar", Command.SCREEN, 1);
		menuPrincipal = new List("Worms",List.IMPLICIT,opcoes,null);
		menuPrincipal.addCommand(exitCommand);
		menuPrincipal.addCommand(okCommand);
		menuPrincipal.setCommandListener(this);	
		Display.getDisplay(this).setCurrent(menuPrincipal);		
	}
	
	private void mostraMenuCenarios(){
		menuCenarios = new List("Worms",List.IMPLICIT,cenarios,null);
		menuCenarios.addCommand(exitCommand);
		menuCenarios.addCommand(okCommand);
		menuCenarios.setCommandListener(this);	
		Display.getDisplay(this).setCurrent(menuCenarios);		
	}
	
	private void mostraMenuPreJogo(){
		String cenarios[] = {"player1 vs player2","player1 vs computer"};
		menuPreJogo = new List("Worms",List.IMPLICIT,cenarios,null);
		menuPreJogo.addCommand(exitCommand);
		menuPreJogo.addCommand(okCommand);
		menuPreJogo.setCommandListener(this);	
		Display.getDisplay(this).setCurrent(menuPreJogo);		
	}
	
	
	private void mostraMenuConfiguracoes() {
		formConfig = new Form("Worms");
		String configs[] = {"Som"};
		chConfig = new ChoiceGroup("Configurações:", Choice.MULTIPLE, configs, null);
		chConfig.setSelectedIndex(0,somAtivo);
		formConfig.append(chConfig);
		formConfig.addCommand(exitCommand);
		formConfig.addCommand(okCommand);
		formConfig.setCommandListener(this);
		formConfig.setItemStateListener(this);
		Display.getDisplay(this).setCurrent(formConfig);
	}
	
	private void mostraHelp(){
		String helpText = "Teclas:\n" +
						  "0 - Muda de mirando pra andando e vice-versa\n"+
						  "2 - Pula ou aumenta o ângulo\n" +
						  "4 - Anda pra esquerda ou diminui a força\n" +
						  "6 - Anda pra direita ou aumenta a força\n" +
						  "5 - Atira";
		Alert help = new Alert("Worms",helpText,null, AlertType.INFO);
		help.setTimeout(Alert.FOREVER);
		Display.getDisplay(this).setCurrent(help);
		
	}
	
	public void commandAction(Command cmd, Displayable disp) {
		try{
		if (cmd==okCommand){
			if (menuPrincipal.isShown()){
				switch (menuPrincipal.getSelectedIndex()) {
				case 0:
					mostraMenuPreJogo();
					break;
				case 1:
					mostraMenuCenarios();
					break;
				case 2:
					mostraMenuConfiguracoes();
					break;
				case 3:
					mostraHelp();
					break;
				}
			}else if(menuCenarios != null && menuCenarios.isShown()){
				int sel = menuCenarios.getSelectedIndex();
				cenarioEscolhido = menuCenarios.getString(sel); //pega o nome do cenário escolhido
				cenarioEscolhido = "/"+cenarioEscolhido+".PNG";
				mostraMenuPrincipal();
			}else if(menuPreJogo.isShown()){
				int sel = menuPreJogo.getSelectedIndex();
				twoPlayers = sel==0?true:false; //pega o nome do cenário escolhido
				iniciarPartida();
			}else 
				mostraMenuPrincipal();
			
		}else if (cmd==exitCommand){
			if(menuPrincipal.isShown())
				notifyDestroyed();
			else// if(menuCenarios.isShown())
				mostraMenuPrincipal();
		}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
	}
	
	public  void itemStateChanged(Item item){
		if (item==chConfig){
			somAtivo = chConfig.isSelected(0);
		}
	}
	
	protected void pauseApp() {
		notifyPaused();
	}
	 
	protected void destroyApp(boolean arg0)throws MIDletStateChangeException {
	}
	 
}
