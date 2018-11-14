package model;

import br.ufsc.inf.leobr.cliente.Jogada;
import enums.Effect;

public class OptionCard extends Card implements Jogada{
	private static final long serialVersionUID = 1L;

	public OptionCard(String name, Effect effect, String path) {
		super(name, effect, path);
	}
}