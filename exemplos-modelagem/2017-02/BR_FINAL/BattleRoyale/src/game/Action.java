package game;

import br.ufsc.inf.leobr.cliente.Jogada;
import enums.ActionType;
import enums.CharacterType;

public class Action implements Jogada {

	protected int initX;
	protected int initY;
	protected int endX;
	protected int endY;
	protected ActionType actionType;
	protected CharacterType charType;

	public Action(int initX, int initY, int endX, int endY, ActionType actionType, CharacterType charType) {
		this.initX = initX;
		this.initY = initY;
		this.endX = endX;
		this.endY = endY;
		this.actionType = actionType;
		this.charType = charType;
	}

	public int getInitX() {
		return initX;
	}

	public void setInitX(int initX) {
		this.initX = initX;
	}

	public int getInitY() {
		return initY;
	}

	public void setInitY(int initY) {
		this.initY = initY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public CharacterType getCharType() {
		return charType;
	}

	public void setCharType(CharacterType charType) {
		this.charType = charType;
	}


}
