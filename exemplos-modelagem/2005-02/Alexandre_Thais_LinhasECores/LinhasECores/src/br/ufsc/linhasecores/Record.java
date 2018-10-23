package br.ufsc.linhasecores;

public class Record
{
   
   private String nome;
   private int pontuacao;
   private int numCores;
   private int tamanho;

   public Record()
   {
      setNome("ninguém");
      setPontuacao(0);
      setNumCores(5);
      setTamanho(9);
   }
   
   public Record (String nome, int pontuacao, int cores, int tamanho)
   {
      setNome (nome);
      setPontuacao (pontuacao);
      setNumCores (cores);
      setTamanho (tamanho);
   }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getNumCores() {
        return numCores;
    }

    public void setNumCores(int numCores) {
        this.numCores = numCores;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
}

