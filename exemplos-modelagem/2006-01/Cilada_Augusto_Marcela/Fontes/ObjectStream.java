import java.io.*;

public class ObjectStream {

	/**
	 * Cria um fluxo de dados para gravar um objeto em arquivo
	 * @param nomeArquivo Nome do arquivo para gravar
	 * @param obj Objeto a ser gravado no arquivo
	 */
	public static void gravar(String nomeArquivo, Object obj) throws FileNotFoundException,IOException{

   	FileOutputStream fileOS = new FileOutputStream(nomeArquivo);
   	ObjectOutputStream objOS = new ObjectOutputStream(fileOS);
   	objOS.writeObject(obj);
   	objOS.flush();
   	objOS.close();

	}

	/**
	 * Cria um fluxo de dados para recuperar um objeto em arquivo
	 * @param nomeArquivo Nome do arquivo para abrir
	 * @return O Object recuperado do arquivo
	 */
	public static Object recuperar(String nomeArquivo) throws ClassNotFoundException,FileNotFoundException,IOException{

		Object obj = null;
		File arquivo = new File(nomeArquivo);
		if(arquivo.exists()){
			FileInputStream fileIS = new FileInputStream(arquivo);
			ObjectInputStream objIS = new ObjectInputStream(fileIS);
			obj = objIS.readObject();
			objIS.close();
		}
   	return obj;

	}


}