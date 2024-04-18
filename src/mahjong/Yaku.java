package mahjong;

import java.util.ArrayList;

public class Yaku {
	private int points;
	private int distance;
	private String name;
	private boolean win;
	private String suit;
	private Integer num;
	private ArrayList<String> newTiles = new ArrayList<String>();

	public void checkWin(Hand hand) {

		for (Tile tile : hand.getTile()) {
			suit = tile.getSuit();
			num = tile.getNumber();

			newTiles.add(suit + num);
		}
		
		// Tanyao - hand does not contain terminals, 1's or 9s
		String[] inputString = { "bamboo2", "bamboo3", "bamboo4", "bamboo5", "bamboo6", "bamboo7", "bamboo8",
				"character2", "character3", "character4", "character5", "character6", "character7", "character8",
				"circle2", "circle3", "circle4", "circle5", "circle6", "circle7", "circle8" };

		for (String item : inputString) {
			if (!newTiles.contains(item)) {
				win = false;
				break;
			} else {
				name = "Tanyao";
				points = 40;
				win = true;
				break;
			}
		}
	}
}
