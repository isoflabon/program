import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.Math;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Collections;

public class Mosaic extends Applet implements Runnable{
  Thread thread = null;
  // image用のobject
  Image img,new_img;
  int w,h;
  int random;
  int xi = 3; //縦の分割数
  int yi = 3; //横の分割数
  int pix[],new_pix[];
  int count = 0;

  public void init(){
    //イメージを取得
    img = getImage(getCodeBase(),"./sample4.jpg");
    //ロードをMediaTrackerを使うことで先に終わらせた
    MediaTracker mt = new MediaTracker(this);
    // リストにイメージの追加
    mt.addImage(img, 0);
    try{
       //指定された識別子をもつイメージのロードの開始
       mt.waitForID(0);
    } catch (InterruptedException e){}
     w = img.getWidth(this);//イメージの幅を取得
     h = img.getHeight(this);//イメージの高さを取得
    /*新しい配列の作成*/
     pix = new int[w*h];
     try {
       PixelGrabber pg = new PixelGrabber(img,0,0,w,h,pix,0,w);
       pg.grabPixels();
     }catch (InterruptedException e){return;}
       thread = new Thread(this);
       thread.start();
  }

  public void paint(Graphics g){
    // 分割用に配列用意
    if((w%xi==0) && (h%yi==0)){
      new_pix = new int[xi * yi];
      if(count == 1){
        setPix();
        count ++;
      }else{
        changePix();
      }
      MemoryImageSource mimg2 = new MemoryImageSource(w,h,pix,0,w);
      MemoryImageSource mimg = new MemoryImageSource(xi,yi,new_pix,0,xi);
      new_img = createImage(mimg); //新しいイメージの生成
      img = createImage(mimg2);

      MediaTracker mt = new MediaTracker(this);
      // リストにイメージの追加
      mt.addImage(new_img, 0);
      mt.addImage(img,1);
      try{
         //指定された識別子をもつイメージのロードの開始
         mt.waitForID(0);
         mt.waitForID(1);
      } catch (InterruptedException e){return;}
      if(count == 0){
        g.drawImage(img, 20, 0, w/2, h/2, this);
        count++;
      }else{
        g.drawImage(new_img, 20, 0, w/2, h/2, this);
      }
    }else{
      g.drawString("分割数が正しくありません",10,20);
    }
  }

  public void changePix(){
    ArrayList<Integer> list = new ArrayList<Integer>();

    // listに値を入れる。この段階では昇順
    for(int i = 0 ; i <= 8 ; i++) {
        list.add(i);
        // System.out.println(list.get(i));
    }
    // シャッフルして、順番を変える
    Collections.shuffle(list);
    int j = 0;
    int new_w = w / xi;
    int new_h = h / yi;
    for(int y=0;y < h;y+=new_h){
      for(int x=0;x < w;x+=new_w){
        // ランダムな場所でモザイクを移動させる
        new_pix[list.get(j++)] = pix[y*w+x];
      }
    }
  }
  public void setPix(){
      int i = 0;
      int new_w = w / xi;
      int new_h = h / yi;
      for(int y=0;y < h;y+=new_h){
        for(int x=0;x < w;x+=new_w){
          new_pix[i++] = pix[y*w+x];
        }
      }
  }
  public void update(Graphics g){
    Dimension size = getSize();
    g.setColor(Color.white);
    g.fillRect(0, 0, size.width - 1, size.height - 1);
    paint(g);
  }

  public void run(){
    while(true){
      // repaintでupdate()を実行する
      repaint();
      try{
        Thread.sleep(5000);
      }catch (InterruptedException e){}

    }
  }

}
