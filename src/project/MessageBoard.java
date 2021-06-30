package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class MessageBoard {
	
	private class Message extends JPanel {
	    private JTextArea content = new JTextArea("Welcome to message board!");
		private JLabel author     = new JLabel("Admin", JLabel.RIGHT);
		private JLabel date       = new JLabel("Time", JLabel.RIGHT);

	    Message(int n) {
	    	content.setLineWrap(true); // ´«¦æ
	    	content.setFont(new Font("NotoSansCJK", Font.BOLD, 16));
	    	content.setForeground(Color.white);
	    	content.setBackground(Color.black);
	    	content.setEditable(false);
	    	author.setMinimumSize(new Dimension(300,18));
	    	author.setMaximumSize(new Dimension(300,18));
	    	author.setFont(new Font("Calibri", Font.BOLD, 18));
			author.setForeground(Color.white);
	    	date.setMinimumSize(new Dimension(300,18));
	    	date.setMaximumSize(new Dimension(300,18));
	    	date.setFont(new Font("Calibri", Font.BOLD, 16));
	    	date.setForeground(Color.white);
	        this.setBorder(BorderFactory.createTitledBorder(
	        		null, "#" + n, TitledBorder.LEFT, TitledBorder.TOP,
	        		new Font("Calibri",Font.BOLD,12), Color.white));
	        GroupLayout l = new GroupLayout(this);          
	        this.setLayout(l);
	        this.setBackground(Color.black);
	        l.setAutoCreateGaps(true);
	        l.setAutoCreateContainerGaps(true);
	        l.setHorizontalGroup(l.createSequentialGroup()
	            .addGroup(l.createParallelGroup(GroupLayout.Alignment.CENTER)
	                .addComponent(content)
		            .addComponent(author)
	                .addComponent(date))
	        );

	        l.setVerticalGroup(l.createSequentialGroup()
	            .addGroup(l.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(content))
	            .addGroup(l.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(author))
	            .addGroup(l.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(date))
	        );
	    }
	    
	    void setText(String text) {
	    	content.setText(text);
	    }
	    
	    void setText(String content, String author, String date) {
	    	this.content.setText(content);
	    	this.author.setText(author);
	    	this.date.setText(date);
	    }
	}
	
	JFrame f = new JFrame("Message Board");
	JPanel mainPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel writePanel = new JPanel();
    BoxLayout box;
    JTextArea writeMsg = new JTextArea();
	JButton send;
	int rowcount = 0;
	Message message[];
	Border border = BorderFactory.createLineBorder(Color.gray);
	ArrayList<Message> msgArray = new ArrayList<>();
	private JDBC_test2 jdbc = new JDBC_test2();
	User user;
	
	MessageBoard(User user) {
		this.user = user;
		createAndShowGUI();
	}
	
	private void createAndShowGUI() {
		box = new BoxLayout(writePanel, BoxLayout.X_AXIS);
		writePanel.setLayout(box);
		writePanel.setBackground(Color.black);

		writeMsg.setLineWrap(true); // ´«¦æ
		writeMsg.setFont(new Font("NotoSansCJK", Font.TRUETYPE_FONT, 16));
		writeMsg.setForeground(Color.white);
		writeMsg.setBackground(Color.black);
		
		writeMsg.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createEmptyBorder(0, 2, 0, 2),border));
		writePanel.add(writeMsg);

		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/img/send.png")); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(100, 42,  java.awt.Image.SCALE_DEFAULT); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back

        send = new JButton(imageIcon);
        send.setSize(new Dimension(100, 42));
        send.addActionListener(e -> writeNewMsg());
        send.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createEmptyBorder(0, 0, 0, 0),null));
        send.setBorderPainted(false);
        send.setContentAreaFilled(false);
        writePanel.add(send);
        
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
        boardPanel.setBackground(Color.black);
        
        
        
        addComponentToBoardPanel();

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(boardPanel);
        mainPanel.setBackground(Color.black);
        
        f.getContentPane().add(scrollPane);
        f.setSize(new Dimension(380,400));
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
	
	private void getTableData() {
		msgArray.clear();
		String data = jdbc.getAllMessage();
        String[] messages = data.split(";");
		rowcount = messages.length;
        message = new Message[rowcount];
        for (int i=0 ; i < rowcount ; i++) {
        	String[] msgrow = messages[i].split(",");
        	message[i] = new Message(i+1);
        	message[i].setText(msgrow[0], msgrow[1], msgrow[2]);
        	msgArray.add(0, message[i]);
        }
	}
	
	private String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    Date date = new Date();  
	    return formatter.format(date);
	}
	
	private void addComponentToBoardPanel() {
		getTableData();
		boardPanel.add(writePanel);
        
        for (int i=0 ; i < rowcount ; i++) {
        	boardPanel.add(msgArray.get(i));
        }
	}
	
	
	private void writeNewMsg() {
		if (!writeMsg.getText().equals("")) {
			rowcount++;
			jdbc.addNewMessage(rowcount, user, writeMsg.getText(), getCurrentTime());
			writeMsg.setText(null);
	        refresh();
		}
	}
	
	private void refresh() {
		boardPanel.removeAll();
		addComponentToBoardPanel();
        boardPanel.revalidate();
        boardPanel.repaint();
	}
	
	public static void main(String[] args) {
		User user = new User();
		new MessageBoard(user);
	}
}
