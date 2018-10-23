package settlers.limite.paineis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import settlers.limite.AtorJogador;

public class PainelJogadores extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private PainelJogador painelJogadores1;
	private PainelJogador painelJogadores2;
	private PainelJogador painelJogadores3;
	private PainelJogador painelJogadores4;
	
	private JLabel iFlag;
	private JLabel lJogadores;
	private JLabel lRecursos;
	private JLabel lCartas;
	private JLabel lSoldados;
	private JLabel lMaiorEstrada;
	private JLabel lPontos;
	
	public PainelJogadores() {
		setSize(260, 120);
		setLayout(null);
		setOpaque(false);
		
		lJogadores = new JLabel("Jogadores");
		lJogadores.setForeground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
		lJogadores.setSize(50, 20);
		lJogadores.setLocation(20, 7);

		lRecursos = new JLabel(new ImageIcon(ClassLoader.getSystemResource("recursos/icone_recursos.png")));
		lRecursos.setBorder(BorderFactory.createLineBorder(Color.white));
		lRecursos.setToolTipText("Recursos");
		lRecursos.setSize(17, 26);
		lRecursos.setLocation(90, 4);

		lCartas = new JLabel(new ImageIcon(ClassLoader.getSystemResource("cartas/icone_cartas.png")));
		lCartas.setBorder(BorderFactory.createLineBorder(Color.white));
		lCartas.setToolTipText("Cartas Desenvolvimento");
		lCartas.setSize(17, 26);
		lCartas.setLocation(120, 4);

		lSoldados = new JLabel(new ImageIcon(ClassLoader.getSystemResource("icone_soldados.png")));
		lSoldados.setBorder(BorderFactory.createLineBorder(Color.white));
		lSoldados.setToolTipText("Soldados");
		lSoldados.setSize(17, 26);
		lSoldados.setLocation(150, 4);

		lMaiorEstrada = new JLabel(new ImageIcon(ClassLoader.getSystemResource("icone_maior_estrada.png")));
		lMaiorEstrada.setBorder(BorderFactory.createLineBorder(Color.white));
		lMaiorEstrada.setToolTipText("Maior Estrada");
		lMaiorEstrada.setSize(17, 26);
		lMaiorEstrada.setLocation(180, 4);
		
		lPontos = new JLabel("Pontos");
		lPontos.setForeground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
		lPontos.setHorizontalAlignment(JLabel.RIGHT);
		lPontos.setSize(40, 20);
		lPontos.setLocation(200, 7);
		
		iFlag = new JLabel(new ImageIcon(ClassLoader.getSystemResource("flag_green.png")));
		iFlag.setSize(25, 25);
		iFlag.setVisible(false);
	
		painelJogadores1 = new PainelJogador(1, AtorJogador.COR_JOGADOR_1);
		painelJogadores2 = new PainelJogador(2, AtorJogador.COR_JOGADOR_2);
		painelJogadores3 = new PainelJogador(3, AtorJogador.COR_JOGADOR_3);
		painelJogadores4 = new PainelJogador(4, AtorJogador.COR_JOGADOR_4);
		
		add(lJogadores);
		add(lRecursos);
		add(lCartas);
		add(lSoldados);
		add(lMaiorEstrada);
		add(lPontos);
		
		add(painelJogadores1);
		add(painelJogadores2);
		add(painelJogadores3);
		add(painelJogadores4);
		
		add(iFlag, 0);
	}
	
	public void setJogador(int jogador, String nome) {
		switch (jogador) {
		case 1: painelJogadores1.setNome(nome); break;
		case 2: painelJogadores2.setNome(nome); break;
		case 3: painelJogadores3.setNome(nome); break;
		case 4: painelJogadores4.setNome(nome); break;
		}
	}
	
	public void setPontos(int jogador, int pontos) {
		switch (jogador) {
		case 1: painelJogadores1.setPontos(pontos); break;
		case 2: painelJogadores2.setPontos(pontos); break;
		case 3: painelJogadores3.setPontos(pontos); break;
		case 4: painelJogadores4.setPontos(pontos); break;
		}
	}
	
	public void setPontosVitoria(int jogador, int pontos) {
		switch (jogador) {
		case 1: painelJogadores1.setPontosVitoria(pontos); break;
		case 2: painelJogadores2.setPontosVitoria(pontos); break;
		case 3: painelJogadores3.setPontosVitoria(pontos); break;
		case 4: painelJogadores4.setPontosVitoria(pontos); break;
		}
	}
	
	public void setRecursos(int jogador, int quantidade) {
		switch (jogador) {
		case 1: painelJogadores1.setRecursos(quantidade); break;
		case 2: painelJogadores2.setRecursos(quantidade); break;
		case 3: painelJogadores3.setRecursos(quantidade); break;
		case 4: painelJogadores4.setRecursos(quantidade); break;
		}
	}
	
	public void setCartas(int jogador, int quantidade) {
		switch (jogador) {
		case 1: painelJogadores1.setCartas(quantidade); break;
		case 2: painelJogadores2.setCartas(quantidade); break;
		case 3: painelJogadores3.setCartas(quantidade); break;
		case 4: painelJogadores4.setCartas(quantidade); break;
		}
	}
	
	public void setSoldados(int jogador, int quantidade) {
		switch (jogador) {
		case 1: painelJogadores1.setSoldados(quantidade); break;
		case 2: painelJogadores2.setSoldados(quantidade); break;
		case 3: painelJogadores3.setSoldados(quantidade); break;
		case 4: painelJogadores4.setSoldados(quantidade); break;
		}
	}
	
	public void setMaiorEstrada(int jogador, int tamanho) {
		switch (jogador) {
		case 1: painelJogadores1.setMaiorEstrada(tamanho); break;
		case 2: painelJogadores2.setMaiorEstrada(tamanho); break;
		case 3: painelJogadores3.setMaiorEstrada(tamanho); break;
		case 4: painelJogadores4.setMaiorEstrada(tamanho); break;
		}
	}

	public void setFlag(int jogador) {
		if (jogador > 0) {
			iFlag.setVisible(true);
			iFlag.setLocation(4, 35 + ((jogador - 1) * 19));
		} else {
			iFlag.setVisible(false);
		}
	}
	
	private class PainelJogador extends JPanel {
		
		private static final long serialVersionUID = 1L;
		
		private JLabel lNome;
		private JLabel lRecursos;
		private JLabel lCartas;
		private JLabel lSoldados;
		private JLabel lMaiorEstrada;
		private JLabel lPontos;
		private JLabel lPontosVitoria;
		
		public PainelJogador(int posicao, Color cor) {
			setBackground(cor);
			setSize(240, 20);
			setLocation(10, 33 + (19 * (posicao - 1)));
			setBorder(BorderFactory.createLineBorder(new Color(1.0f, 1.0f, 1.0f, 0.7f)));
			setLayout(null);

			lNome = new JLabel();
			lNome.setForeground(Color.white);
			lNome.setSize(50, 20);
			lNome.setLocation(20, 0);

			lRecursos = new JLabel();
			lRecursos.setForeground(Color.white);
			lRecursos.setHorizontalAlignment(JLabel.RIGHT);
			lRecursos.setSize(25, 20);
			lRecursos.setLocation(70, 0);

			lCartas = new JLabel();
			lCartas.setForeground(Color.white);
			lCartas.setHorizontalAlignment(JLabel.RIGHT);
			lCartas.setSize(25, 20);
			lCartas.setLocation(100, 0);

			lSoldados = new JLabel();
			lSoldados.setForeground(Color.white);
			lSoldados.setHorizontalAlignment(JLabel.RIGHT);
			lSoldados.setSize(25, 20);
			lSoldados.setLocation(130, 0);

			lMaiorEstrada = new JLabel();
			lMaiorEstrada.setForeground(Color.white);
			lMaiorEstrada.setHorizontalAlignment(JLabel.RIGHT);
			lMaiorEstrada.setSize(25, 20);
			lMaiorEstrada.setLocation(160, 0);

			lPontos = new JLabel();
			lPontos.setForeground(Color.white);
			lPontos.setHorizontalAlignment(JLabel.RIGHT);
			lPontos.setSize(30, 20);
			lPontos.setLocation(190, 0);

			lPontosVitoria = new JLabel();
			lPontosVitoria.setForeground(Color.white);
			lPontosVitoria.setSize(25, 20);
			lPontosVitoria.setLocation(221, 0);
			
			add(lNome);
			add(lRecursos);
			add(lCartas);
			add(lSoldados);
			add(lMaiorEstrada);
			add(lPontos);
			add(lPontosVitoria);
		}
		
		public void setNome(String nome) {
			lNome.setText(nome);
			if (lNome.getText().length() == 0) {
				lRecursos.setText("");
				lCartas.setText("");
				lSoldados.setText("");
				lMaiorEstrada.setText("");
				lPontos.setText("");
				lPontosVitoria.setText("");
			}		
		}
		
		public void setRecursos(int quantidade) {
			lRecursos.setText(((Integer) quantidade).toString());
		}
		
		public void setCartas(int quantidade) {
			lCartas.setText(((Integer) quantidade).toString());
		}
		
		public void setSoldados(int quantidade) {
			lSoldados.setText(((Integer) quantidade).toString());
		}
		
		public void setMaiorEstrada(int tamanho) {
			lMaiorEstrada.setText(((Integer) tamanho).toString());
		}
		
		public void setPontos(int pontos) {
			lPontos.setText(((Integer) pontos).toString());
		}
		
		public void setPontosVitoria(int pontos) {
			if (pontos < 1) {
				lPontosVitoria.setText("");
			} else {
				lPontosVitoria.setText("+" + pontos);
			}
		}
		
	}
	
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        float[] dist = {0.0f, 1.0f};
        Color[] colors = {new Color(0.0f, 0.0f, 0.0f, 0.2f), new Color(1.0f, 1.0f, 1.0f, 0.2f)};
        g2.setPaint(new LinearGradientPaint(0, 0, 0, getHeight(), dist, colors));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        g2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
     }

}
