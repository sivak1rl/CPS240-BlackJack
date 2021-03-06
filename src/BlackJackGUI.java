import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
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
		Collections.shuffle(this.deck);
	}

	public static void main(String[] args) {
		BlackJackGUI bjg = new BlackJackGUI(new PlayerHand(),
				initDeck(new BlackJackGame()), new DealerHand());

		// Create components and panels
		JPanel pnlWest = new JPanel();

		// WEST side stories
		JLabel lblTotalBet = new JLabel("Bet: " + bjg.hand.getBet());
		lblTotalBet.setFont(new Font("Sans-Serif", Font.BOLD, 20));
		JButton btnBet = new JButton("Bet more");
		JButton btnHit = new JButton("Hit");
		JButton btnStand = new JButton("Stand");
		JButton btnDeal = new JButton("Deal");
		btnDeal.setEnabled(false);
		btnHit.setEnabled(false);
		btnStand.setEnabled(false);

		pnlWest.add(btnDeal);
		pnlWest.add(lblTotalBet);
		pnlWest.add(btnBet);
		pnlWest.add(btnStand);
		pnlWest.add(btnHit);

		pnlWest.setBackground(Color.green);
		pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));

		// North
		JPanel pnlNorth = new JPanel(new FlowLayout());
		JLabel lblDealer = new JLabel();
		lblDealer.setText("Place your bet.");

		lblDealer.setFont(new Font("Sans-Serif", Font.BOLD, 16));

		pnlNorth.add(lblDealer);

		pnlNorth.setBackground(Color.green);
		pnlNorth.setAlignmentX(CENTER_ALIGNMENT);

		// South
		JPanel pnlSouth = new JPanel(new FlowLayout());

		pnlSouth.setBackground(Color.green);
		pnlSouth.setAlignmentX(CENTER_ALIGNMENT);
		pnlSouth.setLayout(new FlowLayout());

		// East
		JPanel pnlEast = new JPanel();

		JLabel lblChipCount = new JLabel("Chips: " + bjg.hand.getChipCount());
		lblChipCount.setFont(new Font("Sans-Serif", Font.BOLD, 20));

		JButton btnPlayAgain = new JButton("Play Again");
		JLabel lblWinLose = new JLabel();
		lblWinLose.setFont(new Font("Sans-Serif", Font.BOLD, 16));
		btnPlayAgain.setEnabled(false);

		pnlEast.add(lblWinLose);
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
		btnDeal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				bjg.hand.Deal(bjg.deck);
				bjg.dealer.Deal(bjg.deck);
				btnDeal.setEnabled(false);
				btnBet.setEnabled(false);
				btnHit.setEnabled(true);
				btnStand.setEnabled(true);
				lblDealer.setText("");
				Card c = bjg.dealer.getUpCard();
				pnlNorth.add(new JLabel(c.getImg()));
				pnlNorth.add(new JLabel(new ImageIcon("cards/b1fv.png")));
				for (Card d : bjg.hand.pHand) {
					pnlSouth.add(new JLabel(d.getImg()));
				}
				panel.revalidate();
				panel.repaint();
			}
		});

		btnBet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				bjg.hand.Bet();
				lblTotalBet.setText("Bet: " + bjg.hand.getBet());
				lblChipCount.setText("Chips: " + bjg.hand.getChipCount());
				lblDealer.setText("Increase your bet or deal the cards.");
				if (bjg.hand.getBet() > 0) {
					btnDeal.setEnabled(true);
				}
			}
		});

		btnHit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				btnBet.setEnabled(false);
				bjg.hand.Hit(bjg.deck);
				System.out.println(bjg.hand.getScore());
				if (bjg.hand.getScore() > 21) {
					bjg.hand.setBet(0);
					lblTotalBet.setText("Bet: " + bjg.hand.getBet());
					lblWinLose.setText("You bust!");
					btnHit.setEnabled(false);
					btnStand.setEnabled(false);
					btnPlayAgain.setEnabled(true);
				}
				pnlSouth.removeAll();
				for (Card c : bjg.hand.pHand) {
					pnlSouth.add(new JLabel(c.getImg()));
				}
				pnlSouth.revalidate();
				pnlSouth.repaint();
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
				pnlNorth.removeAll();

				for (Card c : bjg.dealer.dHand) {
					pnlNorth.add(new JLabel(c.getImg()));
				}
				pnlNorth.revalidate();
				pnlNorth.repaint();
				// lblDealer.setText(bjg.dealer.toString());
				btnPlayAgain.setEnabled(true);
				if (bjg.hand.beatDealer(bjg.dealer)) {
					bjg.hand.WonBet();
					lblWinLose.setText("You beat the dealer!");
				} else {
					bjg.hand.setBet(0);
					lblWinLose.setText("You lost to the dealer!");
				}
				lblChipCount.setText("Chips: " + bjg.hand.getChipCount());
				lblTotalBet.setText("Bet: " + bjg.hand.getBet());
			}
		});

		btnPlayAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				bjg.dealer = new DealerHand();
				bjg.hand.pHand.clear();
				bjg.hand.setScore(0);
				bjg.hand.setBet(0);
				lblTotalBet.setText("Bet: " + bjg.hand.getBet());
				pnlNorth.removeAll();
				pnlNorth.revalidate();
				pnlNorth.repaint();
				pnlNorth.add(lblDealer);
				lblDealer.setText("Place your bet.");
				lblWinLose.setText("");
				bjg.deck = initDeck(new BlackJackGame());
				Collections.shuffle(bjg.deck);
				btnDeal.setEnabled(false);
				btnBet.setEnabled(true);
				btnPlayAgain.setEnabled(false);
				pnlSouth.removeAll();
			}
		});

		JPanel pnlCenter = new JPanel();
		pnlCenter.setLayout(new FlowLayout());
		pnlCenter.setAlignmentY(FlowLayout.CENTER);
		pnlCenter.setAlignmentX(FlowLayout.CENTER);
		pnlCenter.setBackground(Color.green);
		pnlCenter.add(new JLabel(new ImageIcon("cards/b1fv.png")));

		panel.add(pnlCenter, null);

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
		// File[] files = new File("cards/").listFiles();
		List<File> files = new ArrayList<File>();
		for (int i = 1; i < 53; i++) {
			files.add(new File("cards/" + i + ".png"));
		}
		files.add(new File("cards/b1fv.png"));
		for (double d : b.deck) {
			deck.add(new Card(d, files));
		}
		return deck;
	}
}
