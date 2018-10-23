package interfaceGrafica;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rede.AtorNetGames;

import br.ufsc.inf.leobr.cliente.Proxy;
import dominioProblema.ImagemDeTabuleiro;
import dominioProblema.Tabuleiro;

public class AtorJogador extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	protected Tabuleiro tab; 

	private JLabel vPosicao00 = null;
	private JLabel vPosicao01 = null;
	private JLabel vPosicao02 = null;
	private JLabel vPosicao03 = null;
	private JLabel vPosicao04 = null;
	private JLabel vPosicao05 = null;
	private JLabel vPosicao06 = null;

	private JLabel vPosicao10 = null;
	private JLabel vPosicao11 = null;
	private JLabel vPosicao12 = null;
	private JLabel vPosicao13 = null;
	private JLabel vPosicao14 = null;
	private JLabel vPosicao15 = null;
	private JLabel vPosicao16 = null;

	private JLabel vPosicao20 = null;
	private JLabel vPosicao21 = null;
	private JLabel vPosicao22 = null;
	private JLabel vPosicao23 = null;
	private JLabel vPosicao24 = null;
	private JLabel vPosicao25 = null;
	private JLabel vPosicao26 = null;

	private JLabel vPosicao30 = null;
	private JLabel vPosicao31 = null;
	private JLabel vPosicao32 = null;
	private JLabel vPosicao33 = null;
	private JLabel vPosicao34 = null;
	private JLabel vPosicao35 = null;
	private JLabel vPosicao36 = null;	

	private JLabel vPosicao40 = null;
	private JLabel vPosicao41 = null;
	private JLabel vPosicao42 = null;
	private JLabel vPosicao43 = null;
	private JLabel vPosicao44 = null;
	private JLabel vPosicao45 = null;
	private JLabel vPosicao46 = null;

	private JLabel vPosicao50 = null;
	private JLabel vPosicao51 = null;
	private JLabel vPosicao52 = null;
	private JLabel vPosicao53 = null;
	private JLabel vPosicao54 = null;
	private JLabel vPosicao55 = null;
	private JLabel vPosicao56 = null;

	private JLabel vPosicao60 = null;
	private JLabel vPosicao61 = null;
	private JLabel vPosicao62 = null;
	private JLabel vPosicao63 = null;
	private JLabel vPosicao64 = null;
	private JLabel vPosicao65 = null;
	private JLabel vPosicao66 = null;
	
	private JLabel vMensagem = null;

	protected JLabel mapaVPosicao[][] = new JLabel[7][7];

	private JMenuBar jMenuBar = null;

	private AtorNetGames atorNetGames;
	
	private PainelSobre painelSobre;

	public AtorJogador(){
		super();
		initialize();
	}
	
	private void initialize() {
		atorNetGames = new AtorNetGames(this);
		this.setSize(538, 630);
		this.setContentPane(getJContentPane());
		this.setTitle("O lobo e as ovelhas");
		this.setResizable(false);
		tab = new Tabuleiro();
	}
	
	private JPanel getJContentPane() {
		if (jContentPane == null) {

			Icon vazia = new ImageIcon(ClassLoader.getSystemResource("Vazia.png"));

			vMensagem = new JLabel();
			vMensagem.setForeground(new Color(250,250,250));
			vMensagem.setBounds(new Rectangle(30, 540, 300, 20));
			vMensagem.setText("Bem vindo!");

			vPosicao00 = new JLabel();
			vPosicao00.setBounds(new Rectangle(20, 40, 70, 70));
			vPosicao00.setIcon(null);
			
			vPosicao01 = new JLabel();
			vPosicao01.setBounds(new Rectangle(90, 40, 70, 70));
			vPosicao01.setIcon(null);
			
			vPosicao02 = new JLabel();
			vPosicao02.setIcon(vazia);
			vPosicao02.setBounds(new Rectangle(160, 40, 70, 70));
			vPosicao02.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(0,2).ocupada()){
								tratarLance(0,2, false);
							} else {
								selecionaPeca(0,2, false);
							}
						}
					}
				}
			});
			vPosicao03 = new JLabel();
			vPosicao03.setIcon(vazia);
			vPosicao03.setBounds(new Rectangle(230, 40, 70, 70));
			vPosicao03.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(0,3).ocupada()){
								tratarLance(0,3, false);
							} else {
								selecionaPeca(0,3, false);
							}
						}
					}
				}
			});
			vPosicao04 = new JLabel();
			vPosicao04.setIcon(vazia);
			vPosicao04.setBounds(new Rectangle(300, 40, 70, 70));
			vPosicao04.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(0,4).ocupada()){
								tratarLance(0,4, false);
							} else {
								selecionaPeca(0,4, false);
							}
						}
					}
				}
			});
			vPosicao05 = new JLabel();
			vPosicao05.setIcon(null);
			vPosicao05.setBounds(new Rectangle(370, 40, 70, 70));
			
			vPosicao06 = new JLabel();
			vPosicao06.setIcon(null);
			vPosicao06.setBounds(new Rectangle(440, 40, 70, 70));

			vPosicao10 = new JLabel();
			vPosicao10.setBounds(new Rectangle(20, 110, 70, 70));
			vPosicao10.setIcon(null);

			vPosicao11 = new JLabel();
			vPosicao11.setBounds(new Rectangle(90, 110, 70, 70));
			vPosicao11.setIcon(null);

			vPosicao12 = new JLabel();
			vPosicao12.setBounds(new Rectangle(160, 110, 70, 70));
			vPosicao12.setIcon(vazia);
			vPosicao12.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(1,2).ocupada()){
								tratarLance(1,2, false);
							} else {
								selecionaPeca(1,2, false);
							}
						}
					}
				}
			});
			vPosicao13 = new JLabel();
			vPosicao13.setBounds(new Rectangle(230, 110, 70, 70));
			vPosicao13.setIcon(vazia);
			vPosicao13.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(1,3).ocupada()){
								tratarLance(1,3, false);
							} else {
								selecionaPeca(1,3, false);
							}
						}
					}
				}
			});
			vPosicao14 = new JLabel();
			vPosicao14.setBounds(new Rectangle(300, 110, 70, 70));
			vPosicao14.setIcon(vazia);
			vPosicao14.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(1,4).ocupada()){
								tratarLance(1,4, false);
							} else {
								selecionaPeca(1,4, false);
							}
						}
					}
				}
			});
			vPosicao15 = new JLabel();
			vPosicao15.setBounds(new Rectangle(370, 110, 70, 70));
			vPosicao15.setIcon(null);

			vPosicao16 = new JLabel();
			vPosicao16.setBounds(new Rectangle(440, 110, 70, 70));
			vPosicao16.setIcon(null);

			vPosicao20 = new JLabel();
			vPosicao20.setBounds(new Rectangle(20, 180, 70, 70));
			vPosicao20.setIcon(vazia);
			vPosicao20.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(2,0).ocupada()){
								tratarLance(2,0, false);
							} else {
								selecionaPeca(2,0, false);
							}
						}
					}
				}
			});
			
			vPosicao21 = new JLabel();
			vPosicao21.setBounds(new Rectangle(90, 180, 70, 70));
			vPosicao21.setIcon(vazia);
			vPosicao21.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(2,1).ocupada()){
								tratarLance(2,1, false);
							} else {
								selecionaPeca(2,1, false);
							}
						}
					}
				}
			});
			
			vPosicao22 = new JLabel();
			vPosicao22.setBounds(new Rectangle(160, 180, 70, 70));
			vPosicao22.setIcon(vazia);
			vPosicao22.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(2,2).ocupada()){
								tratarLance(2,2, false);
							} else {
								selecionaPeca(2,2, false);
							}
						}
					}
				}
			});
			
			vPosicao23 = new JLabel();
			vPosicao23.setBounds(new Rectangle(230, 180, 70, 70));
			vPosicao23.setIcon(vazia);
			vPosicao23.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(2,3).ocupada()){
								tratarLance(2,3, false);
							} else {
								selecionaPeca(2,3, false);
							}
						}
					}
				}
			});
			
			vPosicao24 = new JLabel();
			vPosicao24.setBounds(new Rectangle(300, 180, 70, 70));
			vPosicao24.setIcon(vazia);
			vPosicao24.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(2,4).ocupada()){
								tratarLance(2,4, false);
							} else {
								selecionaPeca(2,4, false);
							}
						}
					}
				}
			});
			
			vPosicao25 = new JLabel();
			vPosicao25.setBounds(new Rectangle(370, 180, 70, 70));
			vPosicao25.setIcon(vazia);
			vPosicao25.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(2,5).ocupada()){
								tratarLance(2,5, false);
							} else {
								selecionaPeca(2,5, false);
							}
						}
					}
				}
			});
			
			vPosicao26 = new JLabel();
			vPosicao26.setBounds(new Rectangle(440, 180, 70, 70));
			vPosicao26.setIcon(vazia);
			vPosicao26.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(2,6).ocupada()){
								tratarLance(2,6, false);
							} else {
								selecionaPeca(2,6, false);
							}
						}
					}
				}
			});
			
			vPosicao30 = new JLabel();
			vPosicao30.setBounds(new Rectangle(20, 250, 70, 70));
			vPosicao30.setIcon(vazia);
			vPosicao30.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(3,0).ocupada()){
								tratarLance(3,0, false);
							} else {
								selecionaPeca(3,0, false);
							}
						}
					}
				}
			});
			
			vPosicao31 = new JLabel();
			vPosicao31.setBounds(new Rectangle(90, 250, 70, 70));
			vPosicao31.setIcon(vazia);
			vPosicao31.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(3,1).ocupada()){
								tratarLance(3,1, false);
							} else {
								selecionaPeca(3,1, false);
							}
						}
					}
				}
			});
			
			vPosicao32 = new JLabel();
			vPosicao32.setBounds(new Rectangle(160, 250, 70, 70));
			vPosicao32.setIcon(vazia);
			vPosicao32.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(3,2).ocupada()){
								tratarLance(3,2, false);
							} else {
								selecionaPeca(3,2, false);
							}
						}
					}
				}
			});
			
			vPosicao33 = new JLabel();
			vPosicao33.setBounds(new Rectangle(230, 250, 70, 70));
			vPosicao33.setIcon(vazia);
			vPosicao33.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(3,3).ocupada()){
								tratarLance(3,3, false);
							} else {
								selecionaPeca(3,3, false);
							}
						}
					}
				}
			});
			
			vPosicao34 = new JLabel();
			vPosicao34.setBounds(new Rectangle(300, 250, 70, 70));
			vPosicao34.setIcon(vazia);
			vPosicao34.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(3,4).ocupada()){
								tratarLance(3,4, false);
							} else {
								selecionaPeca(3,4, false);
							}
						}
					}
				}
			});
			
			vPosicao35 = new JLabel();
			vPosicao35.setBounds(new Rectangle(370, 250, 70, 70));
			vPosicao35.setIcon(vazia);
			vPosicao35.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(3,5).ocupada()){
								tratarLance(3,5, false);
							} else {
								selecionaPeca(3,5, false);
							}
						}
					}
				}
			});
			
			vPosicao36 = new JLabel();
			vPosicao36.setBounds(new Rectangle(440, 250, 70, 70));
			vPosicao36.setIcon(vazia);
			vPosicao36.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(3,6).ocupada()){
								tratarLance(3,6, false);
							} else {
								selecionaPeca(3,6, false);
							}
						}
					}
				}
			});
			vPosicao40 = new JLabel();
			vPosicao40.setBounds(new Rectangle(20, 320, 70, 70));
			vPosicao40.setIcon(vazia);
			vPosicao40.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(4,0).ocupada()){
								tratarLance(4,0, false);
							} else {
								selecionaPeca(4,0, false);
							}
						}
					}
				}
			});
			vPosicao41 = new JLabel();
			vPosicao41.setBounds(new Rectangle(90, 320, 70, 70));
			vPosicao41.setIcon(vazia);
			vPosicao41.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(4,1).ocupada()){
								tratarLance(4,1, false);
							} else {
								selecionaPeca(4,1, false);
							}
						}
					}
				}
			});
			vPosicao42 = new JLabel();
			vPosicao42.setBounds(new Rectangle(160, 320, 70, 70));
			vPosicao42.setIcon(vazia);
			vPosicao42.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(4,2).ocupada()){
								tratarLance(4,2, false);
							} else {
								selecionaPeca(4,2, false);
							}
						}
					}
				}
			});
			vPosicao43 = new JLabel();
			vPosicao43.setBounds(new Rectangle(230, 320, 70, 70));
			vPosicao43.setIcon(vazia);
			vPosicao43.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(4,3).ocupada()){
								tratarLance(4,3, false);
							} else {
								selecionaPeca(4,3, false);
							}
						}
					}
				}
			});
			vPosicao44 = new JLabel();
			vPosicao44.setBounds(new Rectangle(300, 320, 70, 70));
			vPosicao44.setIcon(vazia);
			vPosicao44.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(4,4).ocupada()){
								tratarLance(4,4, false);
							} else {
								selecionaPeca(4,4, false);
							}
						}
					}
				}
			});
			vPosicao45 = new JLabel();
			vPosicao45.setBounds(new Rectangle(370, 320, 70, 70));
			vPosicao45.setIcon(vazia);
			vPosicao45.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(4,5).ocupada()){
								tratarLance(4,5, false);
							} else {
								selecionaPeca(4,5, false);
							}
						}
					}
				}
			});
			vPosicao46 = new JLabel();
			vPosicao46.setBounds(new Rectangle(440, 320, 70, 70));
			vPosicao46.setIcon(vazia);
			vPosicao46.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(4,6).ocupada()){
								tratarLance(4,6, false);
							} else {
								selecionaPeca(4,6, false);
							}
						}
					}
				}
			});
			vPosicao50 = new JLabel();
			vPosicao50.setBounds(new Rectangle(20, 390, 70, 70));
			vPosicao50.setIcon(null);
			
			vPosicao51 = new JLabel();
			vPosicao51.setBounds(new Rectangle(90, 390, 70, 70));
			vPosicao51.setIcon(null);
			
			vPosicao52 = new JLabel();
			vPosicao52.setBounds(new Rectangle(160, 390, 70, 70));
			vPosicao52.setIcon(vazia);
			vPosicao52.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(5,2).ocupada()){
								tratarLance(5,2, false);
							} else {
								selecionaPeca(5,2, false);
							}
						}
					
					}
				}
			});
			vPosicao53 = new JLabel();
			vPosicao53.setBounds(new Rectangle(230, 390, 70, 70));
			vPosicao53.setIcon(vazia);
			vPosicao53.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(5,3).ocupada()){
								tratarLance(5,3, false);
							} else {
								selecionaPeca(5,3, false);
							}
						}
					}
				}
			});
			vPosicao54 = new JLabel();
			vPosicao54.setBounds(new Rectangle(300, 390, 70, 70));
			vPosicao54.setIcon(vazia);
			vPosicao54.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(5,4).ocupada()){
								tratarLance(5,4, false);
							} else {
								selecionaPeca(5,4, false);
							}
						}
					}
				}
			});
			vPosicao55 = new JLabel();
			vPosicao55.setBounds(new Rectangle(370, 390, 70, 70));
			vPosicao55.setIcon(null);
			
			vPosicao56 = new JLabel();
			vPosicao56.setBounds(new Rectangle(440, 390, 70, 70));
			vPosicao56.setIcon(null);
		
			vPosicao60 = new JLabel();
			vPosicao60.setBounds(new Rectangle(20, 460, 70, 70));
			vPosicao60.setIcon(null);
			
			vPosicao61 = new JLabel();
			vPosicao61.setBounds(new Rectangle(90, 460, 70, 70));
			vPosicao61.setIcon(null);
			
			vPosicao62 = new JLabel();
			vPosicao62.setBounds(new Rectangle(160, 460, 70, 70));
			vPosicao62.setIcon(vazia);
			vPosicao62.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(6,2).ocupada()){
								tratarLance(6,2, false);
							} else {
								selecionaPeca(6,2, false);
							}
						}
					}
				}
			});
			vPosicao63 = new JLabel();
			vPosicao63.setBounds(new Rectangle(230, 460, 70, 70));
			vPosicao63.setIcon(vazia);
			vPosicao63.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(6,3).ocupada()){
								tratarLance(6,3, false);
							} else {
								selecionaPeca(6,3, false);
							}
						}
					}
				}
			});
			vPosicao64 = new JLabel();
			vPosicao64.setBounds(new Rectangle(300, 460, 70, 70));
			vPosicao64.setIcon(vazia);
			vPosicao64.addMouseListener(new java.awt.event.MouseAdapter() {
				
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(tab.informarPartidaEmAndamento()){
						if(!(atorNetGames.ehMinhaVez())){
							JOptionPane.showMessageDialog(null, "Não é sua vez, por favor aguarde!");
						} else {
							if(!tab.recuperarPosicao(6,4).ocupada()){
								tratarLance(6,4, false);
							} else {
								selecionaPeca(6,4, false);
							}
						}
					}
				}
			});
			
			vPosicao65 = new JLabel();
			vPosicao65.setBounds(new Rectangle(370, 460, 70, 70));
			vPosicao65.setIcon(null);
			
			vPosicao66 = new JLabel();
			vPosicao66.setBounds(new Rectangle(440, 460, 70, 70));
			vPosicao66.setIcon(null);
						
			JMenu menuSobre = new JMenu("Sobre");
			menuSobre.addMouseListener(new java.awt.event.MouseAdapter() {
				
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					painelSobre = new PainelSobre();
					painelSobre.setModal(true);
					painelSobre.pack();
					painelSobre.setVisible(true);
				}
			});
			
			jMenuBar = new JMenuBar();
			jMenuBar.add(atorNetGames.getMenuRede());
			jMenuBar.add(menuSobre);
			this.setJMenuBar(jMenuBar);

			jContentPane = new PainelPrincipal();
			jContentPane.setLayout(null);
			

			jContentPane.add(vPosicao00, null);
			jContentPane.add(vPosicao01, null);
			jContentPane.add(vPosicao02, null);
			jContentPane.add(vPosicao03, null);
			jContentPane.add(vPosicao04, null);
			jContentPane.add(vPosicao05, null);
			jContentPane.add(vPosicao06, null);

			jContentPane.add(vPosicao00, null);
			jContentPane.add(vPosicao11, null);
			jContentPane.add(vPosicao12, null);
			jContentPane.add(vPosicao13, null);
			jContentPane.add(vPosicao14, null);
			jContentPane.add(vPosicao15, null);
			jContentPane.add(vPosicao16, null);

			jContentPane.add(vPosicao20, null);
			jContentPane.add(vPosicao21, null);
			jContentPane.add(vPosicao22, null);
			jContentPane.add(vPosicao23, null);
			jContentPane.add(vPosicao24, null);
			jContentPane.add(vPosicao25, null);
			jContentPane.add(vPosicao26, null);

			jContentPane.add(vPosicao30, null);
			jContentPane.add(vPosicao31, null);
			jContentPane.add(vPosicao32, null);
			jContentPane.add(vPosicao33, null);
			jContentPane.add(vPosicao34, null);
			jContentPane.add(vPosicao35, null);
			jContentPane.add(vPosicao36, null);

			jContentPane.add(vPosicao40, null);
			jContentPane.add(vPosicao41, null);
			jContentPane.add(vPosicao42, null);
			jContentPane.add(vPosicao43, null);
			jContentPane.add(vPosicao44, null);
			jContentPane.add(vPosicao45, null);
			jContentPane.add(vPosicao46, null);

			jContentPane.add(vPosicao50, null);
			jContentPane.add(vPosicao51, null);
			jContentPane.add(vPosicao52, null);
			jContentPane.add(vPosicao53, null);
			jContentPane.add(vPosicao54, null);
			jContentPane.add(vPosicao55, null);
			jContentPane.add(vPosicao56, null);

			jContentPane.add(vPosicao60, null);
			jContentPane.add(vPosicao61, null);
			jContentPane.add(vPosicao62, null);
			jContentPane.add(vPosicao63, null);
			jContentPane.add(vPosicao64, null);
			jContentPane.add(vPosicao65, null);
			jContentPane.add(vPosicao66, null);


			mapaVPosicao[0][0] = vPosicao00;
			mapaVPosicao[0][1] = vPosicao01;
			mapaVPosicao[0][2] = vPosicao02;
			mapaVPosicao[0][3] = vPosicao03;
			mapaVPosicao[0][4] = vPosicao04;
			mapaVPosicao[0][5] = vPosicao05;
			mapaVPosicao[0][6] = vPosicao06;

			mapaVPosicao[1][0] = vPosicao10;
			mapaVPosicao[1][1] = vPosicao11;
			mapaVPosicao[1][2] = vPosicao12;
			mapaVPosicao[1][3] = vPosicao13;
			mapaVPosicao[1][4] = vPosicao14;
			mapaVPosicao[1][5] = vPosicao15;
			mapaVPosicao[1][6] = vPosicao16;

			mapaVPosicao[2][0] = vPosicao20;
			mapaVPosicao[2][1] = vPosicao21;
			mapaVPosicao[2][2] = vPosicao22;
			mapaVPosicao[2][3] = vPosicao23;
			mapaVPosicao[2][4] = vPosicao24;
			mapaVPosicao[2][5] = vPosicao25;
			mapaVPosicao[2][6] = vPosicao26;

			mapaVPosicao[3][0] = vPosicao30;
			mapaVPosicao[3][1] = vPosicao31;
			mapaVPosicao[3][2] = vPosicao32;
			mapaVPosicao[3][3] = vPosicao33;
			mapaVPosicao[3][4] = vPosicao34;
			mapaVPosicao[3][5] = vPosicao35;
			mapaVPosicao[3][6] = vPosicao36;

			mapaVPosicao[4][0] = vPosicao40;
			mapaVPosicao[4][1] = vPosicao41;
			mapaVPosicao[4][2] = vPosicao42;
			mapaVPosicao[4][3] = vPosicao43;
			mapaVPosicao[4][4] = vPosicao44;
			mapaVPosicao[4][5] = vPosicao45;
			mapaVPosicao[4][6] = vPosicao46;

			mapaVPosicao[5][0] = vPosicao50;
			mapaVPosicao[5][1] = vPosicao51;
			mapaVPosicao[5][2] = vPosicao52;
			mapaVPosicao[5][3] = vPosicao53;
			mapaVPosicao[5][4] = vPosicao54;
			mapaVPosicao[5][5] = vPosicao55;
			mapaVPosicao[5][6] = vPosicao56;

			mapaVPosicao[6][0] = vPosicao60;
			mapaVPosicao[6][1] = vPosicao61;
			mapaVPosicao[6][2] = vPosicao62;
			mapaVPosicao[6][3] = vPosicao63;
			mapaVPosicao[6][4] = vPosicao64;
			mapaVPosicao[6][5] = vPosicao65;
			mapaVPosicao[6][6] = vPosicao66;

			jContentPane.add(vMensagem, null);

		}
		return jContentPane;
	}
 
	public void iniciarPartidaRede(boolean iniciarComoSolicitante) {
		if (tab == null) {
			tab = new Tabuleiro();
		}
		
		String idJ1;
		idJ1 = Proxy.getInstance().getNomeJogador();
		
		if(iniciarComoSolicitante)
			tab.criarJogador(idJ1, 1);
		else
			tab.criarJogador(idJ1, 2);
		
		
		String idJ2;
		idJ2 = atorNetGames.obtemNomeAdversario();
		
		if(iniciarComoSolicitante)
			tab.criarJogador(idJ2, 2);
		else
			tab.criarJogador(idJ2, 1);
				
		
		if (iniciarComoSolicitante) {
			tab.definirOPrimeiro(1);
			
		} else {
			tab.definirOPrimeiro(2);
		}
		

		tab.iniciar();
		
		this.exibirEstado();
		
	}
	
	public void efetuarJogadaRede(int linhaOrigem, int colunaOrigem,int linhaDestino, int colunaDestino) {
		selecionaPeca(linhaOrigem, colunaOrigem, true);
		tratarLance(linhaDestino, colunaDestino, true);
		this.exibirEstado();
		
	}

	public void exibirEstado() {
		ImagemDeTabuleiro estado;
		int valor = 0;
		Icon vazia = new ImageIcon(ClassLoader.getSystemResource("Vazia.png"));
		Icon lobo = new ImageIcon(ClassLoader.getSystemResource("PosicaoLobo.png"));
		Icon ovelha = new ImageIcon(ClassLoader.getSystemResource("PosicaoOvelha.png"));
		Icon loboSelecionada = new ImageIcon(ClassLoader.getSystemResource("PosicaoLoboSelecionada.png"));
		Icon ovelhaSelecionada = new ImageIcon(ClassLoader.getSystemResource("PosicaoOvelhaSelecionada.png"));
		estado = tab.informarEstado();
		vMensagem.setText(estado.informarMensagem());
		for (int linha = 0; linha < 7; linha++) {
			for (int coluna = 0; coluna < 7; coluna++) {
				valor = estado.informarValor(linha, coluna);
				switch (valor) {
				case 0:
					mapaVPosicao[linha][coluna].setIcon(vazia);
					break;
				case 1:
					mapaVPosicao[linha][coluna].setIcon(lobo);
					break;
				case 3:
					mapaVPosicao[linha][coluna].setIcon(loboSelecionada);
					break;
				case 2:
					mapaVPosicao[linha][coluna].setIcon(ovelha);
					break;
				case 4:
					mapaVPosicao[linha][coluna].setIcon(ovelhaSelecionada);
				}
			}
			;
		}
		;
	}
	 
	public void notificarIrregularidade(int codigo) {
		switch(codigo){
		case 1:
			JOptionPane.showMessageDialog(this,
			"Peça Inválida!");
			break;
		case 2:
			JOptionPane.showMessageDialog(this,
			"Não existe peça selecionada! Por favor, selecione uma peça.");
			break;
		case 3:
			JOptionPane.showMessageDialog(this,
			"Não é possível mover se para essa posição!");
			break;
		}
	}
	
	public void selecionaPeca(int linha, int coluna, boolean remetenteServidor){
		
		int resultado = tab.selecionaPeca(linha,coluna,remetenteServidor); 
		/*
		 * resultado = 0 - Sucesso
		 *             1 - Nao é dono da peça
		 */
		if (resultado == 1) {
			this.notificarIrregularidade(resultado);
		}
		this.exibirEstado();
	}
	
	public void tratarLance(int linha, int coluna, boolean remetenteServidor){
		int resultado;
		/*
		 * resultado: 0 - Sucesso
		 *            2 - Sem peca selecionada
		 *            3 - Posicao impossivel
		 */
		resultado = tab.tratarLance(linha, coluna, remetenteServidor);

		if (resultado == 0 && !remetenteServidor) {
			atorNetGames.enviarJogadaRede(tab.informarLinhaSelecionada(), tab.informarColunaSelecionada(), linha, coluna);
		} else {
			this.notificarIrregularidade(resultado);
		}
		
		tab.desselecionarPos();
		this.exibirEstado();
	}
	
	public boolean informarPartidaEmAndamento(){
		return tab.informarPartidaEmAndamento();
	}
	
	public void terminarPartidaEmAndamento(){
		tab.terminarPartidaEmAndamento();
	}
	

}