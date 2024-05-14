mysql -u root -p
create database IkFashion;
use IkFashion;
create table customers(
                          customerId VARCHAR(5) PRIMARY KEY,
                          customerName VARCHAR(30) not null ,
                          customerEmail VARCHAR(30) not null ,
                          customerContact VARCHAR(15) not null ,
                          customerAddress VARCHAR(50) not null
);
insert into customers values ('C001','Tashini','tashini@gmail.com','0783415581','Badugama');
insert into customers values ('C002','Chaminda','chami@gmail.com','0712714329','Pimbura');
insert into customers values ('C003','Kalum','kalum@gmail.com','0712749585','Aluthgama');
insert into customers values ('C004','Pathumi','p@gmail.com','0755911647','Colombo');
insert into customers values ('C005','Pawani','pawani@gmail.com','0752247352','Bopitiya');


create table payments(
                         paymentId VARCHAR(5) PRIMARY KEY,
                         paymentAmount DECIMAL(15,2)  not null ,
                         paymentDate DATE not null
);


create table orders(
                       orderId VARCHAR(5) PRIMARY KEY,
                       OrderDate DATE not null ,
                       customerID VARCHAR(5) not null ,
                       paymentID VARCHAR(5) not null ,
                       foreign key (customerId) references customers(customerId)on DELETE cascade on UPDATE cascade,
                       foreign key (paymentId) references payments(paymentId)on UPDATE cascade on DELETE cascade
);

create table employees(
                          employeeId VARCHAR(5)PRIMARY KEY,
                          employeeName VARCHAR(30) not null ,
                          employeeEmail VARCHAR(30) not null ,
                          employeeContact VARCHAR(15) not null ,
                          employeeAddress VARCHAR(50) not null ,
                          employeeGender VARCHAR(20) not null
);
insert  into employees values ('E001','Tharuka','tharu@gmail.com','0786543129','Baduraliya','FEMALE');
insert into employees values ('E002','Sathsarani','sathi@gmail.com','0754321567','Colombo','FEMALE');

create table tasks(
                      taskId VARCHAR(5) PRIMARY KEY,
                      taskDescription VARCHAR(30) not null
);

create table suppliers(
                          supplierId VARCHAR(5) PRIMARY KEY,
                          supplierName VARCHAR(30)not null ,
                          supplierEmail VARCHAR(30)not null ,
                          supplierContact VARCHAR(15) not null ,
                          supplierAddress VARCHAR(50) not null
);
insert into suppliers values ('S001','Nalin','n@123gmail,com','0765489432','Panadura');
insert into suppliers values ('S002','Kariyawasam','k12@gmail.com','076538902','Malabada');

create table employeeTask(
                             taskId VARCHAR(5) not null ,
                             employeeId VARCHAR(5) not null ,
                             foreign key (taskId) references tasks(taskId)on DELETE cascade on UPDATE cascade,
                             foreign key (employeeId) references employees(employeeId)on UPDATE cascade on DELETE cascade
);

create table inventory(
                          inventoryId VARCHAR(5) PRIMARY KEY,
                          qty INT not null ,
                          costPrice DECIMAL(15,2) not null ,
                          sellingPrice DECIMAL(15,2) not null ,
                          supplierId VARCHAR(5) not null ,
                          foreign key (supplierId) references suppliers(supplierId)on UPDATE cascade on DELETE cascade
);
INSERT INTO inventory values ('IN001',10,400.00,600.00,'S001');
insert into inventory values ('IN002',15,500.00,1000.00,'S002');

create table items(
                      itemId VARCHAR(5)PRIMARY KEY,
                      Description VARCHAR(30)not null ,
                      brand VARCHAR(30) not null ,
                      size VARCHAR(6) not null ,
                      price DECIMAL(15,2) not null ,
                      qtyOnHand VARCHAR(30) not null ,
                      inventoryId VARCHAR(5) not null ,
                      foreign key (inventoryId) references inventory(inventoryId)on UPDATE cascade on DELETE cascade
);
insert into items values ('I001','Shirt','Mouse','XL','500.00','2','IN001');
insert into items values ('I003','Frock','ALine','S','1000.00','1','IN002');

create table orderEmployee(
                              orderId VARCHAR(5)not null ,
                              employeeId VARCHAR(5) not null ,
                              startTime dateTime not null ,
                              endTime dateTime not null ,
                              foreign key (orderId) references orders(orderId)on DELETE cascade on UPDATE cascade,
                              foreign key (employeeId) references employees(employeeId)on UPDATE cascade on DELETE cascade
);

create table orderDetails(
                          itemId VARCHAR(5) not null ,
                          orderId VARCHAR(5) not null ,
                          qty INT not null ,
                          unitPrice DECIMAL(15,3) not null ,
                          total DECIMAL(15,3) not null ,
                          foreign key (itemId) references items(itemId)on DELETE cascade on UPDATE cascade,
                          foreign key (orderId) references orders(orderId)on UPDATE cascade on DELETE cascade
);

create table salary(
                       salaryId VARCHAR(5) PRIMARY KEY,
                       salaryAmount DECIMAL(15,3) not null ,
                       salaryDate DATE not null ,
                       employeeId VARCHAR(5) not null ,
                       foreign key (employeeId) references employees(employeeId)on UPDATE cascade on DELETE cascade
);

create table users(
                      userId VARCHAR(30) PRIMARY KEY ,
                      userName VARCHAR(30)not null  ,
                      password VARCHAR(10) not null
);

insert into users values ('U001','Chamindu','12345');
insert into users values ('U002','Indi','23456');

create table register(
                          registerId VARCHAR(10) PRIMARY KEY,
                          registerName VARCHAR(50) not null ,
                          registerPosition VARCHAR(50) not null ,
                          registerPassword VARCHAR(30) not null
);

INSERT INTO register VALUES('R001','Tashini','cashier','tashi2002###');


