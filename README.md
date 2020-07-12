# Scheduling Job Application

[![Build Status](https://travis-ci.com/erickmob/scheduling-job.svg?token=2zC8isA1MNDV9yuhWGtB&branch=master)](https://travis-ci.org/erickmob/scheduling-job)
[![codecov](https://codecov.io/gh/erickmob/scheduling-job/branch/master/graph/badge.svg?token=LYBQWF2RY9)](https://codecov.io/gh/erickmob/scheduling-job)
[![Maintainability](https://api.codeclimate.com/v1/badges/9365343e9e48d6d3a2f0/maintainability)](https://codeclimate.com/github/erickmob/scheduling-job/maintainability)

A simple spring project that shows the job's execution order; 

### The problem

```
Scheduling Job

Dado um array de "jobs" para execução, no qual cada posição possui um objeto com os
seguintes atributos:

1) ID: Identificação do Job;
2) Descrição: Descrição do Job;
3) Data Máxima de conclusão do Job: Data máxima em que o Job deve ser concluído;
4) Tempo estimado: Tempo estimado de execução do Job.

Criar algoritmo que retorne um conjunto de arrays com as seguintes características:

1) Cada array do conjunto representa uma lista de Jobs a serem executados em sequência;
2) Cada array deve conter jobs que sejam executados em, no máximo, 8h;
3) Deve ser respeitada a data máxima de conclusão do Job;
4) Todos os Jobs devem ser executados dentro da janela de execução (data início e fim).

```


Exemplo de Massa de dados:
```
Janela de execução: 2019-11-10 09:00:00 até 2019-11-11 12:00:00

[
    {
        "ID": 1,
        "Descrição": "Importação de arquivos de fundos",
        "Data Máxima de conclusão": 2019-11-10 12:00:00,
        "Tempo estimado": 2 horas,
    },
    {
        "ID": 2,
        "Descrição": "Importação de dados da Base Legada",
        "Data Máxima de conclusão": 2019-11-11 12:00:00,
        "Tempo estimado": 4 horas,
    },
    {
        "ID": 3,
        "Descrição": "Importação de dados de integração",
        "Data Máxima de conclusão": 2019-11-11 08:00:00,
        "Tempo estimado": 6 horas,
    },
]
```

Output esperado
```
[
    [1, 3],
    [2]
]
```

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
