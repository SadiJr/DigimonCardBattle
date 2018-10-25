/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Poderes;

import Poderes.TipoDePoderes.Custo;
import java.io.Serializable;

/**
 *
 * @author Matheus
 */
public class Poder implements Serializable{

    protected String nome;
    protected String descricao;
    protected Custo[] custos;

    public Poder(String nome, String descricao, Custo[] custo) {
        this.nome = nome;
        this.descricao = descricao;
        this.custos = custo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Custo[] getCustos() {
        return custos;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj!=null){
            Poder tmp = (Poder) obj;
            if(tmp.getNome().equals(getNome())){
                if(tmp.getDescricao().equals(getDescricao())){
                    if(tmp.getCustos().equals(getCustos())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        int result = 67;
        result = 3 * result + nome.hashCode();
        result = 3 * result + descricao.hashCode();
        result = 3 * result + custos.hashCode();
        return result;
    }

}
