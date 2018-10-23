package settlers.limite.janelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import settlers.limite.AtorJogador;

public class JanelaIniciar extends JDialog {

	private static final long serialVersionUID = 1L;
	
	AtorJogador atorJogador;
	
	JLabel lNome = new JLabel("Seu nome ou apelido");
	JLabel lServidor = new JLabel("IP ou Host do servidor");
	JLabel lJogadores = new JLabel("Número de jogadores");
	
	JTextField tfNome = new JTextField();
	JTextField tfServidor = new JTextField();
	JSpinner tfJogadores = new JSpinner();
	
	JSpinner teste = new JSpinner();
	
	JLabel iLoading = new JLabel();
	ImageIcon iconeLoading = new ImageIcon(ClassLoader.getSystemResource("loading.gif"));
	
	JButton bOK = new JButton("OK");
	JButton bCancelar = new JButton("Cancelar");
	
	JLabel lAguarde = new JLabel("Aguarde. Localizando jogadores...");
	
	public JanelaIniciar(AtorJogador atorJogador) {
		super(atorJogador);
		this.atorJogador = atorJogador;
		setTitle("Iniciar");
		setModal(true);
		setSize(240, 275);
		setResizable(false);
		setLayout(null);
		setLocation(atorJogador.getX() + 40, atorJogador.getY() + 80);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new TratadorEventosJanela());
	    getRootPane().setDefaultButton(bOK);
		getRootPane().registerKeyboardAction(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
    			bCancelar.doClick();
	    	}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

		TratadorEventosBotoes actionListener = new TratadorEventosBotoes();

		lNome.setSize(180, 20);
		lNome.setLocation(25, 15);
		tfNome.setSize(180, 20);
		tfNome.setLocation(25, 35);
		tfNome.setText(atorJogador.getNomeJogador());

		lServidor.setSize(180, 20);
		lServidor.setLocation(25, 60);
		tfServidor.setSize(180, 20);
		tfServidor.setLocation(25, 80);
		tfServidor.setText(atorJogador.getServidor());
		
		lJogadores.setSize(180, 20);
		lJogadores.setLocation(25, 105);
		tfJogadores.setSize(100, 20);
		tfJogadores.setLocation(25, 125);
		tfJogadores.setValue(atorJogador.getNumeroJogadores());
		
		bOK.setSize(85, 25);
		bOK.setLocation(25, 165);
		bOK.addActionListener(actionListener);
		
		bCancelar.setSize(85, 25);
		bCancelar.setLocation(120, 165);
		bCancelar.addActionListener(actionListener);
		
		lAguarde.setSize(180, 20);
		lAguarde.setLocation(25, 190);
		lAguarde.setVisible(false);
		
		iLoading.setSize(180, 16);
		iLoading.setLocation(25, 210);
		iLoading.setBorder(BorderFactory.createEtchedBorder());
		
		add(lNome);
		add(tfNome);		
		add(lJogadores);
		add(tfJogadores);
		add(lServidor);
		add(tfServidor);
		
		add(bOK);
		add(bCancelar);
		
		add(iLoading);
		add(lAguarde);
	}
	
	public void cancelar() {
		if (bOK.isEnabled()) {
			this.dispose();
		} else {
			atorJogador.desconectar();
			bOK.requestFocus();
			setComponentesAtivo(true);
			iLoading.setIcon(null);
			lAguarde.setVisible(false);
		}
	}
	
	private void fechar() {
		setVisible(false);
		setComponentesAtivo(true);
		iLoading.setIcon(null);
		atorJogador.desconectar();
	}
	
	private void iniciar() {
		if (tfNome.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "Você precisa informar o seu nome ou apelido.", "Erro", JOptionPane.ERROR_MESSAGE);
			tfNome.requestFocus();
			return;
		}
		if (tfServidor.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "Você precisa informar o IP ou Host do servidor.", "Erro", JOptionPane.ERROR_MESSAGE);
			tfServidor.requestFocus();
			return;
		}
		if ((Integer) tfJogadores.getValue() < 2) {
			tfJogadores.setValue(2);
		} else if ((Integer) tfJogadores.getValue() > 4) {
			tfJogadores.setValue(4);
		}
		setComponentesAtivo(false);
		iLoading.setIcon(iconeLoading);
		lAguarde.setVisible(true);
		atorJogador.conectar((Integer) tfJogadores.getValue(), tfNome.getText(), tfServidor.getText());
	}
	
	private void setComponentesAtivo(boolean status) {
		tfNome.setEnabled(status);
		tfJogadores.setEnabled(status);
		tfServidor.setEnabled(status);
		bOK.setEnabled(status);
	}
	
	private class TratadorEventosBotoes implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		if (e.getSource() == bCancelar) {
    			cancelar();
    		} else if (e.getSource() == bOK) {
    			iniciar();
    		}
    	}
	}
	
	private class TratadorEventosJanela extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			fechar();
		}
	}

}
