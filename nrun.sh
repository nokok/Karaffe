#!/bin/sh
cd ./src/main/java/net/nokok/karaffe/parser
java -cp ../../../../../../../lib/javacc.jar jjtree karaffe.jjt
java -cp ../../../../../../../lib/javacc.jar javacc karaffe.jj
rm AST*.java TokenMgrError.java ParseException.java Token.java SimpleCharStream.java karaffe.jj
