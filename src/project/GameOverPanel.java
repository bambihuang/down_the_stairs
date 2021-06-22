package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*
 * ¹CÀ¸µ²§ô­¶­±
 */
public class GameOverPanel extends JPanel implements ActionListener {
	JButton jbutton_playagain, jbutton_menu, jbutton_ranking, jbutton_messageboard;
	JLabel text,score;
	
	GameOverPanel() {
		text = new JLabel("YOUR SCORE:", JLabel.CENTER);
		text.setBounds(200, 100, 200, 40);
		text.setFont(new Font("Pixelony", Font.TRUETYPE_FONT, 25));
		text.setForeground(Color.white);
		this.add(text);
		
		score = new JLabel(String.valueOf(10000), JLabel.CENTER);
		score.setBounds(200, 150, 200, 40);
		score.setFont(new Font("Pixelony", Font.TRUETYPE_FONT, 25));
		score.setForeground(Color.white);
		this.add(score);
		
		jbutton_playagain = new JButton(new ImageIcon("img/playagain.png"));
		jbutton_playagain.setBounds(200, 232, 92, 92);
		jbutton_playagain.setBorderPainted(false);
		jbutton_playagain.setContentAreaFilled(false);
		jbutton_playagain.addActionListener(this);
		this.add(jbutton_playagain);
		jbutton_menu = new JButton(new ImageIcon("img/exit.png"));
		jbutton_menu.setBounds(308, 232, 92, 92);
		jbutton_menu.setBorderPainted(false);
		jbutton_menu.setContentAreaFilled(false);
		jbutton_menu.addActionListener(this);
		this.add(jbutton_menu);
		jbutton_ranking = new JButton(new ImageIcon("img/ranking.png"));
		jbutton_ranking.setBounds(200, 340, 92, 92);
		jbutton_ranking.setBorderPainted(false);
		jbutton_ranking.setContentAreaFilled(false);
		jbutton_ranking.addActionListener(this);
		this.add(jbutton_ranking);
		jbutton_messageboard = new JButton(new ImageIcon("img/msgboard.png"));
		jbutton_messageboard.setBounds(308, 340, 92, 92);
		jbutton_messageboard.setBorderPainted(false);
		jbutton_messageboard.setContentAreaFilled(false);
		jbutton_messageboard.addActionListener(this);
		this.add(jbutton_messageboard);
		
		this.setSize(600, 600);
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(Color.black);
	} // GameOverPanel end
	
	public void actionPerformed(ActionEvent evt) { 
		if (evt.getSource() == jbutton_playagain)
			// TODO:clear acc score
			new MainFrame(3);
		else if(evt.getSource() == jbutton_menu)
			// TODO:clear acc score
			new MainFrame(2);
		else if (evt.getSource() == jbutton_ranking)
			new RankingBoard();
		else if (evt.getSource() == jbutton_messageboard)
			new MessageBoard();
	}
}