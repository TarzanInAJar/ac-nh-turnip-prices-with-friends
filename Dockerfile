FROM maven:3-jdk-11 AS build
WORKDIR /usr/src/app
COPY ac-nh-turnip-prices-with-friends/pom.xml .
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]
COPY ac-nh-turnip-prices-with-friends/src src/
COPY ac-nh-turnip-prices-with-friends-ui/ src/main/resources/static/
RUN mvn clean package

FROM openjdk:11
COPY --from=build /usr/src/app/target/ac-nh-turnip-prices-with-friends-*.jar /usr/app/turnips-with-friends.jar  
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/turnips-with-friends.jar"]  

