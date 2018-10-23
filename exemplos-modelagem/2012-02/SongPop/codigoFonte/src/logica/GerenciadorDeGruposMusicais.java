package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorDeGruposMusicais {
	protected Map<Integer, GrupoMusical> grupos;
	protected Map<String, GrupoMusical> gruposPorNome;
	protected Map<GeneroMusical, List<GrupoMusical>> gruposPorGenero;
	
	public GerenciadorDeGruposMusicais() {
		grupos = new HashMap<Integer, GrupoMusical>();
		gruposPorNome = new HashMap<String, GrupoMusical>();
		gruposPorGenero = new HashMap<GeneroMusical, List<GrupoMusical>>();
		preencheMapasDeGrupos();
	}
	
	public List<GrupoMusical> getGruposPorGenero(GeneroMusical genero) {
		return gruposPorGenero.get(genero);
	}
	
	private void preencheMapasDeGrupos() {		
		adicionaGruposRock();
		adicionaGruposMetal();
		adicionaGruposReligioso();
		adicionaGruposRap();
		adicionaGruposAxe();
		adicionaGruposSertanejo();
		adicionaGruposCountry();
		adicionaGruposMPB();
		adicionaGruposJovemGuarda();
		adicionaGruposSamba();
		adicionaGruposJazz();
		adicionaGruposPop();
	}
	
	private void adicionaGruposPop() {
		String[] nomesGrupos = new String[]{"Michael Jackson", "Justin Bieber", "Maroon 5", "Jonas Brothers", 
											"Justin Timberlake", "James Blunt", "Boyce Avenue", "Ne-Yo", 
											"Backstreet Boys", "Big Time Rush", "Sean Kingston"};
		adicionaGrupos(nomesGrupos, GeneroMusical.POP, Sexo.MASCULINO);

		nomesGrupos = new String[]{"Rihanna", "Demi Lovato", "Lady Gaga", "Katy Perry", "Britney Spears", 
									"Christina Aguilera", "Shakira", "Madonna", "Ke$ha", "Selena Gomez", "Carly Rae Jepsen"};
		adicionaGrupos(nomesGrupos, GeneroMusical.POP, Sexo.FEMININO);
	}

	private void adicionaGruposJazz() {
		String[] nomesGrupos = new String[]{"Jamie Cullum", "Frank Sinatra", "Leroy Hutson", "Louis Armstrong",
											"George Benson", "Dean Martin", "Tony Bennett"};		
		adicionaGrupos(nomesGrupos, GeneroMusical.JAZZ, Sexo.MASCULINO);

		nomesGrupos = new String[]{"Ella Fitzgerald", "Norah Jones", "Nina Simone", "Diana Krall",
								   "Madeleine Peyroux", "Sarah Vaughan", "Natalie Cole"};	   
		adicionaGrupos(nomesGrupos, GeneroMusical.JAZZ, Sexo.FEMININO);
	}

	private void adicionaGruposSamba() {
		String[] nomesGrupos = new String[]{"Zeca Pagodinho", "Martinho da Vila", "Diogo Nogueira", 
											"Arlindo Cruz", "Almir Guineto", "Fundo de Quintal",
											"Os Originais do Samba"};
		adicionaGrupos(nomesGrupos, GeneroMusical.SAMBA, Sexo.MASCULINO);

		nomesGrupos = new String[]{"Leci Brandão", "Beth Carvalho", "Alcione", "Roberta Sá",
								   "Dona Ivone Lara", "Jovelina Pérola Negra"};
		adicionaGrupos(nomesGrupos, GeneroMusical.SAMBA, Sexo.FEMININO);
	}

	private void adicionaGruposJovemGuarda() {
		String[] nomesGrupos = new String[]{"Roberto Carlos", "Erasmo Carlos", "Ronnie Von", "Wanderley Cardoso",
											"The Fevers", "Renato e Seus Blue Caps", "Márcio Greyck"};
		adicionaGrupos(nomesGrupos, GeneroMusical.JOVEM_GUARDA, Sexo.MASCULINO);

		nomesGrupos = new String[]{"Diana", "Vanusa", "Wanderlea", "Martinha",
								   "Rosemary", "Celly Campello", "Claudia Barroso"};
		adicionaGrupos(nomesGrupos, GeneroMusical.JOVEM_GUARDA, Sexo.FEMININO);
	}

	private void adicionaGruposMPB() {
		String[] nomesGrupos = new String[]{"Djavan", "Tim Maia","Belchior", "Chico Buarque",
											"Zé Ramalho", "Caetano Veloso", "Milton Nascimento"};
		adicionaGrupos(nomesGrupos, GeneroMusical.MPB, Sexo.MASCULINO);

		nomesGrupos = new String[]{"Elis Regina", "Marisa Monte", "Zélia Duncan", 
								   "Adriana Calcanhoto", "Gal Costa", "Ana Carolina", "Maria Bethânia"};
		adicionaGrupos(nomesGrupos, GeneroMusical.MPB, Sexo.FEMININO);
	}

	private void adicionaGruposCountry() {
		String[] nomesGrupos = new String[]{"Alan Jackson", "Rascal Flatts", "Kenny Rogers", 
											"Keith Urban", "Willie Nelson", "Garth Brooks"};
		adicionaGrupos(nomesGrupos, GeneroMusical.COUNTRY, Sexo.MASCULINO);

		nomesGrupos = new String[]{"Sugarland", "Shania Twain", "Carrie Underwood", "Jennette McCurdy", 								   
								   "Martina McBride", "Miranda Lambert"};
		adicionaGrupos(nomesGrupos, GeneroMusical.COUNTRY, Sexo.FEMININO);
	}

	private void adicionaGruposSertanejo() {
		String[] nomesGrupos = new String[]{"Bruno e Marrone", "César Menotti e Fabiano", "Daniel", "Leonardo", 
											"Zezé Di Camargo e Luciano", "Luan Santana", "Victor e Leo"};
		adicionaGrupos(nomesGrupos, GeneroMusical.SERTANEJO, Sexo.MASCULINO);

		nomesGrupos = new String[]{"Paula Fernandes", "Jéssica Queiróz", "Roberta Miranda",  
								   "As Marcianas", "Bruna e Karine", "Dayane Thavares"};
		adicionaGrupos(nomesGrupos, GeneroMusical.SERTANEJO, Sexo.FEMININO);
	}

	private void adicionaGruposAxe() {
		String[] nomesGrupos = new String[]{"Chiclete Com Banana", "Harmonia Do Samba", "Netinho", 
											"Banda Eva", "Timbalada", "Araketu"};
		adicionaGrupos(nomesGrupos, GeneroMusical.AXÉ, Sexo.MASCULINO);

		nomesGrupos = new String[]{"Margareth Menezes", "As Meninas", "Ivete Sangalo", 
								   "Claudia Leitte", "Daniela Mercury", "Cheiro De Amor"};
		adicionaGrupos(nomesGrupos, GeneroMusical.AXÉ, Sexo.FEMININO);
	}

	private void adicionaGruposRap() {
		String[] nomesGrupos = new String[]{"Akon", "Usher", "Jay-Z", "Kanye West", "Chris Brown", 
											"50 Cent", "Snoop Dogg"};		
		adicionaGrupos(nomesGrupos, GeneroMusical.RAP, Sexo.MASCULINO);

		nomesGrupos = new String[]{"Beyoncé", "Mariah Carey", "Alicia Keys", "Nelly Furtado", 
								   "Kelly Rowland", "Leona Lewis"};	   
		adicionaGrupos(nomesGrupos, GeneroMusical.RAP, Sexo.FEMININO);
	}

	private void adicionaGruposReligioso() {
		String[] nomesGrupos = new String[]{"Thalles Roberto", "Fernandinho", "Kleber Lucas", "Padre Marcelo Rossi",
											"André Valadão", "Marquinhos Gomes", "Rosa de Saron"};		
		adicionaGrupos(nomesGrupos, GeneroMusical.RELIGIOSO, Sexo.MASCULINO);

		nomesGrupos = new String[]{"Aline Barros", "Bruna Karla", "Fernanda Brum",
								   "Diante do Trono", "Rose Nascimento", "Cassiane", "Mara Lima"};	   
		adicionaGrupos(nomesGrupos, GeneroMusical.RELIGIOSO, Sexo.FEMININO);
	}

	private void adicionaGruposRock() {
		String[] nomesGrupos = new String[]{"Led Zeppelin", "Queen", "Pink Floyd", "Rush", "Black Sabbath", "KISS",
											"Metallica", "Nazareth", "Guns N' Roses", "Deep Purple", "Jimi Hendrix",
											"Creedence", "Van Halen", "AC/DC", "The Beatles", "The Rolling Stones",
											"The Doors"};
		adicionaGrupos(nomesGrupos, GeneroMusical.ROCK, Sexo.MASCULINO);
		
		nomesGrupos = new String[]{"Flyleaf", "The Letter Black", "Fireflight", "BarlowGirl", "Valora", "Halestorm",
								   "Eowyn",	"Sister Sin", "Hole", "4 Non Blondes", "The Donnas", "Paramore", 
								   "Joan Jett and the Blackhearts", "Evanescence"};
		adicionaGrupos(nomesGrupos, GeneroMusical.ROCK, Sexo.FEMININO);
	}
	
	private void adicionaGruposMetal() {
		String[] nomesGrupos = new String[]{"System Of a Down", "Avenged Sevenfold", "Slipknot", "Scorpions", "Megadeth",
											"Ozzy Osbourne", "Helloween", "Judas Priest", "Disturbed", "Sepultura", "Blind Guardian"};
		adicionaGrupos(nomesGrupos, GeneroMusical.METAL, Sexo.MASCULINO);

		nomesGrupos = new String[]{"Arch Enemy", "Epica", "Guano Apes", "Kittie", 
								   "Lacuna Coil", "Nightwish", "Otep", "Theatre Of Tragedy"};
		adicionaGrupos(nomesGrupos, GeneroMusical.METAL, Sexo.FEMININO);
	}

	private void adicionaGrupos(String[] nomesGrupos, GeneroMusical genero, Sexo sexo) {		
		List<GrupoMusical> lista = new ArrayList<GrupoMusical>();		
		for (int i = 0; i < nomesGrupos.length; i++) {
			int idGrupo = grupos.size();
			String nomeGrupo = nomesGrupos[i];
			GrupoMusical grupo = new GrupoMusical(idGrupo, nomeGrupo, genero, sexo);
			grupos.put(idGrupo, grupo);
			gruposPorNome.put(nomeGrupo, grupo);
			lista.add(grupo);
		}
		List<GrupoMusical> listaDoGenero = gruposPorGenero.get(genero);
		if (listaDoGenero != null)
			listaDoGenero.addAll(lista);
		else 
			gruposPorGenero.put(genero, lista);
	}
	
	public GrupoMusical getGrupo(String nomeGrupo) {
		return gruposPorNome.get(nomeGrupo);
	}
	
	public GrupoMusical getGrupo(int idGrupo) {
		return grupos.get(idGrupo);
	}
}