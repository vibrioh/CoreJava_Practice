import java.time.LocalDate;
import java.util.Random;

/**
 * Created by vibri on 7/15/2017.
 * This program tests the Employee class.
 * Static nested classes and normal classes are almost the same in functionality,
 * it's just different methods to group things. However when using static nested classes,
 * you cannot put definitions of them in separated files, which will lead to a single file
 * containing a lot of class definitions.
 */
public class EmployeeTest {

  public static void main(String[] args) {

    // fill the staff array with three Employee objects
    Employee[] staff = new Employee[3];

    staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
    staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
    staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

    // raise everyone's salary by 5%
    for (Employee e : staff) {
      e.raiseSalary(5);
    }

    for (Employee e : staff) {
      System.out.println("name=" + e.getName() + ", salary=" + e.getSalary() + ", hireDay=" + e.getHireDay());
    }

    int n = Employee.getNextId();  // calls static method
    System.out.println("Next available id = " + n);
  }

//  private static class Employee {
//
//    private String name;
//    private double salary;
//    private LocalDate hireDay;
//
//    public Employee(String n, double s, int year, int month, int day) {
//      name = n;
//      salary = s;
//      hireDay = LocalDate.of(year, month, day);
//    }
//
//    public String getName() {
//      return name;
//    }
//
//    public double getSalary() {
//      return salary;
//    }
//
//    public LocalDate getHireDay() {
//      return hireDay;
//    }
//
//    public void raiseSalary(double byPercent) {
//      double raise = salary * byPercent / 100;
//      salary += raise;
//    }
//  }

}

class Employee {

  private static int nextId;
  private int id;  // unassigned int == 0

  private String name;
  private double salary;
  private LocalDate hireDay;

  // the three instance fields that will hold the data
  // manipulated inside an instance of the Employee class

  // The private keyword makes sure that the only methods that can access these instance
  // fields are the methods of the Employee class itself. No outside method can read or
  // write to these fields.

  /*
   * You could use the public keyword with your instance fields, but it would
   * be a very bad idea. Having public data fields would allow any part of the program
   * to read and modify the instance fields, completely ruining encapsulation.Any
   * method of any class can modify public fields—and, in our experience, some
   * code will take advantage of that access privilege when you least expect it.We
   * strongly recommend to make all your instance fields private
   */

  // static initialization block
  // Static initialization occurs when the class is first loaded. Like instance fields,
  // static fields are 0 , false , or null unless you explicitly set them to another value.
  static {
    Random generator = new Random();
    // set nextId to a radom number between 0 and 9999
    nextId = generator.nextInt(10000);
  }

  // object initialization block
  {
    id = nextId;
    nextId++;
  }

  // All methods of this class are tagged as public . The keyword public means that any
  // method in any class can call the method.
  public Employee(String n, double s, int year, int month, int day) {  // constructor
    // This constructor runs when you construct objects of the Employee class—giving the
    // instance fields the initial state you want them to have.
    // A constructor can only be called in conjunction with the new operator.
    name = n;
    salary = s;
    hireDay = LocalDate.of(year, month, day);
  }

  /*
   * • A constructor has the same name as the class.
   * • A class can have more than one constructor.
   * • A constructor can take zero, one, or more parameters.
   * • A constructor has no return value.
   * • A constructor is always called with the new operator.
   *
   * Sometimes, it happens that you want to get and set the value of an instance field.
   * Then you need to supply three items:
   * • A private data field;
   * • A public field accessor method; and
   * • A public field mutator method.
   */

  // three more overloaded constructors
  public Employee(String n, double s) {
    name = n;
    salary = s;
    hireDay = LocalDate.now();
  }

  public Employee(double s) {
    this("Employee #" + nextId, s);
  }

  // the default constructor
  public Employee(){
    // name initialized to ""
    // salary not explicitly set --  initialized to 0
    // id initialized in initialization block
  }

  public int getId() {
    return id;
  }

  public void setId() {  // void return nothing
    id = nextId;  // set id to next available id
    nextId++;
  }

  public static int getNextId() {
    return nextId;  // returns static field
  }

  public String getName() {
    return name;
  }

  public double getSalary() {
    return salary;
  }

  public LocalDate getHireDay() {
    return hireDay;
  }

  public void raiseSalary(double byPercent) {
    double raise = this.salary * byPercent / 100;
    this.salary += raise;
  }

  public static void main(String[] args) {  // unit test

    // fill the staff array with four Employee objects
    Employee[] staff = new Employee[4];

    staff[0] = new Employee("Test Tony Tester", 40000, 1990, 3, 15);
    staff[1] = new Employee("Betty", 60000);
    staff[2] = new Employee(66666);
    staff[3] = new Employee();

    for (Employee e : staff) {
      System.out.println(e.getName() +  " " + e.getSalary() + " " + e.getId() + " " + e.getHireDay());
    }

  }
}
