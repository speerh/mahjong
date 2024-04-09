package mahjong;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Systems {
	//check straights
	public Boolean checkStraight(ArrayList<Tile> inp) {
		//is this line actually necessary? i have no clue
		inp = sort(inp);
		for(int i = 1; i < 3; i++) {
			if(inp.get(i).getNumber() != inp.get(i-1).getNumber()) {
				return false;
			}
		}
		return true;
	}
	
	//check triplets
	public Boolean checkTriplet(ArrayList<Tile> inp) {
		inp = sort(inp);
		int num = inp.get(0).getNumber();
		for(int i = 1; i < 3; i++) {
			if(inp.get(i).getNumber() != num) {
				return false;
			}
		}
		return true;
	}
	
	//check quadruplets
	public Boolean checkQuadruplet(ArrayList<Tile> inp) {
		return true;
	}
	
	//check hand
	public Boolean checkHand(Hand inp) {
		
		return true;
	}
	
	//sort
	public ArrayList<Tile> sort(ArrayList<Tile> inp){
		//check all same suit
		//honestly could break. i don't like this method
		String s = inp.get(0).getSuit();
		for(Tile i :  inp) {
			//if suit doesnt match, return early
			if(!i.getSuit().equalsIgnoreCase(s)) {
				return null;
			}
		}
			Tile temp1;
			//this sort sucks but it's very very easy to implement for 3 items
			if (inp.get(0).getNumber() > inp.get(1).getNumber()){
				temp1 = inp.get(1);
				inp.set(1, inp.get(0));
				inp.set(0, temp1);
				
			}
			if (inp.get(1).getNumber() > inp.get(2).getNumber()){
				temp1 = inp.get(2);
				inp.set(2, inp.get(0));
				inp.set(1, temp1);
			}
			if (inp.get(0).getNumber() > inp.get(1).getNumber()){
				temp1 = inp.get(1);
				inp.set(1, inp.get(0));
				inp.set(0, temp1);
				
			}
		return inp;
	}
	
	//is non-honor suit?
	public Boolean isHonor(String inp) {
		if(inp == "circle" || inp == "bamboo" || inp == "character") {
			return false;
		}
		else if(inp == "wind" || inp == "dragon") {
			return true;
		}
		else
			return false;
	}
	
}
