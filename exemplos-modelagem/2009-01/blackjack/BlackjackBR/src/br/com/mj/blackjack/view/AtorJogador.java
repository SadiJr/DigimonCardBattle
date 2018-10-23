package br.com.mj.blackjack.view;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import br.com.mj.blackjack.control.BJViewControl;
import br.com.mj.blackjack.model.JogadaBlackjack;
import br.com.mj.blackjack.model.Jogador;
import br.com.mj.blackjack.model.Mesa;
import br.com.mj.blackjack.view.components.CarteadorPanel;
import br.com.mj.blackjack.view.components.JogadorPanel;
import br.com.mj.blackjack.view.components.MesaPanel;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class AtorJogador extends javax.swing.JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8058516321779435079L;

	private static boolean JOGO_EM_REDE = false;
	
	public static final int ERRO = ERROR_MESSAGE;
	
	public static final int INFORMACAO = INFORMATION_MESSAGE;
	
	public static final String TITULO_JANELA = "Blackjack";
	private final String PAINEL_INICIAL = "painelInicialCard";
	private final String PAINEL_JOGO = "painelJogoCard";
	
	private JMenuBar barra;
	private JMenu jmnJogo;
	private JMenuItem jmiNovoJogo;
	private JButton jbtPassarAVez;
	private JMenuItem jmiConsultarRegra;
	private JMenuItem jmiDesconectar;
	private JMenuItem jmiIniciarPartida;
	private JMenuItem jmiConectar;
	private JMenu jmnJogoOnline;
	private JButton jbtEnviaMensagem;
	private JLabel jlaMsgTela;
	private CarteadorPanel carteadorPanel;
	private JPanel painelInicial;
	private JMenuItem jmiSair;
	private JogadorPanel jogador4Panel;
	private JogadorPanel jogador3Panel;
	private JogadorPanel jogador2Panel;
	private JogadorPanel jogador1Panel;
	private JButton jbtPegarCarta;
	private JPanel botoesPanel;
	private MesaPanel mesaPanel;
	private JPanel painelJogo;
	private CardLayout thisLayout;
	
	private BJViewControl controle;
	
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public AtorJogador(BJViewControl controle) {
		super();
		this.controle = controle;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setAlwaysOnTop(true);
			thisLayout = new CardLayout();
			getContentPane().setLayout(thisLayout);
			
			this.setResizable(false);
			{
				painelInicial = new JPanel(){

					/**
					 * 
					 */
					private static final long serialVersionUID = 8217802985517066136L;

					@Override
					public void paint(Graphics g) {
						Graphics2D g2d = (Graphics2D)g;
						Image bgImage = new ImageIcon("images/blackjack.jpg").getImage();
						g2d.drawImage(bgImage,0,0,null);
					}
					
				};
				getContentPane().add(painelInicial, "painelInicialCard");
			}
			{
				painelJogo = new JPanel();
				BorderLayout painelJogoLayout = new BorderLayout();
				getContentPane().add(painelJogo, "painelJogoCard");
				painelJogo.setLayout(painelJogoLayout);
				painelJogo.setPreferredSize(new java.awt.Dimension(486, 435));
				{
					mesaPanel = new MesaPanel();
					GridBagLayout mesaPanelLayout = new GridBagLayout();
					painelJogo.add(mesaPanel, BorderLayout.CENTER);
					mesaPanel.setPreferredSize(new java.awt.Dimension(496, 401));
					mesaPanel.setSize(500, 421);
					mesaPanelLayout.rowWeights = new double[] {0.1, 0.0, 0.0, 0.0, 0.1};
					mesaPanelLayout.rowHeights = new int[] {7, 34, 88, 54, 7};
					mesaPanelLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.1};
					mesaPanelLayout.columnWidths = new int[] {32, 91, 132, 81, 29, 7, 20};
					mesaPanel.setLayout(mesaPanelLayout);
					
				}
				{
					botoesPanel = new JPanel();
					painelJogo.add(botoesPanel, BorderLayout.SOUTH);
					GridBagLayout botoesPanelLayout = new GridBagLayout();
					botoesPanelLayout.columnWidths = new int[] {175, 134, 7};
					botoesPanelLayout.rowHeights = new int[] {20, 7};
					botoesPanelLayout.columnWeights = new double[] {0.0, 0.0, 0.1};
					botoesPanelLayout.rowWeights = new double[] {0.1, 0.1};
					botoesPanel.setLayout(botoesPanelLayout);
					botoesPanel.setPreferredSize(new java.awt.Dimension(466, 56));
					{
						jbtPegarCarta = new JButton();
						botoesPanel.add(jbtPegarCarta, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));
						jbtPegarCarta.setText("Pegar Carta");
						jbtPegarCarta.addActionListener(this);
					}
					{
						jbtPassarAVez = new JButton();
						botoesPanel.add(jbtPassarAVez, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));
						jbtPassarAVez.setText("Passar a Vez");
						jbtPassarAVez.addActionListener(this);
					}
					{
						jlaMsgTela = new JLabel();
						botoesPanel.add(jlaMsgTela, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 10), 0, 0));						
					}
					{
						jbtEnviaMensagem = new JButton();
						botoesPanel.add(jbtEnviaMensagem, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));
						jbtEnviaMensagem.setText("Envia Mensagem");
						jbtEnviaMensagem.addActionListener(this);
					}
				}
			}
			{
				barra = new JMenuBar();
				setJMenuBar(barra);
				{
					jmnJogo = new JMenu();
					barra.add(jmnJogo);
					jmnJogo.setText("Jogo");
					{
						jmiNovoJogo = new JMenuItem();
						jmnJogo.add(jmiNovoJogo);
						jmiNovoJogo.setText("Novo Jogo");
						jmiNovoJogo.addActionListener(this);
					}
					{
						jmiConsultarRegra = new JMenuItem();
						jmnJogo.add(jmiConsultarRegra);
						jmiConsultarRegra.setText("Consultar Regra");
						jmiConsultarRegra.addActionListener(this);
					}
					{
						jmiSair = new JMenuItem();
						jmnJogo.add(jmiSair);
						jmiSair.setText("Sair");
						jmiSair.addActionListener(this);
					}
				}
				{
					jmnJogoOnline = new JMenu();
					barra.add(jmnJogoOnline);
					jmnJogoOnline.setText("Jogo Online");
					{
						jmiConectar = new JMenuItem();
						jmnJogoOnline.add(jmiConectar);
						jmiConectar.setText("Conectar");
						jmiConectar.addActionListener(this);
					}
					{
						jmiIniciarPartida = new JMenuItem();
						jmnJogoOnline.add(jmiIniciarPartida);
						jmiIniciarPartida.setText("Iniciar Partida Rede");
						jmiIniciarPartida.addActionListener(this);
					}
					{
						jmiDesconectar = new JMenuItem();
						jmnJogoOnline.add(jmiDesconectar);
						jmiDesconectar.setText("Desconectar");
						jmiDesconectar.addActionListener(this);
					}
				}
			}
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostraMensagem(String msg){
		this.mostraMensagem(msg,INFORMACAO);
	}
	
	public void mostraMensagem(String msg,int tipoMensagem){
		showMessageDialog(this, msg,TITULO_JANELA,tipoMensagem);
	}
	public void mostraMensagemTela(String msg){
		this.jlaMsgTela.setText(msg);
	}
	public void confirmaSaida(){
		int result = showConfirmDialog(this, "Deseja mesmo sair?",TITULO_JANELA,YES_NO_OPTION);
		if(result == 0)
			this.controle.sair();
	}
	public void mostraTelaInicial(){
		thisLayout.show(getContentPane(), PAINEL_INICIAL);
	}
	public void novoJogoAction(){
		JOGO_EM_REDE = false;
		this.jbtEnviaMensagem.setVisible(false);
		carteadorPanel = null;
		jogador1Panel = null;
		jogador2Panel = null;
		jogador3Panel = null;
		jogador4Panel = null;
		System.gc();
		thisLayout.show(getContentPane(),PAINEL_JOGO);
		this.controle.novoJogo();
	}
	public void pegarCartaAction(){
		if(JOGO_EM_REDE)
			enviaJogadaRede(JogadaBlackjack.PEGAR);
		
		this.controle.procederLance(JogadaBlackjack.PEGAR);
	}
	public void passarAVezAction(){
		if(JOGO_EM_REDE)
			enviaJogadaRede(JogadaBlackjack.PASSAR);
		
		this.controle.procederLance(JogadaBlackjack.PASSAR);
	}
	
	public void adicionaJogador(Jogador jogador){
		switch(jogador.getNumero()){
			case 0://carteador
					carteadorPanel = new CarteadorPanel(jogador);
					mesaPanel.add(carteadorPanel, new GridBagConstraints(2, 1, 2, 2, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
					carteadorPanel.setVisible(true);
				break;
			case 1:
					jogador1Panel = new JogadorPanel(jogador);
					mesaPanel.add(jogador1Panel, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jogador1Panel.setVisible(true);
				break;
			case 2:
					jogador2Panel = new JogadorPanel(jogador);
					mesaPanel.add(jogador2Panel, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jogador2Panel.setVisible(true);
				break;
			case 3:
					jogador3Panel = new JogadorPanel(jogador);
					mesaPanel.add(jogador3Panel, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jogador3Panel.setVisible(true);
				break;
			case 4:
					jogador4Panel = new JogadorPanel(jogador);
					mesaPanel.add(jogador4Panel, new GridBagConstraints(5, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jogador4Panel.setVisible(true);
				break;
		}
	}
	
	public String getNomeDoJogador(int numero){
		return showInputDialog(this,"Digite o nome do jogador "+numero+": ",TITULO_JANELA,QUESTION_MESSAGE);
	}
	public int getNumeroDeJogadores(){
		String resposta = showInputDialog(this,"Digite o nÃºmero de jogadores:",TITULO_JANELA,QUESTION_MESSAGE);
		int jogadores = -1;
		try{
			jogadores = Integer.parseInt(resposta);
		}catch (NumberFormatException e) {
		}
		return jogadores;
	}
	
	public void conectarAction(){
		JOGO_EM_REDE = true;
		this.jbtEnviaMensagem.setVisible(true);
		conectar();
	}
	public void iniciarPartidaRedeAction(){
	   int nrJogadores = this.controle.getNumeroDeJogadores();
      iniciarPartidaRede(nrJogadores);
	}
	public void desconectarAction(){
		desconectar();
	}
	
	public void enviaMensagemAction(){
		String msg = showInputDialog(this,"Digite a mensagem para ser enviada:",TITULO_JANELA,QUESTION_MESSAGE);
		this.controle.enviaMensagem(msg);
	}
	public void actionPerformed(ActionEvent action){
		if(action.getSource() == jmiNovoJogo){
			novoJogoAction();
		}else if(action.getSource() == jbtPegarCarta){
			pegarCartaAction();
		}else if(action.getSource() == jbtPassarAVez){
			passarAVezAction();
		}else if(action.getSource() == jmiConectar){
			conectarAction();
		}else if(action.getSource() == jmiIniciarPartida){
			iniciarPartidaRedeAction();
		}else if(action.getSource() == jmiDesconectar){
			desconectarAction();
		}else if(action.getSource() == jbtEnviaMensagem){
			enviaMensagemAction();
		}else if(action.getSource() == jmiSair){
			confirmaSaida();
		}else if(action.getSource() == jmiConsultarRegra){
			mostraRegras();
		}
	}
	public void atualizaMaoJogadores(){
		if(carteadorPanel != null) carteadorPanel.atualiza();
		if(jogador1Panel != null) jogador1Panel.atualiza();
		if(jogador2Panel != null) jogador2Panel.atualiza();
		if(jogador3Panel != null) jogador3Panel.atualiza();
		if(jogador4Panel != null) jogador4Panel.atualiza();
	}

	public void iniciarPartidaRede(int nrJogadores) {
		thisLayout.show(getContentPane(),PAINEL_JOGO);
		this.controle.iniciarPartidaRede(nrJogadores);
	}
	
	public void conectar(){
		String nick = showInputDialog(this,"Digite seu nick: ",TITULO_JANELA,QUESTION_MESSAGE);
		String servidor = showInputDialog(this,"Digite o servidor: ",TITULO_JANELA,QUESTION_MESSAGE);
		this.controle.conectar(nick,servidor);
	}
	public void desconectar(){
		this.controle.desconectar();
	}
	public void sincronizaMesa(Mesa mesa){
		mesaPanel.removeAll();
		thisLayout.show(getContentPane(),PAINEL_JOGO);
		for(Jogador jogador : mesa.getJogadores()){
			adicionaJogador(jogador);
		}
		mesaPanel.repaint();
		System.gc();
		habilitaDesabilitaBotoes();
	}
	
	public void habilitaDesabilitaBotoes(){
		boolean minhaVez = this.controle.ehMinhaVez();
		jbtPegarCarta.setEnabled(minhaVez);
		jbtPassarAVez.setEnabled(minhaVez);
	}
	
	public void enviaJogadaRede(JogadaBlackjack jogada){
		this.controle.enviaJogadaRede(jogada);
	}
	
	public void mostraRegras(){
		String regras = 
			":::::::::::::::::::::::::::::Blackjack:::::::::::::::::::::::::::::\n"+
			"Este jogo é uma versão simplificada do jogo Blackjack (ou 21).\n" +
			"O objetivo do jogo, é conseguir a pontuação mais alta, que todos,\n" +
			"porém, inferior a 21. Caso o jogador ultrapasse 21 pontos, ele\n" +
			"estará fora! Existem 2 tipos de combinações que valem mais que 21 pontos.\n" +
			"-Blackjack:\n" +
			"  O jogador possui apenas 1 Ás preto e 1 Valete preto.\n" +
			"-Cinco Cartas Charlie:\n" +
			"  O jogador possui cinco cartas na mão, mas sem ultrapassar 21 pontos.\n\n" +
			"Pontuação das Cartas:\n" +
			"-ÁS: 1 ou 11 (o que melhor for para o jogador).\n" +
			"-Cartas de 1 a 10, valem o próprio número.\n" +
			"-Rei, Dama e Valete valem 10 pontos.";
		mostraMensagem(regras);
	}
}
