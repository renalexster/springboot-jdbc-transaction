FROM maven:alpine
MAINTAINER Renato Campelo <renato.campelo@ati.pe.gov.br>

COPY . /opt/app/

WORKDIR /opt/app

RUN ["mvn", "clean", "package"]

CMD ["mvn", "clean", "spring-boot:run"]