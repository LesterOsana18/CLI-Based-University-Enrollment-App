# 🚀 Project Setup Guide

# Automated University Enrollment Application

This guide will help all team members set up the project correctly before development.

---

# 📋 Prerequisites

Before starting, make sure you have the following installed:

- Java JDK 17 or later
- IntelliJ IDEA (Community or Ultimate)
- MySQL Server 8.0+
- MySQL Workbench or phpMyAdmin
- Git
- Maven (Optional - IntelliJ includes Maven support)

---

# 📥 Clone the Repository

Clone the repository to your local machine.

```bash
git clone <repository-url>
```

Move into the project folder.

```bash
cd UniversityEnrollmentApp
```

---

# 💻 Open the Project

Open **IntelliJ IDEA**.

Select:

```
Open
```

Choose the project folder.

IntelliJ should automatically detect the `pom.xml` file and ask to import the Maven project.

If not:

```
Right Click pom.xml
↓
Add as Maven Project
```

or

```
Maven Tool Window
↓
Reload All Maven Projects
```

---

# 📦 Install Dependencies

After importing the Maven project, IntelliJ will automatically download the required libraries.

Current dependencies include:

- MySQL Connector/J
- jBCrypt

No manual installation is required.

---

# 🗄 Database Setup

Open MySQL Workbench or phpMyAdmin.

Run the following SQL scripts in order:

```
CreateDatabase.sql
```

then

```
SeedDatabase.sql
```

---

# ⚙ Configure Database Connection

Open:

```
src/main/java/com/joysistvi/univenrollmentapp/config/DbConnection.java
```

Update the following if necessary:

```java
private static final String URL = "...";
private static final String USERNAME = "...";
private static final String PASSWORD = "...";
```

Use your own local MySQL credentials.

---

# ▶ Running the Application

Navigate to:

```
App.java
```

Right-click.

Select:

```
Run App
```

---

# 🌿 Git Workflow

Each member has an assigned branch.

| Branch | Assigned Module |
|---------|-----------------|
| `student` | Student Module |
| `registrar` | Registrar Module |
| `admin` | Administrator Module |

---

# 🔄 Before You Start Coding

Always pull the latest changes.

```bash
git pull origin master
```

Switch to your assigned branch.

```bash
git checkout student
```

Replace **student** with your assigned branch.

---

# 💾 Commit Frequently

Example:

```bash
git add .
git commit -m "Implement student enrollment feature"
git push origin student
```

Write meaningful commit messages.

---

# ⚠ IMPORTANT

Follow the project's MVC architecture.

```
Repository
      ↓
Service
      ↓
Controller
      ↓
View
```

Do **not** skip layers.

For example:

❌ View → Repository

✅ View → Controller → Service → Repository

---

# 📁 Shared Files

Avoid modifying these files unless discussed with the team leader.

- App.java
- DbConnection.java
- Session.java
- LoginView.java
- MainMenuView.java
- PasswordUtils.java
- ConsoleUtils.java
- ScreenUtils.java
- InputValidator.java
- TableFormatter.java
- All Enums

---

# 📌 Coding Guidelines

- Follow Java naming conventions.
- Keep methods small and focused.
- Add comments for important logic.
- Validate all user input.
- Handle SQL exceptions properly.
- Test before committing.

---

# 🧪 Testing

Before requesting a merge:

- Project compiles successfully.
- Feature works correctly.
- No console errors.
- No unnecessary files committed.
- Database changes tested.

---

# 🆘 Need Help?

If you encounter issues:

1. Pull the latest changes.
2. Check your database connection.
3. Reload the Maven project.
4. Verify your SQL scripts were executed.
5. Contact the team leader if the issue persists.

---

# 🔀 Merge Policy

- Do **not** merge directly into the `master` branch.
- Complete your assigned module in your designated branch.
- Push your latest commits to your branch.
- Notify the team leader when your module is ready for review.
- The team leader will review, test, and merge approved changes into `master`.

Happy coding! 🚀