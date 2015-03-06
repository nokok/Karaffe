#!/bin/sh

./genparser.sh
cd ./src/main/java/karaffe/compiler/phase/parser
rm AST*.java TokenMgrError.java ParseException.java Token.java SimpleCharStream.java karaffe.jj karaffe.html
jarPath='../../../../../../../lib/javacc.jar'
java -cp $jarPath jjtree karaffe.jjt
java -cp $jarPath javacc karaffe.jj
java -cp $jarPath jjdoc karaffe.jj
cd ../../../../../../../
mvn clean compile test
