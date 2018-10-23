package settlers;

import java.util.Locale;

import javax.swing.UIManager;

import settlers.limite.AtorJogador;

public class Settlers {

	public static String APPLICATION_NAME = "Settlers";
	
	public static void main(String[] args) {
		// Define op��es de l�ngua pt-BR
		definirOpcoesRegionais();
		// Tenta definir apar�ncia do SO
		definirLookAndFeel();
		// Instancia
		new AtorJogador().setVisible(true);
	}
	
	public static void definirOpcoesRegionais() {
		// Seta L�ngua e Local
		Locale.setDefault(new Locale("pt", "BR"));
		// Traduz bot�es JOptionPane
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");
		UIManager.put("OptionPane.yesButtonText", "Sim");
		UIManager.put("OptionPane.noButtonText", "N�o");
		UIManager.put("OptionPane.okButtonText", "OK");
	}
	
	public static void definirLookAndFeel() {
		try {
		   javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {}
	}
	
}
