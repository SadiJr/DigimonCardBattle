import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class JogadorView extends JFrame{
 
	protected static final long serialVersionUID = -6537859243532879912L;
	protected Tabuleiro tabuleiro;	 
	//Atributos relativos a implementação da interface
	protected int codigoQuebraCabecaSel;
	protected Container container = getContentPane();
	protected JComponent centro;
	protected JLabel status;
	protected JMenuBar barraMenu;
	protected JMenu menu;
	protected JMenuItem menuItem;
	protected ActionHandler actionHandler;
	protected JPanel jogo; //Jogo: conjunto tabuleiro + peças disponíveis
	protected JPanel tab; //Tabuleiro
	protected JPanel disp; //Peças disponíveis
	protected ImagemDePeca pecaSelecionada;
	protected Vector<ImagemDePeca> pecas_disponiveis;
	protected Vector<ImagemDePeca> pecas_posicionadas;
	//Constantes
	public final static String _JOGO_TITULO = "Cilada";
	public final static String _JOGO_VERSAO = "1.0";
	protected final static String _SELECIONAR_JOGADOR = "_SELECIONAR_JOGADOR";
	protected final static String _CRIAR_JOGADOR = "_CRIAR_JOGADOR";
	protected final static String _FECHAR = "_FECHAR";
	protected final static String _SELECIONAR_TABULEIRO = "_SELECIONAR_TABULEIRO";
	protected final static String _REINICIAR_PARTIDA = "_REINICIAR_PARTIDA";
	protected final static String _SELECIONAR_QUEBRA_CABECA = "_SELECIONAR_QUEBRA_CABECA";	
	public static final String _SELECIONAR_PECA = "_SELECIONAR_PECA";
	public static final String _SELECIONAR_POSICAO = "_SELECIONAR_POSICAO";
	public static final String _ENCERRAR = "_ENCERRAR";
	public static final String _RANKING = "_RANKING";
	public final static String _IMG_QUADRADO = "quadrado.png";
	public final static String _IMG_CIRCULO = "circulo.png";
	public final static String _IMG_CRUZ = "cruz.png";
	protected final static String _IMG_TABULEIRO_ABERTO = "tabuleiro.png";
	protected final static String _IMG_TABULEIRO_FINALIZADO = "tabuleiro_finalizado.png";
	protected final static String _IMG_FIM = "fim.png";
		
	public JogadorView() throws Exception{
		actionHandler = new ActionHandler();
		container.setLayout(new BorderLayout(2,2));		
		centro = new JPanel(new FlowLayout());		
		container.add(centro,BorderLayout.CENTER);		
		tabuleiro = new Tabuleiro();
		this.setStatus("Selecione ou crie um jogador");
		this.inicializarMenus();
		//this.setResizable(false);
		this.setTitulo(null);
		this.setSize(new Dimension(800,600));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(		
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						sair();
					}					
				}				
		);
		
		
	}
	
	/* MÉTODOS RELATIVOS A IMPLEMENTAÇÃO DA INTERFACE */
	
	protected void setTitulo(String str){
		//Adiciona o nome do jogo e a versão
		String titulo = _JOGO_TITULO + " " + _JOGO_VERSAO;
		//Se houver jogador selecionada coloca o nome
		if(this.tabuleiro.informarJogadorAtual() != null){
			titulo += " - " + this.tabuleiro.informarJogadorAtual().getNome(); 
		}
		//Adiciona a string do parametro
		if(str != null){
			titulo += " - " + str;
		}
		this.setTitle(titulo);
	}
	
	/**
	 * Definido o quebra-cabeças que o jogador irá
	 * jogar seta a cor de cada peça disponível neste
	 */
	protected void setarCoresDasPecas(){
		//Incia o array com as cores
		Color[] cores_pecas = {	new Color(0xCC,0xCC,0xCC),		 
								new Color(0xCC,0xCC,0xFF),
								new Color(0xCC,0xFF,0xCC),
								new Color(0xCC,0xFF,0xFF),
								new Color(0x99,0xCC,0xCC),		 
								new Color(0x99,0xCC,0xFF),
								new Color(0x99,0xFF,0xCC),
								new Color(0x99,0xFF,0xFF),
								new Color(0xFF,0xCC,0xCC),
								new Color(0xFF,0xFF,0xCC),
								new Color(0xFF,0xCC,0xFF),
								new Color(0x99,0x99,0xCC),		 
								new Color(0x99,0xCC,0x99),
								new Color(0x99,0xCC,0x99),
								new Color(0x99,0x99,0xFF)};
		try{
			ImagemDePeca aux;
			int numDisp = this.pecas_disponiveis.size();
			for(int i = 0; i < numDisp; i++){
				aux = this.pecas_disponiveis.elementAt(i); 
				aux.setCor(cores_pecas[i]);
				aux.getPeca().setCor(cores_pecas[i]);
			}
		} catch(Exception e){
			this.exibirErro(e.getMessage());
		}
	}
	
	protected void setStatus(String texto){
		if(status == null){
			status = new JLabel();
			status.setForeground(Color.BLUE);
			status.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Status"));
			container.add(status,BorderLayout.SOUTH);	
		}
		status.setText(texto);
	}
	
	protected void exibirErro(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	}
	
	protected void exibirInf(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem, "Ok", JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected boolean solicitarConf(String mensagem){
		boolean opcao = false;
		if(JOptionPane.showConfirmDialog(this,mensagem, "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
			opcao = true;
		}
		return opcao;
	}
	
	
	/**
	 * Salva a lista de jogadores e encerra o jogo
	 */
	protected void sair(){
		if(solicitarConf("Fechar jogo?")){
			try{
				this.tabuleiro.salvarListaJogadores();
			} catch(Exception e){
				this.exibirErro(e.getMessage());
			}
			System.exit(0);
		}
	}
	
	protected void inicializarMenus(){
		if(barraMenu != null){
			barraMenu.removeAll();
			this.repaint();
		} else {
			barraMenu = new JMenuBar();
		}
		//Menu arquivo
		menu = new JMenu("Arquivo");
		menu.setMnemonic('A');
		
			menuItem = new JMenuItem("Selecionar jogador");
			menuItem.setActionCommand(_SELECIONAR_JOGADOR);
			menuItem.addActionListener(this.actionHandler);
			menuItem.setMnemonic('S');
			menu.add(menuItem);
			
			menuItem = new JMenuItem("Criar jogador");
			menuItem.setActionCommand(_CRIAR_JOGADOR);
			menuItem.addActionListener(this.actionHandler);
			menuItem.setMnemonic('C');
			menu.add(menuItem);
			
			menuItem = new JMenuItem("Ranking");
			menuItem.setActionCommand(_RANKING);
			menuItem.addActionListener(this.actionHandler);
			menuItem.setMnemonic('R');
			menu.add(menuItem);
			
			menuItem = new JMenuItem("Fechar");
			menuItem.setActionCommand(_FECHAR);
			menuItem.addActionListener(this.actionHandler);
			menuItem.setMnemonic('F');
			menu.add(menuItem);
			
		barraMenu.add(menu);
		
		//Menu do jogador
		if(this.tabuleiro.informarJogadorAtual() != null){
			//Menu jogo
			menu = new JMenu("Jogo");
			menu.setMnemonic('J');
			
				menuItem = new JMenuItem("Reiniciar partida");
				menuItem.setActionCommand(_REINICIAR_PARTIDA);
				menuItem.addActionListener(this.actionHandler);
				menuItem.setMnemonic('R');
				menu.add(menuItem);
			
				menuItem = new JMenuItem("Selecionar outro quebra-cabeça");
				menuItem.setActionCommand(_SELECIONAR_QUEBRA_CABECA);
				menuItem.addActionListener(this.actionHandler);
				menuItem.setMnemonic('C');
				menu.add(menuItem);				
				
				menuItem = new JMenuItem("Encerrar");
				menuItem.setActionCommand(JogadorView._ENCERRAR);
				menuItem.addActionListener(this.actionHandler);
				menuItem.setMnemonic('F');
				menu.add(menuItem);
				
			barraMenu.add(menu);			
		}		
		this.setJMenuBar(barraMenu);		
	}
	
	protected void exibirConteudo(JComponent conteudo){
		container.remove(centro);
		centro = conteudo;
		container.add(centro,BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	/* MÉTODOS DO JOGO */
	 
	public void criarJogador() {
		String nome = JOptionPane.showInputDialog(this, "Informe seu nome", "Novo jogador", JOptionPane.QUESTION_MESSAGE);
		if(nome != null){			
			try{
				this.tabuleiro.criarJogador(nome);
				this.exibirInf("Jogador " + nome + " criado");
				this.tabuleiro.setarJogadorAtual(nome);				
				this.selecionarQuebraCabeca();
				this.setTitulo(nome);
				this.inicializarMenus();
			}catch(Exception e){
				this.exibirErro(e.getMessage());
				this.criarJogador();
			}
		}
	}
	 
	public void selecionarJogador() {
		int selIndex;
		String selecao;
		try{
			Vector<Jogador> jogadores = tabuleiro.informarJogadores();
			int numJogadores = jogadores.size();
			if(numJogadores == 0){		
				criarJogador();
			} else {
				Vector<String> nomes_jogadores = new Vector<String>();
				nomes_jogadores.add(0, "Criar um novo jogador");
				for(int i = 0;i < numJogadores; i++){
					nomes_jogadores.add(jogadores.elementAt(i).getNome());
				}
				JComboBox combo_jogadores = new JComboBox(nomes_jogadores);
				JOptionPane.showMessageDialog(this, combo_jogadores, "Selecione o jogador", JOptionPane.QUESTION_MESSAGE);
				selecao = (String)combo_jogadores.getSelectedItem();
				selIndex = combo_jogadores.getSelectedIndex();
				if(selIndex == 0){
					criarJogador();
				} else {
					tabuleiro.setarJogadorAtual(selecao);
					//Exibe os quebra-cabeças do jogador para seleção
					this.selecionarQuebraCabeca();
					//Atualiza o título do jogo
					this.setTitulo(null);
					//Atualiza os menus
					this.inicializarMenus();
				}				
			}
		}catch(Exception e){
			this.exibirErro(e.getMessage());
		}		
	}
	 
	public void selecionarQuebraCabeca() {
		String rotulo;
		JButton botao;
		JPanel painel = new JPanel(new GridLayout(10,5,0,0));
		QuebraCabeca[] quebraCabecas = tabuleiro.informeQuebraCabecas();		
		for(int i = 0; i < quebraCabecas.length; i++){
			rotulo = quebraCabecas[i].numTentativas() + " tentativas";					 
			botao = new JButton(rotulo);
			if(quebraCabecas[i].getFinalizado()){
				botao.setIcon(new ImageIcon(_IMG_TABULEIRO_FINALIZADO));
			} else {
				botao.setIcon(new ImageIcon(_IMG_TABULEIRO_ABERTO));
			}		
			botao.setBackground(Color.BLUE);
			botao.setForeground(Color.WHITE);
			botao.setActionCommand(_SELECIONAR_TABULEIRO);			
			botao.addActionListener(actionHandler);
			botao.setToolTipText(""+quebraCabecas[i].getCodigo());
			painel.add(botao);			
		}
		this.setTitulo(null);
		this.setStatus("Selecione um quebra-cabeças");
		this.exibirConteudo(painel);		
	}
	 
	public void reiniciarPartida() {
		this.setStatus("Partida reiniciada");
		setarQuebraCabeca(codigoQuebraCabecaSel);
	}
	
	protected void setarQuebraCabeca(int codigo){
		try{
			codigoQuebraCabecaSel = codigo;
			QuebraCabeca[] quebraCabecas = tabuleiro.informeQuebraCabecas();
			tabuleiro.setarQuebraCabeca(quebraCabecas[codigo]);
			//Limpar o tabuleiro
			this.tabuleiro.limparPosicoes();
			//Criar as imagens relativas as pecas do tabuleiro
			this.pecas_posicionadas = new Vector<ImagemDePeca>();
			this.pecas_disponiveis = new Vector<ImagemDePeca>();
			//Colocar os dados no vector de peças disponiveis
			Peca[] array_pecas_disponiveis = this.tabuleiro.informarPecas();
			for(int i = 0; i < array_pecas_disponiveis.length; i++){
				this.pecas_disponiveis.add(new ImagemDePeca(array_pecas_disponiveis[i],this.actionHandler));
			}			
			//Setar as cores das peças do tabuleiro selecionado
			setarCoresDasPecas();
			this.inicializarTabuleiro();
			this.atualizaTabuleiro();
			this.setTitulo("Quebra-cabeça nº " + (this.codigoQuebraCabecaSel +1));
		} catch (Exception e){
			exibirErro(e.getMessage());
		}
	}
	
	protected void inicializarTabuleiro(){		
		//Painel mais externo
		jogo = new JPanel(new GridLayout(2,1,5,5));
		//Painel do tabuleiro
		tab = new JPanel(new GridLayout(4,7,0,0));
		tab.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Tabuleiro"));
		//Painel das peças disponíveis
		disp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		disp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Peças disponíveis"));
		//Seta a barra de status
		this.setStatus("Selecione uma peça");
		//Adiciona os paineis
		jogo.add(tab);
		jogo.add(disp);		
	}
	 
	public void atualizaTabuleiro(){
		try {
			//Limpa os painéis
			this.tab.removeAll();
			this.disp.removeAll();			
			Posicao[][] posicoes = tabuleiro.informarPosicoes();
			//Exibição das posições do tabuleiro
			for(int i = 0; i < posicoes.length; i++){
				for(int j = 0; j < posicoes[0].length; j++){
					this.tab.add(new ImagemDePosicao(posicoes[i][j],this.actionHandler));
				}
			}	
			//Exibição das peças disponíveis
			int numDisp = this.pecas_disponiveis.size();
			for(int i = 0; i < numDisp; i++){
				this.disp.add(pecas_disponiveis.elementAt(i));
			}
			System.out.println();
			this.exibirConteudo(jogo);			
			this.disp.repaint();
			this.tab.repaint();
		} catch(Exception e){
			this.exibirErro(e.getMessage());
		}
	}
	 
	public void selecionarPeca(ImagemDePeca imgPeca) {		
		if(imgPeca.equals(pecaSelecionada)){
			rotacionar();			
		} else {
			tabuleiro.setarPecaSelecionada(imgPeca.getPeca());
			this.pecaSelecionada = imgPeca;			
			this.setStatus("Peça selecionada! Clique novamente para rotacionar ou selecione a posição no tabuleiro para colocar a peça.");
		}
		//Remove as bordas de todas as peças
		int numDisp = this.pecas_disponiveis.size();
		for(int i = 0; i < numDisp; i++){
			pecas_disponiveis.elementAt(i).setBorder(BorderFactory.createEmptyBorder());
		}
		//Seta a borda da peça selecionada
		imgPeca.setBorder(BorderFactory.createLineBorder(Color.RED,2));
	}
	 
	public void rotacionar() {
		try{
			tabuleiro.rotacionar();
			this.pecaSelecionada.atualizar();
			this.setStatus("Peça rotacionada! Agora selecione a posição no tabuleiro para colocar a peça.");
		} catch (Exception e){
			exibirErro(e.getMessage());
		}
	}
	
	/**
	 * Exibi o ranking dos jogadores ordenado pelo indice de aproveitamento
	 */
	public void exibirRanking(){
		//Ordenar os resultados pelo indice de aproveitamento		
		Vector<Jogador> jogadores = this.tabuleiro.informarJogadores();
		int numJogadores = jogadores.size();
		Jogador[] jogOrd = new Jogador[numJogadores];
		for(int i = 0; i < numJogadores; i++){
			jogOrd[i] = jogadores.elementAt(i);
		}
		jogOrd = this.ordenarJogadoresPorIndice(jogOrd);		
		JTextArea listaJogadores = new JTextArea();
		listaJogadores.setEditable(false);
		String linha = 
		"Ranking\n\n" +
		"Jogador\tÍndice\tTentativas\tFinalizados\n" +
		"-----------------------------------------------------------------------------------------\n";
		listaJogadores.append(linha);
		for(int i = 0; i < jogOrd.length; i++){
			linha = jogOrd[i].getNome() + "\t" + jogOrd[i].indiceDeAproveitamento() + "%\t" +
			jogOrd[i].partidasJogadas() + "\t" + jogOrd[i].quebraCabecasFinalizados() +"\n";
			listaJogadores.append(linha);			
		}
		this.setStatus("Ranking dos jogadores");
		listaJogadores.setTabSize(10);
		JScrollPane rolagem = new JScrollPane(listaJogadores);
		this.exibirConteudo(rolagem);		
	}
	
	/**
	 * Organiza o Vector de jogadores pelo indice;
	 * Usa o método Bubble Sort para ordenação
	 * @param jogadores Vector com os jogadores
	 * @return
	 */
	protected Jogador[] ordenarJogadoresPorIndice(Jogador[] jogadores){		
		boolean trocou;
		int ia1,ia2;
		int total = jogadores.length;
		Jogador aux;
		do{
			trocou = false;
			for(int i = 0; i < total-1; i++){
				ia1 = jogadores[i].indiceDeAproveitamento();
				ia2 = jogadores[i+1].indiceDeAproveitamento();
				if(ia1 < ia2){
					aux = jogadores[i];
					jogadores[i] = jogadores[i+1];
					jogadores[i+1] = aux;
					trocou = true;
				}
			}
		} while(trocou);
		return jogadores;
	}
	 
	/**
	 * Exibi mensagem de congratulações, salva a lista de jogadores
	 * e volta para a tela de seleção de quebra-cabeça
	 */
	public void venceuJogo() {
		JOptionPane.showMessageDialog(this, "Parabéns, você completou o quebra-cabeça!", "Fim de jogo", JOptionPane.PLAIN_MESSAGE, new ImageIcon(JogadorView._IMG_FIM));
		this.selecionarQuebraCabeca();
		try{
			this.tabuleiro.salvarListaJogadores();
		} catch (Exception e){
			exibirErro(e.getMessage());
		}
	}
	 
	public void cancelarPartida() {
		tabuleiro.setarJogadorAtual("");
		this.exibirConteudo(new JPanel());
		this.setTitulo(null);
		this.inicializarMenus();
		this.setStatus("Jogo encerrado!");
		try{
			this.tabuleiro.salvarListaJogadores();
		} catch(Exception e){
			this.exibirErro(e.getMessage());
		}
	}
	 
	public void posicionarPeca(ImagemDePosicao p) {
		try{
			
			if(p.getPosicao().estaVazia()){ //Posição vazia procede jogada
			
				if(!tabuleiro.procederJogada(p.getPosicao())){
					this.exibirErro("Jogada inválida");
				} else {
					p.atualiza();					
					this.pecas_disponiveis.remove(this.pecaSelecionada);
					this.pecas_posicionadas.add(this.pecaSelecionada);
					this.pecaSelecionada = null;
					this.setStatus("Jogada efetuada");					
					this.atualizaTabuleiro();	
					//Verifica o fim do jogo
					if(this.tabuleiro.verificarFimDeJogo()){
						this.venceuJogo();
					}
				}
			} else {
				
				this.removerPeca(p); //Posição ocupada remove peça
			}
			
		}catch(Exception e){
			exibirErro(e.getMessage());
		}		
	}
	
	public void removerPeca(ImagemDePosicao imgPos){
		Peca peca = imgPos.getPosicao().getOcupante();
		//Procura a ImagemDePosicao no Vector de pecas posicionadas
		int numPosicionadas = this.pecas_posicionadas.size();
		ImagemDePeca imgPeca = null;		
		for(int i = 0; i < numPosicionadas; i++){
			imgPeca = this.pecas_posicionadas.elementAt(i);
			if(imgPeca.getPeca().equals(peca)){
				break;
			}
		}
		//Informa ao tabuleiro para remover a peça
		try{
			this.tabuleiro.removerPeca(imgPos.getPosicao());
		} catch (Exception e){
			exibirErro(e.getMessage());
		}
		//Adiciona as peças disponiveis e remove das posicionadas
		this.pecas_disponiveis.add(imgPeca);
		this.pecas_posicionadas.remove(imgPeca);
		this.selecionarPeca(imgPeca);
		this.setStatus("Peça removida do tabuleiro");
		this.atualizaTabuleiro();
		
	}
	
	public static void main(String[] args){
		try{		
			new JogadorView();
		} catch(Exception e){			
			System.out.println("Não foi possível iniciar o aplicativo (" + e.getMessage() + ")");
		}
	}
	
	/* CLASSES INTERNAS PARA TRATAMENTO DE EVENTOS */
	protected class ActionHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			String comando = e.getActionCommand();
			
			//System.out.println("--> " + comando);
			
			if(comando == JogadorView._CRIAR_JOGADOR){
				
				criarJogador();
				
			}else if(comando == JogadorView._SELECIONAR_JOGADOR){
				
				selecionarJogador();
				
			}else if(comando == JogadorView._FECHAR){
				
				sair();
				
			}else if(comando == JogadorView._ENCERRAR){
				
				cancelarPartida();
				
			}else if(comando == JogadorView._REINICIAR_PARTIDA){
				
				reiniciarPartida();
				
			}else if(comando == JogadorView._SELECIONAR_QUEBRA_CABECA){
				
				selecionarQuebraCabeca();
				
			}else if(comando == JogadorView._SELECIONAR_TABULEIRO){
				
				JButton source = (JButton)e.getSource();
				int codigo = Integer.parseInt(source.getToolTipText()) - 1;
				setarQuebraCabeca(codigo);
				
			} else if(comando == JogadorView._SELECIONAR_PECA){
				
				selecionarPeca((ImagemDePeca)e.getSource());
				
			} else if(comando == JogadorView._SELECIONAR_POSICAO){
				
				posicionarPeca((ImagemDePosicao)e.getSource());
				
			} else if(comando == JogadorView._RANKING){
				
				exibirRanking();
				
			}
		}
		
	}
	 
}
 