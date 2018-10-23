package gui;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JPanel;

import app.ControladorTabuleiro;

public class AtorJogador extends JPanel {

	protected static final long serialVersionUID = 1L;
	protected JButton casas[];
	protected JButton repositorios[];
	protected JPanel pnBotoesTabuleiro;
	protected ControladorTabuleiro controle;
	protected HandlerTabuleiro handler;
	protected Label jog;
	
	public AtorJogador (){
		controle = new ControladorTabuleiro();
		this.handler = new HandlerTabuleiro(this,controle);
		this.prepararTabuleiro();
	}

	
	public void prepararTabuleiro(){
		this.setSize(800, 300);	
		this.repositorios = new JButton[2];
		this.repositorios[0] = new JButton();
		this.repositorios[1] = new JButton();
		this.casas = new JButton[12];
		this.controle.prepararTabuleiro();
		
		this.setLayout(new BorderLayout());		
		this.add(repositorios[0], BorderLayout.EAST);
		this.add(repositorios[1], BorderLayout.WEST);
		repositorios[0].setEnabled(false);
		repositorios[1].setEnabled(false);
				
		this.jog = new Label();	
		this.add(jog, BorderLayout.SOUTH);
		
		JPanel botoes = new JPanel();
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(2);
		gridLayout.setColumns(6);
		botoes.setLayout(gridLayout);
		for(Integer i =11; i>5; i--){
			casas[i] = new JButton();
			casas[i].setActionCommand(i.toString());
			casas[i].setEnabled(false);
			casas[i].addActionListener(handler);
			botoes.add(casas[i], null);
			}
		for(Integer i =0; i<6; i++){
			casas[i] = new JButton();
			casas[i].setActionCommand(i.toString());
			casas[i].setEnabled(false);
			casas[i].addActionListener(handler);
			botoes.add(casas[i], null);
		}
		this.add(botoes, BorderLayout.CENTER);
	}
	
	public void tratarJogada(int[] estadoAtual){
				
		if(controle.getInterface() == 1 && controle.getJogador(controle.getJogadorDaVez())=="B"){
			while(controle.getJogador(controle.getJogadorDaVez())=="B" && controle.getpartidaEmAndamento()==true){
				controle.pedirJogada(controle.getJogadorDaVez());
				estadoAtual = controle.getEstadoAtual();
			}
		}
		this.atualizaCasas(estadoAtual);
		
		if(controle.getpartidaEmAndamento()==false){
			jog.setText("vencedor = " + controle.getJogador(controle.getVencedor()));
			this.desabilitarBotoes();
		}
	}

	
	public void atualizaCasas(int[] estadoAtual){
		jog.setText("Vez do jogador "+controle.getJogador(controle.getJogadorDaVez()));
		this.habilitarBotoes();
		String mensagem;
		
		for(int i =0; i<2; i++){
			mensagem = new Integer(controle.getPedrasRepositorio(i)).toString();
			repositorios[i].setText(mensagem);
		}
		for(int i=0;i<12;i++){
			mensagem = new Integer(controle.getPedrasCasa(i)).toString();
			casas[i].setText(mensagem);
		}
	}
		
	public void setInterface (int interf){
			controle.setInterf(interf);
		}
	
	public void iniciarPartida(){	
		controle.iniciarPartida();
		this.atualizaCasas(controle.getEstadoAtual());
	}
	
	public void habilitarBotoes(){
		boolean jog0 = false;
		
		if(this.controle.getJogador(controle.getJogadorDaVez())=="A")
			jog0 = true;
			
			for(int i = 0; i <= 5; i ++)
				if(!(controle.getPedrasCasa(i) == 0))
					casas[i].setEnabled(jog0);
				else
					casas[i].setEnabled(false);
			for(int i = 6; i <= 11; i ++)
				if(!(controle.getPedrasCasa(i) == 0))
					casas[i].setEnabled(!(jog0));
				else
					casas[i].setEnabled(false);
		}
	
	public void desabilitarBotoes(){
		for(int i=0; i<12;i++)
			casas[i].setEnabled(false);
	}
	
	public boolean getpartidaEmAndamento(){
		return controle.getpartidaEmAndamento();
	}
}
