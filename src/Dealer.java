import java.util.Scanner;

public class Dealer {
	public static boolean reshuffle = false;
	public static void main(String[] args) {
		reshuffle = false;
		Cards cards;
		Hand hand, compHand;
		Computer comp;
		boolean playing = true;
		Scanner scan = new Scanner(System.in);
		cards = new Cards();
		
		System.out.print("Start a game? <Y/N>: ");
		char deal = scan.next().charAt(0);
		if (deal == 'Y' || deal == 'y') {//if the user wants to play a game
			System.out.println("Press x at any time to quit.");
			hand = new Hand(cards.startingDeal());//deal user's hand
			compHand = new Hand(cards.startingDeal());//deal computer's hand
			comp = new Computer(compHand, cards);
			System.out.println("\n\nFirst card: " + cards.dealOne(hand));
			System.out.println("Your turn first.\n\n");
			BREAK:
			while(playing) {//game loop
				if (reshuffle) {//if the cards need to be re-shuffled
					cards = new Cards();
					reshuffle = false;
				}
				hand.printHand();
				System.out.print("Play card or draw card? <P/D>: ");
				int playChoice = scan.next().charAt(0);
				if (playChoice == 'P' || playChoice == 'p') {
					System.out.print("What card would you like to play?: ");
					int cardChoice = scan.nextInt();
					if(cards.playCard(cardChoice-1, hand)) {
						System.out.println("\nComputer's turn...");
						comp.play(compHand);
					}
				} else if (playChoice == 'D' || playChoice == 'd') {
					cards.drawCard(hand);
				} else if (playChoice == 'X' || playChoice == 'x') {
					playing = false;
					break BREAK;
				} else {
					System.out.println("Want to try that again?");
				}
				if(hand.hand.length <= 0) {
					System.out.println("Great job! You beat the computer.");
					main(null);
				} else if (compHand.hand.length <= 0) {
					System.out.println("Oof, You got beat by the computer.");
					main(null);
				}
			}
		} else {
			playing = false;
		}
		System.out.println("\nShutting down...");
	}
}
