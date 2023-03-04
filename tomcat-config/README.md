Tomcat configuration related files will be put into this folder.

### kusatsu-portal.xml
- This file should be copied to the folder: ~/tomcat/conf/Catalina/localhost/
- This file is a configuration file which defines the DataSource object and the security realm for Tomcat to use.
- This web application uses DataSource object to connect to the database. It is also defined in the web.xml file located in WEB-INF folder in the web application folder.
- This web application uses Tomcat's security realm to authenticate users during login.
- The security realm also controls what a user can access on the web application. This will be defined in the web.xml file.
