import java.io.*;


public class Tabuleiro {
 
	private Posicao [] posicoes;
	 
	private Nivel nivel;
	
	private Posicao posicaoAnteriorSelecionada;
	
	private Posicao posicaoSelecionada;
	 
	private boolean primeiroClick;
	 
	private int numeroJogadas = 0;
	 
	private Recordes recordes;
	
	public Tabuleiro(){
	//	this.criarPadroes();
		nivel = new Nivel();
		nivel = (Nivel) this.recuperarObjeto(nivel.getCaminhoArquivoNivel());
		nivel.setNivelDiferente(false);
		this.embaralharPares();
		primeiroClick = true;
	}

	public void embaralharPares() {
		recordes = null;
		numeroJogadas = 0;
		primeiroClick = true;
		int [] numeroPares2 = nivel.getInformacoes();
		int numeroPares = 2*numeroPares2[0];
		int[] ordemFiguras = new int[numeroPares];
		for (int i = 0; i < ordemFiguras.length; i++) {
			ordemFiguras[i] = (int) (Math.random() * ( ordemFiguras.length ));
			for (int k = (i - 1); k >= 0; k--) {
				if (ordemFiguras[i] == ordemFiguras[k]) {
						i = i - 1;
				}
			}
		}
		posicoes = new Posicao[numeroPares];
		for(int counter = 0; counter < posicoes.length; counter++){
			posicoes[counter] = new Posicao(ordemFiguras[counter]);
		}
		for(int counter = 0; counter < posicoes.length; counter++){
			if (ordemFiguras[counter]%2 == 0){
				for (int counter2 = 0; counter2 < ordemFiguras.length;counter2++){
					if (ordemFiguras[counter] == (ordemFiguras[counter2]-1)){
						posicoes[counter].setPar(posicoes[counter2].getCarta());
					}
				}		
			}else{
				for (int counter2 = 0; counter2 < ordemFiguras.length;counter2++){
					if (ordemFiguras[counter] == ordemFiguras[counter2]+1){
						posicoes[counter].setPar(posicoes[counter2].getCarta());
					}
				}	
			}
		}
		
	}
	
	 
	public boolean verificarFimdeJogo() {
		boolean fimJogo;
		for ( int x = 0; x < posicoes.length; x++ ){
			if ( !posicoes[x].getVirada() ){
				return (fimJogo = false);
			}
		}
		fimJogo = true;
		return fimJogo;
	}
	 
	public boolean tratarFimdeJogo() {
		if ( recordes == null){
			recordes = new Recordes(nivel.getNivel());
			String caminhoRecordes = recordes.getCaminhoArquivoRecordes();
			recordes = (Recordes) recuperarObjeto(caminhoRecordes);
		}
		if ( recordes.verificarseFoiRecorde(numeroJogadas) ) {
			return true;
		}else{
			return false;
		}
	}
	 
	 
	/**
	 *Metodo para desserializar algum objeto
	 */
	private Object recuperarObjeto(String caminho) {
		try{
			File objFile = new File(caminho);
			Object objeto;
			if ( objFile.exists() ) {
				FileInputStream objFileIS = new FileInputStream(caminho);
				ObjectInputStream objIS = new ObjectInputStream(objFileIS);
				objeto = objIS.readObject();
				return objeto;
			}
			
		}catch(Exception ex){
			System.out.println("Erro ao recuperar Recordes   " + ex.getLocalizedMessage());
		}
		return null;
	}
	 
	/**
	 *Metodo usado para serializar objetos passados como parametros junto com seu caminho
	 *(Primeiro caminho e depois o objeto)
	 */
	private void serializarObjeto(String caminho, Object objeto) {
		try{
			FileOutputStream objFileOS = new FileOutputStream(caminho);
			ObjectOutputStream gravarObjeto = new ObjectOutputStream(objFileOS);
			gravarObjeto.writeObject(objeto);
			gravarObjeto.flush();
			gravarObjeto.close();
			} catch (Exception ex){ 
				System.out.println("Erro ao gravar arquivo"+ex);
			}
	}
	 

	public void alterarNivel(int novoNivel) {
		nivel.setNovoNivel(novoNivel);
		serializarObjeto(nivel.getCaminhoArquivoNivel(), nivel);
		nivel.setNivelDiferente(true);
	}
	 
	public String verRecordes() {
		if ( recordes == null){
			recordes = new Recordes(nivel.getNivel());
			String caminhoRecordes = recordes.getCaminhoArquivoRecordes();
			recordes = (Recordes) recuperarObjeto(caminhoRecordes);
		}
		String listaRecordes = recordes.getListaRecordistas();
		return listaRecordes;
	}
	 
	/**
	 *Ve se o nivel escolhido nas opções é diferente do atual
	 */
	public boolean verificaNivelDiferenteAtual(int novoNivel) {
		if ( nivel.getNivel() == novoNivel ){
			return false;
		} else {
			return true;
		}
	}
	 
	/**
	 *Classe para ver se o par esta correto e
	 *setar variaveis
	 */
	public boolean verificaParCerto(int posicaoClick) {
		numeroJogadas++;
		primeiroClick = true;
		if  ( posicaoSelecionada.getPar() == posicaoAnteriorSelecionada.getCarta() ){
			return true;
		} else {
			posicaoSelecionada.setVirada(false);
			posicaoAnteriorSelecionada.setVirada(false);
			return false;
		}
	}
	 
	/**
	 *Verificar se a jogada é valida
	 */
	public boolean verificaJogada(int posicaoClick) {
		boolean virada = posicoes[posicaoClick].getVirada();
		if (!virada){
			posicaoSelecionada = posicoes[posicaoClick];
			return true;
		} else{			
			return false;
		}
	}
	
	//Retorna em ordem: Caminho da Figura, Dica e Texto
	public String[] getInfomacoesCarta() {
		String [] temp = (posicaoSelecionada.getInformacoesCartaPOSICAO());
		String [] teste = new String [2];
		teste[0] = temp[0];
		teste[1] = this.lerArquivo(temp[1]);
		return teste;
		
	}
	 
	private String lerArquivo(String caminhoArquivo) {
		String texto = "";
		try {
			FileInputStream arquivoTexto = new FileInputStream(caminhoArquivo);
			int caracterlido = arquivoTexto.read();
			while (caracterlido != -1) {
				texto += ((char) caracterlido);
				caracterlido = arquivoTexto.read();
			}
			arquivoTexto.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado");
		} catch (IOException ex) {
			System.err.println("Erro na leitura do arquivo");
		}
		return texto;
	}
	 
	public void trataPrimeiroClick(int posicaoClick) {
		posicaoAnteriorSelecionada = posicaoSelecionada;
		posicaoAnteriorSelecionada.setVirada(true);
		primeiroClick = false;
	}
	 
	public boolean getPrimeiroClick() {
		return primeiroClick;
	}
	 
	 
	public int[] getPosicaoDoisClicks() {
		int posicoes2 [] = new int[2];
		for ( int counter = 0; counter < posicoes.length; counter++){
			if (posicaoSelecionada == posicoes[counter]){
				posicoes2[0] = counter;
			}
			if (posicaoAnteriorSelecionada == posicoes[counter]){
				posicoes2[1] = counter;
			}
		}
		return posicoes2;
	}

	public String [] parCerto(){		
		String [] informacoes;
		String [] informacoes2;
		String [] informacoesFim = new String[5];
		posicaoSelecionada.setVirada(true);
		informacoes = getInformacoesCarta(posicaoSelecionada);
		informacoes2 = getInformacoesCarta(posicaoAnteriorSelecionada);
		informacoesFim[0] = informacoes[0];
		informacoesFim[1] = informacoes2[0];
		informacoesFim[2] = informacoes[2];
		informacoesFim[3] = informacoes[3];
		informacoesFim[4] = informacoes2[3];
		
		return informacoesFim;
	}

	private String[] getInformacoesCarta(Posicao posicao) {
		String [] temp = posicao.getInformacoesCartaPOSICAO();
		temp[1] = lerArquivo(temp[1]);
		temp[2] = lerArquivo(temp[2]);
		temp[3] = lerArquivo(temp[3]);
		return temp;
	}

	public void recorde(String nome) {
		recordes.gravarRecordista(nome, numeroJogadas);
		String caminho = recordes.getCaminhoArquivoRecordes();
		this.serializarObjeto(caminho, recordes);
	}
	public int[] getNivel(){
		return nivel.getInformacoes();
	}

	public boolean getNivelDiferente() {
		boolean temp;
		temp = nivel.getNivelDiferente();
		nivel.setNivelDiferente(false);
		return temp;
	}
	
	private void criarPadroes(){
		Nivel nivel = new Nivel(1);
		this.serializarObjeto(nivel.getCaminhoArquivoNivel(), nivel);
		Recordes recorde1 = new Recordes(1);
		Recordes recorde2 = new Recordes(2);
		this.serializarObjeto(recorde1.getCaminhoArquivoRecordes(), recorde1);
		this.serializarObjeto(recorde2.getCaminhoArquivoRecordes(), recorde2);		
	}
}
 
