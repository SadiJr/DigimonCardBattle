import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;


@SuppressWarnings("serial")
public class Placar extends JDialog{

	private JPanel jContentPane = null;
	private JTextPane jTextPane = null;
	private EntPlacar entPlacar = null;
	private Vector vPlacar = null;
	private String strPlacar = "";

	/**
	 * Este é o construtor
	 */
	public Placar(boolean exibir){
		
		super();
		
		if(exibir){
			this.initialize();			
		}
	}

	/**
	 * Este método inicializa "this" (JDialog)
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(450, 350);
		this.setTitle("Placares...");
		this.setContentPane(getJContentPane());
		this.setVisible(true);
	}

	/**
	 * Este método inicializa o jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.setBackground(java.awt.Color.black);
			jContentPane.add(getJTextPane(), BorderLayout.CENTER );		
		}
		return jContentPane;
	}

	/**
	 * Este método inicializa o jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			jTextPane.setBackground(java.awt.Color.black);
			jTextPane.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
			jTextPane.setForeground(java.awt.Color.red);
			jTextPane.setEditable(false);
			jTextPane.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);
			this.exibirPlacar();
			jTextPane.setText(strPlacar);
		}
		return jTextPane;
	}
	
	public void ordenaPlacar(){
		EntPlacar entAux;
		for(int j = 0; j < vPlacar.size(); j++){
			for(int i = 0; i < (vPlacar.size()-1); i++){
				if(((EntPlacar)vPlacar.elementAt(i)).getPlacar()<((EntPlacar)vPlacar.elementAt(i+1)).getPlacar()){
					entAux = (EntPlacar)vPlacar.elementAt(i+1);
					vPlacar.setElementAt(vPlacar.elementAt(i),(i+1));
					vPlacar.setElementAt(entAux,i);
				}
			}
			
		}
	}
	
	public void exibirPlacar(){
		try {
			this.desserializa();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Utilizado anteriormente como medida preventiva
		//this.ordenaPlacar();
		
			strPlacar = "\t. . . MELHORES PONTUAÇÕES DO PACMAN . . .\n\n";
			strPlacar += "\t\tPLACAR\tNOME\n";
		
		for(int i = 0; i<vPlacar.size(); i++){
			
			strPlacar += "\t\t";
			strPlacar += Integer.toString(((EntPlacar)vPlacar.elementAt(i)).getPlacar());
			strPlacar += "\t" + ((EntPlacar)vPlacar.elementAt(i)).getNome();
			strPlacar += "\n";
		}
	}
	
	public void incluiPlacar(int placar) {
		String nome = JOptionPane.showInputDialog(null,"Digite o seu nome :");
		entPlacar = new EntPlacar();
		entPlacar.setNome(nome);
		entPlacar.setPlacar(placar);
		
		try {
			this.desserializa();
			vPlacar.add(entPlacar);
			this.ordenaPlacar();
			this.serializa();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void desserializa() throws Exception{
		
//		Executa desserialização dos dados:
		File objFile = new File(Dados.NOME_ARQUIVO);
		if(objFile.exists()) {
			FileInputStream objFileIn = new FileInputStream(Dados.NOME_ARQUIVO);
			ObjectInputStream objObjectIn = new ObjectInputStream(objFileIn);
			vPlacar = (Vector)objObjectIn.readObject();
			objObjectIn.close();			
		} else {
			vPlacar = new Vector();
		}		
		
	}
	
	public void serializa()  throws Exception {
		
//		Executa serialização dos dados:
		FileOutputStream objFileOut = new FileOutputStream(Dados.NOME_ARQUIVO);
		ObjectOutputStream objObjectOut = new ObjectOutputStream(objFileOut);
		objObjectOut.writeObject(vPlacar);
		objObjectOut.flush();
		objObjectOut.close();
	}
	
}
