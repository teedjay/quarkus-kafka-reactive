# quarkus-kafka-reactive project

This project uses [Quarkus](https://quarkus.io/), the Supersonic Subatomic Java Framework.

## Start a local Kafka server
```
docker run -d \
   --name kafka26 \
   -p 9092:9092 \
   -e KAFKA_ADVERTISED_HOST_NAME=localhost \
   -e KAFKA_CREATE_TOPICS="test-input:1:8" \
   -e KAFKA_CREATE_TOPICS="test-processing:1:8" \
   -e KAFKA_CREATE_TOPICS="test-output:1:8" \
   blacktop/kafka:2.6
```

## Running the application in dev mode and Java 15 with preview features enabled

You can run your application in dev mode that enables live coding using:
```
export _JAVA_OPTIONS="--enable-preview"
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `quarkus-kafka-reactive-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/quarkus-kafka-reactive-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/quarkus-kafka-reactive-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.