import java.util.Random;
import java.util.Scanner;

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
		numberOfTotalCards = 0;//counts number of cards left in deck
		for (int j = 0; j < 16; j++) {//loop though all cards in the deck
			for (int k = 1; k < 5; k++) {
				numberOfTotalCards += Integer.parseInt(deck[j][k]);//count how many there are
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
		
		if (numberOfTotalCards <= 0) {//re-shuffle the deck when there are no cards left
			Dealer.reshuffle = true;
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
		hand.addToHand(card);//add chosen card to hand
	}
	
	
	//play card function for the human
	String colorOfCardToPlay = null;
	String numberOfCardToPlay = null;
	public boolean playCard(int card, Hand hand) {
		boolean ableToPlay = true;
		String cardToPlay = hand.hand[card];
		for (int i = 0; i < cardToPlay.length(); i++) {//loop through the string containing the card the user wants to play
			if (cardToPlay.substring(i, i+1).equals(" ")) {//if the loop reaches a spot where the next character is a space
				colorOfCardToPlay = cardToPlay.substring(0, i);//the color is the string from the beginning to the current spot
				numberOfCardToPlay = cardToPlay.substring(i+1);//the number is from after the space to the end of the string
				break;
			} else continue;//if the space is not found, continue in the loop
		}
		
		//rules for placing new cards
		if (currentCard.contains(colorOfCardToPlay) || currentCard.contains(numberOfCardToPlay) || currentCard.contains("Wild")) {
			System.out.println("\nYou played: " + hand.hand[card] + "\n");//show what card the user played
			hand.removeFromHand(card);//remove it from the hand
			hand.printHand();//print the hand
			currentCard = colorOfCardToPlay + " " + numberOfCardToPlay;//glue the parts of the card together and make them the current card
		} else if (colorOfCardToPlay.contains("Wild")) {//if the user wants to play a wild card
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
			hand.printHand();//print the updated hand
			currentCard = colorOfCardToPlay + " " + numberOfCardToPlay;//change the current card to reflect the chosen color 
			System.out.println("\nThe color is now " + colorOfCardToPlay + "\n");//display the new color
		} else {//if the attempted card does not follow the rules of placement
			System.out.println("You want to play: " + hand.hand[card] + " on a " + currentCard);
			System.out.println("That doesnt work. Try a different card");
			ableToPlay = false;//the card is not able to be played
		}
		return ableToPlay;//return the validity of the card
	}
	
	String colorOfCurrent = null;
	String numberOfCurrent = null;
	//play card function for the computer
	public boolean compPlayCard(String card, int cardNum, Hand hand, String colorChoice, boolean wilds) {
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
		} else if (wilds) {//if the computer chose to play a wild card
			currentCard = colorChoice + " " + numberOfCardToPlay;//add its chosen color to the current card
			hand.removeFromHand(cardNum);//remove the card from its hand
			System.out.println("The computer has " + hand.hand.length + " cards left\n");//display how many cards it has left
			System.out.println("The color is now " + colorChoice);//display new color
		} else {//if the computer makes a goofy decision
			System.out.println("The computer wants to play: " + card + " on a " + currentCard);
			System.out.println("That doesnt work. Its being scolded");
			ableToPlay = false;//make the card unable to be played
		}
		return ableToPlay;
	}
	
	public String[] getColorAndNum(String card) {//gets color and number of a card from the card string
		String cardColor = null, cardNumber = null;
		for (int i = 0; i < card.length(); i++) {//loop through the string of the current card in play
			if (card.substring(i, i+1).equals(" ")) {////if the loop reaches a spot where the next character is a space
				cardColor = card.substring(0, i);//the color is the string from the beginning to the current spot
				cardNumber = card.substring(i+1);//the number is from after the space to the end of the string
			} else continue;//if the space is not found, continue in the loop
		}
		String[] colorNum = {cardColor, cardNumber};//return an array of [0: color][1: number]
		return colorNum;
	}
}
