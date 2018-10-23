package settlers.limite.componentes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BotaoJogo extends JButton {
	
	private static final long serialVersionUID = 1L;
	
	private Color corFundo = new Color(1.0f, 1.0f, 1.0f, 0.5f);
	private Color corBorda = new Color(1.0f, 1.0f, 1.0f, 1.0f);

	public BotaoJogo() {
		super();
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public BotaoJogo(String arg0) {
		super(arg0);
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public BotaoJogo(Icon arg0) {
		super(arg0);
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public BotaoJogo(String text, Icon icon) {
		super(text, icon);
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
	
	public Color getCorFundo() {
		return corFundo;
	}

	public Color getCorBorda() {
		return corBorda;
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
		        int baseline = g2.getFontMetrics().getMaxAscent() + (getHeight() - (g2.getFontMetrics().getAscent() + g2.getFontMetrics().getMaxDescent())) / 2;
		        g2.drawString(getText(), x, baseline);
	        }
        }
        if (isEnabled() && isFocusOwner()) {
        	g2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
            g2.drawRoundRect(3, 3, getWidth() - 7, getHeight() - 7, 3, 3);
        }
	}
	
}
