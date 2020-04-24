FROM maven:3-jdk-11 AS build
COPY ac-nh-turnip-prices-with-friends/src /usr/src/app/src 
COPY ac-nh-turnip-prices-with-friends/pom.xml /usr/src/app
COPY ac-nh-turnip-prices-with-friends-ui/ /usr/src/app/src/main/resources/static/ 
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:11
COPY --from=build /usr/src/app/target/ac-nh-turnip-prices-with-friends-*.jar /usr/app/turnips-with-friends.jar  
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/turnips-with-friends.jar"]  

