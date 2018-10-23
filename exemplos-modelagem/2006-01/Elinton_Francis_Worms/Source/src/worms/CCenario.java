package worms;

import javax.microedition.lcdui.Image;

public class CCenario {
 	 
	private ECenario eCenario;
	
	/**
	 *Constante que indica o valor do pixel preto
	 */
	private final int PRETO = -16777216;
	
	private int indiceAgua = 0;
	/**
	 * Construtor
	 * @param imagemNome caminho completo da imagem do cen�rio
	 */
	public CCenario(String imagemNome, int largura, int altura){
		eCenario = new ECenario(imagemNome);
		Image cenario = this.mapeaChao(imagemNome,largura,altura);
		eCenario.setImagem(cenario);
	}
	
	/**
	 * M�todo cria um Imagem a partir de uma String recebida e 
	 * procura pixel a pixel por pixels pretos (que fazem a marca��o do ch�o)
	 * Preenchendo um array com todas as posi��es X e Y do ch�o, armazena esse 
	 * array no ECenario e retorna a imagem criada
	 * @param imagemNome String com o nome da Image a ser criada
	 * @param screenWigth int com a largura da tela
	 * @return Image criada int com a altura da tela
	 */
	private Image mapeaChao(String imagemNome, int screenWigth, int screenHeight){
		int arrayMap[]= null;//armazenara todos os pixels da imagem
		int chaoMap[][] = null;//armazenara apenas os valores X e Y do ch�o (pixels pretos)
		Image imagem = null;
		try {
			imagem = Image.createImage(imagemNome);
			int imageWigth = imagem.getWidth();
			int imageHeight = imagem.getHeight();
			boolean refazerImagem = false;
			if (screenWigth < imageWigth){//se a imagem for maior do que a tela
				imageWigth = screenWigth; 
				refazerImagem = true;
			}
			if (screenHeight < imageHeight){//se a altura da tela � menor que a da figura
				imageHeight = screenHeight;
				refazerImagem = true;
			}
			 
			chaoMap = new int[screenWigth+110][2];
			arrayMap = new int[imageWigth*imageHeight];
			//preeche o arrayMap com valor rgb de cada pixel da imagem
			imagem.getRGB(arrayMap, 0, imageWigth,0,0,imageWigth,imageHeight);
			int contChao =0;
			for (int i = 0; i <= imageHeight; i++)
				for (int j = 0; j <= imageWigth;j++)
					if (arrayMap[i*imageWigth+j] == PRETO){//procura pelos pixels pretos
						chaoMap[contChao][1]= i;//y
						chaoMap[contChao][0]= j;//x
						contChao++;
					}
			if (refazerImagem)//se a imagem � maior que a tela cria s� a parte que interessa
				imagem = Image.createRGBImage(arrayMap,imageWigth,imageHeight,true);//pega a parte que cabe na tela
		}catch (NullPointerException e) {
			System.out.println("imagem n�o enontrada "+imagemNome+e.getMessage());
		} catch (Exception e) {
			System.out.println("erro: "+e.getMessage()+e.getClass());
		} finally{
			eCenario.setCorFundo(arrayMap[1]);//o segundo pixel faz parte do fundo
			eCenario.setChaoMap(chaoMap);
		}
		return imagem;
	}
	
	/**
	 * M�todo retorna a posi��o Y do ch�o para um dado X
	 * @param x do ch�o
	 * @return y do ch�o
	 */
	public int getY(int x){
		int pMap[][] = eCenario.getChaoMap();
		for (int i = 0; i < pMap.length; i++) {
				if (pMap[i][0]==x)
					return pMap[i][1]-5; //5 � o tamanho da minhoca
		}
		return -1;
	}
	
	/**
	 * M�todo verifica e altera o ch�o caso a explos�o o afete
	 * @param x do ponto central da explos�o
	 * @param y do ponto central da explos�o
	 * @param raio da explos�o
	 */
	public void alteraChao(int x, int y, int raio){
		int pMap[][] = eCenario.getChaoMap();
		int xComecoDaExplocao = x - raio;
		for (int i = 0; i < pMap.length; i++) {
			if ((pMap[i][0] >= xComecoDaExplocao)&&(pMap[i][0] <= x+raio)){
					int novoY = (int)funcaoCirculo(x,y,raio,pMap[i][0]);
					if (novoY > pMap[i][1] && novoY < eCenario.getImagem().getHeight())
						pMap[i][1]= novoY;						
					else if (novoY >= eCenario.getImagem().getHeight())
						pMap[i][1]= eCenario.getImagem().getHeight();
				}
		}
		
	}
	
	/**
	 * 
	 * @param xC posicao x do centro do c�rculo
	 * @param yC posicao y do centro do c�rculo
	 * @param R  raio do c�rculo
	 * @param x  posicao x para o qual se deseja o y
	 * @return a posicao y para um dado x no c�rculo
	 */
	private int funcaoCirculo(int xC, int yC,int R, int x) {
		return (int) (yC+Utils.SQRT(R*R - ((xC - x)*(xC - x))));
	}
	
	/**
	 * @return Image do Cen�rio
	 */
	public Image getImage(){
		return eCenario.getImagem();
	}
	
	/**
	 * @return int com a cor do fundo da imagem
	 */
	public int informaCorFundo(){
		return eCenario.getCorFundo();
	}
	
	/**
	 * Set a imagem do cen�rio
	 * @param imagemNome nome da imagem
	 */
	public void setImageNome(String imagemNome, int largura, int altura){
		eCenario.setImageName(imagemNome);
		eCenario.setImagem(mapeaChao(imagemNome,largura,altura));
	}
	
	public Image getAgua(){
		Image agua = null;
		try {
			agua = Image.createImage("/blue"+indiceAgua%11+".PNG");
			indiceAgua++;
		} catch (Exception e) {
			System.err.println("Erro ao carregar imagem "+"./blue"+indiceAgua%11+".PNG");
		} finally{
			return agua;
		}
	}
	
	 
}
 
