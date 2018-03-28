# Switchfully

www.switchfully.com

## Örder

-  Large exercise to conclude the following modules:
    - Dependency Management
    - Spring (boot)
    - REST
- Contains the basic skeleton of the application (not the full implementation):
    - Multi-module Maven setup
    - Integration test configuration
    - Spring boot executable
    - Logging
        - To enable logging, we need to depend on a logging library, framework. If we want to use Logback as our logging framework,
         we need to include it (and jcl-over-slf4j (which implements the Commons Logging API)) 
        on the classpath as a dependency. The simplest way to do that is through the Spring boot Starters which all depend on 
        spring-boot-starter-logging (which contains the required dependencies such as Logback). For a web application you only need   spring-boot-starter-web since it depends transitively on the logging Starter.
    - Jenkins `Jenkinsfile`
        - Jenkins requires the Pipeline plugin
        - Jenkins requires the Build Monitor View
        - Jenkins runs as a service (task manager) on Windows
    - Event Publishing with Spring (!)
        - (to update the stock of an item when an order is created in a very decoupled way)

## Usage

- Run `mvn clean install`
- Multiple ways of running:
    - Inside the target folder of jar, run the `war-1.0-SNAPSHOT.jar` using the `java -jar` command
    - Run Application.java inside the IDE
    - Execute command `mvn spring-boot:run` from inside module (folder) war
- Surf to `http://localhost:9000/example`
