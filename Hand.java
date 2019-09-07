/*
 * Author: Kegan Wong
 * Date: 08/29/2019
 * Purpose: Represents a hand that consists of two or more cards.
 * File: Hand.java
 */

import java.util.ArrayList;

public class Hand{
  
//used to determine whether the ace(s) has a value of 1 or 11 based on hand
private static boolean changeAces = false;


/*
 * Purpose: Check if any given hand has a sum over 21
 * Return: True represents a call to end the game, false represents continue
 */
public static boolean checkLosingHand(int sumPlayer, int sumCPU){

//hold return value on whether to continue game
boolean gameOver;
  
  //find loser according to sums, if any
  if (sumPlayer > 21 && sumCPU > 21){    
    System.out.println("\nGame over. Everyone lost.");
    System.out.println("You had a sum of " + sumPlayer);
    System.out.println("CPU had a sum of " + sumCPU);
    gameOver = true;

  }
  
  else if (sumPlayer > 21) {
     System.out.println("\nGame over. You lost.");
     System.out.println("You had a sum of " + sumPlayer);
     System.out.println("CPU had a sum of " + sumCPU); 
     gameOver = true;

  }
   
  else if (sumCPU > 21) {
     System.out.println("\nGame over. CPU lost.");
     System.out.println("You had a sum of " + sumPlayer);
     System.out.println("CPU had a sum of " + sumCPU);
     gameOver = true;
  }
  
  else {
    gameOver = false;
  }
  
  return gameOver;
  
}


/*
 * Purpose: Determines if CPU will ask for another card.
 * Return: True represents yes, false represents no.
 */ 
public static boolean CPUMovesAgain(ArrayList<Card> hand){
  
//stores sum of CPU's current hand
int sum = 0;
  //obtain sum of CPU hand and check for aces
  sum = sumHand(hand);
  //determines CPU decision based on sums and possession of ace
  if ((sum >= 15 && countAces(hand) == 0) || (sum>=18 && countAces(hand) > 0)) {  
    return false;
  }
  
  else if (sum <=17 && countAces(hand) > 0) {
    return true;
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
  
  //determine max sum in terms of purely aces
  switch (numAces){
    case 1:
      maxSum = 11;
      break;
    case 2:
      maxSum = 12;
      break;
    case 3:
      maxSum = 13;
      break;
    case 4:
      maxSum = 14;
      break;
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

  /* does not protect against multiple aces in hand, taken care of in 
     dealHand method */
  
  //set to minimum value
  if (sumNoAces + maxSum > 21){
    return false;
  }
  
  //set to maximum value
  else {
    return true;
  }
}


//sets value of aces so that the sum of the hand is closest to 21
public static boolean setMaximumAces(ArrayList<Card> hand, Card card){
  
  //only deals with aces
  if (card.getValue()==1 || card.getValue()==11){
    
    changeAces = maximizeAces(hand);

    //sets value of ace appropriately
    if (changeAces == true){
      card.setAceValue(11);
      return true;
    }
  
    else {
      card.setAceValue(1);
      return false;
    }
  }
  //for other cards that are not aces
  return false;
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


//prints the CPU or player hand and sets aces to optimal values.
public static void dealHand(ArrayList<Card> hand, boolean isCPUHand){
 
 //safeguards against multiple aces being maximized 
 boolean oneAceIncremented = false;
 
  //prints player hand
  if (!isCPUHand){
  
    System.out.println("Curent hand:");
  
    for(Card card: hand){
      
      System.out.println(card);     
      //ensures only one ace is maximized if necessary
      if (!oneAceIncremented){
        oneAceIncremented = setMaximumAces(hand,card);   
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
      
      //ensures only one ace is maximized if necessary
      if (!oneAceIncremented){
        oneAceIncremented = setMaximumAces(hand,hand.get(index));
      }  
    }      
  }    
}


//determines the winner based on who is closer to 21
public static void checkWinningHand(int sumPlayer, int sumCPU){
    
    //determine winner based on sums
    if (sumCPU > sumPlayer && sumCPU <= 21) {
      System.out.println("\nCPU wins with a sum of " + sumCPU);
      System.out.println("You had a sum of " + sumPlayer);     
    }
    
    else if (sumPlayer > sumCPU && sumPlayer <=21) {
      System.out.println("\nYou win with a sum of " + sumPlayer);
      System.out.println("CPU had a sum of " + sumCPU);    
    }
    
    else {
      System.out.println("\nDraw with both sums being " + sumPlayer);    
    }  
}
}
