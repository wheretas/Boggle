
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boggle;


import Core.Board;
import inputOutput.ReadDataFile;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import userInterface.BoggleUi;


 /*
 * @author Wheretas
 */
public class Boggle {
    static ArrayList<String> mainData= new ArrayList<>();
    static String inputText = "BoggleData.txt";
 static ArrayList<String> tempData= new ArrayList<>();
  static String tempText= "TemporaryDictionary.txt";
    
   
   
/**
    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException
     * @throws java.net.URISyntaxException
     */
 public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        // TODO code application logic here

//1.	Create an instance of class ReadDataFile passing the file name variable as an argument
        ReadDataFile readText = new ReadDataFile(inputText);
        ReadDataFile readTextDict = new ReadDataFile(tempText);
//2.	Call method populateData() from class ReadDataFile
     readText.populateData();
    readTextDict.dictionaryData();
     
     
//3.	Set member variable of type ArrayList equal to method call getData() from class ReadDataFile
     mainData = readText.getData();
    tempData = readTextDict.getDictData();
     //System.out.print(mainData);
  
 //4.	Create an instance of class Board passing the file data in the ArrayList as an argument
      Board board = new Board(mainData);
    //  Board board1 = new Board(tempData);
     
//5.	Call method shakeDice() from class Board
       // board.shakeDice();
    
//6.    Add creating an instance of class BoggleUi passing the instance of class Board as an argument to the Boggle Ui constructor
       BoggleUi bogglegui= new BoggleUi(board, tempData );
       
        //System.out.print(board.shakeDice());
        

     
     
     
     
    }
    
    
    
}




