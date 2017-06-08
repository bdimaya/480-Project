import java.lang.*;
import java.util.*;
import java.io.*;

public class simsCP {
   static Scanner scanner = new Scanner(System.in);

   //Variables
   static int quarter = 1;
   static int studyHrs, workHrs, socialHrs, exerciseHrs, sleepHrs, hoursLeft;
   static int clubFlag = 0;
   static int fratFlag = 0;
   static int relationshipFlag = 0;
   static int jobFlag = 0;
   //Classes
   static HashMap<String, Class> avail;
   static HashMap<String, Class> current;
   static HashMap<String, Class> taken;
   
   static HashMap<String, Work> job;

   public static void main(String[] args) {
      printIntro();
      setupGame();

      //While classes are available: keep playing the game
      while(avail.size() > 0) {
      if (clubFlag != 0 && fratFlag != 0) {
         hoursLeft = 19;
      } else if (clubFlag != 0) {
         hoursLeft = 22;
      } else if (fratFlag != 0) {
         hoursLeft = 21;
      } else {
         hoursLeft = 24;
      }
         classSignups();
         System.out.println("< --------- Quarter #" + quarter + " --------- >\n");
         getHours();
         traverseWeeks();
         //Print each categories' grade here, at the end of the quarter.
         quarter++;
      }
      
      //Print final game results here.
      
      System.out.println("Thanks for playing!");
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
      avail.put("CSC141", new Class("Discrete Structures", "CSC141", 3));
      avail.put("CPE315", new Class("Computer Architecture", "CPE315", 3));
      avail.put("CPE357", new Class("Systems Programming", "CPE357", 5));
      avail.put("CSC225", new Class("Computer Organization", "CSC225", 3));
      avail.put("CPE300", new Class("Professional Responsibilities", "CPE300", 2));
      avail.put("CPE349", new Class("Design and Analysis of Algorithms", "CPE349", 4));
      avail.put("CPE101", new Class("Intro to CS 1", "CPE101", 2));
      avail.put("CPE102", new Class("Intro to CS 2", "CPE102", 2));
      avail.put("CPE103", new Class("Intro to CS 3", "CPE103", 2));

   //Clean out 'taken' classes
      taken = new HashMap<String, Class>();
   }
   
   public static void setupWork() {
   //Setup work
      job = new HashMap<String, Work>();
      job.put("Campus Market(1)", new Work("Starbucks", 10));
      job.put("Milestone Tavern(2)", new Work("Bartender", 15));
      job.put("Tutoring Minions(3)", new Work("Teacher", 30));
   }
   
   public static void chooseJob() throws InputMismatchException {
      int jobs = 1;
   
      System.out.println("\nEven with all those classes on your shoulder,");
      System.out.println("you still decide to work. Good for you! However,");
      System.out.println("work should not affect your studying, graduation is your priority!");
      setupWork();
      
      System.out.println("\nIt looks like these are the current jobs available");
      
      //Print available jobs
      System.out.print("Available: [");
      Object[] occupations = job.keySet().toArray();
      if (occupations.length > 0) {
         for (int i = 0; i < occupations.length - 1; i++) {
            System.out.print(occupations[i] + ", ");
         }
         System.out.print(occupations[occupations.length - 1]);
      }
      System.out.println("]");
      System.out.println("Which job would you like?");
       
      while (jobs > 0) {
         System.out.print("Choose one: ");
         Integer selectedWork = scanner.nextInt();
         if (selectedWork != 1 && selectedWork != 2 && selectedWork != 3) {
            System.out.println("Please choose a number 1, 2 or 3");
         }
         
         else {
            if (selectedWork == 1) {
               System.out.println("You will get $10/hr");
            }
            else if (selectedWork == 2) {
               System.out.println("You will get $15/hr");
            }
            else if (selectedWork == 3) {
               System.out.println("You will get $30/hr");
            }
            jobs--;
         }
         System.out.println();
      }
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
      scanner.nextLine();
      hoursLeft -= studyHrs;

      //Working Hours
      System.out.print("Working (Hours Left: " + hoursLeft + "): ");
      workHrs = scanner.nextInt();
      scanner.nextLine();
      hoursLeft -= workHrs;

      //Socializing Hours
      System.out.print("Socializing (Hours Left: " + hoursLeft + "): ");
      socialHrs = scanner.nextInt();
      scanner.nextLine();
      hoursLeft -= socialHrs;

      //Exercising Hours
      System.out.print("Exercising (Hours Left: " + hoursLeft + "): ");
      exerciseHrs = scanner.nextInt();
      scanner.nextLine();
      hoursLeft -= exerciseHrs;
      
      //Sleeping Hours = hours left remaining
      System.out.println("Sleeping: " + hoursLeft);
      sleepHrs = hoursLeft;
   }

   public static void traverseWeeks() {
      String answer = "";
      
      System.out.println("");
      for (int i = 1; i < 11; i++) {
         System.out.println("- Week #" + i + " -");
         try {
            Thread.sleep(800);
         }
         catch (Exception e) {
            System.out.println("Exception caught: " + e);
            return;
         }
         
         if (quarter == 1 && i == 1) {
            //Select jobs
            if(jobFlag == 0) {
               chooseJob();
               //Calculate monthly paycheck
               jobFlag = 1;
            }
         }
         
         //Club event.
         if (quarter == 1 && i == 3) {
            System.out.println("It’s a fresh start in college, and you’re");
            System.out.println("thinking of new things to do. You considering");
            System.out.println("joining your major’s student club. But there");
            System.out.println("are so many fun social clubs. What do you do? (Enter a/b/c)");
            System.out.println("a) I’ll be fine finding friends and academic help in classes. Don’t join a club.");
            System.out.println("b) Join your major club. Increase in academic grade, loss of available time per quarter.");
            System.out.println("c) Join a social club. Increase in social grade, loss of available time per quarter.\n");
            
            answer = scanner.nextLine();
            
            if (answer.equals("a")) {
               System.out.println("\nYou'll be fine. Nothing has changed.\n\n");
            } else if (answer.equals("b")) {
               System.out.println("\nNice choice. Your academics have boosted, but");
               System.out.println("you've lost a few hours per day from now on.\n\n");
               clubFlag = 1;
               //Change academic grade here. Hour changes are already handled.
            } else if (answer.equals("c")) {
               System.out.println("\nNice choice. Your social life has been boosted,");
               System.out.println("but you've lost a few hours per day from now on.\n\n");
               clubFlag = 1;
               //Change social grade here. Hour changes are already handled.
            } else {
               System.out.println("\nYou couldn't decide on a club. No clubs for you this time around.\n\n");
            }
         }
         
         //Frat event.
         if (quarter == 1 && i == 7) {
            System.out.println("It is your campus Greek Rush Week.");
            System.out.println("There are a ton of cool fraternities");
            System.out.println("and sororities recruiting. Do you join one?");
            System.out.println("If yes, increase in social life, loss of");
            System.out.println("available time per quarter. (Enter y/n)");
            
            answer = scanner.nextLine();
            
            if (answer.equals("y")) {
               System.out.println("\nSweet! You've joined Lambda Omega Lambda!");
               System.out.println("Social life boosted, but a few hours per day have been lost.\n\n");
               fratFlag = 1;
               //Change social grade here. Hour changes are already handled.
            } else if (answer.equals("n")) {
               System.out.println("\nNo worries, nothing has changed.\n\n");
            } else {
               System.out.println("\nNo organizations interested you. Nothing has changed.\n\n");
            }
         }
         
         
         //Relationship event.
         if (quarter == 2 && i == 2){
            System.out.println("You’ve been spending some time with friends,");
            System.out.println("and you’re starting to get closer to one of them.");
            System.out.println("It seems that the feelings are mutual. Should you");
            System.out.println("ask them out? If yes, decrease in academic grade,");
            System.out.println("increase in social grade. Can also trigger other ");
            System.out.println("events, both positive and negative. (Enter y/n)");
            
            answer = scanner.nextLine();
            
            if (answer.equals("y")) {
               System.out.println("\nThey said yes! Let's hope for the best! Social");
               System.out.println("grade increased, academic grade decreased.\n\n");
               relationshipFlag = 1;
               //Change social grade and academic grade here.
            } else if (answer.equals("n")) {
               System.out.println("\nYou remain just friends. Nothing has changed.\n\n");
            } else {
               System.out.println("\nYou flubbed the question. Hopefully you at");
               System.out.println("least stay friends.\n\n");
            }
         }
         
         //Car event.
         if (quarter == 2 && i == 5) {
            System.out.println("Just as you were about to leave for a midterm,");
            System.out.println("your car’s engine decides not to start, and you");
            System.out.println("just missed the last bus to campus out of frustration");
            System.out.println("from your car breaking down. Your professor takes no");
            System.out.println("excuses for missed exams. Your academic grade has slightly decreased.\n\n");
            System.out.println("Press ENTER to continue.");
            scanner.nextLine();
            //Change academic grade here.
         }
         
         //Relationship or health event.
         if (quarter == 3 && i == 4) {
            if (relationshipFlag == 1) {
               System.out.println("Your partner decided to randomly take you out on");
               System.out.println("a light day of homework for a nice, healthy dinner, and");
               System.out.println("follow it up with a get-together with friends.");
               System.out.println("Your social grade and health have been increased!\n\n");
               System.out.println("Press ENTER to continue.");
               scanner.nextLine();
               //Change social grade and health grade here.
            } else {
               System.out.println("An organization on campus is hosting a free healthy");
               System.out.println("food cooking event. You decide to go because you're");
               System.out.println("all caught up on your homework. You learn how to make");
               System.out.println("a delicious recipe that you plan on making all the time");
               System.out.println("now. Your health has increased!\n\n");
               System.out.println("Press Enter to continue.");
               scanner.nextLine();
               //Change health grade here.
            }
         }
         
         //Relationship or parent event.
         if (quarter == 3 && i == 9) {
            if (relationshipFlag == 1) {
               System.out.println("It looks like things haven’t really been going well");
               System.out.println("in your relationship… Seems like you no longer feel");
               System.out.println("the same about your partner. Should you end the relationship?");
               System.out.println("If yes, decrease in social grade, but boost in");
               System.out.println("academic grade. (Enter y/n)");
               
               answer = scanner.nextLine();
               
               if (answer.equals("y")) {
                  System.out.println("\nYou've ended the relationship. Your ex doesn't hate you,");
                  System.out.println("but it doesn't seem like you'll stay friends, either.");
                  System.out.println("Social grade decreased, academic grade increased.\n\n");
                  //Change social and academic grades here.
               } else if (answer.equals("n")) {
                  System.out.println("\nYou decide to tough it out for a couple more months");
                  System.out.println("and see how you feel in the future. Nothing has changed.\n\n");
               } else {
                  System.out.println("\nYou couldn't bring yourself to break the news to your");
                  System.out.println("partner. You're still in the relationship. No changes.\n\n");
               }
            } else {
               System.out.println("Your parents promised you a new laptop if you kept your grades");
               System.out.println("at a 4.0 this quarter. Unfortunately, a 4.0 in your major seems");
               System.out.println("nearly impossible. Do you lie to your parents for the laptop, or");
               System.out.println("stay ethical and tell them the truth? (Enter y/n)");
               System.out.println("y) Increase in social grade, decrease in health.");
               System.out.println("n) No changes.");
               
               answer = scanner.nextLine();
               
               if (answer.equals("y")) {
                  System.out.println("\nYou lie to your parents, and they send you a brand new");
                  System.out.println("laptop. You mostly use it to chat and play games with");
                  System.out.println("friends, but now you stay up too late some nights.");
                  System.out.println("Social grade boosted, health lowered.\n\n");
                  //Change social grade and health here.
               } else if (answer.equals("n")) {
                  System.out.println("\nYou follow your morals and tell your parents the truth.");
                  System.out.println("You explain how difficult your major is, so they aren't");
                  System.out.println("upset, but you get no new laptop. Nothing has changed.\n\n");
               } else {
                  System.out.println("\nYour parents found out about your grades anyway and");
                  System.out.println("did not reward you with a new laptop. No changes.\n\n");
               }
            }
         }
      }
   }
}
