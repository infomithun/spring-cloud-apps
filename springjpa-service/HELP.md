# Installing MySQL on Windows - Ubuntu App

##### Step 1: 

Enable **Windows SubSystem for Linux** feature enabled in Windows

- Type *Turn Windows features on or off* in windows search bar, a Window will open in that select option 	*Windows SubSystem for Linux*, and press OK, then restart your Windows machine.

##### Step 2: 

Install *Ubuntu* from *Microsoft Store*

##### Step 3:

Install *MySQL* on the installed Ubuntu

- Update your Ubuntu packages: 

  `sudo apt update`

- Once the packages have updated, install MySQL with: 

  `sudo apt install mysql-server`

- Confirm installation and get the version number: 

  `mysql --version`

- Start a MySQL server: 

  `sudo service mysql start`

  After the server started, check the status by

  `sudo service mysql status`

- Start the security script prompts:

  `sudo mysql_secure_installation`

  Here set the root password and complete the steps.

- Open the MySQL client:

  `sudo mysql`

-  Execute the below SQL scripts to Create the project database and table

  ```sql
  CREATE DATABASE <DATABASE_NAME>;
  SHOW DATABASES;
  USE <DATABASE_NAME>;
  
  CREATE TABLE IF NOT EXISTS EMPLOYEE (
     id INT AUTO_INCREMENT PRIMARY KEY,
      first_name VARCHAR(50) NOT NULL,
      last_name VARCHAR(50) NOT NULL,
      age INT NOT NULL,
      designation VARCHAR(100) NOT NULL
  );
  
  DESCRIBE EMPLOYEE;
  
  select * from EMPLOYEE;
  ```

