//package lifegame;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Graphics;
//
//import javax.swing.*;
//
//
//import java.util.ArrayList;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class UI {
//
//	Game game;
//	JFrame frame;
//	JPanel pane,southPane;
//	JButton buttonStart,buttonRestart;
//
//	@SuppressWarnings("serial")
//	public static void main(String[] args) {
//		
//		Timer timer = new Timer();
//
//		final UI GameUI = new UI();	
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		for (int i=0;i<20;i++){
//			list.add(i);
//		}
//		GameUI.game = Game.Initialize(list);
//		
//		GameUI.frame = new JFrame("LifeGame");
//		GameUI.frame.setSize(640, 640);
//		GameUI.frame.setVisible(true);
//		GameUI.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//		GameUI.pane = new JPanel() {
//			public void paint(Graphics g) {
//				super.paint(g);
//				g.clearRect(0, 0, getWidth(), getHeight());
//				g.setColor(Color.BLUE);
//				for (int i = 0; i != GameUI.game.GetLives().length; i++) {
//					int x = i % 64 * 10;
//					int y = i / 64 * 10;
//					if (GameUI.game.GetLives()[i].isLive()) {
//						g.fillRect(x, y, 10, 10);
//					}
//				}				
//			}
//		};
//		GameUI.buttonStart = new JButton("开始");
//		GameUI.buttonRestart = new JButton("重新开始");
//		GameUI.southPane = new JPanel();
//		GameUI.southPane.add(GameUI.buttonStart);
//		GameUI.southPane.add(GameUI.buttonRestart);
//		GameUI.frame.add(GameUI.pane,BorderLayout.CENTER);
//		GameUI.frame.add(GameUI.southPane,BorderLayout.SOUTH);
//		
//		timer.schedule(new TimerTaskTest(GameUI.pane,GameUI.game), 3000, 1000);
//	}
//
//	static class TimerTaskTest extends TimerTask {
//		JPanel panel;
//		Game game;
//		TimerTaskTest(JPanel panel,Game game) {
//			this.panel = panel;
//			this.game = game;
//		}
//
//		@Override
//		public void run() {
//			game.NextGeneratation();
//			panel.repaint();
//		}
//
//	}
//}
