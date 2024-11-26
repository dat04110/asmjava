import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class asm {
    static class Student {
        private String firstName;
        private String lastName;

        public Student(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
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

        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    private static final List<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Enter student list");
            System.out.println("2. Find students by last name");
            System.out.println("3. Find and edit students by full name");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> enterStudentList();
                case "2" -> findStudentsByLastName();
                case "3" -> findAndEditStudentByFullName();
                case "4" -> {
                    System.out.println("Exiting...");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void enterStudentList() {
        System.out.print("Enter the number of students: ");
        int numberOfStudents;

        try {
            numberOfStudents = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Please try again.");
            return;
        }

        for (int i = 1; i <= numberOfStudents; i++) {
            System.out.print("Enter first name of student " + i + ": ");
            String firstName = scanner.nextLine().trim();

            System.out.print("Enter last name of student " + i + ": ");
            String lastName = scanner.nextLine().trim();

            students.add(new Student(firstName, lastName));
        }

        System.out.println("Student list entered successfully.");
    }

    private static void findStudentsByLastName() {
        System.out.print("Enter the last name to search for: ");
        String lastName = scanner.nextLine().trim();

        List<Student> matchedStudents = students.stream()
                .filter(student -> student.getLastName().equalsIgnoreCase(lastName))
                .toList();

        if (matchedStudents.isEmpty()) {
            System.out.println("No students found with the last name: " + lastName);
        } else {
            System.out.println("Students found:");
            matchedStudents.forEach(System.out::println);
        }
    }

    private static void findAndEditStudentByFullName() {
        System.out.print("Enter the first name to search for: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter the last name to search for: ");
        String lastName = scanner.nextLine().trim();

        for (Student student : students) {
            if (student.getFirstName().equalsIgnoreCase(firstName) &&
                    student.getLastName().equalsIgnoreCase(lastName)) {
                System.out.println("Student found: " + student);

                System.out.print("Enter new first name: ");
                String newFirstName = scanner.nextLine().trim();

                System.out.print("Enter new last name: ");
                String newLastName = scanner.nextLine().trim();

                student.setFirstName(newFirstName);
                student.setLastName(newLastName);

                System.out.println("Student information updated successfully. Updated student: " + student);
                return;
            }
        }

        System.out.println("No students found with the full name: " + firstName + " " + lastName);
    }
}