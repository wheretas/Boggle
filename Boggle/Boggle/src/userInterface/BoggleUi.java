/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;
import Core.Board;
import Core.Die;
import boggle.Boggle;
import inputOutput.ReadDataFile;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.font.TextAttribute;
/**
 *
 * @author a608526
 */
public class BoggleUi 
{
    JFrame frame = new JFrame();
   
    private static BorderLayout layout;
    private ArrayList<Die> data = new ArrayList<Die>();
    private ArrayList wordList = new ArrayList();
    private ArrayList enteredWords = new ArrayList();
  //menu components
    private JMenuBar menuBar;
    private JMenu Boggle;
    private JMenuItem newGame;
    private JMenuItem Exit;
   //Layout of the Ui
    private JPanel currentWord;
    public JPanel diceLayoutPanel;
    private JPanel rightPanel;
    private JPanel inputWord;
    private static JTextPane textArea;                   
    private JScrollPane scrollPane;                          
    private JLabel timeLabel;      
    private JLabel wordLabel;
    private JLabel scoreLabel;
    private JButton shakeButton;
    private JButton submitWord;
    public JButton[] eachButton;
    private String enterWord = "";
    public String stringWords = "";
    public String charWords= "";
    int score;
    //Timer variables
    public Timer time;
    private long initialTimer = 0;
    private final int oneEighty = 30;
    

//    8. A constructor should be defined that receives a parameter of type Board class           
public BoggleUi(Board board, ArrayList x)
    {
        data = board.shakeDice();
        wordList = x;
        initComponents();
    }

//    9. A method initComponents should initialize all the components for the UI and called from the constructor
private void initComponents() 
    {	
     
    menuComp();
    diceLayoutPanel();
    wordsPanel();
    currentWord();

       frame.setVisible(true);
     
    }	
   

//wordPanel method will add the Jpanel objects
private void wordsPanel()
{
 rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Enter Words Found"));
        rightPanel.setMinimumSize(new Dimension(100, 700));
        rightPanel.setPreferredSize(new Dimension(100,400));
        frame.add(rightPanel, BorderLayout.CENTER);
        
textArea = new JTextPane();
       // textArea.setLineWrap(false);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(180, 330));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        rightPanel.add(scrollPane);
        
        
       
timeLabel = new JLabel();
        timeLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        timeLabel.setBorder(BorderFactory.createTitledBorder("Time Left"));
        timeLabel.setPreferredSize(new Dimension(240,100));
        timeLabel.setMinimumSize(new Dimension(240, 100));
        timeLabel.setMaximumSize(new Dimension(240, 100));
        time = new Timer(100, new timerListener());
        rightPanel.add(timeLabel, BorderLayout.PAGE_END);

shakeButton = new JButton("Shake Dice");
        shakeButton.setPreferredSize(new Dimension(240, 100));
        shakeButton.setMinimumSize(new Dimension(240, 100));
        shakeButton.setMaximumSize(new Dimension(240, 100));
          
        rightPanel.add(shakeButton);
       shakeButton.addActionListener(new shakeListener());
     
       
}
//menuComp method will add menu items
private void menuComp()
    {

 frame.setTitle("Boggle");		
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(660, 500);
       
       //create menubar, menu, submenu, and menuitems
          menuBar = new JMenuBar();
          Boggle = new JMenu("Boggle");
          Boggle.setMnemonic('B');
          menuBar.add(Boggle);
          
        newGame = new JMenuItem("New Game");	
        newGame.setMnemonic('N');
        newGame.addActionListener(new newGameListener());
       
        Boggle.add(newGame);

        Exit = new JMenuItem("Exit");
        Exit.setMnemonic('E');
        Exit.addActionListener(new ExitListener());
         Boggle.add(Exit);

 frame.setJMenuBar(menuBar);

}
//diceLayoutPanel will create the buttons and add them to the panel
 public void diceLayoutPanel() 
        {

   
        diceLayoutPanel = new JPanel(new GridLayout(4,4));
        diceLayoutPanel.setMinimumSize(new Dimension(400, 400));
        diceLayoutPanel.setPreferredSize(new Dimension(400, 400));
        diceLayoutPanel.setBorder(BorderFactory.createTitledBorder("Boggle Board"));
        frame.add(diceLayoutPanel, BorderLayout.WEST);
      
          eachButton = new JButton[16];
          for(int x = 0; x < data.size(); x++)
            {
            
           eachButton[x] = new JButton(data.get(x).getLetter()); 
           eachButton[x].addActionListener(new buttonClick());
           diceLayoutPanel.add(eachButton[x]);
          
        
            }
        
        }
 //submitWord method will create new JPanel and add the items to it
    private void currentWord()
    {
        inputWord = new JPanel();
     
        inputWord.setBorder(BorderFactory.createTitledBorder("Current Word"));
        inputWord.setMinimumSize(new Dimension(50, 80));
        inputWord.setPreferredSize(new Dimension(50,80));
        frame.add(inputWord, BorderLayout.SOUTH);
        
        wordLabel = new JLabel();
        wordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        wordLabel.setBorder(BorderFactory.createTitledBorder("Current Word"));
        wordLabel.setPreferredSize(new Dimension(310,50));
        wordLabel.setMinimumSize(new Dimension(310, 50));
        wordLabel.setMaximumSize(new Dimension(310, 50));
        inputWord.add(wordLabel, BorderLayout.WEST);
        
        
        submitWord = new JButton("Submit Word");
        submitWord.setPreferredSize(new Dimension(200, 50));
        submitWord.setMinimumSize(new Dimension(200, 50));
        submitWord.setMaximumSize(new Dimension(200, 50));
        submitWord.addActionListener(new submitListener());  
       inputWord.add(submitWord, BorderLayout.CENTER);
       
        scoreLabel = new JLabel();
        scoreLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        scoreLabel.setBorder(BorderFactory.createTitledBorder("Score"));
        scoreLabel.setPreferredSize(new Dimension(100,50));
        scoreLabel.setMinimumSize(new Dimension(100, 50));
        scoreLabel.setMaximumSize(new Dimension(100, 50));
        inputWord.add(scoreLabel, BorderLayout.EAST);
        
        
        
    }

//action listener for the "submit button"
  class submitListener implements ActionListener
    {
     
        public void actionPerformed(ActionEvent e)
        {
            
            int wordsFlag = 0;
          int flag =0 ;

          
          if(enteredWords.contains(stringWords))
              {
                  wordsFlag = 1  ;
                try {
                    textArea.getStyledDocument().insertString(0,"*Word Already Entered, \n",null);
                    // System.out.println("duplicate");
                } catch (BadLocationException ex) {
                    Logger.getLogger(BoggleUi.class.getName()).log(Level.SEVERE, null, ex);
                }
              }
          
          
        
            for(int i = 0; i < wordList.size(); i++)
            {
                
                
       
                if(stringWords.equalsIgnoreCase(wordList.get(i).toString()) && wordsFlag == 0)
          {           
               
              
              score++;
                SimpleAttributeSet sas = new SimpleAttributeSet(); 
                StyleConstants.setBold(sas, true);
                    try {
                        textArea.getStyledDocument().insertString(0,stringWords +"\n", sas);
                    } catch (BadLocationException ex) {
                        Logger.getLogger(BoggleUi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                enteredWords.add(stringWords);
                scoreLabel.setText("" + score);
                
                scoreLabel.revalidate();
                 
                 for(int j= 0; j<16; j++)
                 {
                     eachButton[j].setEnabled(true);
                   eachButton[j].setContentAreaFilled(true);
                   eachButton[j].setBackground(null);
                    wordLabel.setText(" ");
                    
                    
                }
                 flag = 1;
               
            }   
            else
                {
                
               
               
                for(int k= 0; k<16; k++)
                    {
                     eachButton[k].setEnabled(true);
                    eachButton[k].setContentAreaFilled(true);
                    eachButton[k].setBackground(null);
                     wordLabel.setText(" ");
                    }      
                }
            }
          
            
            if(flag == 1 )
                    System.out.println("word found");
                else{
                System.out.println("not found");
                  // int caret = textArea.getCaretPosition();
                try {
                    textArea.getStyledDocument().insertString(0 ,"*Word Not Found*\n", null);
                } catch (BadLocationException ex) {
                    Logger.getLogger(BoggleUi.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                }
            
            
            stringWords = "";
            wordLabel.setText("");
           
        }	
    }

//action listener for the exit menu item
  class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int response = JOptionPane.showConfirmDialog(null, "Confirm to exit Boggle?", 
                    "Exit?", JOptionPane.YES_NO_OPTION);
            
            if (response == JOptionPane.YES_OPTION)
                System.exit(0);	
        }	
    }

//action listener for the new game menu item
class newGameListener implements ActionListener
    {

    public void actionPerformed(ActionEvent e){
        time.stop();
    frame.getContentPane().removeAll();
    frame.getContentPane().setBackground(Color.yellow);
    frame.revalidate();
    enteredWords.clear();
    initComponents();
  
        System.out.println("you click a button");
        
    }}

////action listener for the "shake dice" button
 class shakeListener implements ActionListener 
    {	
     
       
     public void actionPerformed(ActionEvent e)
        {	
        
   //     diceLayoutPanel();
//            loop();
//            
        System.out.println("you click the button");
//    System.out.print(data);
            
            diceLayoutPanel.removeAll();
            diceLayoutPanel();
            rightPanel.revalidate();
            rightPanel.repaint();
            //frame.add(diceLayoutPanel);
            Collections.shuffle(data);
            shakeButton.setEnabled(false);
            scoreLabel.removeAll();
            wordLabel.setText(" ");
            rightPanel.revalidate();
           // frame.add(diceLayoutPanel);
            initialTimer = System.currentTimeMillis();
            score = 0;
            enteredWords.clear();
            time.start();
           
        }	

    }
//thread for the stopclock timer
private class timerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {                
            long clock = System.currentTimeMillis();
            int elapseTime = (int) (clock - initialTimer);
            
            String completeTime = "   3:00  ";
             if (elapseTime < oneEighty * 1000)
            {
                completeTime = Time(oneEighty * 1000 - elapseTime);
                timeLabel.setText("   " + completeTime + "   ");
            }
            else
            {    
                time.stop();
                for(int i = 0; i < 16; i++)
                    eachButton[i].setEnabled(false);
                
                JOptionPane.showMessageDialog(null,
            "Computer is comparing words...", "Computer Verification",
            JOptionPane.INFORMATION_MESSAGE);
                compareWords();
            }
  
        }      
    }

private String Time(int counting)
    {
        int sec = counting / 1000;
        int min = sec / 60;
        sec = sec % 60;
 
        String totalTime = (min + ":");
        if (sec < 10){
          totalTime= totalTime + "0" + sec;
        }
        else{
          totalTime = totalTime + sec;
        }
        return totalTime;
    }
//action listener for each of the 16 buttons
private class buttonClick implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           
            for ( int g = 0 ; g < 16 ; g++)
                eachButton[g].setEnabled(false);
           
            charWords = e.getActionCommand();
            stringWords += charWords;
            
          //  System.out.println(charWords);
            System.out.println(stringWords);
            
            
            
            
                
                 String actionCom=e.getActionCommand();
        wordLabel.setText(wordLabel.getText() + actionCom);
         
            
           
            if(e.getSource() == eachButton[0])
                {
                    eachButton[1].setEnabled(true);
                    eachButton[4].setEnabled(true);
                    eachButton[5].setEnabled(true);
                    eachButton[0].setContentAreaFilled(true);
                    eachButton[0].setBackground(Color.PINK);
                   
                }
                   
            else if(e.getSource() == eachButton[1])
                {
                    eachButton[0].setEnabled(true);
                    eachButton[2].setEnabled(true);
                    eachButton[4].setEnabled(true);
                    eachButton[5].setEnabled(true);
                    eachButton[6].setEnabled(true);
                    eachButton[1].setContentAreaFilled(true);
                    eachButton[1].setBackground(Color.PINK);
                }
                   
            else if(e.getSource() == eachButton[2])
                {
                    eachButton[1].setEnabled(true);
                    eachButton[3].setEnabled(true);
                    eachButton[5].setEnabled(true);
                    eachButton[6].setEnabled(true);
                    eachButton[7].setEnabled(true);
                    eachButton[2].setContentAreaFilled(true);
                    eachButton[2].setBackground(Color.PINK);
                }
                   
            else if(e.getSource() == eachButton[3])
                {
                    eachButton[2].setEnabled(true);
                    eachButton[6].setEnabled(true);
                    eachButton[7].setEnabled(true);
                    eachButton[3].setContentAreaFilled(true);
                    eachButton[3].setBackground(Color.PINK);
                }
                   
            else if(e.getSource() == eachButton[4])
                {
                    eachButton[0].setEnabled(true);
                    eachButton[1].setEnabled(true);
                    eachButton[5].setEnabled(true);
                    eachButton[8].setEnabled(true);
                    eachButton[9].setEnabled(true);
                    eachButton[4].setContentAreaFilled(true);
                    eachButton[4].setBackground(Color.PINK);
                }
           
            else if(e.getSource() == eachButton[5])
                {
                    eachButton[0].setEnabled(true);
                    eachButton[1].setEnabled(true);
                    eachButton[2].setEnabled(true);
                    eachButton[4].setEnabled(true);
                    eachButton[6].setEnabled(true);
                    eachButton[8].setEnabled(true);
                    eachButton[9].setEnabled(true);
                    eachButton[10].setEnabled(true);
                    eachButton[5].setContentAreaFilled(true);
                    eachButton[5].setBackground(Color.PINK);
                }
           
            else if(e.getSource() == eachButton[6])
                {
                    eachButton[1].setEnabled(true);
                    eachButton[2].setEnabled(true);
                    eachButton[3].setEnabled(true);
                    eachButton[5].setEnabled(true);
                    eachButton[7].setEnabled(true);
                    eachButton[9].setEnabled(true);
                    eachButton[10].setEnabled(true);
                    eachButton[11].setEnabled(true);
                    eachButton[6].setContentAreaFilled(true);
                    eachButton[6].setBackground(Color.PINK);
                }
           
            else if(e.getSource() == eachButton[7])
                {
                    eachButton[2].setEnabled(true);
                    eachButton[3].setEnabled(true);
                    eachButton[6].setEnabled(true);
                    eachButton[10].setEnabled(true);
                    eachButton[11].setEnabled(true);
                    eachButton[7].setContentAreaFilled(true);
                    eachButton[7].setBackground(Color.PINK);
                }
           
            else if(e.getSource() == eachButton[8])
                {
                    eachButton[4].setEnabled(true);
                    eachButton[5].setEnabled(true);
                    eachButton[9].setEnabled(true);
                    eachButton[12].setEnabled(true);
                    eachButton[13].setEnabled(true);
                    eachButton[8].setContentAreaFilled(true);
                    eachButton[8].setBackground(Color.PINK);
                }
           
            else if(e.getSource() == eachButton[9])
                {
                    eachButton[4].setEnabled(true);
                    eachButton[5].setEnabled(true);
                    eachButton[6].setEnabled(true);
                    eachButton[8].setEnabled(true);
                    eachButton[10].setEnabled(true);
                    eachButton[13].setEnabled(true);
                    eachButton[12].setEnabled(true);
                    eachButton[14].setEnabled(true);
                    eachButton[9].setContentAreaFilled(true);
                    eachButton[9].setBackground(Color.PINK);
                }
           
            else if(e.getSource() == eachButton[10])
                {
                    eachButton[5].setEnabled(true);
                    eachButton[6].setEnabled(true);
                    eachButton[7].setEnabled(true);
                    eachButton[9].setEnabled(true);
                    eachButton[11].setEnabled(true);
                    eachButton[13].setEnabled(true);
                    eachButton[14].setEnabled(true);
                    eachButton[15].setEnabled(true);
                    eachButton[10].setContentAreaFilled(true);
                    eachButton[10].setBackground(Color.PINK);
                }
           
            else if(e.getSource() == eachButton[11])
                {
                    eachButton[6].setEnabled(true);
                    eachButton[7].setEnabled(true);
                    eachButton[10].setEnabled(true);
                    eachButton[14].setEnabled(true);
                    eachButton[15].setEnabled(true);
                    eachButton[11].setContentAreaFilled(true);
                    eachButton[11].setBackground(Color.PINK);
                }
           
            else if(e.getSource() == eachButton[12])
                {
                    eachButton[8].setEnabled(true);
                    eachButton[9].setEnabled(true);
                    eachButton[13].setEnabled(true);
                    eachButton[12].setContentAreaFilled(true);
                    eachButton[12].setBackground(Color.PINK);
                }
           
            else if(e.getSource() == eachButton[13])
                {
                    eachButton[8].setEnabled(true);
                    eachButton[9].setEnabled(true);
                    eachButton[10].setEnabled(true);
                    eachButton[12].setEnabled(true);
                    eachButton[14].setEnabled(true);
                    eachButton[13].setContentAreaFilled(true);
                    eachButton[13].setBackground(Color.PINK);
                }
           
            else if(e.getSource() == eachButton[14])
                {
                    eachButton[9].setEnabled(true);
                    eachButton[10].setEnabled(true);
                    eachButton[11].setEnabled(true);
                    eachButton[13].setEnabled(true);
                    eachButton[15].setEnabled(true);
                    eachButton[14].setContentAreaFilled(true);
                    eachButton[14].setBackground(Color.PINK);
                }
                   
            else if(e.getSource() == eachButton[15])
                {
                    eachButton[10].setEnabled(true);
                    eachButton[11].setEnabled(true);
                    eachButton[14].setEnabled(true);
                    eachButton[15].setContentAreaFilled(true);
                    eachButton[15].setBackground(Color.PINK);
                }
        }
    }  


private void compareWords(){

System.out.print("checking...\n" + enteredWords);

System.out.println(enteredWords.size());




Random rand = new Random();
int  n = rand.nextInt(enteredWords.size()) + 1;
String words = null;
//System.out.println("This is the random number computer chose:" + n); !!!!


 Random random = new Random(); // Make this an instance variable instead.
String[] x = (String[]) enteredWords.toArray(new String[0]);
 for(int i=0; i < n;i++){
     
     
     
    
 //words = enteredWords.get(random.nextInt(enteredWords.size())).toString();
  // System.out.println(x[i]); !!!!!!
 textArea.setContentType( "text/html" );
 
//    try {
//        textArea.getStyledDocument().insertString(0 ,("<strike>" + x[i])+ "\n", null);
//    } catch (BadLocationException ex) {
//        Logger.getLogger(BoggleUi.class.getName()).log(Level.SEVERE, null, ex);
//    }
      //  textArea.getStyledDocument().insertString(0 ,"*Word Not Found*\n", null);
textArea.setText("<strike>" + (x[i]));
//score--;
 }
score--;
  scoreLabel.setText("" + score);
        
//+ "</strike> Words not to Strike"
}
 
 }



// compare words correctly