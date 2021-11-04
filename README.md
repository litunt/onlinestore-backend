# onlinestore-backend
Online store backend for test exercise

## How to run application
- being in project root folder execute
   1. `docker-compose -f docker-compose.postgres.yml up`
   2. `docker-compose -f docker-compose.liquibase.yml up`

- if you want to check database connectivity, open e.g. CMD and execute 
`psql -h onlinestore-db -p 5432 -U store_user -d store_db`