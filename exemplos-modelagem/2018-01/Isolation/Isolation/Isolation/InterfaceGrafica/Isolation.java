package InterfaceGrafica;

import javax.swing.JFrame;

public class Isolation {

	public static void main(String[] args) {
		InterfaceIsolation janela;
		janela=new InterfaceIsolation();
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
	}

}