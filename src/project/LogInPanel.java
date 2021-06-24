package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

/*
 * 註冊登入頁面
 */
public class LogInPanel extends JPanel implements ActionListener {
	private JLabel accountLabel, passwordLabel;
	private JTextField accountArea;
	private JPasswordField passwordField;
	private JButton login, signup, guestLogin;
	private String acc;
	private User user = new User();
	
	LogInPanel(User user) {
		this.user =user;
		accountLabel = new JLabel("ACCOUNT");
		accountLabel.setBounds(143, 120, 150, 30);
		accountLabel.setFont(new Font("Pixelony", Font.TRUETYPE_FONT, 25));
		accountLabel.setForeground(Color.white);
		this.add(accountLabel);

		passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setBounds(143, 180, 150, 30);
		passwordLabel.setFont(new Font("Pixelony", Font.TRUETYPE_FONT, 25));
		passwordLabel.setForeground(Color.white);
		this.add(passwordLabel);

		accountArea = new JTextField();
		accountArea.setBounds(300, 120, 156, 30);
		accountArea.setFont(new Font("Calibri", Font.TRUETYPE_FONT, 16));
		accountArea.setForeground(Color.white);
		accountArea.setBackground(Color.black);
		this.add(accountArea);

		passwordField = new JPasswordField();
		passwordField.setBounds(300, 180, 156, 30);
		passwordField.setFont(new Font("Calibri", Font.TRUETYPE_FONT, 16));
		passwordField.setForeground(Color.white);
		passwordField.setBackground(Color.black);
		passwordField.setActionCommand("");
		this.add(passwordField);

		login = new JButton(new ImageIcon("img/login.png"));
		login.setBounds(143, 240, 313, 62);
		login.addActionListener(this);
		login.setBorderPainted(false);// 去邊框
		login.setContentAreaFilled(false);// 去底色
		this.add(login);

		signup = new JButton(new ImageIcon("img/signup.png"));
		signup.setBounds(143, 320, 313, 62);
		signup.addActionListener(this);
		signup.setBorderPainted(false);
		signup.setContentAreaFilled(false);
		this.add(signup);

		guestLogin = new JButton(new ImageIcon("img/as_guest.png"));
		guestLogin.setBounds(143, 400, 313, 62);
		guestLogin.addActionListener(this);
		guestLogin.setBorderPainted(false);
		guestLogin.setContentAreaFilled(false);
		this.add(guestLogin);

		this.setSize(new Dimension(600, 600));
		this.setBackground(Color.black);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent evt) { 
		if (evt.getSource() == login)
			login();
		else if(evt.getSource() == signup)
			signup();
		else if (evt.getSource() == guestLogin)
			guestLogin();
	}
	
	private void login() {
		JDBC_test2 jdbc2 = new JDBC_test2();
		acc = accountArea.getText();
		
		if (!acc.equals("")) {// 先確認是否有輸入帳號
			user.setName(acc);
	        user.setPassword(new String(passwordField.getPassword()));
			User loginuser = jdbc2.login(user);
			if (loginuser.getName() != null) {// 確認資料庫有此帳號
				if (user.getPassword().equals(loginuser.getPassword())) {// 確認密碼
					user=loginuser;
					switchToMenu(user);
				} else {
					JOptionPane.showMessageDialog(null, "密碼錯誤，請重新確認！", "密碼錯誤", JOptionPane.PLAIN_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "無此帳號，請先註冊！", "帳號錯誤", JOptionPane.PLAIN_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "請輸入帳號！", "帳號錯誤", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void signup() {
		JDBC_test2 jdbc = new JDBC_test2();
		acc = accountArea.getText();
		
		if (!acc.equals("")) {//確定是否輸入帳號
			user.setName(acc);
	        user.setPassword(new String(passwordField.getPassword()));
			
			if (!jdbc.isExist(user)) {//先認確資料庫無此帳號
				if (!(passwordField.getPassword().length == 0)) {//確定是否輸入密碼	
					if (jdbc.isAdd(user)) {
						if (JOptionPane.showConfirmDialog(null, "註冊成功，按確認進入首頁！", "註冊成功",JOptionPane.WARNING_MESSAGE) == 0) {
							switchToMenu(user);
						}
					} else {
						JOptionPane.showMessageDialog(null, "你遇到了工程師也無解的問題，請重新輸入", "密碼錯誤",JOptionPane.PLAIN_MESSAGE);
					}	
				} else {
					JOptionPane.showMessageDialog(null, "請輸入密碼！", "密碼錯誤",JOptionPane.PLAIN_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "帳號已重複，請重新輸入！", "帳號錯誤",JOptionPane.PLAIN_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "請輸入帳號！", "帳號錯誤",JOptionPane.PLAIN_MESSAGE);
		}
	}

	// 以遊客身份進入遊戲
	private void guestLogin() {
		user.setName("Guest");
		
		switchToMenu(user);
	}

	// 前往遊戲選單
	private void switchToMenu(User user) {
		accountArea.setText("");
		passwordField.setText("");
		
		new MainFrame(2, user);
	}
	
}