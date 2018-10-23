import javax.swing.*;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.ActionListener;


public class ImagemDePosicao extends JButton {
	
	protected static final long serialVersionUID = -7315520693630929612L;
	protected Posicao posicao;
	
	public ImagemDePosicao(Posicao posicao, ActionListener actionListener){
		this.addActionListener(actionListener);
		this.setActionCommand(JogadorView._SELECIONAR_POSICAO);
		this.posicao = posicao;
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		this.atualiza();
	}
	
	/**
	 * Atualiza a cor da posicao se acordo com a cor do 
	 * ocupante. Se o ocupante for null coloca a cor padrão
	 */
	public void atualiza(){
		Color c = Color.BLUE;
		Peca ocupante = this.posicao.getOcupante();
		if(ocupante != null){
			c = ocupante.getCor();
		}
		this.setBackground(c);
		int formato = this.posicao.getFormato();
		switch(formato){
			case Formato.CIRCULO: this.setIcon(new ImageIcon(JogadorView._IMG_CIRCULO)); break;
			case Formato.CRUZ: this.setIcon(new ImageIcon(JogadorView._IMG_CRUZ)); break;
			case Formato.QUADRADO: this.setIcon(new ImageIcon(JogadorView._IMG_QUADRADO)); break;
			case Formato.VAZIO: this.setIcon(null); break;
		}
	}

	/**
	 * Informa a posição que o objeto representa
	 * @return Posicao
	 */
	public Posicao getPosicao() {
		return posicao;
	}
	

}
