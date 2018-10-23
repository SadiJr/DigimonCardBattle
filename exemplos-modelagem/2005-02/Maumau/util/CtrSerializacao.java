package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Serializa e Recupera objetos.
 * @author Fabio Kreusch
 * 
 */
public class CtrSerializacao {

	/**
	 * Recebe um Objeto qualquer e serializa com o nome pNomeArquivo.
	 * 
	 * @param pObjGen
	 * @param pNomeArquivo
	 * @throws Exception
	 */
	public void gravaObjeto(Object pObjGen, String pNomeArquivo)
			throws Exception {
		try {
			FileOutputStream objFileOS = new FileOutputStream(pNomeArquivo);
			ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
			objOS.writeObject(pObjGen);
			objOS.flush();
			objOS.close();
			System.out.println("Gravou - CtrSerializa - " + pNomeArquivo);
		} catch (FileNotFoundException e) {
			System.out.println("Não Gravou - CtrSerializa - " + pNomeArquivo);
			throw new Exception("Erro ao gravar arquivo");
		} catch (IOException e) {
			System.out.println("Não Gravou - CtrSerializa - " + pNomeArquivo);
			throw new Exception("Erro ao gravar arquivo");
		}
	}

	/**
	 * Descerializa o Objeto pNomeArquivo.
	 * 
	 * @param pNomeArquivo
	 * @return
	 * @throws Exception
	 */
	public Object recuperaObjeto(String pNomeArquivo) throws Exception {
		File objFile = new File(pNomeArquivo);
		Object objeto;
		if (objFile.exists()) {
			FileInputStream objFileIS = new FileInputStream(pNomeArquivo);
			ObjectInputStream objIS = new ObjectInputStream(objFileIS);
			objeto = objIS.readObject();
			objIS.close();
			System.out.println("Recuperou - CtrSerializa - " + pNomeArquivo);
		} else {
			throw new Exception("Arquivo não encontrado");
		}
		return objeto;
	}
}