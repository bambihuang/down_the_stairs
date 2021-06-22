package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.Border;

public class RankingBoard {

	private class RankingRow extends JPanel {
		private JLabel rank = new JLabel("", JLabel.LEFT);
		private JLabel player = new JLabel("Admin", JLabel.CENTER);
		private JLabel score = new JLabel("Date", JLabel.RIGHT);

		RankingRow() {
			rank.setMinimumSize(new Dimension(20, 18));
			rank.setMaximumSize(new Dimension(20, 18));
			rank.setFont(new Font("Pixelony", Font.TRUETYPE_FONT, 18));
			rank.setForeground(Color.white);
			player.setMinimumSize(new Dimension(200, 18));
			player.setMaximumSize(new Dimension(200, 18));
			player.setFont(new Font("Pixelony", Font.TRUETYPE_FONT, 18));
			player.setForeground(Color.white);
			score.setMinimumSize(new Dimension(80, 18));
			score.setMaximumSize(new Dimension(80, 18));
			score.setFont(new Font("Pixelony", Font.TRUETYPE_FONT, 16));
			score.setForeground(Color.white);
			this.setBorder(BorderFactory.createLineBorder(Color.white));
			GroupLayout l = new GroupLayout(this);
			this.setLayout(l);
			this.setBackground(Color.black);
			l.setAutoCreateGaps(true);
			l.setAutoCreateContainerGaps(true);
			l.setHorizontalGroup(l.createSequentialGroup()
					.addGroup(l.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(rank))
					.addGroup(l.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(player))
					.addGroup(l.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(score)));

			l.setVerticalGroup(l.createSequentialGroup()
					.addGroup(l.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(rank)
							.addComponent(player)
							.addComponent(score)));
		}

		void setText(int rank, String player, String score) {
			this.rank.setText(String.valueOf(rank));
			this.player.setText(player);
			this.score.setText(score);
		}
	}

	JFrame f = new JFrame("Ranking Board");
	JPanel mainPanel = new JPanel();
	JPanel boardPanel = new JPanel();
	int rowcount;
	RankingRow message[];
	ArrayList<RankingRow> msgArray = new ArrayList<>();
	MysqlConnector mc = new MysqlConnector();

	RankingBoard() {
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
		boardPanel.setBackground(Color.black);

		// ask mysql to get ranking data
		mc.connectMysql(mc.selectRanking);
		String data = mc.getData();
		String[] ranking = data.split(";");
		rowcount = ranking.length;
		message = new RankingRow[rowcount];

		// save mysql data into collector msgArray
		for (int i = 0; i < rowcount; i++) {
			String[] rankcol = ranking[i].split(",");
			message[i] = new RankingRow();
			message[i].setText(i + 1, rankcol[0], rankcol[1]);
			msgArray.add(message[i]);
		}

		addComponentToBoardPanel();

		JScrollPane scrollPane = new JScrollPane(mainPanel);
		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(boardPanel);
		mainPanel.setBackground(Color.black);

		f.getContentPane().add(scrollPane);
		f.setSize(new Dimension(380, 400));
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
	private void addComponentToBoardPanel() {
		for (int i = 0; i < rowcount; i++) {
			boardPanel.add(msgArray.get(i));
		}
	}

	public static void main(String[] args) {
		new RankingBoard();
	}

}
