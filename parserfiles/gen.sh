#!/usr/local/bin/fish
java -jar ../lib/jflex-1.6.0.jar karaffe.jflex
java -jar ../lib/java-cup-11b.jar -dump_grammar -parser Parser -interface karaffe.cup
rm Lexer.java~
mv *.java ../src/main/java/karaffe/compiler/
