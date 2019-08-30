/*
 * Author: Kegan Wong
 * Date: 08/28/2019
 * Purpose: Represent a standard deck of cards. 
 * File: Deck.java
 */

import java.util.ArrayList; 
import java.util.Random;
import java.util.Collections;

public class Deck{

//used to determine whether the ace(s) has a value of 1 or 11 based on hand
private static boolean changeAces = false;
//used to model a real life, standard deck of 52 cards
private static ArrayList<Card> deck;

//constructor
public Deck(ArrayList<Card> deck){

   this.deck = deck;  

}

//getter
public ArrayList<Card> getDeck(){
 
  return deck;
  
}


//prints deck
public static void printDeck(){
   
  for (Card card: deck)
  System.out.println(card);
     
}

//shuffles current deck 
public static void shuffle(){

Random random = new Random();
int randomIndex;
//swaps with random index value using collections
  for (int deckIndex = 0; deckIndex < 52; deckIndex++){
    randomIndex = random.nextInt(52);
    Collections.swap(deck, deckIndex, randomIndex);  
  }
}

}