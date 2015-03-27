import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BlackJackGame {

	double[] deck;

	// will this import
	// this one will be it
	// 7:48 attempt to commit
	public BlackJackGame() {
		deck = new double[52];
		int count = 0;
		for (int i = 1; i < 14; i++) {
			for (int j = 0; j < 4; j++) {
				deck[count] = i % 13 + j / 4.0;
				if (deck[count] < 1) {
					deck[count] += 13;
				}
				count++;
			}
		}
	}

	/**
	 * Creates a game of blackjack (an array of doubles) a list (deck) of cards
	 * (Card Object) based on BlackJackGame.deck and a player hand. deals to the
	 * player and asks for a hit.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<Card> cardDeck = new LinkedList<Card>();
		BlackJackGame bjg = new BlackJackGame();
		PlayerHand hand1 = new PlayerHand();
		DealerHand dHand = new DealerHand();
		for (double d : bjg.deck) {
			cardDeck.add(new Card(d));
		}
		Collections.shuffle(cardDeck);
		hand1.Deal(cardDeck.remove(0), cardDeck.remove(0));
		dHand.Deal(cardDeck.remove(0), cardDeck.remove(0));
		System.out.println(hand1);
		Scanner sc = new Scanner(System.in);
		System.out.println("The dealer's up card is " + dHand.getUpCard());
		String input;
		//
		System.out
				.print("Do you want a hit? (y for yes, anything else for no): ");
		input = sc.next();
		while (input.equals("y") && hand1.score <= 21) {
			hand1.Hit(cardDeck.remove(0));
			System.out.println(hand1);
			if (hand1.score <= 21) {
				System.out
						.print("Do you want a hit? (y for yes, anything else for no): ");
				input = sc.next();
			}
		}
		System.out.println("The dealer's score is " + dHand.getScore());
		while (dHand.getScore() < 17) {
			dHand.Hit(cardDeck.remove(0));
			System.out
					.println("The dealer hit and now has " + dHand.getScore());
		}
		if (hand1.score > dHand.getScore() && hand1.score <= 21
				|| dHand.getScore() > 21) {
			System.out.println("You win");
		} else if (hand1.score == dHand.getScore()) {
			System.out.println("Push");
		} else if (hand1.score < dHand.getScore() && dHand.getScore() <= 21
				|| hand1.score > 21) {
			System.out.println("You lose");
		}
		sc.close();
	}
}
