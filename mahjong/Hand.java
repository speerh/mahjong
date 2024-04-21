package mahjong;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;

public class Hand implements Serializable{
	public ArrayList<Tile> tiles = new ArrayList<Tile>();
	public ArrayList<Tile> discards = new ArrayList<Tile>();
	
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
	
	public void setHand(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	public ArrayList<Tile> sort(Hand hand){
		ArrayList<Tile> character = new ArrayList<Tile>();
		ArrayList<Tile> circle = new ArrayList<Tile>();
		ArrayList<Tile> bamboo = new ArrayList<Tile>();
		ArrayList<Tile> dragon = new ArrayList<Tile>();
		ArrayList<Tile> wind = new ArrayList<Tile>();
		ArrayList<Tile> sortedHand = new ArrayList<Tile>();
		
		for (Tile tile : hand.getTile()) {
			if(tile.getSuit().equals("character")) {
				character.add(tile);
			} else if (tile.getSuit().equals("circle")) {
				circle.add(tile);
			} else if (tile.getSuit().equals("bamboo")) {
				bamboo.add(tile);
			} else if (tile.getSuit().equals("dragon")) {
				dragon.add(tile);
			} else if (tile.getSuit().equals("wind")) {
				wind.add(tile);
			}
		}
		
		Collections.sort(character, Comparator.comparing(Tile::getNumber));
		Collections.sort(circle, Comparator.comparing(Tile::getNumber));
		Collections.sort(bamboo, Comparator.comparing(Tile::getNumber));
		Collections.sort(dragon, Comparator.comparing(Tile::getNumber));
		Collections.sort(wind, Comparator.comparing(Tile::getNumber));
		
		sortedHand.addAll(character);
		sortedHand.addAll(circle);
		sortedHand.addAll(bamboo);
		sortedHand.addAll(dragon);
		sortedHand.addAll(wind);
		
		return sortedHand;
	}
}