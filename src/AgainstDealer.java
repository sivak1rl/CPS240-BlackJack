import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//
public class AgainstDealer {
	public static void main(String[] args) {
		BlackJackGame game = new BlackJackGame();
		List<PlayerHand> hands = new LinkedList<PlayerHand>();
		DealerHand dHand = new DealerHand();
		List<Card> deck = new LinkedList<Card>();
		for (double d : game.deck) {
			deck.add(new Card(d));
		}
		Collections.shuffle(deck);
		String input;
		int numOfPlayers;
		Scanner sc = new Scanner(System.in);
		System.out.print("How many people are playing? (5 or less): ");
		numOfPlayers = sc.nextInt();
		while (numOfPlayers > 5 || numOfPlayers < 1) {
			System.out
					.print("You must have a player and can't have more than 5.\nEnter another number: ");
			numOfPlayers = sc.nextInt();
		}
		for (int i = 0; i < numOfPlayers; i++) {
			hands.add(new PlayerHand());
			hands.get(i).Deal(deck);
		}
		// /////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////
		dHand.Deal(deck);
		for (int i = 0; i < hands.size(); i++) {
			int pNumber = i + 1;
			System.out.println("Player " + pNumber + ":");
			System.out.println(hands.get(i));
		}
		System.out.println("\nThe dealer's up card is " + dHand.getUpCard() + "\n");
		for (int i = 0; i < hands.size(); i++) {
			int pNumber = i + 1;
			System.out.println("Player " + pNumber + ":");
			System.out.println(hands.get(i));
			System.out
					.print("Do you want a hit? (y for yes, anything else for no): ");
			input = sc.next();
			while (input.equals("y") && hands.get(i).getScore() <= 21) {
				hands.get(i).Hit(deck);
				System.out.println("\nPlayer " + pNumber + ":");
				System.out.println(hands.get(i));
				System.out
						.print("Do you want a hit? (y for yes, anything else for no): ");
				input = sc.next();
			}
		}
		// Dealer Stuff
		System.out.println(dHand);
		while (dHand.getScore() < 17) {
			dHand.Hit(deck);
			System.out.println(dHand);
		}

		sc.close();
	}
}
