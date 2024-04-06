package mahjong;

import java.util.ArrayList;

public class Hand {
	public ArrayList<Tile> tiles;
	public ArrayList<Tile> discards;
	
	public ArrayList<Tile> getTile(){
		return tiles;
	}
	
	public void addTile(Tile tile){
		tiles.add(tile);
	}
	
	public void removeTile(int pos){
		discards.add(tiles.get(pos));
		tiles.remove(pos);
	}
	
	public ArrayList<Tile> getDiscards(){
		return discards;
	}
	
	public void removeDiscards(int pos){
		tiles.add(discards.get(pos));
		discards.remove(pos);
	}
}