FROM registry.access.redhat.com/ubi8/ubi:8.6
WORKDIR /work/
COPY . .

RUN yum -y install gcc \
    && yum -y install gcc glibc-devel zlib-devel \
    && yum -y install wget \
    && wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.3.0/graalvm-ce-java17-linux-amd64-22.3.0.tar.gz \
    && gunzip graalvm-ce-java17-linux-amd64-22.3.0.tar.gz \
    && tar xvf graalvm-ce-java17-linux-amd64-22.3.0.tar \
    && rm -f graalvm-ce-java17-linux-amd64-22.3.0.tar \
    && export PATH=/work/graalvm-ce-java17-22.3.0/bin:$PATH \
    && export JAVA_HOME=/work/graalvm-ce-java17-22.3.0 \
    && gu install native-image \
    && ./mvnw clean \
    && ./mvnw package -Puber \
    && mv target/pfp-service-runner.jar application.jar

EXPOSE $PORT

ENV PATH=/work/graalvm-ce-java17-22.3.0/bin:$PATH
ENV export JAVA_HOME=/work/graalvm-ce-java17-22.3.0

CMD ["java","-jar","application.jar", "-Dquarkus.http.host=0.0.0.0"]
