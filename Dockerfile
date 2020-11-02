FROM dkron/dkron
WORKDIR /root/scheduler
COPY Scheduler.java /root/scheduler
COPY Startup.jar /root/scheduler

RUN apk add openjdk8

ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $JAVA_HOME/bin:$PATH

RUN javac Scheduler.java

