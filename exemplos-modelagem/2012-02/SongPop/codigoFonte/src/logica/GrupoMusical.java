package logica;

public class GrupoMusical {
	protected String nome;
	protected GeneroMusical generoMusical;
	protected Sexo sexoCantor;
	protected int idGrupo;

	public GrupoMusical(int idGrupo, String nome, GeneroMusical generoMusical, Sexo sexoCantor) {
		this.idGrupo = idGrupo;
		this.generoMusical = generoMusical;
		this.nome = nome;
		this.sexoCantor = sexoCantor;			
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public GeneroMusical getGeneroMusical() {
		return generoMusical;
	}
	
	public Sexo getSexoCantor() {
		return sexoCantor;
	}
	
	public int getId() {
		return idGrupo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idGrupo;
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
		GrupoMusical other = (GrupoMusical) obj;
		if (idGrupo != other.idGrupo)
			return false;
		return true;
	}
}