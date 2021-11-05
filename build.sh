echo "Starting to build Online Store Application!"
./gradlew clean build
docker build -t online-store-image .