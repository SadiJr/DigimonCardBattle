package interfaceGrafica;

import java.awt.Rectangle;
import java.lang.reflect.Field;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ufsc.inf.leobr.annotations.classes.DirectlyReferredClass;
import ufsc.inf.leobr.annotations.classes.DirectlyReferredClasses;
import ufsc.inf.leobr.annotations.classes.Ec;
import ufsc.inf.leobr.annotations.method.DirectlyReferredMethod;
import ufsc.inf.leobr.annotations.method.DirectlyReferredMethods;
import ufsc.inf.leobr.annotations.method.Implements;
import ufsc.inf.leobr.annotations.statement.Statement;
import ufsc.inf.leobr.enumerations.classes.InheritanceType;

import dominioProblema.Tabuleiro;

import br.ufsc.inf.leobr.cliente.Client;
import br.ufsc.inf.leobr.cliente.EstadoProxy;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

@Ec(inheritance = InheritanceType.WHIT_INHERITANCE)
@DirectlyReferredClasses( { @DirectlyReferredClass(Proxy.class),
		@DirectlyReferredClass(OuvidorProxy.class) })
public class AtorRede implements OuvidorProxy {

	public AtorJogador atorJogador;

	public Proxy proxy;

	private JMenu menuRede = null;

	private JMenuItem jMenuItemConectar = null,
			jMenuItemIniciarPartidaRede = null,
			jMenuItemReiniciarPartidaRede = null, jMenuItemDesconectar = null;

	public AtorRede(AtorJogador atorJogador) {
		super();
		this.atorJogador = atorJogador;
		proxy = Proxy.getInstance();
	}

	 
	public JMenu getMenuRede() {
		if (menuRede == null) {
			menuRede = new JMenu();
			menuRede.setText("Jogar Online");
			menuRede.setBounds(new Rectangle(1, 0, 57, 21));
			menuRede.add(getJMenuItemConectar());
			menuRede.add(getJMenuItemIniciarPartidaRede());
			menuRede.add(getJMenuReiniciarParitdaRede());
			menuRede.add(getJMenuItemDesconectar());
		}
		return menuRede;
	}

	 
	private JMenuItem getJMenuItemConectar() {
		if (jMenuItemConectar == null) {
			jMenuItemConectar = new JMenuItem();
			jMenuItemConectar.setText("Conectar");
			jMenuItemConectar
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							conectar();
						}

					});
		}
		return jMenuItemConectar;
	}

	

	 
	private JMenuItem getJMenuItemIniciarPartidaRede() {
		if (jMenuItemIniciarPartidaRede == null) {
			jMenuItemIniciarPartidaRede = new JMenuItem();
			jMenuItemIniciarPartidaRede.setText("Iniciar Partida de Rede");
			jMenuItemIniciarPartidaRede
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							iniciarPartidaRede();
						}

					});
		}
		return jMenuItemIniciarPartidaRede;
	}

	 
	private JMenuItem getJMenuItemDesconectar() {
		if (jMenuItemDesconectar == null) {
			jMenuItemDesconectar = new JMenuItem();
			jMenuItemDesconectar.setText("Desconectar");
			jMenuItemDesconectar
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							desconectar();
						}
					});
		}
		return jMenuItemDesconectar;
	}

	 
	private JMenuItem getJMenuReiniciarParitdaRede() {
		if (jMenuItemReiniciarPartidaRede == null) {
			jMenuItemReiniciarPartidaRede = new JMenuItem();
			jMenuItemReiniciarPartidaRede.setText("Reiniciar partida");
			jMenuItemReiniciarPartidaRede
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							reiniciar();
						}

					});
		}
		return jMenuItemReiniciarPartidaRede;
	}
	
	protected void conectar() {
		GuiRede ic = new GuiRede(this);
		ic.createFront();

	}

	 
	@DirectlyReferredMethods( {
			@DirectlyReferredMethod(classe = Proxy.class, name = "conectar", paramTypes = {
					String.class, String.class }),
			@DirectlyReferredMethod(classe = Proxy.class, name = "addOuvinte", paramTypes = { OuvidorProxy.class })

	})
	protected void conectarRede(String nome, String ipServidor) {
		try {
			Proxy.getInstance().conectar(ipServidor, nome);
			Proxy.getInstance().addOuvinte(this);
		} catch (JahConectadoException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(atorJogador, e.getMessage());
		} catch (NaoPossivelConectarException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(atorJogador, "Erro: "
					+ e.getMessage());
		} catch (ArquivoMultiplayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(atorJogador, "Erro: "
					+ e.getMessage());
		}
	}

	 
	@DirectlyReferredMethod(classe = Proxy.class, name = "iniciarPartida",paramTypes={Integer.class})
	protected void iniciarPartidaRede() {
		try {
			Proxy.getInstance().iniciarPartida(2);
		} catch (NaoConectadoException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(atorJogador, e.getMessage());
		}
	}

	 
	@DirectlyReferredMethod(classe = Proxy.class, name = "reiniciarPartida")
	protected void reiniciar() {
		try {
			Proxy.getInstance().reiniciarPartida();
		} catch (NaoConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(atorJogador, "Erro: "
					+ e.getMessage());
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(atorJogador, "Erro: "
					+ e.getMessage());
		}

	}

	 
	@DirectlyReferredMethod(classe = Proxy.class, name = "desconectar")
	protected void desconectar() {
		try {
			atorJogador.setEhJogoRede(false);
			Proxy.getInstance().desconectar();
		} catch (NaoConectadoException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(atorJogador, "Erro: "
					+ e.getMessage());
		}

	}

	@Implements(interfaceImplement = OuvidorProxy.class)
	@DirectlyReferredMethod(classe = OuvidorProxy.class, name = "receberJogada", paramTypes = { Jogada.class })
	public void receberJogada(Jogada jogada) {
		// Recebe uma jogada do outro lado
		dominioProblema.JogadaVelha jg = (dominioProblema.JogadaVelha) jogada;
		atorJogador.efetuarJogadaRede(jg.getLinha(), jg.getColuna());

	}

	@Implements(interfaceImplement = OuvidorProxy.class)
	@DirectlyReferredMethod(classe = OuvidorProxy.class, name = "finalizarPartidaComErro", paramTypes = { String.class })
	public void finalizarPartidaComErro(String message) {
		JOptionPane.showMessageDialog(atorJogador, message);
	}

	 
	@Implements(interfaceImplement = OuvidorProxy.class)
	@DirectlyReferredMethod(classe = OuvidorProxy.class, name = "iniciarNovaPartida", paramTypes = {Integer.class})
	public void iniciarNovaPartida(Integer posicao) {
		if (posicao == 1) {
			JOptionPane.showMessageDialog(atorJogador,
					"Partida Iniciada, você começa jogando!");
			atorJogador.iniciarPartidaRede(true);
		} else {
			JOptionPane.showMessageDialog(atorJogador,
					"Partida Iniciada, aguarde uma jogada");
			atorJogador.iniciarPartidaRede(false);
		}
	}

	@Implements(interfaceImplement = OuvidorProxy.class)
	@DirectlyReferredMethod(classe = OuvidorProxy.class, name = "receberMensagem", paramTypes = { String.class })
	public void receberMensagem(String msg) {
		JOptionPane.showMessageDialog(atorJogador,
				"Mensagem recebida do servidor:" + msg);

	}

	@Implements(interfaceImplement = OuvidorProxy.class)
	@DirectlyReferredMethod(classe = OuvidorProxy.class, name = "tratarConexaoPerdida")
	public void tratarConexaoPerdida() {
		JOptionPane
				.showMessageDialog(atorJogador,
						"A conexão com o servidor foi perdida, por favor tente novamente mais tarde.");
		atorJogador.setEhJogoRede(false);
	}

	@Implements(interfaceImplement = OuvidorProxy.class)
	@DirectlyReferredMethod(classe = OuvidorProxy.class, name = "tratarPartidaNaoIniciada",paramTypes={String.class})
	public void tratarPartidaNaoIniciada(String message) {
		JOptionPane.showMessageDialog(atorJogador,
				"A partida não pode ser iniciada devido ao seguinte erro: "
						+ message);

	}

}
