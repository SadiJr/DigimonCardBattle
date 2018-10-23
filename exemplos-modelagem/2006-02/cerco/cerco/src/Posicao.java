//classe das posições q compoem o tabuleiro

public class Posicao {


       private boolean temPecinha;
       private boolean taOcupada;


       public boolean disponivel(){

              if(temPecinha==true && taOcupada==false)
                    return true;

              else
                  return false;

       }

       public void setOcupada(boolean pTaOcupada){

              taOcupada = pTaOcupada;

       }

       public void setPecinha(boolean pTemPecinha){

              temPecinha = pTemPecinha;

       }

       public void tiraPecinha(){

              temPecinha=false;
       }

       public void ocupa(){

              taOcupada = true;

       }

       public void desocupa(){

              taOcupada = false;

       }


}