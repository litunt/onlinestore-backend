echo "Starting to run Online Store Application Docker containers!"
cd docker
docker-compose -f docker-compose.postgres.yml up
docker-compose -f docker-compose.liquibase.yml up
docker-compose -f docker-compose.app.yml up