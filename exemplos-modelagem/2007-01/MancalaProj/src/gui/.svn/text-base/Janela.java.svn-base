package gui;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Janela extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel jContentPane = null;
	protected JMenuBar jJMenuBar = null;
	protected JMenu jArquivo = null;
	protected JMenu jConfig = null;
	protected JMenu jAjuda = null;
	protected JMenuItem jNovo = null;
	protected JMenuItem jSair = null;
	protected JMenuItem jSobre = null;
	protected AtorJogador painelTab = null;
	protected HandlerJanela handler = new HandlerJanela(this);
	private JMenu jInterfaces = null;
	private JMenuItem jHumxHum = null;
	private JMenuItem jHumxMaq = null;
	private JMenuItem jAjudaJan = null;
	
	public Janela(){
		this.initialize();
	}
		
	
	private void initialize() {
		this.setSize(800, 300);
		this.setPreferredSize(new Dimension(610,320));
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Mancala");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
	}
	

	protected JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	protected JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJArquivo());
			jJMenuBar.add(getJConfig());
			jJMenuBar.add(getJAjuda());
		}
		return jJMenuBar;
	}


	protected JMenu getJArquivo() {
		if (jArquivo == null) {
			jArquivo = new JMenu();
			jArquivo.setText("Arquivo");
			jArquivo.add(getJNovo());
			jArquivo.setMnemonic('A');
			jArquivo.add(getJSair());
		}
		return jArquivo;
	}


	protected JMenu getJConfig() {
		if (jConfig == null) {
			jConfig = new JMenu();
			jConfig.setText("Configurações");
			jConfig.add(getJInterfaces());
			jConfig.setMnemonic('C');
		}
		return jConfig;
	}


	protected JMenu getJAjuda() {
		if (jAjuda == null) {
			jAjuda = new JMenu();
			jAjuda.setText("Ajuda");
			jAjuda.add(getJSobre());
			jAjuda.setMnemonic('J');
			jAjuda.add(getJAjudaJan());
		}
		return jAjuda;
	}


	protected JMenuItem getJNovo() {
		if (jNovo == null) {
			jNovo = new JMenuItem();
			jNovo.setText("Novo");
			jNovo.setMnemonic('N');
			jNovo.setToolTipText("Inicia um novo jogo");
			jNovo.setActionCommand("novo");
			jNovo.addActionListener(handler);
		}
		return jNovo;
	}


	protected JMenuItem getJSair() {
		if (jSair == null) {
			jSair = new JMenuItem();
			jSair.setText("Sair");
			jSair.setMnemonic('S');
			jSair.setToolTipText("Encerra o programa");
			jSair.setActionCommand("sair");
			jSair.addActionListener(handler);
			
			}
		return jSair;
	}


	protected JMenuItem getJSobre() {
		if (jSobre == null) {
			jSobre = new JMenuItem();
			jSobre.setText("Sobre");
			jSobre.setMnemonic('S');
			jSobre.setToolTipText("Detalhes do Software");
			jSobre.setActionCommand("sobre");
			jSobre.addActionListener(handler);
		}
		return jSobre;
	}
	
	private JMenuItem getJAjudaJan() {
		if (jAjudaJan == null) {
			jAjudaJan = new JMenuItem();
			jAjudaJan.setText("Ajuda");
			jAjudaJan.setMnemonic('D');
			jAjudaJan.setToolTipText("Ajuda com as Regras");
			jAjudaJan.setActionCommand("ajuda");
			jAjudaJan.addActionListener(handler);
		}
		return jAjudaJan;
	}

	
	protected AtorJogador getJPanel() {
		if (painelTab == null) {
			painelTab = new AtorJogador();
		}
		return painelTab;
	}
	
	private JMenu getJInterfaces() {
		if (jInterfaces == null) {
			jInterfaces = new JMenu();
			jInterfaces.setText("Interface");
			jInterfaces.setMnemonic('I');
			jInterfaces.setToolTipText("Define interface do jogo");
			jInterfaces.add(getJHumxHum());
			jInterfaces.add(getJHumxMaq());
		}
		return jInterfaces;
	}

	
	private JMenuItem getJHumxHum() {
		if (jHumxHum == null) {
			jHumxHum = new JMenuItem();
			jHumxHum.setText("Humano x Humano");
			jHumxHum.setActionCommand("interf0");
			jHumxHum.setMnemonic('H');
			jHumxHum.setToolTipText("Interface humano contra humano");
			jHumxHum.addActionListener(handler);
		}
		return jHumxHum;
	}

	
	private JMenuItem getJHumxMaq() {
		if (jHumxMaq == null) {
			jHumxMaq = new JMenuItem();
			jHumxMaq.setText("Humano x Maquina");
			jHumxMaq.setMnemonic('M');
			jHumxMaq.setToolTipText("Interface humano contra maquina");
			jHumxMaq.setActionCommand("interf1");
			jHumxMaq.addActionListener(handler);
		}
		return jHumxMaq;
	}
	
	
	public void iniciarPartida(){
		boolean andamento = painelTab.getpartidaEmAndamento();
		int resp = 1;
		if(andamento==true){
			resp = JOptionPane.showConfirmDialog(null,"Partida em andamento. Deseja reiniciar?","Novo Jogo",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
		}	
			if(resp == JOptionPane.YES_OPTION || andamento == false)
				painelTab.iniciarPartida();	
		
	}
	
	public void setInterf(int interf){
		boolean andamento = painelTab.getpartidaEmAndamento();
		int resp = 1;
		if(andamento==true){
			resp = JOptionPane.showConfirmDialog(null,"Partida em andamento. Deseja reiniciar?","Novo Jogo",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
		}	
			if(resp == JOptionPane.YES_OPTION || andamento == false){
				painelTab.setInterface(interf);
				painelTab.iniciarPartida();
			}
		
	}
	
	public void abreJanelaSobre() {
		JFrame sobre = new JanelaSobre();;
		sobre.setVisible(true);
		sobre.pack();
	}
	
	public void abreJanelaAjuda() {
		JFrame ajuda = new JanelaRegras();;
		ajuda.setVisible(true);
		ajuda.pack();
	}
	
	public void sair(){
		int resp = JOptionPane.showConfirmDialog(null,"Deseja realmente encerrar o programa?","Encerrar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(resp == JOptionPane.YES_OPTION)
			System.exit(0);
	}

}