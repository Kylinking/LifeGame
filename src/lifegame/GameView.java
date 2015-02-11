package lifegame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameView {

	Game game;
	JFrame frame;
	JPanel initPanel, buttonPanel, gamePanel;
	JButton buttonStart, buttonRestart;
	Timer timer;

	public GameView() {
		timer = new Timer();
		frame = new JFrame("Life Game");
		Dimension maxmumsize = new Dimension();
		maxmumsize.setSize(1280, 1280);
		initPanel = new InitPanel();
		frame.setSize(600, 700);
		frame.add(initPanel, BorderLayout.CENTER);
		buttonStart = new StartButton("Start",
				((InitPanel) initPanel).GetSeletectRect());
		buttonRestart = new JButton("Default Demo");
		buttonPanel = new JPanel();
		buttonPanel.add(buttonStart);
		buttonPanel.add(buttonRestart);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@SuppressWarnings("serial")
	class InitPanel extends JPanel implements MouseListener {

		private ArrayList<Integer> selected;

		public InitPanel() {
			this.setSize(600, 800);
			addMouseListener(this);
			selected = new ArrayList<Integer>();
		}

		public void paint(Graphics g) {
			super.paint(g);
			int x, y;
			for (int i = 0; i != 64; i++) {
				x = i * 20;
				for (int j = 0; j != 64; j++) {
					y = j * 20;
					g.setColor(Color.gray);
					g.fillRect(x, y, 20, 20);
					g.setColor(Color.black);
					g.drawRect(x, y, 20, 20);
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			int x = arg0.getX() / 20;
			int y = arg0.getY() / 20;
			Graphics g = this.getGraphics();
			g.setColor(Color.black);
			g.fillRect(x * 20, y * 20, 20, 20);
			System.out.println("x: " + x + " y: " + y);
			this.selected.add(y * 64 + x);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public ArrayList<Integer> GetSeletectRect() {
			return this.selected;
		}
	}

	@SuppressWarnings("serial")
	class GamePanel extends JPanel {
		private Game game;

		public GamePanel() {
			this.game = Game.GetInstance();
			this.setSize(600, 800);
			this.setVisible(false);
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.clearRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.black);
			for (int i = 0; i != game.GetLives().length; i++) {
				int x = i % 64 * 10;
				int y = i / 64 * 10;
				if (game.GetLives()[i].isLive()) {
					g.fillRect(x, y, 10, 10);
				}
			}
		}
	}

	class StartButton extends JButton {
		ArrayList<Integer> list;
		private Game game;

		public StartButton(ArrayList<Integer> arrayList) {
			this.game = Game.GetInstance();
			list = arrayList;
		}

		public StartButton(String string, ArrayList<Integer> arrayList) {
			super(string);
			list = arrayList;
			this.game = Game.GetInstance();
		}

		@Override
		public void doClick() {
			this.game.Initialize(list);
		}
	}
}