CREATE SCHEMA freshman_application;

USE freshman_application;

CREATE TABLE freshman_application.departments(
	`DEPARTMENT_ID` int not null,
    `DEPARTMENT_NAME` varchar(45) not null,
    PRIMARY KEY (`DEPARTMENT_ID`)
);

CREATE TABLE freshman_application.programs(
	`PROGRAM_ID` int not null,
    `PROGRAM_NAME` varchar(45) not null,
    `DEPARTMENT_ID` int not null,
    PRIMARY KEY (`PROGRAM_ID`),
    FOREIGN KEY (`DEPARTMENT_ID`) references departments(`DEPARTMENT_ID`)
);

CREATE TABLE freshman_application.applications(
	`APPLICATION_NO` int(15)  not null auto_increment,
    `LAST_NAME` varchar(45) not null,
    `FIRST_NAME` varchar(45) not null,
    `MIDDLE_NAME` varchar(45) not null,
    `BIRTHDAY` date not null,
    `GENDER` varchar(10),
	`PHONE_NUMBER` varchar(20),
    `ADDRESS` varchar(255),
	`PROGRAM_ID` int not null,
    `APPLICATION_STATUS` varchar(20) DEFAULT 'Submitted',
    PRIMARY KEY(`APPLICATION_NO`),
    FOREIGN KEY (`PROGRAM_ID`) references programs(`PROGRAM_ID`)
);

