package lifegame;

import java.util.*;

public class Game {
	public int lifeCount;
	private Life[] lives = new Life[64*64];
	private boolean isGoOn; 
	
	public Game() {
		for (int i = 0; i != lives.length; i++) {
			lives[i] = new Life();
		}
		lives[200].setLive();
		lives[201].setLive();
		lives[202].setLive();
		lives[199+64].setLive();
		lives[201+64].setLive();
		lives[200+64].setLive();
		LinkNeighbours();		
	}
	
	@SuppressWarnings("unchecked")
	public Game(List points){
		for (int i = 0; i != lives.length; i++) {
			lives[i] = new Life();
		}
		for(Integer j:(ArrayList<Integer>)points){
			lives[j.intValue()].setLive();
		}
		LinkNeighbours();	
	}
	
	public void NextGeneratation(){	
		for (Life i : lives) {
			i.CheckRule();
		}
		for (Life i : lives) {
			i.RefreshNeighbours();
		}
	}
	
	private void LinkNeighbours()
	{
		for (int i = 0; i != lives.length; i++) {			
			ArrayList<Life> neighbors = new ArrayList<Life>();
			int x = i % 64;
			int y = i / 64;
			if (x == 0) {
				if (y == 0) {
					neighbors.add(lives[x + 1 + y * 64]);
					neighbors.add(lives[(y + 1) * 64 + x]);
					neighbors.add(lives[(y + 1) * 64 + x + 1]);
				} else if (y == 63) {
					neighbors.add(lives[x + 1 + y * 64]);
					neighbors.add(lives[(y - 1) * 64 + x]);
					neighbors.add(lives[(y - 1) * 64 + x + 1]);
				} else {
					neighbors.add(lives[x + 1 + y * 64]);
					neighbors.add(lives[(y - 1) * 64 + x]);
					neighbors.add(lives[(y - 1) * 64 + x + 1]);
					neighbors.add(lives[(y + 1) * 64 + x]);
					neighbors.add(lives[(y + 1) * 64 + x + 1]);
				}
			} else if (x == 63) {
				if (y == 0) {
					neighbors.add(lives[x - 1 + y * 64]);
					neighbors.add(lives[(y + 1) * 64 + x]);
					neighbors.add(lives[(y + 1) * 64 + x - 1]);
				} else if (y == 63) {
					neighbors.add(lives[x - 1 + y * 64]);
					neighbors.add(lives[(y - 1) * 64 + x]);
					neighbors.add(lives[(y - 1) * 64 + x - 1]);
				} else {
					neighbors.add(lives[x - 1 + y * 64]);
					neighbors.add(lives[(y - 1) * 64 + x - 1]);
					neighbors.add(lives[x + (y + 1) * 64]);
					neighbors.add(lives[(y - 1) * 64 + x]);
					neighbors.add(lives[(y + 1) * 64 + x - 1]);
				}
			} else {
				if (y == 0) {
					neighbors.add(lives[x - 1 + y * 64]);
					neighbors.add(lives[x + 1 + y * 64]);
					neighbors.add(lives[(y + 1) * 64 + x - 1]);
					neighbors.add(lives[(y + 1) * 64 + x + 1]);
					neighbors.add(lives[(y + 1) * 64 + x]);
				} else if (y == 63) {
					neighbors.add(lives[x - 1 + y * 64]);
					neighbors.add(lives[x + 1 + y * 64]);
					neighbors.add(lives[(y - 1) * 64 + x - 1]);
					neighbors.add(lives[(y - 1) * 64 + x + 1]);
					neighbors.add(lives[(y - 1) * 64 + x]);
				} else {
					neighbors.add(lives[x - 1 + y * 64]);
					neighbors.add(lives[x + 1 + y * 64]);
					neighbors.add(lives[(y - 1) * 64 + x - 1]);
					neighbors.add(lives[(y - 1) * 64 + x + 1]);
					neighbors.add(lives[(y - 1) * 64 + x]);
					neighbors.add(lives[(y + 1) * 64 + x - 1]);
					neighbors.add(lives[(y + 1) * 64 + x + 1]);
					neighbors.add(lives[(y + 1) * 64 + x]);
				}
			}
			lives[i].setNeighbour(neighbors);
		}	
	}
		
	public Life[] GetLives(){		
		return lives;		
	}
	
}
