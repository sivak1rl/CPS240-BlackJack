/**
 * Creates a card object based on a double. Cards have a value (1-13), a suit (a
 * string based on the double given), a score (equal to value except in special
 * blackjack cases)
 * 
 * @author Richard
 *
 */

public class Card {
	private int value;
	private String suit;
	private int score;
	protected boolean ace;

	public Card(double d) {
		value = (int) (d);
		if (d % (int) (d) < 0.25) {
			suit = "spade";
		} else if (d % (int) (d) < 0.5) {
			suit = "diamond";
		} else if (d % (int) (d) < 0.75) {
			suit = "club";
		} else {
			suit = "heart";
		}
		if (value > 10) {
			score = 10;
			ace = false;
		} else if (value == 1) {
			ace = true;
			score = 11;
		} else {
			score = value;
			ace = false;
		}
	}

	public void aceChange() {
		if (this.score == 11) {
			this.score = 1;
		}
	}

	@Override
	public String toString() {
		if (value == 1) {
			return String.format("Ace of %ss", this.suit);
		} else if (value == 11) {
			return String.format("Jack of %ss", this.suit);
		} else if (value == 12) {
			return String.format("Queen of %ss", this.suit);
		} else if (value == 13) {
			return String.format("King of %ss", this.suit);
		} else {
			return String.format("%d of %ss", this.value, this.suit);
		}
	}

	public int getScore() {
		return score;
	}

	protected void setScore(int score) {
		this.score = score;
	}
	
	public boolean hasAce() {
		return ace;
	}

	public boolean compareTo(Card card) {
		if(this.value==card.value){
			return true;
			}
		else
		return false;
	}
	public int getValue(){
		return this.value;
	}
	public String getSuit(){
		return this.suit;
	}
}
