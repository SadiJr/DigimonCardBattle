package model;

import br.ufsc.inf.leobr.cliente.Jogada;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Carta implements Jogada {

    public static final String caminho = "/images/";
    private String nome;
    private int valor;
    private ImageIcon imagemFoto;
    private int numero;
    private Naipe naipe;

    public Carta(String nome) {
        this.nome = nome;
        this.valor = this.calculaValor(nome);
        System.out.println(nome);
        this.imagemFoto = new javax.swing.ImageIcon(getClass().getResource(caminho + nome + ".png"));
    }


    public int calculaValor(String nome) {
        String letraNaipe = nome.substring(nome.length()-1, nome.length());
        int valorNaip = Naipe.getValorNaipe(letraNaipe);
        int num = Integer.parseInt(nome.substring(0, nome.length()-1));

        naipe = Naipe.getNaipePorValor(valorNaip);
        numero = num;

        return this.calculaValor(num, valorNaip);
    }
    
    private int calculaValor(int num, int naipe) {
        int val = 0;
        if (num > 3) {
            val = (num - 4) * 4 + naipe;
        } else {
            val = 40 + (num - 1) * 4 + naipe;
        }
        return val;
    }

    public ImageIcon getImagemFoto() {
        return imagemFoto;
    }

    public void setImagemFoto(ImageIcon imagemFoto) {
        this.imagemFoto = imagemFoto;
    }

   

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }


    public boolean cartaEhMaiorOutra(Carta carta){

        return this.valor > carta.getValor();

    }

    public int getNumero() {
        return numero;
    }

    public Naipe getNaipe() {
        return naipe;
    }




    

}
