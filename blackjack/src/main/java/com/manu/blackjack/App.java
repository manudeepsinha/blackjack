package com.manu.blackjack;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
    	
//    	Scanner sc = new Scanner(System.in);
    	ArrayList<String> players = new ArrayList<String>(2);
    	deckOfCards cards = new deckOfCards();
    	ArrayList<String> deck = cards.deckOfShuffledCards();
    	ArrayList<String> userCards = new ArrayList<String>();
    	ArrayList<String> dealerCards = new ArrayList<String>();
    	int count = 0;	// count of which card has to be deal
    	int scoreUser = 0;
    	int scoreDealer = 0;
    	String valueNotInt = "AJQK";	// Jack Queen King -> 10, Ace -> 11
    	int win = -1; //0 for user, 1 for dealer
    	boolean bothAUser = false;
    	boolean userBJ = false;
    	boolean gameEnd = false;
    	String userCardsStr = "";
    	String dealerCardsStr = "";
    	
    	players.add(0, "sam"); //players.add(0, sc.nextLine());
    	players.add(1, "dealer");
        
    	if(deck.size() < 4) {
    		System.out.println("Deck size should be atleast 4");
    		gameEnd = true;
    	}
    	if(!gameEnd) {
    		//1st hand
            for (int i=0;i<2;i++) {
            	userCards.add(deck.get(count));
            	count++;
            	dealerCards.add(deck.get(count));
            	count++;
            }
            
//            score count
            //check if either player has Blackjack with their initial hand
            if(userCards.get(0).contains("A") || userCards.get(1).contains("A")) {
            	//check if 1st is A
            	if(userCards.get(0).contains("A")) {
            		if("10JQK".contains(userCards.get(1).trim().substring(1))){
            			win = 0;	// user wins
            			userBJ = true;
            		}
            		else if (userCards.get(1).contains("A")) {
            			bothAUser = true;
            			scoreUser = 22;
            		}
            		else {
            			scoreUser = 11 + Integer.parseInt(userCards.get(1).trim().substring(1));
            		}
            	}
            	else {
            		if("10JQK".contains(userCards.get(0).trim().substring(1))){
            			win = 0;	// user wins
            			userBJ = true;
            		}
            		else {
            			scoreUser = 11 + Integer.parseInt(userCards.get(0).trim().substring(1));
            		}
            	}
            	
            	
            }
            else {
            	if(!valueNotInt.contains(userCards.get(0).trim().substring(1))) {
            		scoreUser += Integer.parseInt(userCards.get(0).trim().substring(1));
            	}
            	else
            	{
            		scoreUser += 10;
            	}
            	if(!valueNotInt.contains(userCards.get(1).trim().substring(1))) {
            		scoreUser += Integer.parseInt(userCards.get(1).trim().substring(1));
            	}
            	else {
            		scoreUser += 10;
            	}
            	
            }
            
            if(dealerCards.get(0).contains("A") || dealerCards.get(1).contains("A")) {
            	//check if 1st is A
            	if(dealerCards.get(0).contains("A")) {
            		if("10JQK".contains(dealerCards.get(1).trim().substring(1))){
            			if(!userBJ) {
            				win = 1;	// dealer wins
            			}
            		}
            		else if (dealerCards.get(1).contains("A")) {
            			if(bothAUser) {
            				win = 1;
            			}
            		}
            		else {
            			scoreDealer = 11 + Integer.parseInt(dealerCards.get(1).trim().substring(1));
            		}
            	}
            	else {
            		if("10JQK".contains(dealerCards.get(0).trim().substring(1))){
            			if(!userBJ) {
            				win = 1;	// dealer wins
            			}
            		}
            		else {
            			scoreDealer = 11 + Integer.parseInt(dealerCards.get(0).trim().substring(1));
            		}
            	}
            }
            else {
            	if(!valueNotInt.contains(dealerCards.get(0).trim().substring(1))) {
            		scoreDealer += Integer.parseInt(dealerCards.get(0).trim().substring(1));
            	}
            	else
            	{
            		scoreDealer += 10;
            	}
            	if(!valueNotInt.contains(dealerCards.get(1).trim().substring(1))) {
            		scoreDealer += Integer.parseInt(dealerCards.get(1).trim().substring(1));
            	}
            	else {
            		scoreDealer += 10;
            	}
            	
            }
            
          //checking after hand 1
            gameEnd = checkWhoWins(win, players);
            if(!gameEnd && deck.size() >= count) {
            	//iterating till user gets score >= 17
            	while (scoreUser < 17 && deck.size() >= count) {
            		userCards.add(deck.get(count));
                	
                	if(!valueNotInt.contains(deck.get(count).trim().substring(1))) {
                		scoreUser += Integer.parseInt(deck.get(count).trim().substring(1));
                	}
                	else
                	{
                		if(deck.get(count).contains("A")) {
                			scoreUser += 11;
                		}
                		else {
                			scoreUser += 10;
                		}
                	}
                	count++;
            	}
            	
            	if(scoreUser > 21) {
            		win = 1; //Dealer wins
            	}
            	else {
        	    		while (scoreDealer <= scoreUser  && deck.size() >= count) {
        	        		dealerCards.add(deck.get(count));
        	            	
        	            	if(!valueNotInt.contains(deck.get(count).trim().substring(1))) {
        	            		scoreDealer += Integer.parseInt(deck.get(count).trim().substring(1));
        	            	}
        	            	else
        	            	{
        	            		if(deck.get(count).contains("A")) {
        	            			scoreDealer += 11;
        	            		}
        	            		else {
        	            			scoreDealer += 10;
        	            		}
        	            	}
        	            	count++;
        	        	}
        	    		
        	    		if(scoreDealer > 21) {
        	        		win = 0; //User wins
        	        	}
            	}
            	
            	if(win == -1) {
            		if(scoreDealer > scoreUser) {
            			win = 1;
            		}
            		else {
            			win = 0;
            		}
            	}
            	for (String str: userCards) {
            		userCardsStr += str + ", ";
            	}
            	for (String str: dealerCards) {
            		dealerCardsStr += str + ", ";
            	}
            	
                // final check who wins the game
                gameEnd = checkWhoWins(win, players);
                
                //printing data
                System.out.println(players.get(0) + ":" + userCardsStr.substring(0, userCardsStr.length()-2));
                System.out.println(players.get(1) + ":" + dealerCardsStr.substring(0, dealerCardsStr.length()-2));
            }
            
            
            else {
            	for (String str: userCards) {
            		userCardsStr += str + ",";
            	}
            	for (String str: dealerCards) {
            		dealerCardsStr += str + ",";
            	}
                // printing data
                System.out.println(players.get(0) + ":" + userCardsStr.substring(0, userCardsStr.length()-1));
                System.out.println(players.get(1) + ":" + dealerCardsStr.substring(0, dealerCardsStr.length()-1));
            }
    	}
         
        
    }

	private static boolean checkWhoWins(int win, ArrayList<String> players) {
		if(win == -1) {
			return false;
		}
		else if(win == 0) {
			System.out.println(players.get(0));
		}
		else if(win == 1) {
			System.out.println(players.get(1));
		}
		return true;
	}
    
}
