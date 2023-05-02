package com.manu.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.io.*;  
import java.util.Scanner;  

public class deckOfCards {
	
	public ArrayList<String> deckOfShuffledCards() throws FileNotFoundException
	{
		
		ArrayList<String> deck = new ArrayList<String>();
		ArrayList<String> deckToCheck = new ArrayList<String>();
		String[] suits = {"C","S","D","H"};
		String[] values = {"2","3","4","5","6","7","8","9","10","A","J","Q","K"};
		int extFilePresent = 0;
		String val = "";
		
		for(String s: suits){
			for(String v: values) {
				deckToCheck.add(s+ "" +v);
			}
		}
		
		try {
			FileInputStream fis= new FileInputStream("demo.txt");       
			Scanner sc=new Scanner(fis); 
			
			while(sc.hasNextLine())  
			{  
				val += sc.nextLine();      //returns the line that was skipped  
			}  
			sc.close();   
			
			String[] vals = val.split(",");
			
			for(String v : vals) {
				//check if valid v
				if(!deckToCheck.contains(v.trim())) {	//illegal character in input file
					System.out.println("Illegal card input: " + v);
					break;
				}
				else {
					//finally adding the values in deck to return
					if(!deck.contains(v)) {
						deck.add(v);
					}
				}
			}
			
			extFilePresent = 1;
		}
		catch(FileNotFoundException e) {
			e.fillInStackTrace();
			extFilePresent = 0;
		}
		
		if(extFilePresent == 1) {
			return deck;
		}
		else 
		{
			Collections.shuffle(deckToCheck);
			return deckToCheck;
		}
	}
	
}
