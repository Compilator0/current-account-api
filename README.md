# current-account-api

**This is a spring boot application.**  
The purpose of the application is to propose an API to be used for opening a new “current account” of already existing customers.


REQUIREMENTS
------------

JDK-11  
Maven 3.6.x
An IDE (i've used Intllij 2019.2.3)


SOMES USED DEPENDENCIES
-----------------------
LOMBOK
JMAPPER
SPRING DOC / OPEN API UI
APACHE COMMONS-LANG3
H2 DATABASE


## Installation

Use Maven commands to build and run the application.

```bash
mvn clean # To clean the project.
mvn install # To compile the code and build the jar. The jar generated is in the amazingbank/target subdirectory.
```

## Run Junit tests with maven

```
mvn test
```

## Run the application
The API can runs in two modes:

**Standard mode :**
Go to the "target" at the project's root folder and type the command :
```
java -jar amazingbank-0.0.1-SNAPSHOT.jar # run in standard mode from the .jar snapshot.
```

**Using a dockerfile :**
Prerequisite : Make sure to have docker environment installed!
The dockerfile to run the API can be found in the subFolder "/ops" at the root of the project.

After the jar is builded by maven, navigate to the project's root folder and type the commands below in a bash :
```
> docker build -t amazingbank -f ops/Dockerfile .
> docker run -d -p 8284:8284 --name amazingbank -it amazingbank
```

## Accessing to the API Database
To access the H2 Console realtime database, open the url : http://localhost:8284/h2-console 
 => JDBC URL : jdbc:h2:mem:cab-database 
 => user: cabuser 
 => password: cabPassword0

## Accessing to the API services via swagger-ui client

Once the SpringBoot application is lauched, you can access the API in a web browser through the url : http://localhost:8284/swagger-ui.html


## Code Documentation

Kindly find the generated code documentation of the application in **java-doc/** directory at the root of the project.

Enjoy !!!

## License
@Author : **Idris TSAFACK / junidryves@yahoo.fr** - Software Engineer.  
