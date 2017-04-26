/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Wheretas
 */
public class Die {
    
//    Create constant field:
//1.	NUMBER_OF_SIDES equal to value 6
    static int NUMBER_OF_SIDES = 6;
//Create member variable of type:
//1.	ArrayList  // stores dice data for the sides
    ArrayList diceData = new ArrayList();
//2.	String // stores the current letter of each die
    String currentLetter= "" ;
   
//Create method randomLetter with return type void and an empty parameter list; the method should do the following:
    
    public void randomLetter()
    {
        //1.	Declare an variable of type class Random
       Random randNum = new Random();
        //2.	Generate a random number based on the seed value of the number of die sides
       int value = randNum.nextInt(NUMBER_OF_SIDES);
       
       
        //3.	Set member variable representing the current letter to the data stored at the index of the random number
       currentLetter = diceData.get(value).toString() ;
      
    }


//Create method getLetter with return type String and an empty parameter list; the method should do the following:

    public String getLetter()
        {
         //1.	Call method randomLetter
            this.randomLetter();
         //2.	Return the letter associated with the letter based on the random number 
        
        return currentLetter;
        
        }

//Create method addLetter with a return type of void and one parameter of type String representing one of the six letters on the die; the method should:
    public void addLetter(String oneLetter)
    {
    //1.	Add the passed in value to the ArrayList representing the letters on the die
        //this.currentLetter    
        diceData.add(oneLetter) ;
       
    }


//Create method displayAllLetters with a return type of void and an empty parameter list; the method should:
    public void displayAllLetters()
        {
    //1.	Loop through all sides of the die and display the data
        
        try
            {
        for(int i =0; i < NUMBER_OF_SIDES; i++)
                {
        System.out.print( diceData.get(i) );
        System.out.print(" ");
                }
            }
        catch(Exception ex)
                {
                
                System.out.printf("ERROR %s", ex.toString());
                }
        
        
        }
    }



