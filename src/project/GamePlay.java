package project;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.io.*;

public class GamePlay extends JPanel implements ActionListener, KeyListener {
	private JLabel lblLives, lblLevel, lblRecord2, lblscore;
	private JButton btnPause, btnPlay, btnExit, btnClearRecord, btnrecord;
	private int level = 0, lives = 12, seconds = 0, bestLevel = 0, s = 0, platformPlayerIsOn = -1;
	private player p;
	//private JMenu menu;
	private boolean start = false, moveRight = false, moveLeft = false, pause = false;
	private Timer gameTimer, platformTimer;
	private Platform[] platforms;
	private Random rnd;
	private TopSpike ts;
	private BakGround background;
	private File highscores = new File("highscores.txt");
	private static String name = "";
	//private String line = "";
	private JFrame frame = new JFrame();
	Music2 music=new Music2();
	JDBC_test2 jdbc = new JDBC_test2();

	/*
	 * public static void main(String[] args) { new GamePlay(); }
	 */

	public GamePlay(User loginuser) {
		this.name = loginuser.getName();
		this.music.playSound();
		// lives
		lblLives = new JLabel();
		lblLives.setPreferredSize(new Dimension(140, 85));
		lblLives.setHorizontalAlignment(SwingConstants.CENTER);
		lblLives.setIcon(new ImageIcon("img/lives" + lives + ".png"));

		// level
		lblLevel = new JLabel();
		Font f = new Font("Pixelony", Font.PLAIN, 31);
		lblLevel.setFont(f);
		Color clevel = new Color(93, 184, 233);
		lblLevel.setForeground(clevel);
		lblLevel.setText("HIGH LEVEL");
		lblLevel.setPreferredSize(new Dimension(220, 40));
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblscore = new JLabel();
		Font f8 = new Font("Pixelony", Font.PLAIN, 28);
		lblscore.setFont(f8);
		lblscore.setForeground(Color.WHITE);
		lblscore.setText("" + level);
		lblscore.setHorizontalAlignment(SwingConstants.CENTER);

		// title
		// �Nlabel�m�����Ϥ��ӫD�������r
		Color c1 = new Color(122, 122, 122);
		JLabel lblTitle = new JLabel();
		lblTitle = new JLabel();
		lblTitle.setPreferredSize(new Dimension(240, 110));
		lblTitle.setIcon(new ImageIcon("img/title.png"));
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);

		Font f3 = new Font("Pixelony", Font.PLAIN, 20);

		String record = "img/record.png";
		Icon iconre = new ImageIcon(record);
		btnrecord = new JButton(iconre);
		btnrecord.setBorder(null);
		btnrecord.setContentAreaFilled(false);
		btnrecord.setFocusable(false);
		btnrecord.addActionListener(this);

		String clear = "img/clear.png";
		Icon iconcl = new ImageIcon(clear);
		btnClearRecord = new JButton(iconcl);
		btnClearRecord.setBorder(null);
		btnClearRecord.setContentAreaFilled(false);
		btnClearRecord.setFocusable(false);
		btnClearRecord.addActionListener(this);

		lblRecord2 = new JLabel();
		lblRecord2.setText("             0");
		lblRecord2.setFont(f3);
		lblRecord2.setForeground(Color.WHITE);
		lblRecord2.setPreferredSize(new Dimension(190, 25));
		lblRecord2.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblname = new JLabel();
		lblname.setFont(f3);
		lblname.setForeground(c1);
		lblname.setPreferredSize(new Dimension(190, 25));
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		bestLevel = loginuser.getScore();
		if(!name.equals("Guest")) {
			lblRecord2.setText("     " + loginuser.getScore() + " by " + name);
		}else {
			lblRecord2.setText("     " + 0 + " by Guest");
		}

		String pathHelp = "img/pause.png";
		Icon iconHelp = new ImageIcon(pathHelp);
		btnPause = new JButton(iconHelp);
		btnPause.setBorder(null);
		btnPause.setContentAreaFilled(false);
		btnPause.setFocusable(false);
		btnPause.addActionListener(this);

		String pathPlay = "img/goplay.png";
		btnPlay = new JButton(pathPlay);
		Icon iconPlay = new ImageIcon(pathPlay);
		btnPlay = new JButton(iconPlay);
		btnPlay.setBorder(null);
		btnPlay.setContentAreaFilled(false);
		btnPlay.setFocusable(false);
		btnPlay.addActionListener(this);

		String pathExit = "img/back.png";
		btnExit = new JButton(pathExit);
		Icon iconExit = new ImageIcon(pathExit);
		btnExit = new JButton(iconExit);
		btnExit.setBorder(null);
		btnExit.setContentAreaFilled(false);
		btnExit.setFocusable(false);
		btnExit.addActionListener(this);

		gameTimer = new Timer(1, this);
		rnd = new Random();
		ts = new TopSpike();
		background = new BakGround();

		// panels
		JPanel topmidpanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(topmidpanel, BoxLayout.Y_AXIS);
		topmidpanel.setLayout(boxLayout);
		topmidpanel.setBackground(Color.BLACK);
		lblLevel.setAlignmentX(CENTER_ALIGNMENT);
		lblscore.setAlignmentX(CENTER_ALIGNMENT);
		topmidpanel.add(lblLevel);
		topmidpanel.add(Box.createVerticalStrut(10));
		topmidpanel.add(lblscore);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20));
		topPanel.setBackground(Color.BLACK);
		topPanel.add(lblLives);
		topPanel.add(topmidpanel);
		topPanel.add(lblTitle);
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setBackground(Color.BLACK);
		rightPanel.add(Box.createVerticalStrut(138));
		rightPanel.add(btnrecord);
		rightPanel.add(Box.createVerticalStrut(10));
		rightPanel.add(lblRecord2);
		rightPanel.add(lblname);
		rightPanel.add(Box.createVerticalStrut(2));
		rightPanel.add(btnClearRecord);
		rightPanel.add(Box.createVerticalStrut(35));
		rightPanel.add(btnPause);
		rightPanel.add(Box.createVerticalStrut(10));
		rightPanel.add(btnPlay);
		rightPanel.add(Box.createVerticalStrut(10));
		rightPanel.add(btnExit);

		setBackground(Color.black);
		setFocusable(true);
		Color cframe = new Color(89, 186, 145);
		setBorder(BorderFactory.createLineBorder(cframe, 6));
		addKeyListener(this);
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(rightPanel, BorderLayout.EAST);
		frame.add(this, BorderLayout.CENTER);
		frame.setTitle("Down The Stairs");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(760, 709);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		background.draw(g2);
		ts.draw(g2);
		if (start) {
			p.draw(g2);
			for (int i = 0; i < platforms.length; i++) {
				platforms[i].draw(g2);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == btnPause) {
			if (start) {
				// press pause when game started
				if (!pause) {
					pause = true;
					gameTimer.stop();
					platformTimer.stop();
					JOptionPane.showMessageDialog(null, "Try to get as deep in the cave as possible."
							+ "\nTo continue, press the pause button.", "DownTheStairs",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					pause = false;
					gameTimer.start();
					platformTimer.start();
				}
			} else {
				// �C���|���}�l�ɫ��Upause��
				JOptionPane.showMessageDialog(null, "Use left and right arrow keys to control the player."
						+ "\n you can pause the game with pause button.", "Introduction",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (arg0.getSource() == btnClearRecord) {
			int option = JOptionPane.showConfirmDialog(null, "you sure you want to clear the record?", "ClearRecord",
					JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				// �M���ثe����
				if (highscores.delete()) {
					JOptionPane.showMessageDialog(null, "score deleted!", "ClearRecord", JOptionPane.INFORMATION_MESSAGE);

					bestLevel = 0;
					if(! name.equals("Guest")) {
						JDBC_test2 jdbc = new JDBC_test2();
						User usernew = new User();
						usernew.setName(name);
						usernew.setScore(bestLevel);
						jdbc.score(usernew);
						lblRecord2.setText("             0" + "by" + name);
					}else {
						lblRecord2.setText("             0" + "by Guest " );
					}
				} else {
					JOptionPane.showMessageDialog(null, "score still there!", "Regret", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (arg0.getSource() == btnPlay) {
			s = 0;
			moveRight = false;
			moveLeft = false;
			//btnPlay.setEnabled(false);
			start = true;
			p = new player();
			// ��l�Ʀa�j
			platforms = new Platform[7];
			for (int i = 0; i < platforms.length; i++) {
				int n = rnd.nextInt(5);
				if (n == 0) {
					platforms[i] = new NormalPlatform();
				} else if (n == 1) {
					platforms[i] = new SpikePlatform();
				} else if (n == 2) {
					int m = rnd.nextInt(2);
					if (m == 0) {
						platforms[i] = new NormalPlatform();
					} else {
						platforms[i] = new SpikePlatform();
					}
				} else if (n == 3) {
					int m = rnd.nextInt(2);
					if (m == 0) {
						platforms[i] = new NormalPlatform();
					} else {
						platforms[i] = new SpikePlatform();
					}
				} else if (n == 4) {
					platforms[i] = new TempPlatform();
				}

				platforms[i].setY(250 + i * 60);
			}
			// ���⪺�_�l��m
			platforms[3] = new NormalPlatform();
			platforms[3].setLocation(260, 225);
			gameTimer.restart();
			seconds = 0;
			level = 0;
			lives = 12;
			lblLives.setIcon(new ImageIcon("img/lives" + lives + ".png"));
			lblscore.setText("" + level);
			//menu.setEnabled(false);
			repaint();
		} else if (arg0.getSource() == btnExit) {
			int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit??", "DownTheStairs", JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				//System.exit(0);
				
			}
		}
		if (arg0.getSource() == gameTimer) {
			if (moveRight) {
				int x = p.getX();
				if (x < 555 - p.getWidth()) {
					x += 2;
					p.setX(x);
				}
			} else if (moveLeft) {
				int x = p.getX();
				if (x > 5) {
					x -= 2;
					p.setX(x);
				}
			}
			seconds++;
			if (seconds == 10) {
				platformTimer = new Timer(5, this);// �a�j�ܴ��W�v
				platformTimer.restart();
			}
			if (seconds % 500 == 0 && seconds != 0) {
				// �@�q�ɶ��W�[����
				level += 10;
				lblscore.setText("" + level);
			}
			// �����a�S�����b����a�j����Y��
			if (!(p.getRectBottom().intersects(platforms[0].getRect()))
					&& !(p.getRectBottom().intersects(platforms[1].getRect()))
					&& !(p.getRectBottom().intersects(platforms[2].getRect()))
					&& !(p.getRectBottom().intersects(platforms[3].getRect()))
					&& !(p.getRectBottom().intersects(platforms[4].getRect()))
					&& !(p.getRectBottom().intersects(platforms[5].getRect()))
					&& !(p.getRectBottom().intersects(platforms[6].getRect()))) {
				p.move(seconds);
			}
			// �����a���b�a�j�W�N�H�a�M���W����
			if (p.getRectBottom().intersects(platforms[0].getRect())
					|| p.getRectBottom().intersects(platforms[1].getRect())
					|| p.getRectBottom().intersects(platforms[2].getRect())
					|| p.getRectBottom().intersects(platforms[3].getRect())
					|| p.getRectBottom().intersects(platforms[4].getRect())
					|| p.getRectBottom().intersects(platforms[5].getRect())
					|| p.getRectBottom().intersects(platforms[6].getRect())) {
				if (p.getY() > 0) {
					p.moveOpposite(seconds);
				}
			}
		}
		if (arg0.getSource() == platformTimer) {
			for (int i = 0; i < platforms.length; i++) {
				platforms[i].move(seconds);
				checkCollision();
				// ���@�Ӧa�j�W�X�e���N�s�W�@�Ӧa�j
				if (platforms[i].getY() == 0 - platforms[i].getHeight()) {
					int n = rnd.nextInt(5);
					if (n == 0) {
						platforms[i] = new NormalPlatform();
					} else if (n == 1) {
						platforms[i] = new SpikePlatform();
					} else if (n == 2) {
						int m = rnd.nextInt(2);
						if (m == 0) {
							platforms[i] = new NormalPlatform();
						} else {
							platforms[i] = new SpikePlatform();
						}
					} else if (n == 3) {
						int m = rnd.nextInt(2);
						if (m == 0) {
							platforms[i] = new NormalPlatform();
						} else {
							platforms[i] = new TempPlatform();
						}
					} else if (n == 4) {
						platforms[i] = new TempPlatform();
					}
				}
			}
		}
		repaint();
	}

	@Override
	// ���䱱��KeyPressed�BKeyTyped�BKeyReleased
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (start) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT && !pause) {
				p.setDirection(player.WEST);
				moveLeft = true;
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !pause) {
				p.setDirection(player.EAST);
				moveRight = true;
			} else if (e.getKeyCode() == KeyEvent.VK_P) {
				// ���a��p �C���Ȱ�
				if (!pause) {
					pause = true;
					gameTimer.stop();
					platformTimer.stop();
				} else {
					pause = false;
					gameTimer.start();
					platformTimer.start();
				}
			}
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (start) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				moveLeft = false;
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				moveRight = false;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public void checkCollision() {
		// ���a����W�誺����y��
		if (p.getRectTop().intersects(ts.getRect())) {
			lives -= 2;
			lblLives.setIcon(new ImageIcon("img/lives" + lives + ".png"));
			if (lives <= 0) {
				lblLives.setIcon(new ImageIcon("img/lives0.png"));
				endGame();
			}
			p.setY(p.getY() + 40);
			btnPlay.setEnabled(true);
		}
		// ���a�I��a�j
		for (int i = 0; i < platforms.length; i++) {
			if (platforms[i].getRect().intersects(p.getRectBottom())) {
				if (platforms[i].getType().equals("normal") && lives < 12) {
					// ���a�C�I��@��normal�a�j�ҥi�[�^�@�w��
					if (platformPlayerIsOn != i) {
						lives++;
					}
					platformPlayerIsOn = i;
					lblLives.setIcon(new ImageIcon("img/lives" + lives + ".png"));
				} else if (platforms[i].getType().equals("spike")) {
					// ���a�I��spike�a������
					if (platformPlayerIsOn != i) {
						p.isInjured = true;
						s = seconds + 40;
						lives -= 2;
						lblLives.setIcon(new ImageIcon("img/lives" + lives + ".png"));
						if (lives <= 0) {
							lblLives.setIcon(new ImageIcon("img/lives0.png"));
							endGame();
						} // �妩���h�C������
						platformPlayerIsOn = i;
					}
					// �I��SPIKE���a�ϥܤ��������媬�A
					if (s - seconds < 10) {
						p.isInjured = false;
					}
				} else if (platforms[i].getType().equals("temp")) {
					if (platformPlayerIsOn != i) {
						s = seconds + 70;
						platformPlayerIsOn = i;
					}
					if (lives < 12) {
						if (platformPlayerIsOn != i) {
							lives++;
						}

						lblLives.setIcon(new ImageIcon("img/lives" + lives + ".png"));
					}
					// �a�������A���a�U�Y
					if (s - seconds < 10) {
						p.setY(p.getY() + 5);
						platforms[i].imgPlatform = new ImageIcon("");
					}
				}
			}
		}
		if (p.getY() >= 530 && p.getY() <= 550) {
			endGame();
		}
	}

	public void endGame() {
		start = false;
		p.setY(530);
		for (int j = 0; j < platforms.length; j++) {
			platforms[j].setY(550);
		}
		platformTimer.stop();
		gameTimer.stop();
		/*
		 * if (level != 0) { JOptionPane.showMessageDialog(null, "�A�o��F" + level + " ��!",
		 * "Your level", JOptionPane.INFORMATION_MESSAGE); }
		 */
		// ���a���}�̰�����
		if (level > bestLevel) {
			bestLevel = level;
			if(!name.equals("Guest")) {
				JDBC_test2 jdbc = new JDBC_test2();
				User usernew = new User();
				usernew.setName(name);
				usernew.setScore(bestLevel);
				jdbc.score(usernew);
				JOptionPane.showMessageDialog(null, "Congratulations! You have set a new record of "+ level +" levels!", "Congrates!",
						JOptionPane.INFORMATION_MESSAGE);

			// �g�J�̨αo���H�Ϊ��a�W��
				JOptionPane.showMessageDialog(null,
						"Data has been saved!", "Finished!", JOptionPane.INFORMATION_MESSAGE);
				lblRecord2.setText("     " + bestLevel + " by " + name);
			}else {
				JOptionPane.showMessageDialog(null, "You have set a new record of " + level + " levels!",
						  "Your level", JOptionPane.INFORMATION_MESSAGE);
				lblRecord2.setText("     " + bestLevel + " by Guest" );
			}
			btnPlay.setEnabled(true);
			//menu.setEnabled(false);
		}
	}
}