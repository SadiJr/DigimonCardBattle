package settlers.limite.janelas;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

import settlers.limite.AtorJogador;

public class JanelaResultado extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final String TEXTO_VENCEDOR = "Parabéns, você venceu!"; 
	private static final String TEXTO_PERDEDOR = "Você perdeu!"; 
	
	private JLabel lResultado;
	private JLabel lPlanilhaResultados[][];
	private JButton bOK;
	
	private int idVencedor;
	private String resultado[][];
	
	public JanelaResultado(AtorJogador atorJogador, boolean vencedor, int idVencedor, String resultado[][]) {
		super(atorJogador);
		
		this.idVencedor = idVencedor;
		this.resultado = resultado;

		setTitle("Resultado");
		setModal(true);
		setSize(550, 175 + (22 * resultado.length));
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(atorJogador);
	    getRootPane().setDefaultButton(bOK);
		getRootPane().registerKeyboardAction(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		bOK.doClick();
	    	}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
	    
	    lResultado = new JLabel(vencedor ? TEXTO_VENCEDOR : TEXTO_PERDEDOR);
	    lResultado.setSize(515, 40);
	    lResultado.setLocation(20, 20);
	    lResultado.setHorizontalAlignment(JLabel.CENTER);
	    lResultado.setVerticalAlignment(JLabel.CENTER);
	    lResultado.setFont(new Font("Arial", Font.BOLD, 28));
	    
	    bOK = new JButton("OK");
	    bOK.setSize(120, 25);
		bOK.setLocation((getWidth() - bOK.getWidth()) / 2, 95 + (22 * resultado.length));
		bOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		geraPlanilhaResultado();
		
	    add(lResultado);
	    add(bOK);
	}
	
	public void geraPlanilhaResultado() {
		int larguras[] = {90, 40, 50, 80, 80, 90, 60};
		lPlanilhaResultados = new JLabel[resultado.length][7];
		for (int i = 0; i < resultado.length; i++) {
			int distancia = 20;
			for (int j = 0; j < resultado[i].length; j++) {
				lPlanilhaResultados[i][j] = new JLabel(" " + resultado[i][j] + " ");
				lPlanilhaResultados[i][j].setSize(larguras[j], 20);
				lPlanilhaResultados[i][j].setLocation(distancia, 70 + (22 * i));
				lPlanilhaResultados[i][j].setOpaque(true);
				if (i == 0) {
					lPlanilhaResultados[i][j].setBackground(Color.gray.darker());
					lPlanilhaResultados[i][j].setForeground(Color.white);
				} else if (idVencedor != 0 && i == idVencedor) {
					lPlanilhaResultados[i][j].setBackground(Color.red.darker());
					lPlanilhaResultados[i][j].setForeground(Color.white);
				} else {
					lPlanilhaResultados[i][j].setBackground(Color.gray.brighter());
				}
				if (j > 0) {
					lPlanilhaResultados[i][j].setHorizontalAlignment(JLabel.CENTER);
				}
				add(lPlanilhaResultados[i][j]);
				
				distancia += larguras[j] + 2;
			}
		}
	}

}
