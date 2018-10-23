/*
 * Created on 06/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util;

import java.io.Serializable;

/**
 * Classe Caixinha para uso em estruturas de dados dinâmicas.
 * @author De Lucca
 * 
 */

public class Caixinha implements Serializable {

	/** Coteúdo da Caixinha */
	Object obj;

	/** Ponteiro para a próxima Caixinha */
	Caixinha prox;

	/**
	 * Construtor: recebe um conteúdo para guardar na Caixinha
	 * 
	 * @param pObj
	 */
	public Caixinha(Object pObj) {
		obj = pObj;
		prox = null;
	}

	/** Retorna o conteúdo da Caixinha */
	public Object getObj() {
		return obj;
	}

	/** Aponta para a próxima Caixinha */
	public Caixinha getProx() {
		return prox;
	}

	/**
	 * Seta o conteúdo da Caixinha
	 * 
	 * @param pObj
	 */
	public void setObj(Object pObj) {
		obj = pObj;
	}

	/**
	 * Seta a próxima Caixinha
	 * 
	 * @param pProx
	 */
	public void setProx(Caixinha pProx) {
		prox = pProx;
	}

}
