package settlers.limite.janelas;

import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;

import javax.swing.ButtonGroup;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;

import settlers.limite.AtorJogador;
import settlers.limite.componentes.BotaoJogo;
import settlers.limite.componentes.JanelaJogo;
import settlers.limite.componentes.PainelJogo;

public class JanelaTrocaMaritima extends JanelaJogo {
	
	private static final long serialVersionUID = 1L;

	AtorJogador atorJogador;
	
	ImageIcon icones[];
	ImageIcon iconesGray[];
	JRadioButton rbOferta[];
	JRadioButton rbTroca[];
	ButtonGroup grupoOferta;
	ButtonGroup grupoTroca;
	
	JLabel seta;
	PainelJogo oferta;
	PainelJogo troca;
	
	JButton bOK = new BotaoJogo("OK");
	JButton bCancelar = new BotaoJogo("Cancelar");
	
	TratadorEventosTroca tratadorEventosTroca = new TratadorEventosTroca();
	TratadorEventosBotoes tratadorEventosBotoes = new TratadorEventosBotoes();
	
	int proporcaoOfertas[];
	int recursosDisponiveis[];
	
	public JanelaTrocaMaritima(AtorJogador atorJogador, int recursosDisponiveis[], int proporcaoOfertas[]) {
		super(atorJogador, 290, 356);
		this.atorJogador = atorJogador;
		this.proporcaoOfertas = proporcaoOfertas;
		this.recursosDisponiveis = recursosDisponiveis;

		setTitle("Troca Marítima");
	    getRootPane().setDefaultButton(bOK);
		getRootPane().registerKeyboardAction(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
    			bCancelar.doClick();
	    	}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

		icones = new ImageIcon[5];
		icones[AtorJogador.RECURSO_OVELHA] = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/ovelha.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		icones[AtorJogador.RECURSO_MADEIRA] = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/madeira.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		icones[AtorJogador.RECURSO_MINERIO] = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/minerio.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		icones[AtorJogador.RECURSO_TIJOLO] = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/tijolo.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		icones[AtorJogador.RECURSO_TRIGO] = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("recursos/trigo.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

		iconesGray = new ImageIcon[5];
		ImageFilter filter = new GrayFilter(true, 50);  
		for (int i = 0; i < 5; i++) {
			iconesGray[i] = new ImageIcon(createImage(new FilteredImageSource(icones[i].getImage().getSource(), filter)));
		}
		
		seta = new JLabel(new ImageIcon(ClassLoader.getSystemResource("arrow.png")));
		seta.setSize(50, 50);
		seta.setLocation(115, 120);
		add(seta);
		
		geraOferta();
		geraTroca();
		
		bOK.setSize(95, 25);
		bOK.setLocation(35, 295);
		bOK.addActionListener(tratadorEventosBotoes);
		bOK.setEnabled(false);
		
		bCancelar.setSize(95, 25);
		bCancelar.setLocation(150, 295);
		bCancelar.addActionListener(tratadorEventosBotoes);
		
		add(bOK);
		add(bCancelar);
	}


	public static BufferedImage convertToGrayscale(BufferedImage source) { 
	     BufferedImageOp op = new ColorConvertOp(
	       ColorSpace.getInstance(ColorSpace.CS_GRAY), null); 
	     return op.filter(source, null);
	}

	private void geraOferta() {
		oferta = new PainelJogo();
		oferta.setSize(95, 255);
		oferta.setLocation(20, 20);
		add(oferta);
		
		grupoOferta = new ButtonGroup();
		rbOferta = new JRadioButton[5];
		int ativo = 0;
		int ativos = 0;
		for (int i = 0; i < 5; i++) {
			rbOferta[i] = new JRadioButton(proporcaoOfertas[i] + " x");
			rbOferta[i].addActionListener(tratadorEventosTroca);
			rbOferta[i].setOpaque(false);
			rbOferta[i].setSize(100, 35);
			rbOferta[i].setLocation(10, 10 + (i * 50));
			rbOferta[i].setEnabled(recursosDisponiveis[i] >= proporcaoOfertas[i]);
			JLabel lIcon = new JLabel(rbOferta[i].isEnabled() ? icones[i] : iconesGray[i]);
			lIcon.setSize(35, 35);
			lIcon.setLocation(50, 10 + (i * 50));
			grupoOferta.add(rbOferta[i]);
			oferta.add(rbOferta[i]);
			oferta.add(lIcon);
			if (recursosDisponiveis[i] >= proporcaoOfertas[i]) {
				ativos++;
				ativo = i;
			}
		}
		// Verifica se existe apenas uma opção e deixa selecionada
		if (ativos == 1) {
			rbOferta[ativo].setSelected(true);
		}
	}
	
	private void geraTroca() {
		troca = new PainelJogo();
		troca.setSize(95, 255);
		troca.setLocation(165, 20);
		add(troca);
		
		grupoTroca = new ButtonGroup();
		rbTroca = new JRadioButton[5];
		for (int i = 0; i < 5; i++) {
			rbTroca[i] = new JRadioButton("1 x");
			rbTroca[i].addActionListener(tratadorEventosTroca);
			rbTroca[i].setOpaque(false);
			rbTroca[i].setSize(100, 35);
			rbTroca[i].setLocation(10, 10 + (i * 50));
			JLabel lIcon = new JLabel(icones[i]);
			lIcon.setSize(35, 35);
			lIcon.setLocation(50, 10 + (i * 50));
			grupoTroca.add(rbTroca[i]);
			troca.add(rbTroca[i]);
			troca.add(lIcon);
		}
	}
	
	private void trocar() {
		int recursoOfertado = getRecursoOfertado();
		int recursoTrocado = getRecursoTrocado();
		atorJogador.trocaMaritima(recursoOfertado, proporcaoOfertas[recursoOfertado], recursoTrocado, 1);
		dispose();
	}
	
	
	private int getRecursoOfertado() {
		for (int i = 0; i < 5; i++) {
			if (rbOferta[i].isSelected()) {
				return i;
			}
		}
		return -1;
	}
	
	private int getRecursoTrocado() {
		for (int i = 0; i < 5; i++) {
			if (rbTroca[i].isSelected()) {
				return i;
			}
		}
		return -1;
	}
	
	private void cancelar() {
		dispose();
	}
	
	private class TratadorEventosTroca implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		if (grupoOferta.getSelection() != null && grupoTroca.getSelection() != null) {
    			bOK.setEnabled(true);
    		}
    	}
	}
	
	private class TratadorEventosBotoes implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		if (e.getSource() == bCancelar) {
    			cancelar();
    		} else if (e.getSource() == bOK) {
    			trocar();
    		}
    	}
	}
	
}
