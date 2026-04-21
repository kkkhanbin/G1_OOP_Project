# 🎓 University Management & Research System

A console-based **University Management System** developed in **Java** using **Object-Oriented Programming (OOP)** principles and multiple **design patterns**.

This project simulates a real university environment including academic processes, research activities, management operations, and system-level features such as authentication and data persistence.

---

# 📌 Project Overview

The system consists of several interconnected modules:

- Academic system (courses, registration, grading)
- Research system (papers, projects, researcher roles)
- Management system (admin, manager, requests, news)
- Core user hierarchy (students, teachers, staff)
- Final system features (authentication, serialization, factory creation)

---

# 🧱 Technologies

- Java (OOP)
- Java Collections Framework
- Serialization (ObjectOutputStream / ObjectInputStream)
- Comparator & Comparable
- Design Patterns

---

# 🧠 OOP Concepts Used

- **Inheritance** → User → Student, Teacher, Manager, Admin  
- **Polymorphism** → shared behavior via overridden methods  
- **Encapsulation** → private fields with getters/setters  
- **Abstraction** → abstract class `User`, interface `Researcher`  

---

# 🧩 Design Patterns

The following design patterns are implemented:

- **Singleton** → `DataStore` (centralized data storage)
- **Factory** → `UserFactory` (dynamic user creation)
- **Strategy** → Comparators (sorting research papers)
- **Observer** → News notification system

---

# 🎓 Academic System

Features:

- Course registration with **21 credit limit**
- Students cannot register if limit exceeded
- Students cannot continue after **3 failed courses**
- Multiple instructors per course
- Lesson types:
  - Lecture
  - Practice
- Grading system:
  - Attestation 1
  - Attestation 2
  - Final exam
- GPA calculation via `Transcript`
- Course report generation:
  - Average score
  - Passed / Failed students
  - Highest / Lowest score

---

# 🔬 Research System

Features:

- `Researcher` interface implemented by:
  - Student
  - Teacher
- Research papers with:
  - Title
  - Authors
  - Citations
  - Journal
  - Pages
  - Publication date
- Sorting using comparators:
  - By citations
  - By date
  - By pages
- Research projects:
  - Only researchers can join
- **Top cited researcher:**
  - Overall
  - By school
  - By year
- **4th year students:**
  - Must have a research supervisor
  - Supervisor must have **h-index ≥ 3**
- Custom exceptions:
  - InvalidSupervisorException
  - NonResearcherJoinException

---

# 🏛️ Management System

Features:

- Admin:
  - Manage users
  - View system logs
- Manager:
  - Approve requests
  - Assign teachers to courses
  - Register students
  - Send messages
  - Publish news
- News system:
  - Uses **Observer pattern**
  - Subscribers receive notifications
- Logging system:
  - Tracks system actions

---

# 🔐 Final System Features

- **Authentication system**
  - Login using username and password
- **Serialization**
  - Save data to file (`data.ser`)
  - Load data from file
- **Factory pattern**
  - Dynamic creation of users
- **Singleton storage**
  - Centralized data via `DataStore`

---

# ▶️ How to Run

1. Open terminal in `src` folder:

```bash
cd src