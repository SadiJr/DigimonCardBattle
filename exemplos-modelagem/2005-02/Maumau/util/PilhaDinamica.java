/*
 * Created on 06/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util;

/**
 * Controle a estrutura de dados de uma Pilha Dinâmica
 * @author Administrador
 * 
 *
 */

public class PilhaDinamica {

	/** Número de elementos na Pilha */
	private int num;

	/** Referência a última Caixinha da Pilha */
	Caixinha ultima;

	/** Construtor */
	public PilhaDinamica() {
		ultima = null;
		num = 0;
	}

	public int getNum() {
		return this.num;
	}

	/**
	 * Retorna se a pilha está ou não vazia.
	 * @return
	 */
	public boolean pilhaVazia() {
		if (num == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Retira o elemento do topo da lista.
	 * 
	 * @return
	 * @throws Exception
	 */
	public Object pop() throws Exception {
		if (num == 0) {
			throw new Exception("Pilha vazia");
		} else {
			Object retorno = ultima.getObj();
			ultima = ultima.getProx();
			num--;
			return retorno;
		}
	}

	/**
	 * Adiciona um elemento à pilha
	 * 
	 * @param Object
	 *            pObj
	 */
	public void push(Object pObj) {
		Caixinha nova = new Caixinha(pObj);
		nova.setProx(ultima);
		ultima = nova;
		num++;
	}

	/**
	 * Retorna o elemento do topo da pilha.
	 * 
	 * @return
	 * @throws Exception
	 */
	public Object top() throws Exception {
		if (num == 0) {
			throw new Exception("Pilha vazia");
		} else {
			return ultima.getObj();
		}
	}

}
