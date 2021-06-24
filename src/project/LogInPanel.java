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
 * ���U�n�J����
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
		login.setBorderPainted(false);// �h���
		login.setContentAreaFilled(false);// �h����
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
		
		if (!acc.equals("")) {// ���T�{�O�_����J�b��
			user.setName(acc);
	        user.setPassword(new String(passwordField.getPassword()));
			User loginuser = jdbc2.login(user);
			if (loginuser.getName() != null) {// �T�{��Ʈw�����b��
				if (user.getPassword().equals(loginuser.getPassword())) {// �T�{�K�X
					user=loginuser;
					switchToMenu(user);
				} else {
					JOptionPane.showMessageDialog(null, "�K�X���~�A�Э��s�T�{�I", "�K�X���~", JOptionPane.PLAIN_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "�L���b���A�Х����U�I", "�b�����~", JOptionPane.PLAIN_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "�п�J�b���I", "�b�����~", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void signup() {
		JDBC_test2 jdbc = new JDBC_test2();
		acc = accountArea.getText();
		
		if (!acc.equals("")) {//�T�w�O�_��J�b��
			user.setName(acc);
	        user.setPassword(new String(passwordField.getPassword()));
			
			if (!jdbc.isExist(user)) {//���{�T��Ʈw�L���b��
				if (!(passwordField.getPassword().length == 0)) {//�T�w�O�_��J�K�X	
					if (jdbc.isAdd(user)) {
						if (JOptionPane.showConfirmDialog(null, "���U���\�A���T�{�i�J�����I", "���U���\",JOptionPane.WARNING_MESSAGE) == 0) {
							switchToMenu(user);
						}
					} else {
						JOptionPane.showMessageDialog(null, "�A�J��F�u�{�v�]�L�Ѫ����D�A�Э��s��J", "�K�X���~",JOptionPane.PLAIN_MESSAGE);
					}	
				} else {
					JOptionPane.showMessageDialog(null, "�п�J�K�X�I", "�K�X���~",JOptionPane.PLAIN_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "�b���w���ơA�Э��s��J�I", "�b�����~",JOptionPane.PLAIN_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "�п�J�b���I", "�b�����~",JOptionPane.PLAIN_MESSAGE);
		}
	}

	// �H�C�Ȩ����i�J�C��
	private void guestLogin() {
		user.setName("Guest");
		
		switchToMenu(user);
	}

	// �e���C�����
	private void switchToMenu(User user) {
		accountArea.setText("");
		passwordField.setText("");
		
		new MainFrame(2, user);
	}
	
}