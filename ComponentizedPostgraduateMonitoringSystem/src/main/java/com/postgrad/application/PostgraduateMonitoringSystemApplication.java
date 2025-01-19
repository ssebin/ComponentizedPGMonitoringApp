package com.postgrad.application;

import com.postgrad.student.Student;
import com.postgrad.student.StudentServiceImpl;
import com.postgrad.supervisor.Supervisor;
import com.postgrad.supervisor.SupervisorServiceImpl;
import com.postgrad.base.ProgressUpdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = {"com.postgrad.student", "com.postgrad.supervisor", "com.postgrad.base"})
public class PostgraduateMonitoringSystemApplication {

    private static final Scanner scanner = new Scanner(System.in);

    private final StudentServiceImpl studentService;
    private final SupervisorServiceImpl supervisorService;

    public PostgraduateMonitoringSystemApplication(StudentServiceImpl studentService,
                                                   SupervisorServiceImpl supervisorService) {
        this.studentService = studentService;
        this.supervisorService = supervisorService;
    }

    public static void main(String[] args) {
        var context = SpringApplication.run(PostgraduateMonitoringSystemApplication.class, args);
        System.out.println("\nComponentized Postgraduate Monitoring System is running!");

        PostgraduateMonitoringSystemApplication app =
            context.getBean(PostgraduateMonitoringSystemApplication.class);

        app.runMenu();
    }

    private void runMenu() {
        while (true) {
            System.out.println("\n===== Postgraduate Monitoring System =====");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Supervisors");
            System.out.println("3. Supervisor Appointment");
            System.out.println("4. Manage Progress Updates");
            System.out.println("5. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> manageStudents();
                case 2 -> manageSupervisors();
                case 3 -> supervisorAppointmentMenu();
                case 4 -> progressUpdateManagementMenu();
                case 5 -> {
                    System.out.println("\nExiting the Postgraduate Monitoring System. Goodbye!");
                    return;
                }
                default -> System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    // Manage Students Menu
    private void manageStudents() {
        while (true) {
            System.out.println("\n=== Manage Students ===");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Edit Student Details");
            System.out.println("4. View Student Information");
            System.out.println("5. View All Students");
            System.out.println("6. Back to Main Menu");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> removeStudent();
                case 3 -> editStudentDetails();
                case 4 -> viewStudentInformation();
                case 5 -> viewAllStudents();
                case 6 -> { return; }
                default -> System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    private void addStudent() {
        System.out.print("\nEnter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Research Topic: ");
        String topic = scanner.nextLine();

        Student student = new Student(id, name, email, topic);
        studentService.addStudent(student);
        System.out.println("\nStudent added successfully!");
    }

    private void removeStudent() {
        System.out.print("\nEnter Student ID: ");
        String id = scanner.nextLine();
        if (studentService.removeStudent(id)) {
            System.out.println("\nStudent removed successfully!");
        } else {
            System.out.println("\nStudent not found.");
        }
    }

    private void editStudentDetails() {
        System.out.print("\nEnter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter New Research Topic: ");
        String topic = scanner.nextLine();

        if (studentService.editStudentDetails(id, name, email, topic)) {
            System.out.println("\nStudent details updated successfully!");
        } else {
            System.out.println("\nStudent not found.");
        }
    }

    private void viewStudentInformation() {
        System.out.print("\nEnter Student ID: ");
        String id = scanner.nextLine();
        Student student = studentService.getStudentById(id);
        if (student != null) {
        	System.out.println("\n");
            System.out.println(student);
        } else {
            System.out.println("\nStudent not found.");
        }
    }

    private void viewAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (!students.isEmpty()) {
        	System.out.println("\n");
        	students.forEach(student -> {
                System.out.println(student);
                System.out.println();
            });
        } else {
            System.out.println("\nNo students found.");
        }
    }

    // Manage Supervisors Menu
    private void manageSupervisors() {
        while (true) {
            System.out.println("\n=== Manage Supervisors ===");
            System.out.println("1. Add Supervisor");
            System.out.println("2. Remove Supervisor");
            System.out.println("3. Edit Supervisor Details");
            System.out.println("4. View Supervisor Information");
            System.out.println("5. View All Supervisors");
            System.out.println("6. Back to Main Menu");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addSupervisor();
                case 2 -> removeSupervisor();
                case 3 -> editSupervisorDetails();
                case 4 -> viewSupervisorInformation();
                case 5 -> viewAllSupervisors();
                case 6 -> { return; }
                default -> System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    private void addSupervisor() {
        System.out.print("\nEnter Supervisor ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Office Room: ");
        String room = scanner.nextLine();

        Supervisor supervisor = new Supervisor(id, name, email, room);
        supervisorService.addSupervisor(supervisor);
        System.out.println("\nSupervisor added successfully!");
    }

    private void removeSupervisor() {
        System.out.print("\nEnter Supervisor ID: ");
        String id = scanner.nextLine();
        if (supervisorService.removeSupervisor(id)) {
            System.out.println("\nSupervisor removed successfully!");
        } else {
            System.out.println("\nSupervisor not found.");
        }
    }

    private void editSupervisorDetails() {
        System.out.print("\nEnter Supervisor ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter New Office Room: ");
        String room = scanner.nextLine();

        if (supervisorService.editSupervisorDetails(id, name, email, room)) {
            System.out.println("\nSupervisor details updated successfully!");
        } else {
            System.out.println("\nSupervisor not found.");
        }
    }

    private void viewSupervisorInformation() {
        System.out.print("\nEnter Supervisor ID: ");
        String id = scanner.nextLine();
        Supervisor supervisor = supervisorService.getSupervisorById(id);
        if (supervisor != null) {
        	System.out.println("\n");
            System.out.println(supervisor);
        } else {
            System.out.println("\nSupervisor not found.");
        }
    }

    private void viewAllSupervisors() {
        List<Supervisor> supervisors = supervisorService.getAllSupervisors();
        if (!supervisors.isEmpty()) {
        	System.out.println("\n");
        	supervisors.forEach(supervisor -> {
                System.out.println(supervisor);
                System.out.println();
            });
        } else {
            System.out.println("\nNo supervisors found.");
        }
    }

    // Supervisor Appointment Menu
    private void supervisorAppointmentMenu() {
        while (true) {
            System.out.println("\n=== Supervisor Appointment ===");
            System.out.println("1. Assign Supervisor to Student");
            System.out.println("2. Remove Supervisor from Student");
            System.out.println("3. View Students Without a Supervisor");
            System.out.println("4. View Students Under a Supervisor");
            System.out.println("5. Back to Main Menu");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> assignSupervisor();
                case 2 -> removeSupervisorFromStudent();
                case 3 -> viewStudentsWithoutSupervisor();
                case 4 -> viewStudentsUnderSupervisor();
                case 5 -> { return; }
                default -> System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    private void assignSupervisor() {
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Supervisor ID: ");
        String supervisorId = scanner.nextLine();
        if (supervisorService.assignSupervisorToStudent(studentId, supervisorId)) {
            System.out.println("\nSupervisor assigned successfully!");
        } else {
            System.out.println("\nFailed to assign supervisor. Check IDs.");
        }
    }

    private void removeSupervisorFromStudent() {
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine();
        if (supervisorService.removeSupervisorFromStudent(studentId)) {
            System.out.println("\nSupervisor removed successfully!");
        } else {
            System.out.println("\nFailed to remove supervisor. Check Student ID.");
        }
    }

    private void viewStudentsWithoutSupervisor() {
        List<Student> students = supervisorService.viewStudentsWithoutSupervisor();
        if (!students.isEmpty()) {
        	System.out.println("\n");
        	students.forEach(student -> {
                System.out.println(student);
                System.out.println();
            });
        } else {
            System.out.println("\nAll students have supervisors.");
        }
    }

    private void viewStudentsUnderSupervisor() {
        System.out.print("\nEnter Supervisor ID: ");
        String supervisorId = scanner.nextLine();
        List<Student> students = supervisorService.getStudentsUnderSupervisor(supervisorId);
        if (!students.isEmpty()) {
        	System.out.println("\n");
        	students.forEach(student -> {
                System.out.println(student);
                System.out.println();
            });
        } else {
            System.out.println("\nNo students found for this supervisor.");
        }
    }

    // Progress Update Management Menu
    private void progressUpdateManagementMenu() {
        while (true) {
            System.out.println("\n=== Manage Progress Updates ===");
            System.out.println("1. Add Progress Update");
            System.out.println("2. Remove Progress Update");
            System.out.println("3. Edit Progress Update");
            System.out.println("4. View Progress Updates of a Student");
            System.out.println("5. Back to Main Menu");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addProgressUpdate();
                case 2 -> removeProgressUpdate();
                case 3 -> editProgressUpdate();
                case 4 -> viewProgressUpdatesOfStudent();
                case 5 -> { return; }
                default -> System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    private void addProgressUpdate() {
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Update Date (dd-MM-yyyy): ");
        String dateStr = scanner.nextLine();
        System.out.print("Enter Update Content: ");
        String content = scanner.nextLine();

        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
            ProgressUpdate update = new ProgressUpdate(date, content);
            if (studentService.addProgressUpdate(studentId, update)) {
                System.out.println("\nProgress update added successfully!");
            } else {
                System.out.println("\nFailed to add progress update. Check Student ID.");
            }
        } catch (ParseException e) {
            System.out.println("\nInvalid date format. Please use dd-MM-yyyy.");
        }
    }

    private void removeProgressUpdate() {
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Update Date (dd-MM-yyyy): ");
        String dateStr = scanner.nextLine();

        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
            if (studentService.removeProgressUpdate(studentId, date)) {
                System.out.println("\nProgress update removed successfully!");
            } else {
                System.out.println("\nFailed to remove progress update. Check IDs.");
            }
        } catch (ParseException e) {
            System.out.println("\nInvalid date format. Please use dd-MM-yyyy.");
        }
    }

    private void editProgressUpdate() {
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Update Date (dd-MM-yyyy): ");
        String dateStr = scanner.nextLine();
        System.out.print("Enter New Content: ");
        String content = scanner.nextLine();

        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
            if (studentService.editProgressUpdate(studentId, date, content)) {
                System.out.println("\nProgress update edited successfully!");
            } else {
                System.out.println("\nFailed to edit progress update. Check IDs.");
            }
        } catch (ParseException e) {
            System.out.println("\nInvalid date format. Please use dd-MM-yyyy.");
        }
    }

    private void viewProgressUpdatesOfStudent() {
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine();
        List<ProgressUpdate> updates = studentService.viewProgressUpdatesOfStudent(studentId);
        if (updates != null && !updates.isEmpty()) {
        	System.out.println("\n");
        	updates.forEach(update -> {
                System.out.println(update);
                System.out.println(); 
            });
        } else {
            System.out.println("\nNo progress updates found for this student.");
        }
    }
}