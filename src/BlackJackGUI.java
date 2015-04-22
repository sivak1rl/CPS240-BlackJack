import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Checkbox;


public class BlackJackGUI {

	private JFrame frame;
	public boolean wantHit=false;
	public JLabel jlbBet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlackJackGUI window = new BlackJackGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BlackJackGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GREEN);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		JPanel leftpane= new JPanel();
		leftpane.setLayout(new BoxLayout(leftpane,BoxLayout.PAGE_AXIS));
		frame.getContentPane().add(leftpane,BorderLayout.WEST);
		JPanel botpane= new JPanel();
		botpane.setLayout(new FlowLayout());
		frame.getContentPane().add(botpane,BorderLayout.SOUTH);
		
		Label label_1 = new Label("Cards");
		Label label_2= new Label("Card2");
		Label label_3= new Label("Card3");
		//label_1.setAlignment(Label.CENTER);
		botpane.add(label_1);
		botpane.add(label_2);
		botpane.add(label_3);
		JButton button_1 = new JButton("+");
		
		 jlbBet= new JLabel("0");
		button_1.setBackground(Color.CYAN);
		leftpane.add(jlbBet);
		leftpane.add(button_1);
		
		JButton button = new JButton("   Hit   ");
		button.setBackground(Color.ORANGE);
		leftpane.add(button);
		
		JButton button_2 = new JButton("   Stand   ");
		button_2.setBackground(   Color.RED   );
		leftpane.add(button_2);
		ActionListener btnListener = new BlackJackListener(button_1, button, button_2,jlbBet,wantHit);
		button_1.addActionListener(btnListener);
		button_2.addActionListener(btnListener);
		button.addActionListener(btnListener);
		Label label = new Label("BlackJack");
		Label labelOUT = new Label("message");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Aharoni", Font.BOLD, 27));
		labelOUT.setForeground(Color.DARK_GRAY);
		labelOUT.setAlignment(Label.CENTER);
		labelOUT.setFont(new Font("Aharoni", Font.BOLD, 15));
		labelOUT.setForeground(Color.DARK_GRAY);
		frame.getContentPane().add(label, BorderLayout.NORTH);
		//frame.getContentPane().add(labelOUT, BorderLayout.NORTH);

	}
	

}