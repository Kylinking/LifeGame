//package lifegame;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.WindowConstants;
//
//public class View {
//	Timer timer;
//
//	public View() {
//		timer = new Timer();
//	}
//
//	public static void main(String[] args) throws Exception {
//		View view = new View();
//		Game game = new Game();
//		JFrame frame = new JFrame("LifeGame");
//		frame.setSize(640, 640);
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE );
//		
//		JPanel pane = new JPanel() {
//			public void paint(Graphics g) {
//				super.paint(g);
//			}
//		};
//		ViewTimerTask t = new ViewTimerTask(game, pane);
//		pane.setBackground(Color.CYAN);
//		frame.add(pane);
//		//view.timer.schedule(new ViewTimerTask(game, pane), 1000, 1000);
//	}
//}
//
//class ViewTimerTask extends TimerTask {
//
//	private Game game;
//	JPanel panel;
//	Graphics g;
//
//	public ViewTimerTask(Game _game, JPanel _panel) {
//		game = _game;
//		panel = _panel;
//		g = panel.getGraphics();
//		g.setColor(Color.BLUE);
//	}
//
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		game.NextGeneratation();
//		PaintLives();
//	}
//
//	public void PaintLives() {		
//		Life[] lives = game.GetLives();
//		g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
//		for (int i = 0; i != lives.length; i++) {
//			int x = i % 64 * 10;
//			int y = i / 64 * 10;
//			if (lives[i].isLive()) {
//				g.fillRect(x, y, 10, 10);
//			}
//		}
//		//panel.repaint();
//		System.out.println("Fresh Game");
//	}
//}