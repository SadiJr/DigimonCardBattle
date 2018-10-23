package settlers.limite.janelas;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import settlers.limite.AtorJogador;
import settlers.limite.componentes.JanelaJogo;

public class JanelaSelecionarJogador extends JanelaJogo {

	private static final long serialVersionUID = 1L;
	
	private JButton bJogadores[];
	
	private int jogador;
	
	public JanelaSelecionarJogador(AtorJogador atorJogador) {
		super(atorJogador, 240, 230);

		setTitle("Roubar recursos");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		TratadorCliqueBotoes tratadorBotoes = new TratadorCliqueBotoes();
		
		bJogadores = new JButton[4];
		String nome;
		for (int i = 0; i < 4; i++) {
			nome = atorJogador.getNomeJogador(i + 1);
			bJogadores[i] = new JButton(nome == null ? "Jogador inativo" : nome);
			bJogadores[i].setBackground(AtorJogador.getCorJogador(i + 1));
			bJogadores[i].setSize(200, 35);
			bJogadores[i].setLocation(20, 20 + (45 * i));
			bJogadores[i].addActionListener(tratadorBotoes);
			bJogadores[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			bJogadores[i].setEnabled(false);
			add(bJogadores[i]);
		}
	}
	
	public int selecionarJogador(int jogadoresRedondeza[]) {
		for (int i = 0; i < jogadoresRedondeza.length; i++) {
			bJogadores[jogadoresRedondeza[i] - 1].setEnabled(true);
		}
		setVisible(true);
		return jogador;
	}
	
	private class TratadorCliqueBotoes implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
    		if (e.getSource() == bJogadores[0]) {
    			jogador = 1;
    		} else if (e.getSource() == bJogadores[1]) {
    			jogador = 2;
    		} else if (e.getSource() == bJogadores[2]) {
    			jogador = 3;
    		} else {
    			jogador = 4;
    		}
    		setVisible(false);
		}
	}

}
