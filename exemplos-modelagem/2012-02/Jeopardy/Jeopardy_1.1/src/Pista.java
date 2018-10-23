import br.ufsc.inf.leobr.cliente.Jogada;
import java.text.Normalizer;

public class Pista implements Jogada {

    private static final long serialVersionUID = 1L;
    private String categoria;
    private String pergunta;
    private int valor;
    private String resposta;
    private boolean respondida;
    private String dica;
    private String anagrama;
    private int id;

    public Pista(int id, String categoria, String pergunta, int valor, String resposta, String dica) {
        this.categoria = categoria;
        this.pergunta = pergunta;
        this.valor = valor;
        this.resposta = resposta;
        this.dica = dica;
        this.respondida = false;
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getPergunta() {
        return pergunta;
    }

    public int getValor() {
        return valor;
    }

    public String getResposta() {
        return resposta;
    }

    public boolean isRespondida() {
        return respondida;
    }

    public void setRespondida(boolean respondida) {
        this.respondida = respondida;
    }

    private String formataTexto(String texto) {
        // passa tudo para upper
        String tmp = texto.toUpperCase();        
        // remove a acentuação
        tmp = Normalizer.normalize(tmp, Normalizer.Form.NFD);
        tmp = tmp.replaceAll("[^\\p{ASCII}]", "");
        // remove espaços
        tmp = tmp.replaceAll(" ", "");        
        //tmp = tmp.replaceAll("?" + "", "");        
        return tmp;
    }
    
    public boolean isRespostaValida(String perguntaDigitada) {
        
        perguntaDigitada = formataTexto(perguntaDigitada);
        String perguntaOficial = formataTexto(this.pergunta.toUpperCase());
        int[][] diferenca = new int[perguntaDigitada.length() + 1][perguntaOficial.length() + 1];
        boolean certo = true;
        int acima, esquerda, diagonalEsquerdaAcima, ehDiferente;
        char origem, destino;
       
       
                for (int i = 0; i <= perguntaDigitada.length(); i++){
                        diferenca[i][0] = i;
                }
                
                for (int j = 1; j <= perguntaOficial.length(); j++) {
                        diferenca[0][j] = j; 
                }
 
                for (int i = 1; i <= perguntaDigitada.length(); i++){
                    for (int j = 1; j <= perguntaOficial.length(); j++) {
                         acima =   diferenca[i - 1][j] + 1;
                         esquerda =  diferenca[i][j - 1] + 1;
                         diagonalEsquerdaAcima =  diferenca[i - 1][j - 1];
                         ehDiferente = 0;
                         origem = perguntaDigitada.charAt(i - 1);
                         destino = perguntaOficial.charAt(j - 1);
                        if(origem != destino) {
                            ehDiferente = 1;
                        }
                           
                            diferenca[i][j] = Math.min(Math.min(acima, esquerda), diagonalEsquerdaAcima + ehDiferente);
                    }
                    
                 
                }
                int qtdeErros = diferenca[perguntaDigitada.length()][perguntaOficial.length()];
                //System.err.println("Erros encontrados: "+ qtdeErros);
                
                if(qtdeErros > 5){
                   certo = false;
                }
                
                return certo;
    }
    
     
    public String getDica(){
        return this.dica;
    }
    
    public String getAnagrama(){
        return this.anagrama;
    }
    
    public int getId(){
        return this.id;
    }
}