import java.lang.*;
import java.util.*;
import java.io.*;

public class simsCP {
   static Scanner scanner = new Scanner(System.in);

   //Variables
   static int quarter = 1;
   static int studyHrs, workHrs, socialHrs, exerciseHrs, sleepHrs, hoursLeft;
   //Classes
   static HashMap<String, Class> avail;
   static HashMap<String, Class> current;
   static HashMap<String, Class> taken;

   public static void main(String[] args) {
      printIntro();
      setupGame();

      //While classes are available: keep playing the game
      while(avail.size() > 0) {
         hoursLeft = 24;
         classSignups();
         System.out.println("< --------- Quarter #" + quarter + " --------- >\n");
         getHours();
         traverseWeeks();
         quarter++;
      }
   }

   //Game Methods
   public static void printIntro() {
      System.out.println("\nGreetings! Welcome to the Cal Poly: SLO CPE/CSC/SE Program!");
      System.out.println("During your journey here, you will encounter numerous obstacles");
      System.out.println("that you will have to overcome in order to get your");
      System.out.println("prestigious diploma and move out into the working world!");
      System.out.println("\nLet's get started!\n");
   }

   public static void setupGame() {
   //Setup 'available' classes
      avail = new HashMap<String, Class>();
      avail.put("CPE101", new Class("Intro to CS 1", "CPE101", 2));
      avail.put("CPE102", new Class("Intro to CS 2", "CPE102", 2));
      avail.put("CPE103", new Class("Intro to CS 3", "CPE103", 2));

   //Clean out 'taken' classes
      taken = new HashMap<String, Class>();
   }

   public static void classSignups() {
      int signups = 3;

      System.out.println("What classes would you like to sign up for?\n");

      //Print available classes
      System.out.print("Available: [");
      Object[] classes = avail.keySet().toArray();
      if (classes.length > 0) {
         for (int i = 0; i < classes.length - 1; i++) {
            System.out.print(classes[i] + ", ");
         }
         System.out.print(classes[classes.length - 1]);
      }
      System.out.println("]");

      //Print taken classes
      System.out.print("Taken: [");
      classes = taken.keySet().toArray();
      if (classes.length > 0) {
         for (int i = 0; i < classes.length - 1; i++) {
            System.out.print(classes[i] + ", ");
         }
         System.out.print(classes[classes.length - 1]);
      }
      System.out.println("]");

      current = new HashMap<String, Class>();

      while (signups > 0) {
         System.out.print("Pick a class. (" + signups + ") left: ");
         String selectedClass = scanner.nextLine();
         boolean found = false;

         for (int i = 0; i < avail.size(); i++) {
            if (avail.containsKey(selectedClass)) {
               taken.put(selectedClass, avail.get(i));
               current.put(selectedClass, avail.get(i));
               avail.remove(selectedClass);
               found = true;
               signups--;
            }
         }
         if (!found) {
            System.out.println("Class not found. Please try again.");
         }

         //Print available classes
         System.out.print("\nAvailable: [");
         classes = avail.keySet().toArray();
         if (classes.length > 0) {
            for (int i = 0; i < classes.length - 1; i++) {
               System.out.print(classes[i] + ", ");
            }
            System.out.print(classes[classes.length - 1]);
         }
         System.out.println("]");

         //Print current classes
         System.out.print("Current: [");
         classes = current.keySet().toArray();
         if (classes.length > 0) {
            for (int i = 0; i < classes.length - 1; i++) {
               System.out.print(classes[i] + ", ");
            }
            System.out.print(classes[classes.length - 1]);
         }
         System.out.println("]");

      }
   }

   public static void getHours() {
      System.out.println("--- Hourly Distribution Setup ---");

      //Studying Hours
      System.out.print("Studying (Hours Left: " + hoursLeft + "): ");
      studyHrs = scanner.nextInt();
      hoursLeft -= studyHrs;

      //Working Hours
      System.out.print("Working (Hours Left: " + hoursLeft + "): ");
      workHrs = scanner.nextInt();
      hoursLeft -= workHrs;

      //Socializing Hours
      System.out.print("Socializing (Hours Left: " + hoursLeft + "): ");
      socialHrs = scanner.nextInt();
      hoursLeft -= socialHrs;

      //Exercising Hours
      System.out.print("Exercising (Hours Left: " + hoursLeft + "): ");
      exerciseHrs = scanner.nextInt();
      hoursLeft -= exerciseHrs;
      
      //Sleeping Hours = hours left remaining
      System.out.println("Sleeping: " + hoursLeft);
      sleepHrs = hoursLeft;
   }

   public static void traverseWeeks() {
      for (int i = 1; i < 11; i++) {
         System.out.println("- Week #" + i + " -");
         try {
            Thread.sleep(500);
         }
         catch (Exception e) {
            System.out.println("Exception caught: " + e);
            return;
         }
      }
   }
}
