import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Window extends JFrame {

	public Window() {
		setLayout(new BorderLayout());

		JButton btnOK = new JButton("OK");
		JLabel lblTitle = new JLabel("Welcome to CMU!", JLabel.CENTER);
		JButton btnOK2 = new JButton("►");
		JButton btnOK3 = new JButton("◄");
		ImageIcon imgIcon = new ImageIcon("\\CardTable.png");

		add(btnOK, BorderLayout.SOUTH);
		add(lblTitle, BorderLayout.NORTH);
		add(btnOK2, BorderLayout.EAST);
		add(btnOK3, BorderLayout.WEST);
		

		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Window();

	}

}
