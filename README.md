# Installing Softwares in Ubuntu running on Windows Subsystem for Linux



## Enable Windows SubSystem for Linux and Install Ubuntu

- Enable **Windows SubSystem for Linux** feature enabled in Windows

  Type *Turn Windows features on or off* in windows search bar, a Window will open in that select option 	*Windows SubSystem for Linux*, and press OK, then restart your Windows machine.

- Install *Ubuntu* from *Microsoft Store*


- Update your Ubuntu packages: 

  ```shell
  sudo apt update
  ```

  

## Enable systemd

Script to enable systemd support on current Ubuntu WSL2 images from the Windows store.

- Install git:

  ```shell
  sudo apt install git
  ```

- Run the script and commands

  ```shell
  git clone https://github.com/DamionGans/ubuntu-wsl2-systemd-script.git
  cd ubuntu-wsl2-systemd-script/
  bash ubuntu-wsl2-systemd-script.sh
  ```

- Restart the Ubuntu shell and try running *systemctl*

  ```shell
  systemctl
  ```

  If you don't get an error and see a list of units, the script worked.



## Install MySQL

- Install MySQL: 

  ```shell
  sudo apt install mysql-server
  ```

- Confirm installation and get the version number: 

  ```shell
  mysql --version
  ```

- Start a MySQL server: 

  ```shell
  sudo service mysql start
  ```

  After the server started, check the status by

  ```shell
  sudo service mysql status
  ```

- Start the security script prompts:

  ```shell
  sudo mysql_secure_installation
  ```

  Here set the root password and complete the steps.

- Open the MySQL client:

  ```shell
  sudo mysql
  ```

-  Create the project database and table

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

### Troubleshooting:

If unable to connect to MySQL, then perform the below:

###### Error:

`Access denied for user 'root'@'localhost'` 

###### Solution:

```sql
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'ROOT_PASSWORD';
FLUSH PRIVILEGES;
```



## Install Redis

- Install Redis:

  ```shell
  sudo apt install redis-server
  ```

- Check the Version of Redis installed:

  ```shell
  redis-server --version
  ```

- Start Redis-Server:

  ```shell
  sudo service redis-server start
  ```

- Check to see if redis is working `redis-cli ping` this should return a reply of "PONG".

  ```shell
  redis-cli
  127.0.0.1:6379>ping
  ```

#### Other Commands:

###### Stop Redis-Server:

```shell
sudo service redis-server stop
```

###### Check Redis-Server status:

```shell
sudo service redis-server status
```

###### Check all the services running in Ubuntu running on Windows 10:

```shell
service --status-all
```
