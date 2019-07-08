import java.util.Scanner;

public class Dealer {
	public boolean reshuffle = false;
	static boolean looping = true;
	static boolean playing;
	static Cards cards;
	static Hand hand, compHand;
	static Computer comp;
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
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
					hand.printHand(cards);//print hand each time the loop is started
					System.out.print("Play card or draw card? <P/D>: ");//prompt for action
					int playChoice = scan.next().charAt(0);
					if (playChoice == 'P' || playChoice == 'p') {//if the user wants to play a card
						System.out.print("What card would you like to play?: ");
						int cardChoice = scan.nextInt();//takes index of card the user wants to play
						int playState = cards.playCard(cardChoice-1, hand);
						if(playState == 0) {//plays selected card while also checking if the card can be played
							if(hand.hand.length <= 0) {//if the user runs out of cards before the computer
								System.out.println("Great job! You beat the computer.");
								playing = false;//break from the game loop
								break BREAK;
							}
							//if the card can be played, progress to the computer's turn
							compTurn();
							if (compHand.hand.length <= 0) {//if the computer runs out of cards before the user
								System.out.println("Oof, You got beat by the computer.");
								playing = false;//break from the game loop
								break BREAK;
							}
						} else if (playState == 3) {//draw 2
							drawCards(2, compHand);
							compTurn();
							if (compHand.hand.length <= 0) {//if the computer runs out of cards before the user
								System.out.println("Oof, You got beat by the computer.");
								playing = false;//break from the game loop
								break BREAK;
							}
						} else if (playState == 4) {//draw 4
							drawCards(4, compHand);
							compTurn();
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
	
	public static void compTurn() {
		System.out.println("\nComputer's turn...");
		int compState = comp.play(compHand);
		if (compState == 3) {//draw 2
			drawCards(2, hand);
		} else if (compState == 4) {//draw 4
			drawCards(4, hand);
		}
	}
	
	public static void drawCards(int numCards, Hand handed) {
		if (numCards == 2) {
			cards.drawCard(handed);
			cards.drawCard(handed);
		} else {
			cards.drawCard(handed);
			cards.drawCard(handed);
			cards.drawCard(handed);
			cards.drawCard(handed);
		}
	}
}
