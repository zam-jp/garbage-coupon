### kusatsu-portal.xml
- This file should be copied to the folder: ~/tomcat/conf/Catalina/localhost/
- This file is a configuration file which defines the DataSource object and the security realm for Tomcat to use.
- This web application uses DataSource object to connect to the database. It is also defined in the web.xml file located in WEB-INF folder in the web application folder.
- This web application uses Tomcat's security realm to authenticate users during login.