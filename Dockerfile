FROM openjdk:10
MAINTAINER nokok <nokok.kz@gmail.com>

WORKDIR /karaffe

ENV LANG C.UTF-8

ADD . .

RUN ./gradlew installDist --no-daemon

RUN ln -s /karaffe/build/install/Karaffe-compiler/bin/krfc /krfc

CMD ["/karaffe/build/install/Karaffe-compiler/bin/krfc"]
