package interfaceGrafica;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import rede.Resultado;
import logica.AtorJogador;
import logica.GeneroMusical;

public class InterfaceGraficaSongPop extends JFrame {
	private static final long serialVersionUID = 1L;
	protected AtorJogador atorJogador;
	protected PainelSongPop painel;

	public InterfaceGraficaSongPop() {
		atorJogador = new AtorJogador(this);
		paginaInicial();
		setTitle("Song Pop");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setPreferredSize(new Dimension(700, 470));
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void conectar(String nomeJogador) {
		boolean exitoConexao = atorJogador.conectar(nomeJogador);
		if (exitoConexao)
			notificarConectado();
		else
			notificarErroConexao();
	}

	public void responder(int alternativaRespondida, double tempoDaContagem) {
		int alternativaCorreta = atorJogador.responder(alternativaRespondida, tempoDaContagem);
		PainelDeJogo p = (PainelDeJogo)painel;
		p.mostrarAlternativaCorreta(alternativaCorreta);
		p.habilitarOpcaoContinuar();
	}

	public void desabilitarOpcaoJogarNovamente() {
		PainelDeResultadosFinais p = (PainelDeResultadosFinais)painel;
		p.desabilitarOpcaoJogarNovamente();
	}
	
	public void desabilitarOpcaoDesistir() {
		PainelDeResultadosParciais p = (PainelDeResultadosParciais)painel;
		p.desabilitarOpcaoDesistir();
	}

	public void habilitarOpcaoContinuar() {
		PainelDeResultadosParciais p = (PainelDeResultadosParciais)painel;
		p.habilitarOpcaoContinuar();
	}

	public boolean pedirConfirmacaoDesconexao() {
		int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja desconectar do jogo?", 
													 "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); 
		boolean confirmado = resposta == JOptionPane.YES_OPTION;
		return confirmado;
	}

	public boolean pedirConfirmacaoDesistencia() {
		int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja desistir?", 
				 									 "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); 
		boolean confirmado = resposta == JOptionPane.YES_OPTION;
		return confirmado;
	}

	public void desistirDoJogo() {
		atorJogador.desistirDoJogo();		
	}

	public void jogarNovamente() {
		atorJogador.jogarNovamente();
	}

	public void visualizarResultados() {
		atorJogador.visualizarResultados(false);
	}

	public boolean desconectar() {
		boolean confirmado = atorJogador.desconectar();
		return confirmado;
	}

	public void iniciarRodada() {
		atorJogador.iniciarRodada();
	}

	public void mostrarAlternativas(String[] alternativas) {
		PainelDeJogo p = (PainelDeJogo)painel;
		p.mostrarAlternativas(alternativas);
	}

	public void mostrarComplementoPergunta(String complementoPergunta) {
		PainelDeJogo p = (PainelDeJogo)painel;
		p.mostrarComplementoPergunta(complementoPergunta);
	}

	public void iniciarContagemRegressiva() {
		PainelDeJogo p = (PainelDeJogo)painel;
		p.iniciarContagemRegressiva();
	}
	
	public void atualizaResultadosAdversario(Resultado resultado, double pontuacaoTotalAdversario) {
		PainelDeResultadosParciais painelResultados = (PainelDeResultadosParciais)painel;
		painelResultados.atualizaResultadosAdversario(resultado, pontuacaoTotalAdversario);
		painelResultados.habilitarOpcaoContinuar();	
	}
	
	public String obterIdServidor() {
		String servidor = JOptionPane.showInputDialog("Digite o ip do servidor que deseja conectar:");
		return servidor;
	}

	public void continuar() {
		atorJogador.continuar();
	}

	public void escolherGenero(int indiceGenero) {
		atorJogador.escolherGenero(indiceGenero);
	}
	
	public void notificarErroConexao() {
		JOptionPane.showMessageDialog(this, "Ocorreu um erro na conexão.");
	}

	public void notificarNomeInvalido() {
		JOptionPane.showMessageDialog(this, "O campo nome deve ser preenchido.");
	}
	
	public void notificarConectado() {
		JOptionPane.showMessageDialog(this, "Conexão realizada com sucesso.");	
	}
	
	public void notificarFalhaDesconexao() {
		JOptionPane.showMessageDialog(this, "Ocorreu um erro ao tentar desconectar.");
	}

	public void notificarIniciandoNovoJogo() {
		JOptionPane.showMessageDialog(this, "Um novo jogo está sendo iniciado com o mesmo adversário.");
	}

	public void notificarRecusaRevanche() {
		JOptionPane.showMessageDialog(this, "Seu adversário não deseja jogar novamente com você agora.");
	}
	
	public void notificarEsperandoRespostaRevanche() {
		JOptionPane.showMessageDialog(this, "Seu adversário ainda não decidiu se deseja jogar novamente ou não.\n" +
											"Por favor, aguarde.");
	}

	public void desistirDaRodada() {
		atorJogador.desistirDaRodada();
	}

	private void atualizarPainel() {
		setContentPane(painel);
		pack();
	}
	
	public void aguardarAdversario() {
		painel = new PainelDeEspera(this);
		atualizarPainel();
	}
	
	public void iniciarJogo(String nomeJogador, String pergunta, int tempoMax, boolean rodadaMusical) {
		painel = new PainelDeJogo(this, nomeJogador, pergunta, tempoMax, rodadaMusical);
		atualizarPainel();
	}
	
	public void paginaInicial() {
		painel = new PainelDeEntrada(this);
		atualizarPainel();
	}
	
	public void escolherGenero(String nomeJogador, List<GeneroMusical> generosJaEscolhidos) {
		painel = new PainelEscolhaGenero(this, nomeJogador, generosJaEscolhidos);		
		atualizarPainel();		
	}
	
	public void visualizarResultadosParciais(String nomeJogador, String nomeAdversario,
											 Resultado[][] resultadosJogador, Resultado[][] resultadosAdversario,
											 double pontuacaoTotalJogador, double pontuacaoTotalAdversario,
											 boolean botaoContinuarAtivo, boolean botaoDesistirAtivo) {
		painel = new PainelDeResultadosParciais(this, nomeJogador, nomeAdversario, 
												resultadosJogador, resultadosAdversario,
												pontuacaoTotalJogador, pontuacaoTotalAdversario,
												botaoContinuarAtivo, botaoDesistirAtivo);		
		atualizarPainel();
	}
	
	public void visualizarResultadosFinais(String nomeJogador, String nomeAdversario,										   
										   double pontuacaoTotalJogador, double pontuacaoTotalAdversario, 
										   String nomeVencedor, boolean porDesistencia) {
		painel = new PainelDeResultadosFinais(this, nomeJogador, nomeAdversario,
				 							 pontuacaoTotalJogador, pontuacaoTotalAdversario,
				 							 nomeVencedor, porDesistencia);
		atualizarPainel();
	}

	public void notificarArquivoNaoEncontrado() {	
		JOptionPane.showMessageDialog(this, "O arquivo de áudio não foi encontrado. \n" +
											"Certifique-se de que a instalação foi realizada corretamente.");
	}
}