greeter: Greeter Example
========================
Author: Pete Muir
Level: Beginner
Technologies: CDI, JSF, JPA, EJB, JTA
Summary: Demonstrates the use of CDI 1.0, JPA 2.0, JTA 1.1, EJB 3.1 and JSF 2.0
Target Product: EAP

What is it?
-----------

This example demonstrates the use of *CDI 1.0*, *JPA 2.0*, *JTA 1.1*, *EJB 3.1* and *JSF 2.0* in *JBoss Enterprise Application Platform 6* or *JBoss AS 7*.

When you deploy this example, two users are automatically created for you:  `emuster` and `jdoe`. This data is located in the `src/main/resources/import.sql file`.

To test this example:

1. Enter a name in the `username` field and click on `Greet!`.
2. If you enter a username that is not in the database, you get a message `No such user exists!`.
3. If you enter a valid username, you get a message "Hello, " followed by the user's first and last name.
4. To create a new user, click the `Add a new user` link. Enter the username, first name, and last name and then click `Add User`. The user is added and a message displays the new user id number.
5. Click on the `Greet a user!` link to return to the `Greet!` page.


Data source must be defined in Data source defination in standalone-ha.xml
```xml

				<datasource jndi-name="java:jboss/datasources/MySqlDS" pool-name="MySqlDS">
                    <connection-url>jdbc:mysql://localhost:3306/test&lt;/connection-url>
                    <driver>com.mysql</driver>
                    <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
                    <pool>
                        <min-pool-size>10</min-pool-size>
                        <max-pool-size>100</max-pool-size>
                        <prefill>true</prefill>
                    </pool>
                    <security>
                        <user-name>root</user-name>
                        <password>root</password>
                    </security>
                    <statement>
                        <prepared-statement-cache-size>32</prepared-statement-cache-size>
                        <share-prepared-statements>true</share-prepared-statements>
                    </statement>
                </datasource>
```
Also and mysql driver:
```xml 
                <drivers>
                    <driver name="h2" module="com.h2database.h2">
                        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                    </driver>
                    <driver name="com.mysql" module="com.mysql">
                        <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
                    </driver>
                </drivers>
```
These logs must be useful:
```xml
 			<logger category="org.infinispan">
                <level name="TRACE"/>
            </logger>
            <logger category="org.hibernate.cache.infinispan">
                <level name="TRACE"/>
            </logger>
```

There is a tutorial for this quickstart in the [Getting Started Developing Applications Guide](http://www.jboss.org/jdf/stage/quickstarts/jboss-as-quickstart/guide/GreeterQuickstart/).

System requirements
-------------------

All you need to build this project is Java 6.0 (Java SDK 1.6) or better, Maven 3.0 or better.

The application this project produces is designed to be run on JBoss Enterprise Application Platform 6 or JBoss AS 7. 

 
Configure Maven
---------------

If you have not yet done so, you must [Configure Maven](../README.md#mavenconfiguration) before testing the quickstarts.


Start JBoss Enterprise Application Platform 6 or JBoss AS 7 with the Web Profile
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server:

        Instance 1: standalone.sh -c standalone-ha.xml -Djboss.node.name=node1
		Instance 2: standalone.sh -c standalone-ha.xml -Djboss.socket.binding.port-offset=100 -Djboss.node.name=node2

 
Build and Deploy the Quickstart
-------------------------

_NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See [Build and Deploy the Quickstarts](../README.md#buildanddeploy) for complete instructions and additional options._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        Deploy app to instance 1: mvn clean package jboss-as:deploy
		Deploy app to instance 2: mvn clean package jboss-as:deploy -Djboss-as.port=10099

4. This will deploy `target/jboss-as-greeter.war` to the running instance of the server.
5. Undeploy app:
		Undeploy from instance 1: mvn clean package jboss-as:undeploy
		Undeploy from instance 2: mvn clean package jboss-as:undeploy -Djboss-as.port=10099

Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/jboss-as-greeter> and <http://localhost:8180/jboss-as-greeter/greet.jsf>



Debug the Application
------------------------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

        mvn dependency:sources
        mvn dependency:resolve -Dclassifier=javadoc

