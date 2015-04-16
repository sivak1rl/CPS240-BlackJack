import java.awt.EventQueue;

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
		botpane.setLayout(new CardLayout());
		frame.getContentPane().add(botpane,BorderLayout.SOUTH);
		
		Label label_1 = new Label("Cards");
		label_1.setAlignment(Label.CENTER);
		botpane.add(label_1, "name_716488195047554");
		
		Button button_1 = new Button("+");
		button_1.setBackground(Color.PINK);
		leftpane.add(button_1);
		
		Button button = new Button("   Hit   ");
		button.setBackground(Color.PINK);
		leftpane.add(button);
		
		Button button_2 = new Button("-");
		button_2.setBackground(Color.PINK);
		leftpane.add(button_2);
		
		Label label = new Label("BlackJack");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Aharoni", Font.BOLD, 27));
		label.setForeground(Color.DARK_GRAY);
		frame.getContentPane().add(label, BorderLayout.NORTH);
	}

}