# onlinestore-backend
Online store backend for test exercise

## How to run application
- being in project root folder execute
  1. `./gradlew clean build` to build executable project .jar
  2. `docker build -t online-store-image .` to build docker image for application (used in compose file later)
  3. `cd docker` change directory to the one that contains docker-compose files and execute these one by one
  4. `docker-compose -f docker-compose.postgres.yml up` to start database container
  5. `docker-compose -f docker-compose.liquibase.yml up` to start liquibase container (stops automatically after performing update)
  6. `docker-compose -f docker-compose.app.yml up` to start application container

- to get into psql console of created database: 
`psql -h onlinestore-db -p 5432 -U store_user -d store_db`