CREATE TABLE IF NOT EXISTS Address (
  ID         INT PRIMARY KEY AUTO_INCREMENT,
  Street     VARCHAR(64),
);

INSERT INTO Address VALUES (NULL, 'testaddr1');

INSERT INTO Address VALUES (NULL, 'testaddr2');

INSERT INTO Address VALUES (NULL, 'testaddr3');


CREATE TABLE IF NOT EXISTS Customer (
  ID        INT PRIMARY KEY AUTO_INCREMENT,
  FIRSTNAME VARCHAR(64),
  LASTNAME  VARCHAR(64),
  AddrID INT,
  FOREIGN KEY (AddrID) REFERENCES Address (ID)
);

INSERT INTO Customer VALUES (null, 'test1firstname', 'test1lastname',1);

INSERT INTO Customer VALUES (null, 'test2firstname', 'test2lastname',2);

INSERT INTO Customer VALUES (null, 'test3firstname', 'test3lastname',2);

INSERT INTO Customer VALUES (null, 'test4firstname', 'test4lastname',3);

