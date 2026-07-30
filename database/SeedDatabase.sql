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