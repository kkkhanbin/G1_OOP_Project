# 🎓 Research-Oriented University System

### OOP Project 2026 (Console-Based)

---

## 📌 Project Overview

This project is a **console-based information system for a research-oriented university**, developed as part of the Object-Oriented Programming course.

The system simulates real university processes including:

* user management
* course registration and grading
* research activities
* internal communication and administration
* authentication and data persistence

The project demonstrates **full OOP design**, modular architecture, and usage of **design patterns**.

---

## 🧱 System Architecture

The system is organized into logical modules:

```text
Core → Academic → Research → Management → System Integration
```

### 🔗 Class Hierarchy

```text
User (abstract)
 ├── Student
 └── Employee
      ├── Teacher
      ├── Manager
      └── Admin
```

---

## ⚙️ Modules Description

### 👤 1. Core System

* Base class: `User`
* Derived classes: `Student`, `Teacher`, `Manager`, `Admin`
* Common properties:

  * id, username, password
  * name, gender
* Supports:

  * inheritance
  * encapsulation
  * polymorphism

---

### 🎓 2. Academic System

Implements the educational workflow:

* `Course`
* `Lesson`
* `Mark`
* `Transcript`

#### Features:

* Course registration with **credit limit (21)**
* Multiple instructors per course
* Lesson scheduling (lecture/practice)
* Grading system:

  * attestation 1
  * attestation 2
  * final exam
* GPA calculation
* Transcript generation

---

### 🔬 3. Research System

Implements research-related functionality:

* `Researcher` (interface)
* `ResearchPaper`
* `ResearchProject`

#### Features:

* Teachers and students can act as researchers
* Papers contain:

  * title, authors, citations, journal, pages, DOI
* Sorting research papers by:

  * citations
  * publication date
  * pages
* Top cited researcher detection
* Supervisor validation:

  * must have **h-index ≥ 3**

#### Custom Exceptions:

* `InvalidSupervisorException`
* `NonResearcherJoinException`

---

### 🧑‍💼 4. Management System

Implements administrative operations:

* `Message`
* `Request`
* `News`
* `AdminService`
* `ManagerService`
* `LogRecord`

#### Features:

* Admin:

  * add/remove users
  * view logs
* Manager:

  * approve requests
  * assign teachers to courses
  * approve student registration
  * publish news
* Employees:

  * send messages
  * create requests
* System logs for all actions

---

### 🧠 5. Final System Integration

Implements system-level functionality:

#### 🔐 Authentication

* `AuthService`
* login by username/password

#### 💾 Data Storage

* `DataStore` (Singleton)
* Serialization / Deserialization
* Saves system state to file (`data.ser`)

#### 🏭 Factory Pattern

* `UserFactory`
* Dynamic creation of users

---

## 🧩 Design Patterns Used

* **Singleton** → `DataStore`
* **Factory** → `UserFactory`
* **Strategy** → Comparators for sorting
* **Comparator** → Custom sorting logic

---

## 📁 Project Structure

```text
src/
├── Main.java
│
├── enums/
│   ├── Gender.java
│   ├── TeacherTitle.java
│   ├── ManagerType.java
│   └── LessonType.java
│
├── users/
│   ├── User.java
│   ├── Employee.java
│   ├── Student.java
│   ├── Teacher.java
│   ├── Manager.java
│   └── Admin.java
│
├── comparators/
│   ├── StudentGpaComparator.java
│   └── UserNameComparator.java
│
├── courses/
│   ├── Course.java
│   ├── Lesson.java
│   ├── Mark.java
│   └── Transcript.java
│
├── exceptions/
│   ├── InvalidSupervisorException.java
│   └── NonResearcherJoinException.java
│
├── research/
│   ├── Researcher.java
│   ├── ResearchPaper.java
│   ├── ResearchProject.java
│   ├── ResearchUtils.java
│   └── comparators/
│       ├── PaperByCitationsComparator.java
│       ├── PaperByDateComparator.java
│       └── PaperByPagesComparator.java
│
├── management/
│   ├── Message.java
│   ├── Request.java
│   ├── News.java
│   ├── AdminService.java
│   └── ManagerService.java
│
├── logs/
│   └── LogRecord.java
│
├── auth/
│   └── AuthService.java
│
├── database/
│   └── DataStore.java
│
└── factory/
    └── UserFactory.java
```

---

## 🚀 How to Run

From `src` directory:

```bash
javac enums/*.java users/*.java comparators/*.java courses/*.java exceptions/*.java research/*.java research/comparators/*.java management/*.java logs/*.java database/*.java auth/*.java factory/*.java Main.java
java Main
```

---

## 📊 Key Features Summary

* Full OOP architecture
* Modular system design
* Academic workflow simulation
* Research management system
* Administrative tools
* Authentication system
* Persistent storage (file-based)
* Multiple design patterns
* Custom exceptions and validation

---

## 📌 Notes

This project was developed strictly according to OOP course requirements, focusing on:

* clean architecture
* scalability
* readability
* real-world simulation

---

## 🔮 Future Improvements

* GUI (JavaFX / Web)
* Database integration (PostgreSQL / MySQL)
* REST API
* Role-based access control
* Advanced analytics and reports
