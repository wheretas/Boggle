/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;
import boggle.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wheretas
 */
public class ReadDataFile {
    
//    Define class member variables using the specified data types:
//1.	Scanner // for reading the file 
    Scanner input; 
    Scanner input2;
//2.	String  // for storing the file name
    String fileName = null;
   String dicFileName = null;
//3.	ArrayList  // for storing the data from the file
    ArrayList data = new ArrayList();
   ArrayList data2 = new ArrayList();
//Create constructor with one parameter of type String representing the name of the data file
   public ReadDataFile(String x )
  
       {
          
           String name = x;
           String tempName = x;
          //1.	Set local variable of type String to the value passed in
           fileName =  name;
           dicFileName = tempName;
           
         
        } 
   


   //Create method populateData with return type void and an empty parameter list; it should do the following:
public void populateData() throws MalformedURLException, URISyntaxException
        {
        try 
            {
            //1.	Create an instance of class URL using the file name of the data file
           URL url = getClass().getResource(fileName);
            //2.	Create an instance of class File using the URL created above
            File file = new File(url.toURI());
            
            //3.	Initialize member variable of type Scanner based on the File instance created above
            input = new Scanner(file);
           
            //4.	Loop through the data file until the end
            while (input.hasNext()) //input.hasnextline
            {
                //a.	Add to the ArrayList representing the data in the file each value read from the data file
                 
                data.add(input.next()); //input.nextline
                
                        
                        }
            } 
       // catch (FileNotFoundException ex) 
        catch(URISyntaxException | FileNotFoundException ex)
            {
            //Logger.getLogger(ReadDataFile.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
                
            }
        finally
            {
            input.close();
            }
     
        }

//Create method getData with return type ArrayList and an empty parameter list that returns the ArrayList with the data from the data file
public ArrayList getData()
    {
        return data;
    }

public ArrayList getDictData()
    {
        return data2;
    }



    public void dictionaryData() {
        try 
            {
            //1.	Create an instance of class URL using the file name of the data file
           URL url = getClass().getResource(dicFileName);
            //2.	Create an instance of class File using the URL created above
            File file = new File(url.toURI());
            
            //3.	Initialize member variable of type Scanner based on the File instance created above
            input2 = new Scanner(file);
           
            //4.	Loop through the data file until the end
            while (input2.hasNext()) //input.hasnextline
            {
                //a.	Add to the ArrayList representing the data in the file each value read from the data file
                 
                data2.add(input2.next()); //input.nextline
                
                        
                        }
            } 
       // catch (FileNotFoundException ex) 
        catch(URISyntaxException | FileNotFoundException ex)
            {
            //Logger.getLogger(ReadDataFile.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
                
            }
        finally
            {
            input2.close();
            }

}
  }
     
        
