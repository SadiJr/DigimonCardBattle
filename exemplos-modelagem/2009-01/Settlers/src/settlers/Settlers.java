package settlers;

import java.util.Locale;

import javax.swing.UIManager;

import settlers.limite.AtorJogador;

public class Settlers {

	public static String APPLICATION_NAME = "Settlers";
	
	public static void main(String[] args) {
		// Define opções de língua pt-BR
		definirOpcoesRegionais();
		// Tenta definir aparência do SO
		definirLookAndFeel();
		// Instancia
		new AtorJogador().setVisible(true);
	}
	
	public static void definirOpcoesRegionais() {
		// Seta Língua e Local
		Locale.setDefault(new Locale("pt", "BR"));
		// Traduz botões JOptionPane
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");
		UIManager.put("OptionPane.yesButtonText", "Sim");
		UIManager.put("OptionPane.noButtonText", "Não");
		UIManager.put("OptionPane.okButtonText", "OK");
	}
	
	public static void definirLookAndFeel() {
		try {
		   javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {}
	}
	
}
