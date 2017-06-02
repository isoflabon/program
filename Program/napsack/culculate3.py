# coding: utf-8
# cp は リストの長さに合わせて変わる変数
def round_robin(string, cp) :
	num = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}
	weight = [3,6,5,4,8,5,3,4,3,5,6,4,8,7,11,8,14,6,12,4]
	price = [7,12,9,7,13,8,4,5,3,10,7,5,6,14,5,9,6,12,5,9]
	max_price = 0	#最高の価格
	best_weight =0 #制限重量内での価値が一番高い時のバッグ内重量
	limit_weight = 55 #制限重量
	bag_price = 0 #バッグ内の価値
	bag_weight = 0 #バッグ内の重量
	cnt = 0			#リストの1つあたりの長さに応じて処理するためのカウント変数
	best_combi = [] #最適な組み合わせを保存
	
	for num in string :
		print num
		for number in num : 
			print number
			bag_weight += weight[number-1]
			bag_price += price[number -1]
			print "重さ => " + str(bag_weight)
			print "価格 => " + str(bag_price)
			cnt += 1
			if bag_weight > limit_weight :
				bag_weight = 0
				bag_price = 0
				cnt =0
				print "Limit over !! 探索終了"
				break
			if max_price < bag_price  and cnt == cp :
				max_price = bag_price
				best_combi = num 
				best_weight = bag_weight
				print "価値更新 => " + str(max_price)
# 			if cnt >= cp :	#ここはstringの数字の部分
				bag_weight = 0
				bag_price = 0
				cnt =0
	return [max_price, best_combi,best_weight]