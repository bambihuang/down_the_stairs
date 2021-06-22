package project;

import java.awt.CardLayout;
import javax.swing.*;

/*
 * 此類別控制在視窗中所有頁面的生成及切換流程
 */
public class MainFrame {
	private static JFrame jframe; // 維持一個主視窗
	private static CardLayout cLayout;
	private static JPanel mainPanel; // 主面板用來容納所有子面板
	private int panel;
	
	// 遊戲的主視窗
	MainFrame(int panel) {
		initFont();
		initFrame();
		setPanel(panel); // 設定欲顯示的子面板
		showPanel(); // 顯示對應子面板
	}
	
	private void initFont() {
		new CustomFont();
	}
	
	private void setPanel(int panel) {
		this.panel = panel;
	}
	
	private int getPanel() {
		return panel;
	}
	
	private void initFrame() { // 建立所有子面板及主視窗
		if (mainPanel == null) {
			cLayout = new CardLayout();
			mainPanel = new JPanel(cLayout);
			mainPanel.setSize(600, 600);
			LogInPanel login = new LogInPanel();
			mainPanel.add(login,"login");
			MenuPanel menu = new MenuPanel();
			mainPanel.add(menu,"menu");
			GamePanel game = new GamePanel();
			mainPanel.add(game,"game");
			GameOverPanel gameOver = new GameOverPanel();
			mainPanel.add(gameOver,"game over");
		}
		if (jframe == null) {
			jframe = new JFrame();
			jframe.add(mainPanel);
			jframe.setSize(600, 600);
			jframe.setLayout(null);
			jframe.setVisible(true);
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jframe.setResizable(false);
			jframe.setLocationRelativeTo(null);
		}
	}
	
	private void showPanel() {
		if (getPanel() == 1) {
			cLayout.show(mainPanel, "login");
			jframe.setTitle("Log in");
		} else if(getPanel() == 2) {
			cLayout.show(mainPanel, "menu");
			jframe.setTitle("Game Menu");
		} else if(getPanel() == 3) {
			cLayout.show(mainPanel, "game");
			jframe.setTitle("Start Game");
		} else if(getPanel() == 4) {
			cLayout.show(mainPanel, "game over");
			jframe.setTitle("Game Over");
		}
	}
}