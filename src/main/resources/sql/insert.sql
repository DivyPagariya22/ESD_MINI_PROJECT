INSERT INTO faculty_management.course (course_id, capacity, course_code, credits, description, faculty, name, term, year) VALUES (1, 150, 'CS511', 4, 'Algorithm', 'Ram', 'Algorithm', '1', 2024);
INSERT INTO faculty_management.course (course_id, capacity, course_code, credits, description, faculty, name, term, year) VALUES (2, 150, 'CS512', 4, 'ML', 'John', 'Machine Learning', '1', 2024);
INSERT INTO faculty_management.course (course_id, capacity, course_code, credits, description, faculty, name, term, year) VALUES (3, 150, 'CS513', 4, 'Software System', 'Bob', 'Software Systems', '1', 2024);
INSERT INTO faculty_management.course (course_id, capacity, course_code, credits, description, faculty, name, term, year) VALUES (4, 150, 'CS514', 4, 'MML', 'Sham', 'MML', '1', 2024);
INSERT INTO faculty_management.course (course_id, capacity, course_code, credits, description, faculty, name, term, year) VALUES (5, 150, 'CS515', 4, 'Computer Architecture', 'Alice', 'COA', '1', 2024);



insert into faculty_management.course_schedule (id, building, course_id, day, room, time)
values  (1, 'Ramanujam', 1, 'Tuesday', 'R-103', '9:30'),
        (2, 'Ramanujam', 2, 'Monday', 'R-203', '11:30'),
        (3, 'Aryabhatta', 3, 'Tuesday', 'A-106', '9:30'),
        (4, 'Aryabhatta', 4, 'Wednesday', 'A-107', '9:30'),
        (5, 'Ramanujam', 5, 'Monday', 'R-103', '11:30');