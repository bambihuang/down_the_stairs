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
	
	// �C�����D����
	MainFrame(int panel) {
		initFont();
		initFrame();
		setPanel(panel); // �]�w����ܪ��l���O
		showPanel(); // ��ܹ����l���O
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
	
	private void initFrame() { // �إߩҦ��l���O�ΥD����
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