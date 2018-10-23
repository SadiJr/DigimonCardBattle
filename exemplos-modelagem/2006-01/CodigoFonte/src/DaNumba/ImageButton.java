package DaNumba;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * Classe que implementa o tratamento dos botões da tela menu
 * @author DaNumba
 */

public class ImageButton extends JButton implements FocusListener, MouseListener {

	private Icon buttonImage;
	private Icon focusImage;
	private Icon pressedImage;

	private boolean isMouseInside;    // mouse sobre o botao
	private boolean isMousePressed;   // mouse pressionando o botao
	private boolean isFocused;        // foco no botao

	
	public void atualizaBotao(){
	    if (isMousePressed){
	        if (pressedImage != null){
		    this.setIcon(pressedImage);
		} else {
                    if (focusImage != null){
			this.setIcon(focusImage);	
	            } else {
			this.setIcon(buttonImage);
		    }
		    this.setIcon(buttonImage);
                }
		} else {
		    if (isFocused || isMouseInside){
		        if (focusImage != null){
			    this.setIcon(focusImage);	
			} else {
			    this.setIcon(buttonImage);
			}
			} else {
			    this.setIcon(buttonImage);
			}
		}
	}
	
	public void setButtonImage(Icon icon){
		buttonImage = icon; 
		atualizaBotao();
	}

	public void setPressedImage(Icon icon){
		pressedImage = icon;
		atualizaBotao();
	}

	public void setFocusImage(Icon icon){
		focusImage = icon;
		atualizaBotao();
	}
        
	public ImageButton(){
		super();
		this.isMouseInside=false;
		this.isMousePressed=false;
		this.isFocused= false;
		this.setBorderPainted(false);
		//this.setFocusPainted(false);
		this.setOpaque(false);
		this.addMouseListener(this);
		this.addFocusListener(this);
		//this.setFocusable(false);
		atualizaBotao();
	}
	
	public void mouseClicked(MouseEvent arg0) {
		this.isMouseInside = false;
		this.isMousePressed = false;
		atualizaBotao();
	}

	public void mouseEntered(MouseEvent arg0) {
		isMouseInside = true;
		atualizaBotao();
	}

	public void mouseExited(MouseEvent arg0) {
		isMouseInside = false;
		atualizaBotao();
	}

	public void mousePressed(MouseEvent arg0) {	
		isMousePressed = true;
		atualizaBotao();
	}

	public void mouseReleased(MouseEvent arg0) {
		isMousePressed = false;
		atualizaBotao();
	}

	public void focusGained(FocusEvent arg0) {
		isFocused = true;
		//isMousePressed = false;
		atualizaBotao();
	}
	
	public void focusLost(FocusEvent arg0) {
		isFocused = false;
		isMousePressed = false;
		isMouseInside = false;
		atualizaBotao();
	}	
}