import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    ArrayList<Integer> grades;

    Student(String name) {
        this.name = name;
        grades = new ArrayList<>();
    }

    void addGrade(int grade) {
        grades.add(grade);
    }

    double getAverage() {
        int sum = 0;
        for (int g : grades) sum += g;
        return grades.size() > 0 ? (double) sum / grades.size() : 0;
    }

    int getHighest() {
        int max = Integer.MIN_VALUE;
        for (int g : grades) if (g > max) max = g;
        return grades.size() > 0 ? max : 0;
    }

    int getLowest() {
        int min = Integer.MAX_VALUE;
        for (int g : grades) if (g < min) min = g;
        return grades.size() > 0 ? min : 0;
    }

    void displayReport() {
        System.out.println("Student: " + name);
        System.out.println("Grades: " + grades);
        System.out.println("Average: " + getAverage());
        System.out.println("Highest: " + getHighest());
        System.out.println("Lowest: " + getLowest());
        System.out.println("-------------------");
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("=== Student Grade Tracker ===");

        while (true) {
            System.out.print("Enter student name (or type 'exit' to stop): ");
            String name = sc.nextLine();
            if (name.equalsIgnoreCase("exit")) break;

            Student s = new Student(name);

            System.out.print("Enter number of grades for " + name + ": ");
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                System.out.print("Enter grade " + (i + 1) + ": ");
                s.addGrade(sc.nextInt());
            }
            sc.nextLine(); // clear buffer
            students.add(s);
        }

        System.out.println("\n=== Report ===");
        for (Student s : students) {
            s.displayReport();
        }
        sc.close();
    }
}