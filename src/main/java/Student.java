import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Student {

    private String name;
    private int age;
    private LocalDate dateOfBirth;

    public Student(String name, int age, int day, int month, int year) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Age must be between 0 and 100");
        }
        if (day < 1 || day > 31 || month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid day or month");
        }

        this.name = name;
        this.age = age;

        try {
            this.dateOfBirth = LocalDate.of(year, month, day);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format.");
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return name + " " + age + " " + dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static Student parse(String str) {
        String[] data = str.split(" ");
        if (data.length != 3) {
            throw new IllegalArgumentException("Invalid input string format");
        }
        try {
            int age = Integer.parseInt(data[1]);
            String[] dateParts = data[2].split("-");
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);
            return new Student(data[0], age, day, month, year);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input string format", e);
        }
    }
}
