Install HashiCorp Vault:

1) Run the Vault server
    _vault server -dev_
   
2) In another command Window execute the following
   _set VAULT_ADDR=http://127.0.0.1:8200_
   
3) Add the required properties to be vaulted in a Json file

   JSON file content:
   _{
   "spring.cloud.config.server.git.username": "USER_NAME",
   "spring.cloud.config.server.git.password": "PASSWORD"
   }_
   
4) Navigate to the path of the JSON file and execute the command   
   _vault kv put secret/config-server @secrets.json_
   
5) To check the values are correct
   _vault kv get secret/config-server_
   