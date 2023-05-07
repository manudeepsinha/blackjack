package com.manu.bj_v2;

public class Cards
{
    Suit suit;
    Ranks rank;

    public Cards(Suit suit, Ranks rank)
    {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit()
    {
        return suit;
    }

    public Ranks getRank()
    {
        return rank;
    }

    //call this function to print the card player/ dealer has
    public String printCard()
    {
        return  null;
    }
}