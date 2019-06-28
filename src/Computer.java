import java.util.Random;

public class Computer {
	String[] hand;
	Cards cards;
	Hand hands;
	Random rand = new Random();
	
	public Computer(Hand hands, Cards cards) {
		this.cards = cards;
		this.hands = hands;
		this.hand = hands.hand;
	}
	
	public String colorChoice = "";
	public void play(Hand hands) {
		colorChoice = "";//clear color choice each time the function is ran
		this.hands = hands;//import the current hand into the class
		hand = hands.hand;//import the array version of the hand from the hand class
		String[] possibleCards = null;//string array to hold all possible card choices
		String currentCardNumber = cards.numberOfCurrent;//current number
		String currentCardColor = cards.colorOfCurrent;//current color
		String cardColor = null;//variables to hold the loop's current card parts
		int counter = 0;//counts the number of valid cards there are in the computer's hand
		int[] colors = new int[4];//[red][green][blue][yellow] count of the color choices for wild card color selection
		String[] colorNum = null;//array to hold data from the getColorAndNum function
		//determine which cards are able to be played
		for (int i = 0; i < hand.length; i++) {//loop through the whole hand
			colorNum = cards.getColorAndNum(hand[i]);//get the current card's color and number
			cardColor = colorNum[0];//get the color for the card
			//if the current card fits the rules of a playable card based on the previously played card
			if (hand[i].contains(currentCardNumber) || hand[i].contains(currentCardColor) || hand[i].contains("Wild")) {
				counter++;//increase the counter
			}
			//count how many colors there are in the hand to determine what color to choose if a wild card is played
			if (cardColor.contains("Red")) {
				colors[0]++;
			} else if (cardColor.contains("Green")) {
				colors[1]++;
			} else if (cardColor.contains("Blue")) {
				colors[2]++;
			} else if (cardColor.contains("Yellow")) {
				colors[3]++;
			}
		}
		if (counter <= 0) {//if there are no playable cards
			System.out.println("The computer draws a card");
			cards.drawCard(hands);//draw a card
			System.out.println("The computer now has " + hand.length + " cards");//display how many cards the comp has left
			restart();//restart the play function
		} else {//if there are playable cards
			possibleCards = new String[counter];//create properly sized array to hold the possible cards
			int internalCounter = 0;//keeps track of the index of the possibleCards array
			for (int i = 0; i < hand.length; i++) {//loop through the whole hand
				colorNum = cards.getColorAndNum(hand[i]);
				cardColor = colorNum[0];//get the color for the card
				for(int j = 0; j < possibleCards.length; j++) {//loop through possible card array
					//if the card satisfies the rules of placement
					if (hand[i].contains(currentCardNumber) || hand[i].contains(currentCardColor) || hand[i].contains("Wild") || hand[i].contains("Wild")) {
						if (possibleCards[j] == null) {//and the slot in the array is empty
							possibleCards[internalCounter] = hand[i];//assign the card in the hand to be a possible card
							internalCounter++;//increase the index
						}
					} else continue;//if the card does not satisfy requirements, continue in the loop
				}
			}
			int cardChoice = Math.abs(rand.nextInt(possibleCards.length));//choose a random card from the possible cards to play
			
			//get the color for the card
			String[] colorPossible = cards.getColorAndNum(possibleCards[cardChoice]);
			String cardPossibleColor = colorPossible[0];
			
			//locate the desired card in the computer's total hand as to pass the index to the cards class
			int numberInHand = 0;
			for (int k = 0; k < hand.length; k++) {
				if (hand[k] == possibleCards[cardChoice]) {
					numberInHand = k;
				}
			}
			
			boolean wild = false;
			if (cardPossibleColor.contains("Wild")) {//if the card chosen is a wild card
				colorChoice = chooseColor(colors);//choose the color of the card
				wild = true;
			}
			//if the card is not able to be played
			if(!cards.compPlayCard(possibleCards[cardChoice], numberInHand, hands, colorChoice, wild)) {
				restart();//restart the play function
			}
		}
	}
	
	private void restart() {
		play(hands);
	}
	
	private String chooseColor(int[] colors) {//chooses color of wild cards
		String choice = "";
		int max = 0;
		int highestIndex = 0;
		for (int i = 0; i < colors.length; i++) {
			if (colors[i] > max) {
				max = colors[i];
				highestIndex = i;
			}
		}
		if (highestIndex == 0) {//more red
			choice = "Red";
		} else if(highestIndex == 1) {//more green
			choice = "Green";
		} else if(highestIndex == 2) {//more blue
			choice = "Blue";
		} else if(highestIndex == 3) {//more yellow
			choice = "Yellow";
		}
		return choice;
	}
}
