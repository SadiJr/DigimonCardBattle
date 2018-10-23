/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominioProblema;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Rodrigo
 */
public class Baralho {
    
    private Carta[] baralho;

    /*cria o baralho preenchendo o array com
     *todas as 7 cartas dos 3 tipos diferentes
     */
    public Baralho() {
        
        baralho = new Carta[21];
        
        int cont = 0;
        for(int i = 1; i<=3; i++)
            for(int j = 1; j<=7; j++)
                baralho[cont++] = new Carta(i, j);
                
                
    }
    
    public Carta[] getBaralho() {
        return baralho;
    }

    //embaralha um array de cartas qualquer
    public static Carta[] embaralhar(Carta[] cartas) {


        List a = Arrays.asList(cartas);
        Collections.shuffle(a);
        cartas = (Carta[]) a.toArray();
        return cartas;

    }
    
    //Embaralha as cartas pertencentes ao baralho
    public Carta[] embaralhar() {
        
        
        List a = Arrays.asList(baralho);
        Collections.shuffle(a);
        baralho = (Carta[]) a.toArray();
        return baralho;
        
    }
    
    //Seleciona randomicamente 3 cartas, uma de cada tipo
    // e as retorna num array nas posicoes 0, 1 e 2.
    // seta para null as cartas que foram utilizadas
    public Carta[] retornaSolucao() {
        Random sorteador = new Random();
        int cont = 1;
        Carta[] solucao = new Carta[4];
        while(cont < 4) {
            while(true) {
                int sorteado = sorteador.nextInt(21);
                if(baralho[sorteado] != null) 
                    if(baralho[sorteado].getTipo() == cont){
                        solucao[cont] = baralho[sorteado];
                        baralho[sorteado] = null;
                        cont++;
                        break;
                    }
                   
                
            }
            
        }

        return Arrays.copyOfRange(solucao, 1,4 );
        
        
        
    }
    
    //retorna um array de 9 cartas para um dos jogadores
    //as cartas utilizadas são setadas para null no baralho
    //sendo assim o método pode ser executado 2 vezes
    //retornando conjuntos de cartas sem intersecção
    public Carta[] retornaCartasJogador() {
        Carta[] cartas = new Carta[9];
        int count = 0;
        for(int i = 0; i < 21; i++){
                       
            if(baralho[i] != null) {
                cartas[count] = baralho[i];
                baralho[i] = null;
                count++;
            }
            if(count == 9)
                break;
            
        }
        
        return cartas;
    }
    
    
    
    
    

}
