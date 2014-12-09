#!/bin/sh
cd ./src/main/java/net/nokok/karaffe/parser
rm AST*.java TokenMgrError.java ParseException.java Token.java SimpleCharStream.java karaffe.jj
jarPath='../../../../../../../lib/javacc.jar'
java -cp $jarPath jjtree karaffe.jjt
java -cp $jarPath javacc karaffe.jj
java -cp $jarPath jjdoc karaffe.jj
