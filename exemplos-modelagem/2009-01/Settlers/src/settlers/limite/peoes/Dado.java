package settlers.limite.peoes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

public class Dado extends JComponent {

	private static final long serialVersionUID = 1L;

	private static int DIAMETRO_PONTO = 7;

    private int valor;

    public Dado() {
        setBounds(0, 0, 40, 40);
        valor = (int)(6 * Math.random() + 1);
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
        paint(getGraphics());
    }
    
	public int rolar() {
        setValor((int)(6 * Math.random() + 1));
        return valor;
    }

    @Override
    public void paintComponent(Graphics g) {
        int w = getWidth() - 2;
        int h = getHeight() - 2;
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(1, 1, w, h, w * 20 / 100, h * 20 / 100);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setPaint(new GradientPaint(10, 10, Color.white, 30, 30, Color.LIGHT_GRAY, false));
        g2.fillRoundRect(0, 0, w, h, w * 20 / 100, h * 20 / 100);
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(0, 0, w-1, h-1, w * 20 / 100, h * 20 / 100);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(0, 0, w, h, w * 20 / 100, h * 20 / 100);

        switch (valor) {
            case 1:
                desenharPonto(g2, w / 2, h / 2);
                break;
            case 3:
                desenharPonto(g2, w / 2, h / 2);
            case 2:
                desenharPonto(g2, w / 4, h / 4);
                desenharPonto(g2, 3 * w / 4, 3 * h / 4);
                break;
            case 5:
                desenharPonto(g2, w / 2, h / 2);
            case 4:
                desenharPonto(g2, w / 4, h / 4);
                desenharPonto(g2, 3 * w / 4, 3 * h / 4);
                desenharPonto(g2, 3 * w / 4, h / 4);
                desenharPonto(g2, w / 4, 3 * h / 4);
                break;
            case 6:
                desenharPonto(g2, w / 4, h / 4);
                desenharPonto(g2, 3 * w /4, 3 * h / 4);
                desenharPonto(g2, 3 * w /4, h / 4);
                desenharPonto(g2, w / 4, 3 * h / 4);
                desenharPonto(g2, w / 4, h / 2);
                desenharPonto(g2, 3 * w / 4, h / 2);
                break;
        }
    }
    
    private void desenharPonto(Graphics2D g2, int x, int y) {
        float[] dist = {0.0f, 0.1f, 1.0f};
        Color[] colors = {Color.white, Color.lightGray, Color.darkGray.darker()};
        g2.setPaint(new RadialGradientPaint(new Point2D.Float(x + DIAMETRO_PONTO/2, y + DIAMETRO_PONTO/2), DIAMETRO_PONTO/2, dist, colors));
        g2.fillOval(x - DIAMETRO_PONTO / 2 + 1, y - DIAMETRO_PONTO / 2 + 1, DIAMETRO_PONTO, DIAMETRO_PONTO);
        g2.setColor(Color.black);
        g2.drawOval(x - DIAMETRO_PONTO / 2, y - DIAMETRO_PONTO / 2, DIAMETRO_PONTO, DIAMETRO_PONTO);
    }
    
}
