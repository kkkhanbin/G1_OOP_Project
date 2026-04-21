# 🎓 Research-Oriented University System

### OOP Project 2026 (Console-Based)

---

## 📌 Project Overview

This project is a **console-based information system for a research-oriented university**, developed as part of the Object-Oriented Programming course.

The system simulates real university operations, including:

* academic processes (courses, grading, transcripts)
* user management (students, teachers, administrators)
* research activities (projects, publications)
* internal communication and management workflows

The main goal of the project is to apply **OOP principles, design patterns, and system architecture design** in a realistic domain.

---

## 🧱 System Design & Architecture

The system is designed using **clean OOP architecture** with:

* 🔹 Inheritance hierarchy
* 🔹 Abstraction and encapsulation
* 🔹 Interfaces and polymorphism
* 🔹 Separation into logical packages

### 🔗 Core Class Hierarchy

```text
User (abstract)
 ├── Student
 └── Employee
      ├── Teacher
      ├── Manager
      └── Admin
```

---

## ⚙️ Core Functionality

### 👤 User System

* Authentication-ready structure (username/password)
* Unified base class (`User`)
* Role-based system using inheritance

---

### 🎓 Academic System

* Course registration
* Credit limit control (max 21 credits)
* Grading system:

  * First attestation
  * Second attestation
  * Final exam
* GPA calculation
* Transcript support

---

### 🧑‍🏫 Teaching System

* Teachers assigned to courses
* Multiple instructors per course
* Student evaluation and grading

---

### 🧑‍💼 Management System

* Managers approve course registration
* Assign courses to teachers
* Generate reports (academic performance)
* Manage system data

---

### 🛠 Administration System

* Full user management (CRUD)
* System monitoring (logs, activity tracking)

---

## 🔬 Research Module (Key Feature)

The system includes a **research-oriented subsystem**, which models:

### 🧑‍🔬 Researcher

* Can be:

  * Teacher
  * Student
  * Any Employee
* Supports research activity independent of role

---

### 📄 Research Papers

* Attributes:

  * Title
  * Authors
  * Citations
  * Journal
  * Pages
  * Publication date
  * DOI

---

### 📊 Features

* Print research papers using custom sorting:

  * By citations
  * By publication date
  * By length (pages)

---

### 🧪 Research Projects

* Topic-based projects
* Participants (only researchers allowed)
* Linked publications

---

### ❗ Validation Rules

* Research supervisor must have **h-index ≥ 3**
* Only researchers can join research projects
* Violations throw **custom exceptions**

---

## 🧩 OOP & Design Features

### ✔ Object-Oriented Concepts

* Inheritance
* Encapsulation
* Polymorphism
* Abstraction

---

### ✔ Java Features Used

* `Comparable` (sorting users)
* `Comparator` (custom sorting logic)
* Collections Framework (`List`, `Set`, etc.)
* Serialization / Deserialization

---

### ✔ Design Patterns (Planned / Implemented)

* Factory Pattern (user creation)
* Strategy Pattern (sorting algorithms)
* Singleton Pattern (data storage)
* Observer Pattern (notifications)

---

## 📁 Project Structure

```text
src/
├── Main.java
│
├── enums/
│   ├── Gender.java
│   ├── TeacherTitle.java
│   └── ManagerType.java
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
├── courses/        (planned)
├── research/       (planned)
├── exceptions/     (planned)
└── database/       (planned)
```

---

## 🚀 How to Run

From the `src` directory:

```bash
javac enums/*.java users/*.java comparators/*.java Main.java
java Main
```

---

## 📊 Current Status

| Module          | Status         |
| --------------- | -------------- |
| Core System     | ✅ Completed    |
| Academic        | 🔄 In Progress |
| Research        | 🔄 In Progress |
| Management      | 🔄 In Progress |
| Design Patterns | 🔄 Planned     |

---

## 🎯 Key Highlights

* Clean and scalable architecture
* Strong use of OOP principles
* Modular system design
* Real-world university simulation
* Research-oriented functionality

---

## 📌 Notes

This project is developed strictly according to **OOP course requirements**, focusing on:

* system design
* code quality
* maintainability
* extensibility

---

## 🧠 Future Improvements

* Full authentication system
* GUI (optional extension)
* Database integration
* Advanced analytics & reporting
