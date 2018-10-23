
public class ChecaLimite {

	private short screendata[];
	
	public ChecaLimite(short screendata[]){
		this.screendata = screendata;
	}
	
	public boolean checaLimite (int [] XY, byte dir){
		boolean limite = false;
		int pos = ((XY[0]/Dados.BLOCKSIZE) + ((XY[1]/Dados.BLOCKSIZE)*(Dados.WIDTH/Dados.BLOCKSIZE))); 
		
		if(XY[0]%Dados.BLOCKSIZE==0&& XY[1]%Dados.BLOCKSIZE==0){
			switch (dir){
				case Dados.LEFT: {
					if((screendata[pos]&1)!=0) limite = true;
					break;
				}
				case Dados.UP: {
					if((screendata[pos]&2)!=0) limite = true;
					break;
				}
				case Dados.RIGHT: {
					if((screendata[pos]&4)!=0) limite = true;
					break;
				}
				case Dados.DOWN: {
					if((screendata[pos]&8)!=0) limite = true;
					break;
				}
				default: limite = true;
			}
		} else {
			if (XY[0]%Dados.BLOCKSIZE!=0&&XY[0]%Dados.BLOCKSIZE==0){
				switch(dir){
					case Dados.UP: limite = true;
					break;
					case Dados.DOWN: limite = true;
					break;
					default: limite = false;
				}
			} else if (XY[0]%Dados.BLOCKSIZE==0&&XY[0]%Dados.BLOCKSIZE!=0){
				switch(dir){
					case Dados.LEFT: limite = true;
					break;
					case Dados.RIGHT: limite = true;
					break;
					default: limite = false;
				}
			}
		}
	
		return limite;
	}
	
	public boolean isPastilha(int [] XY){
		int pos = ((XY[0]/Dados.BLOCKSIZE) + ((XY[1]/Dados.BLOCKSIZE)*(Dados.WIDTH/Dados.BLOCKSIZE)));
		boolean status = false;
		
		if(XY[0]%Dados.BLOCKSIZE==0&& XY[1]%Dados.BLOCKSIZE==0){
			if((screendata[pos]&16)!=0){
				status = true;
				screendata[pos]=(short) (screendata[pos]&15);
			}
		}
		return status;
	}
	
	public boolean isPastilhaPoder(int [] XY){
		int pos = ((XY[0]/Dados.BLOCKSIZE) + ((XY[1]/Dados.BLOCKSIZE)*(Dados.WIDTH/Dados.BLOCKSIZE)));
		boolean status = false;
		
		if(XY[0]%Dados.BLOCKSIZE==0&& XY[1]%Dados.BLOCKSIZE==0){
			if((screendata[pos]&32)!=0){
				status = true;
				screendata[pos]=(short) (screendata[pos]&31);
			}
		}
		return status;	
	}
}
