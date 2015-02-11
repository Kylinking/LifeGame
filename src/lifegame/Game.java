package lifegame;

import java.util.*;

public class Game {
	public int lifeCount;
	private Life[] lives = new Life[64 * 64];
	private static Game game;

	private Game() {
		for (int i = 0; i != lives.length; i++) {
			lives[i] = new Life();
		}
		LinkNeighbours();
	}

	@SuppressWarnings("unchecked")
	public Game(List<Integer> points) {
		for (int i = 0; i != lives.length; i++) {
			lives[i] = new Life();
		}
		for (Integer j : (ArrayList<Integer>) points) {
			lives[j.intValue()].setLive();
		}
		LinkNeighbours();
	}

	public void NextGeneratation() {
		for (Life i : lives) {
			i.CheckRule();
		}
		for (Life i : lives) {
			i.RefreshNeighbours();
		}
	}

	private void LinkNeighbours() {
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
	public static Game GetInstance(){
		if (game == null) {
			game = new Game();
		}
		return game;
	}
	public Life[] GetLives() {
		return lives;
	}

	public Game Initialize() {
		if (game == null) {
			game = new Game();
		}
		game.DefaultStartAt();
		return game;
	}

	public Game Initialize(ArrayList<Integer> list) {
		if (game == null) {
			game = new Game();
		}
		game.SetStartAt(list);
		return game;
	}

	private void DefaultStartAt() {
		game.lives[199 + 64 * 18].setLive();
		game.lives[201 + 64 * 18].setLive();
		game.lives[202 + 64 * 18].setLive();
		game.lives[203 + 64 * 18].setLive();
		game.lives[204 + 64 * 18].setLive();
		game.lives[205 + 64 * 18].setLive();
		game.lives[206 + 64 * 18].setLive();
		game.lives[207 + 64 * 18].setLive();
		game.lives[208 + 64 * 18].setLive();
		// lives[209+64*18].setLive();
		// lives[210+64*18].setLive();
		// lives[211+64*18].setLive();
		// lives[212+64*18].setLive();
		game.LinkNeighbours();
	}

	private void SetStartAt(ArrayList<Integer> list) {
		for (Integer i : list) {
			game.lives[i.intValue()].setLive();
		}
		game.LinkNeighbours();
	}

	public void ReStartAt(ArrayList<Integer> list) {
		if (game == null) {
			game = new Game();
		} else {
			for (int i = 0; i != lives.length; i++) {
				game.lives[i].cleanLive();
			}
		}
		for (Integer i : list) {
			game.lives[i.intValue()].setLive();
		}
		game.LinkNeighbours();
	}
}
