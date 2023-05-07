package com.manu.bj_v2;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
	
	private int score = 0;
	private List<Cards> cardsList = new ArrayList<Cards>();
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public List<Cards> getCardsList() {
		return cardsList;
	}
	public void setCardsList(List<Cards> cardsList) {
		this.cardsList = cardsList;
	}

}