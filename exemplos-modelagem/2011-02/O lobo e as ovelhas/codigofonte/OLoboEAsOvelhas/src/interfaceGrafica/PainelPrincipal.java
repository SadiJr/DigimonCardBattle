package interfaceGrafica;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PainelPrincipal extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private Image img;

	  public PainelPrincipal() {
		    this(new ImageIcon(ClassLoader.getSystemResource("Madeira.jpg")).getImage());
	  }

	public PainelPrincipal(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  @Override
	public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }
}
