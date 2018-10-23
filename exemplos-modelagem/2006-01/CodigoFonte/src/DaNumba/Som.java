package DaNumba;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/*
 * Classe responsável por reproduzir os sons do jogo
 */

public class Som extends Thread {
    
    private final int TAMANHO_BUFFER = 128000;
    private String arquivoSom;
    
    //construtor padrão da classe
    public Som() {
    }
    
    //ação da thread que será executada
    public void run() {
        play();
    }
    
    //recebe o endereço do arquivo
    public void setAudio (String arquivoSom) {
        this.arquivoSom = arquivoSom;
    }
    
    //busca o arquivo e executa o som
    public void play() {
        String enderecoArquivo = arquivoSom;
        File arquivoComSom = new File(enderecoArquivo);
        AudioInputStream somInput = null;
        try {
            somInput = AudioSystem.getAudioInputStream(arquivoComSom);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        AudioFormat formatoSom = somInput.getFormat();
        SourceDataLine linha = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class , formatoSom);
        try {
            linha = (SourceDataLine) AudioSystem.getLine(info);
            linha.open(formatoSom);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
      }
      catch (Exception e) {
         e.printStackTrace();
         System.exit(1);
      }
      linha.start();
      int bytesLidos = 0;
      byte[] recebeTamanhoBuffer = new byte[TAMANHO_BUFFER];
      while (bytesLidos != -1) {
          try {
              bytesLidos = somInput.read(recebeTamanhoBuffer , 0 , recebeTamanhoBuffer.length);
          }
          catch (IOException e) {
              e.printStackTrace();
          }
          if (bytesLidos >= 0) {
              int nBytesWritten = linha.write(recebeTamanhoBuffer , 0 , bytesLidos);
          }
      }
      linha.drain();
      linha.close();
    }
}
