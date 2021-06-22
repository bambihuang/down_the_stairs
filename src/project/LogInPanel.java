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
	private char[] pw;
	private String acctest = "Edentest";
	private ArrayList<String> accArray = new ArrayList<String>();//{"Amy","Ben","Mark","Grace","Eden"});
	private String[] acclist = {"Amy","Ben","Mark","Grace","Eden"};
	private String playerinfo = "eden,amy,ben";
	private char[] correctpw = {'1','2','3','4','5','6','7','8'};
	private JFrame optionFrame = new JFrame();
	
	LogInPanel() {
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
		//passwordField.setEchoChar('o');
		passwordField.setActionCommand("");
		// passwordField.addActionListener(this);
		this.add(passwordField);

		ImageIcon imageIcon = new ImageIcon("img/login.png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(62, 62, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg); // transform it back

		login = new JButton(imageIcon);
		login.setBounds(429, 270, 62, 62);
		login.addActionListener(this);
		login.setBorderPainted(false);// 去邊框
		login.setContentAreaFilled(false);// 去底色
		this.add(login);

		signup = new JButton(new ImageIcon("img/signup.png"));
		signup.setBounds(109, 270, 313, 62);
		signup.addActionListener(this);
		signup.setBorderPainted(false);
		signup.setContentAreaFilled(false);
		this.add(signup);

		guestLogin = new JButton(new ImageIcon("img/as_guest.png"));
		guestLogin.setBounds(143, 380, 313, 62);
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
		acc = accountArea.getText();
		pw  = passwordField.getPassword();

		// TODO:search acc is exist or not in mysql
		Boolean isAccountExist = false;
		String[] info = playerinfo.split(",");
		for (String name : info) {
			if (acc.equals(name)) {
				isAccountExist = true;
			}
			break;
		}
		
		if (isAccountExist) {
			if (checkPassword()) {
				// TODO:save acc info into player system
				switchToMenu();
			} else {
				JOptionPane.showMessageDialog(optionFrame,
						"Password fail !","",JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(optionFrame,
					"Account not exist !","",JOptionPane.WARNING_MESSAGE);
		}
	}

	private void signup() {
		acc = accountArea.getText();
		pw  = passwordField.getPassword();
		
		// TODO:search acc is exist or not in mysql
		Boolean isAccountExist = false;
		String[] info = playerinfo.split(",");
		for (String name : info) {
			if (acc.equals(name)) {
				isAccountExist = true;
			}
			break;
		}
		
		if (isAccountExist) {
			JOptionPane.showMessageDialog(optionFrame,
					"Account exist !","",JOptionPane.WARNING_MESSAGE);
		} else {
			Boolean isLegal = false;
			if (pw.length >= 6) isLegal = true;
			if (isLegal) {
				// TODO:insert acc and pw into mysql
				// TODO:save acc and pw into player system
				Arrays.fill(pw, '0');
				switchToMenu();
			} else {
				JOptionPane.showMessageDialog(optionFrame,
						"Password must contain at least 6 characters","",JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	// 以遊客身份進入遊戲
	private void guestLogin() {
		// TODO:set a guest acc into player system
		switchToMenu();
	}

	// 前往遊戲選單
	private void switchToMenu() {
		new MainFrame(2);
	}
	
	private boolean checkPassword() {
		pw = passwordField.getPassword();
		
		// TODO:get pw in mysql
		char[] correct = { 'b', 'u', 'g', 'a', 'b', 'o', 'o' };

		boolean isCorrect = isPasswordCorrect(pw,correct);
		// Zero out the possible password, for security.
		Arrays.fill(pw, '0');
	    Arrays.fill(correct,'0');

		passwordField.selectAll();
		return isCorrect;
	}
	
	private static boolean isPasswordCorrect(char[] input, char[] correctPassword) {
	    boolean isCorrect = false;

	    if (input.length != correctPassword.length) {
	        isCorrect = false;
	    } else {
	        isCorrect = Arrays.equals (input, correctPassword);
	    }
	    
	    return isCorrect;
	}

}