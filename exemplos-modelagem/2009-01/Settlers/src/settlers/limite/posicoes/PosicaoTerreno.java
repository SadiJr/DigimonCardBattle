package settlers.limite.posicoes;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;

import settlers.limite.AtorJogador;
import settlers.limite.paineis.PainelTabuleiro;

public class PosicaoTerreno extends PosicaoHexagono {

	private static final long serialVersionUID = 1L;

    private ImageIcon imageIcon;
    private int numero;
    private int tipo;

    public PosicaoTerreno(int larguraAresta, PainelTabuleiro tabuleiro, int camada, int identificador) {
        super(larguraAresta, tabuleiro, camada, identificador);
    	tipo = AtorJogador.TERRENO_DESERTO;
        setImageIcon(buscaImagem());
    }

    public PosicaoTerreno(PosicaoTerreno terreno, int lado, int distancia, int camada, int identificador) {
    	super(terreno, lado, distancia, camada, identificador);
    	tipo = AtorJogador.TERRENO_DESERTO;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
        setImageIcon(buscaImagem());
        repaint();
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    private ImageIcon buscaImagem() {
        switch (tipo) {
            case AtorJogador.TERRENO_DESERTO:
                return new ImageIcon(ClassLoader.getSystemResource("terrenos/desert.jpg"));
            case AtorJogador.TERRENO_ARGILA:
                return new ImageIcon(ClassLoader.getSystemResource("terrenos/brick.jpg"));
            case AtorJogador.TERRENO_FLORESTA:
                return new ImageIcon(ClassLoader.getSystemResource("terrenos/lumber.jpg"));
            case AtorJogador.TERRENO_PASTO:
                return new ImageIcon(ClassLoader.getSystemResource("terrenos/pasture.jpg"));
            case AtorJogador.TERRENO_PLANTACAO:
                return new ImageIcon(ClassLoader.getSystemResource("terrenos/grain.jpg"));
            case AtorJogador.TERRENO_MINERIO:
                return new ImageIcon(ClassLoader.getSystemResource("terrenos/ore.jpg"));
        }
        return null;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.white);
        g2.fill(getPoligono());
        if (imageIcon != null) {
            g2.setClip(getPoligono());
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getOpacidade()));
            g2.drawImage(imageIcon.getImage(), (int) Math.round((getWidth() - imageIcon.getIconWidth()) / 2), (int) ((getHeight() - imageIcon.getIconHeight()) / 2), this);
            g2.setClip(null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
        
        g2.setStroke(new BasicStroke(2.0f));
        g2.setColor(Color.orange);
        g2.drawPolygon(getPoligono());
        if (numero > 0) {
            desenhaNumero(g2);
        }
    }

    private void desenhaNumero(Graphics2D g2) {
        int diametroCirculo = 24;
        g2.setColor(Color.darkGray);
        g2.drawOval((int) Math.round((getWidth() - diametroCirculo) / 2) + 1, (int) Math.round((getHeight() - diametroCirculo) / 2) + 1, diametroCirculo, diametroCirculo);
        g2.setPaint(new GradientPaint(0, 0, Color.white, diametroCirculo, diametroCirculo, Color.darkGray, true));
        g2.fillOval((int) Math.round((getWidth() - diametroCirculo) / 2), (int) Math.round((getHeight() - diametroCirculo) / 2), diametroCirculo, diametroCirculo);
        g2.setColor(Color.darkGray);
        g2.drawOval((int) Math.round((getWidth() - diametroCirculo) / 2) + 1, (int) Math.round((getHeight() - diametroCirculo) / 2) + 1, diametroCirculo, diametroCirculo);
        g2.setColor(Color.white);
        g2.drawOval((int) Math.round((getWidth() - diametroCirculo) / 2), (int) Math.round((getHeight() - diametroCirculo) / 2), diametroCirculo, diametroCirculo);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD));
        int largura = g2.getFontMetrics().stringWidth(((Integer) numero).toString());
        int altura = g2.getFontMetrics().getHeight();
        g2.setColor(numero >= 6 && numero <= 8 ? Color.red.darker() : Color.darkGray.darker());
        int x = (int) Math.round((getWidth() - largura) / 2) + 1 + (numero > 9 ? 0 : 1);
        int y = (int) Math.round((getHeight() + altura) / 2) - 2;
        g2.drawString(((Integer) numero).toString(), x, y);
    }

}
