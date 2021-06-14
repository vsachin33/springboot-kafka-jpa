FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=application/target/application-0.0.1-SNAPSHOT.jar

# cd /opt/app
#WORKDIR /opt/app

# cp application/target/application-0.0.1-SNAPSHOT.jar /opt/app/app.jar
#COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
#ENTRYPOINT ["java","-jar","app.jar"]



ENV DCNM_HOME_DIR=/opt/dcnm
ENV DCNM_APP_BIN=$DCNM_HOME_DIR/appbin


COPY docker-entrypoint.sh $DCNM_APP_BIN/
COPY ${JAR_FILE} $DCNM_APP_BIN/

WORKDIR $DCNM_HOME_DIR

RUN pwd && ls -al $DCNM_APP_BIN && ls -al . && chmod +x ./appbin/docker-entrypoint.sh

WORKDIR $DCNM_APP_BIN

ENTRYPOINT ["sh","./docker-entrypoint.sh"]


