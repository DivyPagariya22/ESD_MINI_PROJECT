-- Create table for Course
CREATE TABLE Course (
                        courseId BIGINT AUTO_INCREMENT PRIMARY KEY,
                        courseCode VARCHAR(255) NOT NULL UNIQUE,
                        name VARCHAR(255),
                        description TEXT,
                        year INT,
                        term VARCHAR(50),
                        faculty VARCHAR(255),
                        credits INT,
                        capacity INT
);

-- Create table for CourseSchedule
CREATE TABLE CourseSchedule (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                course_id BIGINT NOT NULL,
                                day VARCHAR(50),
                                time VARCHAR(50),
                                building VARCHAR(255),
                                room VARCHAR(255),
                                FOREIGN KEY (course_id) REFERENCES Course(courseId) ON DELETE CASCADE
);

-- Create table for Employee
CREATE TABLE Employee (
                          employeeId BIGINT AUTO_INCREMENT PRIMARY KEY,
                          firstName VARCHAR(255),
                          lastName VARCHAR(255),
                          email VARCHAR(255) NOT NULL UNIQUE,
                          password VARCHAR(255),
                          title VARCHAR(255),
                          photograph_path VARCHAR(255),
                          department VARCHAR(255)
);

-- Create table for FacultyCourse
CREATE TABLE FacultyCourse (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               faculty_id BIGINT NOT NULL,
                               course_id BIGINT NOT NULL,
                               FOREIGN KEY (faculty_id) REFERENCES Employee(employeeId) ON DELETE CASCADE,
                               FOREIGN KEY (course_id) REFERENCES Course(courseId) ON DELETE CASCADE
);
