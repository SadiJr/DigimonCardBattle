package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorDeMusicas {
	protected Map<Integer, Musica> musicasPorId;
	protected Map<Integer, List<Musica>> musicasPorGrupo, musicasComMp3PorGrupo;

	public GerenciadorDeMusicas(GerenciadorDeGruposMusicais gerenciadorDeGrupos) {
		musicasPorId = new HashMap<Integer, Musica>();
		musicasPorGrupo = new HashMap<Integer, List<Musica>>();
		musicasComMp3PorGrupo = new HashMap<Integer, List<Musica>>();
		preencheMapasDeMusicas(gerenciadorDeGrupos);
	}
	
	public List<Musica> getMusicasPorGrupo(int idGrupo) {
		return musicasPorGrupo.get(idGrupo);
	}
	
	public List<Musica> getMusicasComMp3PorGrupo(int idGrupo) {
		return musicasComMp3PorGrupo.get(idGrupo);
	}
	
	public Musica getMusica(int idMusica) {
		return musicasPorId.get(idMusica);
	}
	
	private void preencheMapasDeMusicas(GerenciadorDeGruposMusicais gerenciadorDeGrupos) {			
		adicionaMusicasRock(gerenciadorDeGrupos);
		adicionaMusicasMetal(gerenciadorDeGrupos);
		adicionaMusicasReligioso(gerenciadorDeGrupos);
		adicionaMusicasRap(gerenciadorDeGrupos);
		adicionaMusicasAxe(gerenciadorDeGrupos);
		adicionaMusicasSertanejo(gerenciadorDeGrupos);
		adicionaMusicasCountry(gerenciadorDeGrupos);
		adicionaMusicasMPB(gerenciadorDeGrupos);
		adicionaMusicasJovemGuarda(gerenciadorDeGrupos);
		adicionaMusicasSamba(gerenciadorDeGrupos);
		adicionaMusicasJazz(gerenciadorDeGrupos);
		adicionaMusicasPop(gerenciadorDeGrupos);
	}

	private void adicionaMusicasPop(GerenciadorDeGruposMusicais gerenciadorDeGrupos) {
		/***************POP - VOCAL MASCULINO**********************/
		//Michael Jackson
		GrupoMusical grupo = gerenciadorDeGrupos.getGrupo("Michael Jackson");
		String[] nomesMusicas = new String[]{"You Are Not Alone", "Smooth Criminal", "Ben", "Thriller",
											 "Heal The World", "Beat It", "I'll Be There", "Man In The Mirror", 
											 "They Don't Care About Us", "Human Nature"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Billie Jean"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Justin Bieber
		grupo = gerenciadorDeGrupos.getGrupo("Justin Bieber");
		nomesMusicas = new String[]{"Beauty And A Beat", "As Long As You Love Me ", "Boyfriend", "Be Alright", 
									"Believe", "Baby", "Fall", "Love Me", "One Time", "Take You",
									"Never Let You Go", "A Very Justin Bieber Christmas!"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"U Smile"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
	
		//Maroon 5
		grupo = gerenciadorDeGrupos.getGrupo("Maroon 5");
		nomesMusicas = new String[]{"One More Night", "She Will Be Loved", "Daylight", "This Love", 
									"Misery", "Goodnight Goodnight", "Moves Like Jagger",
									"Makes Me Wonder", "Sad", "Won't Go Home Without You"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Sunday Morning"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Jonas Brothers
		grupo = gerenciadorDeGrupos.getGrupo("Jonas Brothers");
		nomesMusicas = new String[]{"Give Love a Try", "When You Look Me In The Eyes",
									"Wedding Bells", "Dance Until Tomorrow", "Lovebug", "Fly With Me",
									"Falling slowly", "S.O.S.", "Please Be Mine", "Burnin' Up", "This Is Our Song"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Gotta Find You"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Justin Timberlake
		grupo = gerenciadorDeGrupos.getGrupo("Justin Timberlake");
		nomesMusicas = new String[]{"Suit & Tie", "Cry Me A River", "SexyBack",
									"My Love", "Dead and Gone", "My Love", "Summer Love", "Ayo Technology",
									"Se�orita", "(And She Said) Take Me Now", "Love Stoned", "Like I Love You"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"What Goes Around... Comes Around"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//James Blunt
		grupo = gerenciadorDeGrupos.getGrupo("James Blunt");
		nomesMusicas = new String[]{"Same Mistake", "You're Beautiful", "Goodbye My Lover", 
									"High", "I'll Be Your Man", "Stay The Night", "'Cause I Love You", "1973",
									"Calling Out Your Name", "Tears And Rain", "Wiseman", "If Time Is All I Have"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Carry You Home"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Boyce Avenue
		grupo = gerenciadorDeGrupos.getGrupo("Boyce Avenue");
		nomesMusicas = new String[]{"Find Me", "On My Way", "What Makes You Beautiful", 
									"Payphone", "Heaven", "Just The Way You Are", "Superman", 
									"Every Breath", "Titanium", "Dare To Believe", "Change Your Mind" };
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Broken Angel"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Ne-Yo
		grupo = gerenciadorDeGrupos.getGrupo("Ne-Yo");
		nomesMusicas = new String[]{"Mad", "So Sick", "Because Of You", 
									"Miss Independent", "Sexy Love", "Never Knew I Needed", "Do You",
									"One In A Million", "Forever Now", "Closer (I Just Can't Stop)"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Let Me Love You"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Backstreet Boys
		grupo = gerenciadorDeGrupos.getGrupo("Backstreet Boys");
		nomesMusicas = new String[]{"As Long As You Love Me", "Incomplete", "Drowning",
									"Inconsolable", "Everybody (Backstreet's Back)", "All I Have To Give",
									"Show Me The Meaning Of Being Lonely", "Shape Of My Heart",
									"10,000 Promises", "Quit Playing Games (With My Heart)", "Loverboy"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"I Want It That Way"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Big Time Rush
		grupo = gerenciadorDeGrupos.getGrupo("Big Time Rush");
		nomesMusicas = new String[]{"Windows Down", "Big Night", "Boyfriend", 
									"Oh Yeah", "Any Kind Of Guy", "All Over Again", "Love Me Love Me",
									"Paralyzed", "Elevate", "Invisible"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Worldwide"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Sean Kingston
		grupo = gerenciadorDeGrupos.getGrupo("Sean Kingston");
		nomesMusicas = new String[]{"Fire Burning", "Letting Go", "Back 2 Life",
									"Me Love", "Your Sister", "Addicted", "Love Me", "Big Girls Don't Cry",
									"Dry Your Eyes", "All I Want Is You", "Drummer Boy"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Beautiful Girls"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		/***************POP - VOCAL FEMININO**********************/
		//Rihanna
		grupo = gerenciadorDeGrupos.getGrupo("Rihanna");
		nomesMusicas = new String[]{"Diamonds", "Where Have You Been", "California King Bed",
									"Stay", "Take A Bow", "Man Down", "Hate That I Love You",
									"A Child Is Born", "Te Amo", "You Da One"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"We Found Love"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Demi Lovato
		grupo = gerenciadorDeGrupos.getGrupo("Demi Lovato");
		nomesMusicas = new String[]{"Give Your Heart a Break", "Fix a Heart", "Don't Forget", 
									"Shut Up And Love Me", "My Love Is Like a Star", "Catch Me", "La La Land",
									"Unbroken", "Together", "Believe In Me", "Here We Go Again"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Skyscraper"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Lady Gaga
		grupo = gerenciadorDeGrupos.getGrupo("Lady Gaga");
		nomesMusicas = new String[]{"Born This Way", "Bad Romance", "You And I", "Poker Face",
									"Cake Like Lady Gaga ", "Judas", "Marry The Night",
									"Alejandro", "Hair", "The Edge Of Glory", "Government Hooker", "Just Dance"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Paparazzi"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Katy Perry
		grupo = gerenciadorDeGrupos.getGrupo("Katy Perry");
		nomesMusicas = new String[]{"Firework", "Wide Awake", "The One That Got Away", "Thinking Of You",
									"Part Of Me", "Hot N' Cold", "I Kissed A Girl",
									"California Gurls", "Last Friday Night", "Peacock", "E.T."};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Teenage Dream"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Britney Spears
		grupo = gerenciadorDeGrupos.getGrupo("Britney Spears");
		nomesMusicas = new String[]{"Toxic", "Everytime", "Criminal", "I Wanna Go", "Till The World Ends",
									"Scream & Shout", "Womanizer", "Piece Of Me",
									"Baby One More Time", "I'm A Slave 4 U", "Sometimes"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Oops!... I Did It Again"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Christina Aguilera
		grupo = gerenciadorDeGrupos.getGrupo("Christina Aguilera");
		nomesMusicas = new String[]{"Blank Page", "Your Body", "Beautiful", "Hurt", "Fighter",
									"Lady Marmalade", "Lotus", "Just a Fool",
									"Army Of Me", "Bound To You", "Ain't No Other Man"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Not Myself Tonight"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Shakira
		grupo = gerenciadorDeGrupos.getGrupo("Shakira");
		nomesMusicas = new String[]{"Estoy Aqui", "Waka Waka", "Addicted To You", "Loca",
									"La Tortura", "Gypsy", "Hips Don't Lie", "Ojos As�",
									"Whenever, Wherever", "She Wolf", "Antolog�a"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Rabiosa"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Madonna
		grupo = gerenciadorDeGrupos.getGrupo("Madonna");
		nomesMusicas = new String[]{"Like A Virgin", "Give Me All Your Luvin", "Like A Prayer",
									"Turn Up The Radio", "La Isla Bonita", "Vogue", "Hung Up",
									"Animal", "Crazy For You", "Frozen", "Masterpiece"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Girl Gone Wild"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Ke$ha
		grupo = gerenciadorDeGrupos.getGrupo("Ke$ha");
		nomesMusicas = new String[]{"C'mon", "Die Young", "Tik Tok", "Your Love Is My Drug", "Supernatural", 
									"Warrior", "Crazy Kids", "We R Who We R", "Blow", 
									"Blah Blah Blah", "Only Wanna Dance With You", "Take It Off"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Tik Tok"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Selena Gomez
		grupo = gerenciadorDeGrupos.getGrupo("Selena Gomez");
		nomesMusicas = new String[]{"Love You Like a Love Song", "Who Says",
									"Hit The Lights", "Naturally", "Magic", "Tell Me Something I Don't Know",
									"My Dilemma", "Round & Round", "Shake It Up", "Middle Of Nowhere"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"A Year Without Rain"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Carly Rae Jepsen
		grupo = gerenciadorDeGrupos.getGrupo("Carly Rae Jepsen");
		nomesMusicas = new String[]{"This Kiss", "Good Time", "Tug Of War",
									"Curiosity", "Beautiful", "Melt With You", "Sour Candy", 
									"Almost Said It", "Tonight I'm Getting Over You", "Worldly Matters"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Call Me Maybe"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
	}

	private void adicionaMusicasJazz(GerenciadorDeGruposMusicais gerenciadorDeGrupos) {
		/***************JAZZ - VOCAL MASCULINO**********************/
		//Jamie Cullum
		GrupoMusical grupo = gerenciadorDeGrupos.getGrupo("Jamie Cullum");
		String[] nomesMusicas = new String[]{"Everlasting Love", "Singing In The Rain", 
											 "Next Year, Baby", "I Only Have Eyes For You", "Oh God", "I'm All Over It", 
											 "Gran Torino", "Don't Stop the Music", "These Are The Days", "High And Dry",
											 "Twentysomething", "All At Sea", "What A Difference A Day Made"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Mind Trick"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Frank Sinatra
		grupo = gerenciadorDeGrupos.getGrupo("Frank Sinatra");
		nomesMusicas = new String[]{"My Way", "Fly Me To The Moon", "'s Wonderful",
								    "Strangers In The Night", "As Time Goes By", "(I've Got You) Under My Skin", 
								    "That's Life", "Something Stupid", "I've Got You Under My Skin", "Can't Take My Eyes Of You",
								    "Cheek To Cheek", "Let Me Try Again", "The Way You Look Tonight"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"New York, New York"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Leroy Hutson
		grupo = gerenciadorDeGrupos.getGrupo("Leroy Hutson");
		nomesMusicas = new String[]{"So in Love With You", "Love, Oh Love", "Getting It On", "Time Brings on a Change",
									"I'll Be There, I'll Still Care ", "I'm in Love With You Girl", 
									"As Long as There's Love Around", "Can't Say Enough About Mom",
									"Gotta Move - Gotta Groove ", "Ella Weez", "Give This Love A Try",
									"The Ghetto '74", "After The Fight", "Could This Be Love", "Dudley Do-Right"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"When You Smile"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Louis Armstrong
		grupo = gerenciadorDeGrupos.getGrupo("Louis Armstrong");
		nomesMusicas = new String[]{"Moon River", "La Vie En Rose", "Learnin' The Blues",
									"As Time Goes By", "(Oh)Didn't He Ramble", "When You're Smiling", 
									"Let's Call The Whole Thing Off", "Summer Time",
									"A Kiss To Build A Dream On", "Dream a Little Dream Of Me", 
									"Let My People Go", "Over the Rainbow", "Nobody Knows The Trouble I've Seen"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"What a Wonderful World"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//George Benson
		grupo = gerenciadorDeGrupos.getGrupo("George Benson");
		nomesMusicas = new String[]{"In your eyes", "Nothing's Gonna Change My Love For You", "Give Me The Night",
									"Kisses In The Moonlight", "The Greatest Love Of All", "On Broadway",
									"You Are The Love Of My Life", "This Masquerade", "Love X Love", "20-20",
									"Being With You", "Nothing's Gonna Change My Love For You", "Stephanie"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Never Give Up On A Good Thing"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Dean Martin
		grupo = gerenciadorDeGrupos.getGrupo("Dean Martin");
		nomesMusicas = new String[]{"Thats Amore", "Sway", "Baby Won't You Please Come Home", "Let It Snow!", "Mambo Italiano",
									"Dont You Remember?", "My Rifle, My Pony and Me", "It's a Good Day", "Innamorata",
									"Volare", "Return To Me", "'Til I Find You", "Ain't That A Kick In The Head?"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Everybody Loves Somebody"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Tony Bennett
		grupo = gerenciadorDeGrupos.getGrupo("Tony Bennett");
		nomesMusicas = new String[]{"The Lady Is a Tramp", "I Left My Heart In San Francisco", "Body And Soul",
									"Blue Velvet", "A stranger in paradise", "Smile", "The Shadow Of Your Smile", 
									"If I Ruled The World", "For Once In My Life", "Because With You",
									"The Very Thought of You", "La Vie En Rose", "Speak Low"}; 
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"The Way You Look Tonight"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		/***************JAZZ - VOCAL FEMININO**********************/
		//Ella Fitzgerald
		grupo = gerenciadorDeGrupos.getGrupo("Ella Fitzgerald");
		nomesMusicas = new String[]{"Let's Do It (Let's Fall In Love)", "Night And Day", "Mack the Knife",
									"Ev'ry Time We Say Goodbye", "Bewitched, Bothered And Bewildered", "Easy To Love",
									"(I Was) Born To Be Blue", "What Is This Thing Called Love?",
									"Someone To Watch Over Me"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Black Coffee"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Norah Jones
		grupo = gerenciadorDeGrupos.getGrupo("Norah Jones");
		nomesMusicas = new String[]{"Come Away With Me", "Don't Know Why", "Sunrise", "Happy Pills",
									"What Am I To You?", "Any Other Day", "4 Broken Hearts", "Those Sweet Words",
									"Chasing Pirates", "Say Goodbye", "She", "Thinking About You"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Turn Me On"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Nina Simone
		grupo = gerenciadorDeGrupos.getGrupo("Nina Simone");
		nomesMusicas = new String[]{"Feeling Good", "I Put a Spell on You", "Ain't Got No / I Got Life",
									"Love Me or Leave Me", "Don't Let Me Be Misunderstood", "22nd Century",
									"My Baby Just Cares for Me", "Ne Me Quitte Pas", "Here Comes The Sun", "Go To Hell",
									"After You've Gone", "Just In Time", "I Wish I Knew How It Would Feel To Be Free"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Sinnerman"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Diana Krall
		grupo = gerenciadorDeGrupos.getGrupo("Diana Krall");
		nomesMusicas = new String[]{"The Look Of Love", "I've Got You Under My Skin", "Just The Way You Are",
									"'s Wonderful", "Let's Fall in Love", "Love Is Where You Are",
									"Bye Bye Blackbird", "Temptation", "So Nice", "I Miss You So"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Walk On By"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Madeleine Peyroux
		grupo = gerenciadorDeGrupos.getGrupo("Madeleine Peyroux");
		nomesMusicas = new String[]{"I'm All Right", "Dance Me To The End Of Love", "J'ai Deux Amours", 
									"You're Gonna Make Me Lonesome When You Go", "La Javanaise", "La Vie En Rose", 
									"Weary Blues", "Don't Cry Baby", "(Getting Some) Fun Out of Life", 
									"Smile", "Instead", "Walkin' After Midnight", "Don't Wait Too Long"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Careless Love"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Sarah Vaughan
		grupo = gerenciadorDeGrupos.getGrupo("Sarah Vaughan");
		nomesMusicas = new String[]{"Whatever Lola wants", "A Lover's Concerto", 
									"My Favorite Things", "Peter Gunn", "Tenderly", "Bridges", 
									"Broken-Hearted Melody", "Lover Man", "Smoke Gets In Your Eyes"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Fever"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Natalie Cole
		grupo = gerenciadorDeGrupos.getGrupo("Natalie Cole");
		nomesMusicas = new String[]{"Unforgettable", "This Will Be", "Miss You Like Crazy", "Wild Women Do",
									"L-O-V-E", "Cry Me a River", "Say You Love Me", "When I Fall In Love", 
									"(I've Seen) Paradise", "I Wish You Love", "It's Crazy", "Better Than Anything"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Calling You"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
	}

	private void adicionaMusicasSamba(GerenciadorDeGruposMusicais gerenciadorDeGrupos) {
		/***************SAMBA - VOCAL MASCULINO**********************/
		//Zeca Pagodinho
		GrupoMusical grupo = gerenciadorDeGrupos.getGrupo("Zeca Pagodinho");
		String[] nomesMusicas = new String[]{"Deixa a Vida me Levar", "Canto pra Ogum", 
											"Ogum", "Em Um Outdoor", "Maneiras", "Quando A Gira Girou", "Minha F�", 
											"O Canto Da Sereia", "Vou Botar Teu Nome Na Macumba",
											"� Dist�ncia", "Faixa Amarela", "Aquilo Que Era Mulher"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Verdade"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Martinho da Vila
		grupo = gerenciadorDeGrupos.getGrupo("Martinho da Vila");
		nomesMusicas = new String[]{"Mulheres", "Disritmia", "Ex Amor", "Aquarela Brasileira",
									"O Pequeno Burgu�s", "Casa de Bamba", "Devagar, Devagarinho", "Madalena do Jucu",
									"A Carne � Fraca", "Grande Amor", "Festa de Umbanda", "Quem � do Mar N�o Enj�a"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Canta Canta, Minha Gente"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Diogo Nogueira
		grupo = gerenciadorDeGrupos.getGrupo("Diogo Nogueira");
		nomesMusicas = new String[]{"F� em Deus", "Espelho", "Deixa Eu Te Amar", "Deus � Mais", "Verdade Chinesa",
									"A lua de um poeta", "For�a Maior", "T� Fazendo a Minha Parte", "Al�m do espelho",
									"A Vit�ra Demora Mas Vem", "Sou Eu"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);		
		nomesMusicas = new String[]{"Me Leva"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Arlindo Cruz
		grupo = gerenciadorDeGrupos.getGrupo("Arlindo Cruz");
		nomesMusicas = new String[]{"Meu Lugar", "O Que � o Amor?", "Ainda � tempo pra ser feliz",
									"O Bem", "Ser� Que � Amor?", "A Pureza da Flor", "Chegamos ao fim", "Vai Embora Tristeza",
									"Al�m do meu querer", "Esquenta (Samba da Regina)", "A Casa"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Meu Nome � Favela"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Almir Guineto
		grupo = gerenciadorDeGrupos.getGrupo("Almir Guineto");
		nomesMusicas = new String[]{"Conselho", "Brilho no Olhar", "Insensato Destino", "Mel Na Boca",
									"Saco Cheio", "Meiguice Descarada", "Jib�ia", "Lama Nas Ruas", "Mordomia",
									"A Vaca", "M�os", "Dura Miss�o"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Caxambu"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Fundo de Quintal
		grupo = gerenciadorDeGrupos.getGrupo("Fundo de Quintal");
		nomesMusicas = new String[]{"A Amizade", "O Show Tem Que Continuar", "Sorriso Negro",
									"Amor dos Deuses", "Hoje Eu Vou Pagodear", "A Batucada dos Nossos Tant�s",
									"Parab�ns Pra Voc�", "Eu N�o Quero Mais", "E Eu N�o Fui Convidado", "Facho de Esperan�a"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Nosso Grito"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Os Originais do Samba
		grupo = gerenciadorDeGrupos.getGrupo("Os Originais do Samba");
		nomesMusicas = new String[]{"Falador Passa Mal", "Esperan�as Perdidas", "Alegrias De Domingo",
									"O Ouro e a Madeira", "Cabe�a Que N�o Tem Ju�zo", "A Dona Do Primeiro Andar",
									"Trem das Onze", "Na Subida Do Morro", "A vida � assim", "Nego V�io Quando Morre",
									"Do Lado Direito Da Rua Direita"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"O Assassinato do Camar�o"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		/***************SAMBA - VOCAL FEMININO**********************/
		//Leci Brand�o
		grupo = gerenciadorDeGrupos.getGrupo("Leci Brand�o");
		nomesMusicas = new String[]{"Anjos da Guarda", "Perdoa", "Agenda", "Fogueira de Uma Paix�o", "Negro Zumbi",
									"Eu s� quero te Namorar", "Z� do Caro�o", "Isso � Fundo de Quintal", "Papai Vadiou",
									"Meu Sorriso", "A Filha da Dona Lecy", "Barco a Vela"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Sentimento Nu"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Beth Carvalho
		grupo = gerenciadorDeGrupos.getGrupo("Beth Carvalho");
		nomesMusicas = new String[]{"�gua de Chuva no Mar", "Vou Festejar", "Camar�o Que Dorme a Onda Leva",
									"O mundo � um moinho", "Eu s� pe�o a Deus", "Coisinha do Pai", "Andan�as",
									"1800 Colinas", "Ainda � Tempo Pra Ser Feliz", "Acreditar", "Se Voc� Jurar"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Tristeza"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Alcione
		grupo = gerenciadorDeGrupos.getGrupo("Alcione");
		nomesMusicas = new String[]{"N�o Deixe O Samba Morrer", "Voc� Me Vira a Cabe�a", "Meu �bano",
									"Estranha Loucura", "Mulher Ideal", "A Loba", "Al�m Da Cama", 
									"Faz Uma Loucura Por Mim", "Minha Estranha Loucura!", "Sufoco", "To Fazendo Amor",
									"O Que Eu Fa�o Amanh�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Meu V�cio � Voc�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Roberta S�
		grupo = gerenciadorDeGrupos.getGrupo("Roberta S�");
		nomesMusicas = new String[]{"Samba de Um Minuto", "Mais Algu�m", "Pavilh�o de Espelhos", "Cicatrizes",
									"Ah, Se Eu Vou", "Segunda Pele", "Falsa Baiana", "A Flor e o Espinho", "Pressentimento",
									"Cansei De Esperar Voc�", "Belo Estranho Dia de Amanh�", "Chega de Saudade"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Fogo e Gasolina"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
			
		//Dona Ivone Lara
		grupo = gerenciadorDeGrupos.getGrupo("Dona Ivone Lara");
		nomesMusicas = new String[]{"Algu�m me Avisou", "Mas quem disse que eu te Esque�o", "Sonho Meu",
									"Tend�ncia", "Samba, Minha Ra�z", "Minha Verdade", "Ti�", "Sorriso de Crian�a",
									"A Cigana", "T�ndencia", "A For�a do Criador", "Enredo do meu samba"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Acreditar"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Jovelina P�rola Negra
		grupo = gerenciadorDeGrupos.getGrupo("Jovelina P�rola Negra");
		nomesMusicas = new String[]{"Sorriso Aberto", "O Dia se Zangou", "Orgulho Negro", 
									"Feirinha da Pavuna", "Menina Voc� Bebeu", "No Mesmo Manto", "Catatau", 
									"Sonho Juvenil", "Baga�o da Laranja", "33, Destino Dom Pedro II",
									"Banho de Felicidade", "Sorriso de um Banjo"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Luz do Repente"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
	}

	private void adicionaMusicasJovemGuarda(GerenciadorDeGruposMusicais gerenciadorDeGrupos) {
		/***************JOVEM_GUARDA - VOCAL MASCULINO**********************/	
		//Roberto Carlos
		GrupoMusical grupo = gerenciadorDeGrupos.getGrupo("Roberto Carlos");
		String[] nomesMusicas = new String[]{"Esse Cara Sou Eu", "Como � Grande o Meu Amor Por Voc�", "Nossa Senhora",
											 "Detalhes", "Furd�ncio", "Como Vai Voc�", "Amor Sem Limite",
											 "Furd�ncio", "Amor Perfeito", "Falando Serio", "Emo��es", "A Dist�ncia"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Outra Vez"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
				
		//Erasmo Carlos
		grupo = gerenciadorDeGrupos.getGrupo("Erasmo Carlos");
		nomesMusicas = new String[]{"A Pra�a", "Cavaleiro de Aruanda", "Amor, Nada Mais!", "Cachoeira",
									"Meu Bem", "A Catedral", "Tranquei A Vida", "Ritmo Da Chuva", "O Carpinteiro",
									"Banda da Ilus�o", "Diana", "Pequeno Pr�ncipe", "Escuta meu amor"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Sentado � Beira Do Caminho"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Ronnie Von
		grupo = gerenciadorDeGrupos.getGrupo("Ronnie Von");
		nomesMusicas = new String[]{"Mesmo Que Seja Eu", "Gatinha Manhosa", "Pega Na Mentira",
									"Sou Uma Crian�a, N�o Entendo Nada", "Mais Um Na Multid�o",
									"Carta",  "Pode Vir Quente Que Eu Estou Fervendo", "Cacha�a Mec�nica",
									"Festa de Arromba", "Sentado � Beira Do Caminho", "Filho �nico", 
									"Sementes do Amanh�", "A Semana Inteira"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"O Manequim"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//The Fevers
		grupo = gerenciadorDeGrupos.getGrupo("The Fevers");
		nomesMusicas = new String[]{"Guerra dos Sexos", "Mar de Rosas", "Por Causa de Voc�", "Marcas Do Que Se Foi",
									"Onde Est�o Teus Olhos Negros", "C�ndida", "Ningu�m Vive Sem Amor",
									"A Gente Era Feliz E N�o Sabia", "Se Voc� Me Quiser", "Nathalie",
									"Agora eu Sei", "Hey Girl", "Pra Cima, Pra Baixo"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"A Carruagem"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Renato e Seus Blue Caps
		grupo = gerenciadorDeGrupos.getGrupo("Renato e Seus Blue Caps");
		nomesMusicas = new String[]{"Feche Os Olhos", "N�o Me Diga Adeus Jamais", "A Primeira L�grima", "Meu Primeiro Amor",
									"A Irm� Do Meu Melhor Amigo", "365 Dias", "A Esperan�a � a �ltima Que Morre",
									"Menina Linda", "A Garota Que Eu Quero", "Meu Bem N�o Me Quer", "Ana",
									"Menina Feia", "N�o Te Esquecerei", "Mem�rias"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Playboy"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//M�rcio Greyck
		grupo = gerenciadorDeGrupos.getGrupo("M�rcio Greyck");
		nomesMusicas = new String[]{"O Mais Importante � o Verdadeiro Amor", "Apar�ncias", 
									"Impossivel Acreditar Que Perdi Voc�", "Eu preciso de voc�",
									"Vivendo Por Viver", "Reencontro", "Como � triste dizer boa noite",
									"Voc� Veio", "Faz tanto tempo", "Sem Voc� N�o Viverei", "Como um dia Nascer",
									"Eu S� Quero Amor", "N�o Sei Onde Te Encontrar", "O travesseiro"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Infinito"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Wanderley Cardoso
		grupo = gerenciadorDeGrupos.getGrupo("Wanderley Cardoso");
		nomesMusicas = new String[]{"O Bom Rapaz", "Caminhada", "Cem Ovelhas", "Quando o amor se transforma em poesia",
									"Hist�ria De Amor", "Minha Namorada", "Fale Baixinho", "Cora��o de carne", "Adeus Ingrata",
									"O Inimigo Quer Calar a Sua Voz", "A decis�o � Jesus", "Piquenique", "Agora Eu Sou Feliz"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Doce de Coco"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		/***************JOVEM_GUARDA - VOCAL FEMININO**********************/
		//Diana
		grupo = gerenciadorDeGrupos.getGrupo("Diana");
		nomesMusicas = new String[]{"A M�sica da Minha Vida", "Apenas Um Amigo", "A Vida Que Tanto Sonhei", 
									"Abre a Porta e Diz Bye Bye", "Agora Que Sou Livre", "Ainda queima a esperan�a", 
									"Ainda Sou Mais Eu", "Algu�m Pra Me Fazer Feliz", "Amava Todas As Mulheres", 
									"Amor S� Se Paga Com Amor", "Balan�o Manu�h", "Diz Bye Bye",
									"Campo Verde, Flor Selvagem", "Can��o dos Namorados", "Conflito", 
									"Deixa O Sol Entrar Na Sua Casa", "Desfrutar De Ti", "Desculpa L�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"A Outra Metade"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Vanusa
		grupo = gerenciadorDeGrupos.getGrupo("Vanusa");
		nomesMusicas = new String[]{"1971", "A Aranha", "A Espingarda de Rolha", "� La Carte", "A Vida N�o Pode Parar", 
									"Agora Eu Sei", "Al�m Das Alian�as", "Amigos Novos e Antigos", "Arco-�ris",
									"Aonde Est�s", "Aos Nossos Filhos", "Apaixonada", "Aprendendo a Jogar",
									"At�mico Plat�nico", "Artista Eu", "Amor Desempregado", "Alumiou"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Momentos De Amor"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Wanderlea
		grupo = gerenciadorDeGrupos.getGrupo("Wanderlea");
		nomesMusicas = new String[]{"A Felicidade Bate � Sua Porta", "A Terceira For�a", "Acho Que Vou Lhe Esquecer",
									"Atende-me", "Bicho Medo", "Boa Noite, Meu Bem", "Caf�", "Capela do Amor",
									"Chuva, Suor e Cerveja", "Coisas Da Vida", "D�-Me Felicidade", "� O Tempo Do Amor"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Atenda-me"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Martinha
		grupo = gerenciadorDeGrupos.getGrupo("Martinha");
		nomesMusicas = new String[]{"Agua Caliente", "Aqui", "Batuque Na Minha Janela", "Cilada", "Nossa Can��o",
									"Copo De Vinho", "Escuta", "Eu Daria a Minha Vida", "Eu Te Amo Mesmo Assim", 
									"Eu Vou", "Gosto De Voc�", "Meu Namorado", "N�o Brinque Assim", "Natureza do Amor"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Barra Limpa"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Rosemary
		grupo = gerenciadorDeGrupos.getGrupo("Rosemary");
		nomesMusicas = new String[]{"A Dan�a dos Brotos", "Como Pode Acontecer", "Duvida", "Emo��es Diferentes",
									"Feiti�o de Broto", "Folhas Secas", "Foram-se Os An�is", "Horas Perdidas", "J�ia",
									"Juro Por Deus", "Leva Tudo Contigo", "Mulheres Decididas", "Nossa Can��o", "O Barco"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Carne e Osso"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Celly Campello
		grupo = gerenciadorDeGrupos.getGrupo("Celly Campello");
		nomesMusicas = new String[]{"A Esta��o", "Banho de Lua", "Billy", "Lacinhos Cor de Rosa",
									"Bonnie e Clyde", "Eternamente", "Broto Legal", "Can�rio",
									"Chovendo em Meu Cora��o", "Deixa Estar Como Esta", "Diz Que Me Amas"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"A Lenda da Conchinha"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Claudia Barroso
		grupo = gerenciadorDeGrupos.getGrupo("Claudia Barroso");
		nomesMusicas = new String[]{"A Noite do Meu Bem", "A Vida � Mesmo Assim", "Abra�a-me", "Adeus Solid�o",
									"Ah! Se eu fosse voc�", "Ai Y� Y� Linda Flor", "Alian�a Devolvida", 
									"Atiraste Uma Pedra", "Brigas", "Can��o Do Mar", "Casa E Comida", 
									"Castigo", "Cinderela", "Contrasenso", "Amor Proibido", "A Partida"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Se Deus Me Ouvisse"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
	}

	private void adicionaMusicasMPB(GerenciadorDeGruposMusicais gerenciadorDeGrupos) {
		/***************MPB - VOCAL MASCULINO**********************/	
		//Caetano Veloso
		GrupoMusical grupo = gerenciadorDeGrupos.getGrupo("Caetano Veloso");
		String[] nomesMusicas = new String[]{"Sozinho", "N�o Enche", "Voc� N�o Me Ensinou a Te Esquecer", "Sorte",
									"O Le�ozinho", "Nosso Estranho Amor", "Ora��o Ao Tempo", "Sampa", "Alegria, Alegria",
									"C�u De Santo Amaro", "O Quereres", "London, London", "S� Vou Gostar de Quem Gosta de Mim"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Voc� � Linda"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
				
		//Chico Buarque
		grupo = gerenciadorDeGrupos.getGrupo("Chico Buarque");
		nomesMusicas = new String[]{"Apesar De Voc�", "Olhos Nos Olhos", "O Meu Amor", "Mulheres de Atenas",
									"Jo�o e Maria", "Roda Viva", "Constru��o", "Peda�o de Mim", "Ciranda da Bailarina",
									"Cotidiano", "C�lice", "A Banda", "Quando o Carnaval Chegar"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Eu Te Amo"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Tim Maia
		grupo = gerenciadorDeGrupos.getGrupo("Tim Maia");
		nomesMusicas = new String[]{"Gostava Tanto de Voc�", "Voc�", "N�o Quero Dinheiro", "Primavera",
									"Um Dia de Domingo", "Me D� Motivo", "O Descobridor Dos Sete Mares", "Eu Amo Voc�",
									"Sossego", "Bom Senso", "A Bela e a Fera", "Do Leme Ao Pontal", "Leva"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Azul da Cor do Mar"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Djavan
		grupo = gerenciadorDeGrupos.getGrupo("Djavan");
		nomesMusicas = new String[]{"Se", "Nem um dia", "Eu Te Devoro", "Oceano", "Flor de Lis", "Fato Consumado",
									"Sina", "P�tala", "Lil�s", "Samurai", "A Carta", "Linha Do Equador", "Meu Bem-Querer"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Um Amor Puro"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
				
		//Z� Ramalho
		grupo = gerenciadorDeGrupos.getGrupo("Z� Ramalho");
		nomesMusicas = new String[]{"Ch�o de Giz", "Sin�nimos", "Av�hai", "Entre A Serpente E A Estrela",
									"Admir�vel Gado Novo", "Mist�rios da Meia-Noite", "Tocando Em Frente", "Frevo Mulher",
									"Batendo Na Porta Do C�u", "Admir�vel Gado Novo", "T�xi Lunar", "Cidad�o", "A �rvore"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Garoto de Aluguel"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Milton Nascimento
		grupo = gerenciadorDeGrupos.getGrupo("Milton Nascimento");
		nomesMusicas = new String[]{"Can��o Da Am�rica", "Maria, Maria", "Ca�ador de Mim", "Quem sabe isso quer dizer amor",
									"Cora��o de Estudante", "Travessia", "Bola de Meia, Bola de Gude",
									"Outro lugar", "Um Girassol da Cor de Seu Cabelo", "Nada Ser� Como Antes", 
									"Me Deixa Em Paz", "Nos Bailes da Vida", "Encontros e Despedidas"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Paisagem da Janela"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Belchior
		grupo = gerenciadorDeGrupos.getGrupo("Belchior");
		nomesMusicas = new String[]{"Alucina��o", "� Palo Seco", "Tudo Outra Vez", "Divina Com�dia Humana",
									"Velha Roupa Colorida", "Cora��o Selvagem", 
									"Paralelas", "Medo de Avi�o", "Fotografia 3X4", "Saia do meu caminho",
									"Sujeito De Sorte", "Pequeno Mapa do Tempo", "Apenas Um Rapaz Latino-americano"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Como Nossos Pais"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
				
		/***************MPB - VOCAL FEMININO**********************/
		//Elis Regina
		grupo = gerenciadorDeGrupos.getGrupo("Elis Regina");
		nomesMusicas = new String[]{"Me Deixas Louca", "Fascina��o",  
									"Romaria", "Al�, Al�, Marciano", "Atr�s da Porta",
									"�guas de Mar�o", "Madalena", "Tiro ao Alvaro", "Aprendendo a Jogar",
									"Andan�a", "O B�bado e A Equilibrista", "S� Tinha De Ser Com Voc�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Casa No Campo"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Marisa Monte
		grupo = gerenciadorDeGrupos.getGrupo("Marisa Monte");
		nomesMusicas = new String[]{"Depois", "N�o V� Embora", "Velha Inf�ncia", "Mais Um Na Multid�o",
									"Amor I Love You", "Lenda das Sereias", "Ainda Lembro", "Gentileza", 
									"N�o � F�cil", "A Alma E A Mat�ria", "Beija Eu", "Bem Que Se Quis", "A Sua"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Ainda Bem"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Z�lia Duncan
		grupo = gerenciadorDeGrupos.getGrupo("Z�lia Duncan");
		nomesMusicas = new String[]{"Tudo sobre voc�", "Alma", "A Idade Do C�u", "Breve Can��o De Sonho", 
									"Enquanto Durmo", "N�o V� Ainda", "Carne e Osso",  "Sentidos", "Jura Secreta",
									"Tua Boca", "Todos Os Verbos", "L� Vou Eu", "Quem Canta Seus Males Espanta"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Catedral"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
				
		//Adriana Calcanhoto
		grupo = gerenciadorDeGrupos.getGrupo("Adriana Calcanhoto");
		nomesMusicas = new String[]{"Mentiras", "Fico Assim Sem Voc�", "E O Mundo Nao Se Acabou",
									"Devolva-Me", "Vambora", "Ciranda da Bailarina", "Do Fundo do Meu Cora��o", "Metade",
									"Sou Eu Assim Sem Voc�", "Gatinha Manhosa", "Senhas", "Esquadros"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Medo de Amar"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Gal Costa
		grupo = gerenciadorDeGrupos.getGrupo("Gal Costa");
		nomesMusicas = new String[]{"Modinha Para Gabriela", "Neguinho", "Vapor Barato",
									"Vapor Barato", "L�grimas Negras", "� D'oxum", "Chuva de Prata", 
									"Nada Mais", "Festa do Interior", "Barato Total"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Baby"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Ana Carolina
		grupo = gerenciadorDeGrupos.getGrupo("Ana Carolina");
		nomesMusicas = new String[]{"Simplesmente Aconteceu", "Quem de N�s Dois", "� Isso A�", "Rosas", "Aqui",
									"Confesso", "Garganta", "Encostar Na Tua", "Evid�ncias", "Vai", "Nua", "Pra Rua Me Levar"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Problemas"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Maria Beth�nia
		grupo = gerenciadorDeGrupos.getGrupo("Maria Beth�nia");
		nomesMusicas = new String[]{"Gostoso Demais", "Sonho Imposs�vel", "Brincar de Viver", "Cora��o Ateu",
									"Iemanj� Rainha do Mar", "Reconvexo", "Carta de Amor", "Sonho Meu", "Fera Ferida",
									"Onde Estar� O Meu Amor", "Cartas de Amor", "Debaixo D'agua", "Vive"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Cheiro de Amor"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
	}

	private void adicionaMusicasCountry(GerenciadorDeGruposMusicais gerenciadorDeGrupos) {
		/***************Country - VOCAL MASCULINO**********************/
		//Alan Jackson
		GrupoMusical grupo = gerenciadorDeGrupos.getGrupo("Alan Jackson");
		String[] nomesMusicas = new String[]{"I'll Try", "Livin' On Love", "'Tis So Sweet to Trust in Jesus",
											 "Chattahoochee", "Country Boy", "Little Bitty", "I'll Go On Loving You",
											 "A Good Year For The Roses", "Gone Country", "When Somebody Loves You",
											 "Good Time", "Everything I Love", "Here In The Real World", "A Love Like That"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Remember When"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);			
		
		//Rascal Flatts
		grupo = gerenciadorDeGrupos.getGrupo("Rascal Flatts");
		nomesMusicas = new String[]{"My Wish", "Life Is a Highway", 
									"Bless The Broken Road", "Every Day", "Come Wake Me Up", "I Won't Let Go",
									"Stand", "Nothing Like This", "Why", "Forever", "A Little Home",
									"Feels Like Today", "These Days", "Here Comes Goodbye"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"What Hurts The Most"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Kenny Rogers
		grupo = gerenciadorDeGrupos.getGrupo("Kenny Rogers");
		nomesMusicas = new String[]{"You And I", "Lady", "We've Got Tonight", "Islands In The Stream",
									"Just Dropped In", "Til I Can Make It on My Own", "The Gambler", "Just Dropped In",
									"I Can't Unlove You", "Make no Mistakes, She's Mine", "You Decorated My Life",
									"A Little More Love", "A Love Song", "Crazy In Love"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"She Believes In Me"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
				
		//Keith Urban
		grupo = gerenciadorDeGrupos.getGrupo("Keith Urban");
		nomesMusicas = new String[]{"Stupid Boy", "You'll Think Of Me", "Tonight I Wanna Cry",
									"Only You Can Love Me This Way", "For You", "Raining On Sunday", "Making Memories Of Us",
									"All For You", "Sweet Thing", "Long Hot Summer", "I Wanna Love Somebody Like You", 
									"Your Everything", "But For The Grace Of God", "A Girl Like That"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Somebody Like You"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Willie Nelson
		grupo = gerenciadorDeGrupos.getGrupo("Willie Nelson");
		nomesMusicas = new String[]{"Always On My Mind", "Crazy", "Blue Eyes Crying In The Rain",
									"She Is Gone", "Whiskey River", "You Are Always On My Mind", "$1000 Wedding",
									"Are You Sure", "Angel Flying Too Close To The Ground", "He Was a Friend Of Mine",
									"Seven Spanish Angels", "The Scientist", "A Whiter Shade Of Pale"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"On The Road Again"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Garth Brooks
		grupo = gerenciadorDeGrupos.getGrupo("Garth Brooks");
		nomesMusicas = new String[]{"If Tomorrow Never Comes", "The Dance", "Friends In Low Places",
									"Callin' Baton Rouge", "What She's Doing Now", "Standing Outside Of Fire", 
									"Unanswered Prayers", "That Summer", "The Thunder Rolls", "When You Come Back To Me Again",
									"The River", "More Than a Memory", "That Girl Is A Cowboy"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Standing Outside The Fire"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
						
		/***************Country - VOCAL FEMININO**********************/
		//Sugarland
		grupo = gerenciadorDeGrupos.getGrupo("Sugarland");
		nomesMusicas = new String[]{"Stay", "Stuck Like Glue", "Tonight",
									"Something More", "Baby Girl", "All We Are", "50 Cent Lovin'",
									"It Happens", "Fly Away", "Just Might (Make Me Believe)", "Love",
									"Little Miss", "Hello", "Down In Mississipi (Up To No Good)"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"All I Want To Do"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
				
		//Shania Twain
		grupo = gerenciadorDeGrupos.getGrupo("Shania Twain");
		nomesMusicas = new String[]{"Man! I Feel Like a Woman", "You're Still The One", "Don't",
									"Endless Love", "Forever And For Always", "You've Got A Way", "When You Kiss Me",
									"(Don t Gimme That) Once Over", "From This Moment On", "I'm Gonna Getcha Good!",
									"That Don't Impress Me Much", "The Woman In Me (Needs The Man In You)", "Up!", "Ka-ching!"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Any Man Of Mine"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Carrie Underwood
		grupo = gerenciadorDeGrupos.getGrupo("Carrie Underwood");
		nomesMusicas = new String[]{"Good Girl", "Just A Dream", "So Small", "There's a Place for Us", "All-American Girl",
									"Blown Away", "Before He Cheats", "Ever Ever After",
									"I Told You So", "How Great Thou Art", "Last Name", "Cowboy Casanova", 
									"Someday When I Stop Loving You", "Jesus, Take The Wheel"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Two Black Cadillacs"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Jennette McCurdy
		grupo = gerenciadorDeGrupos.getGrupo("Jennette McCurdy");
		nomesMusicas = new String[]{"Better", "Generation Love", "So Close", "Broken Umbrella",
									"Homeless Heart", "Put Your Arms Around Someone", "Break Your Heart", "Stronger",
									"Don't You Just Hate Those People", "Me With You", "Love Is On The Way",
									"Have To Say Goodbye", "Mcdonalds Rap", "Generation Love"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Not That Far Away"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
						
		//Martina McBride
		grupo = gerenciadorDeGrupos.getGrupo("Martina McBride");
		nomesMusicas = new String[]{"Anyway", "In My Daughter's Eyes", "I'm Gonna Love You Through It", 
									"All The Things We've Never Done", "There You Are", "Born To Give My Love To You", 
									"A Great Disguise", "Valentine", "I Love You", "Always Be This Way",
									"Broken Wing", "My Baby Loves Me", "A Woman Knows", "Where Would You Be"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Concrete Angel"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Miranda Lambert
		grupo = gerenciadorDeGrupos.getGrupo("Miranda Lambert");
		nomesMusicas = new String[]{"Over You", "Crazy Ex-Girlfriend", "Jack Daniels", 
									"Gunpowder & Lead", "More Like Her", "Run Daddy Run", "Fastest Girl In Town", 
									"Dead Flowers", "Mama's Broken Heart", "White Liar",
									"Kerosene", "I Wanna Die", "Baggage Claim", "Getting Ready"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"The House That Built Me"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);		
	}

	private void adicionaMusicasSertanejo(GerenciadorDeGruposMusicais gerenciadorDeGrupos) {
		/***************SERTANEJO - VOCAL MASCULINO**********************/
		//Bruno e Marrone
		GrupoMusical grupo = gerenciadorDeGrupos.getGrupo("Bruno e Marrone");
		String[] nomesMusicas = new String[]{"J� N�o Sei Mais Nada", "Amor N�o Vai Faltar", 
											"Telefone Mudo", "Eu N�o Vou Aceitar", "Vidro Fum�", "Bijuteria",
											"Agora Vai", "Por Um Minuto", "Juras de Amor"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Choram as Rosas"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//C�sar Menotti e Fabiano
		grupo = gerenciadorDeGrupos.getGrupo("C�sar Menotti e Fabiano");
		nomesMusicas = new String[]{"Se Fosse Eu", "Como Um Anjo", 
									"Esperando na janela", "Estressada", "Ciumenta", "Amor Em Dobro", 
									"Leil�o", "Caso por acaso", "O Que Tiver Que Vir, Vir�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Tarde Demais"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Daniel
		grupo = gerenciadorDeGrupos.getGrupo("Daniel");
		nomesMusicas = new String[]{"Pra Ser Feliz", "Os Amantes", "E Agora",
									"O Menino da Porteira", "Estou Apaixonado", "Meu Reino Encantado",
									"Evid�ncias", "Declara��o de Amor", "Desatino"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Adoro Amar Voc�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Leonardo
		grupo = gerenciadorDeGrupos.getGrupo("Leonardo");
		nomesMusicas = new String[]{"A fila anda", "Zuar e Beber", "Beber, Beber",
									"Baby Fala Pra Mim", "Al�m do Sol, Al�m do Mar",
									"Esse Algu�m Sou Eu", "Um Sonhador", "Temporal de Amor", "�gua de C�co"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Entre Tapas e Beijos"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Zez� Di Camargo e Luciano
		grupo = gerenciadorDeGrupos.getGrupo("Zez� Di Camargo e Luciano");
		nomesMusicas = new String[]{"� o Amor", "Dois Cora��es E Uma Hist�ria", 
									"No Dia Em Que Eu Sa� de Casa", "Mentes T�o Bem", "Cria��o Divina",
									"Voc� Vai Ver", "Dou A Vida Por Um Beijo", "Pare", "Pra Mudar A Minha Vida"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Sonho de Amor"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Luan Santana
		grupo = gerenciadorDeGrupos.getGrupo("Luan Santana");
		nomesMusicas = new String[]{"Sogr�o Caprichou", "Incondicional", 
									"Nega", "Amar N�o � Pecado", "Hoje N�o", "Amigos Pela F�", 
									"Esqueci de Te Esquecer", "Voc� de Mim N�o Sai", "100% Voc�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Te Vivo"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Victor e Leo
		grupo = gerenciadorDeGrupos.getGrupo("Victor e Leo");
		nomesMusicas = new String[]{"Quando Voc� Some", "N�o Me Perdoei", 
									"Boa Sorte Pra Voc�", "Amigo Apaixonado", "Tem Que Ser Voc�",
									"Vida Boa", "�gua de Oceano", "Fotos", "Meu Eu Em Voc�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Borboletas"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		/***************SERTANEJO - VOCAL FEMININO**********************/
		//Paula Fernandes
		grupo = gerenciadorDeGrupos.getGrupo("Paula Fernandes");
		nomesMusicas = new String[]{"Cuidar Mais de Mim", "P�ssaro de Fogo", "Pra Voc�",
									"N�o Precisa", "Sensa��es", "Meu Eu em Voc�", "Ainda Ontem Chorei de Saudade",
									"Sem Voc�", "Quero Sim", "Eu Quero Ser Pra Voc�", "Al�m da Vida"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Eu Sem Voc�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//J�ssica Queir�z
		grupo = gerenciadorDeGrupos.getGrupo("J�ssica Queir�z");
		nomesMusicas = new String[]{"Ombro Amigo", "Se Voc� Me Pegar", "Ou � 8 ou � 80", 
									"Caso Mal Resolvido", "Quando voc� me olhou", "Eu Amo", "Medo Bobo",
									"Anjo", "Chamego Ou Xaveco", "� Tudo Maravilha", "Cenas com voc�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Brinquedo Caro"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Roberta Miranda
		grupo = gerenciadorDeGrupos.getGrupo("Roberta Miranda");
		nomesMusicas = new String[]{"A Majestade, O Sabi�", "Cad� voc�?", 
									"Faz Amor Comigo", "A Rural II", "V� Com Deus", 
									"A Casa Das Mariquinhas", "Meu Dengo", "Hist�ria de Amor", 
									"�dolo de Pano", "Gaivota", "Voc� � Tudo Que Pedi Pra Deus"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"S�o Tantas Coisas"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//As Marcianas
		grupo = gerenciadorDeGrupos.getGrupo("As Marcianas");
		nomesMusicas = new String[]{"O Mo deuso (as marcinas)", "Lembran�as de Casa", "Quando Eu Mais Precisei De Voc�",
									"Vou Te Amarrar Na Minha Cama", "Amantes", "Eu Amo e N�o Sou Correspondida",
									"Amor Paix�o", "Porque Brigamos", "Nossa Melodia Preferida",
									"Aquele Beijo", "Vou Te Prender", "Amor Que N�o Tem Jeito", "A Vida N�o Vale Um Centavo",
									"Algu�m Que Me Lembra Voc�", "Aquele Beijo Que Te Dei", "Vem C� Caminhoneiro"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Cai da Cama"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Bruna e Karine
		grupo = gerenciadorDeGrupos.getGrupo("Bruna e Karine");
		nomesMusicas = new String[]{"Te Amo Pra Caramba", "J� Percebeu",
									"A Pista Certa", "Reinventar", "Minha Mania", "Se Eu Pudesse",
									"Estranho Sentimento", "D� o Fora", "Desejo Por Desejo", "Meu pensamento � voc�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Eu Quero S� Voc�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Dayane Thavares
		grupo = gerenciadorDeGrupos.getGrupo("Dayane Thavares");
		nomesMusicas = new String[]{"O Velho Ta Podendo", "�gua mole em pedra dura", "Nuvem que Passa",
									"Beijinho Doc�", "Eu Quero ser Feliz", "Nossa can��o de amor",
									"Hoje Tem Rodeio", "T� de Cara Com Voc�", "Brigadeiro de Panela", 
									"Brasil Terra da poesia", "O Rosto e o Resto"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Cedo de Mais Pra Esquecer"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
	}

	private void adicionaMusicasAxe(GerenciadorDeGruposMusicais gerenciadorDeGrupos) {
		/***************AX� - VOCAL MASCULINO**********************/
		//Chiclete Com Banana
		GrupoMusical grupo = gerenciadorDeGrupos.getGrupo("Chiclete Com Banana");
		String[] nomesMusicas = new String[]{"100% Voc�", "Diga Que Valeu", "Voa Voa", "Meu Cora��o Voou",
											 "A Fila Andou", "Quero Chiclete", "Se Me Chamar Eu Vou", "Selva Branca",
											 "Amor Perfeito", "Mega", "Chicleteiro Eu, Chicleteira Ela"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"N�o vou Chorar"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
				
		//Harmonia Do Samba
		grupo = gerenciadorDeGrupos.getGrupo("Harmonia Do Samba");
		nomesMusicas = new String[]{"Meus Sentimentos", "Uma Chance", "Choveu",
									"Comando", "Alegria no Ar", "Meu Amor, Meu Amorzinho", "Vem Nen�m",
									"A Gente Sacode", "Agachadinho", "Excesso de Amor", "Pintou Harmonia"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Quebrou a Cara"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Netinho
		grupo = gerenciadorDeGrupos.getGrupo("Netinho");
		nomesMusicas = new String[]{"Milla", "Gostoso Demais", "Onde Voc� Se Esconde", "A Vida � Festa",
									"Baianidade Nag�", "Sereia", "Tem Jeito N�o", "Extrapolou", "Balan�o Legal"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Menina"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Banda Eva
		grupo = gerenciadorDeGrupos.getGrupo("Banda Eva");
		nomesMusicas = new String[]{"Anjo", "Circulou", "Poema Anjo", "S� eu e voc�", "T�o Sonhada",
									"Eva", "Me Abra�a", "Preta", "Nosso amor � lindo"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Encontro Marcado"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	

		//Timbalada
		grupo = gerenciadorDeGrupos.getGrupo("Timbalada");
		nomesMusicas = new String[]{"Beija-Fl�r", "Um Tantinho Assim", "Mimar Voc�", "Minha Hist�ria",
									"Alegria Original", "�gua Mineral", "Ashans�",
									"T� Na Mulher", "Regando-te", "P� de Pr�dio", "S� Pra Me Ver"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"A Latinha"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Araketu
		grupo = gerenciadorDeGrupos.getGrupo("Araketu");
		nomesMusicas = new String[]{"Minha Raz�o de Viver", "Pipoca", "Amantes", "Sempre Ser�",
									"Carta Branca", "Face Oculta", "Mal Acostumado",
									"Nossa Guerra Santa", "Cobertor"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Araketu Bom Demais"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
				
		/***************AX� - VOCAL FEMININO**********************/
		//Margareth Menezes
		grupo = gerenciadorDeGrupos.getGrupo("Margareth Menezes");
		nomesMusicas = new String[]{"Amor Ainda", "Dandalunda", "Fara� Divindade Do Egito", "Cordeiro De Nan�",
									"Filho Da Bahia", "Bonap� (Um � �mpar, Dois � Par)", "Abra A Boca E Feche Os Olhos",
									"Eu Quero � Botar Meu Bloco Na Rua", "Il� Que Fala De Amor", "Lua Candeia", "Fara�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Pra Voce Meu Amor"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//As Meninas
		grupo = gerenciadorDeGrupos.getGrupo("As Meninas");
		nomesMusicas = new String[]{"Xibom Bombom", "T� Faltando Homem", "Tapinha",
									"Tapa Aqui, Descobre Ali", "Babaca Mentiroso", "Esquisito", 
									"Esconde-Esconde", "Feij�o Com Arroz", "Jeito meigo", "Taboca", "Cambalacho"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Nega Maluca"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Ivete Sangalo
		grupo = gerenciadorDeGrupos.getGrupo("Ivete Sangalo");
		nomesMusicas = new String[]{"Se Eu N�o Te Amasse Tanto Assim", 
									"Eu Nunca Amei Algu�m Como Eu Te Amei", "No Brilho Desse Olhar", 
									 "Beleza Rara", "Agora Eu J� Sei", "Me Abra�a e Me Beija",
									"Cole��o", "Frisson", "Arer�", "Sorte Grande ", "Na Base do Beijo"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Quando a Chuva Passar"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Claudia Leitte
		grupo = gerenciadorDeGrupos.getGrupo("Claudia Leitte");
		nomesMusicas = new String[]{"Pensando Em Voc�", "Bem Vindo Amor", "Preto", "Falando S�rio",
									"P�ssaros", "Amor Perfeito", "Beijar na Boca", "Extravasa", "Bola de Sab�o"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Largadinho"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Daniela Mercury
		grupo = gerenciadorDeGrupos.getGrupo("Daniela Mercury");
		nomesMusicas = new String[]{"O Canto da Cidade", "� Primeira Vista", 
									"Nobre Vagabundo", "Vapor Barato", "M�sica de Rua", 
									"Rapunzel", "Maimb� Dand�", "Protesto Olodum", "Dona Can�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Pensar Em Voc�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Cheiro de Amor
		grupo = gerenciadorDeGrupos.getGrupo("Cheiro De Amor");
		nomesMusicas = new String[]{"Pensa Em Mim", "Amor de Sobremesa", "Chama da Paix�o", 
									"Tudo a Ver", "Canto Ao Pescador", "Esperando Na Janela", "Pureza da Paix�o",
									"Vai Sacudir, Vai Abalar", "Al�m da Can��o", "Bye, Bye Solid�o"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Dias de Sol"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
	}

	private void adicionaMusicasRap(GerenciadorDeGruposMusicais gerenciadorDeGrupos) {
		/***************RAP - VOCAL MASCULINO**********************/
		//Akon
		GrupoMusical grupo = gerenciadorDeGrupos.getGrupo("Akon");
		String[] nomesMusicas = new String[]{"I Wanna Love You", "Right Now", "That Na Na", "Hold My Hand", "Still Will",
											"Don't Matter", "Beautiful", "Smack That", 
											"Used To Know", "Dangerous", "So Special", "Sorry, Blame It On Me"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Lonely"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);		
		
		//Usher
		grupo = gerenciadorDeGrupos.getGrupo("Usher");
		nomesMusicas = new String[]{"Scream", "Climax", "Omg", "Dive", "U Got It Bad", "Oh My Gosh", 
									"DJ Got Us Fallin' In Love", "Burn", "Numb", "Yeah", "Same Girl", "Moving Mountains"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Lemme See"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Jay-Z
		grupo = gerenciadorDeGrupos.getGrupo("Jay-Z");
		nomesMusicas = new String[]{"Empire State Of Mind", "Young Forever", "Anything", 
									"Nigga What, Nigga Who", "Welcome To New York", "Upgrade U", "Who Gon Stop Me",
									"Dead Presidents", "Beware of The Boys", "What More Can I Say?"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"99 Problems"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Kanye West
		grupo = gerenciadorDeGrupos.getGrupo("Kanye West");
		nomesMusicas = new String[]{"No Church In The Wild", "Heartless", "Runaway", 
									"Gold Digger", "Power ", "All Falls Down", "Amazing", "Good Life", 
									"All Of The Lights", "Flashing Lights", "Love Lockdown"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Stronger"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Chris Brown
		grupo = gerenciadorDeGrupos.getGrupo("Chris Brown");
		nomesMusicas = new String[]{"Don't Wake Me Up", "Don't Judge Me", "With You", "Turn Up The Music", 
									"Say Goodbye", "Forever", "All Back", "Crawl", "Kiss Kiss",
									"100 Bottles", "Yo (Excuse Me Miss)", "Beautiful People", "Should've Kissed You"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Yeah 3x"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//50 Cent
		grupo = gerenciadorDeGrupos.getGrupo("50 Cent");
		nomesMusicas = new String[]{"Candy Shop", "Many Men", "21 Questions", "Ayo Technology",
									"Just a Lil' Bit", "Baby By Me", "Down On Me", "You Don't Know", "P.I.M.P.",
									"Disco Inferno", "I'll Whip Ya Head Boy", "Wanksta", "Window Shopper"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"In Da Club"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Snoop Dogg
		grupo = gerenciadorDeGrupos.getGrupo("Snoop Dogg");
		nomesMusicas = new String[]{"Sensual Seduction", "Sweat", "Still Dre", 
									"(O.J.) Wake up", "The Next Episode", "Vato", "Boss Life",
									"Who Am I (What's My Name)?", "That's That", "Pronto",
									"Gin And Juice", "Murder Was The Case", "Sexual Eruption"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Smoke Weed Everyday"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
						
		/***************RAP - VOCAL FEMININO**********************/
		//Beyonc�
		grupo = gerenciadorDeGrupos.getGrupo("Beyonc�");
		nomesMusicas = new String[]{"I Was Here", "If I Were a Boy", "Listen", "Best Thing I Never Had",
									"Love On Top", "Broken-hearted Girl", "Irreplaceable", "1+1", "Run The World (Girls)",
									"End Of Time", "Single Ladies (Put A Ring On It)", "Diva", "Sweet Dreams", "Dance For You"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Halo"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Mariah Carey
		grupo = gerenciadorDeGrupos.getGrupo("Mariah Carey");
		nomesMusicas = new String[]{"Hero", "I Want To Know What Love Is", "Without You", 
									"All I Want For Christmas Is You", "My All", "Bye Bye",
									"Against All Odds", "100 Percent", "Touch My Body", "Always Be My Baby",
									"I Still Believe", "Anytime You Need A Friend", "We Belong Together"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Obsessed"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Alicia Keys
		grupo = gerenciadorDeGrupos.getGrupo("Alicia Keys");
		nomesMusicas = new String[]{"No One", "If I Ain't Got You", "Brand New Me", "Fallin'",
									"New York", "Empire State of Mind (Part II)", "Empire State of Mind",
									"Doesn't Mean Anything", "You Don't Know My Name", "New Day",
									"101", "Superwoman", "I Need You", "Try Sleeping With A Broken Heart"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Girl On Fire"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Nelly Furtado
		grupo = gerenciadorDeGrupos.getGrupo("Nelly Furtado");
		nomesMusicas = new String[]{"Waiting For The Night", "Try", "Spirit Indestructible",
									"I'm Like A Bird", "Say It Right", "All Good Things (Come To An End)",
									"Maneater", "Big Hoops (Bigger The Better)", "Manos Al Aire", "Parking Lot",
									"Give It To Me", "Promiscuous", "Turn Off The Light", "Abre Tu Coraz�n"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Promiscuous Girl"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Kelly Rowland
		grupo = gerenciadorDeGrupos.getGrupo("Kelly Rowland");
		nomesMusicas = new String[]{"Kisses Down Low", "When Love Takes Over", "Dilemma",
									"Like This", "How Deep Is Your Love", "(Love Lives In) Strange Places",
									"Keep It Between Us", "Stole", "Flashback", "Forever And A Day",
									"Work", "Can't Nobody", "All Of The Night", "Rose Colored Glasses"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Commander"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Leona Lewis
		grupo = gerenciadorDeGrupos.getGrupo("Leona Lewis");
		nomesMusicas = new String[]{"Run", "Better In Time", "Bridge Over Troubled Water",
									"Perfection", "Lovebird", "How Everything You Are",
									"The First Time Ever I Saw Your Face", "I Got You", "Burning Down", 
									"Unlearn Me", "I Wanna Be That Girl", "What You Do To Me", "Happy"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Bleeding Love"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
	}

	private void adicionaMusicasReligioso(GerenciadorDeGruposMusicais gerenciadorDeGrupos) {
		/***************RELIGIOSO - VOCAL MASCULINO**********************/
		//Thalles Roberto
		GrupoMusical grupo = gerenciadorDeGrupos.getGrupo("Thalles Roberto");
		String[] nomesMusicas = new String[]{"Eu Escolho Deus", "Nada Al�m de Ti", "Deus da Minha Vida", 
											 "Uma Hist�ria Escrita Pelo Dedo de Deus", "Mesmo Sem Entender", "Deus Me Ama", 
											 "Casa do Pai", "A Alegria Est� No Cora��o", "Deus do Imposs�vel", "Meu Mundo",
											 "O Que Queres de Mim", "Deus da For�a", "Ele � Contigo"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Arde Outra Vez"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Fernandinho
		grupo = gerenciadorDeGrupos.getGrupo("Fernandinho");
		nomesMusicas = new String[]{"Uma Nova Hist�ria", "Faz Chover", "Nada Al�m do Sangue", "Ainda que a Figueira", 
									"Grandes Coisas", "Teus Sonhos", "A Alegria do Senhor", "O Hino", "Caia Fogo",
									"Jesus, Filho de Deus", "Infinitamente Mais", "Deus Tem o Melhor Pra Mim"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Todas as Coisas"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Kleber Lucas
		grupo = gerenciadorDeGrupos.getGrupo("Kleber Lucas");
		nomesMusicas = new String[]{"Te Agrade�o", "Aos P�s Da Cruz", "Deus Cuida de Mim", "Deus Forte", "Meu Alvo",
									"Vou Deixar Na Cruz", "Rompendo Em F�", "O Melhor de Deus Est� Por Vir", "Nova Criatura",
									"A Carta", "Eu Vou Seguir Com F�", "Rio de Vida", "Mais Que Uma Voz"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Vou Seguir Com F�"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Padre Marcelo Rossi
		grupo = gerenciadorDeGrupos.getGrupo("Padre Marcelo Rossi");
		nomesMusicas = new String[]{"Noites Trai�oeiras", "Ningu�m Te Ama Como Eu", "Sonda-me", "Noite Feliz",
									"For�a e Vit�ria", "Segura Na M�o de Deus", "A Alegria",
									"Anjos de Deus", "Tudo � do Pai", "Meu Mestre", "Pai Nosso",
									"S� Por Ti Jesus", "Basta Querer", "Meu Cora��o � Para Ti"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Sou Teu Anjo"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Andr� Valad�o
		grupo = gerenciadorDeGrupos.getGrupo("Andr� Valad�o");
		nomesMusicas = new String[]{"Abra�a-me", "Milagre", "Pela F�", "Eu e Minha Casa", "Vou Crer", 
									"Porque Ele vive", "Se Eu Me Humilhar", "Deus de Promessas", "Alian�a de Amor",
									"A Alegria", "Qu�o Grande �s Tu", "Alegria", "A Minha Ora��o"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Nada Pode Quebrar"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Marquinhos Gomes
		grupo = gerenciadorDeGrupos.getGrupo("Marquinhos Gomes");
		nomesMusicas = new String[]{"Ele N�o Desiste de Voc�", "N�o Morrerei", "Todo Poderoso Deus", "L�grimas no olhar",
									"Uma Coisa Nova", "A Gl�ria � Dele", "Nasci Para Vencer", "Mil Cair�o",
									"O amor nunca perde", "Eu Sonhei", "Nosso Amor", "�guas Cristalinas", "N�o Desiste"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Porque Dele e Por Ele",};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Rosa de Saron
		grupo = gerenciadorDeGrupos.getGrupo("Rosa de Saron");
		nomesMusicas = new String[]{"Sem Voc�", "Metade de Mim", "Apenas Uma Can��o de Amor", "Rara Calma",
									"Do Alto Da Pedra", "M�quina do Tempo", "As Dores Do Sil�ncio", 
									"Meu Abandono", "Ningu�m Mais", "Chance", "O Sol da Meia-noite",
									"Minha Triste Imperfei��o", "Casino Boulevard"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Menos de um Segundo"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		
		/***************RELIGIOSO - VOCAL FEMININO**********************/
		//Aline Barros
		grupo = gerenciadorDeGrupos.getGrupo("Aline Barros");
		nomesMusicas = new String[]{"Ressuscita-me", "Sonda-me, Usa-me", "Recome�ar", "Soube Que Me Amava", "Rendido Estou",
									"Vit�ria No Deserto", "Primeira Ess�ncia", "1000 Bolinhas de Sab�o", "Meu Eterno Namorado",
									"Bem Mais Que Tudo", "Primeiro Amor", "Deus do Imposs�vel", "Ressucita-me"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Diante da Cruz"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Bruna Karla
		grupo = gerenciadorDeGrupos.getGrupo("Bruna Karla");
		nomesMusicas = new String[]{"Que Bom Que Voc� Chegou", "Advogado Fiel", "Sou Humano", 
									"Aceito o Teu Chamado", "Eu N�o Abro M�o", "A Espera de Um Milagre", 
									"Deixar a L�grima Rolar", "Apaixonado Cora��o", "Jamais Deixarei Voc�",
									"Posso Ser Feliz", "Pai, Eu Confiarei", "Acima da M�dia", "Deus Vem Me Socorrer"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Quando Eu Chorar"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Fernanda Brum
		grupo = gerenciadorDeGrupos.getGrupo("Fernanda Brum");
		nomesMusicas = new String[]{"Amar Voc�", "Esp�rito Santo", "N�o � Tarde", "A Casa Vai Tremer", "Cura-me",
									"Liberta-me!", "Imposs�vel de Esquecer ", "Cacos Pelo Ch�o",
									"Voc� Merece", "Em Tua Presen�a", "Amo o Senhor", "Reden��o", "Cora��o que Sangra"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"A Tua Gl�ria Faz"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Diante do Trono
		grupo = gerenciadorDeGrupos.getGrupo("Diante do Trono");
		nomesMusicas = new String[]{"Me Ama (How He Loves)", "Aos Olhos do Pai", "Can��o do Apocalipse",
									"Te Agrade�o", "Creio", "Me Ama", "Enquanto Eu Viver", "�guas Purificadoras",
									"Aclame Ao Senhor", "Eis-Me Aqui", "Nos Bra�os Do Pai", "A Alegria", "Anseio"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Preciso De Ti"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Rose Nascimento
		grupo = gerenciadorDeGrupos.getGrupo("Rose Nascimento");
		nomesMusicas = new String[]{"Mais Firme do Que Nunca", "Deus Est� Contigo", "O Tempo de Deus",
									"Ningu�m Pode Impedir", "Estou de P�", "No Sil�ncio", "Simplesmente Sobrenatural",
									"Acredita", "J�", "A Dose Mais Forte", "Port�es Celestiais"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Sempre Fiel"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Cassiane
		grupo = gerenciadorDeGrupos.getGrupo("Cassiane");
		nomesMusicas = new String[]{"Com Muito Louvor", "Hino da Vit�ria", "500 Graus", "Preciso de Uma Ben��o", 
									"Fa�a Diferen�a", "24 Horas", "Vou seguir", "Esconderijo do Alt�ssimo",
									"Recompensa", "Minha Ben��o", "Sementes Da F�", "Com Cristo � Vencer"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"A tua palavra"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Mara Lima
		grupo = gerenciadorDeGrupos.getGrupo("Mara Lima");
		nomesMusicas = new String[]{"Vaso de Alabastro", "Daniel", "Al�m da Medicina", "Sabe filho",
									"Vou Tocar o C�u", "Un��o Divina", "Valor de Uma Alma", "Dia de Sol",
									"Deus Determinou", "L�grimas", "Semente do Sangue", "Homem dos milagres"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Eu Navegarei"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
	}

	private void adicionaMusicasRock(GerenciadorDeGruposMusicais gerenciadorDeGrupos) {
		/***************ROCK - VOCAL MASCULINO**********************/
		//Led Zeppelin
		GrupoMusical grupo = gerenciadorDeGrupos.getGrupo("Led Zeppelin");
		String[] nomesMusicas = new String[]{"Rock and Roll", "D' yer Mak'er", "Dancing Days", "Immigrant Song",
											 "Kashmir", "Stairway to Heaven", "Whole Lotta Love",
											 "Since I've Been Loving You"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Black Dog"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Queen
		grupo = gerenciadorDeGrupos.getGrupo("Queen");
		nomesMusicas = new String[]{"Under Pressure", "Another one Bites the Dust", "Bicycle Race",
									"Crazy little Thing Called Love", "We Will Rock You", 
									"We Are the Champions", "Love my Life"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Bohemina Rhapsody"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Pink Floyd
		grupo = gerenciadorDeGrupos.getGrupo("Pink Floyd");
		nomesMusicas = new String[]{"Another Brick In teh Wall", "Money", "Time", "Hey You",
									"Shine on You Crazy Diamond", "Brain Damage", "Echoes", "Wish You Were Here"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Confortably Numb"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);		
		
		//Rush
		grupo = gerenciadorDeGrupos.getGrupo("Rush");
		nomesMusicas = new String[]{"YYZ", "Tom Sawier", "Red Barchetta", "Limelight", 
									"The Spirit of Radio", "Freewill", "Xanadu", "Time Stand Still"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Fly by Night"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//Black Sabbath
		grupo = gerenciadorDeGrupos.getGrupo("Black Sabbath");
		nomesMusicas = new String[]{"Paranoid", "Sabbath Bloody Sabbath", "NiB", "Neon Knights",
									"Mob Rules", "War Pigs", "Born Again", "Trashed"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Iron Man"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);	
		
		//KISS
		grupo = gerenciadorDeGrupos.getGrupo("KISS");
		nomesMusicas = new String[]{"Rock and Roll all night", "Shout it out Loud",
									"Forever", "Psycho Circus", "I Love it Loud", "Deuce", "Lick it Up"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Detroit Rock City"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Metallica
		grupo = gerenciadorDeGrupos.getGrupo("Metallica");
		nomesMusicas = new String[]{"One", "Unforgiven", "Master of Puppets", "Blackened", "Harvester of Sorrow",
									"Seek and Destroy", "Nothing Else Matters", "Enter Sandman", "Sad But True"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Fuel"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Nazareth
		grupo = gerenciadorDeGrupos.getGrupo("Nazareth");
		nomesMusicas = new String[]{"Sunshine", "Love Leads To Madness", "I Don't Want To Go On Without You", 
									"Love Hurts", "Where Are You Now", "Dream On", "A Veteran's Song", 
									"Moonlight eyes", "Star", "Lonely In The Night"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Hair Of The Dog"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Guns N' Roses
		grupo = gerenciadorDeGrupos.getGrupo("Guns N' Roses");
		nomesMusicas = new String[]{"Welcome To The Jungle", "Sweet Child O' Mine", "Patience",
									"Dont' Cry", "November Rain", "My Michelle", "Yesterdays", "Civil War", 
									"You Could Be Mine", "Knockin on Heavens Door"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Paradise City"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Deep Purple
		grupo = gerenciadorDeGrupos.getGrupo("Deep Purple");
		nomesMusicas = new String[]{"Highway Star", "Child in Time", "Perfect Strangers",
									"Burn", "Hush", "Knocking At Your Back Door", "Lazy", "Soldier of Fortune"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Smoke on the Water"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Jimi Hendrix
		grupo = gerenciadorDeGrupos.getGrupo("Jimi Hendrix");
		nomesMusicas = new String[]{"Hey Joe", "Voodoo Child", "Purple Haze", "Foxy Lady", "Machine Gun",
									"Angel", "Little Wing", "Bold As Love", "Little Wing", "Red House"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"All Along The Watchtower"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Creedence
		grupo = gerenciadorDeGrupos.getGrupo("Creedence");
		nomesMusicas = new String[]{"Proud Mary", "Bad Moon Rising", "Fortunate Son",
									"Who'll Stop The Rain", "I Heard It Through The Grapevine", "(Wish I Could) Hideaway",
									"Long As I Can See The Light", "Hey Tonight", "Suzie Q", "Born On The Bayou"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Have You Ever Seen The Rain?"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Van Halen
		grupo = gerenciadorDeGrupos.getGrupo("Van Halen");
		nomesMusicas = new String[]{"Can't Stop Lovin' You", "Dreams", "Pretty Woman", "Why Can't This Be Love",
									"Ain't Talkin' 'Bout Love", "When It's Love", "You Really Got Me", "Panama", "Right Now",
									"Hot For Teacher", "Love Walks In"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Jump"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//AC/DC
		grupo = gerenciadorDeGrupos.getGrupo("AC/DC");
		nomesMusicas = new String[]{"Back In Black", "You Shook Me All Night Long",
									"Thunderstruck", "T.N.T.", "Hells Bells", "Shoot To Thrill", "Rock N Roll Train",
									"Back In Black", "The Jack", "Ain't No Fun", "Let There Be Rock"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Highway To Hell"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//The Beatles
		grupo = gerenciadorDeGrupos.getGrupo("The Beatles");
		nomesMusicas = new String[]{"Hey Jude", "Yesterday", "Something", "Come Together",
									"All You Need Is Love", "All My Loving", "Help!", "In My Life",
									"Here Comes The Sun", "Blackbird", "I Want To Hold Your Hand"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Let It Be"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//The Rolling Stones
		grupo = gerenciadorDeGrupos.getGrupo("The Rolling Stones");
		nomesMusicas = new String[]{"Sympathy For The Devil", "Gimme Shelter",
									"Angie", "Doom And Gloom", "You Can't Always Get What You Want", "Paint It Black",
									"Wild Horses", "Miss You", "Start Me Up", "Jumpin' Jack Flash", "Anybody Seen My Baby?",
									"Brown Sugar"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Satisfaction"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//The Doors
		grupo = gerenciadorDeGrupos.getGrupo("The Doors");
		nomesMusicas = new String[]{"The End", "Light My Fire", "Roadhouse Blues",
									"People Are Strange", "Touch Me", "Love Me Two Times", "L.A. Woman",
									"Break On Through (To The Other Side)", "Hello, I Love You", "Love Street"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Riders On The Storm"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		/***************ROCK - VOCAL FEMININO**********************/
		//Flyleaf
		grupo = gerenciadorDeGrupos.getGrupo("Flyleaf");
		nomesMusicas = new String[]{"All Around Me", "Again", "I'm So Sick", "Fully Alive", "Cassie", 
									"New Horizons", "Broken Wings", "Sorrow", "Fire Fire", 
									"Believe In Dreams", "Arise", "Missing"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Call You Out"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//The Letter Black
		grupo = gerenciadorDeGrupos.getGrupo("The Letter Black");
		nomesMusicas = new String[]{"Believe", "Best Of Me", "Fire With Fire", "All I Want",
									"Invisible", "There'll Come A Day", "While You're Away", "Away From Me",
									"More To This", "My Disease", "Care Too Much"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Hanging On By a Thread"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Fireflight
		grupo = gerenciadorDeGrupos.getGrupo("Fireflight");
		nomesMusicas = new String[]{"Unbreakable", "Stay Close", "Forever", 
									"Wrapped In Your Arms", "All I Need To Be", "Desperate", "You Decide",
									"Brand New Day", "Stand Up", "He Weeps", "Escape", "So Help Me God"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"For Those Who Wait"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//BarlowGirl
		grupo = gerenciadorDeGrupos.getGrupo("BarlowGirl");
		nomesMusicas = new String[]{"Never Alone", "Beautiful Ending", "I Need You To Love Me", "I Believe In Love",
									"Hallelujah (Light Has Come)", "Mirror", "5 Minutes Of Fame",
									"It's The Most Wonderful Time Of The Year", "Sing Me A Love Song",
									"Grey", "Porcelain Heart", "Surrender"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Average Girl"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Valora
		grupo = gerenciadorDeGrupos.getGrupo("Valora");
		nomesMusicas = new String[]{"I Waited For You", "Extreme", "Blow Me Away", 
									"No Matter What", "Can You Save Me",
									"Forgotten", "I'm Holding On", "Live", "You Make It OK"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Irreversible"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Halestorm
		grupo = gerenciadorDeGrupos.getGrupo("Halestorm");
		nomesMusicas = new String[]{"Love Bites (So Do I)", "I Miss The Misery", "Here's To Us", 
									"Break In", "I'm Not An Angel", "Familiar Taste Of Poison", "Mz. Hyde", 
									"All I Wanna To do Is Make Love To You",
									"Bet U Wish U Had Me Back",  "Beautiful With You", "Innocence"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"I Get Off"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Eowyn
		grupo = gerenciadorDeGrupos.getGrupo("Eowyn");
		nomesMusicas = new String[]{"Beautiful Ashes", "Unfinished Memories", "Clich�", 
									"All I Need", "Runaway", "Dream", "Helpless", 
									"Locked Away", "Remedy", "Time", "To my surprise"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Silent Screams"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Sister Sin
		grupo = gerenciadorDeGrupos.getGrupo("Sister Sin");
		nomesMusicas = new String[]{"Kiss The Sky", "Outrage", "End Of The Line", "Writings On The Wall", 
									"On Parole", "One Out Of Ten", "Paint It Black", "Make My Day",
									"Sound Of The Underground", "24/7", "Nailbiter", 
									"Minor You Major Me", "Head Over Heels"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Heading For Hell"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Hole
		grupo = gerenciadorDeGrupos.getGrupo("Hole");
		nomesMusicas = new String[]{"Doll Parts", "Malibu", "Violet", "Celebrity Skin", "Honey", 
									"Dying", "Miss World", "Cocaine Girl", 
									"Letter to God", "Samantha", "Reasons To Be Beautiful", "Awful"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Skinny Little Bitch"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//4 Non Blondes
		grupo = gerenciadorDeGrupos.getGrupo("4 Non Blondes");
		nomesMusicas = new String[]{"What's Up?", "Spaceman", "Beautiful", "Pleasantly Blue", "Dear Mr. President",
									"Calling All The People", "In My Dreams", "Train", "Drifting", "I'm The One"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Superfly"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//The Donnas
		grupo = gerenciadorDeGrupos.getGrupo("The Donnas");
		nomesMusicas = new String[]{"40 Boys In 40 Nights", "Dancing With Myself", "Too Bad About Your Girl",
									"Fall Behind Me", "Keep On Loving You", "I Don't Want To Know (If You Don't Want Me)",
									"New Kid In School", "You've Got A Crush On Me", "Backstage", "Bitchin'",
									"Don't Break Me Down", "Don't Wait Up For Me"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Take It Off"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Paramore
		grupo = gerenciadorDeGrupos.getGrupo("Paramore");
		nomesMusicas = new String[]{"The Only Exception", "That's What You Get", "Decode", "Monster",
									"Brick By Boring Brick", "Ignorance", "My Heart", "Crushcrushcrush", "Playing God", 
									"Careful", "All I Wanted"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Misery Business"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Evanescence
		grupo = gerenciadorDeGrupos.getGrupo("Evanescence");
		nomesMusicas = new String[]{"My Immortal", "Going Under", "My Heart Is Broken", "Lithium", 
									"Call Me When You're Sober", "What You Want", "Everybody's Fool", 
									"Good Enough", "Lost In Paradise"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Bring Me To Life"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Joan Jett and the Blackhearts
		grupo = gerenciadorDeGrupos.getGrupo("Joan Jett and the Blackhearts");
		nomesMusicas = new String[] {"I Hate Myself For Loving You", "Do You Wanna Touch Me", 
									"Love Is Pain", "Crimson And Clover", "Bad Reputation", 
									"Little Liar", "I Still Dream About You", "Cherry Bomb"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"I Love Rock N' Roll"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
	}
	
	private void adicionaMusicasMetal(GerenciadorDeGruposMusicais gerenciadorDeGrupos) {
		/***************METAL - VOCAL MASCULINO**********************/
		//System Of a Down
		GrupoMusical grupo = gerenciadorDeGrupos.getGrupo("System Of a Down");
		String[] nomesMusicas = new String[]{"Chop Suey!", "Lonely Day", "B.Y.O.B.", "Toxicity", "Aerials", "Cigaro", 
											 "Sugar", "Hypnotize", "Roulette", "A.T.W.A.", "Question!"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"I-E-A-I-A-I-O"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Avenged Sevenfold
		grupo = gerenciadorDeGrupos.getGrupo("Avenged Sevenfold");
		nomesMusicas = new String[]{"Dear God", "So Far Away", "Seize The Day", "Nightmare", "Almost Easy",
									"Afterlife", "Gunslinger", "Bat Country", "Buried Alive", "The Beast And The Harlot"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"A Little Piece Of Heaven"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Slipknot
		grupo = gerenciadorDeGrupos.getGrupo("Slipknot");
		nomesMusicas = new String[]{"Snuff", "Before I Forget", "Psychosocial", "Duality", "(Sic)", "Vermilion", "Dead Memories", 
									"'Til We Die", "Wait And Bleed", "People = Shit", "The Heretic Anthem"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Disasterpiece"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Scorpions
		grupo = gerenciadorDeGrupos.getGrupo("Scorpions");
		nomesMusicas = new String[]{"Still Loving You", "Wind Of Change", "Send Me An Angel", "You And I", "No Pain No Gain", 
									"Always Somewhere", "Dust In The Wind", "No One Like You", "Love Of My Life"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Rock You Like A Hurricane"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Megadeth
		grupo = gerenciadorDeGrupos.getGrupo("Megadeth");
		nomesMusicas = new String[]{"� Tout Le Monde", "Holy Wars... The Punishment Due", "Trust",
									"Tornado Of Souls", "In My Darkest Hour", "Peace Sells", "Bite The Hand", "Hangar 18", "She-Wolf"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Symphony Of Destruction"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Ozzy Osbourne
		grupo = gerenciadorDeGrupos.getGrupo("Ozzy Osbourne");
		nomesMusicas = new String[]{"Mr. Crowley", "Dreamer", "Mama, I'm Coming Home", "No More Tears", 
									"I Just Want You", "Hellraiser", "Life Won't Wait", "Bark At The Moon", "Goodbye To Romance"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Crazy Train"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Helloween
		grupo = gerenciadorDeGrupos.getGrupo("Helloween");
		nomesMusicas = new String[]{"I Want Out", "If I Could Fly", "All My Loving", "Forever And One (Neverland)", 
									"Power", "Open Your Life", "Salvation", "Heavy Metal Hamsters", "Do You Feel Good"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Eagle Fly Free"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Judas Priest
		grupo = gerenciadorDeGrupos.getGrupo("Judas Priest");
		nomesMusicas = new String[]{"Breaking The Law", "Diamonds And Rust", "Angel", "A Touch Of Evil", 
									"Deal With The Devil", "Devil's Child", "Rapid Fire", "Out In The Cold", "Hard As Iron"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Painkiller"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Disturbed
		grupo = gerenciadorDeGrupos.getGrupo("Disturbed");
		nomesMusicas = new String[]{"Decadence", "Down With The Sickness", "Indestructible", "Inside The Fire", 
									"Divide", "Ishfwilf", "Another Way To Die", "Prayer", "Glass Shatters"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Stricken"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Sepultura
		grupo = gerenciadorDeGrupos.getGrupo("Sepultura");
		nomesMusicas = new String[]{"Roots Bloody Roots", "Orgasmatron", "Arise", "Ratamahatta", "Symptom Of The Universe",
									"Refuse/Resist", "Born Stubborn", "R.I.P. (Rest In Pain)", "Breed Apart"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Territory"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Blind Guardian
		grupo = gerenciadorDeGrupos.getGrupo("Blind Guardian");
		nomesMusicas = new String[]{"The Bard's Song (In the Forest)", "Mirror Mirror", "Valhalla", "A Voice In The Dark",
									"The Lord of the Rings", "Bright Eyes", "Skalds And Shadows",
									"Mr. Sandman", "A Past And Future Secret", "The Bard's Song - The Hobbit", "Battlefield"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Nightfall"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		/***************METAL - VOCAL FEMININO**********************/		
		//Arch Enemy
		grupo = gerenciadorDeGrupos.getGrupo("Arch Enemy");
		nomesMusicas = new String[]{"Burning Angel", "Nemesis", "Beast of Man", "Secrets", "I Am Legend/Out For Blood", 
									"Cult Of Chaos", "Slaves Of Yesterday", "My Apocalypse", 
									"The Immortal", "City Of The Dead", "Dead Inside"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"We Will Rise"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Epica
		grupo = gerenciadorDeGrupos.getGrupo("Epica");
		nomesMusicas = new String[]{"Cry For The Moon", "Memory", "Storm The Sorrow", "Delirium", "The Phantom Agony",
									"Chasing the Dragon", "Feint", "Never Enough", "Solitary Ground", "Sensorium", "Blank Infinity"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Unleashed"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Guano Apes
		grupo = gerenciadorDeGrupos.getGrupo("Guano Apes");
		nomesMusicas = new String[]{"Lords Of The Boards", "Big in Japan", "Never Born", "Fire In Your Eyes", 
									"Apocalyptica", "Don't You Turn Your Back On Me", "Innocent Greed", "Maria", 
									"Rain", "You Can't Stop Me", "Living In A Lie", "Open Your Eyes"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Cuts"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Kittie
		grupo = gerenciadorDeGrupos.getGrupo("Kittie");
		nomesMusicas = new String[]{"We Are The Lamb", "Safe", "Never Again", "Suck", "Oracle", "Brackish",
									"Forgive And Forget", "Now Or Never", "Falling Down", "Pink Lemonade", "Breathe"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Run Like Hell"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Lacuna Coil
		grupo = gerenciadorDeGrupos.getGrupo("Lacuna Coil");
		nomesMusicas = new String[]{"Trip The Darkness", "Enjoy The Silence",
									"Comalies", "Underdog", "The Secret...", "Honeymoon Suite", 
									"The Game", "Words", "Falling Again", "Heaven's a Lie", "The Prophet Said"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"End Of Time"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Nightwish
		grupo = gerenciadorDeGrupos.getGrupo("Nightwish");
		nomesMusicas = new String[]{"The Phantom Of The Opera", "Wish I Had An Angel", "While Your Lips Are Still Red",
									"Storytime", "One more night to live", "Sleeping Sun", "White Night Fantasy", 
									"Amaranth", "The Islander", "Ghost Love Score", "Ever Dream"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Nemo"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Otep
		grupo = gerenciadorDeGrupos.getGrupo("Otep");
		nomesMusicas = new String[]{"Run For Cover", "Invisible", "The Lord Is My Weapon", "Nein", 
									"Milk of Regret", "Confrontation", "Ghostflowers", 
									"Kisses & Kerosene", "Blood Pigs", "House Of Secrets", "Sepsis"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Sweet Tooth"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
		
		//Theatre Of Tragedy
		grupo = gerenciadorDeGrupos.getGrupo("Theatre Of Tragedy");
		nomesMusicas = new String[]{"A Distance There Is", "Venus", "Virago", 
									"A Rose For The Dead", "Debris", "And When He Falleth", "Senseless",
									"M�Re", "Seraphic Deviltry", "Cassandra", "Siren"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasPorGrupo);
		nomesMusicas = new String[]{"Fragment"};
		adicionaMusicasDoGrupo(grupo, nomesMusicas, musicasComMp3PorGrupo);
	}
	
	private void adicionaMusicasDoGrupo(GrupoMusical grupo, String[] nomesMusicas, Map<Integer, List<Musica>> mapa) {
		List<Musica> lista = new ArrayList<Musica>();
		for (int i = 0; i < nomesMusicas.length; i++) {
			int idMusica = musicasPorId.size();
			Musica m = new Musica(idMusica, nomesMusicas[i], grupo);
			lista.add(m);
			musicasPorId.put(m.getIdMusica(), m);
		}
		int idGrupo = grupo.getId();
		mapa.put(idGrupo, lista);
	}	
}