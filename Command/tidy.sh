#!/bin/sh
 ############################################################
#### .拡張子名  になっていないものは消えることがあるので注意！　  ####
 ############################################################

# 拡張子ごとにディレクトリを作成しExtend以下に拡張子の名前で格納
# 既存のディレクトリはそのまま

# Extendディレクトリがない場合のみ作成
extend="Extend"
if [ -e $extend ]; then
	echo $extend"以下に作成を行う"
else
	echo "Extend Directoryを作成"
	mkdir $extend
fi

# ls の結果を1行ずつ読み込む
ls -1 | while read filename
do
# echo "これから制御文 $filename"
	if [ -d $filename ]; then
		echo  "$filename はDirectoryなので現状維持" 
	else
		# 拡張子の取り出し
		ext=`echo $filename | sed 's/^.*\.\([^\.]*\)$/\1/'`
		if [ -e $extend/$ext ]; then
			# ディレクトリ存在する場合
			mv $filename $extend/$ext
		else
			# ディレクトリ存在しない場合
			# 拡張子の名前のディレクトリを作成
			echo "新規で" $ext "作成"
	    	mkdir $extend/$ext
		fi
		mv *.$ext $extend/$ext
	fi
done
