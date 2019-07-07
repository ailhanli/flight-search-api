### Flight Search API


Welcome the Flight Search API


This project is developed for Toki Games assesment and it uses below URLS to fetch flights;

https://tokigames-challenge.herokuapp.com/api/flights/cheap

https://tokigames-challenge.herokuapp.com/api/flights/business


### Used Technologies

In this project following techonologies used:

```
- Java 8
- Mongo DB v3.4.18
- Spring Boot 2.0
- Mockito
```

### Dependency

Following dependencies used in this project;

```
<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.6.RELEASE</version>
		<relativePath />
	</parent>

	<properties>
		<java.version>1.8</java.version>
		<maven-jar-plugin.version>3.1.1</maven-jar-plugin.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb-reactive</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.projectreactor</groupId>
			<artifactId>reactor-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-core</artifactId>
			<version>2.28.2</version>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

### Default Configuration

Here is the default application.properties defined in application

```
server.port=8095

#mongogb configuration
spring.data.mongodb.database=flightDB
spring.data.mongodb.username=demoUser
spring.data.mongodb.password=demoPassword
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017

#flight listener listens mock flight server for each 15minutes(900000 milliseconds)
flight.listener.interval=900000

cheap.flight.api.url=https://tokigames-challenge.herokuapp.com/api/flights/cheap
business.flight.api.url=https://tokigames-challenge.herokuapp.com/api/flights/business

page.number=0
page.size=10
sort.direction=ASC
```


### Before Build

First you need to install MongoDB on your machine or you can update mongo db settings defined in application.properties shared above.

To install MongoDB on your machine follow instructions at https://docs.mongodb.com/manual/administration/install-community/

Before run project, please be sure that you have already defined Mongo DB user in MongoDB like below;

```
db.createUser(
   {
     user: "demoUser",
     pwd: "demoPassword",
     roles: [ "readWrite", "dbAdmin" ]
   }
)
```

#### Logging file

This is the logback.xml to setup logging configuration

```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	
	<logger name="springfox.documentation" level="WARN" />
	<logger name="org.mongodb" level="DEBUG" />
	<logger name="org.springframework" level="INFO" />
	
	<appender name="STDOUT"
		class="ch.qos.logback.core.ConsoleAppender">
		<Target>System.out</Target>
		<encoder>
			<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
			</pattern>
		</encoder>
		<filter class="ch.qos.logback.classic.filter.ThresholdFilter">
			<level>INFO</level>
		</filter>
	</appender>
	<appender name="FILE" class="ch.qos.logback.core.FileAppender">
		<file>application.log</file>
		<append>true</append>
		<encoder>
			<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
			</pattern>
		</encoder>
	</appender>

	<root level="debug">
		<appender-ref ref="STDOUT" />
		<appender-ref ref="FILE" />
	</root>
</configuration>
```
Please note that by default console and file appender is active and application logs stored in application.log file under the project folder. 
During the deploy it on production, you can update log file path via changing logback.xml under the project folder.

### Build & Execution
First you have to be sure that you have already installed maven on you machine.

maven --version

To build project, execute below command;
```
- mvn clean package 
```

To run project use below commands after build;
```
- java -jar flight-search-api-1.0.jar
```

### Testing API
To test API, you can post JSON to URL shared below;

URL:
```
 http://localhost:8095/flight-api/v1/flight
```

JSON:
```
{
	"criteria":{
		"from":"Ankara",
		"to":"Antalya"
	}
}
```


### Code Guidelines

https://www.oracle.com/technetwork/java/javaee/downloads/codeconvtoc-136057.html

For more information about API, please check unit tests under the /src/test/*.Java


