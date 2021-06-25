#!/bin/sh

for file in `find ./ -name "*"`;
do
echo convering : $file
iconv -f GB2312 -t utf-8 $file > $file.t
mv $file.t $file
done
echo DONE
