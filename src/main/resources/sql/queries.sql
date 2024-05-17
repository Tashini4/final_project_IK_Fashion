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
                       salaryDate DATE not null ,
                       salaryAmount DECIMAL(15,3) not null ,
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



