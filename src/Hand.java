
public class Hand {
	String[] hand;
	
	public Hand(String[] hand) {
		this.hand = hand;//localize the hand
	}
	
	public void addToHand(String card) {//function to add a card to the hand
		String[] handtemp = new String[hand.length+1];//increase hand size by one
		for (int i = 0; i < hand.length; i++) {//loop through the length of the hand
			handtemp[i] = hand[i];//add all elements to the temp array
		}
		handtemp[hand.length] = card;//add the new card to the end of the temp array
		hand = handtemp;//make the hand equal to the temp array
	}
	
	public void removeFromHand(int card) {//function for removing card from the hand
		String [] temp = new String[hand.length];//make new temp array for hand
		int internalCounter = 0;//keeps track of next available index
		for(int i = 0; i < hand.length; ++i){//loop through whole hand
		    if( i != card ){//if the current card is not equal to the desired card
		        temp[internalCounter] = hand[i];//add the current card to the array in the lowest available slot
		        internalCounter++;//increase available index
		    }
		}
		temp[temp.length-1] = null;//make last slot null
		String [] temp2 = new String[temp.length-1];//new temp array to shorten the hand to exclude null entries
		for (int j = 0; j < temp2.length; j++) {//loop through the new temp array
			if (temp[j] != null) {//if the slot in the first temp array isn't null
				temp2[j] = temp[j];//add the card to the shortened temp array
			}
		}
		hand = temp2;//make the hand equal to the shortened hand
	}
	
	public void printHand() {//function to print the current hand
		for (int j = 0; j < hand.length; j++) {//loop through the hand
			System.out.println((j+1) + ": " + hand[j]);//print each card with its slot number
		}
	}
}
