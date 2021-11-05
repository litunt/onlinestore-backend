FROM adoptopenjdk:11-jre-hotspot

COPY /build/libs/online-store-0.0.1-SNAPSHOT.jar online-store-app.jar
ENTRYPOINT ["java","-jar","online-store-app.jar"]
EXPOSE 8080