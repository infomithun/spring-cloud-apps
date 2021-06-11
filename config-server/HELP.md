# Install HashiCorp Vault:

1. Run the Vault server

   `vault server -dev`

2. In another command Window execute the following

   _`set VAULT_ADDR=http://127.0.0.1:8200`_

3. Add the required properties to be vaulted in a Json file

   *JSON file content:*

   `_{
      "spring.cloud.config.server.git.username": "USER_NAME",
      "spring.cloud.config.server.git.password": "PASSWORD"
      }_`

4. Navigate to the path of the JSON file and execute the command 

   _`vault kv put secret/config-server @secrets.json`_

5. To check the values are correct 

   _`vault kv get secret/config-server`_

