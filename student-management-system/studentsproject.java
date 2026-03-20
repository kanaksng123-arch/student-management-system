import java.util.ArrayList;
import java.util.Scanner;

class Student {  
    String branch;
    String name;
    int rollno;

    Student(String branch, String name, int rollno) {
        this.branch = branch;
        this.name = name;
        this.rollno = rollno;
    }

    void display() {
        System.out.println(name + " | " + branch + " | " + rollno);
    }
}

public class studentsproject {

    static ArrayList<Student> arr = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Add student
    static void addStudent() {
        System.out.print("Enter name of student: ");
        String name = sc.nextLine();

        System.out.print("Enter branch of student: ");
        String branch = sc.nextLine();

        System.out.print("Enter the roll no: ");
        int rollno = sc.nextInt();
        sc.nextLine(); // clear buffer
        sc.nextLine(); // EXTRA: Ensure clean buffer before returning

        // Prevent duplicate roll numbers
        for (Student s : arr) {
            if (s.rollno == rollno) {
                System.out.println("Roll number already exists!");
                return;
            }
        }

        arr.add(new Student(branch, name, rollno));
        System.out.println("Student added successfully!");
    }

    // View students
    static void viewStudent() {
        if (arr.size() == 0) {
            System.out.println("The list is empty.");
            return;
        }

        for (int i = 0; i < arr.size(); i++) {
            System.out.print((i + 1) + ". ");
            arr.get(i).display();
        }
    }

    // Search student
    static void searchStudent() {
        System.out.print("Enter the roll no: ");
        int roll = sc.nextInt();
        sc.nextLine(); // clear buffer
        sc.nextLine(); // EXTRA: Clean buffer

        boolean found = false;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).rollno == roll) {
                System.out.println("Student found at position " + (i + 1));
                arr.get(i).display();
                System.out.println("Students checked before finding: " + (i + 1));
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found");
            System.out.println("Total students checked: " + arr.size());
        }
    }

    // Delete student
    static void deleteStudent() {
        if (arr.size() == 0) {
            System.out.println("No students available.");
            return;
        }

        System.out.print("Enter the roll no: ");
        int roll = sc.nextInt();
        sc.nextLine(); // clear buffer
        sc.nextLine(); // EXTRA: Clean buffer

        boolean found = false;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).rollno == roll) {
                System.out.println("Student found at position " + (i + 1));
                arr.get(i).display();
                arr.remove(i);
                System.out.println("Student deleted successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found");
        }
    }

    // Update student
    static void updateStudent() {
        if (arr.size() == 0) {
            System.out.println("No students available.");
            return;
        }

        System.out.print("Enter the roll no: ");
        int roll = sc.nextInt();
        sc.nextLine(); // clear buffer
        sc.nextLine(); // EXTRA: Clean buffer

        boolean found = false;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).rollno == roll) {
                System.out.println("Student found at position " + (i + 1));
                arr.get(i).display();

                System.out.println("What do you want to update?");
                System.out.println("1. Name");
                System.out.println("2. Branch");
                System.out.println("3. Both");

                int choice = sc.nextInt();
                sc.nextLine(); // clear buffer after choice
                sc.nextLine(); // EXTRA: Clean buffer

                if (choice == 1) {
                    System.out.print("Enter new name: ");
                    arr.get(i).name = sc.nextLine();
                } 
                else if (choice == 2) {
                    System.out.print("Enter new branch: ");
                    arr.get(i).branch = sc.nextLine();
                } 
                else if (choice == 3) {
                    System.out.print("Enter new name: ");
                    arr.get(i).name = sc.nextLine();
                    System.out.print("Enter new branch: ");
                    arr.get(i).branch = sc.nextLine();
                } 
                else {
                    System.out.println("Invalid choice.");
                    return;
                }

                System.out.println("Student updated successfully!");
                arr.get(i).display();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    
    public static void main(String[] args) {
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
            sc.nextLine(); // clear buffer
            sc.nextLine(); // EXTRA: Ensure clean buffer for stable menu loop

            if (choice == 1) {
                addStudent();
            } 
            else if (choice == 2) {
                deleteStudent();
            } 
            else if (choice == 3) {
                updateStudent();
            } 
            else if (choice == 4) {
                viewStudent();
            } 
            else if (choice == 5) {
                searchStudent();
            } 
            else if (choice == 6) {
                System.out.println("Exiting...");
            } 
            else {
                System.out.println("Invalid choice");
            }

        } while (choice != 6);
    }
}
