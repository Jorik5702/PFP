####
# This Dockerfile is used in order to build a container that runs the Quarkus application in native (no JVM) mode.
#
# Before building the container image run:
#
# ./mvnw package -Pnative
#
# Then, build the image with:
#
# docker build -f src/main/docker/Dockerfile.native -t quarkus/code-with-quarkus .
#
# Then run the container using:
#
# docker run -i --rm -p 8080:8080 quarkus/code-with-quarkus
#
###
FROM registry.access.redhat.com/ubi8/ubi:8.6
WORKDIR /work/
COPY . .

RUN yum -y install gcc \
    && yum -y install gcc glibc-devel zlib-devel \
    && export PATH=/work/graalvm-ce-java17-22.3.0/bin:$PATH \
    && export JAVA_HOME=/work/graalvm-ce-java17-22.3.0 \
    && gu install native-image \
    && ./mvnw clean \
    && ./mvnw package -Pnative

#RUN ./mvnw clean
#RUN ./mvnw package -Pnative

#RUN chown 1001 /work \
#    && chmod "g+rwX" /work \
#    && chown 1001:root /work
COPY target/*-runner /work/application

EXPOSE $PORT

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
