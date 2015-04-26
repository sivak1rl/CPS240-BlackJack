import java.awt.EventQueue;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//
public class AgainstDealer {

	public static void main(String[] args) {

		BlackJackGame game = new BlackJackGame();

		BlackJackGUI window = new BlackJackGUI();
		window.frame.setVisible(true);
		window.initialize();

		boolean split = false;

		PlayerHand[] hands = new PlayerHand[1];
		DealerHand dHand = new DealerHand();
		List<Card> deck = new LinkedList<Card>();
		for (double d : game.deck) {
			deck.add(new Card(d));
		}
		// Collections.shuffle(deck);
		String input;
		int numOfPlayers;
		Scanner sc = new Scanner(System.in);
		System.out.print("How many people are playing? (5 or less): ");
		// GUI must ask about this

		System.out.println(window.wantHit);

		// numOfPlayers = sc.nextInt();
		// numOfPlayers = 1;
		// while (numOfPlayers > 5 || numOfPlayers < 1) {
		// System.out
		// .print("You must have a player and can't have more than 5.\nEnter another number: ");
		// numOfPlayers = sc.nextInt();
		// // GUI Must ask about this
		// }
		// for (int i = 0; i < numOfPlayers; i++) {
		hands[0] = new PlayerHand();
		hands[0].Deal(deck);
		// }
		dHand.Deal(deck);
		// for (int i = 0; i < hands.size(); i++) {
		// int pNumber = i + 1;
		System.out.println("Player 1:");
		System.out.println(hands[0]);
		//
		// }
		System.out.println("\nThe dealer's up card is " + dHand.getUpCard()
				+ "\n");
		// for (int i = 0; i < hands.size(); i++) {
		// int pNumber = i + 1;
		// System.out.println("Player " + pNumber + ":");
		for (int i = 0; i < 1; i++) {
			System.out.println("Player 1:");
			System.out.println(hands[0]);
			if (hands[0].getHasDoubles()) {
				System.out
						.print("You can split. Want to? (y for yes, anything else for no) or Hit for yes: ");

				System.out.println(window.wantHit + "");

				// Want to get wantHit true using GUI
				input = sc.next();

				if (input.equals("y") || window.wantHit) {
					split = true;
					// wont work with gui
					window.wantHit = false;
					PlayerHand temp = hands[0];
					hands = new PlayerHand[2];
					for (int j = 0; j < 2; j++) {
						if (temp.pHand.get(j).getScore() == 11) {
							temp.pHand.get(j).ace = true;
						}
					}

					hands[0] = new PlayerHand();
					hands[0].Deal(temp.pHand.get(0), deck.remove(0));

					hands[1] = new PlayerHand();
					hands[1].Deal(temp.pHand.get(1), deck.remove(0));

					System.out.println(hands[0]);
					System.out.println(hands[1]);
					System.out.println();

					for (PlayerHand ph : hands) {
						PlayerTurn(ph, deck);
					}
				}
				break;
			}
		}
		input = "y";
		if (!split) {
			while ((input.equals("y") || window.wantHit)
					&& hands[0].getScore() <= 21) {
				System.out.println("\nPlayer 1:");
				System.out.println(hands[0]);
				System.out
						.print("Do you want a hit? (y for yes, anything else for no): ");

				// wait for GUI to hit

				input = sc.next();

				if (input.equals("y") || window.wantHit) {
					window.wantHit = false;
					hands[0].Hit(deck);
				}
			}
		}
		System.out.println("\nPlayer 1:");
		System.out.println(hands[0] + "\n");

		// Dealer Stuff
		System.out.println(dHand);
		while (dHand.getScore() < 17) {
			dHand.Hit(deck);
			System.out.println(dHand);
		}

		sc.close();
		for (int i = 0; i < hands.length; i++) {
			// int pNumber = i + 1;
			for (PlayerHand ph : hands) {
				if (ph.beatDealer(dHand)) {
					System.out.println("Player 1 beat the dealer");
				} else {
					System.out.println("Player 1 lost to the dealer");
				}
			}
		}
	}

	public static void PlayerTurn(PlayerHand currentHand, List<Card> deck) {
		Scanner sc = new Scanner(System.in);
		String input = "y";
		while (input.equals("y") && currentHand.getScore() <= 21) {
			System.out.println(currentHand);
			System.out
					.print("Do you want a hit? (y for yes, anything else for no): ");
			input = sc.next();
			if (input.equals("y")) {
				currentHand.Hit(deck);
			}
		}
		System.out.println(currentHand + "\n");
	}
}
