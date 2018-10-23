package logica;

import java.io.FileNotFoundException;
import java.util.List;
import rede.AtorNetgames;
import rede.Resultado;
import interfaceGrafica.InterfaceGraficaSongPop;

public class AtorJogador {
	protected SongPop songPop;
	protected InterfaceGraficaSongPop interfaceGrafica;
	protected AtorNetgames atorNetgames;

	public AtorJogador(InterfaceGraficaSongPop frame) {
		interfaceGrafica = frame;
		atorNetgames = new AtorNetgames(this);
	}

	public boolean desconectar() {
		boolean confirmado;
		boolean jogoEmAndamento = songPop.isJogoEmAndamento();
		if (jogoEmAndamento) {
			confirmado = interfaceGrafica.pedirConfirmacaoDesconexao();
			if (confirmado)
				atorNetgames.enviarDesistencia();
		} else {
			confirmado = true;
			atorNetgames.enviarRecusaDeRevanche();
		}
		if (confirmado)
			atorNetgames.desconectar();
		return confirmado;
	}

	public void desistirDaRodada() {
		songPop.pararMusica();
		gerarResultadoDaRodada(-1, -1);
		visualizarResultados(false);
	}

	public void desistirDoJogo() {
		interfaceGrafica.desabilitarOpcaoDesistir();
		Jogador jogador = songPop.getJogador();
		jogador.setEstado(EstadoDoJogador.DESISTIU);
		songPop.setJogoEmAndamento(false);
		atorNetgames.enviarDesistencia();
		interfaceGrafica.habilitarOpcaoContinuar();
	}

	public boolean conectar(String nomeJogador) {
		String servidor = interfaceGrafica.obterIdServidor();
		boolean exitoConexao = atorNetgames.solicitarConexao(servidor, nomeJogador);
		if (exitoConexao) {
			songPop = new SongPop();
			atorNetgames.solicitarInicio();			
		}
		return exitoConexao;
	}

	public int responder(int alternativaRespondida, double tempoDaContagem) {
		songPop.pararMusica();
		int alternativaCorreta = gerarResultadoDaRodada(alternativaRespondida, tempoDaContagem);
		return alternativaCorreta;
	}

	public void visualizarResultados(boolean finais) {
		Jogador jogador = songPop.getJogador();
		String nomeJogador = jogador.getNome();
		Resultado[][] resultadosJogador = songPop.getResultadosJogador();
		double pontuacaoTotalJogador = songPop.getPontuacaoTotalJogador();
		Jogador adversario = songPop.getAdversario();
		String nomeAdversario = adversario.getNome();
		Resultado[][] resultadosAdversario = songPop.getResultadosAdversario();
		double pontuacaoTotalAdversario = songPop.getPontuacaoTotalAdversario();
		if (finais) {
			String nomeVencedor = songPop.getNomeVencedor();
			EstadoDoJogador estado = adversario.getEstado();
			boolean porDesistencia = estado == EstadoDoJogador.DESISTIU;
			interfaceGrafica.visualizarResultadosFinais(nomeJogador, nomeAdversario, pontuacaoTotalJogador,
														pontuacaoTotalAdversario, nomeVencedor, porDesistencia);			
		}
		else {
			jogador.setEstado(EstadoDoJogador.VISUALIZANDO_RESULTADO);
			boolean botaoContinuarAtivo = songPop.isAdversarioPronto();
			EstadoDoJogador estado = adversario.getEstado();
			boolean botaoDesistirAtivo = estado != EstadoDoJogador.DESISTIU;
			interfaceGrafica.visualizarResultadosParciais(nomeJogador,nomeAdversario, resultadosJogador, resultadosAdversario,
														  pontuacaoTotalJogador, pontuacaoTotalAdversario,
														  botaoContinuarAtivo, botaoDesistirAtivo);
		}
	}
	
	public void tratarInicioDePartida(int posicao) {	
		String nomeJogador = atorNetgames.getNomeJogador();
		Jogador jogador = new Jogador(posicao, nomeJogador);
		songPop.setJogador(jogador);
		// se a posição do jogador = 1, a posição do adversario = 2, e vice-versa
		posicao = posicao % 2 + 1;
		String nomeAdversario = atorNetgames.informarNomeDoAdversario(posicao);
		// Ao reiniciar uma partida com o mesmo adversário, a posição recebida
		// como parâmetro não necessariamente é a posição do jogador (pode ser do adversário)
		// BUG netgames??
		if (nomeAdversario.equals(nomeJogador)) {
			posicao = posicao % 2 + 1;
			nomeAdversario = atorNetgames.informarNomeDoAdversario(posicao);
		}
		Jogador adversario = new Jogador(posicao, nomeAdversario);
		songPop.setAdversario(adversario);
		songPop.setJogoEmAndamento(true);
		List<GeneroMusical> generosJaEscolhidos = songPop.getGenerosJaEscolhidosJogador();
		interfaceGrafica.escolherGenero(nomeJogador, generosJaEscolhidos);
	}

	public void tratarRecebimentoDeResultado(Resultado resultado) {	
		songPop.armazenaResultado(resultado);
		boolean isAdversarioPronto = songPop.isAdversarioPronto();
		if (isAdversarioPronto) {
			double pontuacaoTotalAdversario = songPop.getPontuacaoTotalAdversario();
			interfaceGrafica.atualizaResultadosAdversario(resultado,pontuacaoTotalAdversario);
		}
	}

	public void tratarRecebimentoDeDesistencia() {
		songPop.setJogoEmAndamento(false);
		Jogador jogador = songPop.getJogador();
		EstadoDoJogador estado = jogador.getEstado();
		if (estado == EstadoDoJogador.VISUALIZANDO_RESULTADO) {
			interfaceGrafica.habilitarOpcaoContinuar();
			interfaceGrafica.desabilitarOpcaoDesistir();
		}
		Jogador adversario = songPop.getAdversario();
		adversario.setEstado(EstadoDoJogador.DESISTIU);
	}

	public void jogarNovamente() {
		boolean adversarioConectado = atorNetgames.adversarioConectado();
		Jogador adversario = songPop.getAdversario();
		EstadoDoJogador estado = adversario.getEstado();
		
		if (adversarioConectado && estado != EstadoDoJogador.REVANCHE_RECUSADA) {
			atorNetgames.enviarSolicitacaoDeRevanche();
			if (estado == EstadoDoJogador.REVANCHE_SOLICITADA) {
				interfaceGrafica.notificarIniciandoNovoJogo();
				songPop.reiniciarJogo();
				atorNetgames.reiniciarPartida();
			}
			else {
				Jogador jogador = songPop.getJogador();
				interfaceGrafica.notificarEsperandoRespostaRevanche();
				jogador.setEstado(EstadoDoJogador.AGUARDANDO_RESPOSTA_REVANCHE);
			}
		}
		else
			interfaceGrafica.notificarRecusaRevanche();
	}

	public void tratarRecebimentoDeSolicitacaoDeRevanche() {
		Jogador jogador = songPop.getJogador();
		EstadoDoJogador estado = jogador.getEstado();
		if (estado == EstadoDoJogador.AGUARDANDO_RESPOSTA_REVANCHE) {
			interfaceGrafica.notificarIniciandoNovoJogo();
			songPop.reiniciarJogo();
		} else {
			Jogador adversario = songPop.getAdversario();
			adversario.setEstado(EstadoDoJogador.REVANCHE_SOLICITADA);
		}
	}

	public void tratarRecebimentoDeRecusaDeRevanche() {
		Jogador jogador = songPop.getJogador();
		EstadoDoJogador estado = jogador.getEstado();
		if (estado == EstadoDoJogador.AGUARDANDO_RESPOSTA_REVANCHE) {
			interfaceGrafica.notificarRecusaRevanche();
			interfaceGrafica.desabilitarOpcaoJogarNovamente();
		} else {
			Jogador adversario = songPop.getAdversario();
			adversario.setEstado(EstadoDoJogador.REVANCHE_RECUSADA);
		}
	}

	public void iniciarRodada() {
		Nivel nivel = songPop.getNivelJogador();
		Rodada rodada = nivel.getRodadaAtual();
		rodada.setEmAndamento(true);
		Questao questao = rodada.getQuestao();
		boolean rodadaMusical = rodada.isRodadaMusical();
		if (rodadaMusical) {
			QuestaoMusical q = (QuestaoMusical) questao;
			int idMusica = q.getIdMusica();
			try {
				songPop.tocarMusica(idMusica);
			} catch (FileNotFoundException e) {
				interfaceGrafica.notificarArquivoNaoEncontrado();
			}
		} else {
			QuestaoSemMusica q = (QuestaoSemMusica) questao;
			String complementoPergunta = q.getComplementoPergunta();
			interfaceGrafica.mostrarComplementoPergunta(complementoPergunta);
		}
		String[] alternativas = questao.getAlternativas();
		interfaceGrafica.mostrarAlternativas(alternativas);
		interfaceGrafica.iniciarContagemRegressiva();
	}

	public void escolherGenero(int indiceGenero) {
		Jogador jogador = songPop.getJogador();
		GeneroMusical generoMusical = songPop.getGeneroEscolhido(indiceGenero);
		Nivel nivelAtual = songPop.proximoNivel(generoMusical);
		Rodada rodadaAtual = nivelAtual.getRodadaAtual();
		Questao questao = rodadaAtual.getQuestao();
		String pergunta = questao.getPergunta();
		int tempoMax = nivelAtual.getTempoMax();
		String nomeJogador = jogador.getNome();
		jogador.setEstado(EstadoDoJogador.JOGANDO);
		boolean rodadaMusical = rodadaAtual.isRodadaMusical();
		interfaceGrafica.iniciarJogo(nomeJogador, pergunta, tempoMax,rodadaMusical);
	}

	public int gerarResultadoDaRodada(int alternativaRespondida, double tempoDaContagem) {
		Jogador jogador = songPop.getJogador();
		int idJogador = jogador.getId();
		Nivel nivel = songPop.getNivelJogador();
		Rodada rodada = nivel.getRodadaAtual();
		Questao questao = rodada.getQuestao();
		int alternativaCorreta = questao.getAlternativaCorreta();
		double tempoDeResposta;
		if (tempoDaContagem == -1)
			tempoDeResposta = -1;
		else
			tempoDeResposta = songPop.calculaTempoDeResposta(nivel, tempoDaContagem);
		double pontuacaoDaRodada;
		if (alternativaCorreta == alternativaRespondida)
			pontuacaoDaRodada = songPop.calculaPontuacao(rodada, tempoDaContagem);
		else
			pontuacaoDaRodada = 0.0;
		int numeroNivel = nivel.getNumero();
		int numeroRodada = rodada.getNumero();
		Resultado resultado = new Resultado(idJogador, numeroNivel, numeroRodada, pontuacaoDaRodada, tempoDeResposta);
		songPop.armazenaResultado(resultado);
		atorNetgames.enviarResultado(resultado);
		return alternativaCorreta;
	}

	public void continuar() {
		boolean fimDeJogo = songPop.verificaFimDeJogo();
		if (fimDeJogo) {
			visualizarResultados(true);
		} else {
			Jogador jogador = songPop.getJogador();
			String nomeJogador = jogador.getNome();
			Nivel nivel = songPop.getNivelJogador();
			boolean ultimaRodada = nivel.isUltimaRodada();
			if (ultimaRodada) {
				List<GeneroMusical> generosJaEscolhidos = songPop.getGenerosJaEscolhidosJogador();
				interfaceGrafica.escolherGenero(nomeJogador, generosJaEscolhidos);
			} else {
				nivel.proximaRodada();
				Rodada rodada = nivel.getRodadaAtual();
				Questao questao = rodada.getQuestao();
				String pergunta = questao.getPergunta();
				int tempoMax = nivel.getTempoMax();
				boolean rodadaMusical = rodada.isRodadaMusical();
				interfaceGrafica.iniciarJogo(nomeJogador, pergunta, tempoMax,rodadaMusical);
			}
			jogador.setEstado(EstadoDoJogador.JOGANDO);
		}
	}

	public void tratarFaltaDeAdversario() {
		interfaceGrafica.aguardarAdversario();
	}
}