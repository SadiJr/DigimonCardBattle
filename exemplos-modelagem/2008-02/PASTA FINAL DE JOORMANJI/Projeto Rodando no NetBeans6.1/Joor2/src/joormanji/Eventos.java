/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package joormanji;

import java.util.Vector;

/**
 *
 * @author adm
 */
public class Eventos {
    Vector <Desafio> osDesafios;
    Especial especial;
    
    public Eventos(){
        osDesafios = new Vector<Desafio>(10);
        criarDesafio();
        criarPerguntas();
    }
    
    public void criarPerguntas(){
        
        this.osDesafios.add(0, new Desafio("Elefantes vivem na Australia?", false));
        this.osDesafios.add(1, new Desafio("JooRmanji vale 10?", true));
        this.osDesafios.add(2, new Desafio("Existiam 5 personagens jogando Jumanji?", false));
        this.osDesafios.add(3, new Desafio("Adam morre no final do jogo?", false));
        this.osDesafios.add(4, new Desafio("Adam vence o jogo?", false));
        this.osDesafios.add(5, new Desafio("Elefantes vivem na India?", false));
        this.osDesafios.add(6, new Desafio("Elefantes vivem na Russia?", false));
        this.osDesafios.add(7, new Desafio("Elefantes vivem na Mongolia?", false));
        this.osDesafios.add(8, new Desafio("Elefantes vivem na China?", false));
        this.osDesafios.add(9, new Desafio("Existem elefantes no filme?", false));
        
    }
    
    public void criarDesafio(){
        especial = new Especial("Você tirou o dado que libertou Adam da Selva! Ele oferece gratidão.");
    }

    public Especial getEspecial() {
        return especial;
    }

    public void setEspecial(Especial especial) {
        this.especial = especial;
    }

    public Vector<Desafio> getOsDesafios() {
        return osDesafios;
    }

    public void setOsDesafios(Vector<Desafio> osDesafios) {
        this.osDesafios = osDesafios;
    }
    
    public boolean getEspecialUsado(){
        return this.especial.isUsado();
    }
    
    public void setEspecialUsado(){
        this.especial.setUsado(true);    
    }
    
    public Desafio getUmDesafio(){
        
        int index = (int)(Math.random()*10);
        System.out.println("O index é= "+index);
        return this.osDesafios.elementAt(index);    
    }
}
