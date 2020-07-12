# Scheduling Job Application

[![Build Status](https://travis-ci.com/erickmob/scheduling-job.svg?token=2zC8isA1MNDV9yuhWGtB&branch=master)](https://travis-ci.org/erickmob/scheduling-job)
[![codecov](https://codecov.io/gh/erickmob/scheduling-job/branch/master/graph/badge.svg?token=LYBQWF2RY9)](https://codecov.io/gh/erickmob/scheduling-job)
[![Maintainability](https://api.codeclimate.com/v1/badges/9365343e9e48d6d3a2f0/maintainability)](https://codeclimate.com/github/erickmob/scheduling-job/maintainability)

A simple spring project that shows the job's execution order; 

### Build With

- [OpenJDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Spring 2.3.1.RELEASE](https://spring.io) 
- [Spring boot](https://spring.io/projects/spring-boot)
- [Docker](https://www.docker.com)
- [Lombok](https://projectlombok.org)
- [jUnit 5](https://junit.org/junit5/) for unit tests
- [JaCoCo](https://www.eclemma.org/jacoco/) for tests reports
- [Travis](http://travis-ci.com) for CI and build status
- [codecov.io](https://codecov.io) for Code coverage
- [codeclimate](https://codeclimate.com)for code maintainability
    
### Prerequisites to run it

    - OpenJDK 11
    - Maven 3.6.1
    - Docker (Optional)

### Clone this repo
Clone the repository
```zsh
git clone https://github.com/erickmob/scheduling-job.git
```

Enter on the root folder
```zsh
cd scheduling-job/
```

### With Maven
To run the application with maven:
```zsh
mvn spring-boot:run
```

To run all tests:
```zsh
mvn test
```
mvn test

### With Docker
To run the application with docker:
```zsh
docker-compose up web
```

###JaCoCo Coverage

You can also see the covegare test on:
```
target/site/jacoco/index.html
```

### Author
Erick de Miranda Oliveira (@erickmob)

- [Github](https://github.com/erickmob/) 
- [LinkedIn](https://www.linkedin.com/in/erickmob/) 
- [Gmail](mailto:erickmob@gmail.com)