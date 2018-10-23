package InterfaceGrafica;

import javax.swing.JFrame;

public class MakYek {

	public static void main(String[] args) {
		InterfaceMakYek janela;
		janela = new InterfaceMakYek();
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLocationRelativeTo(null);
		janela.setResizable(false);
		janela.setVisible(true);
	}
}