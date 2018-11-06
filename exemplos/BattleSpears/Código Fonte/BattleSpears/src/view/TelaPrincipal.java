package view;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelaPrincipal extends JFrame {

	// atributos
	protected AtorJogador ator;
	protected JPanel jContentPane;
	protected LabelImg[] labels;
	protected Integer atual;
	protected Integer destino;
	protected JMenuBar menu;
	protected JMenu menuDd;
	protected JMenuItem conectar, iniciarPartida, desconectar, reiniciar;
	protected JButton atacar;
	protected JButton mover;
	protected int posicaoX1 = 50;
	protected int posicaoY1 = 10;	
	protected int posicaoX2 = 200;
	protected int posicaoY2 = 10;
	protected int movimento = 0; // 0 será o nulo, 1 será ataque e 2 será mover
	
	

	public TelaPrincipal(final AtorJogador ator) {
		
		// parâmetros gerais do tabuleiro
		this.ator = ator;
		this.setSize(350, 500);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Battle Spears");
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		

	}

	
	// criando a imagem de tabuleiro
	private JPanel getJContentPane() {
	
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			labels = new LabelImg[6];
			

			// criar casas, e coloca imagens padrão
			for(int i=0; i<=5; i++){
								
				if(i== 0 || i==1 || i==2){
					labels[i] = new LabelImg(i);
					labels[i].setImagem("quadBr.png");
					labels[i].setBounds(new Rectangle(posicaoX1, posicaoY1, 70, 70));
					labels[i].setIcon(new ImageIcon(getClass().getResource(labels[i].retornaImagem())));
					jContentPane.add(labels[i], null);
					posicaoY1 = posicaoY1 + 75; // 75 pois ai pula um quadrado (70pixels) e +5 para distanciamento entre eles.
					
				}
				if(i== 3 || i==4 || i==5){
					labels[i] = new LabelImg(i);
					labels[i].setImagem("quadPr.png");
					labels[i].setBounds(new Rectangle(posicaoX2, posicaoY2, 70, 70));
					labels[i].setIcon(new ImageIcon(getClass().getResource(labels[i].retornaImagem())));
					jContentPane.add(labels[i], null);
					posicaoY2 = posicaoY2 + 75;
					
				}
			}
			
			
			
			//tratador de eventos de cada poição
			labels[0].addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					clickPosicao(0);
				}
			});
			labels[1].addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					clickPosicao(1);
				}
			});
			labels[2].addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					clickPosicao(2);
				}
			});
			labels[3].addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					clickPosicao(3);
				}
			});
			labels[4].addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					clickPosicao(4);
				}
			});
			labels[5].addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					clickPosicao(5);
				}
			});
			
		// chama o método pra construir o menu.	
		criarMenu();

		//criar botoes
		criarBotoes();
	}
	return jContentPane;
}
	
	
	public void criarMenu() { 
		menu = new JMenuBar();
		menuDd = new JMenu("Partida");
		
		
		//Cria cada texto de botão e o tratador de eventos de cada um.
		conectar = menuDd.add("Conectar");
		conectar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					String nomeJogador = solicitar("Insira seu nome:", null);
					String servidor = solicitar("Insira o servidor:", "localhost");
					//user.setText("Jogador: " + nomeJogador);
					ator.conectar(nomeJogador, servidor);
					iniciarPartida.setEnabled(true);
					desconectar.setEnabled(true);
					conectar.setEnabled(false);
					notificar("Conexão estabelecida com sucesso!");
				} catch (Exception ex) {
					notificar(ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		
		
		
		iniciarPartida = menuDd.add("Iniciar Partida");
		iniciarPartida.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					ator.IniciarPartida();
					if (ator.getTabuleiro().informaSePartidaEmAndamento()) {
						reiniciar.setEnabled(true);
						iniciarPartida.setEnabled(false);
					}
					
				} catch (Exception e1) {
					notificar(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		
		
		
		desconectar = menuDd.add("Desconectar");
		desconectar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					ator.desconectar();
					desconectar.setEnabled(false);
					conectar.setEnabled(true);
					reiniciar.setEnabled(false);
					iniciarPartida.setEnabled(false);
					notificar("Conexão encerrada com sucesso!");
				} catch (Exception ex) {
					notificar(ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		
		// Método de reinicio - Não implementado pois não faz parte dos requisitos
		//reiniciar = menuDd.add("Reiniciar");
		//reiniciar.addActionListener(new java.awt.event.ActionListener() {
		//public void actionPerformed(java.awt.event.ActionEvent e) {
		//try {
		//int opt = perguntar("Deseja realmente enviar uma solicitação de reinicio de partida?\nO outro jogador começará a partida com o lado branco se aceitar.");
		//if (opt == 0) {
		//reiniciar.setEnabled(false);
		//ator.solicitarReinicio();
						//}
		//} catch (Exception ex) {
		//notificar(ex.getMessage());
					//}
		//}
		//});
		
		
		// adiciona os botões configurados a barra de menu.
		menu.add(menuDd);
		this.setJMenuBar(menu);

	}

	public void criarBotoes(){
		atacar = new JButton("Atacar!");
		atacar.setBounds(60, 350, 100, 30);
		atacar.setVisible(true);
		//atacar.setEnabled(false);
		atacar.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e)
			  {
			    System.out.println("ATAQUEI");
			    movimento = 1;
			  }	
		});
		
		jContentPane.add(atacar);
		
		
		mover = new JButton("Mover");
		mover.setBounds(160, 350, 100, 30);
		mover.setVisible(true);
		//mover.setEnabled(false);
		mover.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e)
			  {
			    System.out.println("Movimento de movimento");
			    movimento = 2;
			  }	
		});
		jContentPane.add(mover);
	}
	
	
	
	public void notificar(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	
	public String solicitar(String msg, String def) {
		return JOptionPane.showInputDialog(msg, def);
	}

	// trata as mudanças de imagem e é o gatilho para os avisos da tela
	public void clickPosicao(int posicao) {
		try{
			ator.realizaJogada(movimento, posicao);
			if(ator.tabuleiro.getJogador2().getPosicaoAtual() == posicao){
				labels[posicao].setImagem2("atac.png");
				labels[posicao].setIcon(new ImageIcon(getClass().getResource(labels[posicao].retornaImagem())));	
			}
			if(movimento == 2){
				limpar();
				int posicaoAtual = ator.getTabuleiro().getJogador1().getPosicaoAtual();
				labels[posicaoAtual].setImagem2(null);
				labels[posicaoAtual].setIcon(new ImageIcon(getClass().getResource(labels[posicaoAtual].retornaImagem())));
				
				
				labels[posicao].setImagem2("jogador.png");
				labels[posicao].setIcon(new ImageIcon(getClass().getResource(labels[posicao].retornaImagem())));
			}
			else{
				labels[posicao].setImagem2("atac.png");
				labels[posicao].setIcon(new ImageIcon(getClass().getResource(labels[posicao].retornaImagem())));
			}
			movimento = 0;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public int perguntar(String pergunta) {
		int opt = JOptionPane.showConfirmDialog(null, pergunta, "Question", 0, 3);
		return opt;
	}

	public void reiniciar() {
		this.jContentPane = null;
		this.setContentPane(getJContentPane());
	}

	public void limpar(){
		for(LabelImg l : labels){
			l.setImagem2(null);
			l.setIcon(new ImageIcon(getClass().getResource(l.retornaImagem())));
		}
	}


	public void atualizaInterface(int posicaoAtualizar) {	
		labels[posicaoAtualizar].setImagem2("atac.png");
		labels[posicaoAtualizar].setIcon(new ImageIcon(getClass().getResource(labels[posicaoAtualizar].retornaImagem())));
		
		
	}
	
	public void setaImagemJogador(){
		int posicaoImagem = ator.tabuleiro.getJogador1().getPosicaoAtual();
		labels[posicaoImagem].setImagem2("jogador.png");
		labels[posicaoImagem].setIcon(new ImageIcon(getClass().getResource(labels[posicaoImagem].retornaImagem())));
		System.out.print("Posicao imagem" + posicaoImagem);
	}

}