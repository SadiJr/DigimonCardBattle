package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandlerJanela implements ActionListener {
	
	protected Janela janela;
	
	public HandlerJanela(Janela janela){
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("novo")) {
			janela.iniciarPartida();
		} else
		if (e.getActionCommand().equals("interf0")) {
			janela.setInterf(0);
		} else
		if (e.getActionCommand().equals("interf1")) {
				janela.setInterf(1);
			}
		if (e.getActionCommand().equals("sair")) {
			janela.sair();
		}
		if (e.getActionCommand().equals("sobre")){
			janela.abreJanelaSobre();
		}
		if (e.getActionCommand().equals("ajuda")){
			janela.abreJanelaAjuda();
		}
	}
}
