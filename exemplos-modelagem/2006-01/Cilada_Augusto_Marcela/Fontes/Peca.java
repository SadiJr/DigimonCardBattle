import java.io.*;
import java.awt.Color;

public class Peca implements Serializable{

	protected static final long serialVersionUID = -1646091577451269160L;

	protected int[][] formato;

	protected char tipo;
	
	protected boolean disponivel = true;
	
	protected byte pivo_x;
	
	protected byte pivo_y;
	
	protected Color cor = new Color(0xCC,0xFF,0x99);	
	
	public Peca(char vTipo) {
		pivo_x = 0;
		pivo_y = 0;
		tipo = vTipo;
		formato = new int[2][2];
		switch(tipo){
			case 'A': inicializarA(); break;
			case 'B': inicializarB(); break;
			case 'C': inicializarC(); break;
			case 'D': inicializarD(); break;
			case 'E': inicializarE(); break;
			case 'F': inicializarF(); break;
			case 'G': inicializarG(); break;
			case 'H': inicializarH(); break;
			case 'I': inicializarI(); break;
			case 'J': inicializarJ(); break;
			case 'K': inicializarK(); break;
			case 'L': inicializarL(); break;
			case 'M': inicializarM(); break;
			case 'N': inicializarN(); break;		
		}		
	}
	
	public void setPivo_x(byte valor) throws Exception{
		if(valor < 0 || valor > 3){
			throw new Exception("Valor inválido para o pivo x");			
		}
		this.pivo_x = valor;
	}
	
	public void setPivo_y(byte valor) throws Exception{
		if(valor < 0 || valor > 3){
			throw new Exception("Valor inválido para o pivo y");			
		}
		this.pivo_y = valor;
	}	
	
	public byte getPivo_x(){
		return pivo_x;
	}
	
	public byte getPivo_y(){
		return pivo_y;
	}

	/**
	 * Inicializa a peça com formato A
	 */
	protected void inicializarA(){
		formato[0][0] = Formato.CIRCULO;
		formato[0][1] = Formato.VAZIO;
		formato[1][0] = Formato.CRUZ;
		formato[1][1] = Formato.VAZIO;
	}

	/**
	 * Inicializa a peça com formato B
	 */
	protected void inicializarB(){
		formato[0][0] = Formato.QUADRADO;
		formato[0][1] = Formato.VAZIO;
		formato[1][0] = Formato.CRUZ;
		formato[1][1] = Formato.VAZIO;
	}

	/**
	 * Inicializa a peça com formato C
	 */
	protected void inicializarC(){
		formato[0][0] = Formato.CIRCULO;
		formato[0][1] = Formato.VAZIO;
		formato[1][0] = Formato.QUADRADO;
		formato[1][1] = Formato.VAZIO;
	}

	/**
	 * Inicializa a peça com formato D
	 */
	protected void inicializarD(){
		formato[0][0] = Formato.CRUZ;
		formato[0][1] = Formato.VAZIO;
		formato[1][0] = Formato.CRUZ;
		formato[1][1] = Formato.VAZIO;
	}

	/**
	 * Inicializa a peça com formato E
	 */
	protected void inicializarE(){
		formato[0][0] = Formato.QUADRADO;
		formato[0][1] = Formato.VAZIO;
		formato[1][0] = Formato.QUADRADO;
		formato[1][1] = Formato.VAZIO;
	}

	/**
	 * Inicializa a peça com formato F
	 */
	protected void inicializarF(){
		formato[0][0] = Formato.CIRCULO;
		formato[0][1] = Formato.VAZIO;
		formato[1][0] = Formato.CIRCULO;
		formato[1][1] = Formato.VAZIO;
	}

	/**
	 * Inicializa a peça com formato G
	 */
	protected void inicializarG(){
		formato[0][0] = Formato.CIRCULO;
		formato[0][1] = Formato.VAZIO;
		formato[1][0] = Formato.QUADRADO;
		formato[1][1] = Formato.QUADRADO;
	}

	/**
	 * Inicializa a peça com formato H
	 */
	protected void inicializarH(){
		formato[0][0] = Formato.CIRCULO;
		formato[0][1] = Formato.VAZIO;
		formato[1][0] = Formato.CRUZ;
		formato[1][1] = Formato.QUADRADO;
	}

	/**
	 * Inicializa a peça com formato I
	 */
	protected void inicializarI(){
		formato[0][0] = Formato.QUADRADO;
		formato[0][1] = Formato.VAZIO;
		formato[1][0] = Formato.CIRCULO;
		formato[1][1] = Formato.CRUZ;
	}

	/**
	 * Inicializa a peça com formato J
	 */
	protected void inicializarJ(){
		formato[0][0] = Formato.CRUZ;
		formato[0][1] = Formato.VAZIO;
		formato[1][0] = Formato.CRUZ;
		formato[1][1] = Formato.QUADRADO;
	}

	/**
	 * Inicializa a peça com formato K
	 */
	protected void inicializarK(){
		formato[0][0] = Formato.CIRCULO;
		formato[0][1] = Formato.VAZIO;
		formato[1][0] = Formato.CIRCULO;
		formato[1][1] = Formato.QUADRADO;
	}

	/**
	 * Inicializa a peça com formato L
	 */
	protected void inicializarL(){
		formato[0][0] = Formato.QUADRADO;
		formato[0][1] = Formato.VAZIO;
		formato[1][0] = Formato.CIRCULO;
		formato[1][1] = Formato.CIRCULO;
	}

	/**
	 * Inicializa a peça com formato M
	 */
	protected void inicializarM(){
		formato[0][0] = Formato.QUADRADO;
		formato[0][1] = Formato.VAZIO;
		formato[1][0] = Formato.CRUZ;
		formato[1][1] = Formato.CIRCULO;
	}

	/**
	 * Inicializa a peça com formato N
	 */
	protected void inicializarN(){
		formato[0][0] = Formato.CRUZ;
		formato[0][1] = Formato.VAZIO;
		formato[1][0] = Formato.QUADRADO;
		formato[1][1] = Formato.CIRCULO;
	}	

	public void setDisponivel(boolean valor) {
		disponivel = valor;
	}

	/**
	 *Informa se a peça está disponível
	 */
	public boolean getDisponivel() {
		return disponivel;
	}

	public void rotacionar() {
		
		int aux = formato[0][0];
		formato[0][0] = formato[1][0];
		formato[1][0] = formato[1][1];
		formato[1][1] = formato[0][1];
		formato[0][1] = aux;		
		if (pivo_x == 0){
			if (pivo_y == 0){
				pivo_y = 1;
			} else {
				pivo_x = 1;
			}
		} else {
			if (pivo_y == 0){
				pivo_x = 0;
			} else {
				pivo_y = 0;
			}
		}		
	}
	
	public char getTipo() {
		return tipo;
	}
	
	
	public boolean verificaFormato(int x, int y, int f) throws Exception{
		if(x < 0 || x > 1 || y < 0 || y > 1){
			throw new Exception("Coordenadas inválidas para verificação do formato");
		}
		return this.formato[x][y] == f || this.formato[x][y] == Formato.VAZIO;
	}
	
	public boolean itemVazio(int x, int y) throws Exception{
		if(x < 0 || x > 1 || y < 0 || y > 1){
			throw new Exception("Coordenadas inválidas para verificação do formato");
		}	
		return this.formato[x][y] == Formato.VAZIO;		
	}

	public int[][] getFormato() {
		return formato;
	}
	
	public boolean ehPivo(int x, int y){		
		return x == this.pivo_x && y == this.pivo_y;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}
	
	public String toString(){		
		String s = "";		
		s += formato[0][0] + " | ";
		s += formato[0][1] + "\n";
		s += formato[1][0] + " | ";
		s += formato[1][1] + "\n";
		s += "Pivo x: " + pivo_x + "\n";
		s += "Pivo y: " + pivo_y + "\n";
		s += "---------------------------------\n\n";
		return s;	
	}

}
