# onlinestore-backend
Online store backend for test exercise

## Technology stack
1. Java 11
2. PostgreSQL
3. Liquibase
4. Gradle
5. Lombok
6. Spring Boot
7. Docker
8. Jenkins
9. AWS EC2 service

## How to run application

### Being in project root folder execute
  1. `./gradlew clean build` to build executable project .jar
  2. `docker build -t online-store-image .` to build docker image for application (used in compose file later)
  3. `cd docker` change directory to the one that contains docker-compose files and execute these one by one
  4. `docker-compose -f docker-compose.postgres.yml up` to start database container
  5. `docker-compose -f docker-compose.liquibase.yml up` to start liquibase container (stops automatically after performing update)
  6. `docker-compose -f docker-compose.app.yml up` to start application container

### Database PSQL console: 
- to get into psql console of created database (from docker database container CLI, e.g. using Docker Desktop)

`psql -h onlinestore-db -p 5432 -U store_user -d store_db`

password: *123456*

### Debug mode
if you want to run application container with debugger mode enabled

1. add line
`command: java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar online-store-app.jar` in `docker-compose.app.yml` file inside container specifications
2. Edit Configurations... -> add Remote JVM Debug -> enter `-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005` under Command line arguments

**NB!** I could not set up running tests properly along with running application in Docker container due to shortage of time, but you can still fide some tests written.