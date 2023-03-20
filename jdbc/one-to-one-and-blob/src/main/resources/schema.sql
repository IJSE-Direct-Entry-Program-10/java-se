CREATE TABLE IF NOT EXISTS Student(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(500) NOT NULL
);

CREATE TABLE IF NOT EXISTS Profile_Picture(
    student_id INT PRIMARY KEY,
    picture MEDIUMBLOB NOT NULL,
    CONSTRAINT fk_student_picture FOREIGN KEY (student_id) REFERENCES Student (id)
);

INSERT INTO Student (name, address) VALUES
                    ('Kasun', 'Galle'),
                    ('Supun', 'Matara');