import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
	
public class BlackJackListener implements ActionListener {
	PlayerHand hand;
	JLabel lblTotalBet;
	JLabel lblChipCount;
	JButton btnBet;
	JLabel lblCards;
	JButton btnHit;
	JButton btnStand;
	JButton btnPlayAgain;
	DealerHand dealer;
	List<Card> deck;
	JLabel lblDealer;
	
	public BlackJackListener(PlayerHand hand, JLabel lblTotalBet, JLabel lblChipCount,
			JButton btnBet, JLabel lblCards, JButton btnHit, JButton btnStand, 
			JButton btnPlayAgain, DealerHand dealer, List<Card> deck,JLabel lblDealer) {
		this.btnBet=btnBet;
		this.hand=hand;
		this.lblTotalBet=lblTotalBet;
		this.lblChipCount=lblChipCount;
		this.lblCards =lblCards;
		this.btnHit = btnHit;
		this.btnStand= btnStand;
		this.btnPlayAgain = btnPlayAgain;
		this.dealer = dealer;
		this.deck= deck;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnBet){
			hand.Bet();
			lblTotalBet.setText("Bet: " + hand.getBet());
			lblChipCount.setText("Chips: " + hand.getChipCount());
		}
		if(arg0.getSource()==btnHit){
			btnBet.setEnabled(false);
			hand.Hit(deck);
			lblCards.setText(hand.toString());
			if (hand.getScore() > 21) {
				btnHit.setEnabled(false);
				btnStand.setEnabled(false);
				btnPlayAgain.setEnabled(true);
			}
		}
		if(arg0.getSource()==btnStand){
			dealer.setGameDone(true);
			btnHit.setEnabled(false);
			btnStand.setEnabled(false);
			btnBet.setEnabled(false);
			while (dealer.getScore() < 17) {
				dealer.Hit(deck);
			}
			lblDealer.setText(dealer.toString());
			btnPlayAgain.setEnabled(true);
		}
		if(arg0.getSource()==btnPlayAgain){
			dealer.setGameDone(false);
			btnHit.setEnabled(true);
			deck = initDeck(new BlackJackGame());
			//List<Card> deck = new ArrayList<Card>();
			
			
		}
	}
	
	public static List<Card> initDeck(BlackJackGame b) {
		List<Card> deck = new ArrayList<Card>();
		for (double d : b.deck) {
			deck.add(new Card(d));
		}
		return deck;
	}
}