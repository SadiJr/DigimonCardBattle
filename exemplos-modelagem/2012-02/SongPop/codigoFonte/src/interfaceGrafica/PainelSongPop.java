package interfaceGrafica;

import java.text.DecimalFormat;

import javax.swing.JPanel;

public class PainelSongPop extends JPanel {
	private static final long serialVersionUID = 1L;
	protected static final DecimalFormat doubleFormat = new DecimalFormat("00.00");
	protected InterfaceGraficaSongPop frame;
	
	public PainelSongPop(InterfaceGraficaSongPop frame) {
		this.frame = frame;
	}
}