/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.util.ArrayList;


/**
 *
 * @author Wheretas
 */
public class Board {
    
//    Create constant fields:
//1.	NUMBER_OF_DICE equal to value 16
    private final int NUMBER_OF_DICE = 16;
//2.	NUMBER_OF_SIDES equal to value 6
    private final int NUMBER_OF_SIDES = 6;
//3.	GRID equal to value 4
    private final int GRID = 4;
//Create member variable of type:
//1.	ArrayList  // stores all dice data
    ArrayList storage = new ArrayList();
//2.	ArrayList<Die> // stores 16 dice objects
    ArrayList<Die> sixteen = new ArrayList<>();
//Create constructor with one parameter of type ArrayList; set the member variable of type ArrayList that stores all the Boggle data equal to the parameter in the method signature
    public Board(ArrayList data)
        {
         storage = data; 
        }

    
  

   
//Create method populateDice with return type void and an empty parameter list; the method should do the following:
   public void populateDice()
        {
         //1.	 Declare an variable of type class Die
           Die die ;
         
           int q =0;
         //2.	Loop through the 16 dice:
           for(int i=0; i < NUMBER_OF_DICE; i++)
           {
             //a.	Create an instance of class Die using the no-argument constructor
            die  = new Die(); 
             //b.	Loop through the 6 sides of the die:
            
            for(int j = 0; j < NUMBER_OF_SIDES; j++)
                {
                //i.	Add each of the 6 letters to the die ArrayList representing the die letters by calling method addLetter in class Die 
               die.addLetter(storage.get(q).toString());
           q++;
                }
           
             //c.	Display the letters of each die by calling method displayAllLetters() in class Die on a separate row
           
          // System.out.print("Die " + i + ": " );
          // die.displayAllLetters();
          // System.out.println(); 
           
           //3.	Add each die instance to the ArrayList declared specifically for class Die
           sixteen.add(die);
             
               
           }
       
        }
    

    //Create method shakeDice with return type ArrayList and an empty parameter list; the method should do the following:
    public ArrayList<Die> shakeDice()
        {
    //0.	Call method populateDice()
    int counter = 0;
    populateDice();
            
    
   // System.out.println("\nBoggle Board");
   // System.out.println();
    //1.	Loop through the 16 dice, for each Die:
    for(Die die : sixteen)
            {
        //a.	Call method getLetter in class Die
           
               String currentLetter =  die.getLetter();
               counter++;
               //System.out.print(die.getLetter() + " ");
                
                
           
           // b. Display the current letter of each Die in a 4 X 4 grid   
            if(counter % GRID == 0)
                    {
                        System.out.println();
                     }

            }
   // c.	Return the ArrayList of Boggle dice with each letter set 
    
    return sixteen; 
        }
    
}
