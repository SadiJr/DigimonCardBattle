package settlers.limite.paineis;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import settlers.limite.AtorJogador;

public class PainelLances extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BotaoLance bRolarDados;
	private BotaoLance bUsarCarta;
	private BotaoLance bOferecerTroca;
	private BotaoLance bTrocaMaritima;
	private BotaoLance bCancelar;
	private BotaoLance bPassarVez;

	private BotaoLance bConstruirEstrada;
	private BotaoLance bConstruirVila;
	private BotaoLance bConstruirCidade;
	private BotaoLance bComprarCarta;
	
	private AtorJogador atorJogador;
	
	public PainelLances(AtorJogador atorJogador) {
		this.atorJogador = atorJogador;
		
		setSize(260, 190);
		setLayout(null);
		setOpaque(false);
		
		bRolarDados = new BotaoLance("Rolar Dados");
		bRolarDados.setLocation(155, 10);
		bRolarDados.setSize(95, 20);
		bRolarDados.setFocusable(false);
		bRolarDados.setEnabled(false);
		bRolarDados.setMargin(new Insets(0, 0, 0, 0));
		bRolarDados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bRolarDados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bRolarDados.setEnabled(false);
				PainelLances.this.atorJogador.rolarDados();
			}
		});
		
		bUsarCarta = new BotaoLance("Usar Carta");
		bUsarCarta.setLocation(155, 40);
		bUsarCarta.setSize(95, 20);
		bUsarCarta.setFocusable(false);
		bUsarCarta.setEnabled(false);
		bUsarCarta.setMargin(new Insets(0, 0, 0, 0));
		bUsarCarta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bUsarCarta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelLances.this.atorJogador.usarCarta();
			}
		});
		
		bOferecerTroca = new BotaoLance("Oferecer Troca");
		bOferecerTroca.setLocation(155, 70);
		bOferecerTroca.setSize(95, 20);
		bOferecerTroca.setFocusable(false);
		bOferecerTroca.setEnabled(false);
		bOferecerTroca.setMargin(new Insets(0, 0, 0, 0));
		bOferecerTroca.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bOferecerTroca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelLances.this.atorJogador.oferecerTroca();
			}
		});
		
		bTrocaMaritima = new BotaoLance("Troca Marítima");
		bTrocaMaritima.setLocation(155, 100);
		bTrocaMaritima.setSize(95, 20);
		bTrocaMaritima.setFocusable(false);
		bTrocaMaritima.setEnabled(false);
		bTrocaMaritima.setMargin(new Insets(0, 0, 0, 0));
		bTrocaMaritima.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bTrocaMaritima.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelLances.this.atorJogador.trocaMaritima();
			}
		});
		
		bCancelar = new BotaoLance("Cancelar");
		bCancelar.setLocation(155, 130);
		bCancelar.setSize(95, 20);
		bCancelar.setFocusable(false);
		bCancelar.setEnabled(false);
		bCancelar.setMargin(new Insets(0, 0, 0, 0));
		bCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelLances.this.atorJogador.cancelarJogada();
			}
		});

		bPassarVez = new BotaoLance("Passar a Vez");
		bPassarVez.setLocation(155, getHeight() - 30);
		bPassarVez.setSize(95, 20);
		bPassarVez.setFocusable(false);
		bPassarVez.setEnabled(false);
		bPassarVez.setMargin(new Insets(0, 0, 0, 0));
		bPassarVez.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bPassarVez.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelLances.this.atorJogador.passarVez();
			}
		});
		
		bConstruirEstrada = new BotaoLance("Construir Estrada", new ImageIcon(ClassLoader.getSystemResource("lances/construir_estrada.png")));
		bConstruirEstrada.setSize(135, 35);
		bConstruirEstrada.setLocation(10, 10);
		bConstruirEstrada.setEnabled(false);
		bConstruirEstrada.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bConstruirEstrada.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelLances.this.atorJogador.construirEstrada();
			}
		});
		
		bConstruirVila = new BotaoLance("Construir Vila", new ImageIcon(ClassLoader.getSystemResource("lances/construir_vila.png")));
		bConstruirVila.setSize(135, 35);
		bConstruirVila.setLocation(10, 55);
		bConstruirVila.setEnabled(false);
		bConstruirVila.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bConstruirVila.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelLances.this.atorJogador.construirVila();
			}
		});
		
		bConstruirCidade = new BotaoLance("Construir Cidade", new ImageIcon(ClassLoader.getSystemResource("lances/construir_cidade.png")));
		bConstruirCidade.setSize(135, 35);
		bConstruirCidade.setLocation(10, 100);
		bConstruirCidade.setEnabled(false);
		bConstruirCidade.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bConstruirCidade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelLances.this.atorJogador.construirCidade();
			}
		});
		
		bComprarCarta = new BotaoLance("Comprar Carta", new ImageIcon(ClassLoader.getSystemResource("lances/comprar_carta.png")));
		bComprarCarta.setSize(135, 35);
		bComprarCarta.setLocation(10, 145);
		bComprarCarta.setEnabled(false);
		bComprarCarta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bComprarCarta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelLances.this.atorJogador.comprarCarta();
			}
		});
		
		add(bRolarDados);
		add(bUsarCarta);
		add(bOferecerTroca);
		add(bTrocaMaritima);
		add(bCancelar);
		add(bPassarVez);
		
		add(bConstruirEstrada);
		add(bConstruirVila);
		add(bConstruirCidade);
		add(bComprarCarta);
	}
	
	public void desabilitarLances() {
		bRolarDados.setEnabled(false);
		bUsarCarta.setEnabled(false);
		bOferecerTroca.setEnabled(false);
		bTrocaMaritima.setEnabled(false);
		bCancelar.setEnabled(false);
		bPassarVez.setEnabled(false);
		bConstruirEstrada.setEnabled(false);
		bConstruirVila.setEnabled(false);
		bConstruirCidade.setEnabled(false);
		bComprarCarta.setEnabled(false);
	}
	
	public void habilitarRolarDados() {
		bRolarDados.setEnabled(true);
	}
	
	public void habilitarUsarCarta() {
		bUsarCarta.setEnabled(true);
	}
	
	public void habilitarOferecerTroca() {
		bOferecerTroca.setEnabled(true);
	}
	
	public void habilitarTrocaMaritma() {
		bTrocaMaritima.setEnabled(true);
	}

	public void habilitarCancelar() {
		bCancelar.setEnabled(true);
	}
	
	public void habilitarPassarVez() {
		bPassarVez.setEnabled(true);
	}
	
	public void habilitarConstruirEstrada() {
		bConstruirEstrada.setEnabled(true);
	}
	
	public void habilitarConstruirVila() {
		bConstruirVila.setEnabled(true);
	}
	
	public void habilitarConstruirCidade() {
		bConstruirCidade.setEnabled(true);
	}
	
	public void habilitarComprarCarta() {
		bComprarCarta.setEnabled(true);
	}
	
	private class BotaoLance extends JButton {
		
		private static final long serialVersionUID = 1L;
		
		private Color corFundo = new Color(1.0f, 1.0f, 1.0f, 0.2f);
		private Color corBorda = new Color(1.0f, 1.0f, 1.0f, 0.5f);

		public BotaoLance(String arg0) {
			super(arg0);
			setContentAreaFilled(false);
		}
		
		public BotaoLance(Icon arg0) {
			super(arg0);
			setContentAreaFilled(false);
		}
		
		public BotaoLance(String text, Icon icon) {
			super(text, icon);
			setContentAreaFilled(false);
		}
		
		@Override
		public void setEnabled(boolean b) {
			if (b) {
				corFundo = new Color(1.0f, 1.0f, 1.0f, 0.5f);
				corBorda = new Color(1.0f, 1.0f, 1.0f, 1.0f);
			} else {
				corFundo = new Color(1.0f, 1.0f, 1.0f, 0.2f);
				corBorda = new Color(1.0f, 1.0f, 1.0f, 0.5f);
			}
			super.setEnabled(b);
		}
		
		@Override
		public void paint(Graphics g) {
	    	Graphics2D g2 = (Graphics2D) g;

	    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setColor(corFundo);
	        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 5, 5);
	        g2.setColor(corBorda);
	        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 5, 5);
	        if (getIcon() != null) {
	        	Image imagem = ((ImageIcon) getIcon()).getImage();
	        	int x;
	        	int y;
	        	if (getText().length() != 0) {
		        	x = 4;
	        		y = (getHeight() - imagem.getHeight(this));
	        		g2.setFont(g2.getFont().deriveFont(9f));
			        g2.drawString(getText(), 4, 10);
	        	} else {
		        	x = (getWidth() - imagem.getWidth(this)) / 2;
	        		y = (getHeight() - imagem.getHeight(this)) / 2;
	        	}
	        	g2.drawImage(imagem, x, y, this);
	        } else {
		        if (getText().length() != 0) {
			        int x = (getWidth() - g2.getFontMetrics().stringWidth(getText())) / 2;
			        int y = g2.getFontMetrics().getMaxAscent() + (getHeight() - (g2.getFontMetrics().getAscent() + g2.getFontMetrics().getMaxDescent())) / 2;
			        g2.drawString(getText(), x, y);
		        }
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
