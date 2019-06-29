import java.util.Scanner;

public class Dealer {
	public static boolean reshuffle = false;
	public static void main(String[] args) {
		boolean looping = true;
		boolean playing;
		Cards cards;
		Hand hand, compHand;
		Computer comp;
		Scanner scan = new Scanner(System.in);
		
		BREAKLOOP:
		while(looping) {//encompassing loop that allows multiple games to be played in a row
			cards = new Cards();//create new deck
			
			System.out.print("Start a game? <Y/N>: ");
			char deal = scan.next().charAt(0);
			if (deal == 'Y' || deal == 'y') {//if the user wants to play a game
				playing = true;//start the game loop
				System.out.println("Press x at any time to quit.");
				hand = new Hand(cards.startingDeal());//deal user's hand
				compHand = new Hand(cards.startingDeal());//deal computer's hand
				comp = new Computer(compHand, cards);//create the computer
				System.out.println("\n\nFirst card: " + cards.dealOne(hand));//lay first card
				System.out.println("Your turn first.\n\n");
				BREAK:
				while(playing) {//game loop
					hand.printHand();//print hand each time the loop is started
					System.out.print("Play card or draw card? <P/D>: ");//prompt for action
					int playChoice = scan.next().charAt(0);
					if (playChoice == 'P' || playChoice == 'p') {//if the user wants to play a card
						System.out.print("What card would you like to play?: ");
						int cardChoice = scan.nextInt();//takes index of card the user wants to play
						if(cards.playCard(cardChoice-1, hand)) {//plays selected card while also checking if the card can be played
							if(hand.hand.length <= 0) {//if the user runs out of cards before the computer
								System.out.println("Great job! You beat the computer.");
								playing = false;//break from the game loop
								break BREAK;
							}
							//if the card can be played, progress to the computer's turn
							System.out.println("\nComputer's turn...");
							comp.play(compHand);
							if (compHand.hand.length <= 0) {//if the computer runs out of cards before the user
								System.out.println("Oof, You got beat by the computer.");
								playing = false;//break from the game loop
								break BREAK;
							}
						}
					} else if (playChoice == 'D' || playChoice == 'd') {//if the user wants to draw a card
						cards.drawCard(hand);//draw card and add to user's hand
					} else if (playChoice == 'X' || playChoice == 'x') {//exit
						playing = false;//make game loop false
						break BREAK;//break from game loop
					} else {//if the user enters something that doesn't work
						System.out.println("Want to try that again?");
					}
				}
			} else {//if the user does not want to play another round
				playing = false;//break from both loops
				looping = false;
				break BREAKLOOP;
			}
		}
		System.out.println("\nShutting down...");//notify user that the program is terminating
	}
}
