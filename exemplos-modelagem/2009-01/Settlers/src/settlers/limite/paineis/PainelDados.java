package settlers.limite.paineis;

import javax.swing.JPanel;

import settlers.limite.peoes.Dado;

public class PainelDados extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Dado dado1;
    private Dado dado2;

    public PainelDados() {
        setLayout(null);
        setSize(130, 80);
        setOpaque(false);
        this.dado1 = new Dado();
        this.dado2 = new Dado();
        dado1.setLocation(20, 20);
        dado2.setLocation(30 + dado2.getWidth(), 20);
        add(dado1);
        add(dado2);
    }

    public void setValorDados(int valor1, int valor2) {
        for (int i = 0; i < 5; i++) {
        	dado1.rolar();
        	dado2.rolar();
        	try {
				Thread.sleep(20 * 5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        dado1.setValor(valor1);
        dado2.setValor(valor2);
    }

}
