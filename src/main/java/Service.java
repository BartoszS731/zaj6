import java.util.Collection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Service {

    public void addStudent(Student student) throws IOException {
        try (var b = new BufferedWriter(new FileWriter("db.txt", true))) {
            b.write(student.toString()); // Write the Student object as a string
            b.newLine();
        }
    }

    public Collection<Student> getStudents() throws IOException {
        var students = new ArrayList<Student>();
        try (var reader = new BufferedReader(new FileReader("db.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    students.add(Student.parse(line));
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping invalid entry: " + line);
                }
            }
        }
        return students;
    }

    public Student findStudentByName(String name) throws IOException {
        var students = this.getStudents();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }
}
