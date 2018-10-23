//classe Jogador

public class Jogador{

       private String nome;
       private String imagem;
       private int linha;
       private int coluna;
       private boolean daVez;
       private boolean vencedor;

       //contrutor da classe
       public Jogador(String pImagem){

              setImagem(pImagem);
              vencedor = false;
              daVez=false;
       }

       public void setNome(String pNome){

              nome = pNome;

       }

       public void setImagem(String pImagem){

              imagem = pImagem;

       }


       public void setLinha(int pLinha){

              linha = pLinha;

       }

       public void setColuna(int pColuna){

              coluna = pColuna;

       }

       public String getNome(){

              return nome;

       }

       public String getImagem(){

              return imagem;

       }

       public int getLinha(){

              return linha;

       }

       public int getColuna(){

               return coluna;

       }

       public void habilitar(){
             daVez=true;
       }

       public void desabilitar(){
             daVez=false;
       }

       public void tornaVencedor(){

              vencedor=true;
       }

       public void resetarVencedor(){

              vencedor=false;
       }

       public boolean informaDaVez(){

              return daVez;

        }

        public boolean informaVencedor(){

               return vencedor;

        }

}