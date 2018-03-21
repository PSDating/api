FROM jboss/base-jdk:8

EXPOSE 8080

COPY target/psdating-0.0.1-SNAPSHOT.jar /opt/

CMD ["java", "-jar", "/opt/psdating-0.0.1-SNAPSHOT.jar"]