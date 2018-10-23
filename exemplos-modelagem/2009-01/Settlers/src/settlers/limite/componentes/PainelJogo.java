package settlers.limite.componentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PainelJogo extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public PainelJogo() {
		setLayout(null);
		setOpaque(false);
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
