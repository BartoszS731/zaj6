/*
Program sprawdza poprawność wpisywanego imienia. W przypadku wystąpienia spacji w imieniu, funkcja wyrzuca zdefiniowany wyjątek WrongStudentName, który jest wyłapywany w pętli głównej Commit6_0.
Poniższe zadania będą się sprowadzały do modyfikacji bazowego kodu. Proces modyfikacji ogólnie może wyglądać następująco:
• Ustalenie jaki błąd chcę się sprawdzić i wyłapać.
• Decyzja, czy użyje się własnej klasy wyjątku, czy wykorzysta już istniejące (np. Exception, IOException).
• Napisanie kodu sprawdzającego daną funkcjonalność. W przypadku warunku błędu wyrzucany będzie wyjątek: throw new WrongStudentName().
• W definicji funkcji, która zawiera kod wyrzucania wyjątku dopisuje się daną nazwę wyjątku, np. public static String ReadName() throws WrongStudentName.
• We wszystkich funkcjach, które wywołują powyższą funkcję także należy dopisać, że one wyrzucają ten wyjątek – inaczej program się nie skompiluje.
• W pętli głównej, w main’ie, w zdefiniowanym już try-catch dopisuje się Nazwę wyjątku i go obsługuje, np. wypisuje w konsoli co się stało.
*/

//Commit6_1. Na podstawie analogii do wyjątku WrongStudentName utwórz i obsłuż wyjątki WrongAge oraz WrongDateOfBirth. 
//Niepoprawny wiek – gdy jest mniejszy od 0 lub większy niż 100. Niepoprawna data urodzenia – gdy nie jest zapisana w formacie DD-MM-YYYY, np. 28-02-2023.

import java.io.IOException;
import java.util.Scanner;

class WrongStudentName extends Exception { }

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Service service = new Service();
        while (true) {
            try {
                int ex = menu();
                switch (ex) {
                    case 1:
                        exercise1(service);
                        break;
                    case 2:
                        exercise2(service);
                        break;
                    case 3:
                        exercise3(service);
                        break;
                    default:
                        return;
                }
            } catch (IOException e) {
                System.out.println("An IO error occurred: " + e.getMessage());
            } catch (WrongStudentName e) {
                System.out.println("Błędne imie studenta!");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        return scan.nextInt();
    }

    public static String readName() throws WrongStudentName {
        scan.nextLine(); // Consume the newline character
        System.out.println("Podaj imie: ");
        String name = scan.nextLine();
        if (name.contains(" ")) {
            throw new WrongStudentName();
        }
        return name;
    }

    public static void exercise1(Service service) throws IOException, WrongStudentName {
        String name = readName();
        System.out.println("Podaj wiek (0-100): ");
        int age = scan.nextInt();
        while (age < 0 || age > 100) {
            System.out.println("Wiek musi być między 0 a 100. Podaj wiek ponownie: ");
            age = scan.nextInt();
        }
        scan.nextLine(); // Consume the newline character
        System.out.println("Podaj datę urodzenia DD-MM-YYYY:");
        String date = scan.nextLine();
        service.addStudent(new Student(name, age, date));
        System.out.println("Student added successfully.");
    }

    public static void exercise2(Service service) throws IOException {
        var students = service.getStudents();
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    public static void exercise3(Service service) throws IOException {
        scan.nextLine(); // Consume the newline character
        System.out.println("Podaj imie: ");
        String name = scan.nextLine();
        var student = service.findStudentByName(name);
        if (student == null) {
            System.out.println("Nie znaleziono...");
        } else {
            System.out.println("Znaleziono: ");
            System.out.println(student.toString());
        }
    }
}
