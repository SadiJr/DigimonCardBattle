/*
 * Created on 06/09/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util;

import java.io.Serializable;

/**
 * Classe Caixinha para uso em estruturas de dados din�micas.
 * @author De Lucca
 * 
 */

public class Caixinha implements Serializable {

	/** Cote�do da Caixinha */
	Object obj;

	/** Ponteiro para a pr�xima Caixinha */
	Caixinha prox;

	/**
	 * Construtor: recebe um conte�do para guardar na Caixinha
	 * 
	 * @param pObj
	 */
	public Caixinha(Object pObj) {
		obj = pObj;
		prox = null;
	}

	/** Retorna o conte�do da Caixinha */
	public Object getObj() {
		return obj;
	}

	/** Aponta para a pr�xima Caixinha */
	public Caixinha getProx() {
		return prox;
	}

	/**
	 * Seta o conte�do da Caixinha
	 * 
	 * @param pObj
	 */
	public void setObj(Object pObj) {
		obj = pObj;
	}

	/**
	 * Seta a pr�xima Caixinha
	 * 
	 * @param pProx
	 */
	public void setProx(Caixinha pProx) {
		prox = pProx;
	}

}
