import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BlackJackGame {

	public double[] deck;

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
}