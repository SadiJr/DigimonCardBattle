package interfaceGrafica;

import interfaceGrafica.som.ClipPlayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dominioProblema.ImagemDeTabuleiro;
/**
 * @author Wanderson Rigo
 * 
 */
public class InterfaceJogador extends JFrame {
	
	//	 object to play audio clips
	protected ClipPlayer clipPlayer;
	
	protected TratadorEventosPecas tratadorEventos = null;

	protected ControladorJogo controlador;

	private static final long serialVersionUID = 1L;

	public static Icon vazia;

	public static Icon xis;

	public static Icon xisMudado;

	public static Icon bola;

	public static Icon bolaMudada;

	private boolean posicaoInicialSelecionada;

	private boolean posicaoFinalSelecionada;

	private JPanel jContentPane = null;
	
	private JPanel rodape = null;

	private JLabel vPosicao11 = null;

	private JLabel vPosicao12 = null;

	private JLabel vPosicao13 = null;

	private JLabel vPosicao14 = null;

	private JLabel vPosicao15 = null;

	private JLabel vPosicao16 = null;

	private JLabel vPosicao17 = null;

	private JLabel vPosicao18 = null;

	private JLabel vPosicao19 = null;

	private JLabel vPosicao21 = null;

	private JLabel vPosicao22 = null;

	private JLabel vPosicao23 = null;

	private JLabel vPosicao24 = null;

	private JLabel vPosicao25 = null;

	private JLabel vPosicao26 = null;

	private JLabel vPosicao27 = null;

	private JLabel vPosicao28 = null;

	private JLabel vPosicao29 = null;

	private JLabel vPosicao31 = null;

	private JLabel vPosicao32 = null;

	private JLabel vPosicao33 = null;

	private JLabel vPosicao34 = null;

	private JLabel vPosicao35 = null;

	private JLabel vPosicao36 = null;

	private JLabel vPosicao37 = null;

	private JLabel vPosicao38 = null;

	private JLabel vPosicao39 = null;

	private JLabel vPosicao41 = null;

	private JLabel vPosicao42 = null;

	private JLabel vPosicao43 = null;

	private JLabel vPosicao44 = null;

	private JLabel vPosicao45 = null;

	private JLabel vPosicao46 = null;

	private JLabel vPosicao47 = null;

	private JLabel vPosicao48 = null;

	private JLabel vPosicao49 = null;

	private JLabel vPosicao51 = null;

	private JLabel vPosicao52 = null;

	private JLabel vPosicao53 = null;

	private JLabel vPosicao54 = null;

	private JLabel vPosicao55 = null;

	private JLabel vPosicao56 = null;

	private JLabel vPosicao57 = null;

	private JLabel vPosicao58 = null;

	private JLabel vPosicao59 = null;

	protected JLabel mapaVPosicao[][] = new JLabel[5][9];

	private JMenuBar jMenuBar1 = null;

	private JMenu menuJogo = null;
	
	private JMenu menuOpcoes = null;
	
	private JMenu menuAjuda = null;

	private JMenuItem jMenuItem1 = null;
	
	private JMenuItem jMenuItem2 = null;
	
	private JCheckBox checkHabilitarSom = null;
	
	private boolean habilitarSom = false;
		
	private JMenuItem jMenuAjudaItem1 = null;
	
	private JMenuItem jMenuAjudaItem2 = null;

	private JLabel vMensagem = null;
	
	private JTextField pcVerdes = new JTextField();
	private JTextField pcVermelhas = new JTextField();	
	private JTextField pcVerdesCap = new JTextField();
	private JTextField pcVermelhasCap = new JTextField();
	private JTextField verdeJogou = new JTextField();
	private JTextField vermelhoJogou = new JTextField();

	public InterfaceJogador() throws HeadlessException {
		super();
		initialize();
	}
	
	private void initialize() {
		vazia = new ImageIcon(this.getClass().getResource("/img/limpo.gif"));
		xis = new ImageIcon(this.getClass().getResource("/img/xis.gif"));
		xisMudado = new ImageIcon(this.getClass().getResource("/img/xisMudado.gif"));
		bola = new ImageIcon(this.getClass().getResource("/img/bola.gif"));
		bolaMudada = new ImageIcon(this.getClass().getResource("/img/bolaMudada.gif"));
		
		posicaoInicialSelecionada = false;
		posicaoFinalSelecionada = false;
		this.setSize(650, 530);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(getJContentPane(), BorderLayout.CENTER);
		this.getContentPane().add(getRodape(), BorderLayout.SOUTH);
		this.setTitle("Jogo Fanorana"); 
		this.setVisible(true);
		this.setLocation(100, 100);
		this.setResizable(false);		 
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {

			vMensagem = new JLabel();
			vMensagem.setBounds(new Rectangle(25, 10, 200, 20));
			vMensagem.setForeground(Color.red);
			vMensagem.setText("Inicie o Fanorana...");

			vPosicao11 = new JLabel();
			vPosicao11.setName("11");
			vPosicao11.setBounds(new Rectangle(20, 40, 35, 35));
			vPosicao11.setIcon(vazia);

			vPosicao12 = new JLabel();
			vPosicao12.setName("12");
			vPosicao12.setBounds(new Rectangle(90, 40, 35, 35));
			vPosicao12.setIcon(vazia);

			vPosicao13 = new JLabel();
			vPosicao13.setName("13");
			vPosicao13.setIcon(vazia);
			vPosicao13.setBounds(new Rectangle(160, 40, 35, 35));

			vPosicao14 = new JLabel();
			vPosicao14.setName("14");
			vPosicao14.setBounds(new Rectangle(230, 40, 35, 35));
			vPosicao14.setIcon(vazia);

			vPosicao15 = new JLabel();
			vPosicao15.setName("15");
			vPosicao15.setBounds(new Rectangle(300, 40, 35, 35));
			vPosicao15.setIcon(vazia);

			vPosicao16 = new JLabel();
			vPosicao16.setName("16");
			vPosicao16.setIcon(vazia);
			vPosicao16.setBounds(new Rectangle(370, 40, 35, 35));

			vPosicao17 = new JLabel();
			vPosicao17.setName("17");
			vPosicao17.setBounds(new Rectangle(440, 40, 35, 35));
			vPosicao17.setIcon(vazia);

			vPosicao18 = new JLabel();
			vPosicao18.setName("18");
			vPosicao18.setBounds(new Rectangle(510, 40, 35, 35));
			vPosicao18.setIcon(vazia);

			vPosicao19 = new JLabel();
			vPosicao19.setName("19");
			vPosicao19.setIcon(vazia);
			vPosicao19.setBounds(new Rectangle(580, 40, 35, 35));

			vPosicao21 = new JLabel();
			vPosicao21.setName("21");
			vPosicao21.setBounds(new Rectangle(20, 110, 35, 35));
			vPosicao21.setIcon(vazia);

			vPosicao22 = new JLabel();
			vPosicao22.setName("22");
			vPosicao22.setBounds(new Rectangle(90, 110, 35, 35));
			vPosicao22.setIcon(vazia);

			vPosicao23 = new JLabel();
			vPosicao23.setName("23");
			vPosicao23.setBounds(new Rectangle(160, 110, 35, 35));
			vPosicao23.setIcon(vazia);

			vPosicao24 = new JLabel();
			vPosicao24.setName("24");
			vPosicao24.setBounds(new Rectangle(230, 110, 35, 35));
			vPosicao24.setIcon(vazia);

			vPosicao25 = new JLabel();
			vPosicao25.setName("25");
			vPosicao25.setBounds(new Rectangle(300, 110, 35, 35));
			vPosicao25.setIcon(vazia);

			vPosicao26 = new JLabel();
			vPosicao26.setName("26");
			vPosicao26.setIcon(vazia);
			vPosicao26.setBounds(new Rectangle(370, 110, 35, 35));

			vPosicao27 = new JLabel();
			vPosicao27.setName("27");
			vPosicao27.setBounds(new Rectangle(440, 110, 35, 35));
			vPosicao27.setIcon(vazia);

			vPosicao28 = new JLabel();
			vPosicao28.setName("28");
			vPosicao28.setBounds(new Rectangle(510, 110, 35, 35));
			vPosicao28.setIcon(vazia);

			vPosicao29 = new JLabel();
			vPosicao29.setName("29");
			vPosicao29.setIcon(vazia);
			vPosicao29.setBounds(new Rectangle(580, 110, 35, 35));

			vPosicao31 = new JLabel();
			vPosicao31.setName("31");
			vPosicao31.setBounds(new Rectangle(20, 180, 35, 35));
			vPosicao31.setIcon(vazia);

			vPosicao32 = new JLabel();
			vPosicao32.setName("32");
			vPosicao32.setBounds(new Rectangle(90, 180, 35, 35));
			vPosicao32.setIcon(vazia);

			vPosicao33 = new JLabel();
			vPosicao33.setName("33");
			vPosicao33.setBounds(new Rectangle(160, 180, 35, 35));
			vPosicao33.setIcon(vazia);

			vPosicao34 = new JLabel();
			vPosicao34.setName("34");
			vPosicao34.setBounds(new Rectangle(230, 180, 35, 35));
			vPosicao34.setIcon(vazia);

			vPosicao35 = new JLabel();
			vPosicao35.setName("35");
			vPosicao35.setBounds(new Rectangle(300, 180, 35, 35));
			vPosicao35.setIcon(vazia);

			vPosicao36 = new JLabel();
			vPosicao36.setName("36");
			vPosicao36.setIcon(vazia);
			vPosicao36.setBounds(new Rectangle(370, 180, 35, 35));

			vPosicao37 = new JLabel();
			vPosicao37.setName("37");
			vPosicao37.setBounds(new Rectangle(440, 180, 35, 35));
			vPosicao37.setIcon(vazia);

			vPosicao38 = new JLabel();
			vPosicao38.setName("38");
			vPosicao38.setBounds(new Rectangle(510, 180, 35, 35));
			vPosicao38.setIcon(vazia);

			vPosicao39 = new JLabel();
			vPosicao39.setName("39");
			vPosicao39.setIcon(vazia);
			vPosicao39.setBounds(new Rectangle(580, 180, 35, 35));

			vPosicao41 = new JLabel();
			vPosicao41.setName("41");
			vPosicao41.setBounds(new Rectangle(20, 250, 35, 35));
			vPosicao41.setIcon(vazia);

			vPosicao42 = new JLabel();
			vPosicao42.setName("42");
			vPosicao42.setBounds(new Rectangle(90, 250, 35, 35));
			vPosicao42.setIcon(vazia);

			vPosicao43 = new JLabel();
			vPosicao43.setName("43");
			vPosicao43.setBounds(new Rectangle(160, 250, 35, 35));
			vPosicao43.setIcon(vazia);

			vPosicao44 = new JLabel();
			vPosicao44.setName("44");
			vPosicao44.setBounds(new Rectangle(230, 250, 35, 35));
			vPosicao44.setIcon(vazia);

			vPosicao45 = new JLabel();
			vPosicao45.setName("45");
			vPosicao45.setBounds(new Rectangle(300, 250, 35, 35));
			vPosicao45.setIcon(vazia);

			vPosicao46 = new JLabel();
			vPosicao46.setName("46");
			vPosicao46.setIcon(vazia);
			vPosicao46.setBounds(new Rectangle(370, 250, 35, 35));

			vPosicao47 = new JLabel();
			vPosicao47.setName("47");
			vPosicao47.setBounds(new Rectangle(440, 250, 35, 35));
			vPosicao47.setIcon(vazia);

			vPosicao48 = new JLabel();
			vPosicao48.setName("48");
			vPosicao48.setBounds(new Rectangle(510, 250, 35, 35));
			vPosicao48.setIcon(vazia);

			vPosicao49 = new JLabel();
			vPosicao49.setName("49");
			vPosicao49.setIcon(vazia);
			vPosicao49.setBounds(new Rectangle(580, 250, 35, 35));

			vPosicao51 = new JLabel();
			vPosicao51.setName("51");
			vPosicao51.setBounds(new Rectangle(20, 320, 35, 35));
			vPosicao51.setIcon(vazia);

			vPosicao52 = new JLabel();
			vPosicao52.setName("52");
			vPosicao52.setBounds(new Rectangle(90, 320, 35, 35));
			vPosicao52.setIcon(vazia);

			vPosicao53 = new JLabel();
			vPosicao53.setName("53");
			vPosicao53.setBounds(new Rectangle(160, 320, 35, 35));
			vPosicao53.setIcon(vazia);

			vPosicao54 = new JLabel();
			vPosicao54.setName("54");
			vPosicao54.setBounds(new Rectangle(230, 320, 35, 35));
			vPosicao54.setIcon(vazia);

			vPosicao55 = new JLabel();
			vPosicao55.setName("55");
			vPosicao55.setBounds(new Rectangle(300, 320, 35, 35));
			vPosicao55.setIcon(vazia);

			vPosicao56 = new JLabel();
			vPosicao56.setName("56");
			vPosicao56.setIcon(vazia);
			vPosicao56.setBounds(new Rectangle(370, 320, 35, 35));

			vPosicao57 = new JLabel();
			vPosicao57.setName("57");
			vPosicao57.setBounds(new Rectangle(440, 320, 35, 35));
			vPosicao57.setIcon(vazia);

			vPosicao58 = new JLabel();
			vPosicao58.setName("58");
			vPosicao58.setBounds(new Rectangle(510, 320, 35, 35));
			vPosicao58.setIcon(vazia);

			vPosicao59 = new JLabel();
			vPosicao59.setName("59");
			vPosicao59.setIcon(vazia);
			vPosicao59.setBounds(new Rectangle(580, 320, 35, 35));

			jMenuBar1 = new JMenuBar();
			jMenuBar1.add(getMenu());
			jMenuBar1.add(getMenuOpcoes());
			jMenuBar1.add(getMenuAjuda());
			this.setJMenuBar(jMenuBar1);

			mapaVPosicao[0][0] = vPosicao11;
			mapaVPosicao[0][1] = vPosicao12;
			mapaVPosicao[0][2] = vPosicao13;
			mapaVPosicao[0][3] = vPosicao14;
			mapaVPosicao[0][4] = vPosicao15;
			mapaVPosicao[0][5] = vPosicao16;
			mapaVPosicao[0][6] = vPosicao17;
			mapaVPosicao[0][7] = vPosicao18;
			mapaVPosicao[0][8] = vPosicao19;

			mapaVPosicao[1][0] = vPosicao21;
			mapaVPosicao[1][1] = vPosicao22;
			mapaVPosicao[1][2] = vPosicao23;
			mapaVPosicao[1][3] = vPosicao24;
			mapaVPosicao[1][4] = vPosicao25;
			mapaVPosicao[1][5] = vPosicao26;
			mapaVPosicao[1][6] = vPosicao27;
			mapaVPosicao[1][7] = vPosicao28;
			mapaVPosicao[1][8] = vPosicao29;

			mapaVPosicao[2][0] = vPosicao31;
			mapaVPosicao[2][1] = vPosicao32;
			mapaVPosicao[2][2] = vPosicao33;
			mapaVPosicao[2][3] = vPosicao34;
			mapaVPosicao[2][4] = vPosicao35;
			mapaVPosicao[2][5] = vPosicao36;
			mapaVPosicao[2][6] = vPosicao37;
			mapaVPosicao[2][7] = vPosicao38;
			mapaVPosicao[2][8] = vPosicao39;

			mapaVPosicao[3][0] = vPosicao41;
			mapaVPosicao[3][1] = vPosicao42;
			mapaVPosicao[3][2] = vPosicao43;
			mapaVPosicao[3][3] = vPosicao44;

			mapaVPosicao[3][4] = vPosicao45;
			mapaVPosicao[3][5] = vPosicao46;
			mapaVPosicao[3][6] = vPosicao47;
			mapaVPosicao[3][7] = vPosicao48;
			mapaVPosicao[3][8] = vPosicao49;

			mapaVPosicao[4][0] = vPosicao51;
			mapaVPosicao[4][1] = vPosicao52;
			mapaVPosicao[4][2] = vPosicao53;
			mapaVPosicao[4][3] = vPosicao54;
			mapaVPosicao[4][4] = vPosicao55;
			mapaVPosicao[4][5] = vPosicao56;
			mapaVPosicao[4][6] = vPosicao57;
			mapaVPosicao[4][7] = vPosicao58;
			mapaVPosicao[4][8] = vPosicao59;

			tratadorEventos = new TratadorEventosPecas(this, mapaVPosicao);

			vPosicao11.addMouseListener(tratadorEventos);
			vPosicao12.addMouseListener(tratadorEventos);
			vPosicao13.addMouseListener(tratadorEventos);
			vPosicao14.addMouseListener(tratadorEventos);
			vPosicao15.addMouseListener(tratadorEventos);
			vPosicao16.addMouseListener(tratadorEventos);
			vPosicao17.addMouseListener(tratadorEventos);
			vPosicao18.addMouseListener(tratadorEventos);
			vPosicao19.addMouseListener(tratadorEventos);
			vPosicao21.addMouseListener(tratadorEventos);
			vPosicao22.addMouseListener(tratadorEventos);
			vPosicao23.addMouseListener(tratadorEventos);
			vPosicao24.addMouseListener(tratadorEventos);
			vPosicao25.addMouseListener(tratadorEventos);
			vPosicao26.addMouseListener(tratadorEventos);
			vPosicao27.addMouseListener(tratadorEventos);
			vPosicao28.addMouseListener(tratadorEventos);
			vPosicao29.addMouseListener(tratadorEventos);
			vPosicao31.addMouseListener(tratadorEventos);
			vPosicao32.addMouseListener(tratadorEventos);
			vPosicao33.addMouseListener(tratadorEventos);
			vPosicao34.addMouseListener(tratadorEventos);
			vPosicao35.addMouseListener(tratadorEventos);
			vPosicao36.addMouseListener(tratadorEventos);
			vPosicao37.addMouseListener(tratadorEventos);
			vPosicao38.addMouseListener(tratadorEventos);
			vPosicao39.addMouseListener(tratadorEventos);
			vPosicao41.addMouseListener(tratadorEventos);
			vPosicao42.addMouseListener(tratadorEventos);
			vPosicao43.addMouseListener(tratadorEventos);
			vPosicao44.addMouseListener(tratadorEventos);
			vPosicao45.addMouseListener(tratadorEventos);
			vPosicao46.addMouseListener(tratadorEventos);
			vPosicao47.addMouseListener(tratadorEventos);
			vPosicao48.addMouseListener(tratadorEventos);
			vPosicao49.addMouseListener(tratadorEventos);
			vPosicao51.addMouseListener(tratadorEventos);
			vPosicao52.addMouseListener(tratadorEventos);
			vPosicao53.addMouseListener(tratadorEventos);
			vPosicao54.addMouseListener(tratadorEventos);
			vPosicao55.addMouseListener(tratadorEventos);
			vPosicao56.addMouseListener(tratadorEventos);
			vPosicao57.addMouseListener(tratadorEventos);
			vPosicao58.addMouseListener(tratadorEventos);
			vPosicao59.addMouseListener(tratadorEventos);

			jContentPane = new ContainerTabuleiro();
			jContentPane.setLayout(null);
			jContentPane.add(vPosicao11, null);
			jContentPane.add(vPosicao12, null);
			jContentPane.add(vPosicao13, null);
			jContentPane.add(vPosicao14, null);
			jContentPane.add(vPosicao15, null);
			jContentPane.add(vPosicao16, null);
			jContentPane.add(vPosicao17, null);
			jContentPane.add(vPosicao18, null);
			jContentPane.add(vPosicao19, null);

			jContentPane.add(vPosicao21, null);
			jContentPane.add(vPosicao22, null);
			jContentPane.add(vPosicao23, null);
			jContentPane.add(vPosicao24, null);
			jContentPane.add(vPosicao25, null);
			jContentPane.add(vPosicao26, null);
			jContentPane.add(vPosicao27, null);
			jContentPane.add(vPosicao28, null);
			jContentPane.add(vPosicao29, null);

			jContentPane.add(vPosicao31, null);
			jContentPane.add(vPosicao32, null);
			jContentPane.add(vPosicao33, null);
			jContentPane.add(vPosicao34, null);
			jContentPane.add(vPosicao35, null);
			jContentPane.add(vPosicao36, null);
			jContentPane.add(vPosicao37, null);
			jContentPane.add(vPosicao38, null);
			jContentPane.add(vPosicao39, null);

			jContentPane.add(vPosicao41, null);
			jContentPane.add(vPosicao42, null);
			jContentPane.add(vPosicao43, null);
			jContentPane.add(vPosicao44, null);
			jContentPane.add(vPosicao45, null);
			jContentPane.add(vPosicao46, null);
			jContentPane.add(vPosicao47, null);
			jContentPane.add(vPosicao48, null);
			jContentPane.add(vPosicao49, null);

			jContentPane.add(vPosicao51, null);
			jContentPane.add(vPosicao52, null);
			jContentPane.add(vPosicao53, null);
			jContentPane.add(vPosicao54, null);
			jContentPane.add(vPosicao55, null);
			jContentPane.add(vPosicao56, null);
			jContentPane.add(vPosicao57, null);
			jContentPane.add(vPosicao58, null);
			jContentPane.add(vPosicao59, null);

			jContentPane.add(vMensagem, null);
		}
		return jContentPane;
	}
	
	private JPanel getRodape() {
		if (rodape == null) {
			rodape = new JPanel();
			// 3 linhas e 6 colunas com espacamento de 2 entre elas
			rodape.setLayout(new GridLayout(3, 6, 2,2));
			rodape.setBackground(Color.YELLOW);
			rodape.add(new JLabel("Peças Azuis"));
			pcVerdes.setEditable(false);
			rodape.add(pcVerdes);
			rodape.add(new JLabel("em jogo"));
			rodape.add(new JLabel("Peças Amarelas"));
			pcVermelhas.setEditable(false);
			rodape.add(pcVermelhas);
			rodape.add(new JLabel("em jogo"));
			rodape.add(new JLabel("Peças Azuis"));
			pcVerdesCap.setEditable(false);
			rodape.add(pcVerdesCap);
			rodape.add(new JLabel("Capturadas"));
			rodape.add(new JLabel("Peças Amarelas"));
			pcVermelhasCap.setEditable(false);
			rodape.add(pcVermelhasCap);
			rodape.add(new JLabel("Capturadas"));
			rodape.add(new JLabel("Azul jogou"));
			verdeJogou.setEditable(false);
			rodape.add(verdeJogou);
			rodape.add(new JLabel("vezes"));
			rodape.add(new JLabel("Amarelo jogou "));
			vermelhoJogou.setEditable(false);
			rodape.add(vermelhoJogou);
			rodape.add(new JLabel("vezes"));
			//rodape.add(new JLabel("Jogador da Vez"));
			//rodape.add(new JTextField());			
		}
		return rodape;
	}

	public boolean avaliarInterrupcao() {
		boolean afirmativo = false;
		boolean condRepetir = true;
		int resposta;
		while (condRepetir) {
			resposta = JOptionPane.showConfirmDialog(this,
					"Deseja interromper partida em andamento?");
			afirmativo = resposta == JOptionPane.YES_OPTION;
			condRepetir = resposta == JOptionPane.CANCEL_OPTION;
		}		
		vMensagem.setText("");
		return afirmativo;
	}

	public String obterNomeJogador(String ordem) {
		String idJogador = ("jogador" + ordem);
		idJogador = JOptionPane.showInputDialog(this,
				("Insira o nome do jogador" + ordem));

		if (idJogador == null || ("").equals(idJogador)) {
			idJogador = ("jogador" + ordem);
		}
		return idJogador;
	}

//	public boolean obterSimboloJogador(String nomeJogador1) {
//		return (DialogoSimbolo.informaSimbolo("Qual o simbolo do jogador "
//				+ nomeJogador1 + "?"));
//	}

	public int definirQuemComeca(String nomeJogador1) {
		boolean afirmativo = false;
		boolean condRepetir = true;
		int resposta;

		while (condRepetir) {
			resposta = JOptionPane.showConfirmDialog(this,
					(nomeJogador1 + " inicia a partida?"));
			afirmativo = resposta == JOptionPane.YES_OPTION;
			condRepetir = resposta == JOptionPane.CANCEL_OPTION;
		}		
		if (afirmativo) {
			return (1);
		} else {
			return (2);
		}
	}

	public int escolherCaptura() {
		boolean afirmativo = false;
		boolean condRepetir = true;
		int resposta;

		while (condRepetir) {
			resposta = JOptionPane.showConfirmDialog(this,
					("Deseja Captura por APROXIMAÇÃO?"));
			afirmativo = resposta == JOptionPane.YES_OPTION;
			condRepetir = resposta == JOptionPane.CANCEL_OPTION;
		}		
		if (afirmativo) {
			return (1);
		} else {
			return (2);
		}
	}

	public void exibirEstado(ImagemDeTabuleiro estado) {
		int valor = 0;
		// estado = controlador.getTabuleiro().informarEstado();
		vMensagem.setText(estado.getMensagem());
		for (int linha = 1; linha < 6; linha++) {
			for (int coluna = 1; coluna < 10; coluna++) {
				valor = estado.getValor(linha, coluna);
				switch (valor) {
				case 0:
					mapaVPosicao[(linha - 1)][(coluna - 1)]
							.setIcon(InterfaceJogador.vazia);
					break;
				case 1:
					mapaVPosicao[(linha - 1)][(coluna - 1)]
							.setIcon(InterfaceJogador.xis);
					break;
				case 2:
					mapaVPosicao[(linha - 1)][(coluna - 1)]
							.setIcon(InterfaceJogador.bola);
				}
			}			
		}		
	}

	public void notificarIrregularidade(int codigoErro) {
		/*
		 * Valores de Erros possíveis 
		 * 1: Retorno de Posição Ocupada 
		 * 2: Jogador não é o atual
		 * 3: Posição Inválida (Não adjacente)
		 * 4: Partida ja acabou
		 */
		if (codigoErro == 1) {
			JOptionPane.showMessageDialog(this,"Posição ocupada. Jogue novamente.");
			this.setPosicaoInicialSelecionada(false);
			this.setPosicaoFinalSelecionada(false);
			this.tratadorEventos.alterarAparenciaPecaInicial();
			this.tratadorEventos.alterarAparenciaPecaFinal();
		} else if (codigoErro == 2) {
			JOptionPane.showMessageDialog(this, "Peça Jogador Não é o Atual.");
			this.setPosicaoInicialSelecionada(false);
			this.setPosicaoFinalSelecionada(false);
			this.tratadorEventos.alterarAparenciaPecaInicial();
			this.tratadorEventos.alterarAparenciaPecaFinal();
		} else if (codigoErro == 3) {
			JOptionPane.showMessageDialog(this,"Posição Inválida. Escolha uma Adjacente.");
			this.setPosicaoInicialSelecionada(false);
			this.setPosicaoFinalSelecionada(false);
			this.tratadorEventos.alterarAparenciaPecaInicial();
			this.tratadorEventos.alterarAparenciaPecaFinal();
		} else if (codigoErro == 4) {
			JOptionPane.showMessageDialog(this,"O Jogo acabou. Inicie uma nova partida!");
		} else if (codigoErro == 5) {
			JOptionPane.showMessageDialog(this,"Inicie uma nova partida!");
		}

	}


	public void iniciarPartida() {
		if (controlador == null) {
			controlador = new ControladorJogo(this);
		}
		controlador.iniciarPartida();
	}


	public void jogar(int linIni, int colIni, int linFim, int colFim) {
		controlador.jogar(linIni, colIni, linFim, colFim);
	}

	private JMenu getMenu() {
		if (menuJogo == null) {
			menuJogo = new JMenu();
			menuJogo.setText("Jogo");
			menuJogo.setBounds(new Rectangle(1, 0, 57, 21));
			menuJogo.add(getJMenuItem1());
			menuJogo.add(getJMenuItem2());			
		}
		return menuJogo;
	}
	
	private JMenu getMenuOpcoes() {
		if (menuOpcoes == null) {
			menuOpcoes = new JMenu();
			menuOpcoes.setText("Opções");
			menuOpcoes.setBounds(new Rectangle(1, 0, 57, 21));			
			menuOpcoes.add(getJMenuItem3());
		}
		return menuOpcoes;
	}
	
	private JMenu getMenuAjuda() {
		if (menuAjuda == null) {
			menuAjuda = new JMenu();
			menuAjuda.setText("Ajuda");
			menuAjuda.setBounds(new Rectangle(1, 0, 57, 21));
			menuAjuda.add(getJMenuItemAjuda());
			menuAjuda.add(getJMenuItemSobre());
		}
		return menuAjuda;
	}

	// Inicia partida quando aciona opção do Menu
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Iniciar nova partida");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					iniciarPartida();
					// JOptionPane.showMessageDialog(null, "Iniciando!");
				}
			});
		}
		return jMenuItem1;
	}
	
//	 Inicia partida quando aciona opção do Menu
	private JMenuItem getJMenuItem2(){
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("Sair do Jogo");
			jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return jMenuItem2;
	}

	private JCheckBox getJMenuItem3(){
		if (checkHabilitarSom == null) {
			checkHabilitarSom = new JCheckBox("Habilitar Som");			
			checkHabilitarSom.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					int state = e.getStateChange();
			        if (state == ItemEvent.SELECTED) {
			        	habilitarSom = true;
			        } else {
			        	habilitarSom = false;
			        }
				}
			});			
		}
		return checkHabilitarSom;
	}
	
	private JMenuItem getJMenuItemAjuda() {
		if (jMenuAjudaItem1 == null) {
			jMenuAjudaItem1 = new JMenuItem();
			jMenuAjudaItem1.setText("Regras");
			jMenuAjudaItem1
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							JOptionPane
									.showMessageDialog(
											null,
											"O objetivo do jogo é a captura de todas as peças do\n"
													+ "adversário. Consegue-se isso através de movimentos\n"
													+ "estratégicos (em direção às peças de seu oponente ou em\n"
													+ "direção oposta às peças dele) das 22 peças posicionadas\n"
													+ "no tabuleiro. Então tem-se:\n"
													+ "----------------------------CAPTURA POR APROXIMAÇÃO -------------------------\n"
													+ "Se houver(em) peça(s) adversárias vizinhas a posição atual da peça\n"
													+ "que foi movimentada, no sentido da direção da jogada, estas serão\n"
													+ "capturadas até que se encontre uma posição vazia, o fim do tabuleiro\n"
													+ "ou uma posição com a peça do jogador que realizou a jogada.\n"
													+ "----------------------------CAPTURA POR AFASTAMENTO---------------------------\n"
													+ "Após a jogada, caso haja peças do adversário vizinha a posição\n"
													+ "anterior da peça que foi movimentada, no sentido oposto da jogada,\n"
													+ "essas peças serão capturadas até que encontre uma posição vazia,\n"
													+ "o fim do tabuleiro ou uma posição ocupada com a peça do jogador\n"
													+ "que realizou a jogada.");
						}
					});
		}
		return jMenuAjudaItem1;
	}
	
	private JMenuItem getJMenuItemSobre(){
		if (jMenuAjudaItem2 == null) {
			jMenuAjudaItem2 = new JMenuItem();
			jMenuAjudaItem2.setText("Sobre");
			jMenuAjudaItem2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JOptionPane.showMessageDialog(null, 
							"Fanorana é um jogo de tabuleiro de dois jogadores,\n"
							+"originário de Madagascar e baseado no jogo Alquerque.\n"
							+"Cada jogador controla 22 peças, que são posicionadas\n"
							+"antes da partida, deixando vazia a casa central. Os\n"
							+"adversários jogam alternadamente, movimentando uma\n"
							+"peça de cada vez, podendo efetuar captura de peças\n"
							+"adversárias. O jogador que ficar sem peças perde\n"
							+"a partida.\n"
							+"----------------------------AUTORES DESTA IMPLEMENTAÇÃO -------------------------\n"
							+" Nara Sueina\n"
							+" Wanderson Rigo.\n"
					);
				}
			});
		}
		return jMenuAjudaItem2;
	}

	public boolean isPosicaoInicialSelecionada() {
		return posicaoInicialSelecionada;
	}

	public boolean isPosicaoFinalSelecionada() {
		return posicaoFinalSelecionada;
	}

	public void setPosicaoInicialSelecionada(boolean opcao) {
		posicaoInicialSelecionada = opcao;
	}

	public void setPosicaoFinalSelecionada(boolean opcao) {
		posicaoFinalSelecionada = opcao;
	}

	public void setNumPecasVerdesEmJogo(int i) {
		this.pcVerdes.setText(String.valueOf(i));
		
	}

	public void setNumPecasVerdesCapturadas(int i) {
		this.pcVerdesCap.setText(String.valueOf(i));
	}

	public void setNumJogadasVerde(int i) {
		this.verdeJogou.setText(String.valueOf(i));
	}
	
	public void setNumPecasVermelhasEmJogo(int i) {
		this.pcVermelhas.setText(String.valueOf(i));		
	}

	public void setNumPecasVermelhasCapturadas(int i) {
		this.pcVermelhasCap.setText(String.valueOf(i));
	}

	public void setNumJogadasVermelhas(int i) {
		this.vermelhoJogou.setText(String.valueOf(i));
	}

	public void comunicarVencedor(String nomeVencedor) {
		JOptionPane.showMessageDialog(null,"Parabéns " + nomeVencedor + ", você venceu a partida!");
		vMensagem.setText("Inicie o Fanorana...");
	}

	public void emitirSomJogada() {
		if(habilitarSom){			
			 URL mediaFile =  this.getClass().getResource("/interfaceGrafica/som/door.wav");
			 clipPlayer = new ClipPlayer();
			 if ( clipPlayer.openFile(mediaFile) == true ) {       	  
	             // play loaded clip
	             clipPlayer.play();
	             // no replay
	             clipPlayer.setReplay( false );
	          }			
		}		 
	}
	
	public void emitirSomFinalJogo() {
		if(habilitarSom){			
			URL mediaFile =  this.getClass().getResource("/interfaceGrafica/som/bell.wav");
			 clipPlayer = new ClipPlayer();
			 if ( clipPlayer.openFile(mediaFile) == true ) {       	  
	            // play loaded clip
	            clipPlayer.play();
	            // no replay
	            clipPlayer.setReplay( false );
	         }			
		}		 
	}
}
