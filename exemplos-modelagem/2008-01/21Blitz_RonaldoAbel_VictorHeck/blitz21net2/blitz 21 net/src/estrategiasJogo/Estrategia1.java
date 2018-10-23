package estrategiasJogo;
import dominioProblema.ImagemDeTabuleiro;
import dominioProblema.Lance;

public class Estrategia1 extends Estrategia {
 
	public Lance definirLance(ImagemDeTabuleiro estado) {
		Lance lance;
		lance=new Lance();
		int linha2=1;
		int somal1,somal2,somal3,somal4=0;
		boolean lv1,lv2,lv3,lv4,cv1,cv2,cv3,cv4,lm1,lm2,lm3,lm4,cm1,cm2,cm3,cm4;
		lv1=lv2=lv3=lv4=cv1=cv2=cv3=cv4=lm1=lm2=lm3=lm4=cm1=cm2=cm3=cm4=false;
		System.out.println("carta sorteada="+ this.cartaSorteada(estado));
		boolean lv=false,lp=true;
		somal1=(somaColuna(estado,1)+this.cartaSorteada(estado));
		System.out.println("linha1="+somal1);
		
		somal2=(somaColuna(estado,2)+this.cartaSorteada(estado));
		System.out.println("linha2="+somal2);
		somal3=(somaColuna(estado,3)+this.cartaSorteada(estado));
		System.out.println("linha3="+somal3);
		somal4=(somaColuna(estado,4)+this.cartaSorteada(estado));
		System.out.println("linha4="+somal4);
		
		lv1=e21(estado,1); // verifica se linha 1 e vencedora
		lv2=e21(estado,2);	
		lv3=e21(estado,3);
		lv4=e21(estado,4);
		cv1=e21c(estado,2);
		cv2=e21c(estado,3);	
		cv3=e21c(estado,4);
		cv4=e21c(estado,5);
		lm1=e21lm(estado,1); // verifica se linha 1 eh menor ou igual a 21 vencedora
		lm2=e21lm(estado,2);	
		lm3=e21lm(estado,3);
		lm4=e21lm(estado,4);
		cm1=e21cm(estado,2);
		cm2=e21cm(estado,3);	
		cm3=e21cm(estado,4);
		cm4=e21cm(estado,5);
		if(lv1) {lv1=false;return this.lancelinha(estado,1);}
		else if(lv2){return this.lancelinha(estado,2);}
		else if(lv3){return this.lancelinha(estado,3);}
		else if(lv4){return this.lancelinha(estado,4);}
		else if(cv1){return this.lancecoluna(estado,2);}
		else if(cv2){return this.lancecoluna(estado,3);}
		else if(cv3)return this.lancecoluna(estado,4);
		else if(cv4)return this.lancecoluna(estado,5);
		else if((lm1)&& this.tcv(estado,1)){lm1=false;return this.lancelinha(estado,1);}
		else if((lm2)&& this.tcv(estado,2)){lm2=false;return this.lancelinha(estado,2);}
		else if((lm3)&& this.tcv(estado,3)){lm3=false;return this.lancelinha(estado,3);}
		else if((lm4)&& this.tcv(estado,4)){lm4=false;return this.lancelinha(estado,4);}
		else if((cm1)&& this.tlv(estado,2)){cm1=false;return this.lancecoluna(estado,1);}
		else if((cm2)&& this.tlv(estado,3)){cm2=false;return this.lancecoluna(estado,2);}
		else if((cm3)&& this.tlv(estado,4)){cm3=false;return this.lancecoluna(estado,3);}
		else if((cm4)&& this.tlv(estado,5)){cm4=false;return this.lancecoluna(estado,4);}
		else{
			for (int linha=1; linha<5; linha++){
				for (int coluna=2; coluna<6; coluna++){
					 int x;
					 x=estado.informarValor(linha,coluna);
				       if (x==0){ lance.assumir(linha,coluna);
				       return lance;
				          };
				             	
				
				   }
			}
			return lance;
		}
		
		}


	
	
	 
	public Lance lance1(ImagemDeTabuleiro estado) {
		Lance lance;
		ordemLance++;
		lance = new Lance();
		
		lance.assumir(3, 3);
		return lance;
	}
	 
	public Lance lance2(ImagemDeTabuleiro estado) {
		Lance lance;
		if (estado.informarPosicaoOcupada(2, 2)) {
			caminho = 3;
			ordemLance++;
			lance = new Lance();
			lance.assumir(3, 3);
		} else {
			if ((estado.informarPosicaoOcupada(2, 1)) || (estado.informarPosicaoOcupada(2, 3)) 
			   || (estado.informarPosicaoOcupada(3, 1)) ) {
				caminho = 2;
				ordemLance++;
				lance = new Lance();
				lance.assumir(1, 3);
			} else {
				caminho = 1;
				ordemLance++;
				lance = new Lance();
				lance.assumir(3, 1);
			};
		};
		return lance;
	}
	 
	public Lance lance3(ImagemDeTabuleiro estado) {
		Lance lance = null;
		switch (caminho) {
		case 1: lance = this.lance3Caminho1(estado);
		break;
		case 2: lance = this.lance3Caminho2(estado);
		break;
		case 3: lance = this.lance3Caminho3(estado);	
		};
		return lance;
	}
	 
	public Lance lance3Caminho1(ImagemDeTabuleiro estado) {
		Lance lance;
		if (estado.informarPosicaoVazia(2, 1)) {
			lance = this.lanceBasico(estado);
		} else {
			if ((estado.informarPosicaoVazia(3, 2)) && (estado.informarPosicaoVazia(3, 3)) ) {
				ordemLance++;
				lance = new Lance();
				lance.assumir(3, 3);
			} else {
				ordemLance++;
				lance = new Lance();
				lance.assumir(1,5);
			};
		};
		return lance;
	}
	 
	public Lance lance3Caminho2(ImagemDeTabuleiro estado) {
		Lance lance;
		if (estado.informarPosicaoVazia(1, 2)) {
			lance = this.lanceBasico(estado);
		} else {
			if ((estado.informarPosicaoVazia(2, 3)) && (estado.informarPosicaoVazia(3, 3)) ) {
				ordemLance++;
				lance = new Lance();
				lance.assumir(3, 3);
			} else {
				ordemLance++;
				lance = new Lance();
				lance.assumir(3, 1);
			};
		};
		return lance;
	}
	 
	public Lance lance3Caminho3(ImagemDeTabuleiro estado) {
		Lance lance;
		if (estado.informarPosicaoOcupada(3, 1)) {
			ordemLance++;
			lance = new Lance();
			lance.assumir(1, 3);
		} else {
			if (estado.informarPosicaoOcupada(1, 3)) {
				ordemLance++;
				lance = new Lance();
				lance.assumir(3, 1);
			} else {
				lance = this.lanceBasico(estado);
			};
		};
		return lance;
	}
	 
}
 