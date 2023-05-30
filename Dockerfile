FROM selenium/standalone-chrome:88.0

USER root

RUN apt-get update
RUN apt-get install openjdk-8-jdk -y

ENV GRADLE_VERSION 5.6.4

WORKDIR /

# get gradle and supporting libs
RUN wget https://downloads.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -O gradle.zip; \
    unzip gradle.zip; \
    rm gradle.zip

ENV PATH=${PATH}:/gradle-${GRADLE_VERSION}/bin

USER seluser

WORKDIR /usr/src/app

COPY ./src ./src
COPY ./build.gradle ./build.gradle

# RUN gradle --no-daemon build -x test

COPY ./chromeTest.sh ./chromeTest.sh

USER root
RUN chmod +x ./chromeTest.sh
RUN chown -R seluser:seluser /usr/src/app
USER seluser

COPY VERSION /

ENTRYPOINT ["/usr/src/app/chromeTest.sh"]

# RUN ./gradlew chromeHeadlessTest
