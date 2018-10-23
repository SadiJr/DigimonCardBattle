package settlers.limite.posicoes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import settlers.limite.AtorJogador;

public class PosicaoPorto {

    private Image imagemRecurso;
    private Image imagemPorto;
    private PosicaoOceano oceano;
    private int diametro;
    private int recurso;
    private int proporcao;

    public PosicaoPorto(PosicaoOceano oceano) {
        this.oceano = oceano;
        this.diametro = oceano.getLarguraAresta();
        this.imagemPorto = new ImageIcon(ClassLoader.getSystemResource("terrenos/porto.png")).getImage();
    }
    
	public int getRecurso() {
        return recurso;
    }

    public void setRecurso(int recurso) {
        this.recurso = recurso;
        this.imagemRecurso = buscaImagemRecurso();
        this.proporcao = (recurso == AtorJogador.RECURSO_GENERICO) ? 3 : 2;
        oceano.repaint();
    }

    private Image buscaImagemRecurso() {
        switch (recurso) {
            case AtorJogador.RECURSO_GENERICO: return new ImageIcon(ClassLoader.getSystemResource("recursos/generico.png")).getImage();
            case AtorJogador.RECURSO_OVELHA:   return new ImageIcon(ClassLoader.getSystemResource("recursos/ovelha.png")).getImage();
            case AtorJogador.RECURSO_MADEIRA:  return new ImageIcon(ClassLoader.getSystemResource("recursos/madeira.png")).getImage();
            case AtorJogador.RECURSO_MINERIO:  return new ImageIcon(ClassLoader.getSystemResource("recursos/minerio.png")).getImage();
            case AtorJogador.RECURSO_TIJOLO:   return new ImageIcon(ClassLoader.getSystemResource("recursos/tijolo.png")).getImage();
            case AtorJogador.RECURSO_TRIGO:    return new ImageIcon(ClassLoader.getSystemResource("recursos/trigo.png")).getImage();
        }
        return null;
    }

    public void desenhaPorto(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        // Desenha Recurso
        g2.drawImage(imagemRecurso, (oceano.getWidth() - diametro) / 2, (int)Math.round((oceano.getHeight() - diametro) / 2), oceano);
        // String Proporcao
        String textoProporcao = proporcao + " : 1";
        // Desenha Proporcao Troca
        g2.setFont(g2.getFont().deriveFont(Font.BOLD));
        int largura = g2.getFontMetrics().stringWidth(textoProporcao);
        int altura = g2.getFontMetrics().getHeight();
        g2.setColor(Color.black);
        TextLayout tl = new TextLayout(textoProporcao, g2.getFont(), g2.getFontRenderContext());
        g2.setStroke(new BasicStroke(2.0f));
        AffineTransform transform = new AffineTransform();
        transform.translate((int)Math.round((oceano.getWidth() - largura) / 2) + 1, (int)Math.round((oceano.getHeight() + altura) / 2) - 1);
        g2.draw(tl.getOutline(transform));
        g2.setColor(Color.white);
        g2.drawString(textoProporcao, (int)Math.round((oceano.getWidth() - largura) / 2) + 1, (int)Math.round((oceano.getHeight() + altura) / 2) - 1);
        // Desenha Porto
        int ladoPraia = oceano.getLadoPraia();
        BufferedImage temp = new BufferedImage(oceano.getWidth(), oceano.getHeight(), BufferedImage.TYPE_INT_ARGB);
        temp.getGraphics().drawImage(imagemPorto, (oceano.getWidth() - imagemPorto.getWidth(oceano)) / 2, oceano.getHeight() - imagemPorto.getHeight(oceano), oceano);
        g2.rotate(((ladoPraia + 3) % 6) * 60.0 * Math.PI / 180.0, oceano.getWidth() / 2, oceano.getHeight() / 2);
        g2.drawImage(temp, 0, 0, oceano);
    }

}
