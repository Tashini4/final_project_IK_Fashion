mysql -u root -p
create database IkFashion;
use IkFashion;
create table customers(
                          customerId VARCHAR(5) PRIMARY KEY,
                          customerName VARCHAR(30),
                          customerEmail VARCHAR(30),
                          customerContact VARCHAR(15),
                          customerAddress VARCHAR(50)
);

create table payments(
                         paymentId VARCHAR(5) PRIMARY KEY,
                         paymentAmount DECIMAL(5,3),
                         paymentDate DATE
);

create table orders(
                       orderId VARCHAR(5) PRIMARY KEY,
                       orderDescription VARCHAR(30),
                       orderDate DATE,
                       customerID VARCHAR(5),
                       paymentID VARCHAR(5),
                       foreign key (customerId) references customers(customerId)on DELETE cascade on UPDATE cascade,
                       foreign key (paymentId) references payments(paymentId)on UPDATE cascade on DELETE cascade
);

create table employees(
                          employeeId VARCHAR(5)PRIMARY KEY,
                          employeeName VARCHAR(30),
                          employeeEmail VARCHAR(30),
                          employeeContact VARCHAR(15),
                          employeeAddress VARCHAR(50),
                          employeeGender VARCHAR(20)
);

create table tasks(
                      taskId VARCHAR(5) PRIMARY KEY,
                      taskDescription VARCHAR(30)
);

create table suppliers(
                          supplierId VARCHAR(5) PRIMARY KEY,
                          supplierName VARCHAR(30),
                          supplierEmail VARCHAR(30),
                          supplierContact VARCHAR(15),
                          supplierAddress VARCHAR(50)
);

create table employeeTask(
                             taskId VARCHAR(5),
                             employeeId VARCHAR(5),
                             foreign key (taskId) references tasks(taskId)on DELETE cascade on UPDATE cascade,
                             foreign key (employeeId) references employees(employeeId)on UPDATE cascade on DELETE cascade
);

create table inventory(
                          inventoryId VARCHAR(5) PRIMARY KEY,
                          qty INT,
                          costPrice DECIMAL(10,3),
                          sellingPrice DECIMAL(10,3),
                          supplierId VARCHAR(5),
                          foreign key (supplierId) references suppliers(supplierId)on UPDATE cascade on DELETE cascade
);

create table items(
                      itemId VARCHAR(5)PRIMARY KEY,
                      itemDescription VARCHAR(30),
                      price DECIMAL(6,3),
                      size VARCHAR(6),
                      color VARCHAR(30),
                      category VARCHAR(25),
                      inventoryId VARCHAR(5),
                      foreign key (inventoryId) references inventory(inventoryId)on UPDATE cascade on DELETE cascade
);

create table orderEmployee(
                              orderId VARCHAR(5),
                              employeeId VARCHAR(5),
                              startTime dateTime,
                              endTime dateTime,
                              foreign key (orderId) references orders(orderId)on DELETE cascade on UPDATE cascade,
                              foreign key (employeeId) references employees(employeeId)on UPDATE cascade on DELETE cascade
);

create table orderItem(
                          itemId VARCHAR(5),
                          orderId VARCHAR(5),
                          qty INT,
                          unitPrice DECIMAL(10,3),
                          total DECIMAL(8,3),
                          foreign key (itemId) references items(itemId)on DELETE cascade on UPDATE cascade,
                          foreign key (orderId) references orders(orderId)on UPDATE cascade on DELETE cascade
);

create table salary(
                       salaryId VARCHAR(5) PRIMARY KEY,
                       salaryAmount DECIMAL(10,3),
                       salaryDate DATE,
                       employeeId VARCHAR(5),
                       foreign key (employeeId) references employees(employeeId)on UPDATE cascade on DELETE cascade
);

create table users(
                      userId VARCHAR(30) PRIMARY KEY ,
                      userName VARCHAR(30),
                      password VARCHAR(10)
);

insert into users values ('U001','Chamindu','12345');
insert into users values ('U002','Indi','23456');

create table register(
                          registerId VARCHAR(10) PRIMARY KEY,
                          registerName VARCHAR(50) ,
                          registerPosition VARCHAR(50) ,
                          registerPassword VARCHAR(30)
);

INSERT INTO register VALUES('R001','Tashini','cashier','tashi2002###');


