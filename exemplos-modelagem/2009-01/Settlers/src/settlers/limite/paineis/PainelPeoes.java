package settlers.limite.paineis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

import settlers.limite.peoes.PeaoCidade;
import settlers.limite.peoes.PeaoEstrada;
import settlers.limite.peoes.PeaoVila;

public class PainelPeoes extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private PeaoEstrada estrada;
	private PeaoCidade cidade;
	private PeaoVila vila;
	
	private JLabel lEstradas;
	private JLabel lCidades;
	private JLabel lVilas;
	
	private Color corTexto;
	
	private int jogador;
	
	public PainelPeoes() {
        setLayout(null);
        setSize(85, 80);
        setVisible(true);
        setOpaque(false);
        
        jogador = 1;
        
        estrada = new PeaoEstrada(jogador, 50, 8);
        estrada.setLocation(25, 10);
        
        corTexto = new Color(1.0f, 1.0f, 1.0f, 0.5f);
        
        lEstradas = new JLabel("15");
        lEstradas.setHorizontalAlignment(JLabel.RIGHT);
        lEstradas.setForeground(corTexto);
        lEstradas.setSize(15, 20);
        lEstradas.setLocation(5, 5);

        vila = new PeaoVila(jogador);
        vila.setLocation(25, 25);
        
        lVilas = new JLabel("5");
        lVilas.setHorizontalAlignment(JLabel.RIGHT);
        lVilas.setForeground(corTexto);
        lVilas.setSize(15, 20);
        lVilas.setLocation(5, 30);

        cidade = new PeaoCidade(jogador);
        cidade.setLocation(25, 50);
        
        lCidades = new JLabel("4");
        lCidades.setHorizontalAlignment(JLabel.RIGHT);
        lCidades.setForeground(corTexto);
        lCidades.setSize(15, 20);
        lCidades.setLocation(5, 55);
        
        add(estrada);
        add(lEstradas);
        add(vila);
        add(lVilas);
        add(cidade);
        add(lCidades);
	}
	
	public void setJogador(int jogador) {
		estrada.setJogador(jogador);
		vila.setJogador(jogador);
		cidade.setJogador(jogador);
	}
	
	public void setEstradas(int estradas) {
		lEstradas.setText(((Integer) estradas).toString());
	}
	
	public void setVilas(int vilas) {
		lVilas.setText(((Integer) vilas).toString());
	}
	
	public void setCidades(int cidades) {
		lCidades.setText(((Integer) cidades).toString());
	}
	
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.2f));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        g2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
    }

}
