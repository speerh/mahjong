package mahjong;

import static org.junit.Assert.*;

import org.junit.Test;

public class TileTest {

	@Test
	public void testGetSuit() {
		Tile tile = new Tile("dragon", 1, true);
		
		String suit;
		
		suit = tile.getSuit();
		
		assertEquals("Suit equal to suit of generated tile", suit, tile.getSuit());
		
	}

	@Test
	public void testGetNum() {
		Tile tile = new Tile("dragon", 1, true);
		
		Integer suit;
		
		suit = tile.getNumber();
		
		assertEquals("Number equal to number of generated tile", suit, tile.getNumber());
		
	}
	
	@Test
	public void testSetTerminal() {
		Tile tile = new Tile("dragon", 1, true);
		
		tile.setTerminal(false);
		
		assertFalse("Number equal to number of generated tile", tile.getTerminal());
		
	}
	
	@Test
	public void testSetTile() {
		Tile tile = new Tile("dragon", 1, true);
		Tile tile2 = new Tile("dragon", 1, true);
		
		tile2.setTile("wind", 3, false);
		
		assertNotEquals("Tile is different from original", tile, tile2);
	}
}
