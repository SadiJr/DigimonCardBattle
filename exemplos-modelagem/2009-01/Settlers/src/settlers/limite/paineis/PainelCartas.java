package settlers.limite.paineis;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import settlers.limite.AtorJogador;

public class PainelCartas extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Recurso recursos[][];
	private AtorJogador atorJogador;
	
	public PainelCartas(AtorJogador atorJogador) {
		this.atorJogador = atorJogador;
		
		setSize(260, 110);
		setLayout(null);
		setOpaque(false);
		
		TratadorEventosUsarCarta tratadorEventosUsarCarta = new TratadorEventosUsarCarta();
		TratadorEventosRecurso tratadorEventosRecurso = new TratadorEventosRecurso();
		
		recursos = new Recurso[2][5];
		for (int linha = 0; linha < 2; linha++) {
			for (int coluna = 0; coluna < 5; coluna++) {
				recursos[linha][coluna] = new Recurso(linha, coluna);
				if (linha == 1) {
					recursos[linha][coluna].addMouseListener(tratadorEventosUsarCarta);
				} else {
					recursos[linha][coluna].addMouseListener(tratadorEventosRecurso);
				}
				add(recursos[linha][coluna]);
			}
		}
	}
	
	public void inicializarPainelCartas() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {
				recursos[i][j].setValor(0, false);
			}
		}
	}
	
	public void atualizarRecurso(int tipo, int valor) {
		recursos[0][tipo].setValor(valor, true);
	}
	
	public void atualizarCarta(int tipo, int valor) {
		recursos[1][tipo].setValor(valor, true);
	}
	
	public void ativarCarta(int tipo) {
		recursos[1][tipo].ativar();
	}
	
	public void ativarRecursos() {
		for (int i = 0; i < 5; i++) {
			recursos[0][i].ativar();
		}
	}
	
	public void desativarCartas() {
		for (int i = 0; i < 5; i++) {
			recursos[1][i].desativar();
		}
	}
	
	public void desativarRecursos() {
		for (int i = 0; i < 5; i++) {
			recursos[0][i].desativar();
		}
	}
	
	private class TratadorEventosUsarCarta extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (((Recurso) e.getSource()).isAtivo()) {
				desativarCartas();
				atorJogador.usarCarta(((Recurso) e.getSource()).getTipo());
			}
		}
	}
	
	private class TratadorEventosRecurso extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (((Recurso) e.getSource()).isAtivo()) {
				desativarRecursos();
				atorJogador.acaoUsarCarta(((Recurso) e.getSource()).getTipo());
			}
		}
	}

	private class Recurso extends JPanel {
		
		private static final long serialVersionUID = 1L;

		private boolean ativo;
		private int valorAntigo;
		private int tipo;
		
		private JLabel lQuantidade;
		private JLabel lDestaque;
		private JLabel lIcone;
		private JLabel lNome;
		
		private ThreadDestacar threadDestacar = new ThreadDestacar();
		
		private float opacidade = 0.5f;
		
		public Recurso(int linha, int coluna)  {
			tipo = coluna;
			
			setSize(44, 50);
			setLayout(null);
			setLocation(54 * coluna, 60 * linha);
			setToolTipText(getToolTip(linha, coluna));
			setOpaque(false);
			
			if (linha == 0) {
				lIcone = new JLabel(getIconeRecurso(coluna));
				lIcone.setLocation(2, 6);
				lIcone.setSize(35, 35);
			} else {
				lIcone = new JLabel(getIconeCarta(coluna));
				lIcone.setBorder(BorderFactory.createLineBorder(Color.white));
				lIcone.setLocation(12, 5);
				lIcone.setSize(19, 30);
			}
			
			lQuantidade = new JLabel("0");
			lQuantidade.setSize(40, 15);
			lQuantidade.setLocation(1, 0);
			lQuantidade.setHorizontalAlignment(JLabel.RIGHT);
			lQuantidade.setForeground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
			
			lDestaque = new JLabel();
			lDestaque.setSize(40, 15);
			lDestaque.setLocation(1, 25);
			lDestaque.setHorizontalAlignment(JLabel.RIGHT);
			lDestaque.setForeground(Color.white);
			lDestaque.setVisible(false);
			
			lNome = new JLabel(getNome(linha, coluna));
			lNome.setFont(getFont().deriveFont(9f));
			lNome.setSize(44, 15);
			lNome.setLocation(0, 35);
			lNome.setHorizontalAlignment(JLabel.CENTER);
			lNome.setForeground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
			
			add(lQuantidade);
			add(lDestaque);
			add(lIcone);
			add(lNome);
		}
		
		public void setValor(int valor, boolean destacar) {
			if (destacar && valor != valorAntigo) {
				if (valor - valorAntigo > 0) {
					lDestaque.setText("+" + (valor - valorAntigo));
				} else {
					lDestaque.setText(((Integer) (valor - valorAntigo)).toString());
				}
				valorAntigo = valor;
				destacar();
			} else {
				lQuantidade.setText(((Integer) valor).toString());
				valorAntigo = valor;
			}
		}
		
		public int getTipo() {
			return tipo;
		}
		
		public boolean isAtivo() {
			return ativo;
		}

		public void ativar() {
			if (!ativo) {
				ativo = true;
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				setOpacidade(0.8f);
			}
		}
		
		public void desativar() {
			if (ativo) {
				ativo = false;
				setOpacidade(0.5f);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		public void setOpacidade(float valor) {
			opacidade = valor;
			repaint();
		}
		
		public float getOpacidade() {
			return opacidade;
		}
		
		private void destacar() {
			if (threadDestacar.isAlive()) {
				threadDestacar.parar();
			}
			threadDestacar = new ThreadDestacar();
			threadDestacar.start();
		}
		
	    private class ThreadDestacar extends Thread {
	    	
	    	private boolean parar = false;
	    	
	        @Override
	        public void run() {
	        	while (getOpacidade() <= 0.8f) {
	        		if (parar || isAtivo()) {
	    				lQuantidade.setText(((Integer) valorAntigo).toString());
	        			return;
	        		}
	        		setOpacidade(getOpacidade() + 0.05f);
	        		dormir(50);
	        	}
        		if (parar || isAtivo()) {
    				lQuantidade.setText(((Integer) valorAntigo).toString());
        			return;
        		}
	        	lDestaque.setVisible(true);
        		dormir(3000);
	        	lDestaque.setVisible(false);
				lQuantidade.setText(((Integer) valorAntigo).toString());
	        	while (getOpacidade() > 0.5f && !parar && !isAtivo()) {
	        		setOpacidade(getOpacidade() - 0.05f);
	        		dormir(50);
	        	}
	        }
	        
	        public void parar() {
	        	parar = true;
	        }

	        private void dormir(int tempo) {
	            try { 
	                sleep(tempo);
	            } catch (InterruptedException ex) {}
	        }
	        
 	    }

		private String getToolTip(int linha, int coluna) {
			if (linha == 0) {
				switch (coluna) {
				case AtorJogador.RECURSO_OVELHA:  return "Ovelha";
				case AtorJogador.RECURSO_MADEIRA: return "Madeira";
				case AtorJogador.RECURSO_MINERIO: return "Minério";
				case AtorJogador.RECURSO_TIJOLO:  return "Tijolo";
				case AtorJogador.RECURSO_TRIGO:   return "Trigo";
				}
			} else {
				switch (coluna) {
				case AtorJogador.CARTA_CONSTRUCAO_ESTRADA: return "Construção de Estrada";
				case AtorJogador.CARTA_ANO_FARTURA:        return "Ano de Fartura";
				case AtorJogador.CARTA_MONOPOLIO:          return "Monopólio";
				case AtorJogador.CARTA_SOLDADO:            return "Soldado";
				case AtorJogador.CARTA_PONTO_VITORIA:      return "Ponto de Vitória";
				}
			}
			return null;
		}

		private String getNome(int linha, int coluna) {
			if (linha == 0) {
				return getToolTip(linha, coluna);
			} else {
				switch (coluna) {
				case AtorJogador.CARTA_CONSTRUCAO_ESTRADA: return "Estrada";
				case AtorJogador.CARTA_ANO_FARTURA:        return "Fartura";
				case AtorJogador.CARTA_MONOPOLIO:          return "Monopólio";
				case AtorJogador.CARTA_SOLDADO:            return "Soldado";
				case AtorJogador.CARTA_PONTO_VITORIA:      return "Vitória";
				}
			}
			return null;
		}
		
		private ImageIcon getIconeRecurso(int tipo) {
			switch (tipo) {
			case AtorJogador.RECURSO_OVELHA:  return new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/ovelha.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
			case AtorJogador.RECURSO_MADEIRA: return new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/madeira.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
			case AtorJogador.RECURSO_MINERIO: return new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/minerio.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
			case AtorJogador.RECURSO_TIJOLO:  return new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/tijolo.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
			case AtorJogador.RECURSO_TRIGO:   return new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/trigo.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
			}
			return null;
		}
		
		private ImageIcon getIconeCarta(int tipo) {
			switch (tipo) {
			case AtorJogador.CARTA_CONSTRUCAO_ESTRADA: return new ImageIcon(ClassLoader.getSystemResource("cartas/construcao_estrada.png"));
			case AtorJogador.CARTA_ANO_FARTURA:        return new ImageIcon(ClassLoader.getSystemResource("cartas/ano_fartura.png"));
			case AtorJogador.CARTA_MONOPOLIO:          return new ImageIcon(ClassLoader.getSystemResource("cartas/monopolio.png"));
			case AtorJogador.CARTA_SOLDADO:            return new ImageIcon(ClassLoader.getSystemResource("cartas/soldado.png"));
			case AtorJogador.CARTA_PONTO_VITORIA:      return new ImageIcon(ClassLoader.getSystemResource("cartas/ponto_vitoria.png"));
			}
			return null;
		}
		
	    @Override
	    public void paintComponent(Graphics g) {
	    	super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setColor(new Color(1.0f, 1.0f, 1.0f, opacidade - 0.3f));
	        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
	        g2.setColor(new Color(1.0f, 1.0f, 1.0f, opacidade));
	        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
	    }
	    
	}

}
