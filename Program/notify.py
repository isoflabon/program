# coding: utf-8
# 仮想通貨の価格をLineへ通知

import requests
import urllib
import urllib2
import pprint
import json
from time import sleep
from datetime import datetime

# LINE Notice api設定
line_notify_token = 'アクセストークン'
line_notify_api = 'https://notify-api.line.me/api/notify'

# flag = Trueは通知可能, Flaseは通知不可

# xrp設定価格
xrp_min_price = 100
xrp_max_price = 150
xrp_flag= True
# xem設定価格
xem_min_price = 80
xem_max_price = 120
xem_flag = True
# zaif設定価格
zaif_min_price = 1
zaif_max_price = 4
zaif_flag = True
# mona設定価格
mona_min_price = 1300
mona_max_price = 2000
mona_flag = True
# ETH設定価格
eth_min_price = 80000
eth_max_price = 110000
eth_flag = True

while True :
	#  -------------------- rateの取得 ---------------------
	try : 
		tmp = requests.get("https://coincheck.com/api/rate/xrp_jpy")
		xrp = float(tmp.json()["rate"])
		
		tmp = requests.get("https://api.zaif.jp/api/1/last_price/xem_jpy")
		xem = tmp.json()["last_price"]
		
		tmp = requests.get('https://api.zaif.jp/api/1/last_price/zaif_jpy')
		zaif = tmp.json()["last_price"]
		
		tmp = requests.get('https://api.zaif.jp/api/1/last_price/mona_jpy')
		mona = tmp.json()["last_price"]
		
		tmp = requests.get('https://api.zaif.jp/api/1/last_price/eth_jpy')
		eth = tmp.json()["last_price"]
	# -----------------------------------------------------------
	except ValueError :
		datetime.now().strftime("%Y/%m/%d %H:%M:%S")
	finally:
		sleep(5)
		xrp_message = "現在のXRPの価格: " + str(xrp)
		xem_message = "現在のXEMの価格: " + str(xem)
		zaif_message = "現在のZaif_tokenの価格: " + str(zaif)
		mona_message = "現在のMONAの価格: " + str(mona)
		eth_message = "現在のETHの価格: " + str(eth)
		
		# LINEnoticeにメッセージ
		
		# -------------------- xrpに関する通知
		if xrp_min_price >= xrp and xrp_flag :
			message = "価格を下回りました\n" + str(xrp_message)
			payload = {'message': message}
			headers = {'Authorization': 'Bearer ' + line_notify_token}  # 発行したトークン
			line_notify = requests.post(line_notify_api, data=payload, headers=headers)
			xrp_flag = False
		if xrp_min_price >= xrp and xrp_flag == False :
			xrp_flag = True
			
		if xrp >= xrp_max_price and xrp_flag :
			message =  "価格を上回りました\n" + str(xrp_message)
			payload = {'message': message}
			headers = {'Authorization': 'Bearer ' + line_notify_token}  # 発行したトークン
			line_notify = requests.post(line_notify_api, data=payload, headers=headers)
			xrp_flag = False
	# 		xrpの価格が設定価格を下回るとTrueに変更(通知できる状態)
		if xrp_max_price >= xrp and xrp_flag == False :
			xrp_flag = True
				
			
		# -------------------- xemに関する通知
		if xem_min_price >= xem and xem_flag :
			message = "価格を下回りました\n" + str(xem_message)
			payload = {'message': message}
			headers = {'Authorization': 'Bearer ' + line_notify_token}  # 発行したトークン
			line_notify = requests.post(line_notify_api, data=payload, headers=headers)
			xem_flag = False
		if xem_min_price >= xem and xem_flag == False :
			xem_flag = True
			
		if xem >= xem_max_price and xem_flag :
			message =  "価格を上回りました\n" + str(xem_message)
			payload = {'message': message}
			headers = {'Authorization': 'Bearer ' + line_notify_token}  # 発行したトークン
			line_notify = requests.post(line_notify_api, data=payload, headers=headers)
			xem_flag = False
		# 	xemの価格が設定価格を下回るとTrueに変更(通知できる状態)
		if xem_max_price >= xem and xem_flag == False :
			xem_flag = True
		
			
		# -------------------- Zaifに関する通知
		if zaif_min_price >= zaif and zaif_flag :
			message = "価格を下回りました\n" + str(zaif_message)
			payload = {'message': message}
			headers = {'Authorization': 'Bearer ' + line_notify_token}  # 発行したトークン
			line_notify = requests.post(line_notify_api, data=payload, headers=headers)
			zaif_flag = False
		if zaif_min_price >= zaif and zaif_flag == False :
			zaif_flag = True
			
		if zaif >= zaif_max_price and zaif_flag :
			message =  "価格を上回りました\n" + str(zaif_message)
			payload = {'message': message}
			headers = {'Authorization': 'Bearer ' + line_notify_token}  # 発行したトークン
			line_notify = requests.post(line_notify_api, data=payload, headers=headers)
			zaif_flag = False
		# 	zaifの価格が設定価格を下回るとTrueに変更(通知できる状態)
		if zaif_max_price >= zaif and zaif_flag == False :
			zaif_flag = True
			
		# -------------------- Monaに関する通知
		if mona_min_price >= mona and mona_flag :
			message = "価格を下回りました\n" + str(mona_message)
			payload = {'message': message}
			headers = {'Authorization': 'Bearer ' + line_notify_token}  # 発行したトークン
			line_notify = requests.post(line_notify_api, data=payload, headers=headers)
			mona_flag = False
		if mona_min_price >= mona and mona_flag == False :
			mona_flag = True
			
		if mona >= mona_max_price and mona_flag :
			message =  "価格を上回りました\n" + str(mona_message)
			payload = {'message': message}
			headers = {'Authorization': 'Bearer ' + line_notify_token}  # 発行したトークン
			line_notify = requests.post(line_notify_api, data=payload, headers=headers)
			mona_flag = False
		# 	monaの価格が設定価格を下回るとTrueに変更(通知できる状態)
		if mona_max_price >= mona and mona_flag == False :
			mona_flag = True
			
		# -------------------- ETHに関する通知
		if eth_min_price >= eth and eth_flag :
			message = "価格を下回りました\n" + str(eth_message)
			payload = {'message': message}
			headers = {'Authorization': 'Bearer ' + line_notify_token}  # 発行したトークン
			line_notify = requests.post(line_notify_api, data=payload, headers=headers)
			eth_flag = False
		if eth_min_price >= eth and eth_flag == False :
			eth_flag = True
			
		if eth >= eth_max_price and eth_flag :
			message =  "価格を上回りました\n" + str(eth_message)
			payload = {'message': message}
			headers = {'Authorization': 'Bearer ' + line_notify_token}  # 発行したトークン
			line_notify = requests.post(line_notify_api, data=payload, headers=headers)
			eth_flag = False
		# 	ethの価格が設定価格を下回るとTrueに変更(通知できる状態)
		if eth_max_price >= eth and eth_flag == False :
			eth_flag = True

