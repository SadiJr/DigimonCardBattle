import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



/**
 * classe onde fica a interface basicamente
 */

public class AtorJogador extends JFrame {

    //gerenciador de botoes, subclasse desta
	private GerenciadorBotao gerenciadorBotao;

	/**
	 * Componentes gráficos
	 */
	private JMenuBar barraMenu;
	private JMenu menu;
	private JMenuItem menuItem, menuReinicia;
    private Container container = getContentPane();
	private JPanel painel, painelAux, centro;
	private JButton botao [][];
	private JLabel labelVez;
	private JLabel labelObs;

	/**
    * layout e suas configuracoes
    */
	private GridBagLayout gridBagLayout;
	private GridBagConstraints gbConstrains;


	// Tabuleiro
	private Tabuleiro tabuleiro;
	// faz dialogo com jogador
	private Dialogo dialogo;
	// usado para soh criar o tabuleiro uma vez ;)
    private boolean temTabuleiro = false;

	private String comPecinha = "comPecinha.JPG";
	private String semPecinha = "semPecinha.JPG";
	private String inicio = "inicio.JPG";
	private String inicioJogador1 = "inicioJogador1.JPG";
	private String inicioJogador2 = "inicioJogador2.JPG";


	public AtorJogador() {

		super("C   e   R   c   O"); // Titulo da janela

		// Instancia gerenciador de eventos
		gerenciadorBotao = new GerenciadorBotao();

		/**
		 * Inicializa o menu com as configuracoes feitas
		 */
		inicializarMenu();

		centro = new JPanel();
		dialogo = new Dialogo(this);

		/**
		 * Seta operacao default para fechar
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * Seta tamanhos e visibilidade
		 */

		setSize(605, 470);
		setResizable(false); // naum deixa a tela c mecher

		setLocationRelativeTo(null);    // joga para centro da tela
		setVisible(true);

	}

	public void inicializarMenu(){


        //inicializa a barra de menu

	    barraMenu = new JMenuBar();

		// Menu Arquivo
		menu = new JMenu("JOGO");
		menu.setMnemonic('j');

		menuItem = new JMenuItem("Novo");
		menuItem.setActionCommand("48");
		menuItem.addActionListener(gerenciadorBotao);
		menu.add(menuItem);

		menuReinicia = new JMenuItem("Reiniciar");
		// criar um menu item soh para o botaum reiniciar para pode manipular ele depois ;)
        menuReinicia.setEnabled(false);
		menuReinicia.setActionCommand("49");
		menuReinicia.addActionListener(gerenciadorBotao);
		menu.add(menuReinicia);

		menuItem = new JMenuItem("Sair");
		menuItem.setActionCommand("50");
		menuItem.addActionListener(gerenciadorBotao);
		menu.add(menuItem);

		barraMenu.add(menu);

		setJMenuBar(barraMenu);

		//obs o comand começa com 48, pq os outros taum sendo usados pelos
		//botoes q iraum manipular o jogo ;)

	}

	// mostra o conteudo da tela
    public void exibirConteudo(JPanel conteudo) {
		if (conteudo != null) {
			container.remove(centro);
			centro = conteudo;
			container.add(centro);
			setVisible(true);
		}
	}

	public void inicializarInterface(){


		gridBagLayout = new GridBagLayout(); 		// instancia o gridBagLayout
		gbConstrains = new GridBagConstraints(); 	// instancia o gbConstrains
		gbConstrains.insets = new Insets(1, 4, 1, 4); 	// seta distancia em

		gbConstrains.anchor = gbConstrains.WEST; 	// Seta GridBagConstraints

		//seta o layout do painel como gridbag
		painel = new JPanel(gridBagLayout);
		painelAux = new JPanel(gridBagLayout);

		//instaceia os botoes
		//48 das posições
		botao = new JButton [6][8];

		//-----------------------------------------

		// adiciona os testos na tela
		labelVez = new JLabel ("Jogador Vez: ");
		gbConstrains.gridx = 0;
		gbConstrains.gridy = 0;
		painel.add(labelVez, gbConstrains);

		labelObs = new JLabel ("Obs: ");
		gbConstrains.gridx = 0;
		gbConstrains.gridy = 1;
		painel.add(labelObs, gbConstrains);


		//---------------------------------------

		/**
		 * COMECA A ADICIONAR OS ELEMENTOS NO PAINEL
		 */
		// BOTOES

		// vai c usado pra ajuda a arrumar as coisas na tela, sempre faz ir uma linha para baixo
		int aux=2;
        //usado como uma especie de contador, vai ser mandado pra saber qual botaum foi pressionado
        int auxComand=0;
        String comand;

        for(int i=0; i<6; i++){

            for(int j=0; j<8; j++){

	    	        //novo
	     	        botao[i][j] = new JButton ();
	      	        //seta a borda, pra um modelo q dae soh aparece o q tem na figura ;)
	       	        botao[i][j].setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));

	                //adiciona uma figura, vai ser atravez dessa manipulação q vamus movimentar as peças e tals;)
                    if(i==2 && j==0)
                             botao[i][j].setIcon(new javax.swing.ImageIcon(inicioJogador1));
                    else if(i==3 && j==7)
                         botao[i][j].setIcon(new javax.swing.ImageIcon(inicioJogador2));
                    else
	                         botao[i][j].setIcon(new javax.swing.ImageIcon(comPecinha));
	                //coloca o botaum q foi presionado pra dentro da string
                    comand=""+auxComand;
                    //adiciona 1
	                auxComand++;
                    botao[i][j].setActionCommand(comand);
	                botao[i][j].addActionListener(gerenciadorBotao);
		            painelAux.add(botao[i][j]);

            }

		    gbConstrains.gridx = 0;
		    gbConstrains.gridy = aux++;
		    painel.add(painelAux,gbConstrains);
		    painelAux = new JPanel(gridBagLayout);

		}

        menuReinicia.setEnabled(true);

        exibirConteudo(painel);


	}

	public void tiraJogador(int linha, int coluna){

	      if((linha==2 && coluna==0) || (linha==3 && coluna==7)){
                //bota a imagem do inicio
                botao[linha][coluna].setIcon(new javax.swing.ImageIcon(inicio));
          }
          else{
                //volta a imagem q o peao tava ao normal
                botao[linha][coluna].setIcon(new javax.swing.ImageIcon(comPecinha));
         }

	}

	public void tiraPecinha (int linha, int coluna){

	       botao[linha][coluna].setIcon(new javax.swing.ImageIcon(semPecinha));

	}

	public void alocaJogador(int linha, int coluna, String imagem){

	       botao[linha][coluna].setIcon(new javax.swing.ImageIcon(imagem));

	}

	public void exibeMsgObs(String msg){

	       labelObs.setText("Obs: "+ msg);

	}

	public void exigeMsgJogador(String msg){

        labelVez.setText("Jogador Vez: "+ msg);

	}

	public void reinicia(){


          boolean temPartida = tabuleiro.temPartidaAndamento();
          boolean confirma = false;
          if(temPartida){
              confirma = confirmaInterrupcao();
              if(confirma){
                    tabuleiro.finalizaPartida();
                    obterId(true);
                 }
              }
              else
                 obterId(true);
	}

	public void novo(){

           if(temTabuleiro==false){
                tabuleiro = new Tabuleiro(this);
                temTabuleiro=true;
                obterId(false);
           }
           else{
              boolean temPartida = tabuleiro.temPartidaAndamento();
              boolean confirma = false;
              if(temPartida){
                 confirma = confirmaInterrupcao();
                 if(confirma){
                    tabuleiro.finalizaPartida();
                    obterId(false);
                 }
              }
              else
                 obterId(false);
           }

	}

    // obtem as identificações necessarias, como o nome dos jogadores e quem começa
	public void obterId(boolean reinicia){

        exibirConteudo(dialogo.exibirTelaId(reinicia));

	}

	public void organiza(String nome1, String nome2, boolean daVez1){

           inicializarInterface();
	       tabuleiro.organiza(nome1,nome2,daVez1);

	}

	public boolean confirmaInterrupcao(){

        int continua=0;

		continua = JOptionPane.showConfirmDialog(null,"voce tem certeza disto??","CeRcO",JOptionPane.YES_NO_OPTION);

        //se continuar == 0 eh q ele confirma, se for == 1 naum confirma
        if(continua==0)
            return true;
        else
            return false;

	}



		/**
	 * Inner Class usada para gereciar os eventos de botao
	 *
	 */
	private class GerenciadorBotao implements ActionListener {

		/**
		 * Metodo que joga para o controlador o evento tomado
		 */
		public void actionPerformed(ActionEvent event) {
			try {
				int comando = Integer.parseInt(event.getActionCommand());
				int linha ;
                int coluna;
				// v ainda pra onde q vo manda isso,por enqto ta aqui soh pra teste
				if(comando>-1 && comando<48){

				    if(comando==0){
				        linha = 0;
                        coluna = 0;
                    }
                    else{
                        linha = (int)(comando)/8;
                        coluna = (int)(comando)%8;
                    }

                    //faz a jogada
                    tabuleiro.joga(linha,coluna);

                }

				else{

                    switch(comando){
				      case 48:
				         novo();
                         break;
				      case 49:
                         reinicia();
                         break;
				      case 50:
			             System.exit(0);
			             break;
			       }
			    }

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"AtorJogador - Operação mal feita!",JOptionPane.ERROR_MESSAGE);
			}
		}
	}


}