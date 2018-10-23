package settlers.limite.componentes;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;

public abstract class JanelaJogo extends JDialog {
    
    private Painel painel = new Painel();
    
	public JanelaJogo(Frame owner, int width, int height) {
		super(owner);
		pack();
		setModal(true);
		setResizable(false);
		setSize(width + getInsets().left + getInsets().right, height + getInsets().top + getInsets().bottom);
		setLocationRelativeTo(owner);
		add(painel, BorderLayout.CENTER);
	}
	
	@Override
	public Component add(Component comp) {
		return painel.add(comp);
	}
	
	@Override
	public void remove(Component arg0) {
		painel.remove(arg0);
	}
	
	private class Painel extends JPanel {
		
		private static final long serialVersionUID = 1L;
		private ImageIcon textura = new ImageIcon(ClassLoader.getSystemResource("oceano.jpg"));;
		
		public Painel() {
			super();
			setLayout(null);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        for (int x = 0; x < getWidth(); x += textura.getIconWidth()) {
	            for (int y = 0; y < getHeight(); y += textura.getIconHeight()) {
	            	g2.drawImage(textura.getImage(), x, y, this);
	            }
	        }
	        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
	        float[] dist = {0.0f, 1.0f};
	        Color[] colors = {Color.blue.darker().darker(), Color.white};
	        g2.setPaint(new LinearGradientPaint(new Point2D.Float(0, 0), new Point2D.Float(getWidth(), getHeight()), dist, colors));
	        g2.fillRect(0, 0, getWidth(), getHeight());
	        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		}
		
	}

}
