package dominio;

import estatico.PosicoesEnum;

public class Terreno extends Propriedade implements Posicao {

    private boolean hoteis;
    private int aluguel;
	private int aluguel1Casa;
	private int aluguel2Casa;
	private int aluguel3Casa;
	private int aluguel4Casa;
	private int custoPorCasa;
	private int custoHipoteca;
    private int hotel;
    private int comHotel;
    private int numeroDeCasas;

    public int getCustoPorCasa() {
        return custoPorCasa;
    }

    public double custoVenda1Casa(){
        return custoPorCasa*0.50;
    }

    public int decrementarCasa(){
        return --numeroDeCasas;
    }

    public void setCustoPorCasa(int custoPorCasa) {
        this.custoPorCasa = custoPorCasa;
    }

    public Terreno(PosicoesEnum posicoesEnum, int valor, int aluguel, int casa1, int casa2, int casa3, int casa4, int comHotel, int cadaCasa, int hotel, int hipoteca){
        super(posicoesEnum,valor,hipoteca);
        this.aluguel1Casa = casa1;
        this.aluguel2Casa = casa2;
        this.aluguel3Casa = casa3;
        this.aluguel4Casa = casa4;
        this.custoPorCasa = cadaCasa;
        this.hotel = hotel;
        this.aluguel = aluguel;
        this.comHotel = comHotel;
        
    }

    public int calcularHospedagem(int deslocamento) {
        int valor = 0;
        switch (numeroDeCasas) {
            case 0:
                valor = aluguel;
                break;
            case 1:
                valor = aluguel1Casa;
                break;
            case 2:
                valor = aluguel2Casa;
                break;
            case 3:
                valor = aluguel3Casa;
                break;
            case 4:
                valor = aluguel4Casa;
                break;
            case 5:
                valor = comHotel;
                break;
        }
        return valor;
    }

    public int getValorHipoteca(){
        return custoHipoteca;
    }

    public Integer getNumeroDeCasas() {
        return numeroDeCasas;
    }

    public void incrementar1Casa(){
        numeroDeCasas++;
    }

    public void setNumeroDeCasas(Integer numeroDeCasas) {
        this.numeroDeCasas = numeroDeCasas;
    }

    
}