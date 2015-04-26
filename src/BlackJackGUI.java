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

	List<Card> deck;
	DealerHand dealer;
	PlayerHand hand;

	public BlackJackGUI(PlayerHand hand, List<Card> deck, DealerHand dealer) {

		this.hand = hand;
		this.deck = deck;
		this.dealer = dealer;

	}

	public static void main(String[] args) {
		BlackJackGUI bjg = new BlackJackGUI(new PlayerHand(), initDeck(new BlackJackGame()), new DealerHand());
		bjg.hand.Deal(bjg.deck);
		bjg.dealer.Deal(bjg.deck);
		

		// Create components and panels
		JPanel pnlWest = new JPanel();

		// WEST side stories
		JLabel lblTotalBet = new JLabel("Bet: " + bjg.hand.getBet());
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
		JLabel lblDealer = new JLabel(bjg.dealer.toString());

		lblDealer.setFont(new Font("Sans-Serif", Font.BOLD, 16));

		pnlNorth.add(lblDealer);

		pnlNorth.setBackground(Color.green);
		pnlNorth.setAlignmentX(CENTER_ALIGNMENT);

		// South
		JPanel pnlSouth = new JPanel(new FlowLayout());

		pnlSouth.setBackground(Color.green);
		pnlSouth.setAlignmentX(CENTER_ALIGNMENT);

		JLabel lblCards = new JLabel(bjg.hand.toString());
		lblCards.setFont(new Font("Sans-Serif", Font.BOLD, 16));

		pnlSouth.add(lblCards, null);

		// East
		JPanel pnlEast = new JPanel();

		JLabel lblChipCount = new JLabel("Chips: " + bjg.hand.getChipCount());
		lblChipCount.setFont(new Font("Sans-Serif", Font.BOLD, 20));

		JButton btnPlayAgain = new JButton("Play Again");
		btnPlayAgain.setEnabled(false);

		pnlEast.add(lblChipCount);
		pnlEast.add(btnPlayAgain, null);

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
				bjg.hand.Bet();
				lblTotalBet.setText("Bet: " + bjg.hand.getBet());
				lblChipCount.setText("Chips: " + bjg.hand.getChipCount());
			}
		});

		btnHit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				btnBet.setEnabled(false);
				bjg.hand.Hit(bjg.deck);
				lblCards.setText(bjg.hand.toString());
				if (bjg.hand.getScore() > 21) {
					btnHit.setEnabled(false);
					btnStand.setEnabled(false);
					btnPlayAgain.setEnabled(true);
				}
			}
		});

		btnStand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				bjg.dealer.setGameDone(true);
				btnHit.setEnabled(false);
				btnStand.setEnabled(false);
				btnBet.setEnabled(false);
				while (bjg.dealer.getScore() < 17) {
					bjg.dealer.Hit(bjg.deck);
				}
				lblDealer.setText(bjg.dealer.toString());
				btnPlayAgain.setEnabled(true);
			}
		});

		btnPlayAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				bjg.dealer.setGameDone(false);
				btnHit.setEnabled(true);
				bjg.deck = initDeck(new BlackJackGame());
			}
		});

		// Add panel to frame
		bjg.add(panel);


		// Set important frame settings
		bjg.setTitle("CPS 240 - BlackJack");
		bjg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bjg.setSize(966, 723);
		bjg.setBackground(Color.green);
		bjg.setLocation(200, 0);
		bjg.setResizable(false);
		bjg.setVisible(true);
	}

	public static List<Card> initDeck(BlackJackGame b) {
		List<Card> deck = new ArrayList<Card>();
		for (double d : b.deck) {
			deck.add(new Card(d));
		}
		return deck;
	}
}
