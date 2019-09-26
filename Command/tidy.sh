#!/bin/sh
# 拡張子ごとにディレクトリを作成しExtend以下に拡張子の名前で格納
# 既存のディレクトリはそのまま

# Extendディレクトリがない場合のみ作成
extend="Extend"
if [ -e $extend ]; then
	echo "\tINFO: $extend 以下にデータを整理します"
else
	echo "\tCREATE: Extendディレクトリを作成します"
	mkdir $extend
fi

# ls の結果を1行ずつ読み込む
ls -1 | while read filename
do
	# 空白を含んだファイルをSkip
	if [ -d "$filename" ]; then
		echo  "\tSKIP: $filename はDirectoryです"
		continue
	fi
	
	# 空白を取り除き拡張子の取り出し
	ext=`echo $filename | sed 's/ //g' | sed 's/^.*\.\([^\.]*\)$/\1/'`
	if [ $ext = "$filename" ]; then echo "\tSKIP: $filename には拡張子がありません"; continue; fi
	
	if [ ! -d $extend/$ext ]; then
		echo "\tCREATE: $ext ディレクトリを作成します"
		mkdir $extend/$ext
	fi

	echo "\tMOVE: $extend/$ext ディレクトリへ $filename を移動させます"
	mv "$filename" $extend/$ext
done
