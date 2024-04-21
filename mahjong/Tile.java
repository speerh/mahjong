package mahjong;

import java.io.Serializable;

public class Tile implements Serializable{
	private String suit;
	private int number;
	private Boolean terminal;

	public Tile(String suit, int num, Boolean terminal) {
		this.suit = suit;
		number = num;
		this.terminal = terminal;

		// ** WINDS: E S W N (1 2 3 4)
		// ** DRAGONS: R G W (1 2 3)
	}

	public String getSuit() {
		return suit;
	}

	public Integer getNumber() {
		return number;
	}

	public Boolean getTerminal() {
		return terminal;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setTerminal(Boolean terminal) {
		this.terminal = terminal;
	}

	public void setTile(String suit, int num, Boolean terminal) {
		setSuit(suit);
		setNumber(num);
		setTerminal(terminal);
	}

	public int compareTo(Tile o)
	{
	     return(number - o.number);
	}
}