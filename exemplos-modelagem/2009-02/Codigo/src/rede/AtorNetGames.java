package rede;

import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.exception.*;
import interfaceGrafica.AtorJogador;

import javax.swing.*;

import dominio.JogadaImpl;

import java.util.List;

public class AtorNetGames implements OuvidorProxy {

	private  AtorJogador atorJogador;
	private Proxy proxy;
    private boolean emAndamento; 
    private boolean conectado; 

    public AtorNetGames (AtorJogador interfaceGraf){
		super();
		this.atorJogador = interfaceGraf;
		this.proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}

	public boolean conectar(String servidor, String nome) {
		try {
			proxy.conectar(servidor, nome);
            conectado = true;
			return true;
		} catch (JahConectadoException e) {
			JOptionPane.showMessageDialog(atorJogador.informarJanela(), e.getMessage());
			e.printStackTrace();
			return false;
		} catch (NaoPossivelConectarException e) {
			JOptionPane.showMessageDialog(atorJogador.informarJanela(), e.getMessage());
			e.printStackTrace();
			return false;
		} catch (ArquivoMultiplayerException e) {
			JOptionPane.showMessageDialog(atorJogador.informarJanela(), e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public void desconectar() {
        try {
            conectado = false;
            emAndamento = false;
            proxy.desconectar();
        } catch (NaoConectadoException e) {
            e.printStackTrace(); 
        }
    }

	public int iniciarPartida() {
        if (!conectado)
            return 1;
        if (emAndamento)
            return 2;
        
        int quantAdversarios = proxy.obterNomeAdversarios().size();
        if (quantAdversarios<1)
            return 3;//Jogadores insuficientes
        try {
			proxy.iniciarPartida(Math.min(quantAdversarios+1,4));
            return 0;
		} catch (NaoConectadoException e) {
			return 1;
		}
	}

	public void enviarJogada(JogadaImpl jogada) {
        System.out.println("enviei");
        try {
            proxy.enviaJogada(jogada);
        } catch (NaoJogandoException e) {
            e.printStackTrace();
        }
    }


    
	public void receberJogada(Jogada jogada) {
        atorJogador.receberJogada((JogadaImpl)jogada);

    }

    public void finalizarPartida() {
        try {
            proxy.finalizarPartida();
        } catch (NaoConectadoException e) {
            e.printStackTrace();
        } catch (NaoJogandoException e) {
            e.printStackTrace();
        }
    }

	public void finalizarPartidaComErro(String message) {
		// TODO Auto-generated method stub

	}

	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub

	}

	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub

	}

	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub

	}

	public void iniciarNovaPartida(Integer posicao) {
        emAndamento = true;
        List<String> adversarios = proxy.obterNomeAdversarios();
        atorJogador.processarInicioPartida(adversarios,posicao);
	}



}