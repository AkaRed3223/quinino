# Q.uinino Application
### :star_struck:Welcome to the Q.uinino Application!!!:star_struck:

Q.uinino is a phone company specialized in long distance calls. 
At this moment we're building a new product, :telephone_receiver:FaleMais:telephone_receiver:

Currently, a Q.uinino customer is able to make city-to-city calls paying a pre-established dial rate, according to the list of origin and destination state codes,
however, with the new FaleMais plan, you're able to make calls without worrying where you're calling!

This is why this API exists. It provides services where you can verify the plans that are currently being offered in the market and its fares.

:scream:BUT WAIT! THERE'S MORE!:scream:

You can also simulate how much you're going to pay in advance, considering the location of the calls and which minute package you're choosing!

Implemented using:
- TDD
- Clean Architecture / Decoupling
- Interfaces
- Java 14 Records
- Overloading technique
- SpringBoot @Autowired for dependency injection
- Multiple HTTP methods for retrieving and posting information
- Parameterized Unit testing
- Ready to Plug-in database
  - The /repository classes have been designed as a DAO layer, all you have to do is replace it to your own database and adjust the domain objects

# How to Run (without Docker)

## Install Java JDK (Version 14 or higher)
This application makes use of Records, which have been implemented in Java 14, so make sure your jdk is up to date :kissing_smiling_eyes:

Follow this tutorial if you're in a pinch :eyes:
https://www.codejava.net/java-se/download-and-install-openjdk-17

## Install Maven
(I see you not having the default tools :face_in_clouds: ... but anyways, here's your savior)
https://maven.apache.org/download.cgi

## Clone the project (I assume you have git installed, right?... RIGHT?:scream:	)

## R-U-N
```sh 
mvn clean compile
```

all good? then:

```sh 
mvn spring-boot:run
```
The application will run on default port 8080
(no need to complicate, after all)

# How to Run (with Docker)
```sh 
docker build -t spring-boot-docker.jar .
```
```sh 
docker run -p 8080:8080 spring-boot-docker.jar
```

Last but not least, in the /collection folder you can find a json of a pre-built postman collection so you can start calculating right away!