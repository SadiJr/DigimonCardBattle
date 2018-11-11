package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import actor.ActorPlayer;

@SuppressWarnings("serial")
public class DigimonScreen extends JFrame {
	private final ActorPlayer player;
	private JPanel options;
	private JButton jump;
	private JButton discardHand;
	private JButton viewAttributes;
	private JButton downDigimonCard;
	private JButton downSupportCard;
	private JButton sacrifice;
	private JButton evolute;
	private JButton atk1;
	private JButton atk2;
	private JButton atk3;
	
	public DigimonScreen(ActorPlayer actorPlayer) {
		this.player = actorPlayer;
	}
	
	
	
	
	
	
	
	
	public String getNamePlayer() {
		return JOptionPane.showInputDialog("Digite seu nome");
	}

	public String getNameServer() {
		return JOptionPane.showInputDialog("Digite o nome do servidor", "ufsc.algumacoisa-alterar-depois");
	}

	public void sendMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void informError(String error) {
		JOptionPane.showMessageDialog(null, error, "Erro", JOptionPane.ERROR_MESSAGE);
	}

}