#!/bin/sh
cd ./src/main/java/net/nokok/karaffe/parser
rm *.java
java -cp ../../../../../../../lib/javacc.jar jjtree karaffe.jjt
java -cp ../../../../../../../lib/javacc.jar javacc karaffe.jj
