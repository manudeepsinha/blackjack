package com.manu.bj_v2;
import java.util.*;
public class Deck{

    //Deck is basically a list of cards.
    private List<Cards> cardsList = new ArrayList<Cards>();

    public Deck()
    {
        for(Suit suit : Suit.values())
        {
            for(Ranks rank : Ranks.values())
            {
                Cards card = new Cards(suit, rank);
                this.cardsList.add(card);
            }
        }
    }

    public void shuffle()
    {
        Collections.shuffle(cardsList);
    }

    public int deckSize()
    {
        return cardsList.size();
    }
    
    public int getScore(List<Cards> cardsList) {
    	int total = 0;
    	for(Cards card: cardsList) {
    		total += card.getRank().getValue();
    	}
		return total;
	}

}