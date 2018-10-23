package gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import logica.AtualizaGUI;

public class GUIJogo extends JFrame {

	private static final long serialVersionUID = 1L;
	private Font fonte = new Font("Arial",Font.BOLD, 16);
	
	protected AtorApostador ator;
	
	private int fichaAtual = 5, apostaTotal = 0;
	private ArrayList<JLabel> fichasApostadas = new ArrayList<JLabel>();

	private MouseListener mesaListener = new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			JLabel comp = (JLabel)e.getComponent();
			JLabel ficha = new JLabel();
			fichasApostadas.add(ficha);
			ficha.setSize(new Dimension(30,30));

			switch (fichaAtual) {
				case 5: ficha.setIcon(new ImageIcon(getClass().getResource("/img/ficha5.gif"))); break;
				case 25: ficha.setIcon(new ImageIcon(getClass().getResource("/img/ficha25.gif"))); break;
				case 100: ficha.setIcon(new ImageIcon(getClass().getResource("/img/ficha100.gif"))); break;
				case 500: ficha.setIcon(new ImageIcon(getClass().getResource("/img/ficha500.gif"))); break;
				case 1000: ficha.setIcon(new ImageIcon(getClass().getResource("/img/ficha1000.gif"))); break;
				case 5000: ficha.setIcon(new ImageIcon(getClass().getResource("/img/ficha5000.gif"))); break;
				default: break;
			}
			
			AtualizaGUI atualiza = ator.clique(Integer.parseInt(comp.getText()), fichaAtual);
			if(atualiza != null) {
				jContentPane.add(ficha,1);
				jContentPane.validate();
				ficha.setLocation(comp.getLocation());
				apostaTotal += fichaAtual;
				apostadoL.setText("$ "+apostaTotal);
				dinheiroL.setText("$ "+atualiza.getDinheiroApostador());
				comp.setToolTipText("Aposta: $ "+atualiza.getValorApostado());
			}
			
			System.out.println("casa"+comp.getText());
		}
		
		public void mouseEntered(MouseEvent e) {
			setCursor(Cursor.HAND_CURSOR);
		}
		
		public void mouseExited(MouseEvent e) {
			setCursor(Cursor.DEFAULT_CURSOR);
		}
	};
	
	private MouseListener fichaListener = new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			JLabel comp = (JLabel)e.getComponent();
			vFicha.setLocation(comp.getLocation());
			fichaAtual = Integer.parseInt(comp.getText());
			System.out.println("fichaAtual: "+fichaAtual);
		}
		
		public void mouseEntered(MouseEvent e) {
			setCursor(Cursor.HAND_CURSOR);
		}
		
		public void mouseExited(MouseEvent e) {
			setCursor(Cursor.DEFAULT_CURSOR);
		}
	};
	
	private JButton rodarB = null;

	private JPanel jContentPane = null;
	private JLabel nrSorteadoL = null;
	private JLabel casa[] = new JLabel[49];
	private JButton reiniciarB = null;
	private JLabel ficha5 = null;
	private JLabel ficha25 = null;
	private JLabel ficha100 = null;
	private JLabel ficha500 = null;
	private JLabel ficha1000 = null;
	private JLabel ficha5000 = null;
	private JLabel dinheiroL = null;
	private JLabel apostadoL = null;
	private JLabel vFicha = null;
	private JLabel premioL = null;
	private JButton limparB = null;
	/**
	 * This is the default constructor
	 */
	public GUIJogo(AtorApostador ator) {
		super();
		this.ator = ator;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(375, 650);
		this.setContentPane(getJContentPane());
		this.setTitle("Roleta v1.0");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			premioL = new JLabel();
			premioL.setBounds(new Rectangle(235, 135, 106, 22));
			premioL.setText("");
			premioL.setHorizontalAlignment(SwingConstants.CENTER);
			premioL.setFont(fonte);
			vFicha = new JLabel();
			vFicha.setBounds(new Rectangle(14, 276, 40, 40));
			vFicha.setText("");
			vFicha.setIcon(new ImageIcon(getClass().getResource("/img/V.gif")));
			apostadoL = new JLabel();
			apostadoL.setBounds(new Rectangle(17, 469, 107, 23));
			apostadoL.setText("$ 0");
			apostadoL.setHorizontalAlignment(SwingConstants.CENTER);
			apostadoL.setFont(fonte);
			dinheiroL = new JLabel();
			dinheiroL.setBounds(new Rectangle(15, 400, 106, 23));
			dinheiroL.setText("$ 5000");
			dinheiroL.setHorizontalAlignment(SwingConstants.CENTER);
			dinheiroL.setFont(fonte);
			ficha5000 = new JLabel();
			ficha5000.setBounds(new Rectangle(100, 322, 33, 29));
			ficha5000.setText("5000");
			ficha5000.addMouseListener(fichaListener);
			ficha1000 = new JLabel();
			ficha1000.setBounds(new Rectangle(58, 320, 32, 31));
			ficha1000.setText("1000");
			ficha1000.addMouseListener(fichaListener);
			ficha500 = new JLabel();
			ficha500.setBounds(new Rectangle(14, 318, 34, 32));
			ficha500.setText("500");
			ficha500.addMouseListener(fichaListener);
			ficha100 = new JLabel();
			ficha100.setBounds(new Rectangle(100, 277, 32, 31));
			ficha100.setText("100");
			ficha100.addMouseListener(fichaListener);
			ficha25 = new JLabel();
			ficha25.setBounds(new Rectangle(57, 278, 34, 31));
			ficha25.setText("25");
			ficha25.addMouseListener(fichaListener);
			ficha5 = new JLabel();
			ficha5.setBounds(new Rectangle(14, 276, 35, 33));
			ficha5.setText("5");
			ficha5.addMouseListener(fichaListener);
			
			for(int i=0;i<casa.length;i++){
				casa[i] = new JLabel();
				casa[i].setText(""+i);
				casa[i].addMouseListener(mesaListener);
			}
			
			casa[48].setBounds(new Rectangle(297, 564, 47, 23));
			casa[47].setBounds(new Rectangle(249, 564, 46, 22));
			casa[46].setBounds(new Rectangle(202, 563, 46, 24));
			casa[45].setBounds(new Rectangle(154, 508, 22, 52));
			casa[44].setBounds(new Rectangle(154, 450, 22, 56));
			casa[43].setBounds(new Rectangle(154, 396, 22, 53));
			casa[42].setBounds(new Rectangle(154, 340, 22, 53));
			casa[41].setBounds(new Rectangle(154, 283, 21, 54));
			casa[40].setBounds(new Rectangle(154, 230, 22, 51));
			casa[39].setBounds(new Rectangle(178, 450, 21, 110));
			casa[38].setBounds(new Rectangle(177, 340, 22, 109));
			casa[37].setBounds(new Rectangle(177, 230, 23, 107));
			casa[36].setBounds(new Rectangle(297, 535, 47, 25));
			casa[35].setBounds(new Rectangle(249, 535, 47, 25));
			casa[34].setBounds(new Rectangle(202, 535, 46, 25));
			casa[33].setBounds(new Rectangle(297, 508, 47, 24));
			casa[32].setBounds(new Rectangle(250, 508, 46, 25));
			casa[31].setBounds(new Rectangle(202, 507, 45, 26));
			casa[30].setBounds(new Rectangle(297, 479, 46, 26));
			casa[29].setBounds(new Rectangle(249, 479, 46, 27));
			casa[28].setBounds(new Rectangle(202, 479, 46, 26));
			casa[27].setBounds(new Rectangle(297, 451, 47, 26));
			casa[26].setBounds(new Rectangle(250, 451, 45, 28));
			casa[25].setBounds(new Rectangle(202, 451, 46, 26));
			casa[24].setBounds(new Rectangle(297, 424, 46, 25));
			casa[23].setBounds(new Rectangle(250, 424, 46, 24));
			casa[22].setBounds(new Rectangle(202, 424, 47, 25));
			casa[21].setBounds(new Rectangle(297, 396, 46, 26));
			casa[20].setBounds(new Rectangle(250, 396, 46, 26));
			casa[19].setBounds(new Rectangle(202, 396, 46, 27));
			casa[18].setBounds(new Rectangle(297, 367, 47, 27));
			casa[17].setBounds(new Rectangle(250, 367, 45, 27));
			casa[16].setBounds(new Rectangle(202, 367, 46, 27));
			casa[15].setBounds(new Rectangle(296, 340, 47, 25));
			casa[14].setBounds(new Rectangle(249, 340, 47, 25));
			casa[13].setBounds(new Rectangle(202, 340, 47, 25));
			casa[12].setBounds(new Rectangle(297, 313, 46, 25));
			casa[11].setBounds(new Rectangle(250, 312, 45, 25));
			casa[10].setBounds(new Rectangle(202, 312, 46, 26));
			casa[0].setBounds(new Rectangle(202, 200, 141, 26));
			casa[9].setBounds(new Rectangle(297, 284, 47, 27));
			casa[8].setBounds(new Rectangle(249, 284, 47, 28));
			casa[7].setBounds(new Rectangle(203, 284, 44, 26));
			casa[6].setBounds(new Rectangle(297, 257, 47, 27));
			casa[5].setBounds(new Rectangle(249, 257, 47, 24));
			casa[4].setBounds(new Rectangle(202, 257, 47, 25));
			casa[3].setBounds(new Rectangle(297, 229, 46, 26));
			casa[2].setBounds(new Rectangle(250, 230, 46, 25));
			casa[1].setBounds(new Rectangle(202, 230, 47, 25));

			nrSorteadoL = new JLabel();
			nrSorteadoL.setBounds(new Rectangle(269, 53, 38, 28));
			nrSorteadoL.setHorizontalAlignment(SwingConstants.CENTER);
			nrSorteadoL.setFont(fonte);
			nrSorteadoL.setText("");
			JLabel jLabel = new JLabel();
			jLabel.setIcon(new ImageIcon(getClass().getResource("/img/jogo.jpg")));
			jLabel.setSize(new Dimension(375, 630));
			jLabel.setLocation(new Point(0, 0));
			jLabel.setText("");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(dinheiroL, null);
			jContentPane.add(apostadoL, null);
			jContentPane.add(nrSorteadoL, null);
			jContentPane.add(vFicha, null);
			jContentPane.add(premioL, null);
			jContentPane.add(getLimparB(), null);
			jContentPane.add(getReiniciarB(), null);
			jContentPane.add(getRodarB(), null);
			jContentPane.add(jLabel, null);
			
			for(int i=0;i<casa.length;i++) jContentPane.add(casa[i], null);
			
			jContentPane.add(ficha5, null);
			jContentPane.add(ficha25, null);
			jContentPane.add(ficha100, null);
			jContentPane.add(ficha500, null);
			jContentPane.add(ficha1000, null);
			jContentPane.add(ficha5000, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes rodarB	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRodarB() {
		if (rodarB == null) {
			rodarB = new JButton();
			rodarB.setBounds(new Rectangle(20, 510, 100, 30));
			rodarB.setText("Rodar");
			rodarB.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AtualizaGUI atualiza = ator.rodarRoleta();
					int ganho = atualiza.getGanho();
					premioL.setText("$ "+ganho);
					dinheiroL.setText("$ "+atualiza.getDinheiroApostador());
					nrSorteadoL.setText(""+atualiza.getNumeroSorteado());
					
					if(ganho == 0) {
						ator.mostrarMsgPerdeu();
					} else {
						ator.mostrarMsgGanhou();
					}
					
					limpar();
				}
			});
		}
		return rodarB;
	}

	/**
	 * This method initializes reiniciarB	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getReiniciarB() {
		if (reiniciarB == null) {
			reiniciarB = new JButton();
			reiniciarB.setBounds(new Rectangle(20, 545, 100, 30));
			reiniciarB.setText("Reiniciar");
			reiniciarB.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					boolean autoriza = ator.reiniciarPartida(true);
					if(autoriza) {
						dinheiroL.setText("$ 5000");
						nrSorteadoL.setText("");
						premioL.setText("");
						limpar();
					}
				}
			});
		}
		return reiniciarB;
	}
	
	public void limpar() {
		for(int i=0;i<casa.length;i++) casa[i].setToolTipText(null);
		
		int n = fichasApostadas.size();
		System.out.println(n);
		for(int i=0;i<n;i++) {
			jContentPane.remove(fichasApostadas.remove(0));
		}
		jContentPane.validate();
		jContentPane.repaint();
		
		apostadoL.setText("$ 0");
		apostaTotal = 0;
	}

	/**
	 * This method initializes limparB	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLimparB() {
		if (limparB == null) {
			limparB = new JButton();
			limparB.setBounds(new Rectangle(20, 580, 100, 30));
			limparB.setText("Limpar");
			limparB.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					boolean autoriza = ator.reiniciarPartida(false);
					if(autoriza) {
						dinheiroL.setText("$ "+ator.getSaldoAtual());
						limpar();
					}
				}
			});
		}
		return limparB;
	}
}
