/*
 * Author: Kegan Wong
 * Date: 08/28/2019
 * Purpose: Represent a standard deck of cards. Side methods include methods
 *          that deal with the player and CPU hands.
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

/*
 * Purpose: Determines if CPU will ask for another card.
 * Return: True represents yes, false represents no.
 */ 
public static boolean CPUMovesAgain(ArrayList<Card> hand){
//stores sum of CPU's current hand
int sum = 0;
//CPU will be more aggressive if it has an ace in possession
boolean hasAce = false;
  //obtain sum of CPU hand and check for aces
  for (Card card: hand){
    if (card.getValue() == 1 || card.getValue()==11){
      hasAce = true;
    }
    sum += card.getValue();
  }
  //determines CPU decision based on sums and possession of ace
  if (sum >= 15 && hasAce == false) {  
    return false;
  }
  
  else if (sum <=17 && hasAce == true) {
    return true;
  }
  
  else if (sum>=18 && hasAce == true){
    return false;
  }
    return true;  
}


/*
 * Purpose: Determines whether or not one of the aces should be set to 11.
 * Return: True represents yes, false represents no.
 */ 
public static boolean maximizeAces(ArrayList<Card> hand){
//total aces in hand  
int numAces = countAces(hand);
//represents ace(s) with one of them being an 11
int maxSum = 0;
//sum without aces
int sumNoAces = 0;
 
  //set sums according to number of aces
  if (numAces == 1){
    maxSum = 11;
  }
  
  else if (numAces == 2){
    maxSum = 12;   
  }
  
  else if (numAces == 3){
    maxSum = 13;
  }
  
  else {
    maxSum = 14;
  }

  //obtain sum without aces
  for (Card card: hand){
    if (card.getValue() == 1 || card.getValue() == 11){
      continue;  
    }
    else {
      sumNoAces += card.getValue();  
    }
  }

  /*does not protect against multiple aces in hand, taken care of in 
    printHand method */
  
  //set to minimum value
  if (sumNoAces + maxSum > 21){
    return false;
  }
  
  //set to maximum value
  else {
    return true;
  }
}

/*
 * Purpose: Count the number of aces in a hand.
 * Return: Number of aces in hand.
 */ 
public static int countAces(ArrayList<Card> hand){

int numAces = 0;
  
  for (Card card: hand){
    if (card.getValue() == 1 || card.getValue()==11){
      numAces++;  
    }
  }
    
  return numAces; 
}

/*
 * Purpose: Add up all the values in the hand.
 * Return: Sum of hand.
 */ 
public static int sumHand(ArrayList<Card> hand){
  
int sum = 0;
  
  for (Card card:hand){
   
    sum += card.getValue();
    
  }
  
  return sum;
  
}

//prints the CPU or player hand and sets aces to correct values.
public static void printHand(ArrayList<Card> hand, boolean isCPUHand){
  int acesIncremented = 0;
  //prints player hand
  if (!isCPUHand){
  
    System.out.println("Curent hand:");
  
    for(Card card: hand){
    
      System.out.println(card);
    
      //sets ace value appropriately
      if (card.getValue() == 1 || card.getValue()==11) {
      
      changeAces = maximizeAces(hand);
      
        if (changeAces == true && acesIncremented == 0) {
          card.setAceValue(11);
          acesIncremented++;
        }
      
        else {
          card.setAceValue(1);
        }
      }
    }
  }
  
  else {
    
    //prints CPU hand
    System.out.println("CPU hand:");
    
    for (int index = 0; index < hand.size(); index++){
      
      if (index == 0){
        System.out.println(hand.get(index));
      }
        
      else{
        System.out.println("Card: ???");
      }
      
      //sets ace value appropriately
      if (hand.get(index).getValue() == 1 || hand.get(index).getValue()==11) {
      
      changeAces = maximizeAces(hand);
      
        if (changeAces == true && acesIncremented == 0) {
          hand.get(index).setAceValue(11);
          acesIncremented++;
        }
      
        else {
          hand.get(index).setAceValue(1);
        }
      }     
    }      
  }    
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