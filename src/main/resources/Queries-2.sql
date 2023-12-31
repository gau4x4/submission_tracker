create schema company;
use company;
-- write a DDL command to create this table
CREATE TABLE Employee (
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  job_start_date DATETIME,
  salary DECIMAL(10,2)
);
-- write a DDL command to add a new column "department"

ALTER TABLE Employee
ADD COLUMN department VARCHAR(50);

INSERT INTO Employee (first_name, last_name, job_start_date, salary, department)
VALUES
  ('John', 'Doe', '2023-01-01', 50000.00, 'Marketing'),
  ('Jane', 'Smith', '2023-02-01', 60000.00, 'Sales'),
  ('Michael', 'Johnson', '2023-03-01', 55000.00, 'IT'),
  ('Emily', 'Davis', '2023-04-01', 65000.00, 'HR'),
  ('Robert', 'Brown', '2023-05-01', 70000.00, 'Finance'),
  ('Jessica', 'Wilson', '2023-06-01', 45000.00, 'Marketing'),
  ('Daniel', 'Anderson', '2023-07-01', 55000.00, 'Sales'),
  ('Sarah', 'Taylor', '2023-08-01', 60000.00, 'IT'),
  ('David', 'Martinez', '2023-09-01', 70000.00, 'HR'),
  ('Olivia', 'Thomas', '2023-10-01', 80000.00, 'Finance'),
  ('William', 'Clark', '2023-11-01', 55000.00, 'Marketing'),
  ('Sophia', 'Lewis', '2023-12-01', 65000.00, 'Sales'),
  ('John', 'Doe', '2024-01-01', 50000.00, 'Marketing'),
  ('Jane', 'Smith', '2024-02-01', 60000.00, 'Sales'),
  ('Michael', 'Johnson', '2024-03-01', 55000.00, 'IT'),
  ('Emily', 'Davis', '2024-04-01', 65000.00, 'HR'),
  ('Robert', 'Brown', '2024-05-01', 70000.00, 'Finance'),
  ('Jessica', 'Wilson', '2024-06-01', 45000.00, 'Marketing'),
  ('Daniel', 'Anderson', '2024-07-01', 55000.00, 'Sales'),
  ('Sarah', 'Taylor', '2024-08-01', 60000.00, 'IT'),
  ('David', 'Martinez', '2024-09-01', 70000.00, 'HR'),
  ('Olivia', 'Thomas', '2024-10-01', 80000.00, 'Finance'),
  ('William', 'Clark', '2024-11-01', 55000.00, 'Marketing'),
  ('Sophia', 'Lewis', '2024-12-01', 65000.00, 'Sales');
-- Write a SQL query to find the highest salary from an "Employee" table.

SELECT MAX(salary) AS highest_salary
FROM Employee;

SELECT salary
FROM Employee e1
WHERE NOT EXISTS (
  SELECT *
  FROM Employee e2
  WHERE e2.salary > e1.salary
)
LIMIT 1;
-- write a query to find all the employees who joined in the last 6 months;

SELECT *
FROM Employee
WHERE job_start_date >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH);

-- write a query display number of employees in each department.

SELECT department, COUNT(*) AS employee_count
FROM Employee
GROUP BY department;

-- Submission Details

--Write a SQL to find total number of submissions for each constulant.
use submission;
SELECT consultant_id, COUNT(*) AS total_submissions
FROM submission_detail
GROUP BY consultant_id;

-- Write a SQL query to update email_address on the consultant_detail table
UPDATE consultant_detail SET email_address = 'new_email@example.com' WHERE id = 1;

 -- Write a SQL to find total number of submissions for each constulant by each submission day
SELECT c.first_name AS consultant_name, s.submission_date, COUNT(*) AS submission_count
FROM submission_detail s, consultant_detail c
WHERE s.consultant_id = c.id
GROUP BY c.first_name, s.submission_date;

 --write a SQL to delete all submissions where "rate" is null
DELETE FROM submission_detail
WHERE pay_rate IS NULL;

 -- Given a lead name and submission date, Write a SQL query to find the submissions.

SELECT *
FROM submission_detail s, lead_detail l, consultant_detail c
WHERE l.first_name = 'john' AND s.submission_date = '2023-07-18 10:30:00' AND l.id = c.lead_id AND c.id = s.consultant_id;


SELECT s.*
FROM submission_detail s, lead_detail l, consultant_detail c
WHERE l.first_name = 'john' AND s.submission_date = '2023-07-18 10:30:00' AND l.id = c.lead_id AND c.id = s.consultant_id;
    

SELECT s.* FROM submission_detail s INNER JOIN  lead_detail l   
    ON l.first_name = 'john' AND s.submission_date = '2023-07-18 10:30:00' 
    INNER JOIN consultant_detail c ON l.id = c.lead_id AND c.id = s.consultant_id;
    
SELECT s.*
FROM submission_detail s
INNER JOIN consultant_detail c ON s.consultant_id = c.id
INNER JOIN lead_detail l ON c.lead_id = l.id
WHERE l.first_name = 'John'
  AND l.last_name = 'doe'
  AND s.submission_date = '2023-07-18 10:30:00'

-- Write a SQL query to find the number of submissions by each lead.

SELECT l.first_name AS lead_name, COUNT(l.id) AS submission_count
FROM lead_detail l, submission_detail s, consultant_detail c
WHERE l.id = c.lead_id AND c.id = s.consultant_id GROUP BY l.first_name;

