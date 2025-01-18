# Componentized Postgraduate Monitoring System

This project is a **Spring Boot application** that provides a console-based interface for managing students, supervisors, supervisor appointments, and progress updates. It is designed with a componentized architecture for modularity and scalability.


## Table of Contents
1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Prerequisites](#prerequisites)
4. [Setup and Running the Application](#setup-and-running-the-application)
5. [Project Structure](#project-structure)
6. [Usage](#usage)
7. [License](#license)


## Features
- **Manage Students**: Add, edit, remove, and view student details.
- **Manage Supervisors**: Add, edit, remove, and view supervisor details.
- **Supervisor Appointments**: Assign supervisors to students, view students without supervisors, and manage supervisor assignments.
- **Progress Updates**: Add, edit, remove, and view progress updates for students.
- Modularized design with components for Students, Supervisors, and shared services.


## Technologies Used
- **Java 22**
- **Spring Boot 3.4.1**
- **Maven** for dependency management and project building.


## Prerequisites
1. **Java Development Kit (JDK) 22**:
   - Ensure `JAVA_HOME` is set to Java 22.
   - Check your Java version:
     ```bash
     java -version
     ```

2. **Maven 3.8+**:
   - Check your Maven version:
     ```bash
     mvn -v
     ```

3. A terminal or command prompt for running the JAR file.


## Setup and Running the Application

### **1. Build the Application**
If you want to build the project yourself:
1. Navigate to the project root (where `pom.xml` is located).
2. Run the following commands:
   ```bash
   mvn clean package
   ```
3.	The JAR file will be generated in the target/ directory.

Alternatively, use the pre-built JAR file provided in the target/ folder.

### 2. Run the Application

Navigate to the target/ folder and run the JAR file:

```bash
java -jar PostgraduateMonitoringSystem-1.0.0.jar
```

The application will display a menu in the terminal, allowing you to interact with the system.


## Project Structure

```plaintext
src/main/java
│
├── com.postgrad.application
│   └── PostgraduateMonitoringSystemApplication.java  # Main application entry point
│
├── com.postgrad.student
│   ├── Student.java                                   # Student entity
│   ├── StudentServiceImpl.java                        # Student service implementation
│   ├── StudentRepository.java                         # Student repository
│   └── StudentController.java                         # REST controller (if web endpoints are needed)
│
├── com.postgrad.supervisor
│   ├── Supervisor.java                                # Supervisor entity
│   ├── SupervisorServiceImpl.java                     # Supervisor service implementation
│   ├── SupervisorRepository.java                      # Supervisor repository
│   └── SupervisorController.java                      # REST controller (if web endpoints are needed)
│
└── com.postgrad.base
    ├── ProgressUpdate.java                            # Progress update entity
    ├── StudentService.java                            # Student service interface
    └── SupervisorService.java                         # Supervisor service interface

```

## Usage

1. Run the Application

- After starting the application, you’ll see a menu in the terminal.

2. Menu Options

- Manage Students:
  - Add, edit, remove, or view student information.
- Manage Supervisors:
  - Add, edit, remove, or view supervisor information.
- Supervisor Appointments:
  - Assign supervisors to students, remove assignments, and view supervisor-student associations.
- Progress Updates:
  - Add, edit, remove, or view progress updates for a student.
 
3. Exit the Application

- Select option 5 in the main menu to exit.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
