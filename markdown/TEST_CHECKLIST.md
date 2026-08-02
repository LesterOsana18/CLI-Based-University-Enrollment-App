# University Enrollment System
## Manual Testing Checklist

> Version: Stable Refactor Build
> Status Legend:
> - [ ] Not Tested
> - [x] Passed
> - [!] Failed / Needs Fix

---

# 1. Authentication

## Login
- [ ] Login as Administrator
- [ ] Login as Registrar
- [ ] Login as Student
- [ ] Invalid username
- [ ] Invalid password
- [ ] Empty username
- [ ] Empty password

## Registration
- [ ] Student number exists
- [ ] Student number does not exist
- [ ] Email matches database
- [ ] Email mismatch
- [ ] Username already exists
- [ ] Student already has an account
- [ ] Password validation
- [ ] Confirm password mismatch

---

# 2. Student Management (Admin / Registrar)

## View
- [ ] View all students
- [ ] Search student
- [ ] View archived students

## Create
- [ ] Create student successfully
- [ ] Duplicate student number
- [ ] Duplicate email
- [ ] Missing required fields

## Update
- [ ] Update student information
- [ ] Change department
- [ ] Invalid student ID

## Archive
- [ ] Archive student
- [ ] Restore archived student
- [ ] Permanently delete student

---

# 3. Student Portal

## Student Information
- [ ] Display profile correctly

## Courses
- [ ] View available courses

## Enrollment
- [ ] Enroll successfully
- [ ] Duplicate enrollment
- [ ] Missing prerequisite
- [ ] Circular prerequisite prevention
- [ ] Invalid course

## Drop
- [ ] Drop enrolled course
- [ ] Invalid enrollment ID

## History
- [ ] View enrollment history

## Prerequisites
- [ ] View prerequisite list

---

# 4. Course Management

## View
- [ ] View all courses

## Create
- [ ] Create course
- [ ] Duplicate course code

## Update
- [ ] Update course

## Archive
- [ ] Archive course
- [ ] Restore course
- [ ] Delete course

---

# 5. Department Management

## View
- [ ] View all departments

## Create
- [ ] Add department
- [ ] Duplicate department name

## Update
- [ ] Update department

## Archive
- [ ] Archive department
- [ ] Restore department
- [ ] Delete department

---

# 6. Prerequisite Management

## View
- [ ] View all prerequisites

## Create
- [ ] Add prerequisite
- [ ] Duplicate prerequisite
- [ ] Self prerequisite
- [ ] Circular prerequisite

## Update
- [ ] Update prerequisite

## Delete
- [ ] Delete prerequisite

---

# 7. Enrollment Management

## View
- [ ] View all enrollments

## Search
- [ ] Search by student
- [ ] Search by course

## Delete
- [ ] Remove enrollment

---

# 8. Employee Management

## View
- [ ] View employees

## Create
- [ ] Add employee

## Update
- [ ] Update employee

## Archive
- [ ] Archive employee
- [ ] Restore employee
- [ ] Delete employee

---

# 9. User Management

## View
- [ ] View users
- [ ] View archived users

## Create
- [ ] Create Registrar
- [ ] Create Administrator

## Update
- [ ] Update user

## Archive
- [ ] Archive user
- [ ] Restore user

## Delete
- [ ] Delete user

---

# 10. Security

- [ ] Passwords are hashed
- [ ] Students cannot access Admin menus
- [ ] Registrars cannot access Admin-only features
- [ ] Cannot create duplicate usernames
- [ ] Archived users cannot log in

---

# 11. Database Integrity

- [ ] Student linked to correct user
- [ ] Enrollment linked correctly
- [ ] Course linked to department
- [ ] Prerequisite linked correctly
- [ ] Foreign keys enforced
- [ ] Archive flags behave correctly

---

# 12. User Experience

- [ ] Menus display correctly
- [ ] Tables align properly
- [ ] Success messages shown
- [ ] Error messages shown
- [ ] No crashes
- [ ] No infinite loops
- [ ] Logout works correctly

---

# Overall Result

| Module | Status |
|---------|--------|
| Authentication | ☐ |
| Student Management | ☐ |
| Student Portal | ☐ |
| Courses | ☐ |
| Departments | ☐ |
| Enrollments | ☐ |
| Employees | ☐ |
| Users | ☐ |
| Prerequisites | ☐ |
| Security | ☐ |
| Database | ☐ |

---

## Notes

```
Date:
Tester:
Version:

Issues Found:
- 

Resolved:
-
```