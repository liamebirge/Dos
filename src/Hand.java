
public class Hand {
	String[] hand;
	String[] handTemp;
	public Hand(String[] hand) {
		this.hand = hand;
		int counter = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] != null) {
				counter++;
			}
		}
		handTemp = new String[counter];
		for (int i = 0; i < handTemp.length; i++) {
			handTemp[i] = hand[i];
		}
	}
	
	public void addToHand(String card) {
		String[] handtemp = new String[hand.length+1];
		for (int i = 0; i < hand.length; i++) {
			handtemp[i] = hand[i];
		}
		handtemp[hand.length] = card;
		hand = new String[handTemp.length];
		hand = handtemp;
		/*int counter = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] != null) {
				counter++;
			}
		}
		handTemp = new String[counter];
		for (int i = 0; i < handTemp.length; i++) {
			handTemp[i] = hand[i];
		}*/
	}
	
	public void removeFromHand(int card) {
		int handSize = hand.length;
		String [] temp = new String[handSize];
		int internalCounter = 0;
		for(int i = 0; i < handSize; ++i){
		    if( i != card ){
		        temp[internalCounter] = hand[i];
		        internalCounter++;
		    }
		}
		temp[temp.length-1] = null;
		String [] temp2 = new String[temp.length-1];
		for (int j = 0; j < temp2.length; j++) {
			if (temp[j] != null) {
				temp2[j] = temp[j];
			}
		}
		hand = temp2;
	}
	
	public void printHand() {
		for (int j = 0; j < hand.length; j++) {
			System.out.println((j+1) + ": " + hand[j]);
		}
	}
}
