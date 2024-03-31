package mahjong;

import java.util.ArrayList;

public class TileSet {
	private ArrayList<Tile> tileset;
	//set up list of tiles;
	public TileSet() {
		//init suits
		
		//init circles
		initSuit("circle");
		//init bamboos
		initSuit("bamboo");
		//init characters
		initSuit("character");
		
		//init honor sets
		
		//init winds
		initTile(new Tile("wind", 1, true), 4);
		initTile(new Tile("wind", 2, true), 4);
		initTile(new Tile("wind", 3, true), 4);
		initTile(new Tile("wind", 4, true), 4);
		
		//init dragons
		initTile(new Tile("dragon", 1, true), 4);
		initTile(new Tile("dragon", 2, true), 4);
		initTile(new Tile("dragon", 3, true), 4);


	}
	
	//c = copies of tile
	public void initTile(Tile tile, int c) {
		for (int i = 0; i < c; i++) {
			tileset.add(tile);
		}
	}
	
	public void initSuit(String suit) {
		for(int i = 0; i < 9; i++) {
			Boolean term = false;
			if(i == 1 || i == 8)
				term = true;
			else
				term = false;
			initTile(new Tile(suit, i + 1, term), 4);
		}
	}
	
}
