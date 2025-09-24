FROM eclipse-temurin:21.0.8_9-jdk

WORKDIR /root

COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY mvnw /root/mvnw

RUN ./mvnw dependency:go-offline

COPY src /root/src

RUN ./mvnw clean install -DskipTests

EXPOSE 80

ENTRYPOINT [ "java","-jar","/root/target/demo-0.0.1-SNAPSHOT.jar"]