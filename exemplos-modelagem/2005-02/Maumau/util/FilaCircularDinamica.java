/*
 * Created on 21/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util;

/**
 * Esta classe implementa uma Fila Circular Dinâmica.
 * @author Administrador 
 * 
 */
public class FilaCircularDinamica {
	private Caixinha fim;

	private Caixinha inicio;

	public int num;

	public FilaCircularDinamica() {
		inicio = null;
		num = 0;
		fim = null;
	}

	/**
	 * Adiciona um novo Objeto à Fila.
	 * 
	 * @param obj
	 */
	public void entrar(Object obj) {
		Caixinha nova = new Caixinha(obj);
		if (fim != null) {
			fim.setProx(nova);
			fim = nova;
			fim.setProx(inicio);
			num++;
		} else {
			inicio = nova;
			fim = inicio;
			num++;
		}
	}

	/**
	 * Retorna se a fila está ou não vazia.
	 * 
	 * @return
	 */
	public boolean filaVazia() {
		if (num == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Esvazia fila
	 *  
	 */
	public void limpaFila() {
		this.fim = null;
		this.inicio = null;
		this.num = 0;
	}

	/**
	 * Retorna o primeiro Objeto da fila.
	 * 
	 * @return
	 * @throws Exception
	 */
	public Object primeiro() throws Exception {
		if (!filaVazia()) {
			return inicio.getObj();
		} else {
			throw new Exception("Fila vazia");
		}
	}

	/**
	 * Retorna o primeiro Objeto da fila de espera e o joga para último da fila.
	 * 
	 * @return
	 * @throws Exception
	 */
	public Object prox() throws Exception {
		if (!filaVazia()) {
			Object aux = inicio.getObj();
			fim = inicio;
			inicio = inicio.getProx();
			return aux;
		} else {
			throw new Exception("Fila vazia");
		}
	}
}
