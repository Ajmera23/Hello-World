class Person {
    public String firstName;
    public String lastName;
    public int age;
    public String gender;
    public Person(String firstName, String lastName, int age, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void eat() {
        System.out.println("Person is eating.");
    }
}
class Student extends Person {
    public int rollNo;
    public String className;
    public Student(String firstName, String lastName, int age, String gender, int rollNo, String className) {
        super(firstName, lastName, age, gender);
        this.rollNo = rollNo;
        this.className = className;
    }
    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    public void eat(String food) {
        System.out.println("Student is eating " + food);
    }
    public void eat() {
        System.out.println("Student is eating in canteen.");
    }
}
public class Main {
    public static void main(String[] args) {
        Person person = new Person("Shrut", "Ajmera", 22, "Male");
        System.out.println("Person name: " + person.getFirstName() + " " + person.getLastName());
        System.out.println("Person age: " + person.getAge());
        System.out.println("Person gender: " + person.getGender());
        Student student = new Student("Ajmera", "S23", 22, "Male", 23, "10th");
        System.out.println("Student name: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Student age: " + student.getAge());
        System.out.println("Student gender: " + student.getGender());
        System.out.println("Student roll no: " + student.getRollNo());
        System.out.println("Student class name: " + student.getClassName());
        person.eat();
        student.eat();
        student.eat("pizza");
    }
}
