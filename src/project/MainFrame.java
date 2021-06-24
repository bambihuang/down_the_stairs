package project;

import java.awt.CardLayout;
import javax.swing.*;

/*
 * �����O����b�������Ҧ��������ͦ��Τ����y�{
 */
public class MainFrame {
	private static JFrame jframe; // �����@�ӥD����
	private static CardLayout cLayout;
	private static JPanel mainPanel; // �D���O�ΨӮe�ǩҦ��l���O
	private int panel;
	private LogInPanel login;
	private MenuPanel menu;
	private GamePanel game;
	private GameOverPanel gameOver;
	private User user;
	
	// �C�����D����
	MainFrame(int panel, User user) {
		this.user = user;
		initFont();
		setPanel(panel);
		initFrame();
		showPanel(); // ��ܹ����l���O
	}
	
	private void initFont() {
		new CustomFont();
	}
	
	private void setPanel(int panel) {// �إߩҦ��l���O & �]�w����ܪ��l���O
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
	
	private void initFrame() { // �إߥD����
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