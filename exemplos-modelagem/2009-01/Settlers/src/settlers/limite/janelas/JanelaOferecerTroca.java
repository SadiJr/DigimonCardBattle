package settlers.limite.janelas;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.util.Stack;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

import settlers.limite.AtorJogador;
import settlers.limite.componentes.BotaoJogo;
import settlers.limite.componentes.JanelaJogo;
import settlers.limite.componentes.PainelJogo;

public class JanelaOferecerTroca extends JanelaJogo {

	private static final long serialVersionUID = 1L;

	private AtorJogador atorJogador;
	
	private PainelJogador paineisJogador[];
	private PainelTroca paineisOferta[];
	private PainelTroca paineisProcura[];
	
	private BotaoJogo bEnviar;
	private BotaoJogo bCancelar;
	private BotaoJogo bAceitar[]; 
	
	private boolean trocaEmAndamento;
	private int jogadorInstancia;
	private int jogadorDaVez;
	private int recursosDisponiveis[];
	
	public JanelaOferecerTroca(AtorJogador atorJogador, int jogadorInstancia, int jogadorDaVez, String jogadores[], int recursos[]) {
		super(atorJogador, 160 * jogadores.length + 10, 480);
		setTitle("Oferecer Troca");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    getRootPane().setDefaultButton(bEnviar);
		getRootPane().registerKeyboardAction(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
    			bCancelar.doClick();
	    	}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				bCancelar.doClick();
			}
		});
		
		this.atorJogador = atorJogador;

		this.jogadorInstancia = jogadorInstancia;
		this.jogadorDaVez = jogadorDaVez;
		this.recursosDisponiveis = recursos;
		
		paineisJogador = new PainelJogador[jogadores.length];
		paineisOferta = new PainelTroca[jogadores.length];
		paineisProcura = new PainelTroca[jogadores.length];
		
		bAceitar = new BotaoJogo[jogadores.length];
		
		add(new PainelSeparacao("Oferta", (160 * jogadores.length) - 10, 45));
		add(new PainelSeparacao("Procura", (160 * jogadores.length) - 10, 245));

		for (int i = 0; i < jogadores.length; i++) {
			paineisJogador[i] = new PainelJogador(i + 1, jogadores[i]);
			paineisOferta[i] = new PainelTroca(PainelTroca.TIPO_OFERTA, i + 1);
			paineisProcura[i] = new PainelTroca(PainelTroca.TIPO_PROCURA, i + 1);
			if (i + 1 == jogadorInstancia) {
				bEnviar = new BotaoJogo("Enviar");
				bEnviar.setSize(70, 25);
				bEnviar.setLocation(10 + (160 * i), 445);
				bEnviar.setEnabled(false);
				bEnviar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						enviar();
					}
				});
				add(bEnviar);
				bCancelar = new BotaoJogo("Cancelar");
				bCancelar.setSize(70, 25);
				bCancelar.setLocation(90 + (160 * (i)), 445);
				bCancelar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cancelar();
					}
				});
				add(bCancelar);
			} else {
				bAceitar[i] = new BotaoJogo("Aceitar");
				bAceitar[i].setSize(70, 25);
				bAceitar[i].setLocation(45 + (160 * (i)), 445);
				bAceitar[i].setEnabled(false);
				bAceitar[i].addActionListener(new TrataEventosAceitar(i + 1));
				add(bAceitar[i]);
			}
		}
	}
	
	public int getQuantidadeOferta(int jogador) {
		return paineisOferta[jogador - 1].getQuantidadeRecursos();
	}
	
	public int getQuantidadeProcura(int jogador) {
		return paineisProcura[jogador - 1].getQuantidadeRecursos();
	}
	
	public int[] getOferta() {
		return paineisOferta[jogadorInstancia - 1].getRecursos();
	}
	
	public int[] getProcura() {
		return paineisProcura[jogadorInstancia - 1].getRecursos();
	}
	
	public void habilitarEnviar() {
		if (!trocaEmAndamento && getQuantidadeOferta(jogadorInstancia) > 0 && getQuantidadeProcura(jogadorInstancia) > 0) {
			bEnviar.setEnabled(true);
		} else {
			bEnviar.setEnabled(false);
		}
	}
	
	public void atualizarOferta(int jogador, int oferta[], int procura[]) {
		paineisOferta[jogador - 1].setRecursos(oferta);
		paineisProcura[jogador - 1].setRecursos(procura);
		if (oferta != null && procura != null) {
			if (jogadorInstancia == jogadorDaVez || jogador == jogadorDaVez) {
				if (possuiRecursosProcura(procura)) {
					bAceitar[jogador - 1].setEnabled(true);
				}
			} else {
				bAceitar[jogador - 1].setEnabled(false);
			}
		} else {
			bAceitar[jogador - 1].setEnabled(false);
		}
	}
	
	public boolean possuiRecursosProcura(int procura[]) {
		for (int i = 0; i < 5; i++) {
			if (procura[i] > recursosDisponiveis[i]) {
				return false;
			}
		}
		return true;
	}
	
	public void enviar() {
		paineisOferta[jogadorInstancia - 1].setStatusControles(false);
		paineisProcura[jogadorInstancia - 1].setStatusControles(false);
		trocaEmAndamento = true;
		atorJogador.oferecerTroca(getOferta(), getProcura());
		bEnviar.setEnabled(false);
	}
	
	public void cancelar() {
		if (trocaEmAndamento) {
			if (jogadorInstancia == jogadorDaVez) {
				for (int i = 0; i < paineisOferta.length; i++) {
					if (i + 1 != jogadorInstancia) {
						paineisOferta[i].setRecursos(null);
						paineisProcura[i].setRecursos(null);
					}
				}
			}
			paineisOferta[jogadorInstancia - 1].setStatusControles(true);
			paineisProcura[jogadorInstancia - 1].setStatusControles(true);
			trocaEmAndamento = false;
			atorJogador.oferecerTroca(null, null);
			bEnviar.setEnabled(true);
		} else {
			dispose();
		}
	}
	
	public void aceitar(int jogador) {
		atorJogador.aceitarTroca(jogador, paineisOferta[jogador - 1].getRecursos(), paineisProcura[jogador - 1].getRecursos());
		dispose();
	}
	
	public ImageIcon getIconeRecurso(int tipo) {
		switch (tipo) {
		case AtorJogador.RECURSO_OVELHA:  return new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/ovelha.png")).getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH));
		case AtorJogador.RECURSO_MADEIRA: return new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/madeira.png")).getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH));
		case AtorJogador.RECURSO_MINERIO: return new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/minerio.png")).getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH));
		case AtorJogador.RECURSO_TIJOLO:  return new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/tijolo.png")).getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH));
		case AtorJogador.RECURSO_TRIGO:   return new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/trigo.png")).getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH));
		}
		return null;
	}

	public String getNome(int tipo) {
		switch (tipo) {
		case AtorJogador.RECURSO_OVELHA:  return "Ovelha";
		case AtorJogador.RECURSO_MADEIRA: return "Madeira";
		case AtorJogador.RECURSO_MINERIO: return "Minério";
		case AtorJogador.RECURSO_TIJOLO:  return "Tijolo";
		case AtorJogador.RECURSO_TRIGO:   return "Trigo";
		}
		return null;
	}
	
	private class TrataEventosAceitar implements ActionListener {
		
		private int jogador;
		
		public TrataEventosAceitar(int jogador) {
			this.jogador = jogador;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			aceitar(jogador);
		}
		
	}
	
	private class PainelJogador extends PainelJogo {

		private static final long serialVersionUID = 1L;
		
		private JLabel lNome;
		
		private int jogador;
		
		public PainelJogador(int jogador, String nome) {
			super();
			JanelaOferecerTroca.this.add(this);
			setSize(150, 25);
			setLocation(10 + (160 * (jogador - 1)), 10);
			
			this.jogador = jogador;
			
			lNome = new JLabel(nome);
			lNome.setForeground(Color.white);
			lNome.setSize(150, 25);
			lNome.setLocation(0, 0);
			lNome.setVerticalAlignment(JLabel.CENTER);
			lNome.setHorizontalAlignment(JLabel.CENTER);
			add(lNome);
		}

		@Override
	    public void paintComponent(Graphics g) {
	    	super.paintComponent(g);
	    	Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setColor(AtorJogador.getCorJogador(jogador));
	        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
	        g2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
	        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
	    }
		
	}
	
	private class PainelSeparacao extends PainelJogo {

		private static final long serialVersionUID = 1L;
		
		private JLabel lTitulo;
		
		public PainelSeparacao(String titulo, int largura, int posicaoY) {
			super();
			setSize(largura, 25);
			setLocation(10, posicaoY);
			
			lTitulo = new JLabel(titulo);
			lTitulo.setForeground(Color.white);
			lTitulo.setSize(getWidth(), 25);
			lTitulo.setLocation(0, 0);
			lTitulo.setVerticalAlignment(JLabel.CENTER);
			lTitulo.setHorizontalAlignment(JLabel.CENTER);
			add(lTitulo);
		}

		@Override
	    public void paintComponent(Graphics g) {
	    	super.paintComponent(g);
	    	Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
	        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
	        g2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
	        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
	    }
		
	}
	
	private class PainelTroca extends PainelJogo {

		private static final long serialVersionUID = 1L;
		
		public static final int TIPO_OFERTA  = 0;
		public static final int TIPO_PROCURA = 1;
		
		private int tipo;
		private int recursos[];
		private int jogador;
		
		private JLabel lQuantidade[];
		private BotaoRecurso botoesRecurso[];
		private Stack<LabelRecurso> iconesRecursos[];
		
		@SuppressWarnings("unchecked")
		public PainelTroca(int tipo, int jogador) {
			super();
			JanelaOferecerTroca.this.add(this);
			setSize(150, 155);
			setLocation(10 + (160 * (jogador - 1)), 80 + (200 * (tipo)));
			setEnabled(jogador == jogadorInstancia);
			
			this.tipo = tipo;
			this.recursos = new int[5];
			this.jogador = jogador;
			
			iconesRecursos = new Stack[5];
			
			lQuantidade = new JLabel[5];
			botoesRecurso = new BotaoRecurso[5];
			for (int i = 0; i < 5; i++) {
				iconesRecursos[i] = new Stack<LabelRecurso>();
				botoesRecurso[i] = new BotaoRecurso(i);
				botoesRecurso[i].setEnabled(jogador == jogadorInstancia && (tipo == TIPO_PROCURA || recursosDisponiveis[i] > 0));
				botoesRecurso[i].setLocation(5, 5 + (30 * i));
				add(botoesRecurso[i]);
				lQuantidade[i] = new JLabel();
				lQuantidade[i].setSize(20, 23);
				lQuantidade[i].setLocation(125, 5 + (30 * i));
				lQuantidade[i].setHorizontalAlignment(JLabel.RIGHT);
				lQuantidade[i].setVerticalAlignment(JLabel.CENTER);
				lQuantidade[i].setForeground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
				add(lQuantidade[i]);
			}
		}
		
		public void setStatusControles(boolean ativo) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < iconesRecursos[i].size(); j++) {
					iconesRecursos[i].get(j).setAtivo(ativo);
				}
				botoesRecurso[i].setEnabled(ativo);
			}
		}
		
		public void adicionarRecurso(int recurso) {
			LabelRecurso lRecurso = new LabelRecurso(recurso);
			lRecurso.setAtivo(isEnabled());
			iconesRecursos[recurso].push(lRecurso);
			add(lRecurso, 0);
			repaint();
			recursos[recurso]++;
			lQuantidade[recurso].setText(Integer.toString(recursos[recurso]));
			if (jogador == jogadorInstancia && recursos[recurso] < 10 && (tipo == TIPO_PROCURA || recursosDisponiveis[recurso] > recursos[recurso])) {
				botoesRecurso[recurso].setEnabled(true);
			} else {
				botoesRecurso[recurso].setEnabled(false);
			}
			habilitarEnviar();
		}
		
		public void removerRecurso(int recurso) {
			remove((LabelRecurso) iconesRecursos[recurso].pop());
			repaint();
			recursos[recurso]--;
			if (recursos[recurso] > 0) {
				lQuantidade[recurso].setText(Integer.toString(recursos[recurso]));
			} else {
				lQuantidade[recurso].setText("");
			}
			if (jogador == jogadorInstancia && (tipo == TIPO_PROCURA || recursosDisponiveis[recurso] > recursos[recurso])) {
				botoesRecurso[recurso].setEnabled(true);
			}
			habilitarEnviar();
		}
		
		public int getQuantidadeRecursos() {
			int quantidade = 0;
			for (int i = 0; i < 5; i++) {
				quantidade += recursos[i];
			}
			return quantidade;
		}
		
		public int[] getRecursos() {
			return recursos;
		}
		
		public void setRecursos(int novosRecuros[]) {
			if (novosRecuros == null) {
				for (int i = 0; i < 5; i++) {
					while (recursos[i] > 0) {
						removerRecurso(i);
					}
				}
			} else {
				for (int i = 0; i < 5; i++) {
					if (recursos[i] < novosRecuros[i]) {
						while (recursos[i] < novosRecuros[i]) {
							adicionarRecurso(i);
						}
					} else if (recursos[i] > novosRecuros[i]) {
						while (recursos[i] > novosRecuros[i]) {
							removerRecurso(i);
						}
					}
				}
			}
		}
		
		private class LabelRecurso extends JLabel {

			private static final long serialVersionUID = 1L;
			
			private int tipo;
			private boolean ativo;
			
			public LabelRecurso(int tipoRecurso) {
				super(getIconeRecurso(tipoRecurso));
				
				this.tipo = tipoRecurso;
				this.ativo = true;
				
				setSize(23, 23);
				setLocation(35 + (7 * recursos[this.tipo]), 5 + (30 * this.tipo) + 1);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
				addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (ativo) {
							removerRecurso(tipo);
						}
					}
				});
			}
			
			public void setAtivo(boolean ativo) {
				this.ativo = ativo;
				if (ativo) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				} else {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
			
			public int getTipo() {
				return tipo;
			}
			
		}
		
		
		private class BotaoRecurso extends BotaoJogo {

			private static final long serialVersionUID = 1L;
			
			private int recurso;
			
			private ImageIcon icone;
			
			public BotaoRecurso(int tipo) {
				this.recurso = tipo;
				this.icone = getIconeRecurso(recurso);
				
				setToolTipText(getNome(recurso));
				setSize(25, 25);
				addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						adicionarRecurso(recurso);
					}
				});
			}
			
			@Override
			public void paint(Graphics g) {
		    	Graphics2D g2 = (Graphics2D) g;
		    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(getCorFundo());
		        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 5, 5);
		        g2.setColor(getCorBorda());
		        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 5, 5);
		        // Desenha Icone
		        if (isEnabled()) {
		        	g2.drawImage(icone.getImage(), 1, 1, this);
		        } else {
			        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
		        	ImageFilter filter = new GrayFilter(true, 20);  
		        	ImageProducer producer = new FilteredImageSource(icone.getImage().getSource(), filter);  
		        	Image imagem = this.createImage(producer);  	        	
		           	g2.drawImage(new ImageIcon(imagem).getImage(), 1, 1, this);
			        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		        }
			}
			
		}
		
	}

}
