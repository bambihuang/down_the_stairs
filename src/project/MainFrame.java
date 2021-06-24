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
	private LogInPanel login;
	private MenuPanel menu;
	private GamePanel game;
	private GameOverPanel gameOver;
	private User user;
	
	// 遊戲的主視窗
	MainFrame(int panel, User user) {
		this.user = user;
		initFont();
		setPanel(panel);
		initFrame();
		showPanel(); // 顯示對應子面板
	}
	
	private void initFont() {
		new CustomFont();
	}
	
	private void setPanel(int panel) {// 建立所有子面板 & 設定欲顯示的子面板
		this.panel = panel;
		if (mainPanel == null) {
			cLayout = new CardLayout();
			mainPanel = new JPanel(cLayout);
			mainPanel.setSize(600, 600);
		}
		if (panel == 1 && login == null) {
			login = new LogInPanel(user);
			mainPanel.add(login,"login");
		}
		if (panel == 2 && menu == null) {
			menu = new MenuPanel(user);
			mainPanel.add(menu,"menu");
		}
		if (panel == 3 && game == null) {
			game = new GamePanel();
			mainPanel.add(game,"game");
		}
		if (panel == 4 && gameOver == null) {
			gameOver = new GameOverPanel(user);
			mainPanel.add(gameOver,"game over");
		}
	}
	
	private int getPanel() {
		return panel;
	}
	
	private void initFrame() { // 建立主視窗
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