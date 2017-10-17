# zaifのAPIを用いて現在の価格を取得しslackにメッセージとして通知するプログラム
require 'rubygems'
require 'slack/api'
require 'slack'
require 'faraday'
require 'net/http'
require 'uri'
require 'json'

Slack.configure do |config|
  config.token = ""
end

# monacoinのjpyでのlastの価格をapi表示 
uri = URI.parse('https://api.zaif.jp/api/1/ticker/mona_jpy')
#  json形式でレスポンス受け取り
res = Net::HTTP.get_response(uri)
result = JSON.parse(res.body)
# 現在のMonacoinの価格のみを知らせる
alert="Mona_price: " + result['last'].to_s


# monacoinのjpyでのlastの価格をapi表示 
uri = URI.parse('https://api.zaif.jp/api/1/ticker/zaif_jpy')
#  json形式でレスポンス受け取り
res = Net::HTTP.get_response(uri)
result = JSON.parse(res.body)
# 現在のMonacoinの価格のみを知らせる
alert2="Zaif_price: " + result['last'].to_s


## channelにメッセージをPostする
Slack.chat_postMessage(
  channel: '#btc_channel',
  username: 'Mona&Zaif_Price',
  text: alert+"\n"+alert2
)


## channelにファイルをアップロードする
# Slack.files_upload(
#   file: Faraday::UploadIO.new('test.png', 'image/png'),
#   channels: '#btc_channel',
#   username: 'test',
#   initial_comment: 'file upload'
# )