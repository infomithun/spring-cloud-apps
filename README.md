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



# Install HashiCorp Vault:

- Get the latest version of Vault:

  ```shell
  wget https://releases.hashicorp.com/vault/1.7.2/vault_1.7.2_linux_amd64.zip
  ```

- Unzip the downloaded package:

  ```shell
  unzip vault_1.7.2_linux_amd64.zip
  sudo mv vault /usr/bin
  ```

- Go to the vault directory:

  ```shell
  cd /usr/bin
  ```

- Check the version of Vault installed

  ```she
  vault --version
  ```

- Configure Vault data directory

  ```shell
  sudo mkdir /etc/vault
  sudo mkdir -p /var/lib/vault/data
  ```

- Create Vault config file:

  ```shell
  sudo touch /etc/vault/config.hcl
  sudo nano /etc/vault/config.hcl
  ```

  File Content:

  ```shell
  disable_cache = true
  disable_mlock = true
  ui = true
  listener "tcp" {
     address          = "0.0.0.0:8200"
     tls_disable      = 1
      
  }
  storage "file" {
     path  = "/var/lib/vault/data"
   }
  api_addr         = "http://0.0.0.0:8200"
  max_lease_ttl         = "10h"
  default_lease_ttl    = "10h"
  cluster_name         = "vault"
  raw_storage_endpoint     = true
  disable_sealwrap     = true
  disable_printable_check = true
  ```

- Create a Vault service file

  ```shell
  sudo touch /etc/systemd/system/vault.service
  sudo nano /etc/systemd/system/vault.service
  ```

  File content:

  ```shell
  [Unit]
  Description="HashiCorp Vault - A tool for managing secrets"
  Documentation=https://www.vaultproject.io/docs/
  Requires=network-online.target
  After=network-online.target
  ConditionFileNotEmpty=/etc/vault/config.hcl
  
  [Service]
  ProtectSystem=full
  ProtectHome=read-only
  PrivateTmp=yes
  PrivateDevices=yes
  SecureBits=keep-caps
  AmbientCapabilities=CAP_IPC_LOCK
  NoNewPrivileges=yes
  ExecStart=/usr/bin/vault server -config=/etc/vault/config.hcl
  ExecReload=/bin/kill --signal HUP 
  KillMode=process
  KillSignal=SIGINT
  Restart=on-failure
  RestartSec=5
  TimeoutStopSec=30
  StartLimitBurst=3
  LimitNOFILE=6553
  
  [Install]
  WantedBy=multi-user.target
  ```

- Start and enable vault service to start on OS boot

  ```shell
  systemctl daemon-reload
  systemctl start vault
  systemctl enable vault
  sudo service vault status
  ```

- Export `VAULT_ADDR` environment variable

  ```shell
  export VAULT_ADDR=http://127.0.0.1:8200
  echo "export VAULT_ADDR=http://127.0.0.1:8200" >> ~/.bashrc
  sudo rm -rf  /var/lib/vault/data/*
  ```

- Initialize Vault server:

  ```shell
  vault operator init
  ```

- Copy the Unseal Keys and token in a file (Sample is given below)

  ```shell
  Unseal Key 1: m6MKVfARMjnnijBLqFstp6PxoIJdiYhY7vyvHMAcRock
  Unseal Key 2: YtraScwdajP6Lta2QuUu0FoffLIjBTNU6tpIIVebSwya
  Unseal Key 3: fvGrpQIW14sRmbOlSaN4ilJWUCmFhfNVO4BhOZG3vBVZ
  Unseal Key 4: irJjINjZIsIibLrcNKBN7ywSg+vcSDLfURegWCbmgUjF
  Unseal Key 5: sOLZs5b5O4g6AoRuTG0nnWJsxJnsOo/FYZyNAmjHpi6x
  
  Initial Root Token: s.TGkKqJSow9rzp9hTxaoQObEb
  ```

- Access Vault UI 

  [Vault UI](http://localhost:8200/ui)

  Try with the copied keys one by one to unseal the vault. After unsealed paste the token to Sign in

- Check the vault status and export the VAULT_TOKEN

  ```shell
  export VAULT_TOKEN="s.TGkKqJSow9rzp9hTxaoQObEb"
  vault auth list
  ```

- Enable the secret storage

  ```shell
  vault secrets enable -path=secret kv
  vault secrets list -detailed
  ```

- Add the required properties to be vaulted in a Json file

  ```shell
  sudo touch /usr/local/secrets.json
  sudo nano /usr/local/secrets.json
  ```

  *JSON file content:*

  ```json
  {
  	"spring.cloud.config.server.git.username": "USER_NAME",
  	"spring.cloud.config.server.git.password": "PASSWORD"
  }
  ```

  Load the secrets to Vault from JSON file

  ```shell
  vault kv put secret/config-server @/usr/local/secrets.json
  ```

  To check the values are correct 

  ```shell
  vault kv get secret/config-server
  ```

  

##### Troubleshooting:

###### To install unzip utility:

`sudo apt-get install -y unzip`
