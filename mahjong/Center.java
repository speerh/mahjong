package mahjong;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Center {
	//drawable tiles in the center
	private TileSet center;
	//add thing to draw tile
	private String[] originalTiles = {"bamboo1", "bamboo2", "bamboo3", "bamboo4", "bamboo5", "bamboo6", "bamboo7", "bamboo8", "bamboo9", "character1", "character2", "character3", "character4", "character5", "character6", "character7", "character8", "character9", "circle1", "circle2", "circle3", "circle4", "circle5", "circle6", "circle7", "circle8", "circle9", "dragon1", "dragon2", "dragon3", "wind1", "wind2", "wind3", "wind4"};
	private static ArrayList<Tile> centerTiles = new ArrayList<Tile>();
	static Tile tile = new Tile (null, 0, null);
	static boolean terminal;
	
	
	
	public static void addToArrayList(String arr[])
    {
		
        for (int i = 0; i < 4; i++) {
        	 for (int j = 0; j < arr.length; j++) {
        		 
        		 tile = new Tile (null, 0, null);
        		 String str1 = arr[j].substring(0,arr[j].length()-1);
        		 int str2 = Integer.parseInt(arr[j].substring(arr[j].length()-1));
        		 
        		 if(str2 == 1 || str2 == 9 || (str1 == "wind" && str2 == 4) || (str1 == "dragon" && str2 == 4)) {
        			 terminal = true;
        		 }
        		 else if (str1 == "dragon" || str1 == "wind"){
        			 terminal = true;
        		 }
        		 else {
        			 terminal = false;
        		 }
        		 
        		 tile.setTile(arr[j].substring(0,arr[j].length()-1), Integer.parseInt(arr[j].substring(arr[j].length()-1)), terminal);
        		 String tile1 = tile.getSuit();
        		 int tile2 = tile.getNumber();
        		 //System.out.println(tile1 + " " + tile2 + " " + terminal);
        		 centerTiles.add(tile);
        		 
        	 }
        }
    }
	
	public void removeFromCenter() {
		centerTiles.remove(0);
	}
	
	public Tile getFirstTile() {
		return centerTiles.get(0);
		
	}
	
	public void shuffle() {
		Collections.shuffle(centerTiles);
	}
	
	public static void main() {
		System.out.println(centerTiles);
	}
	
	public Center() {
		
		addToArrayList(originalTiles);
		Collections.shuffle(centerTiles);
		
	}
}
