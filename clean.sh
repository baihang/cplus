#!/bin/sh
if [ -e *.out ];then
rm ./*.out;
echo "clean out  complete";
else
echo "cleaned";
fi

if [ -e *.dSYM ];then
rm -r ./*.dSYM;
echo "clean dSYM complete";
fi
