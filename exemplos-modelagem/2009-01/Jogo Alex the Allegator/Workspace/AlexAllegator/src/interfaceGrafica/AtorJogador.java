package interfaceGrafica;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.lang.reflect.Field;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rede.AtorRede;
import br.ufsc.inf.leobr.cliente.Proxy;
import dominioProblema.ImagemDeTabuleiro;
import dominioProblema.Tabuleiro;

public class AtorJogador extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	protected Tabuleiro tab; 
	protected String idJ1;
	
	private JLabel 
	//Declara todas as posi��es que aparecer�o na tela 
	 vPosicao1_1, vPosicao1_2, vPosicao1_3, vPosicao1_4, vPosicao1_5, vPosicao1_6, vPosicao1_7,    
	 vPosicao1_8, vPosicao1_9, vPosicao1_10, vPosicao2_1, vPosicao2_2, vPosicao2_3, vPosicao2_4,    
	 vPosicao2_5, vPosicao2_6, vPosicao2_7, vPosicao2_8, vPosicao2_9, vPosicao2_10, vPosicao3_1,    
	 vPosicao3_2, vPosicao3_3, vPosicao3_4, vPosicao3_5, vPosicao3_6, vPosicao3_7, vPosicao3_8,    
	 vPosicao3_9, vPosicao3_10, vPosicao4_1, vPosicao4_2, vPosicao4_3, vPosicao4_4, vPosicao4_5,    
	 vPosicao4_6, vPosicao4_7, vPosicao4_8, vPosicao4_9, vPosicao4_10, vPosicao5_1, vPosicao5_2,    
	 vPosicao5_3, vPosicao5_4, vPosicao5_5, vPosicao5_6, vPosicao5_7, vPosicao5_8, vPosicao5_9,    
	 vPosicao5_10, vPosicao6_1, vPosicao6_2, vPosicao6_3, vPosicao6_4, vPosicao6_5, vPosicao6_6,    
	 vPosicao6_7, vPosicao6_8, vPosicao6_9, vPosicao6_10, vPosicao7_1, vPosicao7_2, vPosicao7_3,    
	 vPosicao7_4, vPosicao7_5, vPosicao7_6, vPosicao7_7, vPosicao7_8, vPosicao7_9, vPosicao7_10,    
	 vPosicao8_1, vPosicao8_2, vPosicao8_3, vPosicao8_4, vPosicao8_5, vPosicao8_6, vPosicao8_7,    
	 vPosicao8_8, vPosicao8_9, vPosicao8_10, vPosicao9_1, vPosicao9_2, vPosicao9_3, vPosicao9_4,    
	 vPosicao9_5, vPosicao9_6, vPosicao9_7, vPosicao9_8, vPosicao9_9, vPosicao9_10, vPosicao10_1,    
	 vPosicao10_2, vPosicao10_3, vPosicao10_4, vPosicao10_5, vPosicao10_6, vPosicao10_7,
	 vPosicao10_8, vPosicao10_9, vPosicao10_10, 
	 //Declara a mensagem que mostrar� na tela.
	 vMensagem1, vMensagem2;

	protected JLabel mapaVPosicao[][] = new JLabel[10][10];

	private JMenuBar jMenuBar1 = null;

	private JMenu menuJogo = null;

	private JMenuItem jMenuItemIniciarPartida = null,
			jMenuItemReiniciarPartida = null;

	private AtorRede atorRede;

	public AtorJogador() throws HeadlessException {
		super();
		initialize();
	}

	public AtorJogador(GraphicsConfiguration arg0) {
		super(arg0);
		initialize();
	}

	public AtorJogador(String arg0) throws HeadlessException {
		super(arg0);
		initialize();
	}

	public AtorJogador(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		atorRede = new AtorRede(this);
		tab = new Tabuleiro();
		tab.inicializarPosicoes();
		this.setSize(360, 420);
		this.setContentPane(getJContentPane());
		this.setTitle("Alex the Allegator II");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */	
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			Icon vazia = new ImageIcon(ClassLoader.getSystemResource("vazia.png"));
			Icon azul = new ImageIcon(ClassLoader.getSystemResource("azul.png"));
			Icon verde = new ImageIcon(ClassLoader.getSystemResource("verde.png"));
			Icon esquerda = new ImageIcon(ClassLoader.getSystemResource("esquerda.png"));
			Icon direita = new ImageIcon(ClassLoader.getSystemResource("direita.png"));
			Icon cima = new ImageIcon(ClassLoader.getSystemResource("cima.png"));
			Icon baixo = new ImageIcon(ClassLoader.getSystemResource("baixo.png"));
			Icon inativa = new ImageIcon(ClassLoader.getSystemResource("inativa.png"));

			vMensagem1 = new JLabel();
			vMensagem1.setBounds(new Rectangle(25, 10, 200, 20));
			vMensagem1.setText("Jaime F. S. Alosilla");
			
			vMensagem2 = new JLabel();
			vMensagem2.setBounds(new Rectangle(220, 10, 200, 20));
			vMensagem2.setText("Jorge R. S.Alosilla");
			
			jMenuBar1 = new JMenuBar();
			jMenuBar1.add(getMenuJogo());
			jMenuBar1.add(atorRede.getMenuRede());
			this.setJMenuBar(jMenuBar1);
			
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			
			int xInicial = 20;
			int yinicial = 40;
			
			//Cria os objetos por reflection
			for (int ii = 1;ii < 11; ii++){
				for (int jj = 1;jj < 11; jj++){
					String fieldName = "vPosicao" + ii + "_" + jj;
					try {
						final int linha = ii;
						final int coluna = jj;
						
						boolean ehInativa = tab.informarPosicaoInativa(ii, jj);
						Field f = this.getClass().getDeclaredField(fieldName);
						JLabel label = (JLabel)f.get(this);
						label = new JLabel();
						label.setBounds(new Rectangle(xInicial, yinicial, 30, 30));
						label.setIcon(vazia);
						if(linha == 1){
							label.setIcon(cima);
						}	
						if(linha == 10){
							label.setIcon(baixo);		
						}	
						if(coluna == 1){
							label.setIcon(esquerda);
						}							
						if(coluna == 10){
							label.setIcon(direita);
						}
						
						if(! ehInativa) {
							label.addMouseListener(new java.awt.event.MouseAdapter() {
								public void mouseClicked(java.awt.event.MouseEvent e) {
									click(linha, coluna);
								}
							});								
						}else{
							label.setIcon(inativa);
						}
						
						jContentPane.add(label, null);
						mapaVPosicao[ii-1][jj-1] = label;
						
						if(xInicial == 290){
							xInicial = 20;
							yinicial = yinicial+30;
							continue;
						}
						xInicial = xInicial+30;							
					} catch (SecurityException e1) {
						e1.printStackTrace();
					} catch (NoSuchFieldException e1) {
						e1.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			jContentPane.add(vMensagem1, null);
			jContentPane.add(vMensagem2, null);
		}
		return jContentPane;
	}
	
	public void iniciarPartida() {		
		boolean emAndamento = false;
		boolean simbolo = true;
		boolean autoriza = false;
		String idJ2 = "jogador2";
		
		emAndamento = tab.InformarPartidaEmAndamento();
		if(emAndamento){
			autoriza = this.avaliarInterrupcao();
		}

		if (!emAndamento || autoriza) {
			idJ1 = this.obterIdJogador(" 1");
			simbolo = this.obterSimboloJogador();
			tab.criarJogador(idJ1, simbolo);			
			idJ2 = this.obterIdJogador(" 2");
			tab.criarJogador(idJ2, !simbolo);
			this.estabelecerSequenciamento();
			this.alocarPosicoesIniciais();
			this.exibirEstado();
		}
	}
	
	public boolean avaliarInterrupcao() {
        boolean afirmativo = false;
        boolean condRepetir = true;
        int resposta;
        while (condRepetir) {
                resposta = JOptionPane.showConfirmDialog(this,"Deseja interromper partida em andamento?");
                afirmativo = resposta == JOptionPane.YES_OPTION;
                condRepetir = resposta == JOptionPane.CANCEL_OPTION;
        }
        return afirmativo;
}
	
	public void alocarPosicoesIniciais(){
		tab.procederLance(5, 5);
		tab.procederLance(5, 6);
		tab.setPrimeiraJogada(false);
		tab.procederLance(6, 6);
		tab.procederLance(6, 5);		
	}
	
	public String obterIdJogador(String ordem) {
		String idJogador = ("jogador" + ordem);
		idJogador = JOptionPane.showInputDialog(this,
				("Insira o nome do jogador" + ordem));
		return idJogador;
	}

	public void estabelecerSequenciamento() {
		this.definirOPrimeiro();
		this.exibirEstado();
	}

	public void definirOPrimeiro() {
		boolean afirmativo = false;
		boolean condRepetir = true;
		int resposta;
		while (condRepetir) {
			resposta = JOptionPane.showConfirmDialog(this,
					(idJ1 + " inicia a partida?"));
			afirmativo = resposta == JOptionPane.YES_OPTION;
			condRepetir = resposta == JOptionPane.CANCEL_OPTION;
		}
		
		if (afirmativo) {			
			tab.definirOPrimeiro(1);
		} else {
			tab.definirOPrimeiro(2);
		}		
	}

	public boolean obterSimboloJogador() {
		return (DialogoSimbolo.informaSimbolo("Qual o simbolo do jogador "
				+ idJ1 + "?"));
	}
	
	public void reiniciarPartida() {
		boolean emAndamento = false;
		boolean autoriza = false;
		boolean jogadores = false;
			
		emAndamento = tab.InformarPartidaEmAndamento();
		jogadores = tab.informarExistenciaJogadores();
		
		if (jogadores) {
			if(emAndamento)
				autoriza = this.avaliarInterrupcao();
			if (!emAndamento || autoriza) {
				tab.finalizarJogo();
				this.estabelecerSequenciamento();
				this.alocarPosicoesIniciais();					
				this.exibirEstado();
			}		
		} 
		else {
			this.iniciarPartida();
		}
	}
	 
	public void click(int linha, int coluna) {		
		int resultado = 0;
		if (atorRede.ehJogoRede() && !(atorRede.ehMinhaVez())) {
			JOptionPane.showMessageDialog(this,
					"N�o � sua vez, por favor aguarde!");
			return;
		}

		resultado = tab.procederLance(linha, coluna);

		if (resultado == 0) {
			// envia a jogada para o outro lado da conex�o se o jogo for de rede
			atorRede.enviarJogadaRede(linha, coluna);
			this.exibirEstado();
		} else {
			this.notificarIrregularidade(resultado);
		}
	}
	 
	/**
	 * Envia a jogada para o outro jogador
	 * @param linha
	 * @param coluna
	 */
	public void efetuarJogadaRede(int linha, int coluna) {
		if (atorRede.ehJogoRede()){
			tab.procederLance(linha, coluna);
			this.exibirEstado();
		}
	}
	 
	public void exibirEstado() {
		ImagemDeTabuleiro estado;
		int valor = 0;
		Icon vazia = new ImageIcon(ClassLoader.getSystemResource("vazia.png"));
		Icon azul = new ImageIcon(ClassLoader.getSystemResource("azul.png"));
		Icon verde = new ImageIcon(ClassLoader.getSystemResource("verde.png"));
		estado = tab.informarEstado();
		vMensagem1.setText(estado.informarMensagem1());
		vMensagem2.setText(estado.informarMensagem2());
		for (int linha = 1; linha < 11; linha++) {
			for (int coluna = 1; coluna < 11; coluna++) {
				valor = estado.informarValor(linha, coluna);
				switch (valor) {
				case 0:
					mapaVPosicao[(linha - 1)][(coluna - 1)].setIcon(vazia);
					break;
				case 1:
					mapaVPosicao[(linha - 1)][(coluna - 1)].setIcon(azul);
					break;
				case 2:
					mapaVPosicao[(linha - 1)][(coluna - 1)].setIcon(verde);
				}
			}
		}		
	}
	 
	public void notificarIrregularidade(int codigo) {
		if (codigo == 2) {
			JOptionPane.showMessageDialog(this,
					"Partida encerrada ou n�o iniciada");
		} else {
			if (codigo == 1) {
				JOptionPane.showMessageDialog(this,
				"Posi��o ocupada. Jogue novamente");
			}else{
			JOptionPane.showMessageDialog(this,
					"Fileira Travada. Escolha outra.");
			}
		}		
	}

	/**
	 * This method initializes Jogo
	 * 
	 * @return javax.swing.JMenu
	 */	 
	private JMenu getMenuJogo() {
		if (menuJogo == null) {
			menuJogo = new JMenu();
			menuJogo.setText("Jogo");
			menuJogo.setBounds(new Rectangle(1, 0, 57, 21));
			menuJogo.add(getJMenuItemIniciarPartida());
			menuJogo.add(getJMenuItemReiniciarPartida());
		}
		return menuJogo;
	}

	/**
	 * This method initializes jMenuItem1
	 * 
	 * @return javax.swing.JMenuItem
	 */	 
	private JMenuItem getJMenuItemIniciarPartida() {
		if (jMenuItemIniciarPartida == null) {
			jMenuItemIniciarPartida = new JMenuItem();
			jMenuItemIniciarPartida.setText("iniciar nova partida");
			jMenuItemIniciarPartida
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							iniciarPartida();
						}
					});
		}
		return jMenuItemIniciarPartida;
	}

	/**
	 * This method initializes jMenuItem2
	 * 
	 * @return javax.swing.JMenuItem
	 */	 
	private JMenuItem getJMenuItemReiniciarPartida() {
		if (jMenuItemReiniciarPartida == null) {
			jMenuItemReiniciarPartida = new JMenuItem();
			jMenuItemReiniciarPartida.setText("reiniciar partida");
			jMenuItemReiniciarPartida
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							reiniciarPartida();
						}
					});
		}
		return jMenuItemReiniciarPartida;
	}	
	 
	/**
	 * Inicia uma partida de rede assim que recebe a mensagem do servidor.
	 * @param iniciarComoSolicitante
	 */
	public void iniciarPartidaRede(boolean iniciarComoSolicitante) {

		boolean simbolo = true;

		if (tab == null) {
			tab = new Tabuleiro();
			tab.inicializarPosicoes();
		}

		if (iniciarComoSolicitante) {
			simbolo = true;
		} 
		else {
			simbolo = false;
		}

		idJ1 = Proxy.getInstance().getNomeJogador();

		tab.criarJogador(idJ1, simbolo);

		String idJ2 = atorRede.obtemNomeAdversario();
		tab.criarJogador(idJ2, !simbolo);
		
		if (iniciarComoSolicitante) {
			tab.definirOPrimeiro(1);
		} 
		else {
			tab.definirOPrimeiro(2);
		}
		this.alocarPosicoesIniciais();
		this.exibirEstado();
	}
}