/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominioProblema;

import java.io.Serializable;

/**
 *
 * @author Rodrigo
 */
public class Carta implements Serializable {
    
    //tipos de cartas
    public static final int ARMA = 1;
    public static final int LOCAL = 2;
    public static final int ASSASSINO = 3;
    
    //locais
    public static final int BU = 1;
    public static final int REITORIA = 2;
    public static final int NPD = 3;
    public static final int EPS = 4;
    public static final int CTC = 5;
    public static final int INE = 6;
    public static final int CCS = 7;
    
    //armas
    public static final int CASTICAL = 1;
    public static final int CHAVE_INGLESA = 2;
    public static final int REVOLVER = 3;
    public static final int CORDA = 4;
    public static final int CANO = 5;
    public static final int FACA = 6;
    public static final int ARRAY_DE_BYTES = 7;
    
    //suspeitos
    
    public static final int ARTHUR = 1;
    public static final int DELUCCA = 2;
    public static final int ISAIAS = 3;
    public static final int FRANK = 4;
    public static final int FILETO = 5;
    public static final int RICARDO = 6;
    public static final int CARLA = 7;
    
    private int tipo;
    private int carta;

    public Carta(int tipo, int carta) {
        this.tipo = tipo;
        this.carta = carta;
    }
    
    /**
     * Get the value of carta
     *
     * @return the value of carta
     */
    public int getCarta() {
        return carta;
    }

    /**
     * Set the value of carta
     *
     * @param carta new value of carta
     */
    public void setCarta(int carta) {
        this.carta = carta;
    }


    /**
     * Get the value of tipo
     *
     * @return the value of tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Set the value of tipo
     *
     * @param tipo new value of tipo
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String retornaArquivo(){
        return this + ".jpg";
    }
   
    //retorna o nome da carta em String
    public static String retornaNome(int tipo, int carta) {
        switch(tipo) {
            
            case ARMA:
                switch(carta) {
                    case CASTICAL:
                        return "Castiçal";
                    case CHAVE_INGLESA:
                        return "Chave Inglesa";
                    case ARRAY_DE_BYTES:
                        return "Array de Bytes";
                    case CANO:
                        return "Cano";
                    case CORDA:
                        return "Corda";
                    case FACA:
                        return "Faca";
                    case REVOLVER:
                        return "Revolver";
                    
                }
           
            case LOCAL:
                switch(carta) {
                    case BU:
                        return "BU";
                    case NPD:
                        return "NPD";
                    case CCS:
                        return "CCS";
                    case REITORIA:
                        return "Reitoria";
                    case EPS:
                        return "EPS";
                    case CTC:
                        return "CTC";
                    case INE:
                        return "INE";
                }
            
            case ASSASSINO:
                switch(carta) {
                    case ARTHUR:
                        return "Arthur";
                    case DELUCCA:
                        return "De Lucca";
                    case CARLA:
                        return "Carla";
                    case RICARDO:
                        return "Ricardo";
                    case FRANK:
                        return "Frank";
                    case FILETO:
                        return "Fileto";
                    case ISAIAS:
                        return "Isaias";
                }
            
                
                
                
        }
        
        return null;
    }
    
    public String toString()  {
       return retornaNome(this.tipo, this.carta);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        if (this.tipo != other.tipo) {
            return false;
        }
        if (this.carta != other.carta) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.tipo;
        hash = 97 * hash + this.carta;
        return hash;
    }
    
    

    
    
    

}
