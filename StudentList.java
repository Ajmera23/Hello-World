import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
class Student {
    private int id;
    private String name;
    private int age;
    private String gender;

    public Student(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
public class StudentList {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John", 20, "Male"));
        students.add(new Student(2, "Mary", 19, "Female"));
        students.add(new Student(3, "David", 22, "Male"));
        students.add(new Student(4, "Samantha", 18, "Female"));
        students.add(new Student(5, "Kevin", 21, "Male"));

        System.out.println("Original list:");
        System.out.println(students);

        // sort by id
        students.sort(Comparator.comparingInt(Student::getId));
        System.out.println("Sorted by id:");
        System.out.println(students);

        // sort by age
        students.sort(Comparator.comparingInt(Student::getAge));
        System.out.println("Sorted by age:");
        System.out.println(students);

        // filter by gender
        List<Student> females = new ArrayList<>();
        for (Student student : students) {
            if (student.getGender().equals("Female")) {
                females.add(student);
            }
        }
        System.out.println("Females:");
        System.out.println(females);

        // filter by name
        List<Student> davids = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equals("David")) {
                davids.add(student);
            }
        }
        System.out.println("Davids:");
        System.out.println(davids);

        // using basic methods of List and Map
        System.out.println("Size of students list: " + students.size());

        Map<Integer, Student> studentMap = new HashMap<>();
        for (Student student : students) {
            studentMap.put(student.getId(), student);
        }
        System.out.println("Student with id=3: " + studentMap.get(3));
    }
}
