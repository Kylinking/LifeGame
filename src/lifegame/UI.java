package lifegame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class UI {

	Game game;
	JFrame frame;
	JPanel pane;

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		
		Timer timer = new Timer();

		final UI GameUI = new UI();	
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i=0;i<20;i++){
			list.add(i);
		}
		GameUI.game = Game.Initialize(list);
		
		GameUI.frame = new JFrame("LifeGame");
		GameUI.frame.setSize(640, 640);
		GameUI.frame.setVisible(true);
		GameUI.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		GameUI.pane = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				//Life[] lives = GameUI.game.GetLives();
				g.clearRect(0, 0, getWidth(), getHeight());
				g.setColor(Color.BLUE);
				for (int i = 0; i != GameUI.game.GetLives().length; i++) {
					int x = i % 64 * 10;
					int y = i / 64 * 10;
					if (GameUI.game.GetLives()[i].isLive()) {
						g.fillRect(x, y, 10, 10);
					}
				}				
			}
		};
		
		GameUI.frame.add(GameUI.pane);
		timer.schedule(new TimerTaskTest(GameUI.pane,GameUI.game), 3000, 1000);
	}

	static class TimerTaskTest extends TimerTask {
		JPanel panel;
		Game game;
		TimerTaskTest(JPanel panel,Game game) {
			this.panel = panel;
			this.game = game;
		}

		@Override
		public void run() {
			game.NextGeneratation();
			panel.repaint();
		}

	}
}
