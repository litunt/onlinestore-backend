FROM adoptopenjdk:11-jre-hotspot

COPY /build/libs/online-store-0.0.1-SNAPSHOT.jar online-store-app.jar
ENTRYPOINT ["java","-jar","online-store-app.jar"]
EXPOSE 3030
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
