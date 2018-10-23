import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ImagemDePeca extends JButton {
	
	protected static final long serialVersionUID = 4635737989394575589L;
	protected Peca peca;
	protected Color cor;
	protected JPanel interno;

	public ImagemDePeca(Peca peca, ActionListener actionListener) {
		super();
		this.interno = new JPanel();
		this.addActionListener(actionListener);
		this.setActionCommand(JogadorView._SELECIONAR_PECA);
		this.setCor(peca.getCor());
		this.peca = peca;
		this.inicializar();
		this.setBorder(BorderFactory.createEmptyBorder());		
	}
	
	/**
	 * Inicialização da Imagem da peça
	 * Define formato e cores
	 *
	 */
	protected void inicializar(){						
		if(this.eLinha()){
			this.montarEmLinha();			
		} else if(this.eColuna()){
			this.montarEmColuna();			
		} else {
			this.montarEmCanto();			
		}
		this.interno.setBackground(this.cor);
		this.setBackground(this.cor);
		this.add(interno);		
	}
	
	protected void montarEmLinha(){
		JLabel rotulo;
		interno.setLayout(new GridLayout(1,2,5,5));
		int[][] formato = this.peca.getFormato();
		if(formato[0][0] != Formato.VAZIO){
			//Coluna 1
			rotulo = new JLabel();
			rotulo.setIcon(this.pegarIcone(formato[0][0], this.peca.ehPivo(0,0)));
			interno.add(rotulo);
			//Coluna 2
			rotulo = new JLabel();
			rotulo.setIcon(this.pegarIcone(formato[0][1], this.peca.ehPivo(0,1)));
			interno.add(rotulo);
		} else {
			//Coluna 1
			rotulo = new JLabel();
			rotulo.setIcon(this.pegarIcone(formato[1][0], this.peca.ehPivo(1,0)));
			interno.add(rotulo);
			//Coluna 2
			rotulo = new JLabel();
			rotulo.setIcon(this.pegarIcone(formato[1][1], this.peca.ehPivo(1,1)));
			interno.add(rotulo);			
		}		
	}
	
	protected void montarEmColuna(){
		JLabel rotulo;
		interno.setLayout(new GridLayout(2,1,5,5));
		int[][] formato = this.peca.getFormato();
		if(formato[0][0] != Formato.VAZIO){
			//Linha 1
			rotulo = new JLabel();
			rotulo.setIcon(this.pegarIcone(formato[0][0], this.peca.ehPivo(0,0)));
			interno.add(rotulo);
			//Linha 2
			rotulo = new JLabel();
			rotulo.setIcon(this.pegarIcone(formato[1][0], this.peca.ehPivo(1,0)));
			interno.add(rotulo);
		} else {
			//Linha 1
			rotulo = new JLabel();
			rotulo.setIcon(this.pegarIcone(formato[0][1], this.peca.ehPivo(0,1)));
			interno.add(rotulo);
			//Linha 2
			rotulo = new JLabel();
			rotulo.setIcon(this.pegarIcone(formato[1][1], this.peca.ehPivo(1,1)));			
			interno.add(rotulo);			
		}	
	}
	
	
	protected void montarEmCanto(){
		JLabel rotulo;
		int[][] formato = this.peca.getFormato();	
		interno.setLayout(new GridLayout(2,2,5,5));
		// Elemento 0,0
		rotulo = new JLabel();
		rotulo.setIcon(this.pegarIcone(formato[0][0], this.peca.ehPivo(0,0)));
		interno.add(rotulo);
		// Elemento 0,1
		rotulo = new JLabel();
		rotulo.setIcon(this.pegarIcone(formato[0][1], this.peca.ehPivo(0,1)));
		interno.add(rotulo);		
		// Elemento 1,0
		rotulo = new JLabel();
		rotulo.setIcon(this.pegarIcone(formato[1][0], this.peca.ehPivo(1,0)));
		interno.add(rotulo);
		// Elemento 1,1
		rotulo = new JLabel();
		rotulo.setIcon(this.pegarIcone(formato[1][1], this.peca.ehPivo(1,1)));
		interno.add(rotulo);		
	}	
	
	/**
	 * Define o icone de acordo com o formato e se o elemento é pivo ou não
	 * @param formato Formato do elemento
	 * @param pivo Informa se o elemento é pivo
	 * @return ImageIcon com a imagem relativa ao elemento da peça
	 */
	protected ImageIcon pegarIcone(int formato, boolean pivo){
		String prefix = "";
		ImageIcon icone = null;
		if(pivo){
			prefix = "pivo_";
		}
		switch(formato){
			case Formato.CIRCULO: icone = new ImageIcon(prefix + JogadorView._IMG_CIRCULO); break;
			case Formato.CRUZ: icone = new ImageIcon(prefix + JogadorView._IMG_CRUZ); break;
			case Formato.QUADRADO: icone = new ImageIcon(prefix + JogadorView._IMG_QUADRADO); break;						
		}
		return icone;
	}	
	
	/**
	 * Informa se o formato da peça é em linha
	 * @return
	 */
	protected boolean eLinha(){
		int[][] formato = this.peca.getFormato();
		return (formato[0][0] == Formato.VAZIO && formato[0][1] == Formato.VAZIO) ||
			   (formato[1][0] == Formato.VAZIO && formato[1][1] == Formato.VAZIO);
	}
	
	/**
	 * Informa se o formato da peça é em coluna
	 * @return
	 */
	protected boolean eColuna(){
		int[][] formato = this.peca.getFormato();
		return (formato[0][0] == Formato.VAZIO && formato[1][0] == Formato.VAZIO) ||
			   (formato[0][1] == Formato.VAZIO && formato[1][1] == Formato.VAZIO);		
	}	
	
	/**
	 * Atualizar a imagem (em caso de rotação da peça)
	 */
	public void atualizar(){
		this.interno.removeAll();
		this.inicializar();		
	}

	/**
	 * Informa a cor da peça
	 */
	public Color getCor() {
		return cor;
	}

	/**
	 * Seta a cor da peça
	 * @param cor
	 */
	public void setCor(Color cor) {		
		this.cor = cor;
		this.setBackground(cor);
		this.interno.setBackground(cor);
	}

	/**
	 * Informa a peça que a imagem representa
	 * @return
	 */
	public Peca getPeca() {
		return peca;
	}
	
	



}
