import java.io.*;

class Main{
  public static void main(String args[]) throws IOException{
    String url = "https://coincheck.com/api/ticker";
    // データを取ってくる
    Client client = new Client();
    client.connect(url);
  }
}
