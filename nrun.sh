#!/bin/sh
cd ./src/main/java/net/nokok/karaffe/javacc/ast
rm KaraffeParser.java KaraffeParserConstants.java KaraffeParserTokenManager.java ParseException.java SimpleCharStream.java Token.java TokenMgrError.java 
java -cp ../../../../../../../../lib/javacc.jar javacc karaffe.jj

