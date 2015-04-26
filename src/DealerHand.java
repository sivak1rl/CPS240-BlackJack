import java.util.ArrayList;
import java.util.List;

public class DealerHand {
	private List<Card> dHand;
	private int score;
	private boolean gameDone;

	public DealerHand() {
		dHand = new ArrayList<Card>();
		gameDone = false;
	}

	public boolean getGameDone() {
		return this.gameDone;
	}

	public void setGameDone(boolean gameDone) {
		this.gameDone = gameDone;
	}

	// Again I'm a lazy dealer
	public void Deal(List<Card> deck) {
		Deal(deck.remove(0), deck.remove(0));

	}

	public void Deal(Card c1, Card c2) {

		dHand.clear();
		dHand.add(c1);
		score += c1.getScore();
		dHand.add(c2);
		score += c2.getScore();
		if (score > 21 && c1.ace) {
			score -= 10;
			c1.ace = false;
		} else if (score > 21 && c2.ace) {
			score -= 10;
			c2.ace = false;
		}

	}

	// Laze will consume me.
	public void Hit(List<Card> deck) {
		Hit(deck.remove(0));
	}

	public void Hit(Card c) {
		dHand.add(c);

		score += c.getScore();

		if (score > 21) {
			for (Card d : dHand) {
				if (d.ace) {
					score -= 10;
					d.ace = false;
					if (score <= 21) {
						break;
					}
				}
			}
		}
	}

	public Card getUpCard() {
		return dHand.get(0);
	}

	public int getScore() {
		return this.score;
	}

	public String toString() {
		if (gameDone) {
			String str = "The dealer has: ";

			for (Card c : dHand) {
				str += c + ", ";
			}
			str = str.substring(0, str.length() - 2);
			if (score < 21) {
				return str + " \nScore: " + score;
			} else if (score == 21) {
				return str + "\nBlackJack!";
			} else {
				return str + "\nBust...";
			}
		} else {
			String str = "The dealer has ";
			str += getUpCard() + " up.";
			return str;
		}
	}
}