package testCases;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import mahjong.*;

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
	public void testToitoi1() {
		Yaku yaku = new Yaku();
		Hand hand = new Hand();
		Tile tile = new Tile("bamboo", 1, true);
		Tile tile2 = new Tile("bamboo", 1, true);
		Tile tile3 = new Tile("bamboo", 1, true);
		Tile tile4 = new Tile("circle", 1, true);
		Tile tile5 = new Tile("circle", 1, true);
		Tile tile6 = new Tile ("circle", 1, true);
		Tile tile7 = new Tile("character", 1, true);
		Tile tile8 = new Tile("character", 1, true);
		Tile tile9 = new Tile("character", 1, true);
		hand.addTile(tile);
		hand.addTile(tile2);
		hand.addTile(tile3);
		hand.addTile(tile4);
		hand.addTile(tile5);
		hand.addTile(tile6);
		hand.addTile(tile7);
		hand.addTile(tile8);
		hand.addTile(tile9);
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
