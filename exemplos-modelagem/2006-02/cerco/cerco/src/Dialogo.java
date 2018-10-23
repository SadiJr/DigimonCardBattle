import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



// classe q vai ser usada para pegar as informações dos jogadores

public class Dialogo extends JFrame {


    private String nomeJogador1;
    private String nomeJogador2;
    private boolean daVez1;
    private AtorJogador ator;


       //gerenciador de botoes, subclasse desta
	private GerenciadorBotaum gerenciadorBotao;

	/**
	 * Componentes gráficos
	 */
  //  private Container container = getContentPane();
	private JPanel painel,painelAux;
	private JLabel label;
	private JTextField campoTexto1,campoTexto2;
	private JButton botao;
	// Radio Button
	private ButtonGroup radioGrupo;
	private JRadioButton radio1, radio2;

	/**
    * layout e suas configuracoes
    */
	private GridBagLayout gridBagLayout;
	private GridBagConstraints gbConstrains;


	public Dialogo(AtorJogador pAtor){

        ator = pAtor;
	    gerenciadorBotao = new GerenciadorBotaum();

	}

	public JPanel exibirTelaId(boolean reinicia){


        gridBagLayout = new GridBagLayout(); 		// instancia o gridBagLayout

		gbConstrains = new GridBagConstraints(); 	// instancia o
		// gridBagConstrains, para
		// facilitar o uso do
		// gridBagLayout, é as
		// configuraçoes do
		// gribaglayout

		gbConstrains.insets = new Insets(1, 4, 1, 4); 	// seta distancia em
		// relacao aos extremos
		// de cada celula em 5
		gbConstrains.anchor = gbConstrains.WEST; 	// Seta GridBagConstraints
		// com ânora
		// WEST(oeste,esquerda)

		//seta o layout do painel como gridbag
		painel = new JPanel(gridBagLayout);



		//---------------------------------------------

		// INFORMAÇÕES

        label = new JLabel ("C  e  R  c  O ");
		gbConstrains.gridx = 0;
		gbConstrains.gridy = 0;
		painel.add(label, gbConstrains);


        //----------------------------------------------------

        //SOH GANHA ESPAÇO

        label = new JLabel (" ");
		gbConstrains.gridx = 0;
		gbConstrains.gridy = 1;
		painel.add(label, gbConstrains);

		label = new JLabel (" ");
		gbConstrains.gridx = 0;
		gbConstrains.gridy = 2;
		painel.add(label, gbConstrains);


        //----------------------------------------------------

		label = new JLabel("Jogador 1");

		if(reinicia==false){
            campoTexto1 = new JTextField(30); 	// campoTexto = novo campo de texto
		    campoTexto1.setEditable(true);
		}
		else
		    campoTexto1.setEditable(false); 		// campoTexto nao permite ser editavel


		//configura para jogar objeto no painel em coluna 0, linha 1
		gbConstrains.gridx = 0;
		gbConstrains.gridy = 3;
		painel.add(label, gbConstrains);	// joga label NOME no painel
		//configura para jogar objeto no painel em coluna 0, linha 1
		gbConstrains.gridx = 1;
		gbConstrains.gridy = 3;
		painel.add(campoTexto1, gbConstrains);	//joga campo de texto no painel



        //----------------------------------------------------------


        // soh para da espaço

        label = new JLabel (" ");
		gbConstrains.gridx = 0;
		gbConstrains.gridy = 4;
		painel.add(label, gbConstrains);



		//---------------------------------------------------


		label = new JLabel("Jogador 2");

        if(reinicia==false){
		    campoTexto2 = new JTextField(30); 	// campoTexto = novo campo de texto
		    campoTexto2.setEditable(true);
		}
		else
		    campoTexto2.setEditable(false); 		// campoTexto nao permite ser editavel


		//configura para jogar objeto no painel em coluna 0, linha 1
		gbConstrains.gridx = 0;
		gbConstrains.gridy = 5;
		painel.add(label, gbConstrains);	// joga label NOME no painel
		//configura para jogar objeto no painel em coluna 0, linha 1
		gbConstrains.gridx = 1;
		gbConstrains.gridy = 5;
		painel.add(campoTexto2, gbConstrains);	//joga campo de texto no painel


         //-------------------------------------------

        label = new JLabel (" ");
		gbConstrains.gridx = 0;
		gbConstrains.gridy = 6;
		painel.add(label, gbConstrains);


        //----------------------------------------------------


	    label = new JLabel("Começa jogando");
		gbConstrains.gridx = 0;
		gbConstrains.gridy = 7; 		// configura para jogar na coluna 0, linha 6
		painel.add(label, gbConstrains);

		// faz os radioButton e joga em um painel auxiliar
		painelAux = new JPanel();		// limpa o que pode existir no painelAuxiliar
		painelAux.add(radio1 = new JRadioButton("Jogador 1", false));
		painelAux.add(radio2 = new JRadioButton("Jogador 2", false));
		// Faz a logica dos botoes radio (quando um esta clicado o outro nao)
		radioGrupo = new ButtonGroup();
		radioGrupo.add(radio1);
		radioGrupo.add(radio2);
		// joga o painel auxiliar para o painel
		gbConstrains.gridx = 1;
		gbConstrains.gridy = 7; 		// configura para jogar na coluna 1, linha 6
		painel.add(painelAux, gbConstrains);


		//----------------------------------------

		botao = new JButton("Ok"); 	// cria o botao "Salvar"
		botao.setEnabled(true);
		botao.setActionCommand("1");
		botao.addActionListener(gerenciadorBotao);
		// joga o painel auxiliar para o painel
		gbConstrains.gridx = 3;
		gbConstrains.gridy = 8; 		// configura para jogar na coluna 1, linha 6
		painel.add(botao, gbConstrains);

        return painel;


	}


	public void capturaDados(){


	    nomeJogador1 = campoTexto1.getText();
	    nomeJogador1 = nomeJogador1.trim();
	    if(nomeJogador1.equals("")){
	        campoTexto1.setText("jogador1");
	        nomeJogador1= "jogador1";
	    }
	    nomeJogador2 = campoTexto2.getText();
        nomeJogador2 = nomeJogador2.trim();
	    if(nomeJogador2.equals("")){
	        campoTexto2.setText("jogador2");
	        nomeJogador2= "jogador2";
	    }

        if(radio2.isSelected())
				daVez1 = false;
		else
		    daVez1 = true;

        ator.organiza(nomeJogador1,nomeJogador2,daVez1);

	}




    private class GerenciadorBotaum implements ActionListener {

		/**
		 * Metodo que joga para o controlador o evento tomado
		 */
		public void actionPerformed(ActionEvent event) {
			try {
				int comando = Integer.parseInt(event.getActionCommand());
				if(comando == 1)
				    capturaDados();
			} catch (Exception E) {
				JOptionPane.showMessageDialog(null, E.getMessage());
			}
		}
	}

}