#!/bin/bash

dir=`pwd`
cd ./parserfiles
ls *.jjt | sort -n | xargs cat > ../src/main/java/net/nokok/karaffe/parser/karaffe.jjt
cd $dir
