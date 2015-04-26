import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlackJackGUI extends JFrame {

	public BlackJackGUI(PlayerHand hand, List<Card> deck, DealerHand dealer) {
		// Create components and panels
		JPanel pnlWest = new JPanel();

		// WEST side stories
		JLabel lblTotalBet = new JLabel("Bet: " + hand.getBet());
		lblTotalBet.setFont(new Font("Sans-Serif", Font.BOLD, 20));
		JButton btnBet = new JButton("Bet more");
		JButton btnHit = new JButton("Hit");
		JButton btnStand = new JButton("Stand");

		pnlWest.add(lblTotalBet);
		pnlWest.add(btnBet);
		pnlWest.add(btnStand);
		pnlWest.add(btnHit);

		pnlWest.setBackground(Color.green);
		pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));

		// North
		JPanel pnlNorth = new JPanel(new FlowLayout());
		JLabel lblDealer = new JLabel(dealer.toString());

		lblDealer.setFont(new Font("Sans-Serif", Font.BOLD, 16));

		pnlNorth.add(lblDealer);

		pnlNorth.setBackground(Color.green);
		pnlNorth.setAlignmentX(CENTER_ALIGNMENT);

		// South
		JPanel pnlSouth = new JPanel(new FlowLayout());

		pnlSouth.setBackground(Color.green);
		pnlSouth.setAlignmentX(CENTER_ALIGNMENT);

		JLabel lblCards = new JLabel(hand.toString());
		lblCards.setFont(new Font("Sans-Serif", Font.BOLD, 16));

		pnlSouth.add(lblCards, null);

		// East
		JPanel pnlEast = new JPanel();

		JLabel lblChipCount = new JLabel("Chips: " + hand.getChipCount());
		lblChipCount.setFont(new Font("Sans-Serif", Font.BOLD, 20));

		pnlEast.add(lblChipCount);

		pnlEast.setLayout(new BoxLayout(pnlEast, BoxLayout.Y_AXIS));
		pnlEast.setBackground(Color.green);

		// Panel
		JPanel panel = new JPanel(new BorderLayout());

		// Add Components to panel
		panel.add(pnlEast, BorderLayout.EAST);
		panel.add(pnlWest, BorderLayout.WEST);
		panel.add(pnlNorth, BorderLayout.NORTH);
		panel.add(pnlSouth, BorderLayout.SOUTH);

		panel.setBackground(Color.green);

		// Action Listeners
		btnBet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				hand.Bet();
				lblTotalBet.setText("Bet: " + hand.getBet());
				lblChipCount.setText("Chips: " + hand.getChipCount());
			}
		});

		btnHit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				btnBet.setEnabled(false);
				hand.Hit(deck);
				lblCards.setText(hand.toString());
			}
		});

		btnStand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				dealer.setGameDone(true);
				btnHit.setEnabled(false);
				while (dealer.getScore() < 17) {
					dealer.Hit(deck);
				}
				lblDealer.setText(dealer.toString());
			}
		});

		// Add panel to frame
		add(panel);

		// Set important frame settings
		setTitle("CPS 240 - BlackJack");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(966, 723);
		setBackground(Color.green);
		setLocation(200, 0);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		PlayerHand hand = new PlayerHand();
		BlackJackGame b = new BlackJackGame();
		List<Card> deck = new ArrayList<Card>();
		for (double d : b.deck) {
			deck.add(new Card(d));
		}

		hand.Deal(deck);
		DealerHand dealer = new DealerHand();
		dealer.Deal(deck);
		BlackJackGUI bjg = new BlackJackGUI(hand, deck, dealer);
	}
}
