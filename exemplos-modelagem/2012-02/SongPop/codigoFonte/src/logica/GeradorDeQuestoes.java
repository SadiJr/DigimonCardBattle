package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorDeQuestoes {
	protected GerenciadorDeMusicas gerenciadorDeMusicas;
	protected GerenciadorDeGruposMusicais gerenciadorDeGruposMusicais;
	protected Random random;	

	public GeradorDeQuestoes(GerenciadorDeMusicas gerenciadorDeMusicas, GerenciadorDeGruposMusicais gerenciadorDeGruposMusicais) {	
		this.gerenciadorDeMusicas = gerenciadorDeMusicas;
		this.gerenciadorDeGruposMusicais = gerenciadorDeGruposMusicais;
		random = new Random();
	}

	public Questao gerarQuestao(GeneroMusical generoMusical, Rodada rodada) {	
		Questao questao;
		int tipoRodada;
		boolean rodadaMusical;		
		if (rodada != null) {
			tipoRodada = rodada.getTipoRodada();
			rodadaMusical = rodada.isRodadaMusical();
		}
		else {
			tipoRodada = 0;
			rodadaMusical = true;
		}		
		if (tipoRodada == 0)
			if (rodadaMusical)
				questao = gerarQuestaoMusicalTipo0(generoMusical);
			else
				questao = gerarQuestaoSemMusicaTipo0(generoMusical);
		else
			if (rodadaMusical)
				questao = gerarQuestaoMusicalTipo1(generoMusical);
			else
				questao = gerarQuestaoSemMusicaTipo1(generoMusical);
		return questao;
	}
	
	private Questao gerarQuestaoMusicalTipo0(GeneroMusical generoMusical) {
		List<GrupoMusical> gruposMusicais = gerenciadorDeGruposMusicais.getGruposPorGenero(generoMusical);
		int limite = gruposMusicais.size();
		int indexGrupo = random.nextInt(limite);
		GrupoMusical grupoSorteado = gruposMusicais.get(indexGrupo);
		int idGrupo = grupoSorteado.getId();
		List<Musica> musicasDoGrupo = gerenciadorDeMusicas.getMusicasPorGrupo(idGrupo);
		List<Musica> musicasComMp3DoGrupo = gerenciadorDeMusicas.getMusicasComMp3PorGrupo(idGrupo);
		int alternativaCorreta = random.nextInt(5);	
		List<Musica> musicasGeradas = gerarMusicasDistintasQuestaoMusical(musicasDoGrupo, musicasComMp3DoGrupo, alternativaCorreta);
		String[] alternativas = gerarAlternativasTipo0(musicasGeradas);
		String pergunta = gerarPergunta(0);		
		Musica musicaResposta = musicasGeradas.get(alternativaCorreta);
		int idMusica = musicaResposta.getIdMusica();
		Questao questao = new QuestaoMusical(pergunta, alternativas, alternativaCorreta, idMusica);
		return questao;
	}
	
	private Questao gerarQuestaoSemMusicaTipo0(GeneroMusical generoMusical) {
		Questao questao;
		List<GrupoMusical> gruposDoGenero = gerenciadorDeGruposMusicais.getGruposPorGenero(generoMusical);
		List<Musica> musicasGeradas = gerarMusicasDistintasQuestaoSemMusica(gruposDoGenero);
		String[] alternativas = gerarAlternativasTipo0(musicasGeradas);
		String pergunta = gerarPergunta(0);
		int alternativaCorreta = random.nextInt(5);
		Musica musicaResposta = musicasGeradas.get(alternativaCorreta);
		String complemento = gerarComplemento(0, musicaResposta);
		questao = new QuestaoSemMusica(pergunta, alternativas, alternativaCorreta, complemento);			
		return questao;
	}
	
	private Questao gerarQuestaoMusicalTipo1(GeneroMusical generoMusical) {
		Questao questao;
		List<GrupoMusical> gruposMusicais = gerenciadorDeGruposMusicais.getGruposPorGenero(generoMusical);		
		int sexoInt = random.nextInt(2);
		Sexo sexo;
		if (sexoInt == 0)
			sexo = Sexo.MASCULINO;
		else
			sexo = Sexo.FEMININO;
		List<GrupoMusical> gruposGerados = gerarGruposDistintosQuestaoMusical(sexo, gruposMusicais);
		String[] alternativas = gerarAlternativasTipo1(gruposGerados);
		String pergunta = gerarPergunta(1);
		int alternativaCorreta = random.nextInt(5);
		GrupoMusical grupoResposta = gruposGerados.get(alternativaCorreta);
		int idGrupo = grupoResposta.getId();
		List<Musica> musicasDoGrupoResposta = gerenciadorDeMusicas.getMusicasComMp3PorGrupo(idGrupo);
		int limite = musicasDoGrupoResposta.size();
		int indexMusica = random.nextInt(limite);
		Musica musicaResposta = musicasDoGrupoResposta.get(indexMusica);
		int idMusica = musicaResposta.getIdMusica();
		questao = new QuestaoMusical(pergunta, alternativas, alternativaCorreta, idMusica);
		return questao;
	}
	
	private Questao gerarQuestaoSemMusicaTipo1(GeneroMusical generoMusical) {
		Questao questao;
		List<GrupoMusical> gruposMusicais = gerenciadorDeGruposMusicais.getGruposPorGenero(generoMusical);		
		List<GrupoMusical> gruposGerados = gerarGruposDistintosQuestaoSemMusica(gruposMusicais);
		String[] alternativas = gerarAlternativasTipo1(gruposGerados);
		String pergunta = gerarPergunta(1);
		int alternativaCorreta = random.nextInt(5);
		GrupoMusical grupoResposta = gruposGerados.get(alternativaCorreta);
		int idGrupo = grupoResposta.getId();
		List<Musica> musicasDoGrupoResposta = gerenciadorDeMusicas.getMusicasPorGrupo(idGrupo);
		int limite = musicasDoGrupoResposta.size();
		int indexMusica = random.nextInt(limite);
		Musica musicaResposta = musicasDoGrupoResposta.get(indexMusica);		
		String complemento = gerarComplemento(1, musicaResposta);
		questao = new QuestaoSemMusica(pergunta, alternativas, alternativaCorreta, complemento);			
		return questao;
	}
	
	private List<Musica> gerarMusicasDistintasQuestaoMusical(List<Musica> musicasDoGrupo, 
															 List<Musica> musicasComMp3DoGrupo, 
															 int alternativaCorreta) {
		List<Musica> musicasGeradas = new ArrayList<Musica>();
		List<Musica> musicasParaSorteio;
		for (int i = 0; i < 5; i++) {			
			Musica m;
			boolean jaExiste = true;
			do {
				if (i == alternativaCorreta)
					musicasParaSorteio = musicasComMp3DoGrupo;
				else
					musicasParaSorteio = musicasDoGrupo;
				int limite = musicasParaSorteio.size();
				int indice = random.nextInt(limite);
				m = musicasParaSorteio.get(indice);				
				jaExiste = musicasGeradas.contains(m);							
			}
			while (jaExiste);
			musicasGeradas.add(m);
		}
		return musicasGeradas;
	}
	
	private List<Musica> gerarMusicasDistintasQuestaoSemMusica(List<GrupoMusical> gruposDoGenero) {
		List<Musica> musicasGeradas = new ArrayList<Musica>();
		int limite = gruposDoGenero.size();
		List<Integer> idsGruposSorteados = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			boolean jaExiste;
			int idSorteado;
			do {
				int indexGrupo = random.nextInt(limite);
				GrupoMusical g = gruposDoGenero.get(indexGrupo);
				idSorteado = g.getId();
				jaExiste = idsGruposSorteados.contains(idSorteado);
			}
			while (jaExiste);
			idsGruposSorteados.add(idSorteado);
		}
		for (int i = 0; i < 5; i++) {			
			Musica m;
			boolean jaExiste;
			do {
				int idGrupo = idsGruposSorteados.get(i);
				List<Musica> musicasDoGrupo = gerenciadorDeMusicas.getMusicasPorGrupo(idGrupo);
				limite = musicasDoGrupo.size();
				int indice = random.nextInt(limite);
				m = musicasDoGrupo.get(indice);
				jaExiste = musicasGeradas.contains(m);							
			}
			while (jaExiste);
			musicasGeradas.add(m);
		}
		return musicasGeradas;
	}
	
	private List<GrupoMusical> gerarGruposDistintosQuestaoMusical(Sexo sexo, List<GrupoMusical> gruposMusicais) {
		List<GrupoMusical> gruposGerados = new ArrayList<GrupoMusical>();
		int limite = gruposMusicais.size();
		for (int i = 0; i < 5; i++) {
			boolean jaExiste;
			GrupoMusical g;
			do {
				int indice = random.nextInt(limite);
				g = gruposMusicais.get(indice);
				jaExiste = g.getSexoCantor() != sexo || gruposGerados.contains(g);
			}
			while (jaExiste);
			gruposGerados.add(g);
		}
		return gruposGerados;
	}
	
	private List<GrupoMusical> gerarGruposDistintosQuestaoSemMusica(List<GrupoMusical> gruposMusicais) {
		List<GrupoMusical> gruposGerados = new ArrayList<GrupoMusical>();
		int limite = gruposMusicais.size();
		for (int i = 0; i < 5; i++) {
			boolean jaExiste;
			GrupoMusical g;
			do {
				int indice = random.nextInt(limite);
				g = gruposMusicais.get(indice);
				jaExiste = gruposGerados.contains(g);
			}
			while (jaExiste);
			gruposGerados.add(g);
		}
		return gruposGerados;
	}

	private String gerarPergunta(int tipoRodada) {
		String pergunta;
		if (tipoRodada == 0)
			pergunta = "Qual é a música?";
		else
			pergunta = "Quem canta essa música?";
		return pergunta;
	}

	private String[] gerarAlternativasTipo1(List<GrupoMusical> gruposGerados) {
		String[] alternativas = new String[5];
		for (int i = 0; i < 5; i++) {
			GrupoMusical g = gruposGerados.get(i);
			String nomeGrupo = g.getNome();
			alternativas[i] = nomeGrupo;
		}		
		return alternativas;
	}
	
	private String[] gerarAlternativasTipo0(List<Musica> musicasGeradas) {
		String[] alternativas = new String[5];
		for (int i = 0; i < 5; i++) {
			Musica musicaGerada = musicasGeradas.get(i);
			String nomeMusica = musicaGerada.getNome();
			alternativas[i] = nomeMusica;
		}
		return alternativas;
	}

	private String gerarComplemento(int tipoRodada, Musica musicaResposta) {
		String complemento;
		if (tipoRodada == 0) {
			GrupoMusical grupo = musicaResposta.getGrupoMusical();
			complemento = grupo.getNome();
		}
		else {
			complemento = musicaResposta.getNome();
		}	
		return complemento;
	}
}