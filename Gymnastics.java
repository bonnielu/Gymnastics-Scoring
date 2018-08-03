/* Gymnastics.java
 * Scores and ranks the competitors in a gymnastics competition
 * Janurary 16, 2018
 * Bonnie Lu
 */

import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer; 

public class Gymnastics {
  
  static Scanner scan = new Scanner(System.in);  
  
  public static void main (String[]args) throws Exception {
    Scanner scan = new Scanner(System.in);
    
    //Task 1
    
    int numgymnasts = 0; //Declaring integer variable named numgymnasts 
    int numjudges=0; //Declaring integer variable name numjudges 
    int rounds=0; ////Declaring integer variable name rounds
    int inputs = 0; 
    double sum; //Declaring variable sum 
    double max; 
    double min;
    
    //Introduction, explaining rules of the game
    System.out.println ("GYMNASTICS SCORING\n");
    System.out.println ("Welcome to the scoring application for gymnastics.");
    System.out.println ("The rules of the game are: there must be a minimum of three judges. Each judge will award a score for the execution and difficulty. The execution score cannot excceed 10, while the difficulty score must be at least 0."); 
    
    System.out.println ("\nEnter the number of judges judging in the competition.");
    
    do { //Ensures that the rules of a "minimum of three judges" are fulfilled 
      if (scan.hasNextInt()) //Ensurse that the program does not crash when a double or string value is entered 
      {
        numjudges = scan.nextInt(); //Get user input for the the number of judges
        scan.nextLine();
        if (numjudges < 3) //Rules that a minimum of 3 judges must be judging at the panel 
        {
          System.out.println ("Invalid number of judges. There must be a minimum of 3 judges on the panel. Please enter a valid number.");
        }
      }
      else
      {
        scan.next();
        System.out.println ("Enter a valid number. The number of judges must be a positive integer greater than 3.");
      }
    } while (numjudges < 3); 
    
    System.out.println ("\nEnter the number of rounds that the gymnast will be partaking in the competition."); //Getting user input for the number of rounds 
    
    do {
      
      if (scan.hasNextInt())
      {
        rounds = scan.nextInt();
        scan.nextLine(); 
        if (rounds <= 0)
        {
          System.out.println ("There must be at least one round taking place in the competition.");
        }
      }
      else 
      {
        scan.next();
        System.out.println ("Enter a valid number. The number of rounds must be a positive integer, greater than 0.");
      }                
    }while (rounds <=0);
    
    do {
      System.out.println ("\nDo you want to input the gymnasts' information through keyboard or through a file? ");
      System.out.println ("1. Input through keyboard");
      System.out.println( "2. Input through file");
      
      if (scan.hasNextInt())
      {
        inputs = scan.nextInt();
        scan.nextLine();
        if (inputs != 1 && inputs!= 2)
        {
          System.out.println ("Invalid number. Please enter 1 or 2."); 
        }
      }
      else 
      {
        scan.next();
        System.out.println ("Invalid number");
      }
    } while (inputs!= 1 && inputs!=2);
    
    if (inputs == 1)
    {
      
      System.out.println ("\nEnter the number of gymnasts in the competition.");
      
      do { //Ensures that at least one gymnast is participating in the competition
        
        if ((scan.hasNextInt()))
        {
          numgymnasts = scan.nextInt();
          scan.nextLine(); 
          if (numgymnasts <= 0)
          {
            System.out.println ("Invalid number of competitors. There must be at least 1 gymnast participating in the competition.");
          }
        }
        else 
        {
          scan.next();
          System.out.println ("Enter a valid number. The number of gymnasts must be a positive integer, greater than 0.");
        }
      } while (numgymnasts <=0); 
    }
    else if (inputs == 2)
    {
      int lines = 0; 
      System.out.println ("\nEnter the file name with the extention"); //Prompting user to enter the file name with the extention
      Scanner input = new Scanner(System.in);
      File reading = new File (input.nextLine());
      BufferedReader reader = new BufferedReader(new FileReader(reading)); 
      
      while (reader.readLine()!= null) lines++;
      reader.close(); 
      numgymnasts = lines/2; 
      
      System.out.println ("\nThere are " + numgymnasts + " gymnasts participating in the competition.");
    } 
    double total[] = new double[numgymnasts]; //Declaring array for the total score of each competitor 
    String fnames[] = new String[numgymnasts];//Declaring array for first names of each competitor 
    String lnames[] = new String[numgymnasts]; //Declaring array for the last names of each competitor 
    String schools[] = new String[numgymnasts]; 
    double execution[] = new double[numjudges]; //Declaring array for the execution score of each competitor from each judge
    double difficulty[] = new double[numjudges]; //Declaring array for the difficulty score of each competitor from each judge
    
    if (inputs == 1)
    {
      for (int i = 0; i < numgymnasts; i++)
      {
        System.out.println ("\nEnter the first name of the gymnast."); //Prompting first name for gymnast
        fnames[i] = scan.nextLine(); //Collecting input 
        
        System.out.println ("\nEnter the last name of the gymnast."); //Prompting last name for gymnast 
        lnames[i] = scan.nextLine();
        
        do{
          System.out.println ("\nEnter the school of the competitor. It must be abbreviated into four characters."); //Prompting for school
          schools[i] = scan.nextLine();
          
          if (schools[i].length() > 4)
          {
            System.out.println ("\nThe school abbreviation needs to be less than 4 characters.");
          }
        }while (schools[i].length()>4); 
      } 
    }
    else if (inputs == 2)
    {
      System.out.println ("\nPlease confirm the file name extension. Ensure that it is the same as the one you previously entered.");
      Scanner input = new Scanner(System.in);
      File reading = new File (input.nextLine());
      input = new Scanner(reading);
      
      int i=0;
      
      while (input.hasNextLine())
      {
        String s1 = input.nextLine();
        int Index = s1.indexOf(", ");
        if (Index == -1)
        {
          schools[i] = s1;
          System.out.println (fnames[i] + " " + lnames[i] + ", " + schools[i]);
          i++;
        }
        else 
        {
          StringTokenizer stt = new StringTokenizer(s1, ",");
          int counter = 0;
          while (stt.hasMoreTokens())
          {
            if (counter == 1)
            {
              lnames[i] = stt.nextToken();
            }
            else 
            {
              fnames[i] = stt.nextToken();
            }
            counter++;
          }
        }
      }
      input.close();
    }
    else 
    {
      System.out.println ("Invalid.");
    }
    
    for (int i = 0; i < total.length; i++)
    {
      sum = 0; 
      min = 1000;
      max = 0;
      
      //Task 2
      
      for (int j = 0; j < numjudges; j++) //Loop for the total number of competitors 
      {
        System.out.println ("\nEnter the difficulty score for " + fnames[i] + " " + lnames[i] + " from judge " + (j+1)); //Prompting for scores from the judges 
        do {
          if ((scan.hasNextDouble())) //Ensures program does not crash when a string value is inputted 
          {
            difficulty[j] = scan.nextDouble(); //Accepts user input 
            scan.nextLine();
            if (difficulty[j] < 0)  //If the input does not comply with the rules and the difficulty score is less than 0
            {
              System.out.println ("Invalid score. The difficulty score must be 0 or higher. Try again."); //Prompts the user to enter again if the score was invalid 
            }
          }
          else
          {
            scan.next();
            System.out.println ("Enter a valid number. The difficulty score must be a number 0, or higher.");
          }
        } while (difficulty[j] < 0);
        
        System.out.println ("\nEnter the execution score for " + fnames[i] + " " + lnames[i] + " from judge " + (j+1)); //prompts user to enter the execution score 
        
        do {
          if ((scan.hasNextDouble())) //Ensures program does not crash when a string value is inputted 
          {
            execution[j] = scan.nextDouble();
            scan.nextLine();
            if (execution[j] > 10) //If the input does not comply with the rules and the execution score is greater than 10 
            {
              System.out.println ("Invalid score. The highest execution score possible is 10. Try again."); //Prints out invalid message. prompts user to enter a value again 
            }
          }
          else 
          {
            scan.next();
            System.out.println ("Enter a valid number. The execution score must be a number 10, or lower.");
          }
        }while (execution[j] >10);
        
        System.out.println (fnames[i] + " " + lnames[i] + "'s difficulty score from judge " + (j+1) + " is " + difficulty[j]); //Displays the execution and display scores from each judge 
        System.out.println (fnames[i] + " " + lnames[i] + "'s execution score from judge " + (j+1) + " is " + execution[j]); 
        
        sum = sum + difficulty[j] + execution[j];
        
        if (max < difficulty[j] + execution[j]) //Caclulates the maximum score from a judge 
        {
          max = difficulty[j] + execution[j]; 
        }
        if (min > difficulty[j] + execution[j]) //Calculates the minimum score from a judge 
        {
          min = difficulty[j] + execution[j];
        }
      }
      System.out.println ("The sum of the scores of " + fnames[i] + " " + lnames[i] + " " + sum); //Outputs the sum of the scores 
      System.out.println ("The maximum score of " + fnames[i] + " " + lnames[i] + " " + max); //Outputs the maximum score
      System.out.println ("The minimum score of " + fnames[i] + " " + lnames[i] + " " + min); //Outputs the minimum score
      total[i] = (sum - max - min)/(numjudges-2);  //Calculates the toal score according to the rules 
      
      System.out.println (fnames[i] + " " + lnames[i] + " has a total score of " + total[i]); //Outputs the score of the competitor 
    }
    
    //Sorting the competitors using bubble sort 
    
    for (int j = 1; j <total.length; j++) //Bubble sort the competitors 
    {
      for (int i = 0; i <total.length-j; i++)
      {
        if (total[i] < total[i+1]) //Swapping the terms into descending order 
        {
          double temp = total[i]; //Switches the total scores into descending order 
          total[i] = total[i+1]; 
          total[i+1] = temp;
          
          String temp2 = fnames[i]; //Switches the gymnast's first names according to the total descending order 
          fnames[i] = fnames[i+1];
          fnames[i+1] = temp2; 
          
          String temp3 = lnames[i]; //Switches the gymnast's last names according to the total descending order 
          lnames[i] = lnames[i+1];
          lnames[i+1] = temp3; 
          
          String temp4 = schools[i]; //Switches the gymnast's schools according to the total descending order 
          schools[i] = schools[i+1]; 
          schools[i+1] = temp4; 
        }
      }
    }
    
    System.out.println ("\nSCOREBOARD\n"); //Title for the scoreboard 
    
    System.out.println();
    
    for (int i = 0; i < total.length; i++)
    {
      System.out.println ((i+1) + ". " + fnames[i] + " " + lnames[i] + ", " + schools[i] + "----------------------------------------------------------------" + total[i]); //Outputs the ranks of each gymnast from most points to least 
    }
    
    //Writing results to an external file, called "Results.dat"
    
    File file = new java.io.File ("Results.txt");
    PrintWriter output = new PrintWriter(file);
    for (int i = 0; i <total.length; i++)
    {
      output.println ((i+1) + ". " + fnames[i] + " " + lnames[i] + ", " + schools[i] + "............................................................" + total[i]);
    }
    output.close(); 
    
  } // main method
} // Gymnastics class
