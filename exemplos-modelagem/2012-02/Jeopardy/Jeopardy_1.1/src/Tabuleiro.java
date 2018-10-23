import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Tabuleiro {

    private ArrayList<Pista> pistas;
    private Jogador jogador1;
    private Jogador jogador2;
    private boolean meuTurno;  //acho que isso não fica aqui
    private boolean partidaEmAndamento;
    private boolean conectado; // se desconectar, voltar ao estado inicial

    public Tabuleiro() {
        this.meuTurno = false;
        inicializaJogadores();
        inicializaPistas();
        //posicoesIniciais();
    }

    private void inicializaJogadores() {
        this.jogador1 = new Jogador();
        this.jogador2 = new Jogador();
    }

    private void inicializaPistas() {
        pistas = new ArrayList<>();

     
        pistas.add(new Pista(0,  "História", "Quem foi Allan Turing?", 1,  "Foi o matemático inventor da máquina de estados.", "Inicia com A  e possui 11 letras"));
        pistas.add(new Pista(1,  "História", "Quem foi Juscelino Kubitschek?", 2, "Foi o presidente em cujo mandato foi contruída Brasília.", "Inicia com J e possui 19 letras"));
        pistas.add(new Pista(2,  "História", "Quando foi a Revolução Francesa?", 3, "Foi em 1789.", "Inicia com R e possui 17 letras"));
        pistas.add(new Pista(3,  "Geografia", "O que é Rússia?", 1, "É o maior país da Europa.", "Inicia com R e possui 6 letras"));
        pistas.add(new Pista(4,  "Geografia", "O que é Bucareste?", 2, "É a capital da Romênia.", "Inicia com B e possui 9 letras"));
        pistas.add(new Pista(5,  "Geografia", "O que é Volga", 3, "É o rio mais longo da Europa.", "Inicia com V e possui 5 letras"));
        pistas.add(new Pista(6,  "Literatura", "Quem foi Machado de Assis?", 1, "Foi o principal escritor do realismo brasileiro.", "Inicia com M e possui 14 letras"));
        pistas.add(new Pista(7,  "Literatura", "Quem foi Cruz e Souza?", 2, "Foi o principal poeta catarinense.", "Inicia com C e possui 10 letras"));
        pistas.add(new Pista(8,  "Literatura", "Quem foi Aluísio Azevedo", 3, "Foi o autor de 'O Cortiço'.", "Inicia com A e possui 14 letras"));
        pistas.add(new Pista(9,  "Atualidades", "Quem é Barack Obama?", 1, "É o vencedor das últimas eleições presidenciais americanas.", "Inicia com B e possui 11 letras"));
        pistas.add(new Pista(10, "Atualidades", "Quem é Roselane Neckel", 2, "É a reitora eleita da UFSC.", "Inicia com R e possui 14 letras"));
        pistas.add(new Pista(11, "Atualidades", "O que é Primavera Árabe?", 3, "É o movimento de democratização dos países do norte da África.", "Inicia com P e possui 14 letras"));
        pistas.add(new Pista(12, "Esportes", "Quem é Lionel Messi?", 1, "É o jogador eleito pela FIFA como melhor de 2012.", "Inicia com L e possui 11 letras"));
        pistas.add(new Pista(13, "Esportes", "O que é Copa do Mundo?", 2, "É o evento esportivo mundial a ser sediado no Brasil em 2014.", "Inicia com C e possui 11 letras"));
        pistas.add(new Pista(14, "Esportes", "Quem é Usain Bolt", 3, "É o velocista mais rápido da atualidade.", "Inicia com U e possui 9 letras"));
    }

    public void processaJogada(JogadaCustom jogada) {
        // atualiza os dados do oponente
        this.jogador2 = jogada.getJogador();

        // pega a pista que ele respondeu
        Pista pista = jogada.getPista();

        // desabilita a pista utilizada
        if (pista != null) {
            desabilitaPista(pista);
        }               

        // turno do jogador
        jogador1.setDaVez(true);
    }

    private void desabilitaPista(Pista pista) {
        // verifica todas as pistas, a que tiver a mesma resposta será
        // desabilitada, a resposta é única no jogo
        int baseId = pista.getId();
        for (Pista item : this.pistas) {
            int id = item.getId();

            if (baseId == id) {
                item.setRespondida(true);
            }
        }
    }

    public void listarPistas(AtorJogador ator) {
        // mostra a janela de pistas para o usuário selecionar uma pista
        JanelaPistas window = new JanelaPistas(this, ator);
        window.setVisible(true);        
    }

    public void jogar(int idPista, AtorJogador ator){
        // pega a pista selecionada na janela de pistas
        Pista pista = getPistaById(idPista);
        
        // pega a resposta
        String resposta = pista.getResposta();
        JanelaMensagem msg = new JanelaMensagem("Digite a pergunta para a seguinte resposta\n\n" + resposta);

        String pergunta = "";

        do {
            pergunta = msg.getInput();
        } while (pergunta.length() == 0);

        boolean valido = pista.isRespostaValida(pergunta);

        if (valido) {
            int valor = pista.getValor();
            int posicao = jogador1.getPosicao();
            int novaPosicao = valor + posicao;
            jogador1.setPosicao(novaPosicao);
            desabilitaPista(pista);
        } else {
            JanelaMensagem msgAux = new JanelaMensagem("Aceita dica? Se você errar irá perder posições!");
            String[] opcoes = new String[]{"Sim", "Não"};
            
            if (msgAux.choose(opcoes) == 0) {
                String respostaStr = pista.getResposta();
                String dica = pista.getDica();
                msg.setText("Digite a pergunta para a seguinte resposta\n\n" + respostaStr + " - DICA:  " + dica);
                do {                    
                    pergunta = msg.getInput();
                } while (pergunta.length() == 0);
                
                valido = pista.isRespostaValida(pergunta);

                int novaPosicao = jogador1.getPosicao();
                int valor = pista.getValor();
                if (valido) {
                    novaPosicao += valor;
                    desabilitaPista(pista);
                    jogador1.setPosicao(novaPosicao);
                    
                } else {
                    novaPosicao -= valor;
                    if (novaPosicao >= 0) {
                        jogador1.setPosicao(novaPosicao);
                    } else {
                        jogador1.setPosicao(0);
                        
                    }
                    pista = null;
                }
            }
            jogador1.setDaVez(false);
            
        }
        
        ator.continuarJogada(pista);
    }

    public Jogador getJogador1() {
        return this.jogador1;
    }

    public Jogador getJogador2() {
        return this.jogador2;
    }

    public void setNomeJogador(String nome) {
        this.jogador1.setNome(nome);
    }

    public String getNomeJogador() {
        if (this.jogador1 == null) {
            return "";
        }

        return this.jogador1.getNome();
    }

    public void setNomeAdversario(String nome) {
        this.jogador2.setNome(nome);
    }

    public String getNomeAdversario() {
        if (this.jogador2 == null) {
            return "";
        }

        return this.jogador2.getNome();
    }

    public boolean isMeuTurno() {
        return this.meuTurno;
    }

    public boolean acabou() {
        boolean venceu1 = this.jogador1.venceu();
        boolean venceu2 = this.jogador2.venceu();
        
        return venceu1 || venceu2;
    }

    public boolean ispartidaEmAndamento() {
        return partidaEmAndamento;
    }

    public void setpartidaEmAndamento(boolean emAndamento) {
        this.partidaEmAndamento = emAndamento;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }
    
    public void setMeuTurno(boolean valor) {
        this.meuTurno = valor;
    }

    public ArrayList<Pista> getPistas() {
        return this.pistas;
    }

    public Pista getPistaById(int id) {
        for (Pista item : this.pistas) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }
}