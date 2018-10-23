package settlers.limite.paineis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelBarraStatus extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel texto;
	
	public PainelBarraStatus() {
		setPreferredSize(new Dimension(50, 20));
		setOpaque(false);
		setLayout(null);
		
		texto = new JLabel();
		texto.setSize(854, 20);
		texto.setLocation(5, 0);
		add(texto);
	}
	
	public void setTexto(String texto) {
		this.texto.setText(texto);
	}
	
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.white);
        g2.drawLine(0, 0, getWidth(), 0);
        Color[] colors = {new Color(0.73f, 0.85f, 0.94f), new Color(0.95f, 0.98f, 0.99f)};
        float[] dist = {0.0f, 1.0f};
        g2.setPaint(new LinearGradientPaint(getWidth(), 0, 0, 0, dist, colors));
        g2.fillRect(0, 1, getWidth(), getHeight());
        colors[0] = new Color(0.61f, 0.74f, 0.85f);
        colors[1] = new Color(0.95f, 0.98f, 0.99f, 0.0f);
        g2.setPaint(new LinearGradientPaint(0, 0, 0, 10, dist, colors));
        g2.fillRect(0, 1, getWidth(), getHeight());
    }

}
