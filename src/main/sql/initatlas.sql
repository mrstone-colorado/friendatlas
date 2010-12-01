DROP DATABASE IF EXISTS friendatlas;

CREATE DATABASE friendatlas;

USE friendatlas;

CREATE TABLE Users
(
   UserID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
   FirstName varchar(30) NOT NULL,
   LastName varchar(30) NOT NULL,
   Email    varchar(35) NOT NULL,
   Password varchar(30) NOT NULL
)                 ;

CREATE TABLE Friends
(
   ID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
   FirstName varchar(30) NOT NULL,
   LastName varchar(30) NOT NULL,
   Address varchar(100) NOT NULL,
   City varchar (40) NOT NULL,
   State varchar (30) NOT NULL,
   Zip int NOT NULL,
   Email varchar(35) NOT NULL,
   UserID  int NOT NULL,
   FOREIGN KEY (UserID) REFERENCES Users(UserID)   
)            ;

INSERT INTO Users (FirstName,LastName, Email, password) VALUES ('Ara','Adamian', 'Ara.Adamian@colorado.edu', 'atlas')           ;
INSERT INTO Users (FirstName,LastName, Email, password) VALUES ('Michael Ray','Stone', 'Michael.Stone@colorado.edu', 'atlas')     ;
INSERT INTO Users (FirstName,LastName, Email, password) VALUES ('John','Correa', 'John.Correa@colorado.edu', 'atlas')    ;

INSERT INTO Friends ( FirstName, LastName, Address, City, State, Zip, Email, UserID ) VALUES ('Bryan','Sanchez', '1424 King Street', 'Denver', 'Colorado',80204 , 'forskin@cocknballz.net', 3 )          ;
INSERT INTO Friends ( FirstName, LastName, Address, City, State, Zip, Email, UserID ) VALUES ('Buny','Lebowski', '3388 South Robertson Boulevard', 'Los Angeles', 'California', 90034, 'golddigger@hotmail.com', 2 )          ;
INSERT INTO Friends ( FirstName, LastName, Address, City, State, Zip, Email, UserID ) VALUES ('Jeffry (The Dude)','Lebowski', '609 Venezia Ave', 'Venice', 'California', 90291 , 'thedudeminds@msn.com', 3 )          ;

