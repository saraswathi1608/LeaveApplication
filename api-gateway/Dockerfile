# Use Open JDK - 17
FROM openjdk:17.0.1-jdk-slim

# Define Application Home
ENV APP_HOME /api-gateway

RUN mkdir $APP_HOME

# Define Application Logs
RUN mkdir $APP_HOME/logs

VOLUME $APP_HOME/logs

# Define Working Directory
WORKDIR $APP_HOME

# Set Timezone
ENV TZ=Asia/Kolkata
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Read Arguments from maven config
ARG revision
ARG ssl_certificate_file_name


# Copy Self Signed CygNet OSS Certificate
COPY target/classes/$ssl_certificate_file_name $ssl_certificate_file_name

# Copy Start-Up Application
COPY target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar
# Execute Docker Build
ENTRYPOINT exec java $JAVA_OPTS -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintFlagsFinal -XX:NativeMemoryTracking=summary -cp lib/ -jar api-gateway.jar

