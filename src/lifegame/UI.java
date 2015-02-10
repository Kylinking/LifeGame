package lifegame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class UI {

	public Game game;
	JFrame frame;
	JPanel pane;

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		Timer timer = new Timer();
		// TODO Auto-generated method stub
		final UI GameUI = new UI();		
		GameUI.game = new Game();
		GameUI.frame = new JFrame("LifeGame");
		GameUI.frame.setSize(640, 640);
		GameUI.frame.setVisible(true);
		GameUI.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		GameUI.pane = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				Life[] lives = GameUI.game.GetLives();
				g.clearRect(0, 0, getWidth(), getHeight());
				g.setColor(Color.BLUE);
				for (int i = 0; i != lives.length; i++) {
					int x = i % 64 * 10;
					int y = i / 64 * 10;
					if (lives[i].isLive()) {
						g.drawRect(x, y, 10, 10);
					}
				}
				GameUI.game.NextGeneratation();
				// System.out.println("Hello");
			}
		};
		GameUI.frame.add(GameUI.pane);
		timer.schedule(new TimerTaskTest(GameUI.pane), 0, 1000);
	}

	static class TimerTaskTest extends TimerTask {
		JPanel panel;

		TimerTaskTest(JPanel panel) {
			this.panel = panel;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			panel.repaint();

		}

	}
}
