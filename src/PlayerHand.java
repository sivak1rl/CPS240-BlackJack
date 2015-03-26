import java.util.ArrayList;
import java.util.List;

public class PlayerHand {
	List<Card> pHand;
	public int score;

	public PlayerHand() {
		pHand = new ArrayList<Card>();
	}

	public void Deal(Card c1, Card c2) {
		pHand.clear();
		pHand.add(c1);
		score += c1.getScore();
		pHand.add(c2);
		score += c2.getScore();
		if (score > 21 && c1.ace) {
			score -= 10;
			c1.ace = false;
		} else if (score > 21 && c2.ace) {
			score -= 10;
			c2.ace = false;
		}
	}

	public void Hit(Card c) {
		pHand.add(c);

		score += c.getScore();

		if (score > 21) {
			for (Card d : pHand) {
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

	@Override
	public String toString() {
		String str = "";

		for (Card c : pHand) {
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
	}

}
