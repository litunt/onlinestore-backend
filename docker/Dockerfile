FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} online-store-app.jar
ENTRYPOINT ["java","-jar","/online-store-app.jar"]