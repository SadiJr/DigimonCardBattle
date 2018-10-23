package jogo;

import gui.JPrincipal;

import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import util.CtrSerializacao;

/**
 * Controle geral do jogo.
 * @author Administrador
 * 
 */
public class CtrJogo {

	/**
	 * Auxilia a simular o pensamento dos jogadores IA.
	 * 
	 * @author Administrador
	 * 
	 */
	private class Pensar implements Runnable {

		public void run() {
			Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
			try {
				Thread.sleep(CtrJogo.this.velocidadeJogo);
				CtrJogo.this.jogadaPC();
			} catch (InterruptedException ex) {
				System.out.println("Erro no pensar");
			}
		}

		public void start() {
			Thread th = new Thread(this);
			th.start();
		}
	}

	/**
	 * Número de jogadores;
	 */
	public int aJogadores;

	/**
	 * Rodada atual do jogo.
	 */
	public int aRodadaAtual;

	/**
	 * Número de rodadas definido pelo servidor.
	 */
	public int aRodadas;

	public boolean cartaInicial;

	/**
	 * Conta o número de setes jogados em sequencia.
	 */
	private int contaSete;

	public CtrBaralho ctrBaralho;

	public CtrJogador ctrJogador;

	public CtrPilhaDescarte ctrPilhaDescarte;

	public CtrSerializacao ctrSerializacao;

	/**
	 * Contem as opções do jogo.
	 */
	public String[] data;
	
	/**
	 * Frase para ser mostrada ao atualizar a interface.
	 */
	public String frase;

	public JogadorVirtual jogadorVirtual;

	public JPrincipal jPrincipal;

	/**
	 * Controla o número de jogadores para se passar a vez.
	 */
	private int multiplicadorPassaVez;

	/**
	 * Controle de multiplicação da pontuação final.
	 */
	private int multiplicadorPontos;

	//private int calculaProx;
	
	/**
	 * Guarda o naipe do coringa.
	 */
	public int naipeCoringa;

	/**
	 * Controla se um 7 está em jogo.
	 */
	private boolean seteValendo;

	/**
	 * Velocidade do jogo.
	 */
	private int velocidadeJogo;

	public CtrJogo() {
		this.ctrSerializacao = new CtrSerializacao();
		try {
			data = (String[]) this.ctrSerializacao.recuperaObjeto("data.dat");
		} catch (Exception e) {
			String[] datax = { "DeLucca", "Bosco", "Moretto", "Isaías",
					"Marta", "Titi", "Tiago", "Seu Nome", "1" };
			this.data = datax;
			try {
				this.ctrSerializacao.gravaObjeto(data, "data.dat");
			} catch (Exception x) {

			}
		}
		int i = (new Integer(this.data[8]).intValue());
		switch (i) {
		case 0:
			this.velocidadeJogo = 2500;
			break;
		case 1:
			this.velocidadeJogo = 1800;
			break;
		case 2:
			this.velocidadeJogo = 1000;
			break;
		case 3:
			this.velocidadeJogo = 200;
			break;

		}
		this.multiplicadorPassaVez = 1;
		this.contaSete = 1;
		this.frase = "";
		this.seteValendo = true;
		this.jPrincipal = new JPrincipal(this);
		this.jPrincipal.show();
	}

	/**
	 * Atualiza a Interface dos Jogadores.
	 */
	public void atualizarInterface(String pMsg) {
		this.jPrincipal.atualizarInterface(pMsg);
	}

	/**
	 * Manda mostrar um aviso de erro para o usuário.
	 * 
	 * @param aviso
	 */
	public void avisoErro(String aviso) {
		this.jPrincipal.mostraErro(aviso);
	}

	/**
	 * Calcula a pontuação final dos jogadores.
	 */
	public void calculaPontos() {
		try {
			int flag = this.ctrJogador.num;
			for (int i = 0; i < flag; i++) {
				Jogador j = (Jogador) this.ctrJogador.prox();
				//Vector v = j.getAMao();
				for (int k = 0; k < j.getAMao().size(); k++) {
					if (((Carta) j.getAMao().elementAt(k)).getANumero() > 11) {
						j.setAPontuacao(j.getAPontuacao() + 10);
					} else if (((Carta) j.getAMao().elementAt(k)).getANumero() == 11) {
						j.setAPontuacao(j.getAPontuacao() + 20);
					} else {
						j.setAPontuacao(j.getAPontuacao()
								+ ((Carta) j.getAMao().elementAt(k))
										.getANumero());
					}
					this.ctrPilhaDescarte.push(((Carta) j.getAMao()
							.elementAt(k)));
				}
				j.getAMao().clear();
				j.setAPontuacao(j.getAPontuacao() * this.multiplicadorPontos);
			}
			//System.out.println("Pontos calculados");
		} catch (Exception e) {
			this
					.avisoErro("Ocorreu um erro durante o jogo no método\ncalculaPontos().\nMotivo: "
							+ e);
		}
	}

	/**
	 * Tira uma carta do topo do Baralho e coloca na mão do jogador atual
	 */
	public void comprarCarta() {
		try {
			if (this.ctrBaralho.pilhaVazia()) {
				this.reembaralhar();
			}
			((Jogador) this.ctrJogador.primeiro())
					.adicionaCartaAMao((Carta) this.ctrBaralho.pop());
			this.frase = ((Jogador) this.ctrJogador.primeiro()).getANome()
					+ " comprou uma carta";
			System.out.println("Carta comprada");
		} catch (Exception e) {
			this
					.avisoErro("Ocorreu um erro durante o jogo no método\ncomprarCarta().\nMotivo: "
							+ e);
		}
	}

	/**
	 * Controla a consequencia de se descartar um Ás.
	 *  
	 */
	public void consequenciaAs() {
		try {
			this.multiplicadorPassaVez = 2;
			this.frase = ((Jogador) this.ctrJogador.primeiro()).getANome()
					+ " descartou um Ás!";
		} catch (Exception e) {

		}
	}

	/**
	 * Controla a consequencia de se descartar um Nove.
	 *  
	 */
	public void consequenciaNove() {
		try {
			Jogador jogadorAtual = (Jogador) this.ctrJogador.primeiro();
			//System.out.println("Jogador Atual: " + jogadorAtual.getANome());
			Jogador jogadorAnterior = null;
			if (this.ctrJogador.horario) {
				for (int i = 0; i < this.aJogadores; i++) {
					if (this.ctrJogador.referenciaJogadores[i]
							.equals(jogadorAtual)) {
						if ((i - 1) < 0) {
							jogadorAnterior = this.ctrJogador.referenciaJogadores[this.aJogadores - 1];
							break;
						} else {
							jogadorAnterior = this.ctrJogador.referenciaJogadores[i - 1];
							break;
						}
					}
				}
			} else {
				for (int i = 0; i < this.aJogadores; i++) {
					if (this.ctrJogador.referenciaJogadores[i]
							.equals(jogadorAtual)) {
						if ((i + 1) == this.aJogadores) {
							jogadorAnterior = this.ctrJogador.referenciaJogadores[0];
							break;
						} else {
							jogadorAnterior = this.ctrJogador.referenciaJogadores[i + 1];
							break;
						}
					}
				}
			}

			//System.out.println("Jogador Anterior:"
			// +jogadorAnterior.getANome());
			Carta c = (Carta) this.ctrBaralho.pop();
			if (jogadorAnterior.getAMao().size() < 2) {
				jogadorAnterior.setAMaumau(false);
			}
			jogadorAnterior.adicionaCartaAMao(c);
			this.frase = jogadorAnterior.getANome()
					+ " comprou uma carta por causa do Nove!";
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage() + " - Causa: "
					+ e.getLocalizedMessage());
		}
	}

	/**
	 * Controla a consequencia de se descartar um Rei.
	 *  
	 */
	public void consequenciaRei() {
		try {

			if (this.ctrJogador.horario) {
				this.ctrJogador.horario = false;
			} else {
				this.ctrJogador.horario = true;
			}

			this.frase = ((Jogador) this.ctrJogador.primeiro()).getANome()
					+ " descartou um Rei!";
		} catch (Exception e) {

		}
	}

	/**
	 * Controla a consequencia de se descartar um Sete.
	 *  
	 */
	public void consequenciaSete() {
		try {
			Jogador jogadorAtual = (Jogador) this.ctrJogador.primeiro();
			//System.out.println("Jogador Atual: " + jogadorAtual.getANome());
			Jogador proxJogador = null;
			boolean temSete = false;

			if (this.ctrJogador.horario) {
				for (int i = 0; i < this.aJogadores; i++) {
					if (this.ctrJogador.referenciaJogadores[i]
							.equals(jogadorAtual)) {
						if ((i + 1) >= this.aJogadores) {
							proxJogador = this.ctrJogador.referenciaJogadores[0];
							break;
						} else {
							proxJogador = this.ctrJogador.referenciaJogadores[i + 1];
							break;
						}
					}
				}
			} else {
				for (int i = 0; i < this.aJogadores; i++) {
					if (this.ctrJogador.referenciaJogadores[i]
							.equals(jogadorAtual)) {
						if ((i - 1) < 0) {
							proxJogador = this.ctrJogador.referenciaJogadores[this.aJogadores - 1];
							break;
						} else {
							proxJogador = this.ctrJogador.referenciaJogadores[i - 1];
							break;
						}
					}
				}
			}
			//System.out.println("Próximo Jogador:" +proxJogador.getANome());
			if (proxJogador.getAMao().size() < 2) {
				proxJogador.setAMaumau(false);
			}

			for (int i = 0; i < proxJogador.getAMao().size(); i++) {
				int aux;
				aux = ((Carta) proxJogador.getAMao().elementAt(i)).getANumero();
				if (aux == 7) {
					temSete = true;
					this.contaSete++;
					break;
				}
			}

			if (!temSete) {
				for (int i = 0; i < this.contaSete; i++) {
					Carta c = (Carta) this.ctrBaralho.pop();
					proxJogador.adicionaCartaAMao(c);
					c = (Carta) this.ctrBaralho.pop();
					proxJogador.adicionaCartaAMao(c);
					this.seteValendo = false;
				}
				this.frase = proxJogador.getANome() + " não tem 7 e comprou "
						+ contaSete*2 + " cartas!";
				this.multiplicadorPassaVez = 2;
			}

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage() + " - Causa: "
					+ e.getLocalizedMessage());
		}

	}

	/**
	 * Controla a consequencia de se descartar um Valete.
	 *  
	 */
	public void consequenciaValete() {

		try {
			if (this.ctrJogador.primeiro().equals(
					this.ctrJogador.referenciaJogadores[0])) {
				JCheckBox paus = new JCheckBox();
				paus.setText("Paus");

				JCheckBox espada = new JCheckBox();
				espada.setText("Espada");

				JCheckBox ouro = new JCheckBox();
				ouro.setText("Ouro");

				JCheckBox copas = new JCheckBox();
				copas.setText("Copas");

				JCheckBox[] naipes = new JCheckBox[4];

				naipes[0] = paus;
				naipes[1] = espada;
				naipes[2] = ouro;
				naipes[3] = copas;

				JOptionPane.showMessageDialog(null, naipes,
						"Escolha um naipe:", JOptionPane.PLAIN_MESSAGE);
				boolean escolheu = false;
				while (!escolheu) {
					if (paus.isSelected()) {
						this.naipeCoringa = 4;
						escolheu = true;
					} else if (espada.isSelected()) {
						this.naipeCoringa = 3;
						escolheu = true;
					} else if (ouro.isSelected()) {
						this.naipeCoringa = 2;
						escolheu = true;
					} else if (copas.isSelected()) {
						this.naipeCoringa = 1;
						escolheu = true;
					} else {
						JOptionPane.showMessageDialog(null, naipes,
								"Escolha um naipe:", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
			this.frase = ((Jogador) this.ctrJogador.primeiro()).getANome()
					+ " descartou um coringa!";
		} catch (Exception e) {

		}

	}

	/**
	 * Distribui as cartas entre os jogadores.
	 */
	public void darCartas() {
		try {
			for (int i = 0; i < this.aJogadores; i++) {
				Vector mao = new Vector();
				for (int j = 0; j < 7; j++) {
					mao.addElement(this.ctrBaralho.pop());
				}
				((Jogador) (this.ctrJogador.prox())).setAMao(mao);
			}
			//Vira a carta do topo do baralho
			this.ctrPilhaDescarte.push(this.ctrBaralho.pop());
			//System.out.println("Cartas dadas.");
		} catch (Exception e) {
			this
					.avisoErro("Ocorreu um erro durante o jogo no método\ndarCartas().\nMotivo: "
							+ e);
		}
	}

	/**
	 * Descarta uma carta.
	 * @param pCarta
	 * @return
	 */
	public boolean descartar(Carta pCarta) {
		boolean descartou = false;
		this.multiplicadorPassaVez = 1;
		try {

			if (((Carta) this.ctrPilhaDescarte.top()).getANumero() == 11
					&& !this.cartaInicial) {
				if ((pCarta.getANaipe() == this.naipeCoringa)
						|| (pCarta.getANumero() == 11)) {
					this.ctrJogador.descartar(pCarta);
					descartou = true;
					/*
					 * System.out.println("Descartou" + descartou + " " +
					 * ((Jogador) this.ctrJogador.primeiro()).getANome());
					 */
					this.ctrPilhaDescarte.push(pCarta);
					this.frase = ((Jogador) this.ctrJogador.primeiro())
							.getANome()
							+ " descartou!";
					this.verificaConsequencia(pCarta);
					System.out.println("Carta descartada: "
							+ pCarta.getANumero() + " " + pCarta.getANaipe());
				} else {
					this.atualizarInterface("Carta inválida para descarte!");
					System.out.println("Carta NÃO descartada");
				}
				return descartou;
			} else if ((contaSete > 1) && this.seteValendo) {
				if (pCarta.getANumero() == ((Carta) this.ctrPilhaDescarte.top())
						.getANumero()) {
					this.ctrJogador.descartar(pCarta);
					descartou = true;
					/*
					 * System.out.println("Descartou" + descartou + " " +
					 * ((Jogador) this.ctrJogador.primeiro()).getANome());
					 */
					this.ctrPilhaDescarte.push(pCarta);
					this.frase = ((Jogador) this.ctrJogador.primeiro())
							.getANome()
							+ " descartou outro Sete!";
					this.verificaConsequencia(pCarta);
					System.out.println("Carta descartada: "
							+ pCarta.getANumero() + " " + pCarta.getANaipe());
				} else {
					this.atualizarInterface("Carta inválida para descarte!");
					System.out.println("Carta NÃO descartada");
				}
				this.cartaInicial = false;
				return descartou;

			} else {
				if ((pCarta.getANaipe() == ((Carta) this.ctrPilhaDescarte.top())
						.getANaipe())
						|| (pCarta.getANumero() == ((Carta) this.ctrPilhaDescarte
								.top()).getANumero())
						|| (pCarta.getANumero() == 11)) {
					this.ctrJogador.descartar(pCarta);
					descartou = true;
					/*
					 * System.out.println("Descartou" + descartou + " " +
					 * ((Jogador) this.ctrJogador.primeiro()).getANome());
					 */
					this.ctrPilhaDescarte.push(pCarta);
					this.frase = ((Jogador) this.ctrJogador.primeiro())
							.getANome()
							+ " descartou!";
					this.verificaConsequencia(pCarta);
					System.out.println("Carta descartada: "
							+ pCarta.getANumero() + " " + pCarta.getANaipe());
				} else {
					this.atualizarInterface("Carta inválida para descarte!");
					System.out.println("Carta NÃO descartada");
				}
				this.cartaInicial = false;
				return descartou;
			}

		} catch (Exception e) {
			this
					.avisoErro("CTR JOGO: Ocorreu um erro durante o jogo no método\ndescartar().\nMotivo: "
							+ e);
			return descartou;
		}
	}

	/**
	 * Escolhe o naipe deo coringa.
	 * @param pNaipe
	 */
	public void ecolheNaipe(int pNaipe) {
		this.naipeCoringa = pNaipe;
	}

	/**
	 * Rotina de fim de rodada.
	 *  
	 */
	public void fimDeJogo() {
		try {
			this.aRodadaAtual++;
			this.verificaConsequenciaFinal((Carta) this.ctrPilhaDescarte.top());
			this.calculaPontos();
			if (this.aRodadaAtual == this.aRodadas) {
				this.jPrincipal.anunciaFimDeJogo(true);
			} else {
				this.reembaralhar();
				this.darCartas();
				for (int i = 0; i < this.ctrJogador.num; i++) {
					this.ctrJogador.referenciaJogadores[i].setAMaumau(false);
				}
				this.ctrJogador.ajeitaPrimeiroDaFila();
				this.jPrincipal.anunciaFimDeJogo(false);
			}
			System.out.println("Jogo finalizado");
		} catch (Exception e) {
			this
					.avisoErro("Ocorreu um erro durante o jogo no método\nfimDeJogo().\nMotivo: "
							+ e);
		}
	}

	/**
	 * @return Returns the multiplicadorPassaVez.
	 */
	public int getMultiplicadorPassaVez() {
		return multiplicadorPassaVez;
	}

	/**
	 * @return Returns the multiplicadorPontos.
	 */
	public int getMultiplicadorPontos() {
		return multiplicadorPontos;
	}

	/**
	 * @return Returns the naipeCoringa.
	 */
	public int getNaipeCoringa() {
		return naipeCoringa;
	}

	/**
	 * Grava os dados do jogo.
	 * 
	 * @param pData
	 */
	public void gravarData(String[] pData) {
		try {
			this.ctrSerializacao.gravaObjeto(pData, "data.dat");
		} catch (Exception x) {
		}
		try {
			this.data = (String[]) this.ctrSerializacao
					.recuperaObjeto("data.dat");
		} catch (Exception e) {

		}
		int i = (new Integer(this.data[8]).intValue());
		switch (i) {
		case 0:
			this.velocidadeJogo = 2500;
			break;
		case 1:
			this.velocidadeJogo = 1800;
			break;
		case 2:
			this.velocidadeJogo = 1000;
			break;
		case 3:
			this.velocidadeJogo = 200;
			break;
		}
	}

	/**
	 * Inicia uma nova partida. Recebe o número de rodadas a se realizar, e
	 * quantos adversários o jogador terá.
	 */
	public void iniciaNovoJogo(int pRodadas, int nJogadores, String pNome) {
		try {
			this.aRodadas = pRodadas;
			this.aRodadaAtual = 0;
			this.aJogadores = nJogadores + 1;
			this.ctrJogador = new CtrJogador(this);
			this.ctrJogador.jogador = new Jogador(pNome);
			this.ctrJogador.jogador.setId(0);
			this.ctrJogador.entrar(ctrJogador.jogador);
			for (int i = 0; i < nJogadores; i++) {
				this.ctrJogador.jVirtual = new JogadorVirtual(data[i], this);
				this.ctrJogador.jVirtual.setId(i + 1);
				this.ctrJogador.entrar(ctrJogador.jVirtual);
			}
			this.ctrJogador.referenciaJogadores = new Jogador[aJogadores];
			for (int i = 0; i < aJogadores; i++) {
				this.ctrJogador.referenciaJogadores[i] = (Jogador) this.ctrJogador
						.prox();
			}

			this.prepararJogo();
			this.darCartas();
			this.cartaInicial = true;
			this.atualizarInterface(((Jogador) this.ctrJogador.primeiro())
					.getANome()
					+ " começa o jogo");
			//System.out.println("Novo jogo iniciado");
		} catch (Exception e) {
			this
					.avisoErro("Ocorreu um erro durante o jogo no método\niniciaNovoJogo().\nMotivo: "
							+ e);
		}
	}

	/**
	 * Controla as jogadas do computador
	 *  
	 */
	public void jogadaPC() {
		try {
			Carta tCarta = (Carta) this.ctrPilhaDescarte.top();
			JogadorVirtual j = (JogadorVirtual) this.ctrJogador.primeiro();
			int aNaipe = tCarta.getANaipe();
			int aNumero = tCarta.getANumero();
			Carta objCarta = j.jogar(aNaipe, aNumero);
			if (objCarta.getANaipe() == 0) {
				System.out.println("Jodada do PC - Comprando");
				this.comprarCarta();
				if (((JogadorVirtual) this.ctrJogador.primeiro()).getAMao()
						.size() > 1) {
					((JogadorVirtual) this.ctrJogador.primeiro())
							.setAMaumau(false);
				}
				this.passaAVez();
				this.verificaAVez();
			} else {
				if (this.descartar(objCarta)) {
					if (((JogadorVirtual) this.ctrJogador.primeiro()).getAMao()
							.size() == 1) {
						this.mudaMauMau();
					}
					if (this.verificaFimDeJogo()) {
						this.fimDeJogo();
					} else {
						System.out.println("Jodada do PC");
						int i = 1;
						//System.out.println("multi: " +
						// this.multiplicadorPassaVez);
						while (i <= this.multiplicadorPassaVez) {
							this.passaAVez();
							i++;
						}
						this.verificaAVez();
					}
				}
			}

		} catch (Exception e) {
			this.avisoErro("Ocorreu um erro durante a jogada do IA.\nMotivo: "
					+ e);
		}
	}

	/**
	 * Muda o jogador para o estado Maumau.
	 *  
	 */
	public void mudaMauMau() {
		try {
			Carta tCarta = (Carta) this.ctrPilhaDescarte.top();
			int aNaipe = tCarta.getANaipe();
			int aNumero = tCarta.getANumero();
			if (((Jogador) this.ctrJogador.primeiro()).getAMao().size() <= 2
					&& this.validaDescarte(aNaipe, aNumero)) {
				((Jogador) this.ctrJogador.primeiro()).setAMaumau(true);
				this.atualizarInterface(((Jogador) this.ctrJogador.primeiro())
						.getANome()
						+ " declarou Maumau!");
			} else {
				this.comprarCarta();
				this.comprarCarta();
				((Jogador) this.ctrJogador.primeiro()).setAMaumau(false);
				this.atualizarInterface(((Jogador) this.ctrJogador.primeiro())
						.getANome()
						+ " declarou Maumau errado!");
			}

			System.out.println("MauMau alterado");
		} catch (Exception e) {
			this
					.avisoErro("Ocorreu um erro durante o jogo no método\nmudaMauMau().\nMotivo: "
							+ e);
		}
	}

	/**
	 * Passa a vez para o próximo jogador.
	 */
	public void passaAVez() {
		try {
			System.out.println("Jogador Atual: "
					+ ((Jogador) this.ctrJogador.primeiro()).getANome());
			this.verificaMauMau();
			this.jPrincipal.setCliquesMaumau(0);
			int id = this.aJogadores - 1;
			if (!this.ctrJogador.horario) {
				for (int i = 0; i < id; i++) {
					this.ctrJogador.prox();
				}
				System.out.println("Jogador Seguinte: "
						+ ((Jogador) this.ctrJogador.primeiro()).getANome());
			} else {
				this.ctrJogador.prox();
				System.out.println("Jogador Seguinte: "
						+ ((Jogador) this.ctrJogador.primeiro()).getANome());
			}

			this.atualizarInterface(frase);
			//System.out.println("Vez passada: " + ((Jogador)
			// this.ctrJogador.primeiro()).getANome());

		} catch (Exception e) {
			this
					.avisoErro("Ocorreu um erro durante o jogo no método\npassaAVez().\nMotivo: "
							+ e);
		}
	}

	/**
	 * Prepara o jogo através da sequencia Preparar_Jogo.
	 */
	public void prepararJogo() {
		try {
			this.ctrBaralho = new CtrBaralho(this);
			this.ctrPilhaDescarte = new CtrPilhaDescarte(this);
			//System.out.println("Jogo Preparado");
		} catch (Exception e) {
			this
					.avisoErro("Ocorreu um erro durante o jogo no método\nprepararJogo().\nMotivo: "
							+ e);
		}
	}

	/**
	 * Passa a pilha de descarte para o baralho e embaralha novamente.
	 */
	public void reembaralhar() {
		try {
			while (this.ctrPilhaDescarte.pilhaVazia() == false) {
				this.ctrBaralho.push(this.ctrPilhaDescarte.pop());
			}
			Carta[] baralho = new Carta[this.ctrBaralho.getNum()];
			for (int i = 0; i < baralho.length; i++) {
				baralho[i] = (Carta) this.ctrBaralho.pop();
			}
			this.ctrBaralho.setABaralho(baralho);
			this.ctrBaralho.embaralhar();
			this.ctrPilhaDescarte.push(this.ctrBaralho.pop());
			//System.out.println("Reembaralhamento OK");
		} catch (Exception e) {
			this
					.avisoErro("Ocorrou um erro durante o jogo no método\nreembaralhar().\nMotivo: "
							+ e);
		}
	}

	/**
	 * Valida um descarte para a declaração de maumau.
	 * @param pNaipe
	 * @param pNumero
	 * @return
	 */
	public boolean validaDescarte(int pNaipe, int pNumero) {
		boolean pode = false;
		try {
			int n = ((Jogador) this.ctrJogador.primeiro()).getAMao().size();
			for (int i = 0; i < n; i++) {
				if (((Carta) this.ctrPilhaDescarte.top()).getANumero() == 11) {
					if (((Carta) ((Jogador) this.ctrJogador.primeiro())
							.getAMao().elementAt(i)).getANaipe() == this.naipeCoringa
							|| ((Carta) ((Jogador) this.ctrJogador.primeiro())
									.getAMao().elementAt(i)).getANumero() == 11) {
						pode = true;
						break;
					}
				} else {
					if (((Carta) ((Jogador) this.ctrJogador.primeiro())
							.getAMao().elementAt(i)).getANaipe() == pNaipe
							|| ((Carta) ((Jogador) this.ctrJogador.primeiro())
									.getAMao().elementAt(i)).getANumero() == pNumero
							|| ((Carta) ((Jogador) this.ctrJogador.primeiro())
									.getAMao().elementAt(i)).getANumero() == 11) {
						pode = true;
						break;
					}
				}

			}
			return pode;
		} catch (Exception e) {
			return pode;
		}
	}

	/**
	 * Verifica quem é o jogador da vez.
	 *  
	 */
	public void verificaAVez() {
		try {
			if (this.ctrJogador.primeiro().equals(
					this.ctrJogador.referenciaJogadores[0])) {
				this.jPrincipal.setMinhaVez(true);
				/*
				 * this.atualizarInterface("É a vez de " + ((Jogador)
				 * this.ctrJogador.primeiro()).getANome());
				 */
			} else {
				this.jPrincipal.setMinhaVez(false);
				Pensar pensamento = new Pensar();
				pensamento.start();
			}
		} catch (Exception e) {
			this
					.avisoErro("Ocorreu um erro durante a verificação do jogador da vez.\nMotivo: "
							+ e);
		}
	}

	/**
	 * Verifica a consequencia da carta descartada e as efetua caso necessário.
	 */
	public void verificaConsequencia(Carta pCarta) {
		int carta;

		carta = pCarta.getANumero();

		switch (carta) {
		case 1:
			this.consequenciaAs();
			break;
		case 7:
			this.consequenciaSete();
			break;
		case 9:
			this.consequenciaNove();
			break;
		case 11:
			this.consequenciaValete();
			break;
		case 13:
			this.consequenciaRei();
			break;
		default:
			this.contaSete = 1;
		}

	}

	/**
	 * Verifica se a última carta descartada no jogo tem alguma consequencia.
	 */
	public void verificaConsequenciaFinal(Carta pCarta) {
		int carta;

		carta = pCarta.getANumero();

		if (carta == 11) {
			this.multiplicadorPontos = 2;
		} else {
			this.multiplicadorPontos = 1;
		}
	}

	/**
	 * Verifica se a rodada terminou.
	 * 
	 * @return
	 */
	public boolean verificaFimDeJogo() {
		try {
			if (((Jogador) this.ctrJogador.primeiro()).maoVazia() == true) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			this
					.avisoErro("Ocorreu um erro durante o jogo no método\nverificaFimDeJogo().\nMotivo: "
							+ e);
			return false;
		}
	}

	/**
	 * Verifica se o Jogador avisou MauMau.
	 */
	public void verificaMauMau() {
		try {
			if (((((Jogador) this.ctrJogador.primeiro()).getAMao().size())) == 1
					&& (((Jogador) this.ctrJogador.primeiro()).isAMaumau() == false)) {
				this.comprarCarta();
				this.comprarCarta();
				this.frase = ((Jogador) this.ctrJogador.primeiro()).getANome()
						+ " esqueceu de declarar Maumau e compra 2 cartas!";
			} else if (((((Jogador) this.ctrJogador.primeiro()).getAMao()
					.size())) > 1
					&& (((Jogador) this.ctrJogador.primeiro()).isAMaumau() == true)) {
				this.comprarCarta();
				this.comprarCarta();
				((Jogador) this.ctrJogador.primeiro()).setAMaumau(false);
				this.frase = ((Jogador) this.ctrJogador.primeiro()).getANome()
						+ " declarou Maumau errado e compra 2 cartas!";
			}
			//System.out.println("Maumau verificado");
		} catch (Exception e) {
			this
					.avisoErro("Ocorreu um erro durante o jogo no método\nverificaMauMau().\nMotivo: "
							+ e);
		}
	}
}
