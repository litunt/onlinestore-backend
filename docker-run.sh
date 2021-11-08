echo 'Docker build deploy'
docker build -t online-store-image .
cd docker
docker-compose -f docker-compose.postgres.yml up
docker-compose -f docker-compose.liquibase.yml up
docker-compose -f docker-compose.app.yml up