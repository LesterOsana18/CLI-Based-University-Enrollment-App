# 📋 Task Distribution

## Automated University Enrollment Application

This document outlines the assigned responsibilities for each team member. Every member should work only on their assigned module and commit changes to their designated Git branch.

---

# 🌿 Branch Assignments

| Branch | Assigned Member(s) |
|----------|--------------------|
| `student` | Sir Ronald Jay Cruz |
| `registrar` | Sir Jerry Matubis<br>Sir Jeffrey Olivar |
| `admin` | Sir Romel Cabiling |

---

# 👨‍🎓 Student Module
**Branch:** `student`

**Assigned to:**
- Sir Ronald Jay Cruz

### Responsibilities

- Student Dashboard
- View Available Courses
- View Personal Enrollment History
- Enroll in Courses
- Drop Enrolled Courses
- View Student Information
- View Prerequisite Information
- Student menu navigation
- Input validation
- Database integration

---

# 🏢 Registrar Module
**Branch:** `registrar`

**Assigned to:**
- Sir Jerry Matubis
- Sir Jeffrey Olivar

### Responsibilities

- Student Management (CRUD)
- Enrollment Management
- Course Assignment
- Search Students
- Search Enrollments
- Validate Prerequisites
- Generate Enrollment Reports (Optional)
- Registrar menu navigation
- Database integration

---

# 👑 Administrator Module
**Branch:** `admin`

**Assigned to:**
- Sir Romel Cabiling

### Responsibilities

- User Management (CRUD)
- Employee Management
- Department Management
- Course Management
- Prerequisite Management
- System Administration Features
- Administrator menu navigation
- Database integration

---

# 👨‍💻 Team Leader Responsibilities

**Assigned to:**
- Lester Osana

### Completed

- ✔ Database Design (ERD)
- ✔ SQL Database Script
- ✔ Seed Database
- ✔ GitHub Repository Setup
- ✔ Project Structure (MVC)
- ✔ Utility Classes
- ✔ Enums
- ✔ Database Configuration
- ✔ Session Management
- ✔ Login & Registration
- ✔ BCrypt Password Hashing
- ✔ User Authentication
- ✔ Role-Based Access
- ✔ Main Menu
- ✔ Application Flow
- ✔ Use Case Diagram
- ✔ Project Documentation

### Ongoing Responsibilities

- Review pull requests
- Resolve merge conflicts
- Integrate all modules
- Maintain project architecture
- Perform application testing
- Bug fixing
- Final code review
- Final presentation preparation

---

# 📌 Development Guidelines

- Work only on your assigned branch.
- Pull the latest changes before starting development.
- Commit changes regularly with meaningful commit messages.
- Do **not** modify shared infrastructure files unless necessary.
- Test your module before requesting a merge.
- Report any integration issues immediately.

---

# ⚠ Shared Core Files

Please avoid modifying these files unless discussed with the team leader.

- `App.java`
- `DbConnection.java`
- `Session.java`
- `PasswordUtils.java`
- `LoginView.java`
- `MainMenuView.java`
- `UserController.java`
- `UserService.java`
- `UserRepository.java`
- `ConsoleUtils.java`
- `InputValidator.java`
- `ScreenUtils.java`
- `TableFormatter.java`
- All files under the `enums` package

---

# 🎯 Project Goal

Build a clean, modular, and maintainable University Enrollment Application by following the MVC architecture and integrating each module seamlessly into the existing project foundation.