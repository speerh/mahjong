package mahjong;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class YakuTest {
	
	@Test
	public void testTanyao() {
		Yaku yaku = new Yaku();
		Hand hand = new Hand();
		for(int i = 2; i < 9; i++) {
			Tile tile = new Tile("bamboo", i, false);
			hand.addTile(tile);
		}
		boolean win = yaku.checkWin(hand);
		assertTrue("Expected Result True", win);
	}
	
	@Test
	public void testYakuhai() {
		Yaku yaku = new Yaku();
		Hand hand = new Hand();
		for(int i = 1; i < 4; i++) {
			Tile tile = new Tile("dragon", i, false);
			hand.addTile(tile);
		}
		boolean win = yaku.checkWin(hand);
		assertTrue("Expected Result True", win);
	}
	
	@Test
	public void testIttsuu() {
		Yaku yaku = new Yaku();
		Hand hand = new Hand();
		for(int i = 1; i < 10; i++) {
			Tile tile = new Tile("character", i, false);
			hand.addTile(tile);
		}
		boolean win = yaku.checkWin(hand);
		assertTrue("Expected Result True", win);
	}
	
	@Test
	public void testFalse() {
		Yaku yaku = new Yaku();
		Hand hand = new Hand();
		for(int i = 1; i < 4; i++) {
			Tile tile = new Tile("wind", i, false);
			hand.addTile(tile);
		}
		boolean win = yaku.checkWin(hand);
		assertFalse("Expected Result False", win);
	}
}
