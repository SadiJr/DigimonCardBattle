package dominio;

import estatico.TipoSorteReves;

public class CartaSorteOuReves {

	private TipoSorteReves tipoDeCarta;
	private int valor;
	private String descricao;

    public CartaSorteOuReves(TipoSorteReves tipoDeCarta, int valor, String descricao) {
        this.tipoDeCarta = tipoDeCarta;
        this.valor = valor;
        this.descricao = descricao;
    }

    public TipoSorteReves getTipoDeCarta() {
        return tipoDeCarta;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}