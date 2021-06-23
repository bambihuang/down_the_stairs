
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class register implements ActionListener {
	JFrame f;
	JLabel lblogo,lbname,lbpw;
	JPanel panel;
	JButton loginbt, registerbt;
	JTextField inputName;//�b��
	JPasswordField inputpd;//�K�X	

	register(){
		f=new JFrame("Signup");
		panel = new JPanel();
		
		//placeComponents(panel);
		panel.setLayout(null);
		panel.setBackground(Color.black);
		
		lblogo = new JLabel(); 
        lblogo.setBounds(245, 70, 240, 240);
        lblogo.setIcon(new ImageIcon("img/logo.png"));
        lblogo.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblogo);
        Color c = new Color(93, 184, 233);
        Font font = new Font("Pixelony", Font.PLAIN, 15);
		lbname=new JLabel("UserName:");
		lbname.setBounds(210, 400, 80, 25); 
		lbname.setFont(font);
		lbname.setForeground(c);
		panel.add(lbname);
		lbpw=new JLabel("PassWord:");
		lbpw.setBounds(210, 450, 80, 25);
		lbpw.setFont(font);
		lbpw.setForeground(c);
		panel.add(lbpw);
		
		
		inputName = new JTextField();		
		inputName.setBounds(340, 400, 160, 25);
		Font font2 = new Font("Dialog", Font.PLAIN, 15);
		inputName.setFont(font2);
		inputName.setForeground(Color.ORANGE);
		panel.add(inputName);
		inputpd = new JPasswordField();
		inputpd.setBounds(340, 450, 160, 25);
		inputpd.setFont(font2);
		inputpd.setForeground(Color.ORANGE);
		panel.add(inputpd);
		
				
		loginbt = new JButton("Log in");
		loginbt.setBounds(280, 500, 80, 25);
		panel.add(loginbt);
		loginbt.addActionListener(this);

		registerbt = new JButton("Sign up");
		registerbt.setBounds(420, 500, 80, 25);
		panel.add(registerbt);
		registerbt.addActionListener(this); 
		
		f.add(panel);
		f.setLocation(450,100);
		f.setSize(760,709);    
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}
	
	public void actionPerformed(ActionEvent e) {
		JDBC_test2 jdbc = new JDBC_test2();
		
		if(e.getSource()==registerbt){
			if(!inputName.getText().equals("")){//�T�w�O�_��J�b��
				User user = new User();
				user.setName(inputName.getText());
		        user.setPassword(new String(inputpd.getPassword()));
				
				if(!jdbc.isExist(user)){//���{�T��Ʈw�L���b��
					if(!(inputpd.getPassword().length==0)){//�T�w�O�_��J�K�X	
						
						
						if(jdbc.isAdd(user)) {
							if(JOptionPane.showConfirmDialog(null, "���U���\�A���T�{�^�n�J�����I", "���U���\",JOptionPane.WARNING_MESSAGE) == 0){
								f.dispose();
								new LogIn();
							}
						}else {
							JOptionPane.showMessageDialog(null, "�A�J��F�u�{�v�]�L�Ѫ����D�A�Э��s��J", "�K�X���~",JOptionPane.PLAIN_MESSAGE);
						}	
					}else{
						JOptionPane.showMessageDialog(null, "�п�J�K�X�I", "�K�X���~",JOptionPane.PLAIN_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "�b���w���ơA�Э��s��J�I", "�b�����~",JOptionPane.PLAIN_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null, "�п�J�b���I", "�b�����~",JOptionPane.PLAIN_MESSAGE);
			}			
		}if(e.getSource()==loginbt){
			JDBC_test2 jdbc2 = new JDBC_test2();
			User user = new User();
			user.setName(inputName.getText());
	        user.setPassword(new String(inputpd.getPassword()));
			User loginuser=jdbc2.login(user);
			if (loginuser!=null) {// ���T�{��Ʈw�����b��
				if (user.getPassword().equals(loginuser.getPassword())) {// �T�{�K�X
					f.dispose();
					new GamePlay(loginuser);
					// }
				}else {
					JOptionPane.showMessageDialog(null, "�K�X���~�A�Э��s�T�{�I", "�K�X���~", JOptionPane.PLAIN_MESSAGE);
				}
			}else if (inputName.getText().equals("")) {// �T�{�O�_����J�b��
				JOptionPane.showMessageDialog(null, "�п�J�b���I", "�b�����~", JOptionPane.PLAIN_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "�L���b���A�Х����U�I", "�b�����~", JOptionPane.PLAIN_MESSAGE);
			}
			
			//f.dispose();
			//new LogIn();
		}
	}
	
	public static void main(String[] args) {
		new register();
	}
		
}