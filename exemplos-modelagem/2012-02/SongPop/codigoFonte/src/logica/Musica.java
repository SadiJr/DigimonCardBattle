package logica;

public class Musica {
	protected int idMusica;
	protected String nome;
	protected GrupoMusical grupoMusical;

	public Musica(int idMusica, String nome, GrupoMusical grupoMusical) {
		this.idMusica = idMusica;
		this.nome = nome;
		this.grupoMusical = grupoMusical;		
	}
	
	public int getIdMusica() {
		return this.idMusica;
	}

	public String getNome() {
		return this.nome;
	}

	public GrupoMusical getGrupoMusical() {
		return this.grupoMusical;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMusica;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Musica other = (Musica) obj;
		if (idMusica != other.idMusica)
			return false;
		return true;
	}
}