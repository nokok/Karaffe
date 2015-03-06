#!/bin/bash

dir=`pwd`
cd ./parserfiles
ls *.jjt | sort -n | xargs cat > ../src/main/java/karaffe/compiler/phase/parser/karaffe.jjt
cd $dir
