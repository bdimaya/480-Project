import java.util.*;

public class Student {
     
   double gpa;
   double money;
   double performance;
   int health;

   public Student() {
      gpa = 3.5;
      money = 300;
      performance = 0.75;
      health = 0.8;
   }

   public Student(double gpa, double money, double performance, int health) {
      this.gpa = gpa;
      this.money = money;
      this.performance = performance;
      this.health = health;
   }
}
