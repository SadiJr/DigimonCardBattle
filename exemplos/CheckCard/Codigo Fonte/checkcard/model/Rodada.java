package model;

import br.ufsc.inf.leobr.cliente.Jogada;

import java.util.ArrayList;
import java.util.List;

public class Rodada implements Jogada {
	
	protected List<Lance> lances;
	
	public Rodada() {
		this.lances = new ArrayList<Lance>();
	}
	
	public List<Lance> getLances () {
		return this.lances;
	}
	
	public void setLances (List<Lance> lances) {
		this.lances = lances;
	}
	
	public void addLance (Lance lance) {
//		if (this.lances.isEmpty()) {
//			this.lances = new ArrayList<Lance>();
//		}
//		
		this.lances.add(lance);
	}
	
	public int getQuantidadeLances() {
		if (this.lances == null) {
			this.lances = new ArrayList<Lance>(); 
		}
		
		return this.lances.size();
	}
}
