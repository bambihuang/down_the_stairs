package project;

import javax.swing.*;

/*
 * 遊戲遊玩頁面
 */
public class GamePanel extends JPanel {
	JButton jbutton_stop;
	JLabel jlabel_blood, jlabel_score;
	
	GamePanel() {
		jbutton_stop = new JButton("結束");
		jbutton_stop.setBounds(200, 100, 100, 40);
		jbutton_stop.addActionListener(e -> new MainFrame(4));// test use
		add(jbutton_stop);
		
		jlabel_blood = new JLabel("show blood");
		jlabel_blood.setBounds(50, 100, 250, 20);
		//add(jlabel_blood);
		
		jlabel_score = new JLabel("show score");
		jlabel_score.setBounds(50, 150, 250, 20);
		//add(jlabel_score);
		
		setSize(500, 500);
		setLayout(null);
		setVisible(true);
	}
}