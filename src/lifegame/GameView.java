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
	InitPanel initPanel;
	JPanel buttonPanel, gamePanel;
	JButton buttonStart;
	Timer timer;

	public GameView() {
		frame = new JFrame("Life Game");
		Dimension maxmumsize = new Dimension();
		maxmumsize.setSize(1280, 1280);
		initPanel = new InitPanel();
		frame.setSize(600, 700);
		frame.add(initPanel, BorderLayout.CENTER);
		buttonStart = new StartButton("Start");
		buttonStart.addActionListener(new InitPanelButtonListener());
		buttonPanel = new JPanel();
		buttonPanel.add(buttonStart);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@SuppressWarnings("serial")
	class StartButton extends JButton {
		ArrayList<Integer> list;

		public StartButton() {
			super();
			game = Game.GetInstance();
		}

		public StartButton(String string) {
			super(string);
			game = Game.GetInstance();
		}
	}

	static class TimerTaskTest extends TimerTask {
		private JPanel pane;

		TimerTaskTest(JPanel panel) {
			pane = panel;
		}

		@Override
		public void run() {
			Game.GetInstance().NextGeneratation();
			pane.repaint();
		}

	}

	class InitPanelButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gamePanel = new GamePanel();
			game.RestartAt(initPanel.GetSeletectRect());
			frame.remove(initPanel);
			frame.add(gamePanel, BorderLayout.CENTER);
			frame.repaint();
			timer = new Timer();
			timer.schedule(new TimerTaskTest(gamePanel), 1000, 1000);
			buttonStart.setText("ReStart");
			buttonStart.removeActionListener(this);
			buttonStart.addActionListener(new GamePanelButtonListener());
		}
	}

	class GamePanelButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame.remove(gamePanel);
			frame.add(initPanel, BorderLayout.CENTER);
			buttonStart.setText("Start");
			buttonStart.removeActionListener(this);
			buttonStart.addActionListener(new InitPanelButtonListener());
			initPanel.GetSeletectRect().clear();
			timer.cancel();
			frame.repaint();
		}
	}

	public static void main(String[] args) {
		GameView ui = new GameView();
	}
}

@SuppressWarnings("serial")
class InitPanel extends JPanel implements MouseListener {

	private ArrayList<Integer> selected;

	public InitPanel() {
		this.setSize(600, 600);
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
		int x = arg0.getX() / 20;
		int y = arg0.getY() / 20;
		Graphics g = this.getGraphics();
		g.setColor(Color.black);
		g.fillRect(x * 20, y * 20, 20, 20);
		g.setColor(Color.gray);
		g.drawRect(x * 20, y * 20, 20, 20);
		// System.out.println("x: " + x + " y: " + y);
		this.selected.add(y * 64 + x);

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
		this.setSize(600, 600);
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		for (int i = 0; i != game.GetLives().length; i++) {
			int x = i % 64 * 20;
			int y = i / 64 * 20;
			g.setColor(Color.gray);
			g.fillRect(x, y, 20, 20);
			g.setColor(Color.black);
			g.drawRect(x, y, 20, 20);
			if (game.GetLives()[i].isLive()) {
				g.fillRect(x, y, 20, 20);
				g.setColor(Color.gray);
				g.drawRect(x, y, 20, 20);
			}
		}
	}
}
