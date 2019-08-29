/*
 * Author: Kegan Wong
 * Date: 08/28/2019
 * Purpose: Represents an individual card.
 * File: Card.java
 */

public class Card{
//represents the type of card
private final String suite;
//represents the name of the card
private final String name;
//represents the value of the card
private int value;

//constructor
public Card(String suite, String name, int value){
   this.suite = suite;
   this.name = name;
   this.value = value;    
}

/*
 * Purpose: Print the cards in an understandable format.
 * Return: String of the card which displays the name and suite.
 */ 
public String toString(){

   return "Card: " + getName() + " of " + getSuite();
  
}

//getter for card's value
public int getValue(){
  
   return value;
   
}

//getter for card's name
public String getName(){
  
   return name;
   
}

//getter for card's suite
public String getSuite(){
   
  return suite;
   
}

//setter for only ace cards
public void setAceValue(int value){
  
  this.value = value;
  
}

}


