import java.io.*;
import java.lang.Object;

class Main{
  public static void main(String args[]) throws IOException{
    String url = "https://coincheck.com/api/rate/btc_jpy";
    // データを取ってくる
    Client data = new Client();
    String out = data.connect(url);
    System.out.println("整形前: "+out);
    // 取ってきたデータを整形しIntegerに変換
    CutInfo cutInfo = new CutInfo(out);
    String result = cutInfo.cutRate();
    int rate = Integer.parseInt(result);
    // Integer型のrateを表示
    System.out.println(rate);
  }


}
