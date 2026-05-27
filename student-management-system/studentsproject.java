import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class Student {
    private String name;
    private String branch;
    private int rollNo;

    Student(String name, String branch, int rollNo) {
        this.name = name;
        this.branch = branch;
        this.rollNo = rollNo;
    }

    
    public String getName()   { return name; }
    public String getBranch() { return branch; }
    public int getRollNo()    { return rollNo; }

    public void setName(String name)     { this.name = name; }
    public void setBranch(String branch) { this.branch = branch; }

    public void display() {
        System.out.println("Name: " + name + " | Branch: " + branch + " | Roll No: " + rollNo);
    }
}

public class StudentManagementSystem {

    static ArrayList<Student> studentList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "students.txt";

    // ─── File Handling ───────────────────────────────────────────────────────────

    // Reads student data from file into studentList when program starts
    static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return; // No file yet — first run, skip loading

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name   = parts[0];
                    String branch = parts[1];
                    int rollNo    = Integer.parseInt(parts[2].trim());
                    studentList.add(new Student(name, branch, rollNo));
                }
            }
            System.out.println(studentList.size() + " student record(s) loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    // Writes entire studentList to file after every add/update/delete
    static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : studentList) {
                writer.write(s.getName() + "," + s.getBranch() + "," + s.getRollNo());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // ─── CRUD Operations ─────────────────────────────────────────────────────────

    static void addStudent() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter branch: ");
        String branch = sc.nextLine();

        System.out.print("Enter roll no: ");
        int rollNo = sc.nextInt();
        sc.nextLine(); // clears the leftover \n after nextInt()

        // Duplicate roll number check
        for (Student s : studentList) {
            if (s.getRollNo() == rollNo) {
                System.out.println("Roll number already exists!");
                return;
            }
        }

        studentList.add(new Student(name, branch, rollNo));
        saveToFile(); // persist immediately after adding
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("--- Student List ---");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.print((i + 1) + ". ");
            studentList.get(i).display();
        }
    }

    static void searchStudent() {
        System.out.print("Enter roll no to search: ");
        int roll = sc.nextInt();
        sc.nextLine(); // clears the leftover \n

        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNo() == roll) {
                System.out.println("Student found at position " + (i + 1) + ":");
                studentList.get(i).display();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    static void deleteStudent() {
        if (studentList.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.print("Enter roll no to delete: ");
        int roll = sc.nextInt();
        sc.nextLine(); // clears the leftover \n

        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNo() == roll) {
                System.out.println("Deleting student:");
                studentList.get(i).display();
                studentList.remove(i);
                saveToFile(); // persist immediately after deleting
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    static void updateStudent() {
        if (studentList.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.print("Enter roll no to update: ");
        int roll = sc.nextInt();
        sc.nextLine(); // clears the leftover \n

        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNo() == roll) {
                System.out.println("Student found:");
                studentList.get(i).display();

                System.out.println("What do you want to update?");
                System.out.println("1. Name");
                System.out.println("2. Branch");
                System.out.println("3. Both");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // clears the leftover \n

                switch (choice) {
                    case 1:
                        System.out.print("Enter new name: ");
                        studentList.get(i).setName(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("Enter new branch: ");
                        studentList.get(i).setBranch(sc.nextLine());
                        break;
                    case 3:
                        System.out.print("Enter new name: ");
                        studentList.get(i).setName(sc.nextLine());
                        System.out.print("Enter new branch: ");
                        studentList.get(i).setBranch(sc.nextLine());
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        return;
                }

                saveToFile(); // persist immediately after updating
                System.out.println("Student updated successfully!");
                studentList.get(i).display();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // ─── Main ────────────────────────────────────────────────────────────────────

    public static void main(String[] args) {

        loadFromFile(); // load existing data when program starts

        int choice;

        do {
            System.out.println("\n< STUDENT MANAGEMENT SYSTEM >");
            System.out.println("1. Add student");
            System.out.println("2. Delete student");
            System.out.println("3. Update student");
            System.out.println("4. View students");
            System.out.println("5. Search student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // clears the leftover \n

            switch (choice) {
                case 1: addStudent();   break;
                case 2: deleteStudent(); break;
                case 3: updateStudent(); break;
                case 4: viewStudents();  break;
                case 5: searchStudent(); break;
                case 6: System.out.println("Exiting. Goodbye!"); break;
                default: System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);

        sc.close();
    }
}
