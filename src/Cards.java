import java.util.Random;
import java.util.Scanner;

public class Cards {
	public String[][] deck;
	private Hand hand;
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
		
		deck = new String[16][5];//adding titles for each card type
		deck[0][0] = "Zero";
		deck[1][0] = "One";
		deck[2][0] = "Two";
		deck[3][0] = "Three";
		deck[4][0] = "Four";
		deck[5][0] = "Five";
		deck[6][0] = "Six";
		deck[7][0] = "Seven";
		deck[8][0] = "Eight";
		deck[9][0] = "Nine";
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
		int numberOfTotalCards;
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
					cards[i] = ("" + color + deck[cardNumber][0]);//add the card to the hand
					deck[cardNumber][cardColor] = Integer.toString(Integer.parseInt(deck[cardNumber][cardColor])-1);//subtract one from the deck's count of that card
				}
			}
		}
		numberOfTotalCards = 0;//counts number of cards left in deck
		for (int j = 0; j < 16; j++) {//loop though all cards in the deck
			for (int k = 1; k < 5; k++) {
				numberOfTotalCards += Integer.parseInt(deck[j][k]);//count how many there are
			}
		}
		
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
		/*for (int j = 0; j < handTemp.length; j++) {
			System.out.println((j+1) + ": " + handTemp[j]);
		}
		for (int i = 0; i < deck.length; i++) {//prints the entire deck with number of cards
			for (int j = 0; j < deck[0].length; j++) {
				System.out.println(deck[i][j]);
			}
		}*/
		if (numberOfTotalCards <= 0) {//re-shuffle the deck when there are no cards left
			Dealer.reshuffle = true;
		}
		//System.out.println(numberOfTotalCards);//count of cards that are left
		return cards;
	}
	
	public String dealOne(Hand hand) {
		this.hand = hand;
		String card = null;
		while (card == null) {//if the card is chosen as null, continuously loop though to find one that isnt null
			color = "";//reset color string
			int cardNumber = rand.nextInt(16);//choose random card value
			int cardColor = rand.nextInt(5-1)+1;//choose random color
			//convert color selection to string
			if (cardNumber <= 13) {
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
			} else {
				//if the card slot chosen doesn't contain zero cards
				if(Integer.parseInt(deck[cardNumber][cardColor]) > 0) {
					card = ("" + deck[cardNumber][0]);//add the card to the hand
					deck[cardNumber][cardColor] = Integer.toString(Integer.parseInt(deck[cardNumber][cardColor])-1);//subtract one from the deck's count of that card
				}
			}
		}
		currentCard = card;
		return card;
	}
	
	public void drawCard(Hand hand) {
		this.hand = hand;
		String card = null;
		while (card == null) {//if the card is chosen as null, continuously loop though to find one that isnt null
			int cardNumber = rand.nextInt(16);//choose random card value
			int cardColor = rand.nextInt(5-1)+1;//choose random color
			//convert color selection to string
			if (cardNumber <= 13) {
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
			} else {
				//if the card slot chosen doesn't contain zero cards
				if(Integer.parseInt(deck[cardNumber][cardColor]) > 0) {
					card = ("" + deck[cardNumber][0]);//add the card to the hand
					deck[cardNumber][cardColor] = Integer.toString(Integer.parseInt(deck[cardNumber][cardColor])-1);//subtract one from the deck's count of that card
				}
			}
		}
		hand.addToHand(card);
	}
	
	String colorOfCardToPlay = null;
	String colorOfCurrent = null;
	String numberOfCardToPlay = null;
	String numberOfCurrent = null;
	public boolean playCard(int card, Hand hand) {
		this.hand = hand;
		boolean ableToPlay = true;
		this.hand = hand;//update the hand
		String cardToPlay = hand.hand[card];
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
		if (currentCard.contains(colorOfCardToPlay) || currentCard.contains(numberOfCardToPlay) || currentCard.contains(wild)) {
			System.out.println("\nYou played: " + hand.hand[card] + "\n");
			hand.removeFromHand(card);
			hand.printHand();
			currentCard = colorOfCardToPlay + " " + numberOfCardToPlay;
		} else if (colorOfCardToPlay.contains(wild)) {
			System.out.println("You played: " + hand.hand[card]);
			System.out.println("What color would you like to choose? <R-G-B-Y>: ");
			char colorChoice = scan.next().charAt(0);
			if (colorChoice == 'r' || colorChoice == 'R') {
				colorOfCardToPlay = "Red";
			} else if (colorChoice == 'g' || colorChoice == 'G') {
				colorOfCardToPlay = "Green";
			} else if (colorChoice == 'b' || colorChoice == 'B') {
				colorOfCardToPlay = "Blue";
			} else if (colorChoice == 'y' || colorChoice == 'Y') {
				colorOfCardToPlay = "Yellow";
			}
			hand.removeFromHand(card);
			hand.printHand();
			currentCard = colorOfCardToPlay + " " + numberOfCardToPlay;
			System.out.println("The color is now " + colorOfCardToPlay);
		} else {
			System.out.println("You want to play: " + hand.hand[card] + " on a " + currentCard);
			System.out.println("That doesnt work. Try a different card");
			ableToPlay = false;
		}
		return ableToPlay;
	}
	
	public boolean compPlayCard(String card, int cardNum, Hand hand, String colorChoice, boolean wilds) {
		this.hand = hand;
		boolean ableToPlay = true;
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
		if (currentCard.contains(colorOfCardToPlay) || currentCard.contains(numberOfCardToPlay) || currentCard.contains(wild)) {
			System.out.println("\nComputer played: " + card + "\n");
			hand.removeFromHand(cardNum);
			System.out.println("The computer has " + hand.hand.length + " cards left\n");
			currentCard = colorOfCardToPlay + " " + numberOfCardToPlay;
		} else if (wilds) {
			currentCard = colorChoice + " " + numberOfCardToPlay;
			hand.removeFromHand(cardNum);
			System.out.println("The computer has " + hand.hand.length + " cards left\n");
			currentCard = colorOfCardToPlay + " " + numberOfCardToPlay;
			System.out.println("The color is now " + colorOfCardToPlay);
		} else {
			System.out.println("The computer wants to play: " + card + " on a " + currentCard);
			System.out.println("That doesnt work. Its being scolded");
			ableToPlay = false;
		}
		return ableToPlay;
	}
	
	public String[] getColorAndNum(String card) {
		String cardColor = null, cardNumber = null;
		for (int i = 0; i < card.length(); i++) {//loop through the string of the current card in play
			if (card.substring(i, i+1).equals(" ")) {////if the loop reaches a spot where the next character is a space
				cardColor = card.substring(0, i);//the color is the string from the beginning to the current spot
				cardNumber = card.substring(i+1);//the number is from after the space to the end of the string
			} else continue;//if the space is not found, continue in the loop
		}
		String[] colorNum = {cardColor, cardNumber};
		return colorNum;
	}
}
