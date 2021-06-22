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
	    	content.setFont(new Font("Calibri", Font.BOLD, 16));
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
	
	String player = "player1";
    String newMsg = "";
    String timestamp = "";
	JFrame f = new JFrame("Message Board");
	JPanel mainPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel writePanel = new JPanel();
    BoxLayout box;
    JTextArea writeMsg = new JTextArea();
	JButton send;
	int count = 0;
	Message message[] = new Message[count];
	Border border = BorderFactory.createLineBorder(Color.gray);
	ArrayList<Message> msgArray = new ArrayList<>();
	MysqlConnector mc = new MysqlConnector();
	
	MessageBoard() {
		createAndShowGUI();
	}
	
	private void createAndShowGUI() {
		box = new BoxLayout(writePanel, BoxLayout.X_AXIS);
		writePanel.setLayout(box);
		writePanel.setBackground(Color.black);

		writeMsg.setLineWrap(true); // ´«¦æ
		writeMsg.setFont(new Font("Pixelony", Font.TRUETYPE_FONT, 16));
		writeMsg.setForeground(Color.white);
		writeMsg.setBackground(Color.black);
		
		writeMsg.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createEmptyBorder(0, 2, 0, 2),border));
		writePanel.add(writeMsg);

		ImageIcon imageIcon = new ImageIcon("img/send.png"); // load the image to a imageIcon
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
        
        
        for (int i=0 ; i < count ; i++) {
        	message[i] = new Message(i+1);
        	msgArray.add(0, message[i]);
        }
        
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
		mc.connectMysql(mc.selectAllMessage);
	}
	
	private String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    Date date = new Date();  
	    return formatter.format(date);
	}
	
	private void addComponentToBoardPanel() {
		boardPanel.add(writePanel);
        
        for (int i=0 ; i < count ; i++) {
        	boardPanel.add(msgArray.get(i));
        }
	}
	
	
	private void writeNewMsg() {
		//mc.player = player;
		//mc.message = newMsg;
		//mc.timestamp = timestamp;
		if (!writeMsg.getText().equals("")) {
			count++;
			Message newMsg = new Message(count);
			newMsg.setText(writeMsg.getText(), "player1", getCurrentTime());
			writeMsg.setText(null);
			msgArray.add(0, newMsg);
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
		new MessageBoard();
	}

}
