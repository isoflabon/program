#!/bin/sh

ls -1 
# num=`ls -1 | wc -l`
# echo $num
# numの数だけループさせる
ls -1 | while read filename
do
echo "これから制御文 $filename"
if [ -d $filename ]; then
	echo "$filename はディレクトリ"
else
	# 拡張子の取り出し
	ext=`echo $filename | sed 's/^.*\.\([^\.]*\)$/\1/'`
	if [ -e $ext ]; then
		# 存在する場合
		mv $filename $ext
	else
		# 存在しない場合
		# 拡張子の名前のディレクトリを作成
    	mkdir $ext
		mv $filename $ext
	fi
fi
done