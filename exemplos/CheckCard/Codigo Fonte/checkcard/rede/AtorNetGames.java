package rede;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import control.ControladorMesa;
import model.Jogador;

public class AtorNetGames implements OuvidorProxy {

	protected ControladorMesa controladorMesa;
	protected Proxy proxy;
	
	public AtorNetGames(ControladorMesa controladorMesa) {
		super();
		this.controladorMesa = controladorMesa;
		this.proxy = proxy.getInstance();
		proxy.addOuvinte(this);
	}
	
	public boolean conectar(String nome, String servidor) {
        boolean retorno = true;
        
        try {
            proxy.conectar(servidor, nome);
        } catch (JahConectadoException ex) {
            ex.printStackTrace();
            retorno = false;
        } catch (NaoPossivelConectarException ex) {
            ex.printStackTrace();
            retorno = false;
        } catch (ArquivoMultiplayerException ex) {
            ex.printStackTrace();
            retorno = false;
        }
        return retorno;
    }
	
	public void desconectar() {
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			controladorMesa.exibeMensagem(e.getMessage());
			//TODO
			//setarNaoConectadoNoControlador
		}
	}
	
	public List<Jogador> getJogadores() {
        List<Jogador> jogadores = new ArrayList<Jogador>();
        if (controladorMesa.getJogadorAtual().getNome().equals(proxy.obterNomeAdversario(1))) {
            for (int i = 1; i <= 2; i++) {
                Jogador jogador = null;
                try {
                    jogador = new Jogador(i, proxy.obterNomeAdversario(i));
                    jogadores.add(jogador);
                } catch (Exception e){
                    System.out.println("Sem jogadores suficientes");
                }
            }
        } else {
            Jogador jogador = controladorMesa.getJogadorAtual();
            jogadores.add(jogador);
            jogador = new Jogador(proxy.obterNomeAdversario(2));
            jogadores.add(jogador);
        }
        
        return jogadores;
    }
	
	public void enviarJogada(Jogada jogada) {
		try {
			proxy.enviaJogada(jogada);
		} catch (NaoJogandoException e) {}
	}
	
	public void iniciarPartida() {
		try {
			proxy.iniciarPartida(2);
		} catch (NaoConectadoException e) {}
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		controladorMesa.exibeMensagem(message);
		controladorMesa.limparTodosCampos();
	}

	@Override
	public void receberMensagem(String msg) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void receberJogada(Jogada jogada) {
		controladorMesa.receberJogada(jogada);
	}

	@Override
	public void tratarConexaoPerdida() {
		controladorMesa.exibeMensagem("Conexão perdida com o servidor.");
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		controladorMesa.exibeMensagem(message);
	}
	
	public void encerrarPartida() {
		try {
			proxy.finalizarPartida();
		} catch (NaoConectadoException | NaoJogandoException e) {}
	}
}	
