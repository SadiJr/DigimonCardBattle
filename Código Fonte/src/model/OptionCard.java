package model;

import enums.Effect;

public class OptionCard extends Card {
	private static final long serialVersionUID = 1L;

	public OptionCard(String name, Effect effect, String path) {
		super(name, effect, path);
	}
}