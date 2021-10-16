FROM openjdk:12.0.2

EXPOSE 8080

ADD ./build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar", "-XX:+UseG1GC", "-Xms1024M", "-Xmx1024M", "/app.jar"]