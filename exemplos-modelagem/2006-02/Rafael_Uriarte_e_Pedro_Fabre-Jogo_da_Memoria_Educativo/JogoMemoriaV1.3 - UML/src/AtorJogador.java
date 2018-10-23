import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AtorJogador {

	private Tabuleiro tabuleiro;
	
	private LimitePrincipal limite;
		
	public AtorJogador(){
		tabuleiro = new Tabuleiro();
		limite = new LimitePrincipal(tabuleiro.getNivel(),this);
	}
	 
	/**
	 * Retorna o nome do jogador para colocar nos Recordes
	 * 
	 */
	public String mensagemPedirNome() {
		String nome = JOptionPane.showInputDialog("Você bateu um Recorde. Digite seu nome para fazer parte da lista de recordistas");
		return nome;
	}
	 
	public void mensagemparErrado() {
		Icon sadface = new ImageIcon("imagens/sadface");
		JOptionPane.showMessageDialog(null, "Você errou o par.", ":(",JOptionPane.PLAIN_MESSAGE,sadface);
	}
	 
	public void mensagemParCerto(String [] caminhosFiguras, String textoParCerto, String [] nomes) {
		LimiteCerto par = new LimiteCerto(caminhosFiguras, textoParCerto, nomes);
	}
	 
	public void mensagemNaoRecordes() {
		JOptionPane.showMessageDialog(null,"PARABENS! Voce acertou todos os pares mas nao conseguiu ir para os Recordes", "PARABENS",JOptionPane.PLAIN_MESSAGE);
	}
	 
	/**
	 * Mensagem para confirmar a escolha de nivel feita pelo usuário
	 * 
	 */
	public boolean mensagemConfirmarNovoJogo(String texto) {
		Object[] options = { "Sim", "Cancelar" };
		int x = JOptionPane.showOptionDialog(null, texto, "Aviso", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
		if (x == 0){
			return true;
		}else{
			return false;
		}
	}
	 
	public void mensagemMostraRecordes(String recordistas) {
		Icon trofeu = new ImageIcon("sistema/FiguraPadrao/trofeu");
		JOptionPane.showMessageDialog(null, recordistas+"\n\n", "Recordes",JOptionPane.PLAIN_MESSAGE,trofeu);
	}
	
	 
	public void telaRecordes() {
		String textoRecordes = tabuleiro.verRecordes();
		this.mensagemMostraRecordes(textoRecordes);
	}
	
	 
	public void telaNivel() {
		LimiteNivel x = new LimiteNivel(this);
	}
	
	
	public void setarNivel(int nivel) {
		boolean test = tabuleiro.verificaNivelDiferenteAtual(nivel);
		if ( test ){
			tabuleiro.alterarNivel(nivel);
			test = this.mensagemConfirmarNovoJogo("As modificacoes serao efetuadas assim que voce iniciar um novo jogo. Deseja iniciar o novo jogo agora?");
			if ( test ){
				this.novoJogo(true);
			}
		}
		
	}
	
	public void jogada(int posicaoClick) {
		boolean test = tabuleiro.verificaJogada(posicaoClick);
		if ( test ){
			this.virarCarta(posicaoClick);
			this.tratarClickValido(posicaoClick);
		}
		
	}
	 
	public void virarCarta(int posicaoClick) {
		String [] temp = tabuleiro.getInfomacoesCarta(); 
		limite.virarFigura(posicaoClick, temp);
	}
	 
	public void tratarClickValido(int posicao) {
		boolean primeiroClick = tabuleiro.getPrimeiroClick();
		if (primeiroClick){
			tabuleiro.trataPrimeiroClick(posicao);
		}else {
			boolean parCerto = tabuleiro.verificaParCerto(posicao);
				if (parCerto){
					String [] informacoes = tabuleiro.parCerto(); 
					String  texto = informacoes[2];
					String [] nomes = new String[2];
					String [] caminhos = new String[2];
					nomes[0] = informacoes[3];
					nomes[1] = informacoes[4];
					caminhos[0] = informacoes[0];
					caminhos[1] = informacoes[1];
					this.mensagemParCerto(caminhos,texto,nomes);
					boolean fimdeJogo = tabuleiro.verificarFimdeJogo();
					if (fimdeJogo){
						this.fimJogo();
					}
				}else{
					this.mensagemparErrado();
					int[] figuras = tabuleiro.getPosicaoDoisClicks();
					this.desvirarCartas(figuras[0], figuras[1]);
				}
				
		}
	}
	 
	public void desvirarCartas(int posicao1, int posicao2) {
		limite.desvirarFigura(posicao1, posicao2);
		
	}
	 
	public void fimJogo() {
		boolean foiRecorde = tabuleiro.tratarFimdeJogo();
		if (foiRecorde){
			String nome = this.mensagemPedirNome();
			tabuleiro.recorde(nome);
			this.telaRecordes();
		}else  {
			this.mensagemNaoRecordes();
		}
	}

	public void novoJogo(boolean ehNivel) {
		if (!ehNivel){
			if (this.mensagemConfirmarNovoJogo("Você tem certeza que deseja inicia um novo Jogo?")){
				boolean nivelDiferente = tabuleiro.getNivelDiferente();
				if (nivelDiferente){
					tabuleiro.embaralharPares();
					limite.esconder();
					limite = new LimitePrincipal(tabuleiro.getNivel(),this);
				}else{
					tabuleiro.embaralharPares();
					limite.resetarInterface(tabuleiro.getNivel()[0]*2);
				}
			}
		}else{
			tabuleiro.embaralharPares();
			limite.esconder();
			limite = new LimitePrincipal(tabuleiro.getNivel(),this);
		}
	}
	
	//public void novoJogoNivel() {
//				tabuleiro.embaralharPares();
//				limite.esconder();
	//			limite = new LimitePrincipal(tabuleiro.getNivel(),this);
	//	}	
}

