# See https://spring.io/guides/gs/spring-boot-docker/
FROM frolvlad/alpine-oraclejdk8:slim
env TOOLS_DIR /tools
RUN mkdir $TOOLS_DIR
RUN \
        wget http://mirror.rise.ph/apache/maven/maven-3/3.5.3/binaries/apache-maven-3.5.3-bin.zip && \
        unzip apache-maven-3.5.3-bin.zip -d $TOOLS_DIR
env PATH $TOOLS_DIR/apache-maven-3.5.3/bin:$PATH
COPY . /app
WORKDIR /app
RUN mkdir ~/.m2 && mv maven_repository ~/.m2/repository
RUN mvn clean install
#ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar target/student-api.jar" ]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/student-api.jar"]
