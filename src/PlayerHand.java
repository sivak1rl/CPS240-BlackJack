import java.util.ArrayList;
import java.util.List;

public class PlayerHand {
	List<Card> pHand;
	private int score;
	
	public int getScore() {
		return score;
	}

	private boolean hasDoubles;
	public int handSize=0;
	
	public PlayerHand() {
		pHand = new ArrayList<Card>();
	}

	//I'm a lazy dealer
	//Calls the method directly below it.
	public void Deal(List<Card> deck) {
		Deal(deck.remove(0), deck.remove(0));
		
	}
	
	public void Deal(Card c1, Card c2) {
		pHand.clear();
		pHand.add(c1);
		score += c1.getScore();
		pHand.add(c2);
		score += c2.getScore();
		
		if(c1.getValue() == c2.getValue()) {
			hasDoubles = true;
		} else {
			hasDoubles = false;
		}
		
		this.handSize+=2; //keeps track of hand size for check duplicates
		if (score > 21 && c1.ace) {
			score -= 10;
			c1.ace = false;
		} else if (score > 21 && c2.ace) {
			score -= 10;
			c2.ace = false;
		}
		
	}
	
	public int getHandSize(){
		//maybe need this 
		return this.handSize;
		
	}
	
	public boolean checkDuplicates(){
		
		for(int x =0; x<this.handSize-1;x++){
			for(int y=x+1;y<this.handSize;y++){
				if(this.pHand.get(x).compareTo(this.pHand.get(y))){
					return true;
				}
			}
		}
		return false;
		
	}
	public Card getDuplicateCard(){
		Card doopCard= this.pHand.get(this.handSize-1);
		this.pHand.remove(handSize-1);
		return doopCard;
	}
	
	//Laze will consume me.
	public void Hit(List<Card> deck) {
		Hit(deck.remove(0));
	}
	public void Hit(Card c) {
		pHand.add(c);

		score += c.getScore();
		this.handSize+=1;
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

	/**
	 * @return the hasDoubles
	 */
	public boolean getHasDoubles() {
		return hasDoubles;
	}

	

}
