-- ===========================================================
-- Automated University Enrollment Application (Seed Database)
-- ===========================================================

USE university_enrollment_db;

-- Departments --
INSERT INTO departments (id, department_name) VALUES
(1, 'College of Computer Studies'),
(2, 'College of Information Technology'),
(3, 'College of Engineering'),
(4, 'College of Business Administration'),
(5, 'College of Education'),
(6, 'College of Arts and Sciences');

-- Courses --
INSERT INTO courses (id, course_code, course_name, units, department_id) VALUES
(1, 'CS101', 'Introduction to Programming', 3, 1),
(2, 'CS102', 'Object-Oriented Programming', 3, 1),
(3, 'CS201', 'Data Structures and Algorithms', 3, 1),
(4, 'CS210', 'Discrete Mathematics', 3, 1),
(5, 'CS301', 'Database Systems', 3, 1),
(6, 'CS305', 'Software Engineering', 3, 1),
(7, 'CS310', 'Operating Systems', 3, 1),
(8, 'IT101', 'Computer Fundamentals', 3, 2),
(9, 'IT150', 'Networking Fundamentals', 3, 2),
(10, 'IT205', 'Web Development', 3, 2),
(11, 'IT210', 'Systems Analysis and Design', 3, 2),
(12, 'IT305', 'Information Assurance and Security', 3, 2),
(13, 'ENG101', 'Engineering Mathematics', 3, 3),
(14, 'ENG102', 'Physics for Engineers', 4, 3),
(15, 'ENG201', 'Statics of Rigid Bodies', 3, 3),
(16, 'ENG301', 'Thermodynamics', 3, 3),
(17, 'ENG305', 'Engineering Economics', 3, 3),
(18, 'BUS101', 'Principles of Management', 3, 4),
(19, 'BUS102', 'Financial Accounting', 3, 4),
(20, 'BUS201', 'Marketing Management', 3, 4),
(21, 'BUS301', 'Human Resource Management', 3, 4),
(22, 'BUS305', 'Business Law', 3, 4),
(23, 'EDU101', 'Foundations of Education', 3, 5),
(24, 'EDU102', 'Child and Adolescent Development', 3, 5),
(25, 'EDU201', 'Curriculum Development', 3, 5),
(26, 'EDU301', 'Educational Technology', 3, 5),
(27, 'AS101', 'Communication Arts', 3, 6),
(28, 'AS102', 'College Algebra', 3, 6),
(29, 'AS103', 'Philippine History', 3, 6),
(30, 'AS201', 'Statistics', 3, 6);

-- Prerequisites --
INSERT INTO prerequisites (id, course_id, prerequisite_course_id) VALUES
(1, 2, 1),
(2, 3, 2),
(3, 3, 4),
(4, 5, 3),
(5, 6, 5),
(6, 7, 3),
(7, 9, 8),
(8, 10, 8),
(9, 11, 10),
(10, 12, 11),
(11, 14, 13),
(12, 15, 14),
(13, 16, 15),
(14, 17, 13),
(15, 20, 18),
(16, 21, 20),
(17, 22, 18),
(18, 25, 23),
(19, 26, 25),
(20, 30, 28);

-- Students --
INSERT INTO students (student_number, first_name, last_name, email, department_id, user_id, status, is_archived) VALUES
('2022-08948-MN-0','Lester','Osana','lester.osana@university.edu',1,NULL,'ACTIVE',FALSE),
('2022-00002-MN-0','Maria','Cruz','maria.cruz@university.edu',1,NULL,'ACTIVE',FALSE),
('2022-00003-MN-0','Jose','Reyes','jose.reyes@university.edu',2,NULL,'ACTIVE',FALSE),
('2022-00004-MN-0','Anna','Garcia','anna.garcia@university.edu',3,NULL,'ACTIVE',FALSE),
('2022-00005-MN-0','Mark','Torres','mark.torres@university.edu',4,NULL,'ACTIVE',FALSE),
('2022-00006-MN-0','Ella','Ramos','ella.ramos@university.edu',5,NULL,'ACTIVE',FALSE),
('2022-00007-MN-0','Paolo','Mendoza','paolo.mendoza@university.edu',6,NULL,'ACTIVE',FALSE),
('2022-00008-MN-0','Grace','Villanueva','grace.villanueva@university.edu',1,NULL,'ACTIVE',FALSE),
('2022-00009-MN-0','Miguel','Bautista','miguel.bautista@university.edu',2,NULL,'ACTIVE',FALSE),
('2022-00010-MN-0','Sofia','Aquino','sofia.aquino@university.edu',3,NULL,'ACTIVE',FALSE),
('2022-00011-MN-0','Daniel','Castillo','daniel.castillo@university.edu',4,NULL,'ACTIVE',FALSE),
('2022-00012-MN-0','Isabel','Navarro','isabel.navarro@university.edu',5,NULL,'ACTIVE',FALSE),
('2022-00013-MN-0','Rafael','Ocampo','rafael.ocampo@university.edu',6,NULL,'ACTIVE',FALSE);

-- ===========================================================
-- Default System Accounts
-- These accounts are required to bootstrap the application.
-- Passwords must be stored as BCrypt hashes.
-- ===========================================================

/*
===========================================================
    DEFAULT SYSTEM ACCOUNTS

    Administrator
    Username : admin
    Password : admin123

    Registrar #1
    Username : registrar1
    Password : registrar123

    Registrar #2
    Username : registrar2
    Password : registrar123

    NOTE:
    Passwords are stored in the database as BCrypt hashes.
===========================================================
*/

-- Users --
INSERT INTO users (id, username, password, role) VALUES
(1, 'admin', '$2a$12$XtkEkcWMk31cCYL4cxM3OO5Ymjlhrue04XpqRKINkERvcRiuYNrRq', 'ADMIN'),
(2, 'registrar1', '$2a$12$sDiXXuC36QU4G56ZP5jnquMwnVk80UDfyDSt4Sb79zzFa0pI2vXze', 'REGISTRAR'),
(3, 'registrar2', '$2a$12$sDiXXuC36QU4G56ZP5jnquMwnVk80UDfyDSt4Sb79zzFa0pI2vXze', 'REGISTRAR');

-- Employees --
INSERT INTO employees (id, employee_id, first_name, last_name, position, user_id) VALUES
(
    1,
    'ADM-0001',
    'System',
    'Administrator',
    'ADMIN',
    1
),
(
    2,
    'REG-1001',
    'Maria',
    'Santos',
    'REGISTRAR',
    2
),
(
    3,
    'REG-1002',
    'Juan',
    'Dela Cruz',
    'REGISTRAR',
    3
);
