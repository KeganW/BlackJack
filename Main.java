/*
 * Author: Kegan Wong
 * Date: 08/28/2019
 * Purpose: Used to run the game of Black Jack and utilize the card, deck and 
 *          hand classes. Program intented to also refresh java programming 
 *          language for next quarter.
 * File: Main.java
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main{

//values in the arrays are used to pass into the card constructor
private static final String[] suites = {"Clubs", "Diamonds", "Hearts", "Spades"};
private static final int[] values = {1,2,3,4,5,6,7,8,9,10,10,10,10};
private static final String[] names = {"Ace", "Two", "Three", "Four", "Five", "Six", 
                                         "Seven", "Eight", "Nine", "Ten", 
                                            "Jack", "King", "Queen"};
//checks winner only when player decides to reveal cards
private static boolean checkWinner = false;
//status on whether the CPU will take another card
private static boolean determineCPUMove = false;
//stores user input on their next choice of action
private static int input = 0;
//deck index
private static int cardsLeft = 51;
//scoring system
private static int sumPlayer = 0;
private static int sumCPU = 0;
//tracks current cards in possession 
private static ArrayList<Card> playerHand = new ArrayList<Card>(11);
private static ArrayList<Card> CPUHand = new ArrayList<Card>(11);
  
public static void main(String[] args){
//introduction output
System.out.println("Get ready to play some Black Jack!");
System.out.println("Setting up deck...\n");

//used to pass in deck constructor
ArrayList<Card> deck = new ArrayList<Card>(52);

//create deck
for (int num = 0; num<13; num++){
  for (int type = 0; type<4; type++){
    
    //create card object
    Card card = new Card(suites[type], names[num], values[num]);
    //add to temporary deck
    deck.add(card);
    
  }
}

//create new deck and shuffle it
Deck casinoDeck = new Deck(deck);
Deck.shuffle();

//Distribute initial two cards to both player and CPU
playerHand.add(casinoDeck.getDeck().get(cardsLeft));
playerHand.add(casinoDeck.getDeck().get(--cardsLeft));

CPUHand.add(casinoDeck.getDeck().get(--cardsLeft));
CPUHand.add(casinoDeck.getDeck().get(--cardsLeft));

//loop until there is a loser or winner 
while (true){
  //print user's current hand and sum
  Hand.dealHand(playerHand, false); 
  sumPlayer = Hand.sumHand(playerHand);
  System.out.println("Current sum: " + sumPlayer);
  
  
  System.out.println("////////////");
    
  //print only one card from the CPU's hand
  Hand.dealHand(CPUHand,true);
  sumCPU = Hand.sumHand(CPUHand);
    
  //check for loser
  if(Hand.checkLosingHand(sumPlayer, sumCPU)){
    break;
  }
  
  //compares scores, whoever is closer to 21 wins   
  else if (checkWinner){
    Hand.checkWinningHand(sumPlayer, sumCPU);
    break;
  }
    
  //asks for users next choice of action
  Scanner scan = new Scanner(System.in);
  System.out.println("\nChoices:");
  System.out.println("Hit me! [1]");
  System.out.println("Reveal cards! [2]");
  input = scan.nextInt();
   
  //gives player another card and CPU if necessary
  if(input == 1) {
       
    playerHand.add(casinoDeck.getDeck().get(--cardsLeft));
    determineCPUMove = Hand.CPUMovesAgain(CPUHand);
      
    if (determineCPUMove == false) {
      System.out.println("\nCPU is content with its cards.\n");
    }
    
    else{
       System.out.println("\nCPU decided to take another card.");
       CPUHand.add(casinoDeck.getDeck().get(--cardsLeft));
    }   
  }
  
  //checks until the CPU is satisfied with its cards, then checks for winner
  else if (input==2){
     
    while(true){
      
    determineCPUMove = Hand.CPUMovesAgain(CPUHand);
       
      if (determineCPUMove == true) {
        System.out.println("\nCPU decided to take another card.");
        CPUHand.add(casinoDeck.getDeck().get(--cardsLeft));
        sumCPU = Hand.sumHand(CPUHand);     
      }
       
      else if (determineCPUMove == false) {
        checkWinner = true;
        break;
      } 
    }
  }
  
  //error message to invalid input
  else {
    System.out.println("You must enter 1 or 2 only.");
  }
}
}
}