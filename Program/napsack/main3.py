# coding: utf-8
import itertools
import culculate3

#それぞれのデータ
num = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}
max_price = 0	#最高の価格
best_weight = 0 #制限重量内での価値が一番高い時のバッグ内重量
best_combi = [] #最適な組み合わせを保存
string = []	#すべての場合のコンビネーションをstring+数で保存
number = 0 #総当たりの総数
#numからコンビネーションのリストを作成
i = 0

for i  in num :
	string = "string" + str(i) 
	string = list(itertools.combinations(num, i))
	number += len(string)
	print("荷物"+str(i)+"つの時の探索開始")
	printer = culculate3.round_robin(string,i)
	print("荷物"+str(i)+"つの時の探索終了")
	if printer[0] == 0 or printer[1] == [] :
		print("全ての場合で重量オーバー\n")
	else :
		print("荷物"+str(i)+"つの時の最大の価格は"+ str(printer[0]))
		print("その時の組み合わせは"+str(printer[1])+"\n")
		if max_price <= printer[0] :
			max_price = printer[0]
			best_combi = printer[1]
			best_weight = printer[2]
print("総当たりによる解は荷物の組み合わせ" + str(best_combi) + "の時に")
print("重さ：" + str(best_weight)+ "\t価値：" + str(max_price))
print("総当たりの総数は" + str(number))