package lk.ijse.dep10.collections.set;

import java.util.HashSet;
import java.util.Objects;

public class HashSetDemo5 {

    public static void main(String[] args) {
        HashSet<Student> studentSet = new HashSet<>();
        Student s001 = new Student(1, "Kasun");
        Student s002 = new Student(2, "Nuwan");
        Student s001Clone = new Student(1, "Kasun");
        Student s003 = new Student(3, "Ruwan");

        System.out.println(s001 == s001Clone);
        System.out.println(s001.equals(s001Clone));
        System.out.println("s001 hashcode: " + s001.hashCode());
        System.out.println("s001Clone hashcode: " + s001Clone.hashCode());

        studentSet.add(s001);
        studentSet.add(s002);
        studentSet.add(s001Clone);
        studentSet.add(s003);
        System.out.println(studentSet);
    }
}

class Student {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        Student s = (Student) obj;
        return id == s.id && name.equals(s.name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
