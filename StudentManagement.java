import java.util.ArrayList;
import java.util.Scanner;
public class StudentManagement {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Student Management Portal ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> System.out.println("Exiting program. Bye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }
    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("Student with this ID already exists.");
                return;
            }
        }
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Branch: ");
        String branch = scanner.nextLine();
        System.out.print("Enter Section: ");
        String section = scanner.nextLine();
        Student newStudent = new Student(id, name, branch, section);
        students.add(newStudent);
        System.out.println("Student added successfully!");
    }
    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }
    private static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = scanner.nextInt();

        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("Student found: " + s);
                return;
            }
        }
        System.out.println("Student not found.");
    }
    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.print("Enter new Name: ");
                s.setName(scanner.nextLine());
                System.out.print("Enter new Branch: ");
                s.setBranch(scanner.nextLine());
                System.out.print("Enter new Section: ");
                s.setSection(scanner.nextLine());
                System.out.println("Student updated!");
                return;
            }
        }
        System.out.println("Student not found.");
    }
    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();

        for (Student s : students) {
            if (s.getId() == id) {
                students.remove(s);
                System.out.println("Student deleted!");
                return;
            }
        }
        System.out.println("Student not found.");
    }
}
