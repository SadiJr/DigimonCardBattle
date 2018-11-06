package view;

import control.Tabuleiro;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import rede.AtorNetGames;


public class AtorJogador {

	protected Tabuleiro tabuleiro;
	protected AtorNetGames rede;
	protected String idUsuario;
	protected TelaPrincipal tela;

	
	public AtorJogador() {
		this.tabuleiro = new Tabuleiro();
		this.tela = new TelaPrincipal(this);
		this.rede = new AtorNetGames(this);
	}
	
	
	public void conectar(String idUsuario, String servidor) throws Exception {
		this.idUsuario = idUsuario;
		rede.conectar(idUsuario, servidor);
	}
	
	
	public void realizaJogada(int tipoMovimento, int posicao)throws Exception{
		//tratamento de jogador da vez
		if(tabuleiro.getJogador1().isJogadorDaVez()){
			tabuleiro.realizaJogada(tipoMovimento, posicao);
			if(tabuleiro.getJogador1().isVencedor()){
				this.notificar("Venceu a campanha");
				if(tabuleiro.getJogador1().getNumeroDeVitorias() < 2){
					this.notificar("Uma nova campanha será iniciada, o outro jogador inicia. Aguarde.");
				}else{
					this.notificar("Venceu o jogo");
				}
			}
			//enviar a jogada
			this.enviarJogada();
		

			this.tela.limpar();
			this.tela.setaImagemJogador();
		}
		else{
			throw new Exception("Não é sua vez");
		}
	}

	public void solicitarReinicio() throws NaoJogandoException {
		this.tabuleiro.getJogador1().setJogadorDaVez(false);
		this.rede.enviarJogada(null);
	}
	
	
	
	public void notificar(String mensagem) {
		tela.notificar(mensagem);
	}

	public void desconectar() throws NaoConectadoException {
		rede.desconectar();
	}
	
	
	public void notificarErro(String erro) {
		this.tabuleiro.setPartidaEmAndamento(false);
		tela.notificar(erro);
	}

	public void IniciarPartida() throws NaoConectadoException {
		rede.iniciarPartida();
	}

	public void enviarJogada() throws NaoJogandoException{
		this.rede.enviarJogada(this.tabuleiro);
	}


	public Tabuleiro getTabuleiro() {
		return this.tabuleiro;
	}


	public void receberJogada(Tabuleiro tab) throws NaoConectadoException, NaoJogandoException{
		if (tab == null) {
			int opt = this.tela.perguntar("O outro jogador deseja reiniciar a partida. Você iniciará com o lado branco.");
			if (opt == 0) {
				this.reiniciar();
				return;
			} else {
				this.enviarJogada();
				return;
			}
		}
		if (tab.isReiniciado()) {
			this.tabuleiro = tab;
			this.reiniciar();
		}
		this.tabuleiro.setJogador1(tab.getJogador2());
		this.tabuleiro.setJogador2(tab.getJogador1());
		this.tabuleiro.setReiniciado(false);

		this.tabuleiro.getJogador1().setJogadorDaVez(true);
		// se ataque atualiza interface
		if(tab.getUltimoMovimento() == 1){
			this.tela.limpar();
			this.tela.atualizaInterface(tab.getUltimaPosClicJogador1());
			
		}
		if (this.tabuleiro.getJogador2().isVencedor()) {
			this.notificar("Derrota!");
			this.tabuleiro.setPartidaEmAndamento(false);
			if(tabuleiro.getJogador2().getNumeroDeVitorias() < 2){
				this.notificar("Uma nova campanha será iniciada, você inicia.");
				this.iniciarNovaCampanha(1);
			}else{
				this.tela.notificar("Perdeu a melhor de 3");
				return;
			}
		}
		this.tela.notificar("É a sua vez " + this.tabuleiro.getJogador1().getNome());
		tela.setaImagemJogador();
	}


	private void reiniciar() {
		if(tabuleiro.isReiniciado()){
			this.tela.limpar();
			this.notificar("O tabuleiro foi reiniciado.");
			return;
		}
		Tabuleiro tab = new Tabuleiro(); 
		System.out.println("entrou reiniciar");
		this.tela.limpar();
		tab.setJogador1(this.tabuleiro.getJogador1());
		tab.setJogador2(this.tabuleiro.getJogador2());
		tab.setReiniciado(true);
		this.tabuleiro = null;
		this.tabuleiro = tab;
		this.tabuleiro.getJogador1().setJogadorDaVez(true);
		tabuleiro.getJogador1().setCodigo(1);
		tabuleiro.getJogador1().criarPosicoes();
		tabuleiro.getJogador1().obterPosicaoInicial();
		tabuleiro.getJogador2().setCodigo(2);
		tabuleiro.getJogador2().criarPosicoes();
		tabuleiro.getJogador2().obterPosicaoInicial();
		System.out.println("chegou " + tabuleiro.getJogador1().getPosicaoAtual());
		tela.setaImagemJogador();
		System.out.println("fim");
		
	}


	public void iniciarNovaPartida(Integer posicao) {
		tabuleiro = new Tabuleiro();
		String idAdversario = rede.getNomeAdversario(posicao);
		tabuleiro.criarJogadores(idUsuario, idAdversario, posicao);
		tabuleiro.setPartidaEmAndamento(true);
		tela.limpar();
		tela.notificar("Partida encontrada, o nome de seu adversário é: " + idAdversario);
		tela.iniciarPartida.setEnabled(false);
		tela.conectar.setEnabled(false);
		if(tabuleiro.getJogador1().isJogadorDaVez()){
			tela.setaImagemJogador();
		}else{
			this.tela.notificar("Agora é a vez do jogador " +idAdversario+".Aguarde sua jogada");
		}
		
	}
	
	public void iniciarNovaCampanha(Integer posicao) {
		tabuleiro.setPartidaEmAndamento(true);
		tela.limpar();
		tela.notificar("Nova campanha iniciada");
		tela.iniciarPartida.setEnabled(false);
		tela.conectar.setEnabled(false);
		tabuleiro.getJogador1().setCodigo(posicao);
		tabuleiro.getJogador1().criarPosicoes();
		tabuleiro.getJogador1().obterPosicaoInicial();
		tabuleiro.getJogador2().setCodigo(posicao == 1 ? 2 : 1);
		tabuleiro.getJogador2().criarPosicoes();
		tabuleiro.getJogador2().obterPosicaoInicial();
		tela.setaImagemJogador();
		
	}
	

}