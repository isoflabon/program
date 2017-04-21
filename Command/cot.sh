#!/bin/sh

file=$1

if [ -e $file ]; then
	open -a  CotEditor $file
else

while :
do
	echo "『 $file 』 is not exist. Create?[y or n] ==> "
	read ans
	case "$ans" in
		"y")
			touch $file
			open -a CotEditor $file
			break;;
		"n")
			echo "Miss. Not open the $file!"
			break;;
	esac
done
fi