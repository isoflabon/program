import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

class Client {
    private String url;

    public Client(){

    }
    public void connect(String url) throws IOException{
      // コネクションを確立
      HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
      InputStream in = conn.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      StringBuilder out = new StringBuilder();
      String line;

      while ((line = reader.readLine()) != null) {
          out.append(line);
      }

      System.out.println(out.toString());
      reader.close();
    }
}
