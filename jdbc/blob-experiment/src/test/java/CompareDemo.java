import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class CompareDemo {

    public static void main(String[] args) {
        BigDecimal bd1 = new BigDecimal(100);
        BigDecimal bd2 = new BigDecimal(120);
        BigDecimal bd3 = new BigDecimal(80);
        BigDecimal bd4 = new BigDecimal(100);

        System.out.println(bd1.compareTo(bd2)); // Negative Integer
        System.out.println(bd1.compareTo(bd3)); // Positive Integer
        System.out.println(bd1.compareTo(bd4)); // Zero
        System.out.println(bd1.equals(bd4));    // true
        System.out.println(bd1 == bd4);         // false

        Student s1 = new Student(1, "Kasun", 75);
        Student s2 = new Student(2, "Ruwan", 85);
        Student s3 = new Student(3, "Nimal", 85);
        Student s4 = new Student(4, "Kamal", 55);

        System.out.println(s1.compareTo(s2));
        System.out.println(s2.compareTo(s3));
        System.out.println(s2.compareTo(s4));

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        System.out.println(studentList);
        Collections.sort(studentList);
        System.out.println(studentList);
    }
}

class Student implements Comparable<Student>{
    int id;
    String name;
    int marks;

    @Override
    public int compareTo(Student o) {
        if (marks == o.marks) return 0;
        else if (marks > o.marks) return 1;
        else return -1;
    }

    public Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name;
    }
}
