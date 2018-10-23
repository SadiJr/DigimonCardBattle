import java.util.Vector;

public class Tabuleiro {

	protected Jogador jogadorAtual = null;

	protected QuebraCabeca quebraCabeca;

	protected Peca pecaSelecionada = null;

	protected static int pivo = 0;

	protected Posicao posicaoSelecionada;

	protected static final String nome_arquivo = "dados.dat";

	protected Posicao[][] posicao;

	protected JogadorView jogadorView;
	
	protected Vector<Jogador> jogadores;
	
	public Tabuleiro() throws Exception{
		/* Tenta recuperar a lista de jogadores do disco
		 se n�o conseguir cria uma vazia */
		try{
			recuperarListaJogadores();
			if(jogadores == null){
				jogadores = new Vector<Jogador>();
			}
		} catch (Exception e){	
			jogadores = new Vector<Jogador>();			
		}
		inicializarPosicoes();
	}
	

	
	public void inicializarPosicoes(){
		posicao = new Posicao[4][7];
		//Linha 0
		posicao[0][0] = new Posicao(Formato.CIRCULO);
		posicao[0][1] = new Posicao(Formato.CIRCULO);	
		posicao[0][2] = new Posicao(Formato.CRUZ);
		posicao[0][3] = new Posicao(Formato.CRUZ);
		posicao[0][4] = new Posicao(Formato.CRUZ);
		posicao[0][5] = new Posicao(Formato.CIRCULO);
		posicao[0][6] = new Posicao(Formato.QUADRADO);
		//Linha 1
		posicao[1][0] = new Posicao(Formato.CIRCULO);
		posicao[1][1] = new Posicao(Formato.QUADRADO);
		posicao[1][2] = new Posicao(Formato.QUADRADO);
		posicao[1][3] = new Posicao(Formato.QUADRADO);
		posicao[1][4] = new Posicao(Formato.CRUZ);
		posicao[1][5] = new Posicao(Formato.QUADRADO);
		posicao[1][6] = new Posicao(Formato.CRUZ);
		//Linha 2
		posicao[2][0] = new Posicao(Formato.QUADRADO);
		posicao[2][1] = new Posicao(Formato.CIRCULO);
		posicao[2][2] = new Posicao(Formato.CRUZ);
		posicao[2][3] = new Posicao(Formato.CIRCULO);
		posicao[2][4] = new Posicao(Formato.CIRCULO);
		posicao[2][5] = new Posicao(Formato.QUADRADO);
		posicao[2][6] = new Posicao(Formato.CIRCULO);
		//Linha 2
		posicao[3][0] = new Posicao(Formato.QUADRADO);
		posicao[3][1] = new Posicao(Formato.CIRCULO);
		posicao[3][2] = new Posicao(Formato.CRUZ);
		posicao[3][3] = new Posicao(Formato.QUADRADO);
		posicao[3][4] = new Posicao(Formato.CRUZ);
		posicao[3][5] = new Posicao(Formato.CRUZ);
		posicao[3][6] = new Posicao(Formato.CIRCULO);
	}

	/**
	 *Retorna um array de String com o nome
	 *dos jogadores cadastrados
	 */
	public Vector<Jogador> informarJogadores() {
		return this.jogadores;
	}
	
	/**
	 * Informa o jogador selecionado (atual)
	 */
	public Jogador informarJogadorAtual(){
		return this.jogadorAtual;
	}

	/**
	 * Cria um novo jogador, verificando se j� existe outro jogador com o 
	 * mesmo nome
	 */
	public void criarJogador(String nome) throws Exception{
		int numJogadores = jogadores.size();
		Jogador j;
		for(int i = 0; i < numJogadores; i++){
			j = jogadores.elementAt(i);
			if(j.getNome().equalsIgnoreCase(nome)){
				throw new Exception("O jogador " + nome + " j� existe");
			}
		}
		jogadores.add(new Jogador(nome));
		salvarListaJogadores();
	}

	/**
	 *Salvar a lista de jogadores (persist�ncia)
	 */
	protected void salvarListaJogadores() throws Exception{
		ObjectStream.gravar(nome_arquivo, jogadores);
	}

	/**
	 *Recuperar a lista de jogadores
	 */
	@SuppressWarnings("unchecked")
	protected void recuperarListaJogadores() throws Exception{
		this.jogadores = (Vector<Jogador>)ObjectStream.recuperar(Tabuleiro.nome_arquivo);
	}

	/**
	 *Dado o nome, retorna o objeto Jogador
	 */
	public Jogador retornaJogador(String nome) {
		int numJogadores = jogadores.size();
		Jogador aux = null;
		for(int i = 0; i < numJogadores; i++){
			aux = jogadores.elementAt(i);
			if(aux.getNome().equalsIgnoreCase(nome))
				return aux;
		}
		return null;
	}

	public void setarJogadorAtual(String nome) {
		jogadorAtual = retornaJogador(nome);
	}

	/*
	 * Limpa as posi��es e atualiza o n�mero de tentativas do tabuleiro selecionado
	 */
	public void iniciarPartida() {
		quebraCabeca.inicializar();
		quebraCabeca.atualizaTentativas();
	}

	/**
	 * Seta o quebra cabe�a selecionado e inicia a partida
	 * @param quebraCabeca
	 */
	public void setarQuebraCabeca(QuebraCabeca quebraCabeca) {
		this.quebraCabeca = quebraCabeca;
		iniciarPartida();
	}

	/**
	 * Informa os quebra-cabe�as do jogador atual
	 * @return
	 */
	public QuebraCabeca[] informeQuebraCabecas() {
		return jogadorAtual.informarQuebraCabecas();
	}
	
	/**
	 * Informa as posi��es do tabuleiro
	 * @return
	 */
	public Posicao[][] informarPosicoes(){
		return this.posicao;
	}

	/**
	 * Seta os ocupantes de todas as posi��es como null
	 */
	public void limparPosicoes(){
		quebraCabeca.limparPecas();
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 7; j++){
				posicao[i][j].setOcupante(null);
			}
		}
	}

	/**
	 * Rotaciona a pe�a selecionada
	 * @param direcao true rotaciona para direita, false para esquerda
	 * @throws Exception Se n�o hover pe�a selecionada
	 */
	public void rotacionar() throws Exception{
		if(this.pecaSelecionada == null)
			throw new Exception("Nenhuma pe�a selecioada");
		pecaSelecionada.rotacionar();	
	}

	/**
	 * Seta a pe�a selecionada
	 */
	public void setarPecaSelecionada(Peca peca) {
		pecaSelecionada = peca;
	}

	public boolean procederJogada(Posicao pos) throws Exception{
		if(pecaSelecionada == null){
			throw new Exception("Nenhuma pe�a selecionada");
		}
		Posicao pos00 = null;
		Posicao pos01 = null;
		Posicao pos10 = null;
		Posicao pos11 = null;
		boolean jogadaOk = true;
		boolean parar = false;
		int pivo_x = pecaSelecionada.getPivo_x();
		int pivo_y = pecaSelecionada.getPivo_y();
		int tab_x, tab_y = 0; 
		int pos00_x, pos00_y, pos01_x, pos01_y, pos10_x, pos10_y, pos11_x, pos11_y = 0;
		//Procura as coordenadas da posi��o no tabuleiro
		for(tab_x = 0; tab_x < 4; tab_x++){
			for(tab_y = 0; tab_y < 7; tab_y++){
				if(posicao[tab_x][tab_y].equals(pos)){					
					parar = true;
					break;					
				}
			}
			if(parar){
				break;
			}
		}		
		//Define as coordenadas das outras posi��es necess�rias
		pos00_x = tab_x - pivo_x;
		pos00_y = tab_y - pivo_y;
		pos01_x = pos00_x;
		pos01_y = pos00_y + 1;
		pos10_x = pos00_x + 1;
		pos10_y = pos00_y;
		pos11_x = pos00_x + 1;
		pos11_y = pos00_y + 1;				
		//Verifica se existe alguma coordenada fora dos limites do tabuleiro
		if(pos00_x < 0 || pos00_x > 3 || pos01_x < 0 || pos01_x > 3 || pos10_x < 0 || pos10_x > 3 || pos11_x < 0 || pos11_x > 3 ||
		   pos00_y < 0 || pos00_y > 6 || pos01_y < 0 || pos01_y > 6 || pos10_y < 0 || pos10_y > 6 || pos11_y < 0 || pos11_y > 6){
			/*Existem coordenadas fora dos limites temos que verificar se a pe�a � 
			  nula ou n�o para dizer se a jogada � inv�lida*/
			//Para a posi��o 1
			if((pos00_x < 0 || pos00_x > 3 || pos00_y < 0 || pos00_y > 6) && !pecaSelecionada.itemVazio(0, 0)){
				jogadaOk = false;
			}
			if((pos01_x < 0 || pos01_x > 3 || pos01_y < 0 || pos01_y > 6) && !pecaSelecionada.itemVazio(0, 1)){
				jogadaOk = false;
			}
			if((pos10_x < 0 || pos10_x > 3 || pos10_y < 0 || pos10_y > 6) && !pecaSelecionada.itemVazio(1, 0)){
				jogadaOk = false;
			}
			if((pos11_x < 0 || pos11_x > 3 || pos11_y < 0 || pos11_y > 6) && !pecaSelecionada.itemVazio(1, 1)){
				jogadaOk = false;
			}
		}	
		//Se as coordenadas est�o ok verifica se h� alguma posi��o ocupada e se os formatos coincidem
		if(jogadaOk){
			//Verificar 00
			if((pos00_x >= 0 && pos00_x <= 3) && (pos00_y >= 0 && pos00_y <= 6)){
				pos00 = this.posicao[pos00_x][pos00_y];
				if(!pecaSelecionada.itemVazio(0, 0) && (!pos00.estaVazia() || !pecaSelecionada.verificaFormato(0,0,pos00.getFormato()))){
					jogadaOk = false;
				}
			}
			//Verificar 01
			if(pos01_x >= 0 && pos01_x <= 3 && pos01_y >= 0 && pos01_y <= 6){
				pos01 = this.posicao[pos01_x][pos01_y];
				if(!pecaSelecionada.itemVazio(0, 1) && (!pos01.estaVazia() || !pecaSelecionada.verificaFormato(0,1,pos01.getFormato()))){
					jogadaOk = false;
				}
			}
			//Verificar 10
			if(pos10_x >= 0 && pos10_x <= 3 && pos10_y >= 0 && pos10_y <= 6){
				pos10 = this.posicao[pos10_x][pos10_y];
				if(!pecaSelecionada.itemVazio(1, 0) && (!pos10.estaVazia() || !pecaSelecionada.verificaFormato(1,0,pos10.getFormato()))){
					jogadaOk = false;
				}
			}			
			//Verificar 11
			if(pos11_x >= 0 && pos11_x <= 3 && pos11_y >= 0 && pos11_y <= 6){
				pos11 = this.posicao[pos11_x][pos11_y];				
				if(!pecaSelecionada.itemVazio(1, 1) && (!pos11.estaVazia() || !pecaSelecionada.verificaFormato(1,1,pos11.getFormato()))){
					jogadaOk = false;
				}
			}
		}
		//Se estiver tudo ok at� aqui posiciona a peca no tabuleiro
		if(jogadaOk){
			if(!pecaSelecionada.itemVazio(0, 0))
				pos00.setOcupante(pecaSelecionada);
			if(!pecaSelecionada.itemVazio(0, 1))
				pos01.setOcupante(pecaSelecionada);
			if(!pecaSelecionada.itemVazio(1, 0))			
				pos10.setOcupante(pecaSelecionada);
			if(!pecaSelecionada.itemVazio(1, 1))			
				pos11.setOcupante(pecaSelecionada);
			pecaSelecionada.setDisponivel(false);
			this.setarPecaSelecionada(null);
		}
		return jogadaOk;
	}

	/**
	 * Verifica o fim do jogo (todas as posi��es ocupadas)
	 * Se for fim de jogo seta o quebra-cabe�a como finalizado
	 * @return True se todas as posi��es estiverem ocupadas
	 */
	public boolean verificarFimDeJogo() throws Exception{
		boolean fimDeJogo = true;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 7; j++){
				if(posicao[i][j].getOcupante() == null){
					fimDeJogo = false;
					break;
				}
			}
			if(!fimDeJogo){
				break;
			}
		}
		//Seta o quebra-cabe�a como finalizado e salva
		if(fimDeJogo){
			this.quebraCabeca.setFinalizado(true);
			this.salvarListaJogadores();
			
		}
		return fimDeJogo;
	}

	/**
	 * 
	 * @param posSel
	 */
	public void removerPeca(Posicao posSel) throws Exception{
		Peca pecaNaPosicao = posSel.getOcupante();
		if(pecaNaPosicao == null){
			throw new Exception("A posi��o n�o est� ocupada");
		}
		Posicao aux;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 7; j++){
				aux = posicao[i][j];
				if(aux.getOcupante() != null){
					if(aux.getOcupante().equals(pecaNaPosicao)){
						aux.setOcupante(null);	
					}					
				}
			}
		}
		adicionaPecaDisponivel(pecaNaPosicao);
	}

	protected void adicionaPecaDisponivel(Peca peca) {
		peca.setDisponivel(true);
	}

	public void cancelarPartida() {
		limparPosicoes();
		setarQuebraCabeca(null);
	}
	
	/**
	 * Informa as pe�as do quebra-cabe�a selecionado	 *
	 */
	public Peca[] informarPecas() throws Exception{
		if(this.quebraCabeca == null){
			throw new Exception("Nenhum quebra-cabe�a selecionado");
		}
		return quebraCabeca.informarPecas();		
	}
	
	/**
	 * Informa as pe�as do quebra-cabe�a selecionado que est�o dispon�veis
	 */
	public Peca[] informarPecasDisponiveis() throws Exception{
		if(this.quebraCabeca == null){
			throw new Exception("Nenhum quebra-cabe�a selecionado");
		}
		//Pegas as pe�as e conta as dispon�veis
		Peca[] pecas = this.informarPecas();
		int numDisp = 0;
		for(int i = 0; i < pecas.length; i++){
			if(pecas[i].getDisponivel()){
				numDisp++;
			}
		}
		//Cria um array somente com as pe�as que est�o dispon�veis
		Peca[] disponiveis = new Peca[numDisp];		
		int aux = 0;
		for(int i = 0; i < numDisp; i++){			
			if(pecas[i].getDisponivel()){
				disponiveis[aux] = pecas[i];
				aux++;
			}			
		}
		return disponiveis;		
	}	
	

}
