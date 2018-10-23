package logica;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import rede.Resultado;

public class SongPop {
	protected Jogador jogador;
	protected Jogador adversario;
	protected Nivel nivelJogador;
	protected GerenciadorDeMusicas gerenciadorDeMusicas;
	protected GerenciadorDeResultados gerenciadorDeResultados;
	protected GerenciadorDeGruposMusicais gerenciadorDeGruposMusicais;
	protected GeradorDeQuestoes geradorDeQuestoes;
	protected TocadorDeMusica tocadorDeMusica;
	protected List<GeneroMusical> generosJaEscolhidosJogador;
	protected boolean jogoEmAndamento;
	
	public SongPop() {		
		gerenciadorDeResultados = new GerenciadorDeResultados();
		gerenciadorDeGruposMusicais = new GerenciadorDeGruposMusicais();
		gerenciadorDeMusicas = new GerenciadorDeMusicas(gerenciadorDeGruposMusicais);
		geradorDeQuestoes = new GeradorDeQuestoes(gerenciadorDeMusicas, gerenciadorDeGruposMusicais);
		tocadorDeMusica = new TocadorDeMusica(gerenciadorDeMusicas);
		generosJaEscolhidosJogador = new LinkedList<GeneroMusical>();
		jogoEmAndamento = false;
	}
	
	public boolean isJogoEmAndamento() {
		return jogoEmAndamento;
	}
	
	public void setJogoEmAndamento(boolean emAndamento) {
		jogoEmAndamento = emAndamento;
	}
	
	public Nivel getNivelJogador() {
		return nivelJogador;
	}
	
	public boolean verificaFimDeJogo() {
		boolean fimDeJogo;
		if (jogoEmAndamento) {
			fimDeJogo = isUltimaRodadaDoJogo();
			jogoEmAndamento = !fimDeJogo;
		}
		else
			fimDeJogo = true;		
		return fimDeJogo;
	}
	
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public void setAdversario(Jogador adversario) {
		this.adversario = adversario;
	}
	
	public Jogador getJogador() {
		return this.jogador;
	}
	
	public Jogador getAdversario() {
		return this.adversario;
	}
	
	public double calculaTempoDeResposta(Nivel nivel, double tempoDaContagem) {
		double tempoMax = nivel.getTempoMax();
		double tempoDeResposta = tempoMax - tempoDaContagem;
		return tempoDeResposta;
	}

	public double calculaPontuacao(Rodada rodada, double tempoDaContagem) {
		double pontuacaoDaRodada = rodada.getPontuacaoPorSegundo();
		double pontuacao = pontuacaoDaRodada * tempoDaContagem;
		return pontuacao;
	}

	private Resultado[][] getResultadosJogador(int idJogador) {		
		Resultado[][] resultados = gerenciadorDeResultados.getResultadosJogador(idJogador);		
		return resultados;
	}
	
	public Resultado[][] getResultadosJogador() {
		int idJogador = jogador.getId();
		Resultado[][] resultados = getResultadosJogador(idJogador);
		return resultados;
	}

	public Resultado[][] getResultadosAdversario() {
		int idJogador = adversario.getId();
		Resultado[][] resultados = getResultadosJogador(idJogador);
		return resultados;
	}

	private double getPontuacaoTotalJogador(int idJogador) {
		double pontuacaoTotal = gerenciadorDeResultados.getPontuacaoTotalJogador(idJogador);
		return pontuacaoTotal;
	}
	
	public double getPontuacaoTotalJogador() {
		int idJogador = jogador.getId();
		double pontuacaoTotal = getPontuacaoTotalJogador(idJogador);
		return pontuacaoTotal;
	}
	
	public double getPontuacaoTotalAdversario() {
		int idJogador = adversario.getId();
		double pontuacaoTotal = getPontuacaoTotalJogador(idJogador);
		return pontuacaoTotal;
	}
	
	public String getNomeVencedor() {
		Jogador vencedor;
		EstadoDoJogador estado = jogador.getEstado();
		if (estado == EstadoDoJogador.DESISTIU)
			vencedor = adversario;
		else {
			estado = adversario.getEstado();
			if (estado == EstadoDoJogador.DESISTIU)
				vencedor = jogador;
			else {
				double pontuacaoJogador = getPontuacaoTotalJogador();
				double pontuacaoAdversario = getPontuacaoTotalAdversario();
				if (pontuacaoJogador >= pontuacaoAdversario)//Double.compare(pontuacaoJogadorUm, pontuacaoJogadorDois) > 1)
					vencedor = jogador;
				else
					vencedor = adversario;
			}
		}		
		String nomeVencedor = vencedor.getNome();
		return nomeVencedor;
	}

	public void armazenaResultado(Resultado resultado) {	
		gerenciadorDeResultados.adicionarResultado(resultado);
	}

	public Nivel proximoNivel(GeneroMusical generoMusical) {
		int numeroNivel;
		if (nivelJogador != null) {
			numeroNivel = nivelJogador.getNumero();
			numeroNivel++;
		}
		else
			numeroNivel = 0;
		nivelJogador = new Nivel(numeroNivel, generoMusical, geradorDeQuestoes);
		return nivelJogador;
	}

	public List<GeneroMusical> getGenerosJaEscolhidosJogador() {
		return generosJaEscolhidosJogador;
	}

	public GeneroMusical getGeneroEscolhido(int indiceGenero) {		
		GeneroMusical generos[] = GeneroMusical.values();
		GeneroMusical generoEscolhido = generos[indiceGenero];
		generosJaEscolhidosJogador.add(generoEscolhido);
		return generoEscolhido;
	}

	private boolean isUltimoNivelDoJogo() {
		int numeroNivel = nivelJogador.getNumero();
		return numeroNivel == 4;
	}
	
	private boolean isUltimaRodadaDoJogo() {
		boolean ultimaRodada = false;		
		boolean ultimoNivel = isUltimoNivelDoJogo();
		if (ultimoNivel)
			ultimaRodada = nivelJogador.isUltimaRodada();						
		return ultimaRodada;
	}

	public void reiniciarJogo() {
		nivelJogador = null;
		gerenciadorDeResultados.zerarResultados();
		gerenciadorDeResultados.zerarPontuacoes();
		generosJaEscolhidosJogador.clear();
	}

	public void tocarMusica(int idMusica) throws FileNotFoundException {
		tocadorDeMusica.tocarMusica(idMusica);
	}
	
	public void pararMusica() {
		if (tocadorDeMusica.isMusicaTocando())
			tocadorDeMusica.pararMusica();
	}

	public boolean isAdversarioPronto() {
		boolean adversarioPronto;
		EstadoDoJogador estado = adversario.getEstado();
		if (estado == EstadoDoJogador.DESISTIU)
			adversarioPronto = true;
		else {
			int idJogador = adversario.getId();
			Resultado[][] resultadosAdversario = gerenciadorDeResultados.getResultadosJogador(idJogador);
			int numeroNivel = nivelJogador.getNumero();		
			Rodada rodadaAtual = nivelJogador.getRodadaAtual();
			int numeroRodada = rodadaAtual.getNumero();
			adversarioPronto = resultadosAdversario[numeroNivel][numeroRodada] != null;
		}
		return adversarioPronto;
	}
}