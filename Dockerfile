FROM maven:3-jdk-11 AS build
WORKDIR /usr/src/app
COPY ./pom.xml .
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]
COPY . .
RUN mvn clean package

FROM openjdk:11
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/ac-nh-turnip-prices-with-friends-*.jar ./turnips-with-friends.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","./turnips-with-friends.jar"]

