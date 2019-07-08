import java.util.Random;
import java.util.Scanner;

/* To-Do:
 * Code rules for Skip, Draw 2, draw 4/shuffle wilds, and Reverse
 * Make ASCII cards for visual aspect
 * */


public class Cards {
	public String[][] deck;
	public String currentCard;
	//array[# card types][# colors]
	
	String color;
	Random rand = new Random();
	Scanner scan = new Scanner(System.in);
	
	public Cards() {
				//[Title] [#Red][#Blue][#Green][#Yellow]
				//----------------------------------------------
				//[Zero]    [1]   [1]     [1]    [1]
				//[One]     [2]   [2]     [2]    [2]
				//[Two]     [2]   [2]     [2]    [2]
				//[Three]   [2]   [2]     [2]    [2]
				//[Four]    [2]   [2]     [2]    [2]
				//[Five]    [2]   [2]     [2]    [2]
				//[Six]     [2]   [2]     [2]    [2]
				//[Seven]   [2]   [2]     [2]    [2]
				//[Eight]   [2]   [2]     [2]    [2]
				//[Nine]    [2]   [2]     [2]    [2]
				//[Skip]    [2]   [2]     [2]    [2]
				//[Reverse] [2]   [2]     [2]    [2]
				//[Draw 2]  [2]   [2]     [2]    [2]
		   //[Choose Color] [1]   [1]     [1]    [1]
    //[Choose Color&Draw 4] [1]   [1]     [1]    [1]
     //[Color&Shuffle Hand] [1]   [1]     [1]    [1]
		init();
	}
	
	public void init() {
		deck = new String[16][5];//adding titles for each card type
		deck[0][0] = "0";
		deck[1][0] = "1";
		deck[2][0] = "2";
		deck[3][0] = "3";
		deck[4][0] = "4";
		deck[5][0] = "5";
		deck[6][0] = "6";
		deck[7][0] = "7";
		deck[8][0] = "8";
		deck[9][0] = "9";
		deck[10][0] = "Skip";
		deck[11][0] = "Reverse";
		deck[12][0] = "Draw 2";
		deck[13][0] = "Wild Card";
		deck[14][0] = "Wild Card, Draw 4";
		deck[15][0] = "Wild Card, Shuffle Hands";
		
		//number and special cards
		//these adds the amount of each card to the array
		for (int i = 0; i < 13; i++) {
			for (int j = 1; j < 5; j++) {
				if (i == 0) {
					deck[i][j] = "1";
				} else {
					deck[i][j] = "2";
				}
			}
		}
		
		//wild cards
		for (int i = 13; i < 16; i++) {
			for (int j = 1; j < 5; j++) {
				deck[i][j] = "1";//putting as one of each color, although it that wont matter
			}
		}
	}
	
	public String[] startingDeal() {//for the first deal
		String[] cards = new String[7];//hand of 7 cards
		for (int i = 0; i < 7; i++) {//loop through all slots in hand array
			color = "";//reset color string
			while (cards[i] == null) {//if the card is chosen as null, continuously loop though to find one that isnt null
				int cardNumber = rand.nextInt(16);//choose random card value
				int cardColor = rand.nextInt(5-1)+1;//choose random color
				//convert color selection to string
				if (cardNumber < 13) {
					if (cardColor == 1) {
						color = "Blue ";
					} else if (cardColor == 2) {
						color = "Red ";
					} else if (cardColor == 3) {
						color = "Yellow ";
					} else if (cardColor == 4) {
						color = "Green ";
					}
				}
				//if the card slot chosen doesn't contain zero cards
				if(Integer.parseInt(deck[cardNumber][cardColor]) > 0) {
					if (cardNumber >= 13) {//if the card is a wild card
						cards[i] = ("" + deck[cardNumber][0]);//add the card to the hand without color string
						deck[cardNumber][cardColor] = Integer.toString(Integer.parseInt(deck[cardNumber][cardColor])-1);//subtract one from the deck's count of that card
					} else {
						cards[i] = ("" + color + deck[cardNumber][0]);//add the card to the hand
						deck[cardNumber][cardColor] = Integer.toString(Integer.parseInt(deck[cardNumber][cardColor])-1);//subtract one from the deck's count of that card
					}
				}
			}
		}
		
		//shrink the hand array to include no empty spaces
		String[] handTemp;
		int counter = 0;
		for (int i = 0; i < cards.length; i++) {
			if (cards[i] != null) {
				counter++;
			}
		}
		handTemp = new String[counter];
		for (int i = 0; i < handTemp.length; i++) {
			handTemp[i] = cards[i];
		}
		//System.out.println("Cards Left: " + numberOfTotalCards);//count of cards that are left
		return cards;
	}
	
	public String dealOne(Hand hand) {
		String card = null;
		while (card == null) {//if the card is chosen as null, continuously loop though to find one that isnt null
			color = "";//reset color string
			int cardNumber = rand.nextInt(16);//choose random card value
			int cardColor = rand.nextInt(5-1)+1;//choose random color
			//convert color selection to string
			if (cardNumber < 13) {
				if (cardColor == 1) {
					color = "Blue ";
				} else if (cardColor == 2) {
					color = "Red ";
				} else if (cardColor == 3) {
					color = "Yellow ";
				} else if (cardColor == 4) {
					color = "Green ";
				}
				//if the card slot chosen doesn't contain zero cards
				if(Integer.parseInt(deck[cardNumber][cardColor]) > 0) {
					card = ("" + color + deck[cardNumber][0]);//add the card to the hand
					deck[cardNumber][cardColor] = Integer.toString(Integer.parseInt(deck[cardNumber][cardColor])-1);//subtract one from the deck's count of that card
				}
			} else {//if the card chosen is a wild card
				//if the card slot chosen doesn't contain zero cards
				if(Integer.parseInt(deck[cardNumber][cardColor]) > 0) {
					card = ("" + deck[cardNumber][0]);//add the card to the hand without color string
					deck[cardNumber][cardColor] = Integer.toString(Integer.parseInt(deck[cardNumber][cardColor])-1);//subtract one from the deck's count of that card
				}
			}
		}
		currentCard = card;//make the chosen card the current card
		return card;
	}
	
	public void drawCard(Hand hand) {
		int numberOfTotalCards;
		numberOfTotalCards = 0;//counts number of cards left in deck
		for (int j = 0; j < 16; j++) {//loop though all cards in the deck
			for (int k = 1; k < 5; k++) {
				numberOfTotalCards += Integer.parseInt(deck[j][k]);//count how many there are
			}
		}
		if (numberOfTotalCards <= 0) {//re-shuffle the deck when there are no cards left
			init();
		}
		
		String card = null;
		while (card == null) {//if the card is chosen as null, continuously loop though to find one that isnt null
			int cardNumber = rand.nextInt(16);//choose random card value
			int cardColor = rand.nextInt(5-1)+1;//choose random color
			//convert color selection to string
			if (cardNumber < 13) {
				if (cardColor == 1) {
					color = "Blue ";
				} else if (cardColor == 2) {
					color = "Red ";
				} else if (cardColor == 3) {
					color = "Yellow ";
				} else if (cardColor == 4) {
					color = "Green ";
				}
				//if the card slot chosen doesn't contain zero cards
				if(Integer.parseInt(deck[cardNumber][cardColor]) > 0) {
					card = ("" + color + deck[cardNumber][0]);//add the card to the hand
					deck[cardNumber][cardColor] = Integer.toString(Integer.parseInt(deck[cardNumber][cardColor])-1);//subtract one from the deck's count of that card
				}
			} else {//if the card is not a wild card
				//if the card slot chosen doesn't contain zero cards
				if(Integer.parseInt(deck[cardNumber][cardColor]) > 0) {
					card = ("" + deck[cardNumber][0]);//add the card to the hand with no color string
					deck[cardNumber][cardColor] = Integer.toString(Integer.parseInt(deck[cardNumber][cardColor])-1);//subtract one from the deck's count of that card
				}
			}
		}
		//System.out.println("Total Cards: " + numberOfTotalCards);
		hand.addToHand(card);//add chosen card to hand
	}
	
	
	//play card function for the human
	String colorOfCardToPlay = null;
	String numberOfCardToPlay = null;
	public String colorOfCurrent = null;
	public String numberOfCurrent = null;
	public int playCard(int card, Hand hand) {
		int state = 0;//0 = card can be played, 1 = card cant be played, 2 = card can be played and replay turn, 3 = opponent draw 2, 4 = opponent draw 4
		String cardToPlay = hand.hand[card];
		for (int i = 0; i < cardToPlay.length(); i++) {//loop through the string containing the card the user wants to play
			if (cardToPlay.substring(i, i+1).equals(" ")) {//if the loop reaches a spot where the next character is a space
				colorOfCardToPlay = cardToPlay.substring(0, i);//the color is the string from the beginning to the current spot
				numberOfCardToPlay = cardToPlay.substring(i+1);//the number is from after the space to the end of the string
				break;
			} else continue;//if the space is not found, continue in the loop
		}
		for (int i = 0; i < currentCard.length(); i++) {//loop through the string of the current card in play
			if (currentCard.substring(i, i+1).equals(" ")) {////if the loop reaches a spot where the next character is a space
				colorOfCurrent = currentCard.substring(0, i);//the color is the string from the beginning to the current spot
				numberOfCurrent = currentCard.substring(i+1);//the number is from after the space to the end of the string
				break;
			} else continue;//if the space is not found, continue in the loop
		}
		
		//rules for placing new cards
		if (cardToPlay.contains("Wild")) {//if the user wants to play a wild card
			if (cardToPlay.contains("Draw 4")) {
				state = 4;
				//other player has 4 cards added to hand
			} else if (cardToPlay.contains("Shuffle")) {
				//shuffle the hands of all players and deal equal sized hands back
			}
			System.out.println("You played: " + hand.hand[card]);
			System.out.println("What color would you like to choose? <R-G-B-Y>: ");//prompt the user to choose a color of the card
			char colorChoice = scan.next().charAt(0);
			if (colorChoice == 'r' || colorChoice == 'R') {//red
				colorOfCardToPlay = "Red";
			} else if (colorChoice == 'g' || colorChoice == 'G') {//green
				colorOfCardToPlay = "Green";
			} else if (colorChoice == 'b' || colorChoice == 'B') {//blue
				colorOfCardToPlay = "Blue";
			} else if (colorChoice == 'y' || colorChoice == 'Y') {//yellow
				colorOfCardToPlay = "Yellow";
			}
			hand.removeFromHand(card);//remove the card from the hand
			hand.printHand(this);//print the updated hand
			currentCard = colorOfCardToPlay + " " + numberOfCardToPlay;//change the current card to reflect the chosen color 
			System.out.println("\nThe color is now " + colorOfCardToPlay + "\n");//display the new color
		} else if (currentCard.contains(colorOfCardToPlay) || currentCard.contains(numberOfCardToPlay) || currentCard.contains("Wild")) {
			if (numberOfCardToPlay.contains("Draw 2")) {
				//other player has 2 cards added to hand
				state = 3;
			} else if (numberOfCardToPlay.contains("Reverse") || numberOfCardToPlay.contains("Skip")) {
				//play another card
				state = 2;
				System.out.println("\nYour turn again!");
			}
			System.out.println("\nYou played: " + hand.hand[card] + "\n");//show what card the user played
			hand.removeFromHand(card);//remove it from the hand
			//hand.printHand();//print the hand
			currentCard = colorOfCardToPlay + " " + numberOfCardToPlay;//glue the parts of the card together and make them the current card
		} else {//if the attempted card does not follow the rules of placement
			System.out.println("You want to play: " + hand.hand[card] + " on a " + currentCard);
			System.out.println("That doesnt work. Try a different card");
			state = 1;//the card is not able to be played
		}
		return state;//return the validity of the card
	}

	//play card function for the computer
	public int compPlayCard(String card, int cardNum, Hand hand, String colorChoice, boolean wilds) {
		int state = 0;//0=able to play, 1=not able to play, 2=reverse, 3=draw 2, 4=draw 4
		String cardToPlay = card;
		String wild = "Wild";
		for (int i = 0; i < cardToPlay.length(); i++) {//loop through the string containing the card the user wants to play
			if (cardToPlay.substring(i, i+1).equals(" ")) {//if the loop reaches a spot where the next character is a space
				colorOfCardToPlay = cardToPlay.substring(0, i);//the color is the string from the beginning to the current spot
				numberOfCardToPlay = cardToPlay.substring(i+1);//the number is from after the space to the end of the string
				break;
			} else continue;//if the space is not found, continue in the loop
		}
		for (int i = 0; i < currentCard.length(); i++) {//loop through the string of the current card in play
			if (currentCard.substring(i, i+1).equals(" ")) {////if the loop reaches a spot where the next character is a space
				colorOfCurrent = currentCard.substring(0, i);//the color is the string from the beginning to the current spot
				numberOfCurrent = currentCard.substring(i+1);//the number is from after the space to the end of the string
				break;
			} else continue;//if the space is not found, continue in the loop
		}
		
		//rules for placing new cards
		if (wilds) {//if the computer chose to play a wild card
			currentCard = colorChoice + " " + numberOfCardToPlay;//add its chosen color to the current card
			hand.removeFromHand(cardNum);//remove the card from its hand
			System.out.println("The computer has " + hand.hand.length + " cards left\n");//display how many cards it has left
			System.out.println("The color is now " + colorChoice + "\n");//display new color
		} else if (currentCard.contains(colorOfCardToPlay) || currentCard.contains(numberOfCardToPlay) || currentCard.contains(wild)) {
			if (cardToPlay.contains("Draw 2")) {
				//other player has 2 cards added to hand
				state = 3;
			} else if (cardToPlay.contains("Reverse") || cardToPlay.contains("Skip")) {
				//play another card
				state = 2;
				System.out.println("\nComputer's turn again");
			}
			System.out.println("\nComputer played: " + card + "\n");
			hand.removeFromHand(cardNum);
			System.out.println("The computer has " + hand.hand.length + " cards left\n");
			currentCard = colorOfCardToPlay + " " + numberOfCardToPlay;
		} else {//if the computer makes a goofy decision
			System.out.println("The computer wants to play: " + card + " on a " + currentCard);
			System.out.println("That doesnt work. Its being scolded");
			state = 1;//make the card unable to be played
		}
		return state;
	}
	
	public String[] getColorAndNum(String card) {//gets color and number of a card from the card string
		String cardColor = null, cardNumber = null;
		for (int i = 0; i < card.length(); i++) {//loop through the string of the current card in play
			if (card.substring(i, i+1).equals(" ")) {////if the loop reaches a spot where the next character is a space
				cardColor = card.substring(0, i);//the color is the string from the beginning to the current spot
				cardNumber = card.substring(i+1, card.length());//the number is from after the space to the end of the string
			} else continue;//if the space is not found, continue in the loop
		}
		String[] colorNum = {cardColor, cardNumber};//return an array of [0: color][1: number]
		return colorNum;
	}
	
	public void makeCard(String color, String number, String card) {
		if (!checkNotNum(card)) {
			if(color.contains("Red")) {
				System.out.print("_________\n" +
						 "| " + number + "      |\n" +
						 "|        |\n" +
						 "|  " + color + "   |\n" +
						 "|        |\n" +
						 "|     " + number +"  |\n" +
						 "---------\n");
			} else if (color.contains("Yellow")) {
				System.out.print("_________\n" +
						 "| " + number + "      |\n" +
						 "|        |\n" +
						 "| " + color + " |\n" +
						 "|        |\n" +
						 "|     " + number +"  |\n" +
						 "---------\n");
			} else if (color.contains("Green")) {
				System.out.print("_________\n" +
						 "| " + number + "      |\n" +
						 "|        |\n" +
						 "|  " + color + " |\n" +
						 "|        |\n" +
						 "|     " + number +"  |\n" +
						 "---------\n");
			} else if (color.contains("Blue")) {
				System.out.print("_________\n" +
						 "| " + number + "      |\n" +
						 "|        |\n" +
						 "|  " + color + "  |\n" +
						 "|        |\n" +
						 "|     " + number +"  |\n" +
						 "---------\n");
			}
		} else if (checkNotNum(card)) {
			if(number.contains("Reverse")) {
				System.out.print("_________\n" +
						 "|Reverse |\n" +
						 "|        |\n" +
						 "|  " + color + "  |\n" +
						 "|        |\n" +
						 "| Reverse|\n" +
						 "---------\n");
			}
			if(card.contains("Draw 2")) {
				color = color.substring(0, color.length()-5);
				System.out.print("_________\n" +
						 "|Draw 2  |\n" +
						 "|        |\n" +
						 "|  " + color + "  |\n" +
						 "|        |\n" +
						 "|  Draw 2|\n" +
						 "---------\n");
			}
			if(number.contains("Skip")) {
				System.out.print("_________\n" +
						 "| Skip   |\n" +
						 "|        |\n" +
						 "|  " + color + "  |\n" +
						 "|        |\n" +
						 "|  Skip  |\n" +
						 "---------\n");
			}
			if(card.contains("Card")) {
				if (card.contains("Draw 4")) {
					System.out.print("_________\n" +
							 "|Wild +4 |\n" +
							 "|        |\n" +
							 "|        |\n" +
							 "|        |\n" +
							 "| Wild +4|\n" +
							 "---------\n");
				} else if(number.contains("Hands")) {
					System.out.print("_________\n" +
							 "| Wild   |\n" +
							 "|        |\n" +
							 "| Shuffle|\n" +
							 "|        |\n" +
							 "|   Wild |\n" +
							 "---------\n");
				} else {
					System.out.print("_________\n" +
							 "| Wild   |\n" +
							 "|        |\n" +
							 "|        |\n" +
							 "|        |\n" +
							 "|   Wild |\n" +
							 "---------\n");
				}
			}
		}
	}
	private boolean checkNotNum(String number) {
		boolean notNum = false;
		if (number.contains("Hands") || number.contains("Reverse") || number.contains("Draw") || number.contains("Skip") || number.contains("Card")) {
			notNum = true;
		}
		return notNum;
	}
}