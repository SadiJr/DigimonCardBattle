package settlers.limite.paineis;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import settlers.jogadas.JogadaIniciarTabuleiro;
import settlers.limite.AtorJogador;
import settlers.limite.peoes.PeaoCidade;
import settlers.limite.peoes.PeaoColonia;
import settlers.limite.peoes.PeaoEstrada;
import settlers.limite.peoes.PeaoLadrao;
import settlers.limite.peoes.PeaoVila;
import settlers.limite.posicoes.PosicaoAresta;
import settlers.limite.posicoes.PosicaoHexagono;
import settlers.limite.posicoes.PosicaoOceano;
import settlers.limite.posicoes.PosicaoPorto;
import settlers.limite.posicoes.PosicaoTerreno;
import settlers.limite.posicoes.PosicaoVertice;

/**
 * 
 * @author Murilo Fernando de Oliveira
 * @author Paulo Rogério de Pinho Filho
 * 
 */
public class PainelTabuleiro extends JPanel {

	private static final long serialVersionUID = 1L;
	
    private int aresta;
    private int camadas;
    private int distancia;
    
    private PeaoLadrao ladrao;
    
    private PainelDados painelDados;
    private PainelPeoes painelPeoes;
    private PainelAcoes painelAcoes;
    private PainelJogadores painelJogadores;
    private PainelLances painelLances;
    private PainelCartas painelCartas;
    
    private JLabel lPeoesRestantes;
    
	private PosicaoHexagono hexagonos[][];
  
    private ArrayList<PosicaoAresta> posicoesAresta;
    private ArrayList<PosicaoVertice> posicoesVertice;

    private ArrayList<PosicaoPorto> portos;
    private ArrayList<PeaoColonia> colonias;
    private ArrayList<PeaoEstrada> estradas;
    
    private ImageIcon textura;
    
    private AtorJogador atorJogador;

    private int width;
    private int height;
    
    public PainelTabuleiro(AtorJogador atorJogador, int aresta, int camadas, int distancia) {
    	this.posicoesAresta =  new ArrayList<PosicaoAresta>();
    	this.posicoesVertice =  new ArrayList<PosicaoVertice>();
    	this.atorJogador = atorJogador;
        this.aresta = aresta;
        this.camadas = camadas;
        this.distancia = camadas > 1 ? distancia : 0;
        this.textura = new ImageIcon(ClassLoader.getSystemResource("oceano.jpg"));

        inicializarInterface();
        inicializarPaineis();

        ladrao = new PeaoLadrao();

        colonias = new ArrayList<PeaoColonia>();
        estradas = new ArrayList<PeaoEstrada>();
        portos = new ArrayList<PosicaoPorto>();

        instanciarEstruturaHexagonos();
        gerarEstruturaHexagonos();
    }
    
	private void inicializarInterface() {
		// Calcula largura e altura do tabuleiro
        width = (int) Math.round(aresta / 2.0 * 3.0 + distancia + 1) * (camadas * 2 + 1);
        height = (int) (Math.round(aresta * Math.sqrt(3) + 1) * (camadas * 2)) + (distancia * (camadas * 2 - 2)) + aresta;

        setLayout(null);
        setSize(width, height);
	}
	
	private void inicializarPaineis() {
        // Painel Peões
        painelPeoes = new PainelPeoes();
        painelPeoes.setLocation(30, 30);
        add(painelPeoes);
        lPeoesRestantes = new JLabel("Peças Restantes");
        lPeoesRestantes.setFont(getFont().deriveFont(9f));
        lPeoesRestantes.setForeground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        lPeoesRestantes.setHorizontalAlignment(JLabel.CENTER);
        lPeoesRestantes.setSize(painelPeoes.getWidth(), 15);
        lPeoesRestantes.setLocation(painelPeoes.getX(), painelPeoes.getY() + painelPeoes.getHeight());
        add(lPeoesRestantes);
        // Painel Dados
        painelDados = new PainelDados();
        painelDados.setLocation(getWidth() - painelDados.getWidth() - 40, 20);
        add(painelDados);
		// Painel Jogadores
		painelJogadores = new PainelJogadores();
		painelJogadores.setLocation(getWidth() - 9, 20);
		add(painelJogadores);
		// Painel Lances
		painelLances = new PainelLances(atorJogador);
		painelLances.setLocation(getWidth() - 9, 10 + painelJogadores.getHeight() + painelJogadores.getY());
		add(painelLances);
		// Painel Cartas
		painelCartas = new PainelCartas(atorJogador);
		painelCartas.setLocation(getWidth() - 9, 10 + painelLances.getHeight() + painelLances.getY());
		add(painelCartas);
		// Painel Acoes
		painelAcoes = new PainelAcoes();
		painelAcoes.setLocation(getWidth() - 9, 10 + painelCartas.getHeight() + painelCartas.getY());
		add(painelAcoes);
	}
	
	private void instanciarEstruturaHexagonos() {
		// Instancia array camadas
		hexagonos = new PosicaoHexagono[camadas + 1][];
		// Instancia array camada 0
		hexagonos[0] = new PosicaoHexagono[1];
		// Instancia array demais camadas
		for (int i = 1; i <= camadas; i++) {
			hexagonos[i] = new PosicaoHexagono[i * 6];
		}
	}

    private void gerarEstruturaHexagonos() {
        TrataCliqueTerreno trataCliqueTerreno = new TrataCliqueTerreno();
        // Camada Central
        hexagonos[0][0] = new PosicaoTerreno(this.aresta, this, 0, 0);
        hexagonos[0][0].addMouseListener(trataCliqueTerreno);
        // Demais Camadas
        for (int camada = 1; camada <= camadas; camada++) {
        	int identificador = 0;
            for (int lado = 0; lado < 6; lado++) {
                PosicaoHexagono terreno = hexagonos[0][0].getHexagonoMaisDistante(lado);
                for (int i = 0; i < camada; i++) {
                	int ladoHexatono = (i == 0) ? lado : ((lado + 3) % 6) - 1;
                    if (camada == camadas) {
                        terreno = new PosicaoOceano(terreno, ladoHexatono, this.distancia, camada, identificador);
                        if (identificador % 2 == 0) {
                            ((PosicaoOceano) terreno).setPorto(0);
                            portos.add(((PosicaoOceano) terreno).getPorto());
                        }
                    } else {
                        terreno = new PosicaoTerreno((PosicaoTerreno) terreno, ladoHexatono, this.distancia, camada, identificador);
                        terreno.addMouseListener(trataCliqueTerreno);
                    }
                    hexagonos[camada][identificador] = terreno;
    				identificador++;
                }
            }
        }
        // Adiciona hexágonos ao tabuleiro
        for (int camada = 0; camada < hexagonos.length; camada++) {
        	for (int identificador = 0; identificador < hexagonos[camada].length; identificador++) {
        		add(hexagonos[camada][identificador], 0);
        	}
        }
    }
	
	public void registrarPosicaoAresta(PosicaoAresta posicaoAresta) {
		posicoesAresta.add(posicaoAresta);		
	}
	
	public void registrarPosicaoVertice(PosicaoVertice posicaoVertice) {
		posicoesVertice.add(posicaoVertice);
	}
  
    public void desabilitarLances() {
    	painelLances.desabilitarLances();
    }
	
	public void habilitarRolarDados() {
		painelLances.habilitarRolarDados();
	}
	
	public void habilitarUsarCarta() {
		painelLances.habilitarUsarCarta();
	}
	
	public void habilitarOferecerTroca() {
		painelLances.habilitarOferecerTroca();
	}
	
	public void habilitarConstruirEstrada() {
		painelLances.habilitarConstruirEstrada();
	}
	
	public void habilitarConstruirVila() {
		painelLances.habilitarConstruirVila();
	}
	
	public void habilitarConstruirCidade() {
		painelLances.habilitarConstruirCidade();
	}
	
	public void habilitarComprarCarta() {
		painelLances.habilitarComprarCarta();
	}
	
	public void habilitarTrocaMaritima() {
		painelLances.habilitarTrocaMaritma();
	}
	
	public void habilitarCancelar() {
		painelLances.habilitarCancelar();
	}
	
	public void habilitarPassarVez() {
		painelLances.habilitarPassarVez();
	}
	   
    public void atualizarPainelJogadoresNome(int jogador, String nome) {
    	painelJogadores.setJogador(jogador, nome);    	
    }
    
    public void atualizarPainelJogadoresPontos(int jogador, int pontos) {
    	painelJogadores.setPontos(jogador, pontos);    	
    }
    
    public void atualizarPainelJogadoresPontosVitoria(int jogador, int pontos) {
    	painelJogadores.setPontosVitoria(jogador, pontos);    	
    }
    
    public void atualizarPainelJogadoresRecursos(int jogador, int quantidade) {
    	painelJogadores.setRecursos(jogador, quantidade);    	
    }
    
    public void atualizarPainelJogadoresCartas(int jogador, int quantidade) {
    	painelJogadores.setCartas(jogador, quantidade);    	
    }
    
    public void atualizarPainelJogadoresSoldados(int jogador, int quantidade) {
    	painelJogadores.setSoldados(jogador, quantidade);    	
    }
    
    public void atualizarPainelJogadoresMaiorEstrada(int jogador, int tamanho) {
    	painelJogadores.setMaiorEstrada(jogador, tamanho);    	
    }
    
	public void atualizarPainelJogadoresDaVez(int jogador) {
		painelJogadores.setFlag(jogador);
	}
	
	public void inicializarPainelCartas() {
		painelCartas.inicializarPainelCartas();
	}
	
	public void atualizarPainelCartasRecursos(int tipo, int valor) {
		painelCartas.atualizarRecurso(tipo, valor);
	}
	
	public void atualizarPainelCartasCartas(int tipo, int valor) {
		painelCartas.atualizarCarta(tipo, valor);
	}
	
	public void ativarCarta(int tipo) {
		painelCartas.ativarCarta(tipo);
	}
	
	public void ativarRecursos() {
		painelCartas.ativarRecursos();
	}
	
	public void desativarCartas() {
		painelCartas.desativarCartas();
	}

    public void atualizarTabuleiro(JogadaIniciarTabuleiro jogadaTabuleiro) {
    	removerPeoes();
    	for (int camada = 0; camada < jogadaTabuleiro.getCamadas(); camada++) {
    		for (int identificador = 0; identificador < jogadaTabuleiro.getTamanhoCamada(camada); identificador++) {
    			((PosicaoTerreno) hexagonos[camada][identificador]).setTipo(jogadaTabuleiro.getTerreno(camada, identificador));
    			((PosicaoTerreno) hexagonos[camada][identificador]).setNumero(jogadaTabuleiro.getNumero(camada, identificador));
    		}
    	}
    	ladrao.setTerreno((PosicaoTerreno) hexagonos[jogadaTabuleiro.getCamadaLadrao()][jogadaTabuleiro.getIdentificadorLadrao()]);
    	for (int i = 0; i < jogadaTabuleiro.getQuantidadePortos(); i++) {
    		portos.get(i).setRecurso(jogadaTabuleiro.getPorto(i));
    	}
    }
    
    private void removerPeoes() {
    	for (int i = 0; i < colonias.size(); i++) {
    		colonias.get(i).remover();
    	}
    	colonias = new ArrayList<PeaoColonia>();
    	for (int i = 0; i < estradas.size(); i++) {
    		estradas.get(i).remover();
    	}
    	estradas = new ArrayList<PeaoEstrada>();
    	repaint();
    }
    
    public void iniciarPainelPeoes(int jogador, int estradas, int vilas, int cidades) {
    	atualizarPainelPeoesEstradas(estradas);
    	atualizarPainelPeoesVilas(vilas);
    	atualizarPainelPeoesCidades(cidades);
    	painelPeoes.setJogador(jogador);
    	painelPeoes.setVisible(true);
    }
    
    public void atualizarPainelPeoesEstradas(int estradas) {
    	painelPeoes.setEstradas(estradas);
    }
    
    public void atualizarPainelPeoesVilas(int vilas) {
    	painelPeoes.setVilas(vilas);
    }
    
    public void atualizarPainelPeoesCidades(int cidades) {
    	painelPeoes.setCidades(cidades);
    }

    public ArrayList<PeaoColonia> getColonias() {
        return colonias;
    }

    public ArrayList<PeaoEstrada> getEstradas() {
        return estradas;
    }

    public void ativarPosicoesAresta(ArrayList<Integer> posicoes) {
    	for (int i = 0; i < posicoes.size(); i++) {
    		posicoesAresta.get(posicoes.get(i)).setAtivo(true);
    	}
    }
    
    public void desativarPosicoesAresta() {
    	for (int i = 0; i < posicoesAresta.size(); i++) {
    		posicoesAresta.get(i).setAtivo(false);
    	}
    }

    public void ativarPosicoesVertice(ArrayList<Integer> posicoes) {
    	for (int i = 0; i < posicoes.size(); i++) {
    		posicoesVertice.get(posicoes.get(i)).setAtivo(true);
    	}
    }
    
    public void desativarPosicoesVertice() {
    	for (int i = 0; i < posicoesVertice.size(); i++) {
    		posicoesVertice.get(i).setAtivo(false);
    	}
    }
    
    public void ativarPeoesVila(ArrayList<Integer> posicoes) {
    	for (int i = 0; i < posicoes.size(); i++) {
    		posicoesVertice.get(posicoes.get(i)).getColonia().setAtivo(true);
    	}
    }
    
    public void desativarPeoesVila() {
    	for (int i = 0; i < posicoesVertice.size(); i++) {
    		if (posicoesVertice.get(i).getColonia() != null) {
    			posicoesVertice.get(i).getColonia().setAtivo(false);
    		}
    	}
    }
    
    public void ativarPosicoesTerreno(int camadaLadrao, int identificadorLadrao) {
    	for (int camada = 0; camada < camadas; camada++) {
    		for (int id = 0; id < hexagonos[camada].length; id++) {
    			if (!(camada == camadaLadrao && id == identificadorLadrao)) {
    				hexagonos[camada][id].setAtivo(true);
    			}
    		}
    	}
    }
    
    public void desativarPosicoesTerreno() {
    	for (int camada = 0; camada < camadas; camada++) {
    		for (int id = 0; id < hexagonos[camada].length; id++) {
				hexagonos[camada][id].setAtivo(false);
    		}
    	}
    }
    
    public void ativarLadrao() {
    	ladrao.setAtivo(true);
    }
    
    public void moverLadrao(int camada, int identificador) {
        ladrao.setTerreno((PosicaoTerreno) hexagonos[camada][identificador]);
        desativarPosicoesTerreno();
    }
    
    public void colocarPeaoVila(PosicaoVertice vertice) {
    	atorJogador.finalizarConstruirVila(vertice.getId());
    }
    
    public void colocarPeaoVila(int vertice, int jogador) {
    	colonias.add(new PeaoVila(jogador, posicoesVertice.get(vertice)));
    }
    
    public void colocarPeaoCidade(PosicaoVertice vertice) {
    	atorJogador.finalizarConstruirCidade(vertice.getId());
    }
    
    public void colocarPeaoCidade(int vertice, int jogador) {
    	posicoesVertice.get(vertice).getColonia().remover();
    	colonias.add(new PeaoCidade(jogador, posicoesVertice.get(vertice)));
    }

    public void colocarEstrada(PosicaoAresta aresta) {
    	atorJogador.finalizarConstruirEstrada(aresta.getIdentificador());
    }
    
    public void colocarEstrada(int aresta, int jogador) {
        estradas.add(new PeaoEstrada(jogador, posicoesAresta.get(aresta)));
    }

    public void setValorDados(int valor1, int valor2) {
    	painelDados.setValorDados(valor1, valor2);
    }
    
    public void destacarTerrenos(int terrenos[][]) {
		for (int camada = 0; camada < camadas; camada++) {
			for (int i = 0; i < terrenos[camada].length; i++) {
				if (terrenos[camada][i] > 0) {
					hexagonos[camada][i].destacar();
				}
			}
		}
    }
	
	public void registrarAcao(int jogador, String texto) {
		painelAcoes.registrarAcao(jogador, texto);
	}
	
	public void limparPainelAcoes() {
		painelAcoes.limparPainelAcoes();
	}
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int x = 0; x < getWidth(); x += textura.getIconWidth()) {
            for (int y = 0; y < getHeight(); y += textura.getIconHeight()) {
            	g2.drawImage(textura.getImage(), x, y, this);
            }
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        float[] dist = {0.4f, 1.0f};
        Color[] colors = {Color.white, Color.blue.darker().darker()};
        g2.setPaint(new RadialGradientPaint(new Point2D.Float(width / 2, height / 2), width / 2, dist, colors));
        g2.fillRect(0, 0, atorJogador.getWidth(), atorJogador.getHeight());
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(2.0f));
        g2.drawRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 20, 20);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new BasicStroke(1.0f));
    }

    private class TrataCliqueTerreno extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent event) {
            PosicaoTerreno terreno = (PosicaoTerreno) event.getSource();
            if (terreno.isAtivo()) {
            	atorJogador.finalizarMoverLadrao(terreno.getCamada(), terreno.getId());
            }
        }
        
    }

}
