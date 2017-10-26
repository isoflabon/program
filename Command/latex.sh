#!/bin/sh
# LaTexをコマンド1つでコンパイルするようにする
# 目標:ファイルを指定しなくてもディレクトリ内texファイルが1つのみであればそのファイルをコンパイル

# ファイル名の取得
file=`ls | grep *.tex`
# コンパイル
platex $file > make.log
# 拡張子を取り除いたファイル名 
f=`echo $file | sed 's/\.[^\.]*$//'`

echo $f
dvipdfmx $f

while :
do
	printf "『作業ファイル(.texと.pdfを除く)』 を消しますか?[y or n] => "
	read ans
	case "$ans" in
		"y")
			rm $f.log $f.dvi $f.aux
			break;;
		"n")
			echo "完了しました"
			break;;
	esac
done
