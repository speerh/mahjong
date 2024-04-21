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

	public boolean checkWin(Hand hand) {
		int counter = 0;

		for (Tile tile : hand.getTile()) {
			suit = tile.getSuit();
			num = tile.getNumber();

			newTiles.add(suit + num);
		}
		// Tanyao - hand does not contain terminals, 1's or 9s
		String[] TanyaoBamboo = { "bamboo2", "bamboo3", "bamboo4", "bamboo5", "bamboo6", "bamboo7", "bamboo8" };

		for (String item : TanyaoBamboo) {
			if (!newTiles.contains(item)) {
				win = false;
				break;
			} else {
				name = "Tanyao";
				points = 40;
				win = true;
				counter++;
				if (counter == 7)
				{
					return win;
				}
			}
		}
		// Tanyao - hand does not contain terminals, 1's or 9s
		String[] TanyaoChar = { "character2", "character3", "character4", "character5", "character6", "character7",
				"character8", };

		counter = 0;
		for (String item : TanyaoChar) {
			if (!newTiles.contains(item)) {
				win = false;
				break;
			} else {
				name = "Tanyao";
				points = 40;
				win = true;
				counter++;
				if (counter == 7)
				{
					return win;
				}
			}
		}

		// Tanyao - hand does not contain terminals, 1's or 9s
		String[] TanyaoCircle = { "circle2", "circle3", "circle4", "circle5", "circle6", "circle7", "circle8" };

		counter = 0;
		for (String item : TanyaoCircle) {
			if (!newTiles.contains(item)) {
				win = false;
				break;
			} else {
				name = "Tanyao";
				points = 40;
				win = true;
				counter++;
				if (counter == 7)
				{
					return win;
				}
			}
		}
		// Yakuhai - hand contains triplet of dragon
		String[] Yakuhai = { "dragon1", "dragon2", "dragon3" };

		counter = 0;
		for (String item : Yakuhai) {
			if (!newTiles.contains(item)) {
				win = false;
				break;
			} else {
				name = "Yakuhai";
				points = 40;
				win = true;
				counter++;
				if (counter == 3)
				{
					return win;
				}
			}

		}
		// Ittsuu - hand contains 1-9 of in same suit
		String[] IttsuuBamboo = { "bamboo1", "bamboo2", "bamboo3", "bamboo4", "bamboo5", "bamboo6", "bamboo7",
				"bamboo8", "bamboo9" };

		counter = 0;
		for (String item : IttsuuBamboo) {
			if (!newTiles.contains(item)) {
				win = false;
				break;
			} else {
				name = "Ittsuu";
				points = 40;
				win = true;
				counter++;
				if (counter == 9)
				{
					return win;
				}

			}

		}
		// Ittsuu - hand contains 1-9 of in same suit
		String[] IttsuuCircle = { "circle1", "circle2", "circle3", "circle4", "circle5", "circle6", "circle7",
				"circle8", "circle9" };

		counter = 0;
		for (String item : IttsuuCircle) {
			if (!newTiles.contains(item)) {
				win = false;
				break;
			} else {
				name = "Ittsuu";
				points = 40;
				win = true;
				counter++;
				if (counter == 9)
				{
					return win;
				}

			}
		}
		// Ittsuu - hand contains 1-9 of in same suit
		String[] IttsuuChar = { "character1", "character2", "character3", "character4", "character5", "character6",
				"character7", "character8", "character9" };

		counter = 0;
		for (String item : IttsuuChar) {
			if (!newTiles.contains(item)) {
				win = false;
				break;
			} else {
				name = "Ittsuu";
				points = 40;
				win = true;
				counter++;
				if (counter == 9)
				{
					return win;
				}

			}

		}
		return win;
	}
}