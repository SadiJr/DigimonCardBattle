 package worms;


public class Fila {
	private String dados[];
	private int i,f;//inicio e fim
	private int numElementos = 0;
	private int max;
	
	public Fila(int tamanho){
		max=tamanho;
		i=f=0;
		dados = new String[tamanho];
	}
	
	public void entrada(String elemento)throws Exception{
		if (!filaCheia()){
			dados[f] = elemento;
			numElementos++;
			if(f==max-1)
				f=0;
			else
				f++;
		}
		else throw new Exception("fila cheia");
			
	}
	
	public String saida() throws Exception{
		if (numElementos>0){
			numElementos--;
			String aux = dados[i];
			if (i == max-1)
				i= 0;
			else 
				i++;
			return aux;
		}
		else 
			throw new Exception("fila vazia");		
	}
	
	public String primeiro() throws Exception{
		if (numElementos>0){
			return dados[i];
		}
		else 
			throw new Exception("fila vazia");		
				
	}
	
	public String ultimo() throws Exception{
		if (numElementos>0){
			return dados[f];
		}
		else 
			throw new Exception("fila vazia");		
				
	}
	
	public boolean filaVazia(){
		return numElementos==0;
	}
	
	public boolean filaCheia(){
		return numElementos==max;
	}
}

