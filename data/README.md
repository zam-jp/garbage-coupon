Database related files will be put in this folder.

### kusatsu_20230304.sql
- This file is a database dump of the web application dated 2023-03-04.
- It contains the script to build the required tables for the web application as well as all the test data. You only need to load it into a MySQL database to use it.

### User login
- Password for all users is 'test'.
- You can change this in the *users* table in the database.
- Passwords are encrypted, so you cannot read it directly from the table.
- If you forgot the password, since there is no way to decrypt it, you will need to update the password.
