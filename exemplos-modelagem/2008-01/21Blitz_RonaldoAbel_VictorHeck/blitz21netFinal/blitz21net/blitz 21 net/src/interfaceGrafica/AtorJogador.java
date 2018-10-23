package interfaceGrafica;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.net.URL;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ufsc.inf.leobr.annotations.attribute.DirectlyReferredAttribute;
import ufsc.inf.leobr.annotations.classes.DirectlyReferredClass;
import ufsc.inf.leobr.annotations.classes.Ec;
import ufsc.inf.leobr.annotations.method.DirectlyReferredMethod;
import ufsc.inf.leobr.annotations.statement.Statement;
import ufsc.inf.leobr.enumerations.classes.InheritanceType;

import br.ufsc.inf.leobr.cliente.Client;
import br.ufsc.inf.leobr.cliente.EstadoProxy;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import dominioProblema.ImagemDeTabuleiro;
import dominioProblema.JogadaBlitz;
import dominioProblema.Jogador;
import dominioProblema.Tabuleiro;
import javax.swing.JFrame;
/**
 * @author Ronaldo Jose Abel
 * @author Victor Hugo Heck
 * 
 */
@Ec(inheritance=InheritanceType.WHITOUT_INHERITANCE)
@DirectlyReferredClass(Proxy.class)

public class AtorJogador extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	
	protected Tabuleiro tab;  //  @jve:decl-index=0:
	protected String idJ1;
	protected String idJ2;
	
	//linha 1
	private JLabel vPosicao11 = null;
	private JLabel vMensagem11 = null;
	private JLabel vPosicao12 = null;
	private JLabel vPosicao13 = null;
	private JLabel vPosicao14 = null;
	private JLabel vPosicao15 = null;
	//soma da 1a linha:
	private JLabel vPosicao16 = null;
	private JLabel vMensagem16 = null;
	private JLabel lateralesq16 = null;	
	private JLabel lateralesd16 = null;
	private JLabel basep16 = null;
	
	//linha 2
	private JLabel vPosicao21 = null;
	private JLabel vPosicao22 = null;
	private JLabel vPosicao23 = null;
	private JLabel vPosicao24 = null;
	private JLabel vPosicao25 = null;
	//soma da 2a linha:
	private JLabel vPosicao26 = null;
	private JLabel vMensagem26 = null;
	private JLabel lateralesq26 = null;	
	private JLabel lateralesd26 = null;
	private JLabel basep26 = null;
	
	//linha3
	private JLabel vPosicao31 = null;
	private JLabel vPosicao32 = null;
	private JLabel vPosicao33 = null;
	private JLabel vPosicao34 = null;
	private JLabel vPosicao35 = null;
	//soma da 3a linha:
	private JLabel vPosicao36 = null;
	private JLabel vMensagem36 = null;
	private JLabel lateralesq36 = null;	
	private JLabel lateralesd36 = null;
	private JLabel basep36 = null;
	
	//linha4
	private JLabel vPosicao41 = null;
	private JLabel vPosicao42 = null;
	private JLabel vPosicao43 = null;
	private JLabel vPosicao44 = null;
	private JLabel vPosicao45 = null;
	//soma da 4a linha:
	private JLabel vPosicao46 = null;
	private JLabel vMensagem46 = null;
	private JLabel lateralesq46 = null;	
	private JLabel lateralesd46 = null;
	private JLabel basep46 = null;
	
	//linha5
	private JLabel vPosicao51 = null;
	//soma da coluna 1:
	private JLabel vPosicao52 = null;
	private JLabel vMensagem52 = null;
	private JLabel lateralesq52 = null;	
	private JLabel lateralesd52 = null;
	private JLabel basep52 = null;
		
	//soma da coluna 2:
	private JLabel vPosicao53 = null;
	private JLabel vMensagem53 = null;
	private JLabel lateralesq53 = null;	
	private JLabel lateralesd53 = null;
	private JLabel basep53 = null;

	//soma da coluna 3:
	private JLabel vPosicao54 = null;
	private JLabel vMensagem54 = null;
	private JLabel lateralesq54 = null;	
	private JLabel lateralesd54 = null;
	private JLabel basep54 = null;
	
	//soma da coluna 4:
	private JLabel vPosicao55 = null;
	private JLabel vMensagem55 = null;
	private JLabel lateralesq55 = null;	
	private JLabel lateralesd55 = null;
	private JLabel basep55 = null;
	
	private JLabel vPosicao56 = null;
	private JLabel vMensagem2 = null;
	private JLabel lateralesq = null;	
	private JLabel lateralesd = null;
	private JLabel basep56 = null;
	
	protected JLabel mapaVPosicao[][] = new JLabel[5][6];

    private JMenuBar jMenuBar1 = null;
	
	private JMenu menuJogo = null;

	private JMenuItem jMenuItem1 = null;

	private JMenuItem jMenuItem2 = null;

	private JLabel vMensagem = null;
// colado1
	//private JMenuBar jMenuBar1 = null;

	//private JMenu menuJogo = null;

	private JMenuItem jMenuItemIniciarPartida = null,
			jMenuItemReiniciarPartida = null;

	private boolean ehJogoRede = false;

	private AtorRede atorRede;

	private Jogador jogadorPapel;

	
	
//fim colado1	
	


	public AtorJogador() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		initialize();
	}

	public AtorJogador(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public AtorJogador(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public AtorJogador(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}


	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		atorRede=new AtorRede(this);
		this.setSize(500, 640);
		this.setContentPane(getJContentPane());
		this.setTitle("Blitz 21 Net REDE 2008");
		tab = new Tabuleiro();
		tab.iniciar();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			Icon xis = new ImageIcon(ClassLoader.getSystemResource("xis.gif"));
			Icon bola = new ImageIcon(ClassLoader.getSystemResource("bola.gif"));
			Icon vazia = new ImageIcon(ClassLoader.getSystemResource("vazia.gif"));
			Icon vazia2 = new ImageIcon(ClassLoader.getSystemResource("vazia2.gif"));
			Icon vazia3 = new ImageIcon(ClassLoader.getSystemResource("vazia3.gif"));
			Icon topo = new ImageIcon(ClassLoader.getSystemResource("topo.gif"));
			Icon base = new ImageIcon(ClassLoader.getSystemResource("base.gif"));
			Icon lat = new ImageIcon(ClassLoader.getSystemResource("lat.gif"));
			Icon lat2 = new ImageIcon(ClassLoader.getSystemResource("lat.gif"));
			
			vMensagem = new JLabel();
			vMensagem.setBounds(new Rectangle(25, 10, 400, 20));
			vMensagem.setText("Bem Vindo ao 21 Blitz -agora em rede");
			
			//linha 1
			vPosicao11 = new JLabel();
			vPosicao11.setBounds(new Rectangle(20, 40, 74, 98));
			vPosicao11.setIcon(vazia2);				
			vMensagem11=new JLabel();
			vMensagem11.setBounds(new Rectangle(20, 40, 74, 98));
			vMensagem11.setText("0");	
					
			vPosicao11.addMouseListener(new java.awt.event.MouseAdapter() {
			//public void mouseClicked(java.awt.event.MouseEvent e) {click(1,1);}
			});
					
			vPosicao12 = new JLabel();
			vPosicao12.setBounds(new Rectangle(94, 40, 74, 98));
			vPosicao12.setIcon(vazia);
			vPosicao12.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
					click(1,2); 
				}
			});
			
			vPosicao13 = new JLabel();
			vPosicao13.setIcon(vazia);
			//vPosicao13.setIcon(new ImageIcon("C:\\21blitz\\11-E.JPG"));
			vPosicao13.setBounds(new Rectangle(168, 40, 74, 98));
			vPosicao13.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {click(1,3);}});
			vPosicao14 = new JLabel();
			vPosicao14.setIcon(vazia);
			vPosicao14.setBounds(new Rectangle(242, 40, 74, 98));
			vPosicao14.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(1,4); 
				}
			});
			vPosicao15 = new JLabel();
			vPosicao15.setIcon(vazia);
			vPosicao15.setBounds(new Rectangle(316, 40, 74, 98));
			vPosicao15.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(1,5); 
				}
			});
			/*vPosicao16 = new JLabel();
			vPosicao16.setIcon(vazia2);
			vPosicao16.setBounds(new Rectangle(390, 40, 74, 98));*/
			vPosicao16 = new JLabel();
			vPosicao16.setIcon(topo);
			vPosicao16.setBounds(new Rectangle(390, 40, 74, 20));
			lateralesq16 = new JLabel();
			lateralesq16.setIcon(lat);
			lateralesq16.setBounds(new Rectangle(390, 60, 8, 53));
			lateralesd16 = new JLabel();
			lateralesd16.setIcon(lat2);
			lateralesd16.setBounds(new Rectangle(453, 60, 8, 53));
			basep16 = new JLabel();
			basep16.setIcon(base);
			basep16.setText("ronaldo");
			basep16.setBounds(new Rectangle(390,113 , 74, 23));
			vMensagem16=new JLabel();
			vMensagem16.setBounds(new Rectangle(420, 68, 40, 22));
			vMensagem16.setText("0");
			
			
			
			
			
			vPosicao16.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
					click(1,6); 
				}
			});
			//linha 2
			vPosicao21 = new JLabel();
			vPosicao21.setBounds(new Rectangle(20, 138, 74, 98));
			vPosicao21.setIcon(vazia2);				
			vPosicao21.addMouseListener(new java.awt.event.MouseAdapter() {
			//	public void mouseClicked(java.awt.event.MouseEvent e) {
			//		click(2,1); 
			//	}
			});
			vPosicao22 = new JLabel();
			vPosicao22.setBounds(new Rectangle(94, 138, 74, 98));
			vPosicao22.setIcon(vazia);
			vPosicao22.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(2,2); 
				}
			});
			vPosicao23 = new JLabel();
			vPosicao23.setIcon(vazia);
			vPosicao23.setBounds(new Rectangle(168, 138, 74, 98));
			vPosicao23.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(2,3); 
				}
			});
			vPosicao24 = new JLabel();
			vPosicao24.setIcon(vazia);
			vPosicao24.setBounds(new Rectangle(242, 138, 74, 98));
			vPosicao24.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(2,4); 
				}
			});
			vPosicao25 = new JLabel();
			vPosicao25.setIcon(vazia);
			vPosicao25.setBounds(new Rectangle(316, 138, 74, 98));
			vPosicao25.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(2,5); 
				}
			});
			/*vPosicao26 = new JLabel();
			vPosicao26.setIcon(vazia2);
			vPosicao26.setBounds(new Rectangle(390, 138, 74, 98));*/
			
			vPosicao26 = new JLabel();
			vPosicao26.setIcon(topo);
			vPosicao26.setBounds(new Rectangle(390, 138, 74, 20));
			lateralesq26 = new JLabel();
			lateralesq26.setIcon(lat);
			lateralesq26.setBounds(new Rectangle(390, 158, 8, 53));
			lateralesd26 = new JLabel();
			lateralesd26.setIcon(lat2);
			lateralesd26.setBounds(new Rectangle(453, 158, 8, 53));
			basep26 = new JLabel();
			basep26.setIcon(base);
			basep26.setText("ronaldo");
			basep26.setBounds(new Rectangle(390,211 , 74, 23));
			vMensagem26=new JLabel();
			vMensagem26.setBounds(new Rectangle(420, 166, 40, 22));
			vMensagem26.setText("0");
			
			vPosicao26.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(2,6); 
				}
			});
			
			// linha 3
			
			vPosicao31 = new JLabel();
			vPosicao31.setBounds(new Rectangle(20,236 , 74, 98));
			vPosicao31.setIcon(vazia2);				
			vPosicao31.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(3,1); 
				}
			});
			vPosicao32 = new JLabel();
			vPosicao32.setBounds(new Rectangle(94, 236, 74, 98));
			vPosicao32.setIcon(vazia);
			vPosicao32.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(3,2); 
				}
			});
			vPosicao33 = new JLabel();
			vPosicao33.setIcon(vazia);
			vPosicao33.setBounds(new Rectangle(168, 236, 74, 98));
			vPosicao33.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(3,3); 
				}
			});
			vPosicao34 = new JLabel();
			vPosicao34.setIcon(vazia);
			vPosicao34.setBounds(new Rectangle(242, 236, 74, 98));
			vPosicao34.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(3,4); 
				}
			});
			vPosicao35 = new JLabel();
			vPosicao35.setIcon(vazia);
			vPosicao35.setBounds(new Rectangle(316, 236, 74, 98));
			vPosicao35.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(3,5); 
				}
			});
		/*	vPosicao36 = new JLabel();
			vPosicao36.setIcon(vazia2);
			vPosicao36.setBounds(new Rectangle(390, 236, 74, 98));*/
			
			vPosicao36 = new JLabel();
			vPosicao36.setIcon(topo);
			vPosicao36.setBounds(new Rectangle(390, 236, 74, 20));
			lateralesq36 = new JLabel();
			lateralesq36.setIcon(lat);
			lateralesq36.setBounds(new Rectangle(390, 256, 8, 53));
			lateralesd36 = new JLabel();
			lateralesd36.setIcon(lat2);
			lateralesd36.setBounds(new Rectangle(453, 256, 8, 53));
			basep36 = new JLabel();
			basep36.setIcon(base);
			basep36.setText("");
			basep36.setBounds(new Rectangle(390,309 , 172, 23));
			vMensagem36=new JLabel();
			vMensagem36.setBounds(new Rectangle(420, 264, 40, 22));
			vMensagem36.setText("0");
			
			vPosicao36.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(3,6); 
				}
			});
			
			//linha 4
			
			vPosicao41 = new JLabel();
			vPosicao41.setBounds(new Rectangle(20, 334, 74, 98));
			vPosicao41.setIcon(vazia2);				
			vPosicao41.addMouseListener(new java.awt.event.MouseAdapter() {
			/**	public void mouseClicked(java.awt.event.MouseEvent e) {
					click(4,1); 
				}**/
			});
			vPosicao42 = new JLabel();
			vPosicao42.setBounds(new Rectangle(94, 334, 74, 98));
			vPosicao42.setIcon(vazia);
			vPosicao42.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(4,2); 
				}
			});
			vPosicao43 = new JLabel();
			vPosicao43.setIcon(vazia);
			vPosicao43.setBounds(new Rectangle(168, 334, 74, 98));
			vPosicao43.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(4,3); 
				}
			});
			vPosicao44 = new JLabel();
			vPosicao44.setIcon(vazia);
			vPosicao44.setBounds(new Rectangle(242, 334, 74, 98));
			vPosicao44.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(4,4); 
				}
			});
			vPosicao45 = new JLabel();
			vPosicao45.setIcon(vazia);
			vPosicao45.setBounds(new Rectangle(316, 334, 74, 98));
			vPosicao45.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(4,5); 
				}
			});
			/*vPosicao46 = new JLabel();
			vPosicao46.setIcon(vazia2);
			vPosicao46.setBounds(new Rectangle(390, 334, 74, 98));*/
			
			vPosicao46 = new JLabel();
			vPosicao46.setIcon(topo);
			vPosicao46.setBounds(new Rectangle(390, 334, 74, 20));
			lateralesq46 = new JLabel();
			lateralesq46.setIcon(lat);
			lateralesq46.setBounds(new Rectangle(390, 354, 8, 53));
			lateralesd46 = new JLabel();
			lateralesd46.setIcon(lat2);
			lateralesd46.setBounds(new Rectangle(453, 354, 8, 53));
			basep46 = new JLabel();
			basep46.setIcon(base);
			basep46.setText("");
			basep46.setBounds(new Rectangle(390,407 , 172, 23));
			vMensagem46=new JLabel();
			vMensagem46.setBounds(new Rectangle(420, 362, 40, 22));
			vMensagem46.setText("0");
			
			vPosicao46.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(4,6); 
				}
			});
			
			//linha 5
			
			
			vPosicao51 = new JLabel();
			vPosicao51.setBounds(new Rectangle(20, 432, 74, 98));
			vPosicao51.setIcon(vazia2);				
			vPosicao51.addMouseListener(new java.awt.event.MouseAdapter() {
			/**	public void mouseClicked(java.awt.event.MouseEvent e) {
					click(5,1); 
				}**/
			});
			/*vPosicao52 = new JLabel();
			vPosicao52.setBounds(new Rectangle(94, 432, 74, 98));
			vPosicao52.setIcon(vazia2);*/
			vPosicao52 = new JLabel();
			vPosicao52.setIcon(topo);
			vPosicao52.setBounds(new Rectangle(94, 432, 74, 20));
			lateralesq52 = new JLabel();
			lateralesq52.setIcon(lat);
			lateralesq52.setBounds(new Rectangle(94, 452, 8, 53));
			lateralesd52 = new JLabel();
			lateralesd52.setIcon(lat2);
			lateralesd52.setBounds(new Rectangle(157, 452, 8, 53));
			basep52 = new JLabel();
			basep52.setIcon(base);
			basep52.setBounds(new Rectangle(94,505 , 74, 23));
			vMensagem52=new JLabel();
			vMensagem52.setBounds(new Rectangle(124, 460, 40, 22));
			vMensagem52.setText("0");
			
			
			
			
			
			
			
			
			
			vPosicao52.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(5,2); 
				}
			});
			/*vPosicao53 = new JLabel();
			vPosicao53.setIcon(vazia2);
			vPosicao53.setBounds(new Rectangle(168, 432, 74, 98));*/
			//
			vPosicao53 = new JLabel();
			vPosicao53.setIcon(topo);
			vPosicao53.setBounds(new Rectangle(168, 432, 74, 20));
			lateralesq53 = new JLabel();
			lateralesq53.setIcon(lat);
			lateralesq53.setBounds(new Rectangle(168, 452, 8, 53));
			lateralesd53 = new JLabel();
			lateralesd53.setIcon(lat2);
			lateralesd53.setBounds(new Rectangle(231, 452, 8, 53));
			basep53 = new JLabel();
			basep53.setIcon(base);
			basep53.setBounds(new Rectangle(168,505 , 74, 23));
			vMensagem53=new JLabel();
			vMensagem53.setBounds(new Rectangle(198, 460, 40, 22));
			vMensagem53.setText("0");
			
			
			//
			vPosicao53.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(5,3); 
				}
			});
		/*	vPosicao54 = new JLabel();
			vPosicao54.setIcon(vazia2);
			vPosicao54.setBounds(new Rectangle(242, 432, 74, 98));*/
			vPosicao54 = new JLabel();
			vPosicao54.setIcon(topo);
			vPosicao54.setBounds(new Rectangle(242, 432, 74, 20));
			lateralesq54 = new JLabel();
			lateralesq54.setIcon(lat);
			lateralesq54.setBounds(new Rectangle(242, 452, 8, 53));
			lateralesd54 = new JLabel();
			lateralesd54.setIcon(lat2);
			lateralesd54.setBounds(new Rectangle(305, 452, 8, 53));
			basep54 = new JLabel();
			basep54.setIcon(base);
			basep54.setBounds(new Rectangle(242,505 , 74, 23));
			vMensagem54=new JLabel();
			vMensagem54.setBounds(new Rectangle(272, 460, 40, 22));
			vMensagem54.setText("0");
			vPosicao54.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(5,4); 
				}
			});
			/*vPosicao55 = new JLabel();
			vPosicao55.setIcon(vazia2);
			vPosicao55.setBounds(new Rectangle(316, 432, 74, 98));*/
			vPosicao55 = new JLabel();
			vPosicao55.setIcon(topo);
			vPosicao55.setBounds(new Rectangle(316, 432, 74, 20));
			lateralesq55 = new JLabel();
			lateralesq55.setIcon(lat);
			lateralesq55.setBounds(new Rectangle(316, 452, 8, 53));
			lateralesd55 = new JLabel();
			lateralesd55.setIcon(lat2);
			lateralesd55.setBounds(new Rectangle(379, 452, 8, 53));
			basep55 = new JLabel();
			basep55.setIcon(base);
			basep55.setBounds(new Rectangle(316,505 , 74, 23));
			vMensagem55=new JLabel();
			vMensagem55.setBounds(new Rectangle(346, 460, 40, 22));
			vMensagem55.setText("0");
			vPosicao55.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(5,5); 
				}
			});
			vPosicao56 = new JLabel();
			vPosicao56.setIcon(topo);
			vPosicao56.setBounds(new Rectangle(390, 432, 74, 20));
			lateralesq = new JLabel();
			lateralesq.setIcon(lat);
			lateralesq.setBounds(new Rectangle(390, 452, 8, 53));
			lateralesd = new JLabel();
			lateralesd.setIcon(lat2);
			lateralesd.setBounds(new Rectangle(453, 452, 8, 53));
			basep56 = new JLabel();
			basep56.setIcon(base);
			basep56.setText("ronaldo");
			basep56.setBounds(new Rectangle(390,505 , 74, 23));
			vMensagem2=new JLabel();
			vMensagem2.setBounds(new Rectangle(420, 460, 40, 22));
			vMensagem2.setText("0");
			vPosicao56.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					click(5,6); 
				}
			});
			//colado2
			jMenuBar1 = new JMenuBar();
			jMenuBar1.add(getMenuJogo());
			jMenuBar1.add(atorRede.getMenuRede());
			this.setJMenuBar(jMenuBar1);
            //fim colado2
			
			
			//jMenuBar1 = new JMenuBar();
			//jMenuBar1.add(getMenu());
		   // this.setJMenuBar(jMenuBar1);

			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(vPosicao11, null);
			jContentPane.add(vPosicao12, null);
			jContentPane.add(vPosicao13, null);
			jContentPane.add(vPosicao14, null);
			jContentPane.add(vPosicao15, null);
			
			jContentPane.add(vPosicao16, null);
			jContentPane.add(lateralesq16, null);
			jContentPane.add(lateralesd16, null);
			jContentPane.add(basep16, null);
			jContentPane.add(vMensagem16, null);
			
			
			
			
			jContentPane.add(vPosicao21, null);
			jContentPane.add(vPosicao22, null);
			jContentPane.add(vPosicao23, null);
			jContentPane.add(vPosicao24, null);
			jContentPane.add(vPosicao25, null);
			
			jContentPane.add(vPosicao26, null);
			jContentPane.add(lateralesq26, null);
			jContentPane.add(lateralesd26, null);
			jContentPane.add(basep26, null);
			jContentPane.add(vMensagem26, null);
			
			
			jContentPane.add(vPosicao31, null);
			jContentPane.add(vPosicao32, null);
			jContentPane.add(vPosicao33, null);
			jContentPane.add(vPosicao34, null);
			jContentPane.add(vPosicao35, null);
			
			jContentPane.add(vPosicao36, null);
			jContentPane.add(lateralesq36, null);
			jContentPane.add(lateralesd36, null);
			jContentPane.add(basep36, null);
			jContentPane.add(vMensagem36, null);
			
			
			
			jContentPane.add(vPosicao41, null);
			jContentPane.add(vPosicao42, null);
			jContentPane.add(vPosicao43, null);
			jContentPane.add(vPosicao44, null);
			jContentPane.add(vPosicao45, null);
			
			jContentPane.add(vPosicao46, null);
			jContentPane.add(lateralesq46, null);
			jContentPane.add(lateralesd46, null);
			jContentPane.add(basep46, null);
			jContentPane.add(vMensagem46, null);
			
			jContentPane.add(vPosicao51, null);
			
			jContentPane.add(vPosicao52, null);
			jContentPane.add(lateralesq52, null);
			jContentPane.add(lateralesd52, null);
			jContentPane.add(basep52, null);
			jContentPane.add(vMensagem52, null);
			
			
			//
			jContentPane.add(vPosicao53, null);
			jContentPane.add(lateralesq53, null);
			jContentPane.add(lateralesd53, null);
			jContentPane.add(basep53, null);
			jContentPane.add(vMensagem53, null);
			
			
			
			
			//
			jContentPane.add(vPosicao54, null);
			jContentPane.add(lateralesq54, null);
			jContentPane.add(lateralesd54, null);
			jContentPane.add(basep54, null);
			jContentPane.add(vMensagem54, null);
			
			//
			jContentPane.add(vPosicao55, null);
			jContentPane.add(lateralesq55, null);
			jContentPane.add(lateralesd55, null);
			jContentPane.add(basep55, null);
			jContentPane.add(vMensagem55, null);
			//
			jContentPane.add(vPosicao56, null);
			
			jContentPane.add(lateralesq, null);
			jContentPane.add(lateralesd, null);
			jContentPane.add(basep56, null);
			jContentPane.add(vMensagem2, null);
		//	jContentPane.add(vPosicao65, null);
		//	jContentPane.add(vPosicao66, null);			
			
					
			
			mapaVPosicao[0][0] = vPosicao11;
			mapaVPosicao[0][1] = vPosicao12;
			mapaVPosicao[0][2] = vPosicao13;
			mapaVPosicao[0][3] = vPosicao14;
			mapaVPosicao[0][4] = vPosicao15;
			mapaVPosicao[0][5] = vPosicao16;
			
			
			
						
			mapaVPosicao[1][0] = vPosicao21;
			mapaVPosicao[1][1] = vPosicao22;
			mapaVPosicao[1][2] = vPosicao23;
			mapaVPosicao[1][3] = vPosicao24;
			mapaVPosicao[1][4] = vPosicao25;
			mapaVPosicao[1][5] = vPosicao26;
			
			
			mapaVPosicao[2][0] = vPosicao31;
			mapaVPosicao[2][1] = vPosicao32;
			mapaVPosicao[2][2] = vPosicao33;
			mapaVPosicao[2][3] = vPosicao34;
			mapaVPosicao[2][4] = vPosicao35;
			mapaVPosicao[2][5] = vPosicao36;
			
			mapaVPosicao[3][0] = vPosicao41;
			mapaVPosicao[3][1] = vPosicao42;
			mapaVPosicao[3][2] = vPosicao43;
			mapaVPosicao[3][3] = vPosicao44;
			mapaVPosicao[3][4] = vPosicao45;
			mapaVPosicao[3][5] = vPosicao46;
			
			mapaVPosicao[4][0] = vPosicao51;
			mapaVPosicao[4][1] = vPosicao52;
			mapaVPosicao[4][2] = vPosicao53;
			mapaVPosicao[4][3] = vPosicao54;
			mapaVPosicao[4][4] = vPosicao55;
			mapaVPosicao[4][5] = vPosicao56;
			

			jContentPane.add(vMensagem, null);
		}
		return jContentPane;
	}
	 
	public void iniciarPartida() {
		ehJogoRede = false;
		boolean emAndamento = false;
		boolean autoriza = true;
		boolean contraPrograma = true;
		boolean simbolo = true;		
		String idJ2 = "Chuck Norris - Forever";
		
		if (tab == null){
			tab = new Tabuleiro();
			tab.iniciar();
		};
		emAndamento = tab.InformarPartidaEmAndamento();
		if (emAndamento) {
			autoriza = this.avaliarInterrupcao();			
		};
		if (!emAndamento || autoriza){
			contraPrograma = this.avaliarContraPrograma();
			idJ1 = this.obterIdJogador("1");
			simbolo = this.obterSimboloJogador();
			tab.criarJogadorHumano(idJ1, simbolo);
			if (contraPrograma) {
				tab.criarJogadorAutomatico(idJ2, !simbolo);		
			}
			else {
				idJ2 = this.obterIdJogador("2");
				tab.criarJogadorHumano(idJ2, !simbolo);	
			};
			this.estabelecerSequenciamento();				
		};
	}
	 
	public boolean avaliarInterrupcao() {
		boolean afirmativo = false;
		boolean condRepetir = true;
		int resposta;
		while (condRepetir) {
			resposta = JOptionPane.showConfirmDialog(this, "Deseja interromper partida em andamento?");
			afirmativo = resposta == JOptionPane.YES_OPTION;
			condRepetir = resposta == JOptionPane.CANCEL_OPTION;
		};
		return afirmativo;
}

	public boolean avaliarContraPrograma() {
		boolean afirmativo = false;
		boolean condRepetir = true;
		int resposta;
		while (condRepetir) {
			resposta = JOptionPane.showConfirmDialog(this, "Deseja jogar Sozinho?");
			afirmativo = resposta == JOptionPane.YES_OPTION;
			condRepetir = resposta == JOptionPane.CANCEL_OPTION;
		};
		return afirmativo;
}
 
	public String obterIdJogador(String ordem) {
		String idJogador = ("jogador" + ordem);
		idJogador = JOptionPane.showInputDialog(this, ("Insira o nome do jogador" + ordem));
		return idJogador;
	}
	 
	public void estabelecerSequenciamento() {
		this.definirOPrimeiro();
		this.exibirEstado();
	}
	 
	
	 
	public boolean obterSimboloJogador2() {
	      // return (DialogoSimbolo.informaSimbolo("Qual o simbolo do jogador " + idJ1 + "?"));
		return false;
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
		;
		if (afirmativo) {
			tab.definirOPrimeiro(1000);
		} else {
			tab.definirOPrimeiro(2000);
		}
		;
	}

	public boolean obterSimboloJogador() {
		return (DialogoSimbolo.informaSimbolo("Qual o simbolo do jogador "
				+ idJ1 + "?"));
		// return false;
	}

	 
	public void reiniciarPartida() {
		boolean emAndamento = false;
		boolean autoriza = false;
		boolean jogadores = false;
		if (tab == null) {
			this.iniciarPartida();
		} else { // ;
			emAndamento = tab.InformarPartidaEmAndamento();
			jogadores = tab.informarExistenciaJogadores();
			if (jogadores) {
				if (emAndamento) {
					autoriza = this.avaliarInterrupcao();
				}
				;
				if (!emAndamento || autoriza) {
					this.estabelecerSequenciamento();
				}
				;
			} else {
				this.iniciarPartida();
			}
			;
		}
		;
	}

	/**
	 * Efetua a jogada no modelo.
	 * 
	 * @param linha
	 * @param coluna
	 * @param carta
	 */
	 
	public void click(int linha, int coluna) {
		int carta=tab.getCartaE();
		if (ehJogoRede && !(jogadorPapel.informarDaVez())) {
			JOptionPane.showMessageDialog(this,
					"Não é sua vez, por favor aguarde!");
			return;
		}

		int resultado = 0;
		if (ehJogoRede){
			
		resultado=tab.clickRede(linha, coluna,carta);	
		
		}else{
		resultado = tab.click(linha, coluna);}
	
		
		if (resultado == 0) {
			
		       if(ehJogoRede){
			// envia a jogada para o outro lado da conexão
			
			this.enviarJogadaRede(linha, coluna,carta);}
     
			this.exibirEstado();
		} else {
			this.notificarIrregularidade(resultado);
		}
		;
	}

	/**
	 * Se o jogo estiver sendo realizado em rede, envia a jogada para os outros
	 * jogadores
	 * 
	 * @param linha
	 * @param coluna
	 */
	 
@DirectlyReferredMethod(classe=Proxy.class,name="enviaJogada",paramTypes={Jogada.class})
	public void enviarJogadaRede(int linha, int coluna, int carta) {
		if (ehJogoRede) {
			
			Jogada jg = new JogadaBlitz(linha, coluna,carta);
			
			try {
				Proxy.getInstance().enviaJogada(jg);
				
			} catch (NaoJogandoException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
		}
	}

	 
	public void efetuarJogadaRede(int linha, int coluna, int carta) {
		if (ehJogoRede) {
			
			tab.clickRede(linha, coluna,carta);
			
			this.exibirEstado();

		}
	}

	
	 
	public void exibirEstado() {
		ImagemDeTabuleiro estado;
		int valor = 0;
		Icon vazia = new ImageIcon(ClassLoader.getSystemResource("vazia.gif"));
		Icon vazia2 = new ImageIcon(ClassLoader.getSystemResource("vazia2.gif"));
		Icon xis = new ImageIcon(ClassLoader.getSystemResource("xis.gif"));
		Icon bola = new ImageIcon(ClassLoader.getSystemResource("bola.gif"));
		Icon piupiu = new ImageIcon(ClassLoader.getSystemResource("piu-piu.jpg"));
		Icon C1O=new ImageIcon(ClassLoader.getSystemResource("1-O.JPG"));
		Icon C2O=new ImageIcon(ClassLoader.getSystemResource("2-O.JPG"));
		Icon C3O=new ImageIcon(ClassLoader.getSystemResource("3-O.JPG"));
		Icon C4O=new ImageIcon(ClassLoader.getSystemResource("4-O.JPG"));
		Icon C5O=new ImageIcon(ClassLoader.getSystemResource("5-O.JPG"));
		Icon C6O=new ImageIcon(ClassLoader.getSystemResource("6-O.JPG"));
		Icon C7O=new ImageIcon(ClassLoader.getSystemResource("7-O.JPG"));
		Icon C8O=new ImageIcon(ClassLoader.getSystemResource("8-O.JPG"));
		Icon C9O=new ImageIcon(ClassLoader.getSystemResource("9-O.JPG"));
		Icon C10O=new ImageIcon(ClassLoader.getSystemResource("10-O.JPG"));
		Icon C11O=new ImageIcon(ClassLoader.getSystemResource("11-O.JPG"));
		Icon C12O=new ImageIcon(ClassLoader.getSystemResource("12-O.JPG"));
		Icon C13O=new ImageIcon(ClassLoader.getSystemResource("13-O.JPG"));
		
		Icon C1P=new ImageIcon(ClassLoader.getSystemResource("1-P.JPG"));
		Icon C2P=new ImageIcon(ClassLoader.getSystemResource("2-P.JPG"));
		Icon C3P=new ImageIcon(ClassLoader.getSystemResource("3-P.JPG"));
		Icon C4P=new ImageIcon(ClassLoader.getSystemResource("4-P.JPG"));
		Icon C5P=new ImageIcon(ClassLoader.getSystemResource("5-P.JPG"));
		Icon C6P=new ImageIcon(ClassLoader.getSystemResource("6-P.JPG"));
		Icon C7P=new ImageIcon(ClassLoader.getSystemResource("7-P.JPG"));
		Icon C8P=new ImageIcon(ClassLoader.getSystemResource("8-P.JPG"));
		Icon C9P=new ImageIcon(ClassLoader.getSystemResource("9-P.JPG"));
		Icon C10P=new ImageIcon(ClassLoader.getSystemResource("10-P.JPG"));
		Icon C11P=new ImageIcon(ClassLoader.getSystemResource("11-P.JPG"));
		Icon C12P=new ImageIcon(ClassLoader.getSystemResource("12-P.JPG"));
		Icon C13P=new ImageIcon(ClassLoader.getSystemResource("13-P.JPG"));
		
		Icon C1E=new ImageIcon(ClassLoader.getSystemResource("1-E.JPG"));
		Icon C2E=new ImageIcon(ClassLoader.getSystemResource("2-E.JPG"));
		Icon C3E=new ImageIcon(ClassLoader.getSystemResource("3-E.JPG"));
		Icon C4E=new ImageIcon(ClassLoader.getSystemResource("4-E.JPG"));
		Icon C5E=new ImageIcon(ClassLoader.getSystemResource("5-E.JPG"));
		Icon C6E=new ImageIcon(ClassLoader.getSystemResource("6-E.JPG"));
		Icon C7E=new ImageIcon(ClassLoader.getSystemResource("7-E.JPG"));
		Icon C8E=new ImageIcon(ClassLoader.getSystemResource("8-E.JPG"));
		Icon C9E=new ImageIcon(ClassLoader.getSystemResource("9-E.JPG"));
		Icon C10E=new ImageIcon(ClassLoader.getSystemResource("10-E.JPG"));
		Icon C11E=new ImageIcon(ClassLoader.getSystemResource("11-E.JPG"));
		Icon C12E=new ImageIcon(ClassLoader.getSystemResource("12-E.JPG"));
		Icon C13E=new ImageIcon(ClassLoader.getSystemResource("13-E.JPG"));
		
		Icon C1C=new ImageIcon(ClassLoader.getSystemResource("1-C.JPG"));
		Icon C2C=new ImageIcon(ClassLoader.getSystemResource("2-C.JPG"));
		Icon C3C=new ImageIcon(ClassLoader.getSystemResource("3-C.JPG"));
		Icon C4C=new ImageIcon(ClassLoader.getSystemResource("4-C.JPG"));
		Icon C5C=new ImageIcon(ClassLoader.getSystemResource("5-C.JPG"));
		Icon C6C=new ImageIcon(ClassLoader.getSystemResource("6-C.JPG"));
		Icon C7C=new ImageIcon(ClassLoader.getSystemResource("7-C.JPG"));
		Icon C8C=new ImageIcon(ClassLoader.getSystemResource("8-C.JPG"));
		Icon C9C=new ImageIcon(ClassLoader.getSystemResource("9-C.JPG"));
		Icon C10C=new ImageIcon(ClassLoader.getSystemResource("10-C.JPG"));
		Icon C11C=new ImageIcon(ClassLoader.getSystemResource("11-C.JPG"));
		Icon C12C=new ImageIcon(ClassLoader.getSystemResource("12-C.JPG"));
		Icon C13C=new ImageIcon(ClassLoader.getSystemResource("13-C.JPG"));	
		
		
		estado = tab.informarEstado();
		vMensagem.setText(estado.informarMensagem());
		vMensagem2.setText(estado.getM56());
		vMensagem11.setText(estado.getM11());
		vMensagem55.setText(estado.getM55());
		vMensagem54.setText(estado.getM54());
		vMensagem53.setText(estado.getM53());
		vMensagem52.setText(estado.getM52());
		vMensagem16.setText(estado.getM16());
		vMensagem26.setText(estado.getM26());
		vMensagem36.setText(estado.getM36());
		vMensagem46.setText(estado.getM46());
	
	
		for (int linha=1; linha<5; linha++){
			for (int coluna=1; coluna<6; coluna++){
				
				valor = estado.informarValor(linha, coluna);
				//if (estado.getM11() == lolo2000) mapaVPosicao[0][0].setIcon(xis)	else if (estado.getM11()==lolo1000) mapaVPosicao[0][0].setIcon(bola) else mapaVPosicao[0][0].setIcon(piupiu);
				
				if ( (coluna==1 && linha==2) || (coluna==1 && linha==4) )
					mapaVPosicao[(linha-1)][(coluna-1)].setIcon(vazia2);
				else{
					
				switch (valor){
				case 0: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(vazia);
				break;
				case 1: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C1O);
				break;
				case 2: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C2O);
			    break;
				case 3: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C3O);
			    break;
			    case 4: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C4O);
			    break;
			    case 5: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C5O);
			    break;
			    case 6: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C6O);
			    break;
			    case 7: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C7O);
			    break;
			    case 8: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C8O);
			    break;
			    case 9: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C9O);
			    break;	
			    case 10: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C10O);
				break;
				case 11: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C11O);
				break;
				case 12: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C12O);
			    break;
				case 13: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C13O);
			    break;
			    // icones cartas Paus
			    case 14: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C1P);
			    break;
			    case 15: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C2P);
			    break;
			    case 16: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C3P);
			    break;
			    case 17: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C4P);
			    break;
			    case 18: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C5P);
			    break;
			    case 19: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C6P);
			    break;
			    case 20: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C7P);
			    break;
			    case 21: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C8P);
			    break;
			    case 22: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C9P);
			    break;
			    case 23: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C10P);
			    break;
			    case 24: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C11P);
			    break;
			    case 25: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C12P);
			    break;
			    case 26: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C13P);
			    break;
			    // icones cartas espadas
			    
			    case 27: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C1E);
			    break;
			    case 28: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C2E);
			    break;
			    case 29: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C3E);
			    break;
			    case 30: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C4E);
			    break;
			    case 31: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C5E);
			    break;
			    case 32: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C6E);
			    break;
			    case 33: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C7E);
			    break;
			    case 34: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C8E);
			    break;
			    case 35: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C9E);
			    break;
			    case 36: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C10E);
			    break;
			    case 37: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C11E);
			    break;
			    case 38: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C12E);
			    break;
			    case 39: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C13E);
			    break;
			    // ICONES CARTAS COPAS
			    case 40: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C1C);
			    break;
			    case 41: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C2C);
			    break;
			    case 42: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C3C);
			    break;
			    case 43: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C4C);
			    break;
			    case 44: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C5C);
			    break;
			    case 45: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C6C);
			    break;
			    case 46: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C7C);
			    break;
			    case 47: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C8C);
			    break;
			    case 48: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C9C);
			    break;
			    case 49: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C10C);
			    break;
			    case 50: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C11C);
			    break;
			    case 51: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C12C);
			    break;
			    case 52: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(C13C);
			    break;
			    case 100: mapaVPosicao[(linha-1)][(coluna-1)].setIcon(vazia2);
			    break;
			    case 1000:mapaVPosicao[(linha-1)][(coluna-1)].setIcon(bola);
			    break;	
			    case 2000:mapaVPosicao[(linha-1)][(coluna-1)].setIcon(xis);
			    break;	
				}
				}
			};
		};
		}
	 
	public void notificarIrregularidade(int codigo) {
		if (codigo == 2){
			JOptionPane.showMessageDialog(this, "Partida encerrada ou não iniciada");
		}else {
			JOptionPane.showMessageDialog(this, "Posição ocupada. Jogue novamente");	
		};
	}

	/**
	 * This method initializes Jogo	
	 * 	
	 * @return javax.swing.JMenu	
	 */
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

	/**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("iniciar nova partida");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					iniciarPartida();
				}
			});
		}
		return jMenuItem1;
	}

	/**
	 * This method initializes jMenuItem2	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("reiniciar partida");
			jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					reiniciarPartida();
				}
			});
		}
		return jMenuItem2;
	}
	
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
 		
 		 
 		@DirectlyReferredMethod(classe=Proxy.class,name="obterNomeAdversarios")
 		public void iniciarPartidaRede(boolean iniciarComoSolicitante) {

 			boolean simbolo = true;

 			if (tab == null) {
 				tab = new Tabuleiro();
 				tab.iniciar();
 			}

 			if (iniciarComoSolicitante) {
 				simbolo = true;
 			} else {
 				simbolo = false;
 			}

 			idJ1 = Proxy.getInstance().getNomeJogador();

 			tab.criarJogadorHumano(idJ1, simbolo);

 			List<String> nomes = Proxy.getInstance().obterNomeAdversarios();
 			String idJ2 = nomes.get(0);
 			tab.criarJogadorHumano(idJ2, !simbolo);
 			jogadorPapel = tab.getJogador1();
 			if (iniciarComoSolicitante) {
 				tab.definirOPrimeiro(1000);

 			} else {
 				tab.definirOPrimeiro(2000);
 			}

 			this.exibirEstado();

 			ehJogoRede = true;
            tab.setRedeTrue();
 		}

 		public boolean isEhJogoRede() {
 			return ehJogoRede;
 		}

 		public void setEhJogoRede(boolean ehJogoRede) {
 			this.ehJogoRede = ehJogoRede;
 			tab.setRedeTrue();
 		}

 	}
 	

