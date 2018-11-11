package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class DigimonScreen extends JFrame {
	
	
	
	
	
	
	
	
	
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