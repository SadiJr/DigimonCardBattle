package DaNumba;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Classe que implementa a tela de menu.
 * @author Leonardo
 */
class TelaMenu extends Container implements ActionListener{
	
	private ControlePrincipal objControlePrincipal;
        
        private ImageButton botaoNovoJogo;
	private ImageButton botaoInstrucoes;
	private ImageButton botaoCreditos;
        private ImageButton botaoSair;
	private JPanel painel;
	
	/**
	 * Método construtor da classe TelaMenu.
	 */
	public TelaMenu(ControlePrincipal pObjControlePrincipal){
		
            this.objControlePrincipal = pObjControlePrincipal;
            this.setBackground(Color.WHITE);
            painel = new JPanel();
            //painel.paint(imgFundo);
            painel.setLayout(null);
            painel.setBounds(0,0,400,400);
            this.add(painel);
            
            //Botão Novo Jogo
            Icon imgNovoJogoNormal = new ImageIcon("./imagens/png/botaoJogarRoxoClaro.png");
            Icon imgNovoJogoMouse = new ImageIcon("./imagens/png/botaoJogarRoxo.png");
            botaoNovoJogo = new ImageButton();
            botaoNovoJogo.setButtonImage(imgNovoJogoNormal);
            botaoNovoJogo.setFocusImage(imgNovoJogoMouse);
            botaoNovoJogo.setPressedImage(imgNovoJogoMouse);
            botaoNovoJogo.setBounds(118,125,165,74);
            botaoNovoJogo.addActionListener(this);
            painel.add(botaoNovoJogo);
	    
            //Botão Instrucoes
	    Icon imgInstrucoesNormal = new ImageIcon("imagens/png/botaoInstrucoesRoxoClaro.png");
            Icon imgInstrucoesMouse = new ImageIcon("imagens/png/botaoInstrucoesRoxo.png");
            botaoInstrucoes = new ImageButton();
            botaoInstrucoes.setButtonImage(imgInstrucoesNormal);
            botaoInstrucoes.setFocusImage(imgInstrucoesMouse);
            botaoInstrucoes.setPressedImage(imgInstrucoesMouse);
            botaoInstrucoes.setBounds(118,185,165,74);
            botaoInstrucoes.addActionListener(this);
	    painel.add(botaoInstrucoes);		    

            //Botão Créditos
	    Icon imgCreditosNormal = new ImageIcon("imagens/png/botaoCreditosRoxoClaro.png");
	    Icon imgCreditosMouse = new ImageIcon("imagens/png/botaoCreditosRoxo.png");
            botaoCreditos = new ImageButton();
            botaoCreditos.setButtonImage(imgCreditosNormal);
            botaoCreditos.setFocusImage(imgCreditosMouse);
            botaoCreditos.setPressedImage(imgCreditosMouse);
            botaoCreditos.setBounds(118,245,165,74);
            botaoCreditos.addActionListener(this);
	    painel.add(botaoCreditos);
	    
            //Botão Sair
	    Icon imgSairNormal = new ImageIcon("imagens/png/botaoSairRoxoClaro.png");
            Icon imgSairMouse = new ImageIcon("imagens/png/botaoSairRoxo.png");
            botaoSair = new ImageButton();
            botaoSair.setButtonImage(imgSairNormal);
            botaoSair.setFocusImage(imgSairMouse);
            botaoSair.setPressedImage(imgSairMouse);
            botaoSair.setBounds(118,305,165,74);
            botaoSair.addActionListener(this);
	    painel.add(botaoSair);
            
            //Imagem do plano de fundo
            Icon imgPlanoFundo = new ImageIcon("imagens/gif/screen.gif");
            JLabel imagemDeFundo = new JLabel();
            imagemDeFundo.setIcon(imgPlanoFundo);
            imagemDeFundo.setBounds(new java.awt.Rectangle(0,0,400,400));
            painel.add(imagemDeFundo);
	}
	
	/**
	 * Slot que associa os eventos dos botões aos métodos.
	 */
	public void actionPerformed( ActionEvent evento ) {

		Object origem = evento.getSource();
		
		if (origem == botaoNovoJogo){
                    this.clickNovoJogo();
                }
		if (origem == botaoInstrucoes){
                    this.clickMostrarRanking();
                }
                if (origem == botaoCreditos){
                    this.clickMostrarCreditos();
                }
	        if (origem == botaoSair){
                    clickSair();
                }
	}

	/**
	 * Método executado ao clicar no botão Novo jogo.
	 */
	private void clickNovoJogo() {
            try {
                objControlePrincipal.novoJogo();
            } catch (Exception exc){
                JOptionPane.showMessageDialog(null,"Problema ao iniciar novo jogo","Erro",1);
            }
	}
        
	/**
	 * Método executado ao clicar no botão Ranking.
	 */
	private void clickMostrarRanking() {
            try {
                objControlePrincipal.mostrarRanking();
            } catch (Exception exc){
                JOptionPane.showMessageDialog(null,"Problema ao mostrar o ranking","Erro",1);
            }
	}        
        
        /**
         * Método executa ao clicar o botão Créditos.
         */
        private void clickMostrarCreditos(){
            try {
                objControlePrincipal.mostrarCreditos();
            } catch (Exception exc){
                JOptionPane.showMessageDialog(null,"Problema ao mostrar os créditos","Erro",1);
            }
        }
        
        /**
	 * Método executado ao clicar no botão Sair.
	 */
	private void clickSair() {
            try {
                objControlePrincipal.sairDoPrograma();
            } catch (Exception exc){
                JOptionPane.showMessageDialog(null,"Problema ao sair do jogo.\nTente amanhã.","Erro",1);
            }
	    
	}
}
