package logica;

public enum GeneroMusical {
	ROCK, 
	METAL, 
	SAMBA, 
	RAP, 
	AXÉ, 
	SERTANEJO, 
	COUNTRY, 
	MPB, 
	JOVEM_GUARDA, 
	RELIGIOSO, 
	JAZZ, 
	POP;

	public String toString() {
		String genero;
		if (ordinal() == GeneroMusical.MPB.ordinal())
			genero = "MPB";
		else if (ordinal() == GeneroMusical.JOVEM_GUARDA.ordinal())
			genero = "Jovem Guarda";
		else
			genero = name().charAt(0) + name().substring(1).toLowerCase();
		
		return genero;
	}
}