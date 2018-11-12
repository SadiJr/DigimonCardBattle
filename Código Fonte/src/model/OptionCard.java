package model;

import br.ufsc.inf.leobr.cliente.Jogada;
import enums.Effect;

public class OptionCard extends Card implements Jogada {

	public OptionCard(String name, Effect effect, String path) {
		super(name, effect, path);
	}
}