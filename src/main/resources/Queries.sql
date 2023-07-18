create schema submission;
use submission;
create table lead_detail(
	id varchar(32),
	first_name varchar(60),
	last_name varchar(60),
	email_address varchar(60),
	phone_number varchar(15),
    primary key(id)
);
--- -drop table lead_detail;

create table consultant_detail(
	id varchar(32) NOT NULL,
	lead_id varchar(32) NOT NULL,
	first_name varchar(60),
	last_name varchar(60),
	email_address varchar(60),
	phone_number varchar(15),
    PRIMARY KEY(id),
    FOREIGN KEY(lead_id) REFERENCES lead_detail(id)
);
create table submission_detail(
	id varchar(32) not null,
	consultant_id varchar(32) not null,
	submission_date datetime,
	vendor_company varchar(60),
	vendor_name varchar(60),
	vendor_email_address varchar(60),
	vendor_phone_number varchar(15),
	implementation_partner varchar(60),
	cleint_name varchar(60),
	pay_rate varchar(5),
	submission_status varchar(100),
	submission_type varchar(10),
	city varchar(20),
	state varchar(2),
	zip integer,
    PRIMARY KEY(id),
    FOREIGN KEY(consultant_id) REFERENCES consultant_detail(id)
);

create table status_update(
	id varchar(32) not null,
	submission_id varchar(32) not null,
	update_text varchar(200),
	created_date datetime,
    PRIMARY KEY(id),
    FOREIGN KEY(submission_id) REFERENCES submission_detail(id)
);

use submission;
select * from submission_detail;
INSERT INTO lead_detail (id, first_name, last_name, email_address, phone_number)
VALUES
    ('1', 'John', 'Doe', 'john.doe@example.com', '1234567890'),
    ('2', 'Jane', 'Smith', 'jane.smith@example.com', '9876543210'),
    ('3', 'Michael', 'Johnson', 'michael.johnson@example.com', '4567890123'),
    ('4', 'Emily', 'Davis', 'emily.davis@example.com', '7890123456'),
    ('5', 'Robert', 'Brown', 'robert.brown@example.com', '2345678901'),
    ('6', 'Jessica', 'Wilson', 'jessica.wilson@example.com', '9012345678'),
    ('7', 'Daniel', 'Anderson', 'daniel.anderson@example.com', '3456789012'),
    ('8', 'Sarah', 'Taylor', 'sarah.taylor@example.com', '6789012345'),
    ('9', 'David', 'Martinez', 'david.martinez@example.com', '0123456789'),
    ('10', 'Olivia', 'Thomas', 'olivia.thomas@example.com', '5678901234');

INSERT INTO consultant_detail (id, lead_id, first_name, last_name, email_address, phone_number)
VALUES
    ('1', '1', 'John', 'Doe', 'john.doe@example.com', '1234567890'),
    ('2', '2', 'Jane', 'Smith', 'jane.smith@example.com', '9876543210'),
    ('3', '3', 'Michael', 'Johnson', 'michael.johnson@example.com', '4567890123'),
    ('4', '4', 'Emily', 'Davis', 'emily.davis@example.com', '7890123456'),
    ('5', '5', 'Robert', 'Brown', 'robert.brown@example.com', '2345678901'),
    ('6', '6', 'Jessica', 'Wilson', 'jessica.wilson@example.com', '9012345678'),
    ('7', '7', 'Daniel', 'Anderson', 'daniel.anderson@example.com', '3456789012'),
    ('8', '8', 'Sarah', 'Taylor', 'sarah.taylor@example.com', '6789012345'),
    ('9', '9', 'David', 'Martinez', 'david.martinez@example.com', '0123456789'),
    ('10', '10', 'Olivia', 'Thomas', 'olivia.thomas@example.com', '5678901234');

select * from consultant_detail;
select * from lead_detail;

INSERT INTO submission_detail (id, consultant_id, submission, vendor_company, vendor_name, vendor_email_address, vendor_phone_number, implementation_partner, client_name, pay_rate, submission_status, submission_type, city, state, zip)
VALUES
    ('1', '1', '2023-07-18 10:30:00', 'Vendor Company 1', 'Vendor Name 1', 'vendor1@example.com', '1234567890', 'Implementation Partner 1', 'Client Name 1', '100', 'Submitted', 'Type 1', 'City 1', 'ST', 12345),
    ('2', '2', '2023-07-18 11:45:00', 'Vendor Company 2', 'Vendor Name 2', 'vendor2@example.com', '9876543210', 'Implementation Partner 2', 'Client Name 2', '90', 'Approved', 'Type 2', 'City 2', 'ST', 23456),
    ('3', '3', '2023-07-18 13:15:00', 'Vendor Company 3', 'Vendor Name 3', 'vendor3@example.com', '4567890123', 'Implementation Partner 3', 'Client Name 3', '80', 'Rejected', 'Type 1', 'City 3', 'ST', 34567),
    ('4', '4', '2023-07-18 14:30:00', 'Vendor Company 4', 'Vendor Name 4', 'vendor4@example.com', '7890123456', 'Implementation Partner 4', 'Client Name 4', '70', 'Submitted', 'Type 2', 'City 4', 'ST', 45678),
    ('5', '5', '2023-07-18 15:45:00', 'Vendor Company 5', 'Vendor Name 5', 'vendor5@example.com', '2345678901', 'Implementation Partner 5', 'Client Name 5', '60', 'Approved', 'Type 1', 'City 5', 'ST', 56789),
    ('6', '6', '2023-07-18 17:00:00', 'Vendor Company 6', 'Vendor Name 6', 'vendor6@example.com', '9012345678', 'Implementation Partner 6', 'Client Name 6', '50', 'Rejected', 'Type 2', 'City 6', 'ST', 67890),
    ('7', '7', '2023-07-18 18:15:00', 'Vendor Company 7', 'Vendor Name 7', 'vendor7@example.com', '3456789012', 'Implementation Partner 7', 'Client Name 7', '40', 'Submitted', 'Type 1', 'City 7', 'ST', 78901),
    ('8', '8', '2023-07-18 19:30:00', 'Vendor Company 8', 'Vendor Name 8', 'vendor8@example.com', '6789012345', 'Implementation Partner 8', 'Client Name 8', '30', 'Approved', 'Type 2', 'City 8', 'ST', 89012),
    ('9', '9', '2023-07-18 20:45:00', 'Vendor Company 9', 'Vendor Name 9', 'vendor9@example.com', '0123456789', 'Implementation Partner 9', 'Client Name 9', '20', 'Rejected', 'Type 1', 'City 9', 'ST', 90123),
    ('10', '10', '2023-07-18 22:00:00', 'Vendor Company 10', 'Vendor Name 10', 'vendor10@example.com', '5678901234', 'Implementation Partner 10', 'Client Name 10', '10', 'Submitted', 'Type 2', 'City 10', 'ST', 01234),
    ('11', '1', '2023-07-18 10:30:00', 'Vendor Company 1', 'Vendor Name 1', 'vendor1@example.com', '1234567890', 'Implementation Partner 1', 'Client Name 1', '100', 'Submitted', 'Type 1', 'City 1', 'ST', 12345);
    


ALTER TABLE submission_detail
RENAME COLUMN cleint_name TO client_name;
