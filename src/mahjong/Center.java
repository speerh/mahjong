package mahjong;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Center {
	//drawable tiles in the center
	private TileSet center;
	//add thing to draw tile
	private String[] originalTiles = {"bamboo1", "bamboo2", "bamboo3", "bamboo4", "bamboo5", "bamboo6", "bamboo7", "bamboo8", "bamboo9", "character1", "character2", "character3", "character4", "character5", "character6", "character7", "character8", "character9", "circle1", "circle2", "circle3", "circle4", "circle5", "circle6", "circle7", "circle8", "circle9", "dragon1", "dragon2", "dragon3", "dragon4", "wind1", "wind2", "wind3", "wind4"};
	private static ArrayList<Tile> centerTiles = new ArrayList<Tile>();
	static Tile tile = new Tile (null, 0, null);
	static boolean terminal;
	
	public Center() {
		addToArrayList(originalTiles);
		addToArrayList(originalTiles);
		addToArrayList(originalTiles);
		addToArrayList(originalTiles);
		Collections.shuffle(centerTiles);
		
	}
	
	public static void addToArrayList(String arr[])
    {
        for (int i = 0; i < 4; i++) {
        	 for (int j = 0; j < arr.length; j++) {
        		 if(arr.length % 2 == 1 || arr.length % 2 == 9) {
        			 terminal = true;
        		 }
        		 else {
        			 terminal = false;
        		 }
        		 tile.setTile(arr[j].substring(arr[j].length()-1), Integer.parseInt(arr[j].substring(arr[j].length())), terminal);
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
}
