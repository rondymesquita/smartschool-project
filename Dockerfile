FROM ubuntu:14.04

RUN apt update &&\
	apt install -y\
	firefox \
	wget \
	curl \
	git \
	openjdk-7-jdk \
	openjdk-7-doc \
	openjdk-7-jdk-headless \
	openjdk-7-jre \
	unzip

ENV JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
ENV JAVAHOME=$JAVA_HOME
ENV PATH=$PATH:$JAVA_HOME/bin

RUN mkdir /opt/gradle
RUN wget https://services.gradle.org/distributions/gradle-4.3-bin.zip -P /opt/gradle
RUN unzip -d /opt/gradle /opt/gradle/gradle-4.3-bin.zip
ENV PATH=$PATH:/opt/gradle/gradle-4.3/bin

RUN curl -sL https://deb.nodesource.com/setup_8.x | bash -
RUN apt-get install -y nodejs
